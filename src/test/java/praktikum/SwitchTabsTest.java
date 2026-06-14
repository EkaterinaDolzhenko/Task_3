package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import praktikum.pageobject.MainPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwitchTabsTest {
    @RegisterExtension
    public final DriverExtension extension = new DriverExtension();

    @DisplayName("Переключение между всеми табами конструктора")
    @Test
    public void switchBetweenAllTabsTest() throws Exception {
        WebDriver driver = extension.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.open();

        // Проверка таба "Булки" (активен по умолчанию)
        assertTrue(mainPage.isBunsTabActive(), "Таб 'Булки' должен быть активен по умолчанию");
        assertTrue(mainPage.isBunsSectionDisplayed(), "Секция булок должна отображаться");

        // Переключение на таб "Соусы"
        mainPage.clickSaucesTabAndWait();
        assertTrue(mainPage.isSaucesTabActive(), "Таб 'Соусы' должен стать активным");
        assertTrue(mainPage.isSaucesSectionDisplayed(), "Секция соусов должна отображаться");
        assertFalse(mainPage.isBunsTabActive(), "Таб 'Булки' не должен быть активным");

        // Переключение на таб "Начинки"
        mainPage.clickFillingsTabAndWait();
        assertTrue(mainPage.isFillingsTabActive(), "Таб 'Начинки' должен стать активным");
        assertTrue(mainPage.isFillingsSectionDisplayed(), "Секция начинок должна отображаться");
        assertFalse(mainPage.isSaucesTabActive(), "Таб 'Соусы' не должен быть активным");

        // Переключение обратно на таб "Булки"
        mainPage.clickBunsTabAndWait();
        assertTrue(mainPage.isBunsTabActive(), "Таб 'Булки' должен снова стать активным");
        assertTrue(mainPage.isBunsSectionDisplayed(), "Секция булок должна снова отображаться");
    }
}
