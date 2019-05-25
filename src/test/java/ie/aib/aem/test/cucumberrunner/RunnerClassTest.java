package ie.aib.aem.test.cucumberrunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
//@RunWith(Cucumber.class)
/**
 *Cucumber runner class.
 */
@Listeners({ ListenerClass.class })
@CucumberOptions(features = "classpath:feature/FxForm.feature",
    glue = { "ie.aib.aem.test.stepdefinition" },
    plugin = {"json:target/cucumber.json",
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-html-report/report.html",
        "html:target/cucumber-reports" },
        monochrome = true,
        dryRun = false,
        strict = true)

/**
 * An example of using TestNG when the test class does not inherit from
 * AbstractTestNGCucumberTests but still executes each scenario as a separate
 * TestNG test.
 */
public class RunnerClassTest {
	   
/**
   * Run each cucumber scenario found in the features as separated test.
   */
  private TestNGCucumberRunner testnGCucumberRunner;
 
  /**
  * This method should run before class always regardless of condition.
  * @throws Exception exception occured.
  */

  @BeforeClass(alwaysRun = true)
  public void setUpClass() throws Exception {
	  testnGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	  
  }
  /**
 * Test method definition.
 * @param cucumberFeature parameter to display the feature file in a test report.
 */

  @Test(groups = "cucumber", description = "Runs Cucumber scenarios", dataProvider = "features")
  public void feature(CucumberFeatureWrapper cucumberFeature) {
    testnGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
  }
  /**
   * Returns two dimensional array associated CucumberFeatureWrapper feature.
   * @return a two dimensional array of scenarios features.
   */

  @DataProvider
  public Object[][] features() {
    return testnGCucumberRunner.provideFeatures();
  }
  /**
 * After class for teardown.
 * @throws Exception exception occured.
 */

  @AfterClass(alwaysRun = true)
  public void tearDownClass() throws Exception {
    testnGCucumberRunner.finish();
  }
}
		