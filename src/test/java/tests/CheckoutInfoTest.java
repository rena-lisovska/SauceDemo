package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CheckoutInfoTest extends BaseTest {

    @Test(
            testName = "Переход из корзины к оформлению заказа: шаг 1",
            description = "Проверка перехода из корзины к оформлению заказа: шаг 1",
            groups = "smoke"
    )
    @Owner("Lisovskaya Ira")
    @Epic("Sauce Demo 1")
    @Feature("Checkout")
    @Story("Checkout user information")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSuccessfulSwitchToCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        cartPage.clickCheckout();
        assertEquals(checkoutInfoPage.getTitle(),
                "Checkout: Your Information",
                "The heading 'Checkout: Your Information' is missing. An error probably occurred when going to the checkout page.");
    }

    @Test(
            testName = "Форма оформления заказа с валидными данными",
            description = "Проверка успешного заполнения формы заказа и переход к шагу 2",
            groups = "smoke"
    )
    @Owner("Lisovskaya Ira")
    @Epic("Sauce Demo 1")
    @Feature("Checkout")
    @Story("Checkout with positive credential")
    @Severity(SeverityLevel.CRITICAL)
    public void checkFormWithPositiveData() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutInfoPage.makeOrder("Test_name", "Test_last_name", "123456");
        assertEquals(checkoutOverviewPage.getTitle(),
                "Checkout: Overview",
                "The heading 'Checkout: Overview' is missing. An error probably occurred when going to the checkout page.");
    }

    @DataProvider(
            name = "Параметризированный тест для невалидного заполнения формы заказа"
    )
    public Object[][] checkoutData() {
        return new Object[][]{
                {"", "Test_last_name", "123456", "Error: First Name is required"},
                {"Test_first_name", "", "123456", "Error: Last Name is required"},
                {"Test_first_name", "Test_last_name", "", "Error: Postal Code is required"}
        };
    }

    @Test (
            dataProvider = "Параметризированный тест для невалидного заполнения формы заказа",
            testName = "Форма оформления заказа с невалидными данными",
            description = "Проверка негативных кейсов в оформлении заказа",
            groups = "regression"
    )
    @Owner("Lisovskaya Ira")
    @Epic("Sauce Demo 1")
    @Feature("Checkout")
    @Story("Checkout with negative credential")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithNegativeTests(String firstName, String lastName, String postalCode, String errorMessage) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutInfoPage.makeOrder(firstName, lastName, postalCode);
        assertEquals(loginPage.getErrorMessage(),
                errorMessage,
                "No error message or error message incorrect");
    }

    @Test(
            testName = "Возвращение в корзину из оформления заказа",
            description = "Проверка возвращения в корзину из оформления заказа",
            groups = "regression"
    )
    @Owner("Lisovskaya Ira")
    @Epic("Sauce Demo 1")
    @Feature("Checkout")
    @Story("Return to shop cart by button Cancel")
    @Severity(SeverityLevel.MINOR)
    public void checkReturnShopCartByButtonCancel() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutInfoPage.clickCancel();
        assertEquals(cartPage.getTitle(),
                "Your Cart",
                "The heading 'Your Cart' is missing. An error probably occurred when going to the checkout page.");
    }
}
