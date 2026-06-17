package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends AuthBasePage {
    // Поле Имя
    protected final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    // Кнопка Зарегистрироваться
    protected final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //Кнопка «Войти»
    protected final By loginButton = By.xpath(".//a[text()='Войти']");

    @Step("Ввод имени")
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Нажатие на кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Нажатие на кнопку Войти в форме регистрации")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
}