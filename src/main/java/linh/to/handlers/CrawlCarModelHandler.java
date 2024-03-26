package linh.to.handlers;

import linh.to.constants.WebElementConstants;
import linh.to.entities.BrandEntity;
import linh.to.entities.ModelEntity;
import linh.to.utils.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CrawlCarModelHandler {
    private static final String JOB_NAME = "Job crawlCarModels: ";
    private static final int LOG_LEVEL = 3;

    public static List<ModelEntity> crawl(WebDriver driver, BrandEntity brand) {
        LoggerUtil.printJob(JOB_NAME + brand.getName(), LOG_LEVEL);
        Long start = System.currentTimeMillis();

        List<ModelEntity> cars = new ArrayList<>();
        driver.get(brand.getContentURL());
        List<WebElement> elements = driver.findElements(By.className(WebElementConstants.MODEL_ELEMENT_CLASS_NAME));
        for (WebElement element : elements) {
            String name = element.findElement(By.tagName(WebElementConstants.ELEMENT_STRONG_TAG_NAME)).getText();
            String imgURL = element.findElement(By.tagName(WebElementConstants.ELEMENT_IGM_TAG_NAME)).getAttribute(WebElementConstants.ELEMENT_SRC_ATTRIBUTE);
            String contentURL = element.getAttribute(WebElementConstants.ELEMENT_HREF_ATTRIBUTE);
            cars.add(new ModelEntity(name, imgURL, contentURL));
        }

        LoggerUtil.printTimeComsuming("Time Consumed", start, LOG_LEVEL);
        return cars;
    }
}
