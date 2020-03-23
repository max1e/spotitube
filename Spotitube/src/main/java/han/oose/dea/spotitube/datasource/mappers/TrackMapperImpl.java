package han.oose.dea.spotitube.datasource.mappers;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackMapperImpl implements DTOMapper<List<TrackDTO>> {

    @Override
    public List<TrackDTO> toDTO(ResultSet resultset) throws SQLException {
        var tracks = new ArrayList<TrackDTO>();

        while (resultset.next()) {
            tracks.add(new TrackDTO(
                    resultset.getInt("trackId"),
                    resultset.getString("title"),
                    resultset.getString("performerName"),
                    resultset.getInt("duration"),
                    resultset.getString("albumName"),
                    resultset.getInt("playcount"),
                    resultset.getString("publicationDate"),
                    resultset.getString("trackDescription"),
                    resultset.getBoolean("offlineAvailable"))
            );
        }

        return tracks;
    }
}
