package han.oose.dea.spotitube.datasource.assembler.implementation;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.datasource.assembler.abstraction.TrackAssembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackAssemblerImpl implements TrackAssembler {

    @Override
    public List<TrackDTO> toTrackDTOList(ResultSet resultset) throws SQLException {
        var tracks = new ArrayList<TrackDTO>();

        while (resultset.next()) {
            tracks.add(new TrackDTO(
                    resultset.getInt("trackId"),
                    resultset.getString("title"),
                    resultset.getString("performer"),
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
