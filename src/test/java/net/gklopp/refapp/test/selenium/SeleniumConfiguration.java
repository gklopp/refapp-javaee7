package net.gklopp.refapp.test.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SeleniumConfiguration {

    private String serverHost = System.getProperty("selenium.server.host");

    private final static int DEFAULT_SERVER_PORT = 80;
    private int serverPort = System.getProperty("selenium.server.port") != null ? Integer.valueOf(System.getProperty("selenium.server.port")) : DEFAULT_SERVER_PORT;

    private final static String DEFAULT_BROWSER_TYPE = BrowserType.FIREFOX;
    private String browserType = System.getProperty("selenium.browser.type") != null ? System.getProperty("selenium.browser.type") : DEFAULT_BROWSER_TYPE;

    private final static String DEFAULT_BROWSER_VERSION = "";
    private String browserVersion = System.getProperty("selenium.browser.version") != null ? System.getProperty("selenium.browser.version") : DEFAULT_BROWSER_VERSION;

    private final static Platform DEFAULT_PLATFORM = Platform.WINDOWS;
    private Platform platform = System.getProperty("selenium.platform") != null ? Platform.valueOf(System.getProperty("selenium.platform")) : DEFAULT_PLATFORM;

    private final static int DEFAULT_TIME_OUT_IN_SECONDS = 10;
    private int timeOutInSeconds = System.getProperty("selenium.timeout") != null ? Integer.valueOf(System.getProperty("selenium.timeout")) : DEFAULT_TIME_OUT_IN_SECONDS;

    private String webSiteUnderTestBaseUrl = System.getProperty("selenium.webSiteUnderTest.baseUrl");

    // *************************** GETTERS / SETTERS ***************************

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public int getTimeOutInSeconds() {

        return timeOutInSeconds;
    }

    public void setTimeOutInSeconds(int timeOutInSeconds) {
        this.timeOutInSeconds = timeOutInSeconds;
    }

    public String getWebSiteUnderTestBaseUrl() {
        return webSiteUnderTestBaseUrl;
    }

    public void setWebSiteUnderTestBaseUrl(String webSiteUnderTestBaseUrl) {
        this.webSiteUnderTestBaseUrl = webSiteUnderTestBaseUrl;
    }

    // ***************************** PUBLIC METHODS ****************************

    public WebDriver getWebDriver() {

        switch (browserType) {
            case BrowserType.CHROME:
                return new ChromeDriver(getDesiredCapabilities());
            case BrowserType.FIREFOX:
                return new FirefoxDriver(getDesiredCapabilities());
            case BrowserType.IE:
                return new InternetExplorerDriver(getDesiredCapabilities());
            case BrowserType.SAFARI:
                return new SafariDriver(getDesiredCapabilities());
            default:
                return new FirefoxDriver(getDesiredCapabilities());
        }
    }


    public RemoteWebDriver getRemoteWebDriver() {

        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(getTestServerUrl(), getDesiredCapabilities());
        initWebDriverImplicitWaitTime(remoteWebDriver);
        return remoteWebDriver;
    }

    public URL getTestServerUrl() {

        URL testServerUrl = null;

        try {
            testServerUrl = new URL("http",  getServerHost(), getServerPort(), "/wd/hub");
        } catch (MalformedURLException malformedUrlException) {

            throw new RuntimeException(malformedUrlException);
        }

        return testServerUrl;
    }

    public DesiredCapabilities getDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities(getBrowserType(), getBrowserVersion(), getPlatform());

        if (getBrowserType().equals("internet explorer")) {

            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        }

        return capabilities;
    }

    /**
     * An implicit wait is to tell WebDriver to poll the DOM for a certain amount of time
     * when trying to find an element or elements if they are not immediately available.
     */
    private void initWebDriverImplicitWaitTime(WebDriver webDriver) {

        webDriver.manage().timeouts().implicitlyWait(getTimeOutInSeconds(), TimeUnit.SECONDS);
    }
}
