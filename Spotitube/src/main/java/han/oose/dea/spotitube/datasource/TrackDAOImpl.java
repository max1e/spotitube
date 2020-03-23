package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.datasource.databaseConnection.DatabaseConnector;
import han.oose.dea.spotitube.datasource.mappers.TrackMapperImpl;
import han.oose.dea.spotitube.datasource.exceptions.ExceptionMapper;
import han.oose.dea.spotitube.service.datasource.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Default
public class TrackDAOImpl implements TrackDAO {

    private DatabaseConnector dbConnector;
    private Logger logger;
    private TrackMapperImpl trackMapper;
    private ExceptionMapper exceptionMapper;

    public TrackDAOImpl() {
        logger = Logger.getLogger(getClass().getName());
    }

    @Inject
    public void setTrackMapper(TrackMapperImpl trackMapper) {
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

            // Query database
            var sqlQuery = "CALL sp_getTracksNotInPlaylist(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setInt(2, playlistId);

            var resultset = sqlStatement.executeQuery();

            // Read result set
            tracks = trackMapper.toDTO(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
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

            // Query database
            var sqlQuery = "CALL sp_getAllTracks(?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);

            // Read result set
            var resultset = sqlStatement.executeQuery();

            tracks = trackMapper.toDTO(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
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

            // Query database
            var sqlQuery = "CALL sp_getPlaylistsTracks(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setInt(2, playlistId);

            // Read result set
            var resultset = sqlStatement.executeQuery();

            tracks = trackMapper.toDTO(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }

        return tracks;
    }
}
