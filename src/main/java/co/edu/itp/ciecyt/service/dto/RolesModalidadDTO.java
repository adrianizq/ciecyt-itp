package co.edu.itp.ciecyt.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.RolesModalidad} entity.
 */
public class RolesModalidadDTO implements Serializable {

    private Long id;

    private String rol;

    private Integer cantidad;

    private Boolean calificador;


    private Long rolesModalidadModalidadId;

    private String rolesModalidadModalidadModalidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean isCalificador() {
        return calificador;
    }

    public void setCalificador(Boolean calificador) {
        this.calificador = calificador;
    }

    public Long getRolesModalidadModalidadId() {
        return rolesModalidadModalidadId;
    }

    public void setRolesModalidadModalidadId(Long modalidadId) {
        this.rolesModalidadModalidadId = modalidadId;
    }

    public String getRolesModalidadModalidadModalidad() {
        return rolesModalidadModalidadModalidad;
    }

    public void setRolesModalidadModalidadModalidad(String modalidadModalidad) {
        this.rolesModalidadModalidadModalidad = modalidadModalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RolesModalidadDTO rolesModalidadDTO = (RolesModalidadDTO) o;
        if (rolesModalidadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rolesModalidadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RolesModalidadDTO{" +
            "id=" + getId() +
            ", rol='" + getRol() + "'" +
            ", cantidad=" + getCantidad() +
            ", calificador='" + isCalificador() + "'" +
            ", rolesModalidadModalidad=" + getRolesModalidadModalidadId() +
            ", rolesModalidadModalidad='" + getRolesModalidadModalidadModalidad() + "'" +
            "}";
    }
}
