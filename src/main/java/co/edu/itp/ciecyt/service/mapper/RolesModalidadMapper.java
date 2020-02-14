package co.edu.itp.ciecyt.service.mapper;

import co.edu.itp.ciecyt.domain.*;
import co.edu.itp.ciecyt.service.dto.RolesModalidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RolesModalidad} and its DTO {@link RolesModalidadDTO}.
 */
@Mapper(componentModel = "spring", uses = {ModalidadMapper.class})
public interface RolesModalidadMapper extends EntityMapper<RolesModalidadDTO, RolesModalidad> {

    @Mapping(source = "rolesModalidadModalidad.id", target = "rolesModalidadModalidadId")
    @Mapping(source = "rolesModalidadModalidad.modalidad", target = "rolesModalidadModalidadModalidad")
    RolesModalidadDTO toDto(RolesModalidad rolesModalidad);

    @Mapping(source = "rolesModalidadModalidadId", target = "rolesModalidadModalidad")
    RolesModalidad toEntity(RolesModalidadDTO rolesModalidadDTO);

    default RolesModalidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        RolesModalidad rolesModalidad = new RolesModalidad();
        rolesModalidad.setId(id);
        return rolesModalidad;
    }
}
