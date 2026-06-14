package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import praktikum.Constants;

public class MainPage extends HeaderPage {
    //Кнопка "Войти в аккаунт" на главной
    protected final By loginToAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //Кнопка "Оформить заказ"
    protected final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    //Заголовок Соберите бургер
    protected final By constructorHeader = By.xpath(".//h1[text()='Соберите бургер']");
    //Таб Булки
    protected final By bunsTab = By.xpath(".//span[text()='Булки']");
    //Таб Соус
    protected final By souseTab = By.xpath(".//span[text()='Соусы']");
    //Таб Начинки
    protected final By fillingsTab = By.xpath(".//span[text()='Начинки']");

    // Локаторы заголовков в конструкторе
    protected final By bunsSection = By.xpath(".//h2[text()='Булки']");
    protected final By saucesSection = By.xpath(".//h2[text()='Соусы']");
    protected final By fillingsSection = By.xpath(".//h2[text()='Начинки']");

    @Step("Открытие главной страницы")
    public void open() {
        driver.get(Constants.BASE_URL);
    }

    @Step("Нажатие на кнопку Войти в аккаунт на главной")
    public void clickLoginToAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginToAccountButton));
        driver.findElement(loginToAccountButton).click();
    }

    @Step("Проверка, что кнопка 'Оформить заказ' видима и кликабельна")
    public boolean isCreateOrderButtonDisplayedAndClickable() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
            wait.until(ExpectedConditions.elementToBeClickable(createOrderButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка видимости заголовка Соберите бургер")
    public boolean isConstructorHeaderDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(constructorHeader));
        return driver.findElement(constructorHeader).isDisplayed();
    }

    @Step("Проверка видимости таба Булки")
    public boolean isBunsTabDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
        return driver.findElement(bunsTab).isDisplayed();
    }

    @Step("Проверка видимости таба Соусы")
    public boolean isSaucesTabDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(souseTab));
        return driver.findElement(souseTab).isDisplayed();
    }

    @Step("Проверка видимости таба Начинки")
    public boolean isFillingsTabDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
        return driver.findElement(fillingsTab).isDisplayed();
    }

    @Step("Проверка видимости всех табов в конструкторе")
    public boolean areAllTabsDisplayed() {
        return isBunsTabDisplayed() &&
                isSaucesTabDisplayed() &&
                isFillingsTabDisplayed();
    }

    @Step("Проверка, что таб Булки активен")
    public boolean isBunsTabActive() {
        // Находим родительский div для таба "Булки"
        WebElement tab = driver.findElement(bunsTab);
        WebElement parent = tab.findElement(By.xpath("./.."));
        String classAttribute = parent.getAttribute("class");
        return classAttribute.contains("tab_tab_type_current");
    }

    @Step("Проверка видимости секции с ингредиентами Булки")
    public boolean isBunsSectionDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(bunsSection));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Клик по табу Булки с ожиданием активации")
    public void clickBunsTabAndWait() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
        // Небольшая задержка для анимации в Яндекс.Браузере
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement tab = driver.findElement(bunsTab);
        WebElement parent = tab.findElement(By.xpath("./.."));
        wait.until(ExpectedConditions.attributeContains(parent, "class", "tab_tab_type_current"));
    }

    @Step("Проверка, что таб Соус активен")
    public boolean isSaucesTabActive() {
        WebElement tab = driver.findElement(souseTab);
        WebElement parent = tab.findElement(By.xpath("./.."));
        String classAttribute = parent.getAttribute("class");
        return classAttribute.contains("tab_tab_type_current");
    }

    @Step("Проверка видимости секции с ингредиентами Соус")
    public boolean isSaucesSectionDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(saucesSection));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Клик по табу Соус с ожиданием активации")
    public void clickSaucesTabAndWait() {
                wait.until(ExpectedConditions.elementToBeClickable(souseTab)).click();
        // Небольшая задержка для анимации в Яндекс.Браузере
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement tab = driver.findElement(souseTab);
        WebElement parent = tab.findElement(By.xpath("./.."));
        wait.until(ExpectedConditions.attributeContains(parent, "class", "tab_tab_type_current"));
    }

    @Step("Проверка, что таб Начинки активен")
    public boolean isFillingsTabActive() {
        WebElement tab = driver.findElement(fillingsTab);
        WebElement parent = tab.findElement(By.xpath("./.."));
        String classAttribute = parent.getAttribute("class");
        return classAttribute.contains("tab_tab_type_current");
    }

    @Step("Проверка видимости секции с ингредиентами Начинки")
    public boolean isFillingsSectionDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsSection));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Клик по табу Начинки с ожиданием активации")
    public void clickFillingsTabAndWait() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
        // Небольшая задержка для анимации в Яндекс.Браузере
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement tab = driver.findElement(fillingsTab);
        WebElement parent = tab.findElement(By.xpath("./.."));
        wait.until(ExpectedConditions.attributeContains(parent, "class", "tab_tab_type_current"));
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }
}