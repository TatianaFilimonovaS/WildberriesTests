import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForWomenPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By jeansInBurgerLocator = By.xpath("(//a[text()=\"Джинсы\"])[1]");

    public ForWomenPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    public void clickJeans() {
        WebElement jeansLine = driver.findElement(jeansInBurgerLocator);
        jeansLine.click();
    }

}
