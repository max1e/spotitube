package han.oose.dea.spotitube.service.datasource;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;

import java.util.List;

public interface TrackDAO {

    List<TrackDTO> getTracksNotInPlaylist(String token, Integer playlistId);
    List<TrackDTO> getAllTracks(String token);
    List<TrackDTO> getPlaylistsTracks(String token, int playlistId);
}
