package nl.thewally.cucumberwithselenium3.browser;

import nl.thewally.cucumberwithselenium3.browser.types.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DriverFactory.class);

    private static boolean hasBrowser(String browser) {
        return DriverType.getDriverType(browser) != null;
    }

    private static Driver newDriverInstance(DriverType browser) {
        try {
            return browser.getDriverClass().newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Error trying to get driver instance for"
                    + " driver [" + browser + "]", e);
        }
    }

    public static Driver getDriver(String browser) {
        if (!DriverFactory.hasBrowser(browser)) {
            LOG.error("Used configuration for browser property in "
                    + "testdata.properties is not compatible. "
                    + "Change browser or implement new browser.");
            throw new RuntimeException(browser + " is not supported, supported "
                    + "browsers are 'firefox', 'Chrome' and 'phantomJS'");
        }

        return newDriverInstance(DriverType.getDriverType(browser));
    }
}
