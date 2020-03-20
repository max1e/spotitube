package han.oose.dea.spotitube.service;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;
import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.controllers.dto.TracksDTO;
import han.oose.dea.spotitube.controllers.service.PlaylistService;
import han.oose.dea.spotitube.service.datasource.PlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.util.List;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistDAO playlistDAO;

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public PlaylistsDTO getAllPlaylists(String token) {
        var playlists = playlistDAO.getAllPlaylists(token);
        var playlistsWrapper = putPlaylistsInWrapper(playlists);
        return playlistsWrapper;
    }

    private PlaylistsDTO putPlaylistsInWrapper(List<PlaylistDTO> playlists) {
        var totalPlaylistsDuration = calculateTotalPlaylistsDuration(playlists);
        var playlistsWrapper = new PlaylistsDTO(playlists, totalPlaylistsDuration);
        return playlistsWrapper;
    }

    private int calculateTotalPlaylistsDuration(List<PlaylistDTO> playlists) {
        var totalPlaylistsDuration = 0;

        for (var playlist : playlists) {
            totalPlaylistsDuration += playlist.getDuration();
        }

        return totalPlaylistsDuration;
    }

    @Override
    public TracksDTO getPlaylistsTracks(int playlistId) {
        var tracks = playlistDAO.getPlaylistsTracks(playlistId);
        var tracksWrapper = new TracksDTO(tracks);
        return tracksWrapper;
    }

    @Override
    public void deletePlaylist(String token, int id) {
        playlistDAO.deletePlaylist(token, id);
    }

    @Override
    public void addPlaylist(String token, PlaylistDTO playlist) {
        var playlistName = playlist.getName();

        var playlistId = playlistDAO.addPlaylist(token, playlistName);

        var playlistTracks = playlist.getTracks();
        for (var track : playlistTracks) {
            var trackId = track.getId();
            playlistDAO.addTrackToPlaylist(token, playlistId, trackId);
        }
    }

    @Override
    public void editPlaylistName(String token, int playlistId, PlaylistDTO playlist) {
        if (playlist.getId() != playlistId) {
            throw new BadRequestException();
        }

        var newName = playlist.getName();
        playlistDAO.editPlaylistName(token, playlistId, newName);

    }

    @Override
    public void removeTrackFromPlaylist(String token, int playlistId, int trackId) {
        playlistDAO.removeTrackFromPlaylist(token, playlistId, trackId);
    }

    @Override
    public void addTrackToPlaylist(String token, int playlistId, TrackDTO track) {
        var trackId = track.getId();
        playlistDAO.addTrackToPlaylist(token, playlistId, trackId);
    }
}
