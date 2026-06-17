package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

public class HeaderPage {
    //Кнопка "Личный кабинет"
    protected final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    //Кнопка "Конструктор"
    protected final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //Кнопка "логотип Stellar Burgers"
    protected final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    @Step("Нажатие на кнопку Личный кабинет")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
    }

    //Добавили поле driver и wait
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    //Добавили конструктор класса page object
    public HeaderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Constants.EXPLICIT_TIMEOUT);
    }
}