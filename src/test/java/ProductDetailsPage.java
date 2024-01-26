import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class ProductDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    private final By productPageContentLocator = By.className("product-page");
    //private final By priceHistorySectionLocator = By.className("price-history");
    private final By priceHistoryButtonLocator = By.xpath("//button[@class='price-history__btn']");
    private final By popUpHistoryPriceLocator = By.xpath("//div[contains(@class,'popup-history-price')]");
    private final By priceBlockLocator = By.xpath("//div[@class='price-block'][1]");
    private final By likeButtonLocator = By.xpath("//button[@aria-label='Добавить в избранное']");

    private final By addButtonLocator = By.xpath("//button[contains(@class,'order__btn-buy')]");

    public void openPage() {
        driver.get("https://www.wildberries.ru/catalog/64890494/detail.aspx");
    }

    public void openAndWaitForContent() {
        openPage();
        waitForContent();
    }

    public void waitForContent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(productPageContentLocator));
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public void clickPriceHistoryButton() {
        wait.until(ExpectedConditions.elementToBeClickable(priceHistoryButtonLocator));
        WebElement priceHistorySection = driver.findElement(priceHistoryButtonLocator);
        priceHistorySection.click();
    }

    public boolean popUpHistoryPriceVisible() {
        try {
            WebElement popUp = driver.findElement(popUpHistoryPriceLocator);
            wait.until(ExpectedConditions.visibilityOf(popUp));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkPriceBlockVisible() {
        try {
            WebElement price = driver.findElement(priceBlockLocator);
            wait.until(ExpectedConditions.visibilityOf(price));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean likeButtonVisible() {
        try {
            WebElement button = driver.findElement(likeButtonLocator);
            wait.until(ExpectedConditions.visibilityOf(button));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void hoverOverLike() {
        Actions actions = new Actions(driver);
        WebElement like = driver.findElement(likeButtonLocator);
        actions.moveToElement(like).perform();
    }

}
