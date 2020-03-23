package han.oose.dea.spotitube.datasource.databaseConnection;

import javax.enterprise.inject.Default;
import javax.ws.rs.InternalServerErrorException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Default
public class DatabaseProperties {

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
            throw new InternalServerErrorException("Error loading database properties: ", e);
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