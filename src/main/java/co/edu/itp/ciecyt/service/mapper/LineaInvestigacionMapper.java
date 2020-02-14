package co.edu.itp.ciecyt.service.mapper;

import co.edu.itp.ciecyt.domain.*;
import co.edu.itp.ciecyt.service.dto.LineaInvestigacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LineaInvestigacion} and its DTO {@link LineaInvestigacionDTO}.
 */
@Mapper(componentModel = "spring", uses = {FacultadMapper.class})
public interface LineaInvestigacionMapper extends EntityMapper<LineaInvestigacionDTO, LineaInvestigacion> {

    @Mapping(source = "lineaPadre.id", target = "lineaPadreId")
    @Mapping(source = "lineaPadre.linea", target = "lineaPadreLinea")
    @Mapping(source = "lineaInvestigacionFacultad.id", target = "lineaInvestigacionFacultadId")
    @Mapping(source = "lineaInvestigacionFacultad.facultad", target = "lineaInvestigacionFacultadFacultad")
    LineaInvestigacionDTO toDto(LineaInvestigacion lineaInvestigacion);

    @Mapping(source = "lineaPadreId", target = "lineaPadre")
    @Mapping(source = "lineaInvestigacionFacultadId", target = "lineaInvestigacionFacultad")
    LineaInvestigacion toEntity(LineaInvestigacionDTO lineaInvestigacionDTO);

    default LineaInvestigacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        LineaInvestigacion lineaInvestigacion = new LineaInvestigacion();
        lineaInvestigacion.setId(id);
        return lineaInvestigacion;
    }
}
