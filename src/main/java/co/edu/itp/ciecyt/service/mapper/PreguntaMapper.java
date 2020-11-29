package co.edu.itp.ciecyt.service.mapper;


import co.edu.itp.ciecyt.domain.*;
import co.edu.itp.ciecyt.service.dto.PreguntaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pregunta} and its DTO {@link PreguntaDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoPreguntaMapper.class, ModalidadMapper.class, RolesModalidadMapper.class, ElementoMapper.class})
public interface PreguntaMapper extends EntityMapper<PreguntaDTO, Pregunta> {

    @Mapping(source = "preguntaTipoPregunta.id", target = "preguntaTipoPreguntaId")
    @Mapping(source = "preguntaTipoPregunta.tipoPregunta", target = "preguntaTipoPreguntaTipoPregunta")
    @Mapping(source = "preguntaModalidad.id", target = "preguntaModalidadId")
    @Mapping(source = "preguntaModalidad.modalidad", target = "preguntaModalidadModalidad")
    @Mapping(source = "preguntaRolesModalidad.id", target = "preguntaRolesModalidadId")
    @Mapping(source = "preguntaRolesModalidad.rol", target = "preguntaRolesModalidadRol")
    @Mapping(source = "preguntaElemento.id", target = "preguntaElementoId")
    @Mapping(source = "preguntaElemento.elemento", target = "preguntaElementoElemento")
    PreguntaDTO toDto(Pregunta pregunta);

    @Mapping(source = "preguntaTipoPreguntaId", target = "preguntaTipoPregunta")
    @Mapping(source = "preguntaModalidadId", target = "preguntaModalidad")
    @Mapping(source = "preguntaRolesModalidadId", target = "preguntaRolesModalidad")
    @Mapping(source = "preguntaElementoId", target = "preguntaElemento")
    Pregunta toEntity(PreguntaDTO preguntaDTO);

    default Pregunta fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pregunta pregunta = new Pregunta();
        pregunta.setId(id);
        return pregunta;
    }
}
