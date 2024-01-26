import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By mainLogoLocator = By.className("nav-element__logo");
    private final By mainPageContentLocator = By.className("main-page");
    private final By searchInputLocator = By.id("searchInput");
    private final By dropDownListLocator = By.className("search-catalog__autocomplete");
    private final By quickNavButtonLocator = By.className("btn-quick-nav");
    private final By cityHeaderLocator = By.className("header__top");
    private final By jobsLocator = By.xpath("//a[@data-wba-header-name=\"Job\"]");
    private final By menuLocator = By.xpath("//button[contains(@class,'nav-element__burger')]");
    private final By womenLineInBurgerMenuLocator = By.xpath("//a[@href='https://www.wildberries.ru/catalog/zhenshchinam']");
    private final By dropDownListFromBurgerMenuLocator = By.xpath("//div[contains(@class,'menu-burger__main')]");
    private final By mainBannerLocator = By.xpath("//div[contains(@class,'main-page__banner')]");
    private final By swiperButtonNextOnMainBannerLocator = By.xpath("//button[@class='swiper-button-next']");
    private final By swiperButtonPrevOnMainBannerLocator = By.xpath("//button[@class='swiper-button-prev']");
    private final By signInLocator = By.xpath("//div[@class='navbar-pc__item']");
    private final By currencyChangerLocator = By.xpath("//button[contains(@class,'profile-menu__link--currency')]");
    private final By popUpCountryWithCurrencyLocator = By.xpath("//div[contains(@class,'popup-country')]");
    private final By basketLocator = By.xpath("//div[contains(@class,'j-item-basket')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    public void openPage() {
        driver.get("https://www.wildberries.ru/");
    }

    public void openAndWaitForMainPageContent() {
        openPage();
        wait.until(ExpectedConditions.presenceOfElementLocated(mainPageContentLocator));
    }

    public void quickNavButtonClick() {
        WebElement button = driver.findElement(quickNavButtonLocator);
        button.click();
    }

    public boolean mainLogoExists() {
        try {
            driver.findElement(mainLogoLocator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkWebElementVisibility(By locator) {
        try {
            WebElement webElement = driver.findElement(locator);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean dropDownListVisible() {
        return checkWebElementVisibility(dropDownListLocator);
    }

    public boolean cityHeaderVisible() {
        return checkWebElementVisibility(cityHeaderLocator);
    }

    public boolean dropDownListFromBurgerMenuVisible() {
        return checkWebElementVisibility(dropDownListFromBurgerMenuLocator);
    }

    public boolean popUpCountryWithCurrencyVisible() {
        return checkWebElementVisibility(popUpCountryWithCurrencyLocator);
    }

    public Boolean swiperButtonPrevOnMainBannerVisible() {
        return checkWebElementVisibility(swiperButtonPrevOnMainBannerLocator);
    }

    public Boolean swiperButtonNextOnMainBannerVisible() {
        return checkWebElementVisibility(swiperButtonNextOnMainBannerLocator);
    }

    public String getPageTitle() {
        String title = driver.getTitle();
        return title;
    }

    public void clickSearchInput() {
        WebElement input = driver.findElement(searchInputLocator);
        input.click();
    }

    public SearchResultPage clickAndSendKeysSearchInput(String s) {
        WebElement input = driver.findElement(searchInputLocator);
        input.click();
        input.sendKeys(s);
        input.sendKeys(Keys.ENTER);
        return new SearchResultPage(driver);
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public VacanciesPage clickVacancies() {
        WebElement vacancies = driver.findElement(jobsLocator);
        vacancies.click();
        return new VacanciesPage(driver);
    }

    public void clickMenu() {
        WebElement menu = driver.findElement(menuLocator);
        menu.click();
    }

    public ForWomenPage clickForWomenInBurgerMenu() {
        WebElement forWomenLine = driver.findElement(womenLineInBurgerMenuLocator);
        forWomenLine.click();
        return new ForWomenPage(driver);
    }

    public BannerResultPage clickMainBannerOnMainPage() {
        WebElement banner = driver.findElement(mainBannerLocator);
        banner.click();
        return new BannerResultPage(driver);
    }

    public void hoverOverMainBanner() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(mainBannerLocator)).perform();
    }

    public void hoverOverSignIn() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(signInLocator)).perform();
    }

    public void clickCurrencyChanger() {
        WebElement button = driver.findElement(currencyChangerLocator);
        button.click();
    }

    public BasketPage clickBasketButton() {
        WebElement button = driver.findElement(basketLocator);
        button.click();
        return new BasketPage(driver);
    }

}
