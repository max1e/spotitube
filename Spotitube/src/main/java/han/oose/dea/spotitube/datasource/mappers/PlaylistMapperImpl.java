package han.oose.dea.spotitube.datasource.mappers;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistMapperImpl implements DTOMapper<List<PlaylistDTO>> {

    @Override
    public List<PlaylistDTO> toDTO(ResultSet resultset) throws SQLException {
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