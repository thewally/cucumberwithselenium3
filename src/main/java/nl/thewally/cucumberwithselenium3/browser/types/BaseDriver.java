package nl.thewally.cucumberwithselenium3.browser.types;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseDriver implements Driver {

    protected WebDriver driver;
    protected Logger logger = getLogger();

    public BaseDriver() {
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(BaseDriver.class);
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public boolean isClosed() {
        return driver != null ? ((RemoteWebDriver) driver).getSessionId() == null : true;
    }

    @Override
    public void close() {
        if (!isClosed()) {
            this.driver.quit();
        }
    }

}
