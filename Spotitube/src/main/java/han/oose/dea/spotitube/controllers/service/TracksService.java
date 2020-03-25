package han.oose.dea.spotitube.controllers.service;

import han.oose.dea.spotitube.controllers.dto.TracksDTO;

public interface TracksService {

    /**
     * Returns all tracks of playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whose tracks are requested
     * @return All tracks within a TracksDTO
     */
    TracksDTO getPlaylistsTracks(String token, int playlistId);

    /**
     * Returns all tracks that are not in the given playlist, if no playlist are given all tracks are returned
     *
     * @param token The token of the querying user
     * @param playlistId The playlist whose available tracks are requested
     * @return Tracks that are not in the given playlist within a TracksDTO
     */
    TracksDTO getAvailableTracks(String token, Integer playlistId);
}
