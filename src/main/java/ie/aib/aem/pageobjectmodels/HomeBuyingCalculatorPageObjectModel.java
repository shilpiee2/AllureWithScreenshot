package ie.aib.aem.pageobjectmodels;

import ie.aib.aem.base.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * POM for AIB home buying calculator.
 */
public class HomeBuyingCalculatorPageObjectModel extends BaseClass {
  /**
 * css value of search box.
 */
  @FindBy(id = "branchName")
  private WebElement searchbox;
  /**
 * css value of contact details.
 */
  @FindBy(css = ".branch-background")
  private WebElement contactDetail;
  /**
   * css value of click on hyperlink.
   */
  @FindBy(css = "#appointmentLinks > a")
  private WebElement clickHyperlink;
  /**
 * page factory initialization.
 */

  public HomeBuyingCalculatorPageObjectModel() {
    PageFactory.initElements(driver, this);
  }

  /**
   * Method for search box.
   * @param branchName Name of the branch.
   * @throws InterruptedException exception handling.
   */

  public void searchBox(String branchName) throws InterruptedException {
    setText(searchbox, branchName);
  }

  /**
   * Method for the entering details inside contact tab.
   * @return 0/1.
   */
  public boolean contactDetailBox() {
    explicitWait(contactDetail, 2);
    searchbox.sendKeys(Keys.DOWN);
    explicitWait(contactDetail, 2);
    searchbox.sendKeys(Keys.RETURN);
    return contactDetail.isDisplayed();
  }
  /**
 * Method to click on the hyperlink.
 * @return 0/1
 */

  public boolean clickHyperlink() {
    explicitWait(clickHyperlink, 2);
    return clickHyperlink.isDisplayed();
  }

  /**
 * Method for returning result in boolean.
 * @return 0/1
 */

  public boolean booleanValue() {
    return true;
  }

  /**
   * Method to navigation click.
   */

  public void clickNavigation() {
    explicitWait(clickHyperlink, 2);
    click(clickHyperlink, "Click on hyperlink");
  }
  /**
 * Method to cerify mortgage URl.
 * @return String in Values.
 */

  public String verifyMortgageAppointmentUrl() {
    for (String winHandle : driver.getWindowHandles()) {
      driver.switchTo().window(winHandle);
    }
    String url = currentUrl();
    return url;
  }
}
