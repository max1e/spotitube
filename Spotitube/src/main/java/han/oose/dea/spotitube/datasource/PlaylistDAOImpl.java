package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.datasource.util.DatabaseProperties;
import han.oose.dea.spotitube.domain.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaylistDAOImpl {

    private DatabaseProperties databaseProperties;
    private Logger logger;

    public PlaylistDAOImpl() {
        databaseProperties = new DatabaseProperties();
        logger = Logger.getLogger(getClass().getName());
    }

    public List<Playlist> findAllPlaylists() {
        var playlists = new ArrayList<Playlist>();

        try {
            // Connect to database
//            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlStatement = connection.prepareStatement("SELECT * FROM Playlists");
            var resultSet = sqlStatement.executeQuery();

            // Read result set
            while (resultSet.next()) {
                // Find tracks of playlist
                playlists.add(new Playlist(
                        resultSet.getInt("playlistId"),
                        resultSet.getString("playlistName"),
                        resultSet.getBoolean("isOwner"),
                        null // tracks of playlist in
                ));
            }

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }

        return playlists;
    }

    public boolean addPlaylist(Playlist playlist) {
        boolean querySucceeded;

        try {
            // Connect to database
//            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlStatement = connection.prepareStatement("SELECT * FROM Playlists");
            var rowsAffected = sqlStatement.executeUpdate();
            querySucceeded = rowsAffected > 0;

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            querySucceeded = false;
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }

        return querySucceeded;
    }
}
