package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage {
    private WebDriver driver;
    private final WebDriverWait wait;

    //Поле "Email"
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");

    //Кнопка "Восстановить"
    private final By restoreButton = By.xpath("//button[text()='Восстановить']");

    //Ссылка "Войти"
    private final By loginLink = By.xpath("//a[@href='/login']");


    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввести email для восстановления пароля")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Нажать кнопку Восстановить")
    public void clickRestoreButton() {
        wait.until(ExpectedConditions.elementToBeClickable(restoreButton)).click();
    }

    @Step("Перейти на страницу входа")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Восстановить пароль для пользователя с email")
    public void restorePassword(String email) {
        setEmail(email);
        clickRestoreButton();
    }
}