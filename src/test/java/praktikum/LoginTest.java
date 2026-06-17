package praktikum;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import praktikum.pageobject.ForgotPasswordPage;
import praktikum.pageobject.LoginPage;
import praktikum.pageobject.MainPage;
import praktikum.pageobject.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
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

    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Test
    public void loginFromMainSuccessTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginToAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();

        userClient = new UserClient(Constants.EMAIL, Constants.PASSWORD);

        assertTrue(mainPage.isCreateOrderButtonDisplayedAndClickable(),
                "Кнопка Оформить заказ не доступна после входа");
    }

    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Test
    public void loginFromPersonalAccountSuccessTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();

        userClient = new UserClient(Constants.EMAIL, Constants.PASSWORD);

        assertTrue(mainPage.isCreateOrderButtonDisplayedAndClickable(),
                "Кнопка Оформить заказ не доступна после входа");
    }

    @DisplayName("Вход через кнопку в форме регистрации")
    @Test
    public void loginFromRegisterPageSuccessTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();

        var registerPage = new RegisterPage(driver);
        registerPage.clickLoginButton();

        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();

        userClient = new UserClient(Constants.EMAIL, Constants.PASSWORD);

        assertTrue(mainPage.isCreateOrderButtonDisplayedAndClickable(),
                "Кнопка Оформить заказ не доступна после входа");
    }

    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Test
    public void loginFromForgotPasswordPageSuccessTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordButton();

        var forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginButton();

        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();

        userClient = new UserClient(Constants.EMAIL, Constants.PASSWORD);

        assertTrue(mainPage.isCreateOrderButtonDisplayedAndClickable(),
                "Кнопка Оформить заказ не доступна после входа");
    }
}