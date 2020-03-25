package han.oose.dea.spotitube.controllers.service;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;
import han.oose.dea.spotitube.controllers.dto.TrackDTO;

public interface PlaylistService {

    /**
     * Returns all playlists
     *
     * @param token The token of the querying user
     * @return All playlists with total duration within a PlaylistsDTO
     */
    PlaylistsDTO getAllPlaylists(String token);

    /**
     * Deletes a playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist to be deleted
     */
    void deletePlaylist(String token, int playlistId);

    /**
     * Creates a new playlist
     *
     * @param token The token of the querying user
     * @param playlist The playlist to be added
     */
    void addPlaylist(String token, PlaylistDTO playlist);

    /**
     * Edits the name of a playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whose name is edited
     * @param playlist The playlist whose edited containing its new name
     */
    void editPlaylistName(String token, int playlistId, PlaylistDTO playlist);

    /**
     * Removes a track from a playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whose track is removed
     * @param trackId The ID of the track to be removed
     */
    void removeTrackFromPlaylist(String token, int playlistId, int trackId);

    /**
     * Adds a track to a playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whom a track is added to
     * @param track The track to be added
     */
    void addTrackToPlaylist(String token, int playlistId, TrackDTO track);
}
