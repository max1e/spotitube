package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.datasource.assembler.abstraction.PlaylistAssembler;
import han.oose.dea.spotitube.datasource.assembler.abstraction.TrackAssembler;
import han.oose.dea.spotitube.datasource.util.DatabaseProperties;
import han.oose.dea.spotitube.service.datasource.PlaylistDAO;

import javax.inject.Inject;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaylistDAOImpl implements PlaylistDAO {

    private DatabaseProperties databaseProperties;
    private Logger logger;
    private PlaylistAssembler playlistAssembler;
    private TrackAssembler trackAssembler;

    public PlaylistDAOImpl() {
        databaseProperties = new DatabaseProperties();
        logger = Logger.getLogger(getClass().getName());
    }

    @Inject
    public void setPlaylistAssembler(PlaylistAssembler playlistAssembler) {
        this.playlistAssembler = playlistAssembler;
    }

    @Inject
    public void setTrackAssembler(TrackAssembler trackAssembler) {
        this.trackAssembler = trackAssembler;
    }

    @Override
    public List<PlaylistDTO> getAllPlaylists(String token) {
        List<PlaylistDTO> playlists = null;

        try {
            // Connect to database
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_getAllPlaylists(?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);

            var resultset = sqlStatement.executeQuery();

            playlists = playlistAssembler.toPlaylistDTOList(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }

        return playlists;
    }

    @Override
    public void deletePlaylist(String token, int playlistId) {
        try {
            // Connect to database
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
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
    }

    @Override
    public int addPlaylist(String token, String playlistName) {
        var playlistId = 0;

        try {
            // Connect to database
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
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }

        return playlistId;
    }

    @Override
    public void editPlaylistName(String token, int playlistId, String newName) {
        try {
            // Connect to database
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
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
    }

    @Override
    public void removeTrackFromPlaylist(String token, int playlistId, int trackId) {
        try {
            // Connect to database
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
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
    }

    @Override
    public List<TrackDTO> getPlaylistsTracks(int playlistId) {
        List<TrackDTO> tracks = null;

        try {
            // Connect to database
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_getPlaylistsTracks(?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setInt(1, playlistId);

            var resultset = sqlStatement.executeQuery();

            tracks = trackAssembler.toTrackDTOList(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }

        return tracks;
    }

    @Override
    public void addTrackToPlaylist(String token, int playlistId, int trackId) {
        try {
            // Connect to database
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
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
    }
}
