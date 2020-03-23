package han.oose.dea.spotitube.datasource.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DTOMapper<DTO> {
    DTO toDTO(ResultSet resultset) throws SQLException;
}
