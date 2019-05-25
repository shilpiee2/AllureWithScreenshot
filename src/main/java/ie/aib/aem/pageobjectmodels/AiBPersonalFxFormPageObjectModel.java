package ie.aib.aem.pageobjectmodels;

import ie.aib.aem.base.BaseClass;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Page object model for AIB FX form Page.
 */

public class AiBPersonalFxFormPageObjectModel extends BaseClass {
		
  /**
 * css value of branchfield.
 */
  @FindBy(id = "branchName")
  private WebElement branchField;
  /**
 * css value of branchDropdown.
 */
  @FindBy(id = "branchNSC")
  private WebElement branchDropdown;
  
  /**
 * page object model initialization.
 */
  
  public AiBPersonalFxFormPageObjectModel() {
	PageFactory.initElements(driver, this);
	  
  }

	/**
 * method to enter the branch into branch field tab.
 * @param branchName name of the branch
 * @throws InterruptedException 
 */

  public void enterBranchDetails(String branch) throws InterruptedException {
    setText(branchField, branch);
  }
  
  /**
 * Method to verify the invisibility of branch.
 * @return 0/1
 */

  public boolean invisibilityBranch() {
	  explicitWait(branchDropdown, 20);
    branchField.sendKeys(Keys.RETURN);
    boolean result = invisibilityOfElement(branchDropdown);
    //assertEquals(true, result);
    return result;
  }

public void navigate() {
	  String url = getProperties("URL", "FXForm.properties");
	  setBrowser(url);
	  assertEquals(url, "https://aib2.aibtest.ie/personal-forms/fx-form");
	  }
}
