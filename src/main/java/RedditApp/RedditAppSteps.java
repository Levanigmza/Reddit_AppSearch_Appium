package RedditApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class RedditAppSteps {
    private AppiumDriver<MobileElement> driver;
    private RedditApp_POM redditAppPOM;

    public RedditAppSteps(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        this.redditAppPOM = new RedditApp_POM(driver);

    }
    public void waitForElementToBeClickable(MobileElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(MobileElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickSearch() {
        waitForElementToBeClickable(redditAppPOM.getSearch(), 10);
        redditAppPOM.getSearch().click();
    }

    public void enterSearchText(String searchText) {
        waitForElementToBeVisible(redditAppPOM.getSearchContainer(), 10);
        MobileElement searchContainer = redditAppPOM.getSearchContainer();
        searchContainer.sendKeys(searchText);
        driver.hideKeyboard();
    }

    public void submitSearch() {
        waitForElementToBeClickable(redditAppPOM.getSearchIcon(), 10);
        MobileElement searchIcon = redditAppPOM.getSearchIcon();
        MobileElement searcherRunner = redditAppPOM.runSearch();
        searchIcon.click();
        sleep(2000);
        searcherRunner.click();
    }

    public void clickFilterButton() {
        waitForElementToBeClickable(redditAppPOM.getFilterButton(), 10);
        redditAppPOM.getFilterButton().click();
    }

    public void clickSortByHot() {
        waitForElementToBeClickable(redditAppPOM.getSortByHotButton(), 10);
        redditAppPOM.getSortByHotButton().click();
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void pickPostWithMostUpvotes() {
        sleep(4000);
        int maxUpvotes = Integer.MIN_VALUE;
        MobileElement selectedPost = null;


        List<MobileElement> posts = driver.findElements(MobileBy.xpath("//android.view.View[@resource-id='feed_lazy_column']//android.view.View"));

        for (int i = 1; i <= 20 && i <= posts.size(); i++) {
            MobileElement currentPost = posts.get(i - 1);
            String contentDesc = currentPost.getAttribute("content-desc");

            if (contentDesc != null) {
                String[] parts = contentDesc.split(", ");

                if (parts.length >= 2) {
                    String upvotesPart = parts[parts.length - 1];

                    if (upvotesPart.contains("upvotes")) {
                        int currentUpvotes = Integer.parseInt(upvotesPart.split(" ")[0]);

                        if (currentUpvotes > maxUpvotes) {
                            maxUpvotes = currentUpvotes;
                            selectedPost = currentPost;
                        }
                    }
                }
            }
        }

        if (selectedPost != null) {
            String postDetails = extractPostDetails(selectedPost);
            System.out.println("Selected Post Details: " + postDetails);
        } else {
            System.out.println("No post found");
        }
    }

    private String extractPostDetails(MobileElement post) {

        return post.getAttribute("content-desc");
    }
}
