package co.edu.itp.ciecyt.service.mapper;


import co.edu.itp.ciecyt.domain.*;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Proyecto} and its DTO {@link ProyectoDTO}.
 */
@Mapper(componentModel = "spring", uses = {LineaInvestigacionMapper.class, GrupoSemilleroMapper.class, ModalidadMapper.class, FacultadMapper.class})
public interface ProyectoMapper extends EntityMapper<ProyectoDTO, Proyecto> {

    @Mapping(source = "proyectoLineaInvestigacion.id", target = "proyectoLineaInvestigacionId")
    @Mapping(source = "proyectoLineaInvestigacion.linea", target = "proyectoLineaInvestigacionLinea")
    @Mapping(source = "proyectoGrupoSemillero.id", target = "proyectoGrupoSemilleroId")
    @Mapping(source = "proyectoGrupoSemillero.nombre", target = "proyectoGrupoSemilleroNombre")
    @Mapping(source = "proyectoModalidad.id", target = "proyectoModalidadId")
    @Mapping(source = "proyectoModalidad.modalidad", target = "proyectoModalidadModalidad")
    @Mapping(source = "facultad.id", target = "facultadId")
    @Mapping(source = "subLineaLineaInvestigacion.id", target = "subLineaLineaInvestigacionId")
    @Mapping(source = "subLineaLineaInvestigacion.linea", target = "subLineaLineaInvestigacionLinea")
    ProyectoDTO toDto(Proyecto proyecto);

    @Mapping(source = "proyectoLineaInvestigacionId", target = "proyectoLineaInvestigacion")
    @Mapping(source = "proyectoGrupoSemilleroId", target = "proyectoGrupoSemillero")
    @Mapping(source = "proyectoModalidadId", target = "proyectoModalidad")
    @Mapping(source = "facultadId", target = "facultad")
    @Mapping(source = "subLineaLineaInvestigacionId", target = "subLineaLineaInvestigacion")
    Proyecto toEntity(ProyectoDTO proyectoDTO);

    default Proyecto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proyecto proyecto = new Proyecto();
        proyecto.setId(id);
        return proyecto;
    }
}
