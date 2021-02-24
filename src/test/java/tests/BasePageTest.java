package tests;

import helpers.TabsManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasePageTest extends TestRunner {

  private final String TEST_URL = "https://medium.com/better-practices/deploying-a-scalable-web-"
      + "application-with-docker-and-kubernetes-a5000a06c4e9";
  private final String GITHUB_PAGE_TITLE = "GitHub - postmanlabs/node-doc-kube: "
      + "\uD83D\uDC31 URL shortener using cat verbs, cat adjectives, and cat emojis";

  @Test
  public void verifyBasePageTitleIsPresent() {
    boolean isPageTitlePresent = loadApplication().isPageTitlePresent();
    Assert.assertTrue(isPageTitlePresent);
  }

  @Test
  public void verifyCatUrlIsClickable() {
    boolean isCatUrlClickable = loadApplication().clickCatUrlLink()
        .isPageTitlePresent();
    Assert.assertTrue(isCatUrlClickable);
  }

  @Test
  public void verifyForkMeOnGitHubLinkIsClickable() {
    String gitHubPageTitle = loadApplication().clickForkMeOnGitHubLink()
        .getPageTitle();
    Assert.assertEquals(gitHubPageTitle, GITHUB_PAGE_TITLE);
  }

  @Test
  public void verifyTransformedUrlIsPresentInNewUrlField() {
    String newUrlFieldText = loadApplication().typeUrlInUrlInputField(TEST_URL)
        .clickCattifyButton()
        .getTransformedStringUrl();
    Assert.assertTrue(newUrlFieldText.length() > 0);
  }

  @Test
  public void verifyPossibilityOfCopyingUrlUsingCopyButton() {
    String url = loadApplication().typeUrlInUrlInputField(TEST_URL)
        .clickCattifyButton()
        .clickCopyButton()
        .getTextFromClipboard();
    Assert.assertTrue(url.length() > 0);
  }

  @Test
  public void verifyTransformedUrlIsWorking() {
    String url = loadApplication().typeUrlInUrlInputField(TEST_URL)
        .clickCattifyButton()
        .clickCopyButton()
        .getTextFromClipboard();
    String pageTitle = TabsManager.getPageTitleOpenedInANewTab(getDriver(), url);
    Assert.assertEquals(TEST_URL, pageTitle);
  }

}
