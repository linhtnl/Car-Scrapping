package linh.to.handlers;

import linh.to.constants.FileNameConstants;
import linh.to.constants.WebElementConstants;
import linh.to.entities.BrandEntity;
import linh.to.utils.FileUtils;
import linh.to.utils.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CrawlCarBrandHandler {
    private static final String JOB_NAME = "Job crawlCarBrands";
    private static final int LOG_LEVEL = 2;

    public static List<BrandEntity> crawl(WebDriver driver) {
        LoggerUtil.printJob(JOB_NAME, LOG_LEVEL);
        Long start = System.currentTimeMillis();

        List<BrandEntity> brandModels = new ArrayList<>();
        List<WebElement> brandElements = driver.findElements(By.className(WebElementConstants.BRAND_ELEMENT_CLASS_NAME));
        for (WebElement brandElement : brandElements) {
            String contentURL = brandElement.getAttribute(WebElementConstants.ELEMENT_HREF_ATTRIBUTE);
            String name = brandElement.findElement(By.tagName(WebElementConstants.ELEMENT_STRONG_TAG_NAME)).getText();
            String imageURL = brandElement.findElement(By.tagName(WebElementConstants.ELEMENT_IGM_TAG_NAME)).getAttribute(WebElementConstants.ELEMENT_SRC_ATTRIBUTE);
            brandModels.add(new BrandEntity(name, imageURL, contentURL));
        }

        LoggerUtil.printTimeComsuming("Time Consumed", start, LOG_LEVEL);
        FileUtils.writeFile(brandModels, FileNameConstants.BRAND_GENERAL_FILE_NAME);

        return brandModels;
    }
}
