package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountProfilePage extends HeaderPage {
    // Кнопка Профиль
    protected final By profileButton = By.xpath(".//a[@href='/account/profile']");
    // Кнопка История заказов
    protected final By orderHistoryButton = By.xpath(".//a[@href='/account/order-history']");
    // Кнопка Выход
    protected final By logoutButton = By.xpath(".//button[text()='Выход']");

    @Step("Проверка видимости кнопки Профиль")
    public boolean isProfileButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        return driver.findElement(profileButton).isDisplayed();
    }

    @Step("Проверка видимости кнопки История заказов")
    public boolean isOrderHistoryButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderHistoryButton));
        return driver.findElement(orderHistoryButton).isDisplayed();
    }

    @Step("Проверка видимости кнопки Выход")
    public boolean isLogoutButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        return driver.findElement(logoutButton).isDisplayed();
    }

    @Step("Нажатие на кнопку Выход")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        driver.findElement(logoutButton).click();
    }

    public AccountProfilePage(WebDriver driver) {
        super(driver);
    }
}
