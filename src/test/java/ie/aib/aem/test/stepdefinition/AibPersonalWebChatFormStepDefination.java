
package ie.aib.aem.test.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import ie.aib.aem.base.BaseClass;
import ie.aib.aem.pageobjectmodels.AibPersonalWebChatFormPageObjectModel;

/**
 * Step definition class of AIB Personal WebChat Form page.
 */
public class AibPersonalWebChatFormStepDefination extends BaseClass {
	
	AibPersonalWebChatFormPageObjectModel aibPersonalWebChatFormPageObjectModel;

  /**
   * Given statement.
   * @throws Throwable exception occured.
   */
  @Given("^that customer navigates to web chat for for requesting a new Product$")
  public void that_customer_navigates_to_web_chat_for_for_requesting_a_new_Product()
      throws Throwable {
	  
	 aibPersonalWebChatFormPageObjectModel = new AibPersonalWebChatFormPageObjectModel();
	 aibPersonalWebChatFormPageObjectModel.navigate();
  }

  /**
   * And statement.
   * @param product is name of product for dropdown
   * @throws Throwable exception occured.
   */
  @And("^is able to select \"([^\"]*)\" from Product Requested dropdown$")
  public void selects_from_Product_Requested_dropdown(String product) throws Throwable {

    aibPersonalWebChatFormPageObjectModel = new AibPersonalWebChatFormPageObjectModel();
    aibPersonalWebChatFormPageObjectModel.dropdownSelect(product);

  }
}
