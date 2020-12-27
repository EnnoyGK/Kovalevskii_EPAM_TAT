package by.kovalevskii.framework.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private String productUrl;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[1]/header/div[1]/div/button[4]")
    private WebElement shoppingCart;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[1]/header/div[1]/div/button[3]")
    private WebElement wishList;

    @FindBy(xpath = "//*[@id=\"product\"]/div[1]/div/div[3]/div[2]/div[3]/div[2]/button[1]")
    private WebElement addItemToCartBtn;

    @FindBy(xpath = "//*[@id=\"product\"]/div[1]/div/div[3]/div[2]/div[3]/div[2]/button[2]")
    private WebElement addItemToWishListBtn;

    @FindBy(xpath = "//*[@id=\"product\"]/div[1]/div/div[3]/div[2]/div[3]/div[2]/div/button")
    private WebElement sizeSelector;

    @FindBy(xpath = "//*[@id=\"product\"]/div[5]/div/div/div[3]/div/div[1]/div[2]")
    private WebElement mediumSize;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[2]/div/div/div[3]/div/ul/li/div[2]/div[1]/a")
    private WebElement nameInCart;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[2]/div/div/div[3]/div/div/ul/li/div[2]/div[1]/a")
    private WebElement nameInWishList;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[2]/div/div/div[3]/div/div[1]/div/div/input")
    private WebElement discountInput;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[2]/div/div/div[3]/div/div[1]/button")
    private WebElement discountBtn;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[2]/div/div/div[3]/div/h4")
    private WebElement textInEmptyCart;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[2]/div/div/div[1]/button[1]/i")
    private WebElement deleteBtn;

    @FindBy(id = "notificationAction2")
    private WebElement deleteAcception;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/span")
    private WebElement discountErrorMessage;



    public ItemPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ItemPage(WebDriver driver, String productUrl){
        super(driver);
        this.productUrl = productUrl;
        PageFactory.initElements(this.driver, this);
    }

    public ItemPage openWishList(){
        logger.info("Opening wish list");
        wishList.click();
        return this;
    }

    public ItemPage openCart(){
        logger.info("Opening cart");
        shoppingCart.click();
        return this;
    }

    public ItemPage chooseSize(){
        waitWebElement(sizeSelector).click();
        waitWebElement(mediumSize).click();
        return this;
    }

    public ItemPage addItemToCart(){
        logger.info("Adding an item to cart");
        chooseSize();
        waitWebElement(addItemToCartBtn).click();
        return this;
    }

    public ItemPage cleanCart(){
        logger.info("Cleaning the cart");
        openCart();
        waitWebElement(deleteBtn).click();
        waitWebElement(deleteAcception).click();
        return this;
    }

    public String getTextFromEmptyCart(){
        return waitWebElement(textInEmptyCart).getText();
    }

    public ItemPage addItemToWishList(){
        logger.info("Adding an item to wish list");
        waitWebElement(addItemToWishListBtn).click();
        return this;
    }

    public ItemPage enterDiscountCode(){
        logger.info("Entering discount code");
    openCart();
    waitWebElement(discountInput).sendKeys("ehguif");
    waitWebElement(discountBtn).click();
    return this;
    }

    public WebElement getDiscountErrorText(){
        return waitWebElement(discountErrorMessage);
    }

    public String getNameOfItemInCart(){
        return waitWebElement(nameInCart).getText();
    }

    public String getNameOfItemInWishList(){
        return waitWebElement(nameInWishList).getText();
    }

    @Override
    public ItemPage openPage() {
        driver.navigate().to(productUrl);
        logger.info("Item page opened");
        return this;
    }

    private WebElement waitWebElement(WebElement element){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
