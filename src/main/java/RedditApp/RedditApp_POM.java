package RedditApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class RedditApp_POM  {

    private final AppiumDriver<MobileElement> driver;

    public RedditApp_POM(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public MobileElement getSearch() {
        return findElement(MobileBy.AccessibilityId("Search"));
    }

    public MobileElement getSearchContainer() {
        return findElement(By.id("com.reddit.frontpage:id/search"));
    }
    public MobileElement getSearchIcon() {
        return findElement(By.id("com.reddit.frontpage:id/toolbar_search_icon"));
    }
    public MobileElement runSearch() {
        return findElement(By.xpath("//android.widget.TextView[@resource-id='query_prompt_text']"));
    }


    public MobileElement getFilterButton() {
        return findElement(MobileBy.AccessibilityId("Sort by Most relevant"));
    }

    public MobileElement getSortByHotButton() {
        return findElement(MobileBy.AccessibilityId("Sort by Hot"));
    }

    public MobileElement getViewList() {
        return findElement(MobileBy.AccessibilityId("feed_lazy_column"));
    }

    private MobileElement findElement(By by) {
        return driver.findElement(by);
    }
}
