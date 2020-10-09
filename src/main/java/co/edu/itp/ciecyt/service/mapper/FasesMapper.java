package co.edu.itp.ciecyt.service.mapper;

import co.edu.itp.ciecyt.domain.*;
import co.edu.itp.ciecyt.service.dto.FasesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fases} and its DTO {@link FasesDTO}.
 */
@Mapper(componentModel = "spring", uses = {ModalidadMapper.class})
public interface FasesMapper extends EntityMapper<FasesDTO, Fases> {

    @Mapping(source = "fasesModalidad.id", target = "fasesModalidadId")
    @Mapping(source = "fasesModalidad.modalidad", target = "fasesModalidadModalidad")
    FasesDTO toDto(Fases fases);

    @Mapping(source = "fasesModalidadId", target = "fasesModalidad")
    Fases toEntity(FasesDTO fasesDTO);

    default Fases fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fases fases = new Fases();
        fases.setId(id);
        return fases;
    }
}
