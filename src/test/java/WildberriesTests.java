import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class WildberriesTests {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @Before
    public void init() {
        webDriver = new ChromeDriver();
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(200));
    }

    // Тест 1 - проверяем открытие сайта https://www.wildberries.ru/
    @Test
    public void checkThatWebSiteOpens() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        boolean mainLogoExists = mainPage.mainLogoExists();
        Assert.assertTrue(mainLogoExists);
    }

    //Тест 2 - проверяем название вкладки
    @Test
    public void checkPageTitle() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        String actualTitle = mainPage.getPageTitle();
        String expectedTitle = "Wildberries – Интернет-магазин модной одежды и обуви";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    //Тест 3 - на главной странице вводим в поле поиска название товара, проверяем, что открылась соответствующая страница
    @Test
    public void checkSearchInput() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        SearchResultPage newPage = mainPage.clickAndSendKeysSearchInput("pusheen");
        String url = newPage.getCurrentUrl();
        String expectedUrl = "https://www.wildberries.ru/catalog/0/search.aspx?search=pusheen";
        Assert.assertEquals(expectedUrl, url);
    }

    //Тест 4 - проверяем наличие дропдаун списка в поле поиска на главной странице
    @Test
    public void checkDropDownListInSearchInput() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        mainPage.clickSearchInput();
        boolean dropDownListVisible = mainPage.dropDownListVisible();
        Assert.assertTrue(dropDownListVisible);
    }

    //Тест 5 - на главной странице проверяем наличие и функционал кнопки На верх
    @Test
    public void checkQuickNavButtonVisibilityAndFunction() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        mainPage.scrollDown();
        mainPage.quickNavButtonClick();
        boolean cityHeaderVisible = mainPage.cityHeaderVisible();
        Assert.assertTrue(cityHeaderVisible);
    }

    //Тест 6 - на главной странице проверяем кликабельность кнопки "Работа в Wildberries" и открытие соответствующей страницы
    @Test
    public void openVacanciesPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        VacanciesPage vacanciesPage = mainPage.clickVacancies();
        String url = vacanciesPage.getCurrentUrl();
        String expectedUrl = "https://www.wildberries.ru/services/trudoustroystvo";
        Assert.assertEquals(expectedUrl, url);
    }

    //Тест 7 - на странице Работа в Wildberries проверяем кликабельность ссылки на соц. сеть Вконтакте
    @Test
    public void clickVkLinkOnVacanciesPage() {
        VacanciesPage page = new VacanciesPage(webDriver);
        page.openAndWaitForMainPageContent();
        page.scrollToFooter();
        VkontaktePage vkontaktePage = page.vkLinkClick();
        String url = vkontaktePage.getUrl();
        String expectedUrl = "https://vk.com/public9695053";
        Assert.assertEquals(expectedUrl, url);
    }

    //Тест 8 - из меню на главной странице выбираем джинсы женские, проверяем переход на соответствующую страницу
    @Test
    public void clickJeansOnForWomanPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        mainPage.clickMenu();
        mainPage.dropDownListFromBurgerMenuVisible();
        mainPage.clickForWomenInBurgerMenu();
        ForWomenPage page = new ForWomenPage(webDriver);
        page.clickJeans();
    }

    //Тест 9 - на странице Джинсы женские сортируем товары по рейтингу
    @Test
    public void sortJeansByRatingOnJeansForWomanPage() {
        //clickJeansOnForWomanPage();
        JeansForWomanPage page = new JeansForWomanPage(webDriver);
        page.openAndWaitFiltersBlock();
        page.clickPopularityFilter();
        page.clickRadioButtonRating();
        String url = page.getUrl();
        String expectedUrl = "https://www.wildberries.ru/catalog/zhenshchinam/odezhda/dzhinsy-dzhegginsy?sort=rate&page=1";
        Assert.assertEquals(expectedUrl, url);
    }

    //Тест 10 - на странице Джинсы женские проверяем наличие формы "Все фильтры"
    @Test
    public void checkAllFiltersOnJeansForWomanPage() {
        JeansForWomanPage page = new JeansForWomanPage(webDriver);
        page.openAndWaitFiltersBlock();
        page.clickAllFilters();
        Boolean visibility = page.allFiltersSlideVisible();
        Assert.assertTrue(visibility);
    }

    //Тест 11 - кликаем на первую карточку на странице с результатами поиска
    @Test
    public void clickFirstCardInProductCatalog() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        SearchResultPage newPage = mainPage.clickAndSendKeysSearchInput("чайник электрический");
        newPage.waitForCatalog();
        ProductDetailsPage productDetailsPage = newPage.clickFirstCard();
        productDetailsPage.waitForContent();
        String currentUrl = productDetailsPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("detail.aspx"));
    }

    //Тест 12 - на странице товара проверяем наличие поп-ап окна "Ценовая история". !Тест flaky, так как кнопка "Ценовая история" переодичекси не прогружается
    @Test
    public void checkPopUpWindowForHistoryPrice() {
        ProductDetailsPage page = new ProductDetailsPage(webDriver);
        page.openAndWaitForContent();
        page.clickPriceHistoryButton();
        Boolean visibility = page.popUpHistoryPriceVisible();
        Assert.assertTrue(visibility);
    }

    //Тест 13 - кликаем на баннер на главной странице
    @Test
    public void clickMainBannerOnMainPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        String mainPageUrl = mainPage.getCurrentUrl();
        BannerResultPage newPage = mainPage.clickMainBannerOnMainPage();
        String bannerResultPageUrl = newPage.getUrl();
        Assert.assertNotEquals(mainPageUrl, bannerResultPageUrl);
    }

    //Тест 14 - проверяем наличие кнопок "Предыдущий" и "Следующий" на главном баннере
    @Test
    public void checkSwiperButtonOnMainBanner() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        mainPage.hoverOverMainBanner();
        Boolean visibilitySwiperButtonPrev = mainPage.swiperButtonPrevOnMainBannerVisible();
        Boolean visibilitySwiperButtonNext = mainPage.swiperButtonNextOnMainBannerVisible();
        Assert.assertTrue(visibilitySwiperButtonPrev);
        Assert.assertTrue(visibilitySwiperButtonNext);
    }

    //Тест 15 - проверяем всплывающее окно со списком стран и валютой при наведении курсора на кнопку "Войти"
    @Test
    public void changeCurrency() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        mainPage.hoverOverSignIn();
        mainPage.clickCurrencyChanger();
        mainPage.popUpCountryWithCurrencyVisible();
        Boolean visibility = mainPage.popUpCountryWithCurrencyVisible();
        Assert.assertTrue(visibility);
    }

    //Тест 16 - пытаемся ввести некорректный номер телефона и проверяем наличии предупреждения об ошибке
    @Test
    public void checkIncorrectPhoneNumberDuringAutorisation() {
        AutorisationPage page = new AutorisationPage(webDriver);
        page.openPage();
        page.inputPhoneNumber("1234567");
        String actualValidationSign = page.checkFieldValidationError();
        String expectedValidationSign = "Некорректный формат номера";
        Assert.assertEquals(expectedValidationSign, actualValidationSign);
    }

    //Тест 17 - на странице продукта присутствует символ лайка
    @Test
    public void checkLikeButtonOnProductDetailsPage() {
        ProductDetailsPage page = new ProductDetailsPage(webDriver);
        page.openAndWaitForContent();
        Boolean visibility = page.likeButtonVisible();
        Assert.assertTrue(visibility);
    }

    //Тест 18 - на странице с результатами поиска раскладка карточек с товарами меняется с 5-ти товаров на 4-ре
    @Test
    public void checkGoodGridOnSearchResultPage() {
        SearchResultPage page = new SearchResultPage(webDriver);
        page.openPage();
        page.waitForCatalog();
        page.clickCardSizeBigButton();
        String currentUrl = page.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cardsize=big"));
    }

    //Тест 19 - проверяем сообщение о пустой корзине при отсутствие в ней товаров
    @Test
    public void checkEmptyBasket() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openAndWaitForMainPageContent();
        BasketPage page= mainPage.clickBasketButton();
        String validationSign = page.checkSignOfEmptyBasket();
        Assert.assertTrue(validationSign.contains("В корзине пока пусто"));
    }

    //Тест 20 - на странице с результатами поиска нажать на кнопку покупки в карточке первого товара
    @Test
    public void pressQuickBasketButtonOnFirstProduct() {
        SearchResultPage page = new SearchResultPage(webDriver);
        page.openPage();
        page.waitForCatalog();
        page.clickQuickBasketButtonOnFirstProduct();
        String sign = page.checkChangesOfQuickBasketButtonOnFirstProduct();
        Assert.assertTrue(sign.contains("В корзине"));
    }

//    @After
//    public void closedDriver() {
//        webDriver.close();
//    }

}

    //Тест 13 - увеличить количество товара в корзине
    //Тест 14 - удалить товар из корзины




