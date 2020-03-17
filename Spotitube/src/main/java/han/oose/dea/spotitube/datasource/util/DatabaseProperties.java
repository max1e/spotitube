package han.oose.dea.spotitube.datasource.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class DatabaseProperties {

    // TODO add logger for error handling
    // TODO write unit tests

    private Properties properties;

    public DatabaseProperties() {
        properties = new Properties();

        try {
            properties.load(Objects
                    .requireNonNull(getClass()
                    .getClassLoader()
                    .getResourceAsStream("database.properties")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDriver() {
        String driver = properties.getProperty("driver");
        return driver;
    }

    public String getConnectionString() {
        String connectionString = properties.getProperty("connectionString");
        return connectionString;
    }
}
