import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {

    /*
    Прекондишн: открыть браузер

    Шаги:
    1. Открыть страницу https://www.saucedemo.com/
    2. Ввести логин standard_user и пароль secret_sauce
    3. Не переходя в карточку товара добавить товар Sauce Labs Backpack в корзину по средствам кнопки "Add to cart"
    4. Перейти в корзину, кликнув на значок корзины в верхнем правом углу страницы
    5. Проверить стоимость товара и его имя в корзине

    Посткондишн: закрыть браузер

    Ожидаемый результат: имя и стоимость товара идентичны как в сниппите карточки товара, так и в корзине
    */

    String expectedProductName = "Sauce Labs Backpack";
    String expectedPriceOfProduct = "29.99";

    @Test
    public void checkCart() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
        String actualProductName = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']")).getText();
        String actualPriceOfProduct = driver.findElement(By.xpath("//div[@class='cart_item' and .//div[text()='Sauce Labs Backpack']]//div[@class='inventory_item_price']")).getText().replace("$", "");
        softAssert.assertEquals(actualProductName, expectedProductName, "Название товара не совпадает.");
        softAssert.assertEquals(actualPriceOfProduct, expectedPriceOfProduct, "Стоимость товара не совпадает.");
        softAssert.assertAll();
    }
}
