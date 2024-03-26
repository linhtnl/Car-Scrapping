package linh.to.handlers;

import linh.to.constants.WebElementConstants;
import linh.to.entities.ModelGenerationEntity;
import linh.to.entities.ModelEntity;
import linh.to.utils.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CrawlCarModelGenerationHandler {
    private static final String JOB_NAME = "Job crawlCarGenerationModel: ";

    private static final String SUB_JOB_NAME = "Job getCarGeneration";

    private static final int LOG_LEVEL = 4;

    public static List<ModelGenerationEntity> crawl(WebDriver driver, ModelEntity model) {
        LoggerUtil.printJob(JOB_NAME + model.getGeneralName(), LOG_LEVEL);
        Long timeStart = System.currentTimeMillis();

        List<ModelGenerationEntity> generationModels = new ArrayList<>();
        driver.get(model.getContentURL());

        /* The red badge means the car generation is older than year 2020. */
        List<WebElement> generationModelElements = driver.findElements(By.className(WebElementConstants.GENERATION_MODEL_RED_BADGE_CLASS_NAME));
        for (WebElement element : generationModelElements) {
            generationModels.add(getCarGeneration(element));
        }

        /* The green badge means the car generation is greater than or equal year 2020. */
        generationModelElements = driver.findElements(By.className(WebElementConstants.GENERATION_MODEL_GREEN_BADGE_CLASS_NAME));
        for (WebElement element : generationModelElements) {
            generationModels.add(getCarGeneration(element));
        }

        LoggerUtil.printTimeComsuming("Time Comsumed", timeStart, LOG_LEVEL);
        return generationModels;
    }

    private static ModelGenerationEntity getCarGeneration(WebElement element) {
        LoggerUtil.printJob(SUB_JOB_NAME, LOG_LEVEL + 1);
        /*
         * Car model web element is a table row. The header contains the image and the link of the car's generation. The body contains car's information which are years, car type, power and dimensions.
         * */
        WebElement header = element.findElement(By.tagName(WebElementConstants.ELEMENT_TH_TAG_NAME));
        WebElement body = element.findElement(By.tagName(WebElementConstants.ELEMENT_TD_TAG_NAME));

        String modelGenerationName = header.findElement(By.tagName(WebElementConstants.ELEMENT_STRONG_TAG_NAME)).getText();
        String modelGenerationHref = header.findElement(By.tagName(WebElementConstants.ELEMENT_A_TAG_NAME)).getAttribute(WebElementConstants.ELEMENT_HREF_ATTRIBUTE);
        String modelGenerationImgUrl = header.findElement(By.tagName(WebElementConstants.ELEMENT_IGM_TAG_NAME)).getAttribute(WebElementConstants.ELEMENT_SRC_ATTRIBUTE);
        String year = extractYear(body);
        String description = extractDescription(body);
        String carType = extractCarType(body);

        return new ModelGenerationEntity().setName(modelGenerationName)
                .setContentURL(modelGenerationHref)
                .setImageURL(modelGenerationImgUrl)
                .setGeneration(year).setDesciption(description).setCarType(carType);

    }

    private static String extractCarType(WebElement body) {
        try {
            return body.findElement(By.className(WebElementConstants.GENERATION_MODELS_CHAOS_CLASS_NAME)).getText();
        } catch (Exception e) {
            // Don't have car type value.
            return WebElementConstants.NOT_APPLICABLE;
        }
    }

    private static String extractDescription(WebElement body) {
        try {
            return body.findElement(By.className(WebElementConstants.ELEMENT_SPAN_TAG_NAME)).getText();
        } catch (Exception e) {
            // Don't have description value.
            return WebElementConstants.NOT_APPLICABLE;
        }
    }

    private static String extractYear(WebElement body) {
        try {
            return body.findElement(By.className(WebElementConstants.GENERATION_MODELS_END_CLASS_NAME)).getText();
        } catch (NoSuchElementException e) {
            try {
                return body.findElement(By.className(WebElementConstants.GENERATION_MODELS_END_CLASS_NAME)).getText();
            } catch (NoSuchElementException ex) {
                // Don't have Year value.
                return WebElementConstants.NOT_APPLICABLE;
            }
        }
    }
}
