package linh.to.handlers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverHandler {
    private static WebDriverHandler _instance;
    private WebDriver driver;
    private static final String CAR_PAGE = "https://www.auto-data.net/en/";

    private WebDriverHandler() {

    }

    public static WebDriverHandler getInstance() {
        if (_instance == null) {
            _instance = new WebDriverHandler();

        }
        return _instance;
    }

    public void destroy() {
        _instance = null;
        closeWebDriver();
    }

    public void closeWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver openHomePage() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.get(CAR_PAGE);
            driver.manage().window().maximize();
        }
        return driver;
    }


}
