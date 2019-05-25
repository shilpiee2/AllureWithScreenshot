package ie.aib.aem.pageobjectmodels;

import ie.aib.aem.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * POM Mortgage appointment request form.
 */

public class MortgageAppointmentRequestFormPageObjectModel extends BaseClass {
  /**
 * css value of aapointment preffred dropdown.
 */
  @FindBy(css = ".appointment_dropdown")
  private WebElement appointmentPrefDropdown;
  /**
 * page factory initialization.
 */

  public MortgageAppointmentRequestFormPageObjectModel() {
    PageFactory.initElements(driver, this);
  }
  /**
 * Method to select drop down.
 * @param appointmentPref Appointmenet preffered time.
 */

  public void dropdownSelect(String appointmentPref) {
    selectDropdown(appointmentPrefDropdown,appointmentPref);
  }
}
