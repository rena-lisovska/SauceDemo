package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class CheckoutOverviewTest extends BaseTest {

    String expectedProductName = "Sauce Labs Backpack";
    String expectedPriceOfProduct = "29.99";

    @Test
    public void checkOverview() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutInfoPage.makeOrder("Test_name", "Test_last_name", "123456");
        softAssert.assertEquals(checkoutOverviewPage.getOverviewItemName("Sauce Labs Backpack"),
                expectedProductName,
                "Product name does not match");
        softAssert.assertEquals(checkoutOverviewPage.getOverviewItemPrice("29.99"),
                expectedPriceOfProduct,
                "Product price does not match");
        softAssert.assertAll();
    }

    @Test
    public void checkComplete() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutInfoPage.makeOrder("Test_name", "Test_last_name", "123456");
        checkoutOverviewPage.clickFinish();
        assertEquals(checkoutCompletePage.getTitle(),
                "Checkout: Complete!",
                "The 'Checkout: Complete!' heading is missing. Login likely failed.");
    }
}
