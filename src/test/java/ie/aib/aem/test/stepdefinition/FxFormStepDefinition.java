package ie.aib.aem.test.stepdefinition;

import static org.testng.Assert.assertEquals;
import ie.aib.aem.base.BaseClass;
import ie.aib.aem.pageobjectmodels.AiBPersonalFxFormPageObjectModel;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * Step definition class for AIB fx-form page.
 */

public class FxFormStepDefinition extends BaseClass {

	public AiBPersonalFxFormPageObjectModel aibPersonalFxFormPageObjectModel;

	/**
 * Given statement.
 * @param branch name of the branch.
	 * @throws Exception 
 */
	
	@Step
  @Given("^that customer wants to enquire contact details about \"([^\"]*)\" for "
      + "ROI personal FX Form and enters same in searchbox$")
  public void roi_personal_FX_Form_and_enters_same_in_searchbox(String branch)
      throws Exception {
	  aibPersonalFxFormPageObjectModel = new AiBPersonalFxFormPageObjectModel();
	  aibPersonalFxFormPageObjectModel.navigate();
	  getscreenshot();	    
  }
/**
 * and statement.
 * 
 * @param branch name of the branch
 * @throws Exception 
 */

  @Step
  @And("^Enters \"([^\"]*)\" details$") 
  public void enters_detsils(String
  branch) throws Exception {
  
  aibPersonalFxFormPageObjectModel = new AiBPersonalFxFormPageObjectModel();
  aibPersonalFxFormPageObjectModel.enterBranchDetails(branch); 
  getscreenshot();
  }
 /**
	 * Then statement.
	 * 
	 * @param branch name of the branch.
 * @throws Exception 
	 */

  @Step
  @Then("^customer is not provided with any \"([^\"]*)\" information$") 
  public void customer_is_not_provided_with_any_information(String branch) throws Exception { 
	  boolean expected = false; 
	  aibPersonalFxFormPageObjectModel = new AiBPersonalFxFormPageObjectModel(); 
      boolean actual = aibPersonalFxFormPageObjectModel.invisibilityBranch();
      assertEquals(expected, actual); 
  }
  /**
* Teardown.
 */
 
 @After(order = 0)
 public void quitDriver() { 
	 //driver.quit(); 
	 }
 
 @After(order = 1)
	 public void screenshot(Scenario scenario) throws Exception {
	 if(scenario.isFailed()) {
		 String screenshotName = scenario.getName().replaceAll(" ", "_");
		 final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		 scenario.embed(screenshot, "image/png");
		 getscreenshot();
 }
}
}
 
 
 