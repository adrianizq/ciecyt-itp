package co.edu.itp.ciecyt.service.mapper;


import co.edu.itp.ciecyt.domain.*;
import co.edu.itp.ciecyt.service.dto.AdjuntoProyectoFaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjuntoProyectoFase} and its DTO {@link AdjuntoProyectoFaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProyectoFaseMapper.class})
public interface AdjuntoProyectoFaseMapper extends EntityMapper<AdjuntoProyectoFaseDTO, AdjuntoProyectoFase> {

    @Mapping(source = "adjuntoProyectoFaseProyectoFase.id", target = "adjuntoProyectoFaseProyectoFaseId")
    @Mapping(source = "adjuntoProyectoFaseProyectoFase.titulo", target = "adjuntoProyectoFaseProyectoFaseTitulo")
    AdjuntoProyectoFaseDTO toDto(AdjuntoProyectoFase adjuntoProyectoFase);

    @Mapping(source = "adjuntoProyectoFaseProyectoFaseId", target = "adjuntoProyectoFaseProyectoFase")
    AdjuntoProyectoFase toEntity(AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO);

    default AdjuntoProyectoFase fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjuntoProyectoFase adjuntoProyectoFase = new AdjuntoProyectoFase();
        adjuntoProyectoFase.setId(id);
        return adjuntoProyectoFase;
    }
}
