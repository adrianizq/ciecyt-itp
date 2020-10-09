package co.edu.itp.ciecyt.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.Fases} entity.
 */
public class FasesDTO implements Serializable {

    private Long id;

    private String fase;

    private Boolean notificable;


    private Long fasesModalidadId;

    private String fasesModalidadModalidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Boolean isNotificable() {
        return notificable;
    }

    public void setNotificable(Boolean notificable) {
        this.notificable = notificable;
    }

    public Long getFasesModalidadId() {
        return fasesModalidadId;
    }

    public void setFasesModalidadId(Long modalidadId) {
        this.fasesModalidadId = modalidadId;
    }

    public String getFasesModalidadModalidad() {
        return fasesModalidadModalidad;
    }

    public void setFasesModalidadModalidad(String modalidadModalidad) {
        this.fasesModalidadModalidad = modalidadModalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FasesDTO fasesDTO = (FasesDTO) o;
        if (fasesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fasesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FasesDTO{" +
            "id=" + getId() +
            ", fase='" + getFase() + "'" +
            ", notificable='" + isNotificable() + "'" +
            ", fasesModalidad=" + getFasesModalidadId() +
            ", fasesModalidad='" + getFasesModalidadModalidad() + "'" +
            "}";
    }
}
