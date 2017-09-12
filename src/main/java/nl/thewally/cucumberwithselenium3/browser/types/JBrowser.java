package nl.thewally.cucumberwithselenium3.browser.types;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.RequestHeaders;
import com.machinepublishers.jbrowserdriver.Settings;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class JBrowser extends BaseDriver {

    private boolean quit = false;
    private Settings settings;

    @Override
    public DesiredCapabilities setHeader(Map<String, String> headers) {
        DesiredCapabilities capabilities
                = new DesiredCapabilities("jbrowserdriver", "1", Platform.ANY);

        LinkedHashMap<String, String> headersTmp = new LinkedHashMap<>();
        for (String key : headers.keySet()) {
            headersTmp.put(key, headers.get(key));
        }

        RequestHeaders requestHeaders = new RequestHeaders(headersTmp);
        Settings.Builder builder = Settings.builder()
                .requestHeaders(requestHeaders)
                .javascript(true);
        settings = builder.build();
        return capabilities.merge(builder.buildCapabilities());
    }

    @Override
    public WebDriver init(DesiredCapabilities capabilities) {
        driver = (capabilities != null)
                ? new JBrowserDriver(settings)
                : new JBrowserDriver();
        return getDriver();
    }

    @Override
    public void close() {
        if (!isClosed()) {
            this.quit = true;
            driver.close();
            driver.quit();
        }
    }

    @Override
    public boolean isClosed() {
        return driver != null ? this.quit : true;
    }

}
