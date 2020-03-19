package han.oose.dea.spotitube.service.datasource;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;

import java.util.List;

public interface TrackDAO {

    List<TrackDTO> getTracksNotInPlaylist(Integer playlistId);
    List<TrackDTO> getAllTracks();
}
