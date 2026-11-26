package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private  final WebDriverWait wait;

    private final By emailField = By.cssSelector("input[name='name']");

    private final By passwordField = By.cssSelector("input[type='password']");

    private final By loginButten = By.cssSelector("form button");

    private final By registerLink = By.cssSelector("a[href='/register']");

    private final By forgotPasswordLink = By.cssSelector("a[href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввести email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Нажать кнопку войти")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButten)).click();
    }

    @Step("Перейти к регистрации")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Перейти к восстановлению пароля")
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }

    @Step("Выполнить вход")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Проверить переход на шлавную страницу")
    public boolean isMainPageOpened() {
        return wait.until(ExpectedConditions.urlToBe("https://stellarburgers.education-services.ru/"));
    }
}
