package han.oose.dea.spotitube.datasource.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DTOMapper<DTO> {
    /**
     * Maps a resultset to a DTO
     *
     * @param resultset The resultset to be mapped
     * @return A DTO depending on generic
     * @throws SQLException The resultset could not be read
     */
    DTO toDTO(ResultSet resultset) throws SQLException;
}
