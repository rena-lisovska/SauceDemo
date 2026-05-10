package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CheckoutInfoTest extends BaseTest{

    @Test
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

    @Test
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

    @Test
    public void checkFormWithEmptyFirstName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutInfoPage.makeOrder("", "Test_last_name", "123456");
        assertEquals(checkoutInfoPage.getErrorMessage(),
                "Error: First Name is required",
                "No error message or error message incorrect");
    }

    @Test
    public void checkFormWithEmptyLastName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutInfoPage.makeOrder("Test_first_name", "", "123456");
        assertEquals(checkoutInfoPage.getErrorMessage(),
                "Error: Last Name is required",
                "No error message or error message incorrect");
    }

    @Test
    public void checkFormWithEmptyPostalCode() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutInfoPage.makeOrder("Test_first_name", "Test_last_name", "");
        assertEquals(checkoutInfoPage.getErrorMessage(),
                "Error: Postal Code is required",
                "No error message or error message incorrect");
    }

    @Test
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
