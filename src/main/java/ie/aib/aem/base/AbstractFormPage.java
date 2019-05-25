package ie.aib.aem.base;

import ie.aib.aem.utility.UtilityClass;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *Abstract class that abstracts some of the common functionality shared between Online Form Pages.
 */
public class AbstractFormPage extends BaseClass {

  /**
   * Declaring Error within field on WebPage.
   */
  private static final String ERROR_WITHIN_FIELD = "Required Field";
  /**
   *  Declaring Error Text within field on WebPage.
   */
  private static final String ERROR_PLEASE_SELECT = "Please Select";
  /**
   *  Declaring delayed Error within field on WebPage.
   */
  private static final String DELAYED_ERROR_MESSAGE = "Please select an option";
  /**
 * Description: method that takes a weblement as parameter and then tries to delete
 * that webelement using javascript
 * this is so we can check if a form submitted after deleting (bypassing) recaptcha component.
 * @param recaptchaComponent WebElement.
 */

  public void deleteRecaptchaComponent(WebElement recaptchaComponent) {
    System.err.println("entering delete recaptcha");
    ((JavascriptExecutor) driver).executeScript("var elements = document"
        + ".getElementsByClassName('googlerecaptchaV2');"
        + "while(elements.length > 0){"
        + "elements[0].parentNode.removeChild(elements[0]);"
        + "}", recaptchaComponent);
    System.err.println("exiting delete recaptcha");
    hardWait(1000);
  }

