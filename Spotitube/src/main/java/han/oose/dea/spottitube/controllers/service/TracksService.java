package han.oose.dea.spottitube.controllers.service;

import han.oose.dea.spottitube.controllers.dto.TracksDTO;

public interface TracksService {

    TracksDTO getAvailableTracks(int playlistId);
}
