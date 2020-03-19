package han.oose.dea.spotitube.datasource.assembler.implementation;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistAssemblerImpl implements han.oose.dea.spotitube.datasource.assembler.abstraction.PlaylistAssembler {

    @Override
    public List<PlaylistDTO> toPlaylistDTOList(ResultSet resultset) throws SQLException {
        var playlists = new ArrayList<PlaylistDTO>();

        while (resultset.next()) {
            playlists.add(new PlaylistDTO(
                    resultset.getInt("playlistId"),
                    resultset.getString("playlistName"),
                    resultset.getBoolean("isOwner"),
                    null)
            );
        }

        return playlists;
    }

    @Override
    public List<PlaylistDTO> toPlaylistDTOWithDurationList(ResultSet resultset) throws SQLException {
        var playlists = new ArrayList<PlaylistDTO>();

        while (resultset.next()) {
            playlists.add(new PlaylistDTO(
                    resultset.getInt("playlistId"),
                    resultset.getString("playlistName"),
                    resultset.getBoolean("isOwner"),
                    null,
                    resultset.getInt("duration"))
            );
        }

        return playlists;
    }
}