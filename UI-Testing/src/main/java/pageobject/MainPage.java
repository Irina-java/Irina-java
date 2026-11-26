package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.qameta.allure.Step;

public class MainPage {

    private static final String URL = "https://stellarburgers.education-services.ru/";

    private final WebDriver driver;
    private  final WebDriverWait wait;

    //В HTML это кнопка button с текстом "Войти в аккаунт"
    //Текст находится во вложенном теге font, поэтому используем contains(...)
    private final By loginButton = By.xpath("//button[contains(., 'Войти в аккаунт')]");

    //В HTML это ссылка с адресом /account
    //Визуальный текст на стенде искажен, поэтому ищем по стабилбному href
    private final By personaAccountLink = By.cssSelector("a[href='/account']");

    //Вкладка "Булки"
    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");

    //Вкладка "Соусы"
    private final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");

    //Вкладка "Начинки"
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

@Step("Открыть главную страницу")
    public void open() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Нажать кнопку войти в аккаунт")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажать ссылку Личный кабинет")
    public void clickPersonalAccountLink() {
        wait.until(ExpectedConditions.elementToBeClickable(personaAccountLink)).click();
    }

    @Step("Открыть раздел Булки")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
        wait.until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current"));
    }

    @Step("Открыть раздел Соусы")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
        wait.until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current"));
    }

    @Step("Открыть раздел Начинки")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
        wait.until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current"));
    }
    @Step("Проверяем что раздел Булки активен")
    public boolean isBunsTabActive() {
        return wait.until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current"));
    }

    @Step("Проверяем что раздел Соусы активен")
    public boolean isSaucesTabActive() {
        return wait.until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current"));
    }

    @Step("Проверяем что раздел Начинки активен")
    public boolean isFillingsTabActive() {
        return wait.until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current"));
    }
}
