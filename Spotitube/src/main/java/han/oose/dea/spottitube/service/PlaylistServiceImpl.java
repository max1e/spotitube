package han.oose.dea.spottitube.service;

import han.oose.dea.spottitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spottitube.controllers.dto.PlaylistsDTO;
import han.oose.dea.spottitube.controllers.dto.TrackDTO;
import han.oose.dea.spottitube.controllers.dto.TracksDTO;
import han.oose.dea.spottitube.controllers.service.PlaylistService;

import java.util.ArrayList;
import java.util.List;

public class PlaylistServiceImpl implements PlaylistService {

    private List<PlaylistDTO> playlists;

    public PlaylistServiceImpl() {
        playlists = new ArrayList<>();
        var trackArray = new ArrayList<TrackDTO>();

        trackArray.add(new TrackDTO(3, "Ocean and a rock", "Lisa Hannigan", 337, "Sea sew", 0, "undefined", "undefined", false));
        trackArray.add(new TrackDTO(4, "So Long, Marianne", "Leonard Cohen", 546, "Songs of Leonard Cohen", 0, "undefined", "undefined", false));
        trackArray.add(new TrackDTO(5, "One", "Metallica", 423, "undefined", 37, "18-03-2001", "Long version", true));

        var playlist1 = new PlaylistDTO(1, "Death metal", true, trackArray);
        var playlist2 = new PlaylistDTO(2, "Pop", false, trackArray);
        var playlist3 = new PlaylistDTO(3, "Progressive Rock", true, trackArray);
        playlists.add(playlist1);
        playlists.add(playlist2);
        playlists.add(playlist3);

    }

    @Override
    public PlaylistsDTO getAllPlaylists() {
        var playlistWrapper = new PlaylistsDTO(playlists, 12345);
        return playlistWrapper;
    }

    @Override
    public TracksDTO getPlaylistsTracks(int playlistId) {
        var tracksWrapper = new TracksDTO(playlists.get(0).getTracks());
        return tracksWrapper;
    }

    @Override
    public void deletePlaylist(int id) {
        playlists.removeIf(playlist -> playlist.getId() == id);
    }

    @Override
    public void addPlaylist(PlaylistDTO playlist) {
        playlists.add(playlist);
    }

    @Override
    public void editPlaylistName(PlaylistDTO playlist) {

    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId) {

    }

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO track) {

    }
}
