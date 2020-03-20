package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.datasource.mappers.abstraction.PlaylistMapper;
import han.oose.dea.spotitube.datasource.mappers.abstraction.TrackMapper;
import han.oose.dea.spotitube.datasource.util.DatabaseProperties;
import han.oose.dea.spotitube.datasource.util.ExceptionMapper;
import han.oose.dea.spotitube.service.datasource.PlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {

    private DatabaseProperties databaseProperties;
    private Logger logger;
    private PlaylistMapper playlistMapper;
    private TrackMapper trackMapper;
    private ExceptionMapper exceptionMapper;

    public PlaylistDAOImpl() {
        databaseProperties = new DatabaseProperties();
        logger = Logger.getLogger(getClass().getName());
    }

    @Inject
    public void setPlaylistMapper(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }

    @Inject
    public void setTrackMapper(TrackMapper trackMapper) {
        this.trackMapper = trackMapper;
    }

    @Inject
    public void setExceptionMapper(ExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public List<PlaylistDTO> getAllPlaylists(String token) {
        List<PlaylistDTO> playlists = null;

        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_getAllPlaylists(?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);

            var resultset = sqlStatement.executeQuery();

            playlists = playlistMapper.toPlaylistDTOWithDurationList(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
        }

        return playlists;
    }

    @Override
    public void deletePlaylist(String token, int playlistId) {
        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_deletePlaylist(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setInt(2, playlistId);

            sqlStatement.executeUpdate();

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
        }
    }

    @Override
    public int addPlaylist(String token, String playlistName) {
        var playlistId = 0;

        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_addPlaylist(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setString(2, playlistName);

            var resultset = sqlStatement.executeQuery();
            playlistId = resultset.getInt("playlistId");

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
        }

        return playlistId;
    }

    @Override
    public void editPlaylistName(String token, int playlistId, String newName) {
        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_editPlaylistName(?, ?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setInt(2, playlistId);
            sqlStatement.setString(3, newName);

            sqlStatement.executeUpdate();

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
        }
    }

    @Override
    public void removeTrackFromPlaylist(String token, int playlistId, int trackId) {
        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_removeTrackFromPlaylist(?, ?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setInt(2, playlistId);
            sqlStatement.setInt(3, trackId);

            sqlStatement.executeUpdate();

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
        }
    }

    @Override
    public void addTrackToPlaylist(String token, int playlistId, int trackId) {
        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_addTrackToPlaylist(?, ?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setInt(2, playlistId);
            sqlStatement.setInt(3, trackId);

            sqlStatement.executeUpdate();

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
        }
    }
}