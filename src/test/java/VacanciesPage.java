import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class VacanciesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By footerLocator = By.className("footer");
    private final By vkLinkLocator = By.xpath("//li[@data-wba-footer-name=\"Vk\"]//a");
    private final By mainPageContentLocator = By.className("service-page");

    public VacanciesPage(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    public void openPage() {
        driver.get("https://www.wildberries.ru/services/trudoustroystvo");
    }

    public void openAndWaitForMainPageContent() {
        openPage();
        wait.until(ExpectedConditions.presenceOfElementLocated(mainPageContentLocator));
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public void scrollToFooter() {
        WebElement footer = driver.findElement(footerLocator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", footer);
    }

    public VkontaktePage vkLinkClick() {
        //String vacanciesPage =driver.getWindowHandle();
        WebElement vkLink = driver.findElement(vkLinkLocator);
        vkLink.click();
        switchTab(1);


        //ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        //windowHandles.remove(vacanciesPage);
        //String vkTabWindowHandle = windowHandles.get(1);
        //driver.switchTo().window(vkTabWindowHandle);



        return new VkontaktePage(driver);
    }

    public void switchTab(int numTab) {
        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        String oldTab = windowHandles.get(numTab-1);
        String tab = windowHandles.get(numTab);
        driver.switchTo().window(tab);
        windowHandles.remove(oldTab);
    }
}
