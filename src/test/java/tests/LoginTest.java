package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(
            testName = "Вход в систему с валидными данными",
            description = "Проверка успешного входа в систему с валидными данными",
            groups = "smoke"
    )
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
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
    public void checkLoginWithNegativeTests(String user, String password, String errorMessage) { // такой тест является параметризированный, это круто, мы в один тест засунили считай 3
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                errorMessage,
                "No error message or error message incorrect");
    }
}
