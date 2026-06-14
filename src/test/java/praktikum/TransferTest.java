package praktikum;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import praktikum.pageobject.AccountProfilePage;
import praktikum.pageobject.LoginPage;
import praktikum.pageobject.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferTest {
    @RegisterExtension
    public final DriverExtension extension = new DriverExtension();

    private UserClient userClient;

    @BeforeEach
    public void setUp() {
        // Создаем пользователя для тестов
        userClient = new UserClient(Constants.EMAIL, Constants.PASSWORD, Constants.NAME);
        ValidatableResponse response = userClient.create();

        // Проверка успешного создания
        response.statusCode(200);
    }

    @AfterEach
    public void tearDown() {
        if (userClient != null) {
            userClient.deleteUser(); // Удаляем пользователя после теста
        }
    }

    @DisplayName("Переход с главной страницы в Личный кабинет по клику на Личный кабинет")
    @Test
    public void transferFromMainToPersonalAccountSuccessTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccountButton();

        var accountProfilePage = new AccountProfilePage(driver);

        assertTrue(accountProfilePage.isProfileButtonDisplayed(),
                "Кнопка Профиль не отображается в Личном кабинете»");
        assertTrue(accountProfilePage.isOrderHistoryButtonDisplayed(),
                "Кнопка История заказов не отображается в Личном кабинете»");
        assertTrue(accountProfilePage.isLogoutButtonDisplayed(),
                "Кнопка Выход не отображается в Личном кабинете»");
    }

    @DisplayName("Переход из личного кабинета в конструктор по клику на Конструктор")
    @Test
    public void transferFromPersonalAccountToConstructionSuccessTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccountButton();

        var accountProfilePage = new AccountProfilePage(driver);
        accountProfilePage.clickConstructorButton();

        assertTrue(mainPage.isConstructorHeaderDisplayed(),
                "Заголовок Соберите бургер не отображается на странице»");
        assertTrue(mainPage.areAllTabsDisplayed(),
                "Разделы конструктора не отображаются на странице»");
        assertTrue(mainPage.isCreateOrderButtonDisplayedAndClickable(),
                "Кнопка Оформить заказ не доступна после перехода из личного кабинета");
    }

    @DisplayName("Переход из личного кабинета в конструктор по клику на на логотип Stellar Burgers")
    @Test
    public void transferFromPersonalAccountToConstructionByLogoSuccessTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccountButton();

        var accountProfilePage = new AccountProfilePage(driver);
        accountProfilePage.clickLogo();

        assertTrue(mainPage.isConstructorHeaderDisplayed(),
                "Заголовок Соберите бургер не отображается на странице»");
        assertTrue(mainPage.areAllTabsDisplayed(),
                "Разделы конструктора не отображаются на странице»");
        assertTrue(mainPage.isCreateOrderButtonDisplayedAndClickable(),
                "Кнопка Оформить заказ не доступна после перехода из личного кабинета");
    }

    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Test
    public void exitFromPersonalAccountSuccessTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccountButton();

        var accountProfilePage = new AccountProfilePage(driver);
        accountProfilePage.clickLogoutButton();

        assertTrue(loginPage.isLoginHeaderDisplayed(),
                "Форма входа не отображается после выхода");
    }
}