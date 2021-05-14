package co.edu.itp.ciecyt.service.mapper;

import co.edu.itp.ciecyt.domain.*;
import co.edu.itp.ciecyt.service.dto.AdjuntoRetroalimentacionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjuntoRetroalimentacion} and its DTO {@link AdjuntoRetroalimentacionDTO}.
 */
@Mapper(componentModel = "spring", uses = { RetroalimentacionMapper.class, ProyectoMapper.class, FasesMapper.class })
public interface AdjuntoRetroalimentacionMapper extends EntityMapper<AdjuntoRetroalimentacionDTO, AdjuntoRetroalimentacion> {
    @Mapping(source = "adjuntoRetroalimentacionRetroalimentacion.id", target = "adjuntoRetroalimentacionRetroalimentacionId")
    @Mapping(source = "adjuntoRetroalimentacionRetroalimentacion.titulo", target = "adjuntoRetroalimentacionRetroalimentacionTitulo")
    @Mapping(source = "adjuntoRetroalimentacionProyecto.id", target = "adjuntoRetroalimentacionProyectoId")
    @Mapping(source = "adjuntoRetroalimentacionProyecto.titulo", target = "adjuntoRetroalimentacionProyectoTitulo")
    @Mapping(source = "adjuntoRetroalimentacionFase.id", target = "adjuntoRetroalimentacionFaseId")
    @Mapping(source = "adjuntoRetroalimentacionFase.fase", target = "adjuntoRetroalimentacionFaseFase")
    AdjuntoRetroalimentacionDTO toDto(AdjuntoRetroalimentacion adjuntoRetroalimentacion);

    @Mapping(source = "adjuntoRetroalimentacionRetroalimentacionId", target = "adjuntoRetroalimentacionRetroalimentacion")
    @Mapping(source = "adjuntoRetroalimentacionProyectoId", target = "adjuntoRetroalimentacionProyecto")
    @Mapping(source = "adjuntoRetroalimentacionFaseId", target = "adjuntoRetroalimentacionFase")
    AdjuntoRetroalimentacion toEntity(AdjuntoRetroalimentacionDTO adjuntoRetroalimentacionDTO);

    default AdjuntoRetroalimentacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjuntoRetroalimentacion adjuntoRetroalimentacion = new AdjuntoRetroalimentacion();
        adjuntoRetroalimentacion.setId(id);
        return adjuntoRetroalimentacion;
    }
}
