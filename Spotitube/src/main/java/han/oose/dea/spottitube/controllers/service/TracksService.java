package han.oose.dea.spottitube.controllers.service;

import han.oose.dea.spottitube.controllers.dto.TracksDTO;

public interface TracksService {
    public TracksDTO getAvailableTracks(int playlistId);
}
