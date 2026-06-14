package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AuthBasePage {
    // Заголовок формы входа
    protected final By loginHeader = By.xpath(".//h2[text()='Вход']");
    //Ошибка для некорректного пароля
    protected final By errorPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");
    // Кнопка Зарегистрироваться
    protected final By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    //Кнопка "Восстановить пароль"
    protected final By forgotPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");
    //Кнопка «Войти»
    protected final By loginButton = By.xpath(".//button[text()='Войти']");

    @Step("Нажатие на кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Проверка видимости заголовка формы")
    public boolean isLoginHeaderDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
        return driver.findElement(loginHeader).isDisplayed();
    }

    @Step("Проверка ошибки для некорректного пароля")
    public boolean isErrorPasswordMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorPasswordMessage));
        return driver.findElement(errorPasswordMessage).isDisplayed();
    }

    @Step("Нажатие на кнопку Восстановить пароль")
    public void clickForgotPasswordButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordButton));
        driver.findElement(forgotPasswordButton).click();
    }

    @Step("Нажатие на кнопку Войти")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}