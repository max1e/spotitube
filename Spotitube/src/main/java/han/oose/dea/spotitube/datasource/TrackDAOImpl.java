package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.datasource.StatementHandlers.StatementBuilder;
import han.oose.dea.spotitube.datasource.databaseConnection.DatabaseConnector;
import han.oose.dea.spotitube.datasource.mappers.DTOMapper;
import han.oose.dea.spotitube.datasource.exceptions.ExceptionMapper;
import han.oose.dea.spotitube.service.datasource.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Default
public class TrackDAOImpl implements TrackDAO {

    private DatabaseConnector dbConnector;
    private DTOMapper<List<TrackDTO>> trackMapper;
    private ExceptionMapper exceptionMapper;

    @Inject
    public void setTrackMapper(DTOMapper<List<TrackDTO>> trackMapper) {
        this.trackMapper = trackMapper;
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
    public List<TrackDTO> getTracksNotInPlaylist(String token, Integer playlistId) {
        List<TrackDTO> tracks = null;

        try {
            var connection = dbConnector.makeConnection();

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_getTracksNotInPlaylist")
                    .addParameter(token)
                    .addParameter(playlistId)
                    .build();

            var resultset = sqlStatement.executeQuery();
            tracks = trackMapper.toDTO(resultset);

            closeConnection(connection, sqlStatement);
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }

        return tracks;
    }

    @Override
    public List<TrackDTO> getAllTracks(String token) {
        List<TrackDTO> tracks = null;

        try {
            var connection = dbConnector.makeConnection();

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_getPlaylistsTracks")
                    .addParameter(token)
                    .build();

            var resultset = sqlStatement.executeQuery();
            tracks = trackMapper.toDTO(resultset);

            closeConnection(connection, sqlStatement);
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }

        return tracks;
    }

    @Override
    public List<TrackDTO> getPlaylistsTracks(String token, int playlistId) {
        List<TrackDTO> tracks = null;

        try {
            var connection = dbConnector.makeConnection();

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_getPlaylistsTracks")
                    .addParameter(token)
                    .addParameter(playlistId)
                    .build();


            var resultset = sqlStatement.executeQuery();
            tracks = trackMapper.toDTO(resultset);

            closeConnection(connection, sqlStatement);
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }

        return tracks;
    }

    private void closeConnection(Connection connection, PreparedStatement sqlStatement) throws SQLException {
        sqlStatement.close();
        dbConnector.closeConnection(connection);
    }
}
