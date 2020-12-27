package by.kovalevskii.framework.test;

import by.kovalevskii.framework.model.User;
import by.kovalevskii.framework.page.LandingPage;
import by.kovalevskii.framework.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AccessTests extends CommonConditions {

    @Test
    public void signIn() {
        User testUser = UserCreator.withCredentialsFromProperty();
        String loginButtonText = new LandingPage(driver)
                .openPage()
                .acceptCookies()
                .login(testUser)
                .getLoginButtonText();
        Assert.assertEquals(loginButtonText, "Hi Uilliam");

    }

    /*@Test
    public void register() {
        User testUser = UserCreator.withRandomCredentials();
        String loginButtonText = new LandingPage(driver)
                .openPage()
                .acceptCookies()
                .register(testUser)
                .getLoginButtonText();
        Assert.assertEquals(loginButtonText, "Hi " + testUser.getFirstName());
    }*/

    @Test
    public void changeRegion(){
        LandingPage page = new LandingPage(driver)
                .openPage()
                .acceptCookies()
                .changeRegion();
        String newURL = driver.getCurrentUrl();
        Assert.assertEquals(newURL, "https://www.impericon.com/de/");
    }
}
