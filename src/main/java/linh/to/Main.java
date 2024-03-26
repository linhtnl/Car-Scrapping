package linh.to;

import com.google.gson.Gson;
import linh.to.constants.FileNameConstants;
import linh.to.entities.ModelGenerationEntity;
import linh.to.handlers.CrawlCarBrandHandler;
import linh.to.handlers.CrawlCarModelGenerationHandler;
import linh.to.handlers.CrawlCarModelHandler;
import linh.to.handlers.WebDriverHandler;
import linh.to.entities.BrandEntity;
import linh.to.entities.ModelEntity;
import linh.to.utils.FileUtils;
import linh.to.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static WebDriver driver;

    private static final int LOG_LEVEL = 1;
    public static void main(String[] args) {
        // Clear files
        FileUtils.emptyResourceFolder();

        Long timeStart = System.currentTimeMillis();

        driver = WebDriverHandler.getInstance().openHomePage();
        Set<String> carTypes = new HashSet<>();

        try {
            List<BrandEntity> brands = crawlBrandsAndStoreFile();
            for (BrandEntity brand : brands) {
                List<ModelEntity> models = crawlModelAndStoreFile(brand);
                for (ModelEntity model : models) {
                    List<ModelGenerationEntity> generationModels = crawlModelGenerationsAndStoreFile(model, brand.getName());
                    model.setCarGeneration(generationModels);
                    extractCarType(generationModels, carTypes);
                }
                brand.setCars(models);
                writeFile(brand, FileNameConstants.BRANDS_FILE_NAME);
                writeFile(carTypes, FileNameConstants.CAR_TYPES_FILE_NAME);

            }
        } finally {
            WebDriverHandler.getInstance().destroy();
            LoggerUtil.printTimeComsuming("Total time", timeStart, LOG_LEVEL);
        }
    }

    private static void extractCarType(List<ModelGenerationEntity> generationModels, Set<String> carTypes) {
        for (ModelGenerationEntity modelGeneration : generationModels) {
            carTypes.add(modelGeneration.getCarType());
        }
    }

    private static List<ModelGenerationEntity> crawlModelGenerationsAndStoreFile(ModelEntity model, String brandName) {
        List<ModelGenerationEntity> generationModels = CrawlCarModelGenerationHandler.crawl(driver, model);
        String fileName = MessageFormat.format(FileNameConstants.CAR_MODEL_GENERATION_FILE_NAME, brandName, model.getCarGeneration());
        writeFile(generationModels, fileName);

        return generationModels;
    }

    private static List<BrandEntity> crawlBrandsAndStoreFile() {
        List<BrandEntity> brands = CrawlCarBrandHandler.crawl(driver);
        writeFile(brands, FileNameConstants.BRAND_GENERAL_FILE_NAME);
        return brands;
    }

    private static List<ModelEntity> crawlModelAndStoreFile(BrandEntity brand) {
        List<ModelEntity> models = CrawlCarModelHandler.crawl(driver, brand);
        String fileName = MessageFormat.format(FileNameConstants.CAR_MODELS_FILE_NAME, brand);
        writeFile(models, fileName);
        return models;
    }


    private static void writeFile(Object obj, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        FileUtils.writeFile(json, fileName);
    }

}