package han.oose.dea.spotitube.service.datasource;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;

import java.util.List;

public interface PlaylistDAO {

    /**
     * Returns all playlists from database
     *
     * @param token The token of the querying user
     * @return All playlists with total duration within a PlaylistsDTO
     */
    List<PlaylistDTO> getAllPlaylists(String token);

    /**
     * Deletes a playlist in database
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist to be deleted
     */
    void deletePlaylist(String token, int playlistId);

    /**
     * Creates a new playlist in database
     *
     * @param token The token of the querying user
     * @param playlistName The name of the playlist to be added
     */
    void addPlaylist(String token, String playlistName);

    /**
     * Edits the name of a playlist in database
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whose name is edited
     * @param newName The new name of the playlist
     */
    void editPlaylistName(String token, int playlistId, String newName);

    /**
     * Removes a track from a playlist in database
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whose track is removed
     * @param trackId The ID of the track to be removed
     */
    void removeTrackFromPlaylist(String token, int playlistId, int trackId);

    /**
     * Adds a track to a playlistin database
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whom a track is added to
     * @param trackId The ID of the track to be added
     */
    void addTrackToPlaylist(String token, int playlistId, int trackId);
}
