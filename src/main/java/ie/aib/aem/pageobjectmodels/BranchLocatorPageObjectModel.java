package ie.aib.aem.pageobjectmodels;

import ie.aib.aem.base.BaseClass;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * POM for AIB branch locator page.
 */

@SuppressWarnings("unused")
public class BranchLocatorPageObjectModel extends BaseClass {

  /**
   * css value of contact us button.
   */
  @FindBy(css = ".text-button")
  private WebElement contactUs;
  /**
   * css value of branch locator tab.
   */
  @FindBy(css = "#assistance-layer > div.container.span12.contact_left >"
      + "div.column-2 > ul > li:nth-child(2) > div > div")
  private WebElement branchLocator;
  /**
   * css value ofsearch button.
   */
  @FindBy(css = "#txtSearch")
  private WebElement search;
  /**
   * css value of search button tab.
   */
  @FindBy(css = "#butSearch")
  private WebElement searchButton;
  /**
   * css value of infobox.
   */
  @FindBy(css = "#google-map > div > div > div:nth-child(1) > div:nth-child(3)"
      + "> div > div:nth-child(4) > div > div:nth-child(3)")
  private WebElement infoBox;
  /**
   * css value of go to branch hyperlink.
   */
  @FindBy(css = "#google-map > div > div > div:nth-child(1) > div:nth-child(3)"
      + "> div > div:nth-child(4) > div > div:nth-child(3) > div > a:nth-child(8)")
  private WebElement goToBranchHyperlink;
  /**
   * css value of appointment details tab for branch.
   */
  @FindBy(css = "#google-map > div > div > div:nth-child(1) > div:nth-child(3) >"
      + "div > div:nth-child(4) > div > div:nth-child(3) > div")
  private WebElement appointmentDetails;
  /**
   * css value of Appointment table for branch locator on google map.
   */
  @FindBy(css = "#google-map > div > div > div:nth-child(1) > div:nth-child(3) >"
      + "div > div:nth-child(4) > div > div:nth-child(3) > div > table")
  private WebElement appointmentTable;
  /**
   * css value of branch insible on branch locator search tab.
   */
  @FindBy(css = "#tat_td1")
  private WebElement branchNameInvisibility;
  /**
   * name value of list of branch area.
   */
  @FindBy(name = "area")
  WebElement lstOfBranchArea;
  /**
   * list of name of branches.
   */
  @FindBy(name = "branch")
  WebElement lstOfBranchname;
  /**
   * 
   */
  @FindBy(xpath = "//*[@id=\"mobileForm\"]/div[3]/div/button")
  WebElement submitButton;
  /**
   * xpath of Text value of branch details
   */
  @FindBy(xpath = "//*[@id=\"google-map-container-mobile\"]/div/div/h2")
  WebElement txtValueOfBranch;
  /**
   * Page factory initialization..
   */

  public BranchLocatorPageObjectModel() {
    PageFactory.initElements(driver, this);
  }

  /**
   * Method to navigate to the branch locator page.
   * @return String value of URL.
   */

  public String navigateToBranchLocator() {
    String url = "http://sys.aibtest.ie/branchlocator";
    return url;
  }
  /**
 * method to verify the branch locator url.
 * @return String value expected url.
 */

  public String verifyBranchLocatorUrl() {
    click(branchLocator, "Click on Branch locator");
    for (String winHandle : driver.getWindowHandles()) {
      driver.switchTo().window(winHandle);
    }
    String url = currentUrl();
    return url;
  }

  /**
 * method to enter the branch detail.
 * @param branchName branch name in String
 * @throws InterruptedException 
 */

  public void enterBranchDetails(String branchName) throws InterruptedException {
	  
    explicitWait(search, 40);
    setText(search, branchName);
  }
  /**
 * method to click on the hyperlink.
 */

  public void clickOnHyperlink() {
    click(goToBranchHyperlink, "Click on GoToBranchHyperlink");
  }
  /**
 * method to click on search button.
 */

  public void searchButton() {
    click(searchButton, "Click on SearchButton");
  }
  /**
 * method to enter the branch details.
 */

  public void branchContactDetails() {
    explicitWait(infoBox, 40);
    elementIsVisiblePass(infoBox);
  }
  /**
 * Method to check the hyperlink availability.
 * @return Value in Strings.
 */

  public String hyperlinkAvailability() {
    String hyperlinkValue = goToBranchHyperlink.getText();
    return hyperlinkValue;
  }
  /**
 * Method to verify the appointment info.
 * @return String in value.
 */

  public String appointmentInfo() {
    String hyperlinkValue = appointmentDetails.getText();
    String tableInfo = appointmentTable.getText();
    String actual = hyperlinkValue.replaceAll(tableInfo, "");
    String goToBranchText = goToBranchHyperlink.getText();
    String actual1 = actual.trim().replaceAll(goToBranchText, "");
    String actual2 = actual1.replaceAll("\n", "");
    return actual2;
  }
  /**
 * Method to navigate to branch URL.
 * @return URL.
 */

  public String navigationToBranch() {
    click(goToBranchHyperlink, "Go to Branch hyperlink");
    String url = currentUrl();
    return url;
  }
  /**
 * method to verify the branch invisible.
 * @return String in value.
 */

  public boolean branchInvisibility() {
    boolean result = invisibilityOfElement(branchNameInvisibility);
    return result;
  }
  
  /**
   * method to enter branch details on mobile view.
   */
  
  public void verifyBranchDetail() {
	  selectDropdownUsingValue(lstOfBranchArea, "Dublin 06");
	  selectDropdownUsingValue(lstOfBranchname, "Rathgar");
	  click(submitButton, "submit button clicked");
	  explicitWait(txtValueOfBranch, 20);
	  System.out.println(txtValueOfBranch.getText());
	  elementIsVisiblePass(txtValueOfBranch);
	  click(driver.findElement(By.xpath("//*[@id=\"google-map-container-mobile\"]/div/div/div/ul/li[1]/h3")), "contact details of branch");
	  elementIsVisiblePass(driver.findElement(By.xpath("//*[@id=\"google-map-container-mobile\"]/div/div/div/ul/li[1]/div/div/div")));
	  
  }
}