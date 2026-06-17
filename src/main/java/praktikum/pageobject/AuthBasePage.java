package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AuthBasePage extends HeaderPage {
    // Общие поля для страниц авторизации
    // Поле Email
    protected final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    // Поле Пароль
    protected final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    public AuthBasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }
}