package tests;

import org.junit.Before;
import org.junit.Test;
import pageobject.ForgotPasswordPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;
import static org.junit.Assert.assertTrue;
import practikum.client.UserClient;
import practikum.model.User;
import practikum.model.UserCredentials;
import org.junit.After;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class LoginTest extends BaseTest {
    private String email;
    private String password;
    private String accessToken;

    private UserClient userClient = new UserClient();

    @Before
    public void createUser() {
        email = "irina" + System.currentTimeMillis() + "@mail.ru";
        password = "123456";

        User user = new User(email, password,"Ирина");
        accessToken = userClient.create(user)
                .then()
                .extract()
                .path("accessToken");
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Вход через кнопку Войти в аккаунт на главной странице")
    @Description("Проверяем успешный вход зарегестрированного пользователя через кнопку войти в аккаунт на главной странице")
    public void loginByLoginButtonOnMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickLoginButton();

        loginPage.login(email, password);

        assertTrue("Не выполнен вход по кнопке войти в аккаунт", loginPage.isMainPageOpened());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверяем успешный вход зарегестрированного пользователя через ссылку входа в форме регистрации")
    public void loginFromRegistrationPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();

        loginPage.login(email, password);

        assertTrue("Не выполнен вход через форму регистрации", loginPage.isMainPageOpened());
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    @Description("Проверяем успешный вход зарегестрированного пользователя через ссылку входа в форме восстановления пароля")
    public void loginFromForgotPasswordPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.clickLoginLink();

        loginPage.login(email, password);

        assertTrue("Невыполнен вход через форму восстановления пароля", loginPage.isMainPageOpened());
    }
}
