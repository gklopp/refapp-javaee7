package net.gklopp.refapp.web.actor;

import static org.junit.Assert.assertTrue;

import net.gklopp.refapp.test.selenium.SeleniumConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateActorWebIT {

    private static String refappBaseUrl;
    private static WebDriver driver;

    // ***************************** INITIALIZATION ****************************

    @BeforeClass
    public static void init() throws Exception {

        SeleniumConfiguration seleniumConfiguration = new SeleniumConfiguration();
        driver = seleniumConfiguration.getWebDriver();
        refappBaseUrl = seleniumConfiguration.getWebSiteUnderTestBaseUrl();
    }

    @AfterClass
    public static void tearDown() {

        driver.close();
    }

    // *************************************************************************

    @Test
    public void shouldCreateActor() throws Exception {

        driver.get(refappBaseUrl + "createActor.xhtml");

        WebElement userIdElement = findElementByPartialId("userId");
        String userId = "userId";
        userIdElement.sendKeys(userId);

        WebElement firstNameElement = findElementByPartialId("firstName");
        firstNameElement.sendKeys("fistName");

        WebElement lastNameElement = findElementByPartialId("lastName");
        lastNameElement.sendKeys("lastName");

        WebElement createButton = driver.findElement(By.xpath("//input[@type='submit']"));
        createButton.click();

        assertTrue("Message 'Actor created' not found",
                   driver.getPageSource().contains("Actor '" + userId + "' has been created"));

    }

    @Test
    public void createActor_should_display_errors_if_missing_values() {

        driver.get(refappBaseUrl + "createActor.xhtml");

        WebElement createButton = driver.findElement(By.xpath("//input[@type='submit']"));
        createButton.click();

        assertTrue("Message 'User id not valid' not found",
                driver.getPageSource().contains("User id is not valid"));
        assertTrue("Message 'First name not valid' not found",
                driver.getPageSource().contains("First name is not valid"));
        assertTrue("Message 'Last name not valid' not found",
                   driver.getPageSource().contains("Last name is not valid"));
    }

    private WebElement findElementByPartialId(String partOfElementId) {

        String xpathExpression = "//input[contains(@id,'" + partOfElementId + "')]";
        return driver.findElement(By.xpath(xpathExpression));
    }
}