package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgotPasswordPage extends HeaderPage{
    //Кнопка «Войти»
    protected final By loginButton = By.xpath(".//a[text()='Войти']");

    @Step("Нажатие на кнопку Войти в форме восстановления пароля")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }
}