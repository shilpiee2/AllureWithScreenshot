
package ie.aib.aem.pageobjectmodels;

import ie.aib.aem.base.BaseClass;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * POM for AIB Personal WebChat Form page.
 */
public class AibPersonalWebChatFormPageObjectModel extends BaseClass {

  /**
   * css value of Product Dropdown.
   */
  @FindBy(css = "#Webchat_contact_webchat_product")
  private WebElement productDropdown;

  /**
   * Page factory initialization.
   */
  public AibPersonalWebChatFormPageObjectModel() {

    PageFactory.initElements(driver, this);
  }

  /**
   * Method to dropdown Select.
   * @param dropdownValue is dropdown needs to be selected
   * @throws InterruptedException exception while looking for locator
   */
  public void dropdownSelect(String dropdownValue) throws InterruptedException {
    selectDropdown(productDropdown, dropdownValue);
    Thread.sleep(5000);

  }
  
  public void navigate() {
	  String url = getProperties("URL", "AIBPersonalWebChatForm.properties");
	  driver.get(url);
	  
	  }


}
