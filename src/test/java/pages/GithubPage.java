package pages;

import org.openqa.selenium.WebDriver;

public class GithubPage {

  private WebDriver driver;

  public GithubPage(WebDriver driver) {
    this.driver = driver;
  }

  public String getPageTitle() {
    return driver.getTitle();
  }
}
