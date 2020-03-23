package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.datasource.databaseConnection.DatabaseConnector;
import han.oose.dea.spotitube.datasource.mappers.DTOMapper;
import han.oose.dea.spotitube.datasource.exceptions.ExceptionMapper;
import han.oose.dea.spotitube.service.datasource.PlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.*;
import java.util.List;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {

    private DatabaseConnector dbConnector;
    private DTOMapper<List<PlaylistDTO>> playlistMapper;
    private ExceptionMapper exceptionMapper;

    @Inject
    public void setPlaylistMapper(DTOMapper<List<PlaylistDTO>> playlistMapper) {
        this.playlistMapper = playlistMapper;
    }

    @Inject
    public void setExceptionMapper(ExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
    }

    @Inject
    public void setDbConnector(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public List<PlaylistDTO> getAllPlaylists(String token) {
        List<PlaylistDTO> playlists = null;

        try {
            var connection = dbConnector.makeConnection();

            // Query database
            var sqlQuery = "CALL sp_getAllPlaylists(?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);

            var resultset = sqlStatement.executeQuery();

            playlists = playlistMapper.toDTO(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }

        return playlists;
    }

    @Override
    public void deletePlaylist(String token, int playlistId) {
        try {
            var connection = dbConnector.makeConnection();

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
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }

    @Override
    public void addPlaylist(String token, String playlistName) {
        try {
            var connection = dbConnector.makeConnection();

            // Query database
            var sqlQuery = "CALL sp_addPlaylist(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setString(2, playlistName);

            sqlStatement.executeUpdate();

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }

    @Override
    public void editPlaylistName(String token, int playlistId, String newName) {
        try {
            var connection = dbConnector.makeConnection();

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
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }

    @Override
    public void removeTrackFromPlaylist(String token, int playlistId, int trackId) {
        try {
            var connection = dbConnector.makeConnection();

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
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }

    @Override
    public void addTrackToPlaylist(String token, int playlistId, int trackId) {
        try {
            var connection = dbConnector.makeConnection();

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
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }
}