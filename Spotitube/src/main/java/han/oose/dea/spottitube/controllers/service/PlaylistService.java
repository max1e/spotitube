package han.oose.dea.spottitube.controllers.service;

import han.oose.dea.spottitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spottitube.controllers.dto.PlaylistsDTO;
import han.oose.dea.spottitube.controllers.dto.TracksDTO;

public interface PlaylistService {

    public PlaylistDTO getPlaylist(int playlistId);
    public PlaylistsDTO getAllPlaylists();
    public TracksDTO getPlaylistsTracks(int playlistId);
    public void deletePlaylist(int id);
    public void addPlaylist(PlaylistDTO playlist);
}
