package han.oose.dea.spotitube.datasource.assembler.abstraction;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PlaylistAssembler {

    List<PlaylistDTO> toPlaylistDTOList(ResultSet resultset) throws SQLException;
}