  /**
 * Description:- method that takes a webelement as parameter and
 * then uses that webelement as a starting point to deliver a click action on recaptcha checkbox.
 * @param submit WebElement.
 */
  public void clickRecaptchaBox(WebElement submit) {
    Actions builder = new Actions(driver);
    builder.moveToElement(submit, 80, -80).click().build().perform();
    builder.moveToElement(submit, 300, 0).click().build().perform();
    //this extra click is used for 2 forms which have different coordinate
    //(mortgage appointment form & branch appointment form)
    builder.moveToElement(submit, 0, -80).click().build().perform();
    //this extra click is used for social media support form
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
 * Description:- method that takes String var args as parameter.
 * The String(s) represents the form field(s) on that page.
 * It returns the error message(s) relating to that field(s)
 * @param formFields the form field(s) on the page. e.g. first name
 * @param actualPageErrorsMap hashmap containing webelement values.
 *     They represent the page error elements from the relative web page.
 * @return errorMessages the error messages returned when a field(s) is not filled/Submit is clicked
 */
  public String[] getActualErrorMessagesFromForm(HashMap<String,
      WebElement> actualPageErrorsMap, String... formFields) {
    String[] actualErrorMessages = new String[formFields.length];
    for (int i = 0; i < formFields.length; ++i) {
      actualErrorMessages[i] = actualPageErrorsMap.get(formFields[i]).getText();
    }
    return actualErrorMessages;
  }

  /**
 * Description:- method that takes String var args as parameter.
 * The String(s) represents the form field(s) on that page.
 * It returns the error message(s) relating to that field(s).
 * This method is used when an error message is not displayed on screen
 * immediately - and a small wait is required before capturing the error message
 *
 * @param formFields the form field(s) on the page. e.g. first name
 * @param actualPageErrorsMap hashmap containing webelement values.
 *      They represent the page error elements from the relative web page
 * @return errorMessages The error messages returned when a field(s) is not filled/Submit is clicked
 * @throws TimeoutException time out exception occured.
 */
  public String[] getActualDelayedErrorMessagesFromForm(HashMap<String,
      WebElement> actualPageErrorsMap, String... formFields) throws TimeoutException {
    String[] actualErrorMessages = new String[formFields.length];
    if (actualPageErrorsMap.get(formFields[0]).getAttribute("class").equals("textError")) {
      new WebDriverWait(driver, 1).until(ExpectedConditions
          .invisibilityOfElementWithText(By.id(formFields[0]), DELAYED_ERROR_MESSAGE));
    }
    for (int i = 0; i < formFields.length; ++i) {
      actualErrorMessages[i] = actualPageErrorsMap.get(formFields[i]).getText();
    }
    return actualErrorMessages;
  }

  /**
 * Description:- method that takes String var args as parameter.
 * The String(s) represents the form field(s) to be filled in.
 * It returns the error message(s) relating to that field(s).
 * @param formFields the form field(s) on the page. e.g. first name
 * @param properties the properties file relative to the current web page.
 * @return errorMessages error messages returned when a field(s) is not filled/Submit is clicked
 */
  public String[] getExpectedErrorMessagesForForm(UtilityClass properties, String... formFields) {
    String[] expectedErrorMessages = new String[formFields.length];
    for (int i = 0; i < formFields.length; ++i) {
      expectedErrorMessages[i] = properties.getScenarioProperties(formFields[i]);
    }
    return expectedErrorMessages;
  }

  /**
 * Description: method that takes 2 parameters. The first represents the radio set
 * element on the page and the 2nd parameter is the radio button which should be selected.
 * As some radio set children contain different tag names (e.g. "li" or "label"), an
 * if statement is used to catch the radio sets that use the "label" tag
 * @param radioSet the radio set on the form page
 * @param radioOption the radio option which should be chosen
 */
  public void chooseRadioOption(WebElement radioSet, String radioOption) {
    List<WebElement> radioOptions = radioSet.findElements(By.tagName("li"));
    if (radioOptions.size() < 3) {
      radioOptions = radioSet.findElements(By.tagName("label"));
    }
    //if no options, click element/radio button not in group
    if (radioOptions.size() == 0) {
      radioSet.click();
    }
    for (WebElement radioButton : radioOptions) {
      if (radioButton.getText().contains(radioOption)) {
        click(radioButton, "Click on radio button");
      }
    }
  }

  /**
 * method that takes var args of String as parameter. The String is the 'id' attribute
 * of the web element. The method finds that web element (using the id attribute passed in)
 * & returns the text from within that web element.
 * @param field String which to extract the text from
 * @return textFromElements: String[] returning the text from the webelements
 */
  public static String[] getTextFromFieldUsingJavaScript(String... field) {
    String[] textFromElements = new String[field.length];
    JavascriptExecutor js = (JavascriptExecutor) driver;
    //this explicit wait is needed, otherwise, empty strings intermittently returned in loop below
    new WebDriverWait(driver, 2).until(ExpectedConditions
        .textToBePresentInElementValue(By.id(field[0]), ERROR_WITHIN_FIELD));
    for (int i = 0; i < field.length; ++i) {
      WebElement field1 = driver.findElement(By.id(field[i]));
      String textFromElement = (String) js.executeScript("return arguments[0].value;", field1);
      textFromElements[i] = textFromElement;
    }
    return textFromElements;
  }

  /**
   * Function to select the date picker.
   * @param datePicker Webelement.
   * @param date value in String.
   */
  public void selectDateFromDatePicker(WebElement datePicker, String date) {
    String dayOfMonth = date.split(" ")[0];
    String month = date.split(" ")[1];
    String year = date.split(" ")[2];
    try {
      driver.findElement(By.id("preferredDate")).click();
    } catch (WebDriverException ex) {
      click(datePicker, "Click on date picker");
    }
    for (int i = 0; i <= 12; i++) {
      WebElement monthValue = driver.findElement(By.className("ui-datepicker-month"));
      WebElement yearValue = driver.findElement(By.className("ui-datepicker-year"));
      String val = monthValue.getText() + " " + yearValue.getText();
      if (val.equals(month + " " + year)) {
        break;
      } else {
        WebElement nextButton = driver.findElement(By.xpath("//span[text()= 'Next']"));
        if (driver.findElement(By.xpath("//span[text()= 'Next']/ancestor::a")).getAttribute("class")
            .equals("ui-datepicker-next ui-corner-all")) {
          nextButton.click();
        } else {
          throw new MonthNotFoundException("Month is Diable as the data provided is wrong");
        }
      }
    }
    try {
      WebElement dayVal = driver.findElement(By.xpath("//a[text()= '" + dayOfMonth + "'][contains(@href,'#')]"));
      dayVal.click();
    } catch (NoSuchElementException n) {
      throw new DateNotFoundException("Day is Disable");
    } catch (WebDriverException w) {
      throw new DateNotFoundException("Day is Disable");
    }
  }
  /**
   * Class declaration.
   * @author 58577
   *
   */

  class MonthNotFoundException extends RuntimeException {
    /**
     * exception handling.
     * @param reason String value.
     */
    public MonthNotFoundException(String reason) {
      super(reason);
    }
    /**
  * Declaring the serial version UID.
  */

    private static final long serialVersionUID = 1L;
  }
  /**
 * class declaration.
 * @author 58577
 *
 */

  class DateNotFoundException extends NoSuchElementException {
    /**
     * Exception Handling.
     * @param reason String value.
     */
    public DateNotFoundException(String reason) {
      super(reason);
    }
    /**
     * declaring the final serial version UID.
     */

    private static final long serialVersionUID = 1L;
  }

  /**
   * Function to pick the date from old date picker.
   * @param datePicker WebElement.
   * @param date Value in Strings.
   */
  public void selectDateFromOldDatePicker(WebElement datePicker, String date) {
    final String dayOfMonth = date.split(" ")[0];
    String month = date.split(" ")[1];
    String year = date.split(" ")[2];
    click(datePicker, "Click datepicker");
    WebElement datepickerBox = driver.findElement(By.id("ui-datepicker-div"));
    Select monthTable = new Select(datepickerBox.findElement(By.className("ui-datepicker-month")));
    monthTable.selectByVisibleText(month);
    Select yearTable = new Select(datepickerBox.findElement(By.className("ui-datepicker-year")));
    yearTable.selectByVisibleText(year);
    datepickerBox.findElement(By.linkText(dayOfMonth)).click();
  }
}
