package han.oose.dea.spotitube.datasource.mappers.abstraction;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TrackMapper {

    List<TrackDTO> toTrackDTOList(ResultSet resultset) throws SQLException;
}
