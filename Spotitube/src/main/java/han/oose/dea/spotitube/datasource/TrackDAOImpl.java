package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.datasource.mappers.implementation.TrackMapperImpl;
import han.oose.dea.spotitube.datasource.util.DatabaseProperties;
import han.oose.dea.spotitube.datasource.util.ExceptionMapper;
import han.oose.dea.spotitube.service.datasource.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Default
public class TrackDAOImpl implements TrackDAO {

    private DatabaseProperties databaseProperties;
    private Logger logger;
    private TrackMapperImpl trackAssemblerImpl;

    public TrackDAOImpl() {
        databaseProperties = new DatabaseProperties();
        logger = Logger.getLogger(getClass().getName());
    }

    @Inject
    public void setTrackAssemblerImpl(TrackMapperImpl trackAssemblerImpl) {
        this.trackAssemblerImpl = trackAssemblerImpl;
    }

    @Override
    public List<TrackDTO> getTracksNotInPlaylist(Integer playlistId) {
        List<TrackDTO> tracks = null;

        try {
            Class.forName(databaseProperties.getDriver());
            // Connect to database
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_getTracksNotInPlaylist(?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setInt(1, playlistId);

            var resultset = sqlStatement.executeQuery();

            // Read result set
            tracks = trackAssemblerImpl.toTrackDTOList(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            ExceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
        }

        return tracks;
    }

    @Override
    public List<TrackDTO> getAllTracks() {
        List<TrackDTO> tracks = null;

        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_getAllTracks()";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            var resultset = sqlStatement.executeQuery();

            // Read result set
            tracks = trackAssemblerImpl.toTrackDTOList(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            ExceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
        }

        return tracks;
    }
}
