package helpers;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitWrapper {

  public static final long DEFAULT_IMPLICIT_WAIT = 10L;

  public static void setDefaultImplicitlyWait(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS);
  }

  public static void setCustomImplicitlyWait(WebDriver driver, long implicitlyWait) {
    driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
  }

  public static void setZeroImplicitlyWait(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
  }

  public static void waitForInvisibilityOfElementLocated(WebDriver driver, By locator) {
    setZeroImplicitlyWait(driver);
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    setDefaultImplicitlyWait(driver);
  }

  public static void setPageLoadTimeout(WebDriver driver, long seconds) {
    driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
  }

  public static void waitForPresenceOfElementLocated(WebDriver driver, By locator) {
    setZeroImplicitlyWait(driver);
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    setDefaultImplicitlyWait(driver);
  }
}
