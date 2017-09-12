package nl.thewally.cucumberwithselenium3.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "application.properties")
public class TestProperties {

    @Autowired
    private Environment environment;

    public String getProperty(String propName) {
        return environment.getProperty(propName);
    }
}
