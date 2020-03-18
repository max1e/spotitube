package han.oose.dea.spotitube.service;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.controllers.dto.TracksDTO;
import han.oose.dea.spotitube.controllers.service.TracksService;
import han.oose.dea.spotitube.service.datasource.TracksDAO;

import javax.inject.Inject;
import java.util.List;

public class TracksServiceImpl implements TracksService {

    private TracksDAO tracksDAO;

    @Inject
    public void setTracksDAO(TracksDAO tracksDAO) {
        this.tracksDAO = tracksDAO;
    }

    @Override
    public TracksDTO getAvailableTracks(Integer playlistId) {
        List<TrackDTO> availableTracks;

        if (playlistId != null) {
            availableTracks = tracksDAO.getTracksNotInPlaylist(playlistId);
        }
        else {
            availableTracks = tracksDAO.getAllTracks();
        }

        var trackWrapper = new TracksDTO(availableTracks);

        return trackWrapper;
    }
}
