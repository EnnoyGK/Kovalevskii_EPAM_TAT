package by.kovalevskii.framework.test;


import by.kovalevskii.framework.page.LandingPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CartTests extends CommonConditions {


    @Test
    public void addItemToCartTest(){
        String expectedItemName = "Dickies - Cornwell - Jacket";
        String actualItemName = new LandingPage(driver)
                .openPage()
                .acceptCookies()
                .insertTextInInputField(expectedItemName)
                .goToFoundItem()
                .chooseSize()
                .addItemToCart()
                .openCart()
                .getNameOfItemInCart();
        assertThat(actualItemName, is(equalTo(expectedItemName)));
    }

    @Test
    public void addItemToWishListTest(){
        String expectedItemName = "Dickies - Cornwell - Jacket";
        String actualItemName = new LandingPage(driver)
                .openPage()
                .acceptCookies()
                .insertTextInInputField(expectedItemName)
                .goToFoundItem()
                .addItemToWishList()
                .openWishList()
                .getNameOfItemInWishList();
        assertThat(actualItemName, is(equalTo(expectedItemName)));
    }

    @Test
    public void CleanCartTest(){
        String itemName = "Dickies - Cornwell - Jacket";
        String expectedText = "Your shopping cart is empty.";
        String actualText = new LandingPage(driver)
                .openPage()
                .acceptCookies()
                .insertTextInInputField(itemName)
                .goToFoundItem()
                .chooseSize()
                .addItemToCart()
                .cleanCart()
                .getTextFromEmptyCart();
        assertThat(actualText, is(equalTo(expectedText)));
    }

    @Test
    public void EnterDiscountCode(){
        String itemName = "Dickies - Cornwell - Jacket";
        WebElement actual = new LandingPage(driver)
                .openPage()
                .acceptCookies()
                .insertTextInInputField(itemName)
                .goToFoundItem()
                .chooseSize()
                .addItemToCart()
                .enterDiscountCode()
                .getDiscountErrorText();
        Assert.assertNotNull(actual);
    }
}
