package han.oose.dea.spotitube.service.datasource;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;

import java.util.List;

public interface PlaylistDAO {

    List<PlaylistDTO> getAllPlaylists(String token);
    void deletePlaylist(String token, int playlistId);
    void addPlaylist(String token, String playlistName);
    void editPlaylistName(String token, int playlistId, String newName);
    void removeTrackFromPlaylist(String token, int playlistId, int trackId);
    void addTrackToPlaylist(String token, int playlistId, int trackId);
}
