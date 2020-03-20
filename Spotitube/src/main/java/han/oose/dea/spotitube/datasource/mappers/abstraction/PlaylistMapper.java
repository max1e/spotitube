package han.oose.dea.spotitube.datasource.mappers.abstraction;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PlaylistMapper {

    List<PlaylistDTO> toPlaylistDTOList(ResultSet resultset) throws SQLException;
    List<PlaylistDTO> toPlaylistDTOWithDurationList(ResultSet resultset) throws SQLException;
}