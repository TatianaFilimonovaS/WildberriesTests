import io.opentelemetry.sdk.trace.internal.data.ExceptionEventData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JeansForWomanPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    //private final By popularityFilterLocator = By.xpath("//button[contains(@class, 'btn--sorter')]");
    private final By filtersBlockLocator = By.xpath("//div[contains(@class, 'filters-block__dropdown')]");
    private final By popularityFilterLocator = By.xpath("//div[@class='filters-block__container']//button[text()=\"По популярности\"]");
    private final By radioButtonRatingLocator = By.xpath("//span[text()=\"По рейтингу\"]");
    private final By allFiltersFilterLocator = By.xpath("//div[@class='filters-block__container']//button[text()=\"Все фильтры\"]");
    private final By allFiltersSlideLocator = By.xpath("//div[@class='filters-desktop__content']");

    public JeansForWomanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    public void openPage() {
        driver.get("https://www.wildberries.ru/catalog/zhenshchinam/odezhda/dzhinsy-dzhegginsy");
    }

    public void openAndWaitFiltersBlock() {
        openPage();
        wait.until(ExpectedConditions.presenceOfElementLocated(filtersBlockLocator));
    }

    public boolean popularityFilterLocatorVisible() {
        try {
            WebElement popularityFilter = driver.findElement(popularityFilterLocator);
            wait.until(ExpectedConditions.visibilityOf(popularityFilter));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public boolean allFiltersSlideVisible() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(allFiltersSlideLocator));
            WebElement allFiltersSlide = driver.findElement(allFiltersSlideLocator);
            wait.until(ExpectedConditions.visibilityOf(allFiltersSlide));
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    public void clickPopularityFilter() {
        WebElement popilarityFilter = driver.findElement(popularityFilterLocator);
        popularityFilterLocatorVisible();
        popilarityFilter.click();
    }

    public void clickRadioButtonRating() {
        WebElement radioButton = driver.findElement(radioButtonRatingLocator);
        radioButton.click();
    }

    public String getUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public void clickAllFilters() {
        wait.until(ExpectedConditions.presenceOfElementLocated(allFiltersFilterLocator));
        WebElement allFilters = driver.findElement(allFiltersFilterLocator);
        wait.until(ExpectedConditions.visibilityOf(allFilters));
        allFilters.click();
    }

}
