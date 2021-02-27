package co.edu.itp.ciecyt.service.mapper;


import co.edu.itp.ciecyt.domain.*;
import co.edu.itp.ciecyt.service.dto.PreguntaAuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PreguntaAuthority} and its DTO {@link PreguntaAuthorityDTO}.
 */
@Mapper(componentModel = "spring", uses = {PreguntaMapper.class, AuthorityMapper.class})
public interface PreguntaAuthorityMapper extends EntityMapper<PreguntaAuthorityDTO, PreguntaAuthority> {

    @Mapping(source = "pregunta3.id", target = "pregunta3Id")
    @Mapping(source = "pregunta3.pregunta", target = "pregunta3Pregunta")
    @Mapping(source = "authority3.id", target = "authority3Id")
    @Mapping(source = "authority3.name", target = "authority3Name")
    PreguntaAuthorityDTO toDto(PreguntaAuthority preguntaAuthority);

    @Mapping(source = "pregunta3Id", target = "pregunta3")
    @Mapping(source = "authority3Id", target = "authority3")
    PreguntaAuthority toEntity(PreguntaAuthorityDTO preguntaAuthorityDTO);

    default PreguntaAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        PreguntaAuthority preguntaAuthority = new PreguntaAuthority();
        preguntaAuthority.setId(id);
        return preguntaAuthority;
    }
}
