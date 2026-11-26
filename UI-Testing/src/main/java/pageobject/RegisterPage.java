package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriverWait wait;

    //Поле "Имя"
    private final By nameField = By.name("name");

    //Поле "Email"
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");

    //Поле "Пароль"
    private final By passwordField = By.xpath("//input[@type='password']");

    //Кнопка "Загерестрироваться"
    private final By registerButton = By.cssSelector("form button");

    //Ссылка "Войти"
    private final By loginLink = By.xpath("//a[text()='Войти']");

    //Сообщение об ошибке пароля
    private final By incorrectPasswordMessage = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввести имя")
    public void setName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    @Step("Ввести email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Нажать кнопку зарегестироваться")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Зарегестрировать пользователя")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Перейти на страницу входа")
    public boolean isIncorrectPasswordMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordMessage)).isDisplayed();
    }

    @Step("Проверить сообщение о некорректном пароле")
    public boolean isLoginPageOpened() {
        return wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Step("Проверить открытие страницы входа")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
}
