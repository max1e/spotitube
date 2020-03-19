package han.oose.dea.spotitube.datasource.assembler.abstraction;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TrackAssembler {

    List<TrackDTO> toTrackDTOList(ResultSet resultset) throws SQLException;
}
