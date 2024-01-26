import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    private final By catalogPageLocator = By.id("catalog");
    private final By firstCardLocator = By.xpath("//article[contains(@class,product-card)][1]");
    ////div[@class='catalog-page']//div[@class='product-card-list']//article[contains(@class,product-card)][1]
    private final By cardSizeBigButtonLocator = By.xpath("//a[contains(@class,'card-sizes__btn--big')]");
    private final By quickBasketButtonOnFirstProductLocator = By.xpath("//a[contains(@class,'product-card__add-basket')][1]");

    public void openPage() {
        driver.get("https://www.wildberries.ru/catalog/0/search.aspx?search=%D1%87%D0%B0%D0%B9%D0%BD%D0%B8%D0%BA%20%D1%8D%D0%BB%D0%B5%D0%BA%D1%82%D1%80%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9");
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    /*public boolean catalogVisible() {
        try {
            WebElement catalog = driver.findElement(catalogPageLocator);
            wait.until(ExpectedConditions.visibilityOf(catalog));
            return true;
        }catch (Exception e) {
            return false;
        }

    }*/

    public void waitForCatalog() {
        wait.until(ExpectedConditions.presenceOfElementLocated(catalogPageLocator));
    }

    public ProductDetailsPage clickFirstCard() {
        WebElement card = driver.findElement(firstCardLocator);
        card.click();
        return new ProductDetailsPage(driver);
    }

    public void clickCardSizeBigButton() {
        WebElement button = driver.findElement(cardSizeBigButtonLocator);
        button.click();
    }

    public void clickQuickBasketButtonOnFirstProduct() {
        WebElement button = driver.findElement(quickBasketButtonOnFirstProductLocator);
        wait.until(ExpectedConditions.visibilityOf(button));
        button.click();
    }

    public String checkChangesOfQuickBasketButtonOnFirstProduct() {
        WebElement button = driver.findElement(quickBasketButtonOnFirstProductLocator);
        wait.until(ExpectedConditions.textToBe(quickBasketButtonOnFirstProductLocator, "В корзине"));
        String text = button.getText();
        return text;
    }

}
