package nl.thewally.cucumberwithselenium3.browser;

import nl.thewally.cucumberwithselenium3.browser.types.*;

public enum DriverType {

    FIREFOX(Firefox.class),
    PHANTOMJS(Phantomjs.class),
    CHROME(Chrome.class),
    HTMLUNIT(HtmlUnit.class);

    private final Class<? extends Driver> driver;

    DriverType(final Class<? extends Driver> driver) {
        this.driver = driver;
    }

    /**
     * @return the driver
     */
    public Class<? extends Driver> getDriverClass() {
        return driver;
    }

    public static DriverType getDriverType(String browser) {
        DriverType[] browsers = DriverType.values();
        for (DriverType app : browsers) {
            if (app.name().equalsIgnoreCase(browser)) {
                return app;
            }
        }
        return null;
    }

}
