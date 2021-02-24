package tests;

import helpers.WaitWrapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;

public class TestRunner {

  private String serverUrl = "http://localhost:3000/";
  private Map<Long, WebDriver> drivers;

  protected WebDriver getDriver() {
    WebDriver currentWebDriver = drivers.get(Thread.currentThread().getId());
    if (currentWebDriver == null) {
      currentWebDriver = new ChromeDriver();
      drivers.put(Thread.currentThread().getId(), currentWebDriver);
    }
    return currentWebDriver;
  }

  @BeforeClass
  public void beforeClass(ITestContext context) {
    drivers = new HashMap<>();
    WebDriverManager.chromedriver().setup();
    WaitWrapper.setDefaultImplicitlyWait(getDriver());
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
    for (Map.Entry<Long, WebDriver> currentWebDriver : drivers.entrySet()) {
      if (currentWebDriver.getValue() != null) {
        currentWebDriver.getValue().close();
        currentWebDriver.getValue().quit();
      }
    }
  }

  @BeforeMethod
  public void beforeMethod() {
    getDriver().get(serverUrl);
  }

  @AfterMethod
  public void afterMethod(ITestResult result) {
    if (!result.isSuccess()) {
      getDriver().manage().deleteAllCookies();
    }
  }

  protected BasePage loadApplication() {
    return new BasePage(getDriver());
  }
}
