package pages;

import helpers.WaitWrapper;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

  private WebDriver driver;
  private WebElement catUrlLink;
  private WebElement forkMeOnGitHubLink;
  private WebElement urlInputField;
  private WebElement cattifyButton;
  private WebElement newUrlField;
  private WebElement copyButton;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    initElements();
  }

  private void initElements() {
    catUrlLink = driver.findElement(By.xpath("//a[@class='nav-brand' and text()='catURL']"));
    forkMeOnGitHubLink = driver.findElement(By.xpath("//img[@class='snipe']"));
    urlInputField = driver.findElement(By.id("notes"));
    cattifyButton = driver.findElement(By.xpath("//input[@type='submit']"));
  }

  private void initElementsAfterCattifyButtonWasPressed() {
    newUrlField = driver.findElement(By.xpath("//input[@class='new-url']"));
    copyButton = driver.findElement(By.xpath("//button[@class='btn copy-btn']"));
  }

  public boolean isPageTitlePresent() {
    return driver.getTitle().equals("React App");
  }

  public BasePage clickCatUrlLink() {
    catUrlLink.click();
    return this;
  }

  public GithubPage clickForkMeOnGitHubLink() {
    forkMeOnGitHubLink.click();
    return new GithubPage(driver);
  }

  public BasePage typeUrlInUrlInputField(String url) {
    urlInputField.sendKeys(url);
    return this;
  }

  public BasePage clickCattifyButton() {
    WaitWrapper.setZeroImplicitlyWait(driver);
    WaitWrapper.waitForPresenceOfElementLocated(driver, By.xpath("//input[@type='submit']"));
    cattifyButton.click();
    WaitWrapper.setDefaultImplicitlyWait(driver);
    initElementsAfterCattifyButtonWasPressed();
    return this;
  }

  public String getTransformedStringUrl() {
    return newUrlField.getAttribute("value");
  }

  //click on copy button to copy cattify url to clipboard
  public BasePage clickCopyButton() {
    WaitWrapper.setZeroImplicitlyWait(driver);
    WaitWrapper.waitForPresenceOfElementLocated(driver, By
        .xpath("//div[@class='display-contents']//h3"));
    WaitWrapper.setDefaultImplicitlyWait(driver);
    copyButton.click();
    return this;
  }

  public String getTextFromClipboard() {
    String copiedText = "";
    try {
      copiedText = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
          .getData(DataFlavor.stringFlavor);
    } catch (IOException | UnsupportedFlavorException exception) {
      exception.printStackTrace();
      exception.getCause();
    }
    return copiedText;
  }
}
