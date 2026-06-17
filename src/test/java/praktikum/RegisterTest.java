package praktikum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import praktikum.pageobject.LoginPage;
import praktikum.pageobject.MainPage;
import praktikum.pageobject.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest {
    @RegisterExtension
    public final DriverExtension extension = new DriverExtension();

    private UserClient userClient;
    // Удаляем пользователя после теста
    @AfterEach
    public void tearDown() {
        if (userClient != null) {
            userClient.deleteUser();
        }
    }

    @DisplayName("Успешная регистрация")
    @Test
    public void registerSuccessTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();

        var registerPage = new RegisterPage(driver);
        registerPage.enterName(Constants.NAME);
        registerPage.enterEmail(Constants.EMAIL);
        registerPage.enterPassword(Constants.PASSWORD);
        registerPage.clickRegisterButton();

        // Инициализируем API клиент после регистрации
        userClient = new UserClient(Constants.EMAIL, Constants.PASSWORD);

        assertTrue(loginPage.isLoginHeaderDisplayed(),
                "Форма входа не отображается после регистрации");
    }

    @DisplayName("Ошибка при регистрации - пароль короче требуемого")
    @Test
    public void registerFailTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        var loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();

        var registerPage = new RegisterPage(driver);
        registerPage.enterName(Constants.NAME);
        registerPage.enterEmail(Constants.EMAIL);
        registerPage.enterPassword(Constants.INVALID_PASSWORD);
        registerPage.clickRegisterButton();

        // Инициализируем API клиент после регистрации
        userClient = new UserClient(Constants.EMAIL, Constants.INVALID_PASSWORD);

        assertTrue(loginPage.isErrorPasswordMessageDisplayed(),
                "Отсутсвует ошибка Некорректный пароль");
    }
}