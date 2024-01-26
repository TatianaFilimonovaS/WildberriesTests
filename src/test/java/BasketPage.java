import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasketPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    private final By signEmptyBasketLocator = By.xpath("//h1[contains(@class,'basket-empty__title')]");

    public String checkSignOfEmptyBasket() {
        wait.until(ExpectedConditions.presenceOfElementLocated(signEmptyBasketLocator));
        WebElement sign = driver.findElement(signEmptyBasketLocator);
        String text = sign.getText();
        return text;
    }
}
