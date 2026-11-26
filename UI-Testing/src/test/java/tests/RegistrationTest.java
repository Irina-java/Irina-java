package tests;

import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;
import practikum.client.UserClient;
import practikum.model.User;
import practikum.model.UserCredentials;

import static org.junit.Assert.assertTrue;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class RegistrationTest extends BaseTest {
    private UserClient userClient = new UserClient();

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации нового пользователя с валидными данными")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        //Email должен быть уникальным при каждом запуске теста
        String email = "irina" + System.currentTimeMillis() + "@mail.ru";
        String password = "123456";
        String name = "Ирина";

        User user = new User(email, password, name);

        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        registerPage.register(name, email, password);

        assertTrue("После успешной регистрации не открылась страница входа", registerPage.isLoginPageOpened());

        String accessToken = userClient.login(new UserCredentials(email, password))
                .then()
                .extract()
                .path("accessToken");
    }

    @Test
    @DisplayName("Регистрация с коротким паролем")
    @Description("Проверка отображения сообщения Некорректный пароль при регистрации с паролем менее 6 символов")
    public  void registrationWithShortPasswordShowsErrorTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        String email = "irina" + System.currentTimeMillis() + "@mail.ru";
        String password = "12345";
        String name = "Ирина";

        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        registerPage.register(name, email, password);

        assertTrue("Сообщение Некорректный пароль не появилось", registerPage.isIncorrectPasswordMessageDisplayed());
    }
}
