package nl.thewally.cucumberwithselenium3.browser.context;

import nl.thewally.cucumberwithselenium3.browser.Browser;
import nl.thewally.cucumberwithselenium3.browser.properties.TestProperties;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppContext {

    private static final Logger LOG = LoggerFactory.getLogger(AppContext.class);

    private final Browser browserDriver;

    @Autowired
    public AppContext(TestProperties properties) {
        try {
            browserDriver = new Browser(properties.getProperty("browser"));
        } catch (Exception e) {
            LOG.error("Error on creating application context", e);
            throw new RuntimeException("Error on creating application context", e);
        }
    }

    public Browser getBrowserDriver() {
        return browserDriver;
    }

    public WebDriver getDriver() {
        return browserDriver.getDriver();
    }

}
