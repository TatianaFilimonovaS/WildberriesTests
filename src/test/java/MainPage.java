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
    private final By footerLocator = By.className("footer");
    private final By jobsLocator = By.xpath("//a[@data-wba-header-name=\"Job\"]");
    private final By menuLocator = By.xpath("//button[contains(@class,'nav-element__burger')]");
    private final By kidsLineInBurgerMenuLocator = By.xpath("//li[@data-menu-id='115']");
    private final By womenLineInBurgerMenuLocator = By.xpath("//a[@href='https://www.wildberries.ru/catalog/zhenshchinam']");
    private final By dropDownListFromBurgerMenuLocator = By.xpath("//div[contains(@class,'menu-burger__main')]");
    private final By jeansInBurgerLocator = By.xpath("(//a[text()=\"Джинсы\"])[1]");
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

    public boolean dropDownListVisible() {
//        try {
//            WebElement list = driver.findElement(dropDownListLocator);
//            wait.until(ExpectedConditions.visibilityOf(list));
//            return true;
//        }catch (Exception e) {
//            return false;
//        }
        return checkWebElementVisibility(dropDownListLocator);
    }

    public boolean cityHeaderVisible() {
//        try {
//            WebElement cityHeader = driver.findElement(cityHeaderLocator);
//            wait.until(ExpectedConditions.visibilityOf(cityHeader));
//            return true;
//        }catch (Exception e) {
//            return false;
//        }
        return checkWebElementVisibility(cityHeaderLocator);
    }

    public boolean dropDownListFromBurgerMenuVisible() {
        return checkWebElementVisibility(dropDownListFromBurgerMenuLocator);
    }

    public boolean popUpCountryWithCurrencyVisible() {
        return checkWebElementVisibility(popUpCountryWithCurrencyLocator);
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

    public void scrollToFooter() {
        WebElement footer = driver.findElement(footerLocator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", footer);
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

    public void clickKidsinBurgerMenu() {
        WebElement kidsLine = driver.findElement(kidsLineInBurgerMenuLocator);
        kidsLine.click();
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

    public void clickSwiperButtonOnMainBanner() {
        WebElement swiperButton = driver.findElement(swiperButtonPrevOnMainBannerLocator);
        swiperButton.click();
    }

    public void hoverOverMainBanner(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(mainBannerLocator)).perform();
    }

    public Boolean swiperButtonPrevOnMainBannerVisible() {
        try {
            WebElement swiperButton = driver.findElement(swiperButtonPrevOnMainBannerLocator);
            wait.until(ExpectedConditions.visibilityOf(swiperButton));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Boolean swiperButtonNextOnMainBannerVisible() {
        try {
            WebElement swiperButton = driver.findElement(swiperButtonNextOnMainBannerLocator);
            wait.until(ExpectedConditions.visibilityOf(swiperButton));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public void hoverOverSignIn(){
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
