package tests;
import org.junit.Test;
import pageobject.MainPage;
import static org.junit.Assert.assertTrue;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class ConstructorTest extends BaseTest{

    //"Булки" изначалбно уже активна, поэтому сначало переходим в другой раздел
    @Test
    @DisplayName("Переход к разделу булки")
    @Description("Проверка активации вкладки Булки в конструкторе бургера")
    public void clickConstructorButtonOpensConstructorSectionTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        assertTrue("Владка Булки не стала активной", mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    @Description("Проверка активации вкладки Соусы в конструкторе бургера")
    public void clickSaucesTabActivatesSaucesSectionTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.clickSaucesTab();

        assertTrue("Владка Соусы не стала активной", mainPage.isSaucesTabActive());

    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("Проверка активации вкладки Начинки в конструкторе бургера")
    public void clickFillingsTabActivatesFillingsSectionTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.clickFillingsTab();

        assertTrue("Владка Начинки не стала активной", mainPage.isFillingsTabActive());
    }
}

