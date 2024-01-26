import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BannerResultPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BannerResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    public String getUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }
}
