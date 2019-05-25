package ie.aib.aem.pageobjectmodels;

import ie.aib.aem.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * POM for AIB financial advisor form page.
 */
public class FinancialAdvisorFormPageObjectModel extends BaseClass {
  /**
   * Page factory initialization.
   */
  public FinancialAdvisorFormPageObjectModel() {
    PageFactory.initElements(driver, this);
  }

  /**
   * css value of contact time.
   */
  @FindBy(css = "#personal_financialadvisor_personalform_preferredtime")
  private WebElement contactTime;
  /**
   * Method to verify the preferred time expected.
   * @return String value of preferred time expected.
   */

  public String preferredContactTimeExpected() {
    return getProperties("PreferredContactTimeDropdown", "FinancialAdvisorForm.properties");
  }
  /**
   * Method for preferred contact time actual.
   * @return String value.
   */

  public String preferredContactTimeActual() {
    String value = dropdownActualValue("#personal_financialadvisor_personalform_preferredtime");
    return value;
  }
  /**
 * method to select drop down.
 */

  public void selectDropdown() {
    selectDropdown(contactTime,getProperties("PreferredContactTime",
        "FinancialAdvisorForm.properties"));
  }
}

