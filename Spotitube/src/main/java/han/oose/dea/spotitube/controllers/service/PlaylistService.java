package han.oose.dea.spotitube.controllers.service;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;
import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.controllers.dto.TracksDTO;

public interface PlaylistService {

    PlaylistsDTO getAllPlaylists(String token);
    TracksDTO getPlaylistsTracks(String token, int playlistId);
    void deletePlaylist(String token, int id);
    void addPlaylist(String token, PlaylistDTO playlist);
    void editPlaylistName(String token, int playlistId, PlaylistDTO playlist);
    void removeTrackFromPlaylist(String token, int playlistId, int trackId);
    void addTrackToPlaylist(String token, int playlistId, TrackDTO track);
}
