package han.oose.dea.spottitube.controllers.service;

import han.oose.dea.spottitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spottitube.controllers.dto.PlaylistsDTO;
import han.oose.dea.spottitube.controllers.dto.TrackDTO;
import han.oose.dea.spottitube.controllers.dto.TracksDTO;

public interface PlaylistService {

    PlaylistsDTO getAllPlaylists();
    TracksDTO getPlaylistsTracks(int playlistId);
    void deletePlaylist(int id);
    void addPlaylist(PlaylistDTO playlist);
    void editPlaylistName(PlaylistDTO playlist);
    void removeTrackFromPlaylist(int playlistId, int trackId);
    void addTrackToPlaylist(int playlistId, TrackDTO track);
}
