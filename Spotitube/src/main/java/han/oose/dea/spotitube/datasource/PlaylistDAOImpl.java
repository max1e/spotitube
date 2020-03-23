package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.datasource.StatementHandlers.StatementBuilder;
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

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_getAllPlaylists")
                    .addParameter(token)
                    .build();

            var resultset = sqlStatement.executeQuery();
            playlists = playlistMapper.toDTO(resultset);

            closeConnection(connection, sqlStatement);
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

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_deletePlaylist")
                    .addParameter(token)
                    .addParameter(playlistId)
                    .build();

            sqlStatement.executeUpdate();

            closeConnection(connection, sqlStatement);
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }

    @Override
    public void addPlaylist(String token, String playlistName) {
        try {
            var connection = dbConnector.makeConnection();

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_addPlaylist")
                    .addParameter(token)
                    .addParameter(playlistName)
                    .build();

            sqlStatement.executeUpdate();

            closeConnection(connection, sqlStatement);
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }

    @Override
    public void editPlaylistName(String token, int playlistId, String newName) {
        try {
            var connection = dbConnector.makeConnection();

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_editPlaylistName")
                    .addParameter(token)
                    .addParameter(playlistId)
                    .addParameter(newName)
                    .build();

            sqlStatement.executeUpdate();

            closeConnection(connection, sqlStatement);
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }

    @Override
    public void removeTrackFromPlaylist(String token, int playlistId, int trackId) {
        try {
            var connection = dbConnector.makeConnection();

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_removeTrackFromPlaylist")
                    .addParameter(token)
                    .addParameter(playlistId)
                    .addParameter(trackId)
                    .build();

            sqlStatement.executeUpdate();

            closeConnection(connection, sqlStatement);
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }

    @Override
    public void addTrackToPlaylist(String token, int playlistId, int trackId) {
        try {
            var connection = dbConnector.makeConnection();

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_addTrackToPlaylist")
                    .addParameter(token)
                    .addParameter(playlistId)
                    .addParameter(trackId)
                    .build();

            sqlStatement.executeUpdate();

            closeConnection(connection, sqlStatement);
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }
    }

    private void closeConnection(Connection connection, PreparedStatement sqlStatement) throws SQLException {
        sqlStatement.close();
        dbConnector.closeConnection(connection);
    }
}