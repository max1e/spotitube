package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.datasource.mappers.implementation.TrackMapperImpl;
import han.oose.dea.spotitube.datasource.util.DatabaseProperties;
import han.oose.dea.spotitube.datasource.util.ExceptionMapper;
import han.oose.dea.spotitube.service.datasource.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Default
public class TrackDAOImpl implements TrackDAO {

    private DatabaseProperties databaseProperties;
    private Logger logger;
    private TrackMapperImpl trackMapper;
    private ExceptionMapper exceptionMapper;

    public TrackDAOImpl() {
        databaseProperties = new DatabaseProperties();
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

    @Override
    public List<TrackDTO> getTracksNotInPlaylist(String token, Integer playlistId) {
        List<TrackDTO> tracks = null;

        try {
            Class.forName(databaseProperties.getDriver());
            // Connect to database
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_getTracksNotInPlaylist(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setInt(2, playlistId);

            var resultset = sqlStatement.executeQuery();

            // Read result set
            tracks = trackMapper.toTrackDTOList(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
            throw new InternalServerErrorException("Something went horribly wrong!");
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
            throw new InternalServerErrorException("Something went horribly wrong!");
        }

        return tracks;
    }

    @Override
    public List<TrackDTO> getAllTracks(String token) {
        List<TrackDTO> tracks = null;

        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_getAllTracks(?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);

            // Read result set
            var resultset = sqlStatement.executeQuery();

            tracks = trackMapper.toTrackDTOList(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
            throw new InternalServerErrorException("Something went horribly wrong!");
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
            throw new InternalServerErrorException("Something went horribly wrong!");
        }

        return tracks;
    }

    @Override
    public List<TrackDTO> getPlaylistsTracks(String token, int playlistId) {
        List<TrackDTO> tracks = null;

        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_getPlaylistsTracks(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);
            sqlStatement.setInt(2, playlistId);

            // Read result set
            var resultset = sqlStatement.executeQuery();

            tracks = trackMapper.toTrackDTOList(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
            throw new InternalServerErrorException("Something went horribly wrong!");
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
            throw new InternalServerErrorException("Something went horribly wrong!");
        }

        return tracks;
    }
}
