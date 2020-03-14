package han.oose.dea.spottitube.controllers.service;

import han.oose.dea.spottitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spottitube.controllers.dto.PlaylistsDTO;
import han.oose.dea.spottitube.controllers.dto.TrackDTO;
import han.oose.dea.spottitube.controllers.dto.TracksDTO;

public interface PlaylistService {

    public PlaylistsDTO getAllPlaylists();
    public TracksDTO getPlaylistsTracks(int playlistId);
    public void deletePlaylist(int id);
    public void addPlaylist(PlaylistDTO playlist);
    public void editPlaylistName(PlaylistDTO playlist);
    public void removeTrackFromPlaylist(int playlistId, int trackId);
    public void addTrackToPlaylist(int playlistId, TrackDTO track);
}
