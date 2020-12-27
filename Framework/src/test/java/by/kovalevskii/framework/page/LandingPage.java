package by.kovalevskii.framework.page;

import by.kovalevskii.framework.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    public static final String LANDING_URL = "https://www.impericon.com/en/";

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div[4]/button[2]")
    private WebElement cookieButton;

    @FindBy(id = "quicksearch")
    private WebElement inputButton;

    @FindBy(id = "search")
    private WebElement inputString;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[1]/header/div[2]/div/div[1]/div")
    private WebElement buttonSearch;

    @FindBy(xpath = "//*[@id=\"viewport\"]/footer/div[1]/div/div/div[1]/div[2]/div/a[1]")
    private WebElement regionChange;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[1]/header/div[1]/div/button[2]")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/form/button[3]")
    private WebElement registerButton;

    @FindBy(id = "email")
    private WebElement loginEmail;

    @FindBy(id = "password")
    private WebElement loginPwd;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/form/button[1]")
    private WebElement enterButton;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div/form/div[1]/div/input")
    private WebElement regEmail;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div/form/div[2]/div/input")
    private WebElement regFName;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div/form/div[3]/div/input")
    private WebElement regLName;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div/form/div[4]/div/select")
    private WebElement regGender;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div/form/div[4]/div/select/option[2]")
    private WebElement maleGender;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div/form/div[5]/div/input")
    private WebElement regDOB;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div/form/div[6]/div/input")
    private WebElement regPwd;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div/form/div[7]/div/input")
    private WebElement repeatPwd;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[3]/div[2]/div[2]/div/div/form/div[9]/button[1]")
    private WebElement regBtn;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[2]/div/div/div[3]/div/div[2]/div[2]/div[2]")
    private WebElement itemInSearch;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[2]/div/div[2]/div[3]/nav/div/div/div/div")
    private WebElement profileText;

    @FindBy(xpath = "//*[@id=\"viewport\"]/div[2]/div/div/div[3]/div/div")
    private WebElement queryErrorMessage;


    public LandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public LandingPage insertTextInInputField(String text){
        logger.info("Inserting text into input field");
        waitWebElement(inputButton).click();
        waitWebElement(inputString).sendKeys(text);
        return this;
    }

    public String getQueryErrorText(){
        new WebDriverWait(driver, WAIT_IMPLICIT_SECONDS).until(
                ExpectedConditions
                        .textToBe(By
                                    .xpath("//*[@id=\"viewport\"]/div[2]/div/div/div[3]/div/div"),
                        "No results were found."));;
        return waitWebElement(queryErrorMessage).getText();
    }

    public LandingPage buttonSearchClick(){
        waitWebElement(buttonSearch).click();
        return this;
    }

    public ItemPage goToFoundItem(){
        logger.info("Going to the item found");
        waitWebElement(itemInSearch).click();
        return new ItemPage(driver);
    }

    public LandingPage changeRegion() {
        logger.info("Changing region");
        waitWebElement(regionChange).click();
        return this;
    }

    public LandingPage acceptCookies(){
        logger.info("Accepting cookies");
        waitWebElement(cookieButton).click();
        return this;
    }

    public LandingPage openLoginTable(){
        loginButton.click();
        return this;
    }

    public LandingPage fillLoginForm(User user){
        loginEmail.sendKeys(user.getEmail());
        loginPwd.sendKeys(user.getPassword());
        return this;
    }

    public LandingPage login(User user){
        logger.info("Trying to log in");
        openLoginTable();
        fillLoginForm(user);
        enterButton.click();
        return this;
    }

    public String getLoginButtonText(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .textToBe(By
                                .xpath("//*[@id=\"viewport\"]/div[1]/header/div[1]/div/button[2]/span[2]"),
                                "My Account"));
        waitWebElement(loginButton).click();
        return waitWebElement(profileText).getText();
    }

    public LandingPage openRegisterTable(){
        openLoginTable();
        waitWebElement(registerButton).click();
        return this;
    }

    public LandingPage fillRegisterForm(User user){
        Select selectGender = new Select(waitWebElement(regGender));
        waitWebElement(regEmail).sendKeys(user.getEmail());
        regFName.sendKeys(user.getFirstName());
        regLName.sendKeys(user.getLastName());
        selectGender.selectByVisibleText("Female");
        selectGender.selectByVisibleText("Male");
        regDOB.sendKeys(user.getDateOfBirth());
        regPwd.sendKeys(user.getPassword());
        repeatPwd.sendKeys(user.getPassword());
        return this;
    }

    public LandingPage register(User user){
        logger.info("Registering");
        openRegisterTable();
        fillRegisterForm(user);
        regBtn.click();
        return this;
    }



    @Override
    public LandingPage openPage() {
        driver.navigate().to(LANDING_URL);
        logger.info("Landing page opened");
        return this;
    }

    private WebElement waitWebElement(WebElement element){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
