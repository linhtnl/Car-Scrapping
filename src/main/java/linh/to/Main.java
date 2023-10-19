package linh.to;

import com.google.gson.Gson;
import linh.to.handler.CrawlHandler;
import linh.to.models.BrandModel;
import linh.to.models.CarModel;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        katalonTest();
//        Long timeStart = System.currentTimeMillis();
//        List<String> writtenBrands = getWrittenBrands();
//        try {
//            CrawlHandler.getInstance().initWebDriver();
//            List<BrandModel> brands = CrawlHandler.getInstance().crawlBrandModels();
////            writeBrands(brands);
//            for (BrandModel brand : brands) {
//                if (!writtenBrands.contains(brand.getName())) {
//                    // Refesh for long session
//                    CrawlHandler.getInstance().openWebDriver();
//                    Long start = System.currentTimeMillis();
//                    List<CarModel> cars = CrawlHandler.getInstance().crawlCarModels(brand);
//                    for (CarModel car : cars) {
//                        car.setCarGeneration(CrawlHandler.getInstance().crawlCarGenerationModel(car));
//                    }
//                    brand.setCars(cars);
//                    writeBrandModel(brand);
//                    CrawlHandler.getInstance().closeWebDriver();
//                    Long end = System.currentTimeMillis();
//                    System.out.println("Time consumed: " + DurationFormatUtils.formatDuration(end - start, "mm:ss,SSS"));
//                }
//            } //https://www.auto-data.net/en/lada-brand-140 eror
//            writeCarTypes();
//        } finally {
//            CrawlHandler.getInstance().destroy();
//            Long timeEnd = System.currentTimeMillis();
//            System.out.println("Total time: " + DurationFormatUtils.formatDuration(timeEnd - timeStart, "mm:ss,SSS"));
//        }

    }

    private static void katalonTest() {
        int number = 60;
        for (int i = 0; i < number; i++) {
            WebDriver driver = new FirefoxDriver();
            driver.get("https://www.google.com/");
            driver.quit();
        }
    }

    private static List<String> getWrittenBrands() {
        List<String> writtenBrands = new ArrayList<>();
        File folder = new File("src\\main\\resources");
        for (File f : folder.listFiles()) {
            if (!f.getName().equals("brands.json") && !f.getName().equals("car_types.json")) {
                writtenBrands.add(f.getName().replace(".json", ""));
            }
        }
        return writtenBrands;
    }

    private static void writeBrands(List<BrandModel> brands) {
        Gson gson = new Gson();
        String json = gson.toJson(brands);
        File f = new File("src\\main\\resources\\brands.json");
        writeFile(json, f);
    }

    private static void writeCarTypes() {
        Gson gson = new Gson();
        String json = gson.toJson(CrawlHandler.getInstance().getCarTypes());
        File f = new File("src\\main\\resources\\car_types.json");
        writeFile(json, f);
    }

    private static void writeFile(String json, File f) {
        try {
            if (f.exists()) {
                f.deleteOnExit();
            }
            f.createNewFile();
            FileUtils.writeStringToFile(f, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeBrandModel(BrandModel brand) {
        Gson gson = new Gson();
        String json = gson.toJson(brand);
        File f = new File("src\\main\\resources", brand.getName() + ".json");
        System.out.println("Write into file - " + f.getAbsolutePath());
        writeFile(json, f);
    }

}