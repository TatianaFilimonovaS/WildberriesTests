import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VkontaktePage {
    private final WebDriver driver;


    public VkontaktePage(WebDriver driver) {
        this.driver=driver;
    }

    public String getUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }
}
