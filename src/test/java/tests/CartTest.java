package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {

    String expectedProductName = "Sauce Labs Backpack";
    String expectedPriceOfProduct = "29.99";

    @Test(
            testName = "Добавление товара в корзину",
            description = "Проверка добавления конкретного товара в корзину",
            groups = "smoke"
    )
    public void checkCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        softAssert.assertEquals(cartPage.getCartItemName("Sauce Labs Backpack"),
                expectedProductName,
                "Product name does not match");
        softAssert.assertEquals(cartPage.getCartItemPrice("29.99"),
                expectedPriceOfProduct,
                "Product price does not match");
        softAssert.assertAll();
    }

    @Test(
            testName = "Удаление товара из корзины",
            description = "Проверка корректного удаление товара из корзины",
            groups = "regression"
    )
    public void checkRemoveItem() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        softAssert.assertTrue(cartPage.isProductDisplayed("Sauce Labs Backpack"),
                "The item should be in the cart, but it was not found.");
        cartPage.removeToCart("Sauce Labs Backpack");
        softAssert.assertFalse(cartPage.isProductDisplayed("Sauce Labs Backpack"),
                "The item is still showing in the cart after deleting it.");
        softAssert.assertAll();
    }
}
