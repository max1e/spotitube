package han.oose.dea.spotitube.service.datasource;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;

import java.util.List;

public interface TrackDAO {

    /**
     * Returns all tracks of playlist from database
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whose tracks are requested
     * @return All tracks within a TracksDTO
     */
    List<TrackDTO> getPlaylistsTracks(String token, int playlistId);

    /**
     * Returns all tracks that are not in the given playlist
     *
     * @param token The token of the querying user
     * @param playlistId The playlist whose available tracks are requested
     * @return Tracks that are not in the given playlist within a TracksDTO
     */
    List<TrackDTO> getTracksNotInPlaylist(String token, Integer playlistId);

    /**
     * Returns all tracks
     *
     * @param token The token of the querying user
     * @return All tracks within a TracksDTO
     */
    List<TrackDTO> getAllTracks(String token);
}
