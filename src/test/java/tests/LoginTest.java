package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(
            testName = "Вход в систему с валидными данными",
            description = "Проверка успешного входа в систему с валидными данными",
            groups = "smoke"
    )
    @Owner("Lisovskaya Ira")
    @Epic("Sauce Demo 1")
    @Feature("Login")
    @Story("Login with positive credential")
    @Description("Проверка успешного входа в систему с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Flaky
    @Link(name="Webside", url="https://www.saucedemo.com")
    @TmsLink("SD-T01")
    @Issue("BUG-01")
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(productsPage.getTitle(),
                "Products",
                "The 'Products' heading is missing. Login likely failed.");
    }

    @DataProvider(
            name = "Параметризированный тест для невалидного входа в систему"
    )
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test_user", "test_password", "Epic sadface: Username and password do not match any user in this service"}
        };
    }


    @Test(
            dataProvider = "Параметризированный тест для невалидного входа в систему",
            testName = "Вход в систему с невалидными данными",
            description = "Проверка входа в систему с невалидными данными",
            groups = "regression"
    )
    @Owner("Lisovskaya Ira")
    @Epic("Sauce Demo 1")
    @Feature("Login")
    @Story("Login with negative credential")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithNegativeTests(String user, String password, String errorMessage) { // такой тест является параметризированный, это круто, мы в один тест засунили считай 3
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                errorMessage,
                "No error message or error message incorrect");
    }
}
