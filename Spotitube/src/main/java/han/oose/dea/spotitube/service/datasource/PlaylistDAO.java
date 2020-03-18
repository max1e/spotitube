package han.oose.dea.spotitube.service.datasource;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;
import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.controllers.dto.TracksDTO;

import java.util.List;

public interface PlaylistDAO {

    List<PlaylistDTO> getAllPlaylists(String token);
    void deletePlaylist(String token, int id);
    int addPlaylist(String token, String playlist);
    void editPlaylistName(String token, int playlistId, String newName);
    void removeTrackFromPlaylist(String token, int playlistId, int trackId);
    List<TrackDTO> getPlaylistsTracks(int playlistId);
    void addTrackToPlaylist(String token, int playlistId, int trackId);
}
