import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.awt.windows.WBufferStrategy;

import java.util.concurrent.TimeUnit;

public class AddItemToWishlistTest {

    private WebDriver driver;
    private static final String URL = "https://www.impericon.com/en/parkway-drive-underdogs-bottle-glow-in-the-dark-t-shirt.html";
    WebElement addToWishListButton;

    @Before
    public void setUpDriverAndConditions(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.get(URL);
        WebElement cookieSettingsButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id=\"viewport\"]/div[4]/div[2]/div[2]/div/div[4]/button[2]")));
        cookieSettingsButton.click();
        addToWishListButton = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[@id=\"product\"]/div[1]/div/div[3]/div[2]/div[3]/div[2]/button[2]")));
        addToWishListButton.click();
    }

    @After
    public void tearDownDriver(){
        driver.quit();
    }

    @Test
    public void WishListButtonChangedTest(){
        String expectedAlt = "REMOVE";
        WebElement clickedButtonAlt = driver.findElement(By
                .xpath("//*[@id=\"product\"]/div[1]/div/div[3]/div[2]/div[3]/div[2]/button[2]/span"));
        String actualAlt = clickedButtonAlt.getText();
        Assert.assertEquals(expectedAlt, actualAlt);
    }

    @Test
    public void ItemNameInWishListTest(){
        String expectedItemName = "Parkway Drive - Underdogs Bottle Glow In The Dark - T-Shirt";
        String expectedItemPrice = "19.99 €";

        WebElement goToWishListButton = driver.findElement(By
                .xpath("//*[@id=\"viewport\"]/div[1]/header/div[1]/div/button[3]"));
        goToWishListButton.click();

        WebElement itemToTest = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id=\"viewport\"]/div[3]/div/div/div[3]/div/div/ul/li/div[2]/div[1]/a")));
        String actualItemName = itemToTest.getText();
        Assert.assertEquals(expectedItemName, actualItemName);
    }
}
