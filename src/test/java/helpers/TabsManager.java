package helpers;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TabsManager {

  public static String getPageTitleOpenedInANewTab(WebDriver driver, String url) {
    String secondBrowserTab = getBrowserTabs(driver).get(1);
    switchToWindow(driver, secondBrowserTab);
    WaitWrapper.setPageLoadTimeout(driver, 10);
    driver.get(url);
    return driver.getTitle();
  }

  public static void switchToWindow(WebDriver driver, String window) {
    driver.switchTo().window(window);
  }

  public static List<String> getBrowserTabs(WebDriver driver) {
    ((JavascriptExecutor) driver).executeScript("window.open()");
    return new ArrayList<>(driver.getWindowHandles());
  }
}
