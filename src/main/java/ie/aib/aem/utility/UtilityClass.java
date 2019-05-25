package ie.aib.aem.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
/**
 * Utility class.
 */

public class UtilityClass {

  /**
   * xyz.
   */
  private String scenarioProperties;
  /**
 *xyz.
 */

  public UtilityClass() {

  }

  /**
 * Utility class.
 * @param scenarioProperties string args passing.
 */
  public UtilityClass(String scenarioProperties) {
    this.scenarioProperties = scenarioProperties;
  }

  /*******************************************************
 * Utility For Group Web-Site Regression test cases.
 *******************************************************
 * Test-cases: Group Web-site Regression Description: Function to get the data.
 * from properties file.
 * @param propertiesFileName property file name.
 * @param propKey property key in string value.
 * @return propKey String value.
 */

  public String getProperties(String propKey, String propertiesFileName) {
    try {
      String fileProperties = ClassLoader.getSystemResource(propertiesFileName).getFile();
      FileInputStream fis = new FileInputStream(fileProperties);
      Properties prop = new Properties();
      prop.load(fis);
      return prop.getProperty(propKey);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
 * properties method for UTF characters.
 * @param skey key value.
 * @param propertiesFileName property file name.
 * @return skey keyvalue.
 */

  public String getPropertyutf(String skey, String propertiesFileName) {
    try {
      String fileProperties = ClassLoader.getSystemResource(propertiesFileName).getFile();
      Properties props = new Properties();
      FileInputStream input = new FileInputStream(new File(fileProperties));
      Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
      props.load(reader);
      return props.getProperty(skey);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * function to get the scenario property.
   * @param skey key value.
   * @return skey String in value.
   */

  public String getScenarioProperties(String skey) {
    try {
      String fileProperties = ClassLoader.getSystemResource(scenarioProperties).getFile();
      FileInputStream fis = new FileInputStream(fileProperties);
      Properties prop = new Properties();
      prop.load(fis);
      return prop.getProperty(skey);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
 * Function to get the scenario properties.
 * @param requiredProperties property value.
 * @return requesteddProperties String in Value.
 */

  public String[] getScenarioProperties(String... requiredProperties) {
    try {
      String[] requesteddProperties = new String[requiredProperties.length];
      for (int i = 0; i < requiredProperties.length; i++) {
        String fileProperties = ClassLoader.getSystemResource(scenarioProperties).getFile();
        FileInputStream fis = new FileInputStream(fileProperties);
        Properties properties = new Properties();
        properties.load(fis);
        requesteddProperties[i] = properties.getProperty(requiredProperties[i]);
      }
      return requesteddProperties;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}