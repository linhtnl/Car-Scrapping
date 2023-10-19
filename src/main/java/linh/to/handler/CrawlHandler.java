package linh.to.handler;

import linh.to.models.BrandModel;
import linh.to.models.CarGenerationModel;
import linh.to.models.CarModel;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CrawlHandler {
    private static CrawlHandler _instance;
    public WebDriver driver;

    private List<String> carTypes = new ArrayList<>();

    private CrawlHandler() {

    }

    public static CrawlHandler getInstance() {
        if (_instance == null) {
            _instance = new CrawlHandler();

        }
        return _instance;
    }

    public List<String> getCarTypes() {
        return carTypes;
    }

    public void destroy() {
        _instance = null;
        closeWebDriver();
    }

    public void initWebDriver() {
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        driver.get("https://www.auto-data.net/en/");
    }

    public void closeWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public void openWebDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
//            driver.manage().window().maximize();
        }
    }

    public List<BrandModel> crawlBrandModels() {
        List<BrandModel> brandModels = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.className("marki_blok"));
        for (WebElement element : elements) {
            String contentURL = element.getAttribute("href");
            String name = element.findElement(By.tagName("strong")).getText();
            String imageURL = element.findElement(By.tagName("img")).getAttribute("src");
            brandModels.add(new BrandModel(name, imageURL, contentURL));
        }
        return brandModels;
    }

    public List<CarModel> crawlCarModels(BrandModel brand) {
        System.out.println("-Job crawlCarModels: " + brand.getName());
        List<CarModel> cars = new ArrayList<>();
        driver.get(brand.getContentURL());
        List<WebElement> elements = driver.findElements(By.className("modeli"));
        for (WebElement element : elements) {
            String name = element.findElement(By.tagName("strong")).getText();
            String imgURL = element.findElement(By.tagName("img")).getAttribute("src");
            String contentURL = element.getAttribute("href");
            cars.add(new CarModel(name, imgURL, contentURL));
        }
        return cars;
    }

    public List<CarGenerationModel> crawlCarGenerationModel(CarModel car) {
        System.out.println("---Job crawlCarGenerationModel: " + car.getGeneralName());
        List<CarGenerationModel> generation = new ArrayList<>();
        driver.get(car.getContentURL());
        List<WebElement> elements = driver.findElements(By.className("lred"));
        for (WebElement element : elements) {
            generation.add(getCarGeneration(element));
        }
        elements = driver.findElements(By.className("lgreen"));
        for (WebElement element : elements) {
            generation.add(getCarGeneration(element));
        }
        return generation;
    }

    public CarGenerationModel getCarGeneration(WebElement element) {
        System.out.println("-----Job getCarGeneration");
        WebElement header = element.findElement(By.tagName("th"));

        WebElement body = element.findElement(By.tagName("td"));
        // Get Year | end or cur

        Integer[] years = extractYear(body);
        String des = extractDescription(body);
        String carType = extractCarType(body);

        if (!carTypes.contains(carType)) {
            carTypes.add(carType);
        }
        return new CarGenerationModel().setName(header.findElement(By.tagName("strong")).getText()).setContentURL(header.findElement(By.tagName("a")).getAttribute("href")).setImageURL(header.findElement(By.tagName("img")).getAttribute("src")).setFromYear(years[0]).setToYear(years[1]).setDesciption(des).setCarType(carType);
    }

    private String extractCarType(WebElement body) {
        try {
            return body.findElement(By.className("chas")).getText();
        } catch (Exception e) {
            if (!(e instanceof NoSuchElementException)) {
                System.err.println(e);
            } else {
                return "N/A";
            }
        }
        return "";
    }

    private String extractDescription(WebElement body) {
        String des = "";
        try {
            des = body.findElement(By.className("span")).getText();
        } catch (Exception e) {
            if (e instanceof NoSuchElementException) {
                des = "N/A";
            } else {
                System.err.println(e);
            }
        }
        return des;
    }

    private Integer[] extractYear(WebElement body) {
        String year = "";
        try {
            year = body.findElement(By.className("end")).getText();
        } catch (NoSuchElementException e) {
            try {
                year = body.findElement(By.className("cur")).getText();
            } catch (NoSuchElementException ex) {
                // Don't have Year value.
            }
        }
        int yearFrom = -1;
        int yearTo = -1;
        if (!year.isEmpty()) {
            String[] years = year.split("-");
            if (years.length == 2) {
                yearFrom = !years[0].trim().isEmpty() ? Integer.parseInt(years[0].trim()) : -1;
                yearTo = !years[1].trim().isEmpty() ? Integer.parseInt(years[1].trim()) : -1;
            }
        }
        return new Integer[]{yearFrom, yearTo};
    }
}
