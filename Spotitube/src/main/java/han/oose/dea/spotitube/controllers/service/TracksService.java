package han.oose.dea.spotitube.controllers.service;

import han.oose.dea.spotitube.controllers.dto.TracksDTO;

public interface TracksService {

    TracksDTO getAvailableTracks(int playlistId);
}
