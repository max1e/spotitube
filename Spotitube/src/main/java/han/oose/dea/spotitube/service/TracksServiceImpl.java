package han.oose.dea.spotitube.service;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.controllers.dto.TracksDTO;
import han.oose.dea.spotitube.controllers.service.TracksService;
import han.oose.dea.spotitube.service.datasource.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class TracksServiceImpl implements TracksService {

    private TrackDAO trackDAO;

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public TracksDTO getPlaylistsTracks(String token, int playlistId) {
        var tracks = trackDAO.getPlaylistsTracks(token, playlistId);
        var tracksWrapper = new TracksDTO(tracks);
        return tracksWrapper;
    }

    @Override
    public TracksDTO getAvailableTracks(String token, Integer playlistId) {
        List<TrackDTO> availableTracks;

        if (playlistId != null) {
            availableTracks = trackDAO.getTracksNotInPlaylist(token, playlistId);
        }
        else {
            availableTracks = trackDAO.getAllTracks(token);
        }

        var trackWrapper = new TracksDTO(availableTracks);

        return trackWrapper;
    }
}
