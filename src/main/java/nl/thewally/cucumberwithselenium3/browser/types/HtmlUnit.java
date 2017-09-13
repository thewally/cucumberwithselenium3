package nl.thewally.cucumberwithselenium3.browser.types;

import com.gargoylesoftware.htmlunit.WebClient;
import nl.thewally.cucumberwithselenium3.properties.TestProperties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;

@Component
public class HtmlUnit extends BaseDriver {

    @Autowired
    private TestProperties properties;

    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(HtmlUnit.class);
    }

    private Map<String, String> headers;

    @Override
    public DesiredCapabilities setHeader(Map<String, String> headers) {
        this.headers = headers;

        DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
        capabilities.setCapability("marionette", true);
        capabilities.setPlatform(Platform.ANY);
        return capabilities;
    }

    @Override
    public WebDriver init(DesiredCapabilities capabilities) {
        try {
            driver = (capabilities == null) ? new HtmlUnitDriver()
                    : new HtmlUnitDriver(capabilities);
            if(headers!=null) {
                WebClient webClient = null;
                try {
                    webClient = (WebClient) get(driver, "webClient");
                    for (String key : headers.keySet()) {
                        webClient.addRequestHeader(key, headers.get(key));
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            return driver;
        } catch (RuntimeException e) {
            logger.error("Error on trying to create fire fox driver instance", e);
            throw new RuntimeException("Error on creating new Firefox driver", e);
        }
    }

    private static Object get(Object object, String field) throws Exception {
        Field f = object.getClass().getDeclaredField(field);
        f.setAccessible(true);
        return f.get(object);
    }

    @Override
    public void close() {
        if (!isClosed()) {
            try {
                driver.quit();
            } catch (Exception e) {
                logger.error("Error trying to close firefox driver", e);
            }
        }
    }

    /**
     * related to firefox timing issues. Increase this value to slow your test
     * speed down.
     */
    private static final long NUMBER_OF_MILLIS = 45;

    @Override
    public WebDriver getDriver() {
        // this is done because the firefox driver does not wait for ajax calls
        // on page loads. Which cause tests to fail.
        try {
            Thread.sleep(NUMBER_OF_MILLIS);
        } catch (InterruptedException e) {
            logger.error("error trying to sleep driver", e);
        }

        return super.getDriver();
    }

}
