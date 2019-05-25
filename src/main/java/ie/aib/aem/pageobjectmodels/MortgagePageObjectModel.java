package ie.aib.aem.pageobjectmodels;

import ie.aib.aem.base.BaseClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * POM for AIB Mortgage page.
 */
public class MortgagePageObjectModel extends BaseClass {
  /**
 * id value of submit button.
 */
  @FindBy(id = "personal_mortgage_form_submit")
  private WebElement submitButton;
  /**
 * xpath value of first name error message.
 */
  @FindBy(xpath = "//*[@id='fr_mortgage_person_name']/div[3]")
  private WebElement firstNameErrorMessage;
  /**
 * xpath value of surname error message.
 */
  @FindBy(xpath = "//*[@id='fr_mortgage_person_surname']/div[3]")
  private WebElement surNameErrorMessage;
  /**
 * xpath value of contact number error message.
 */
  @FindBy(xpath = "//*[@id='fr_personalform_contactnumber_home']/div[3]")
  private WebElement contactNumberErrorMessage;
  /**
 * xpath value of preferred contact time error message.
 */
  @FindBy(xpath = "//*[@id='fr_mortgage_preferred_contact_time']/div[2]/div[2]")
  private WebElement preferredContactTimeErrorMessage;
  /**
 * css value of capcha error message.
 */
  @FindBy(css = "#captcha")
  private WebElement captchaErrorMessage;
  /**
 * css value of name field.
 */
  @FindBy(css = "#fr_mortgage_person_name > div.form_leftcol > div.form_leftcollabel > label")
  private WebElement nameField;
  /**
 * css value of sur name field.
 */
  @FindBy(css = "#fr_mortgage_person_surname > div.form_leftcol > div.form_leftcollabel > label")
  private WebElement surnamefield;
  /**
 * css value of contact number field.
 */
  @FindBy(css = "#fr_personalform_contactnumber_home >"
      + "div.form_leftcol > div.form_leftcollabel > label")
  private WebElement contactNumberField;
  /**
 * css value of preferred contact time field.
 */
  @FindBy(css = "#fr_mortgage_preferred_contact_time >"
      + "div.form_leftcol > div.form_leftcollabel > label")
  private WebElement preferredContactTimeField;
  /**
 * css value of captcha frame element.
 */
  @FindBy(xpath = "//iframe[@role= 'presentation']")
  public WebElement captchaFrameElement;
  /**
 * css value of captcha checkbox.
 */
  @FindBy(xpath = "//*[@id=\"recaptcha-anchor\"]/div[5]")
  public WebElement captchaCheckBox;
  /**
 * css value of first name box element.
 */
  @FindBy(css = "#personal_mortgage_form_mortgage_person_name")
  public WebElement firstNameBox;
  /**
 * xpath value of frame.
 */
  @FindBy(xpath = "//iframe[@role= 'presentation']")
  public WebElement iframe;
  /**
 * css value of checkbox.
 */
  @FindBy(css = "#recaptcha-anchor > div.recaptcha-checkbox-checkmark")
  public WebElement captchabox;
  /**
 * css value of firstname.
 */
  @FindBy(css = "#personal_mortgage_form_mortgage_person_name")
  private WebElement firstName;
  /**
 * css value of title.
 */
  @FindBy(css = "#threefluid-center > div.content-page.parsys > div >"
      + "div.personal_title_text > div.personal_title > h1")
  private WebElement title;
  /**
 * page factory initialization..
 */

  public MortgagePageObjectModel() {
    PageFactory.initElements(driver, this);
  }

  /**
 * method to click submit button.
 */
  public void submitButtonClick() {
    submitButton.click();
  }
  /**
 * method to click on captcha.
 */

  public void clickOnCaptcha() {
    clickRecaptcha(submitButton, iframe, captchabox);
  }
  /**
 * method to verify the actual error msg on capcha click.
 * @return actual capcha error in String value.
 */

  public String checkCaptchaError() {
    String captchaErorActual = captchaErrorMessage.getText();
    return captchaErorActual;
  }
  /**
 * method to check the actualerr msgs in all field.
 * @return list of string value.
 */

  public   List<String>  checkErrorMessageForAllFieldsActual() {
    WebElement[] errorMessage = { firstNameErrorMessage,surNameErrorMessage,
        contactNumberErrorMessage,preferredContactTimeErrorMessage};
    explicitWait(surNameErrorMessage,8);
    List<String>  result = checkAllErrorMessagePresentInUiActual(errorMessage);
    return result;
  }
  /**
   * Method to verify the expected check the err msgs in all field.
   * @return List of err msgs expected.
   */

  public  List<String> checkErrorMessageForAllFieldsExpected() {
    List<String> result = Arrays.asList("Please enter your Name","Please enter your Name",
        "Please enter (without spaces) your Contact Number including prefix",
        "Please choose your Preferred Contact Time");
    return result;
  }
  /**
   * Method to set date for field and check all the err msgs on remaining field.
   */

  public void setDataForFieldOneByOneAndCheckErrorMessage() {
    // Need to insert common function
  }
  /**
   * Method to check field names in actual.
   * @return Set of Strings.
   */

  public  Set<String>  checkFieldNameActual() {
    WebElement[] fieldnames = {nameField,surnamefield,
        contactNumberField,preferredContactTimeField };
    return checkFieldValuesPresentInUiActual(fieldnames);
  }
  /**
   *Method to check field names in expected.
   * @return expected field values in array list
   */

  public ArrayList<String> checkFieldNameExpected() {
    ArrayList<String> expected = new ArrayList<String>();
    final ArrayList<String> expected2 = new ArrayList<String>();
    expected.add("First Name");
    expected.add("Surname");
    expected.add("Contact Number");
    expected.add("Preferred Contact Time");
    expected2.addAll(expected);
    return expected2;
  }

  /**
   * Method to verify the title of page.
   * @return title of the page.
   */

  public String titleCheck() {
    return title.getText();
  }
  /**
   * Method to select the preffered time from drop down tab.
   * @return String in value.
   */

  public String preferredContactTimeDropdownValueActual() {
    return dropdownActualValue("#personal_mortgage_form_mortgage_preferred_contact_time");
  }
}
