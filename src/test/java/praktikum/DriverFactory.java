package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private WebDriver driver;

    public void setupDriver() {
        if ("yandex".equals(System.getProperty("browser"))) {
            setupYandex();
        } else {
            setupChrome();
        }
    }

    public void setupChrome() {
        WebDriverManager.chromedriver().setup(); // Автоматически скачивает и настраивает ChromeDriver
        driver = new ChromeDriver(); // Создание драйвера перед каждым тестом
    }

    public void setupYandex() {
        // 1. Настраиваем WebDriverManager на скачивание ChromiumDriver
        WebDriverManager.chromedriver().browserVersion("146").setup();

        ChromeOptions options = new ChromeOptions();

        // 3. Указываем путь к исполняемому файлу Яндекс.Браузера
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        // 4. Добавляем опцию для отключения предупреждения об автоматизации
        options.addArguments("--remote-allow-origins=*");

        // 5. Инициализируем драйвер с этими опциями
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
