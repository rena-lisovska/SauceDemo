package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {

    @Test
    public void checkLocators() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        driver.findElement(By.tagName("footer"));
        driver.findElement(By.linkText("Sauce Labs Backpack"));
        driver.findElement(By.partialLinkText("Backpack"));
        driver.findElement(By.xpath("//img[@src='/static/media/sauce-backpack-1200x1500.0a0b85a385945026062b.jpg']"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']"));
        driver.findElement(By.xpath("//div[contains(@data-test,'name')]"));
        driver.findElement(By.xpath("//div[contains(text(),'Bike')]"));
        driver.findElement(By.xpath("//*[text()='9.99']//ancestor::div"));
        driver.findElement(By.xpath("//a[contains(@data-test,'item-0')]//descendant::div"));
        driver.findElement(By.xpath("//div[@class='pricebar']/following::button"));
        driver.findElement(By.xpath("//div/a/img/.."));
        driver.findElement(By.xpath("//button[text()='Add to cart']/preceding::*"));
        driver.findElement(By.xpath("//div[div and button]"));
        driver.findElement(By.cssSelector(".pricebar"));
        driver.findElement(By.cssSelector(".btn.btn_primary"));
        driver.findElement(By.cssSelector(".pricebar .inventory_item_price"));
        driver.findElement(By.cssSelector("#item_4_title_link"));
        driver.findElement(By.cssSelector("ul"));
        driver.findElement(By.cssSelector("li.social_linkedin"));
        driver.findElement(By.cssSelector("[data-test='inventory-item-description']"));
        driver.findElement(By.cssSelector("[class~='btn_small']"));
        driver.findElement(By.cssSelector("[data-test|='inventory']"));
        driver.findElement(By.cssSelector("[href^='https']"));
        driver.findElement(By.cssSelector("[href$='.png']"));
        driver.findElement(By.cssSelector("[href*='saucelabs']"));
    }
}
