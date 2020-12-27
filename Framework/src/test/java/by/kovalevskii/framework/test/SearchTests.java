package by.kovalevskii.framework.test;

import by.kovalevskii.framework.page.LandingPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchTests extends CommonConditions {

    @Test
    public void IncorrectQueryTest(){
        String incorrectItemName = "amgwioregm";
        String expectedErrorText = "No results were found.";
        String actualErrorText = new LandingPage(driver)
                .openPage()
                .acceptCookies()
                .insertTextInInputField(incorrectItemName)
                .getQueryErrorText();
        assertThat(actualErrorText, is(equalTo(expectedErrorText)));
    }
}
