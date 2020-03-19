package han.oose.dea.spotitube.datasource.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {

    private Properties properties;
    private Logger logger;

    public DatabaseProperties() {
        properties = new Properties();

        try {
            properties.load(Objects
                    .requireNonNull(getClass()
                    .getClassLoader()
                    .getResourceAsStream("database.properties")));
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading database properties: " + e);
        }
    }

    public String getDriver() {
        var driver = properties.getProperty("driver");
        return driver;
    }

    public String getConnectionString() {
        var connectionString = properties.getProperty("connectionString");
        return connectionString;
    }
}
