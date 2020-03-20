package han.oose.dea.spotitube.datasource.mappers.implementation;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.datasource.mappers.abstraction.PlaylistMapper;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistMapperImpl implements PlaylistMapper {

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