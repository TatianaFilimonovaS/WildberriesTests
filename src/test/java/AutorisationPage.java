import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutorisationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AutorisationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    private final By phoneNumberInput = By.xpath("//input[@class='input-item']");
    private final By fieldValidationError = By.xpath("//span[contains(@class,'field-validation-error')]");

    public void openPage() {
        driver.get("https://www.wildberries.ru/security/login?returnUrl=https%3A%2F%2Fwww.wildberries.ru%2Fcatalog%2F98365489%2Fdetail.aspx");
    }

    public void clickInputPhoneNumber() {
        WebElement input = driver.findElement(phoneNumberInput);
        input.click();
    }

    public void inputPhoneNumber(String s) {
        wait.until(ExpectedConditions.presenceOfElementLocated(phoneNumberInput));
        WebElement input = driver.findElement(phoneNumberInput);
        input.click();
        input.sendKeys(s);
        input.sendKeys(Keys.ENTER);
    }

    public String checkFieldValidationError() {
        WebElement sign = driver.findElement(fieldValidationError);
        String text = sign.getText();
        return text;
    }


}
