package RedditApp_Tests;

import RedditApp.RedditAppSteps;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Setup;

import java.net.MalformedURLException;

public class RedditAppTest {
    private AppiumDriver<MobileElement> driver;
    private RedditAppSteps redditAppSteps;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        Setup.setupCapabilities();
        driver = Setup.getDriver();
        redditAppSteps = new RedditAppSteps(driver);
    }

    @Test(description = " test", priority = 1)
    public void testRedditApp() {
        redditAppSteps.clickSearch();
        redditAppSteps.enterSearchText("banking");
        redditAppSteps.submitSearch();
        redditAppSteps.clickFilterButton();
        redditAppSteps.clickSortByHot();
        redditAppSteps.pickPostWithMostUpvotes();
    }

    @AfterClass
    public void tearDown() {
        Setup.tearDown();
    }
}
