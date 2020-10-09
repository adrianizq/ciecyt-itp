package co.edu.itp.ciecyt.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.LineaInvestigacion} entity.
 */
public class LineaInvestigacionDTO implements Serializable {

    private Long id;

    private String linea;

    private String codigoLinea;


    private Long lineaPadreId;

    private String lineaPadreLinea;

    private Long lineaInvestigacionFacultadId;

    private String lineaInvestigacionFacultadFacultad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getCodigoLinea() {
        return codigoLinea;
    }

    public void setCodigoLinea(String codigoLinea) {
        this.codigoLinea = codigoLinea;
    }

    public Long getLineaPadreId() {
        return lineaPadreId;
    }

    public void setLineaPadreId(Long lineaInvestigacionId) {
        this.lineaPadreId = lineaInvestigacionId;
    }

    public String getLineaPadreLinea() {
        return lineaPadreLinea;
    }

    public void setLineaPadreLinea(String lineaInvestigacionLinea) {
        this.lineaPadreLinea = lineaInvestigacionLinea;
    }

    public Long getLineaInvestigacionFacultadId() {
        return lineaInvestigacionFacultadId;
    }

    public void setLineaInvestigacionFacultadId(Long facultadId) {
        this.lineaInvestigacionFacultadId = facultadId;
    }

    public String getLineaInvestigacionFacultadFacultad() {
        return lineaInvestigacionFacultadFacultad;
    }

    public void setLineaInvestigacionFacultadFacultad(String facultadFacultad) {
        this.lineaInvestigacionFacultadFacultad = facultadFacultad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LineaInvestigacionDTO lineaInvestigacionDTO = (LineaInvestigacionDTO) o;
        if (lineaInvestigacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lineaInvestigacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LineaInvestigacionDTO{" +
            "id=" + getId() +
            ", linea='" + getLinea() + "'" +
            ", codigoLinea='" + getCodigoLinea() + "'" +
            ", lineaPadre=" + getLineaPadreId() +
            ", lineaPadre='" + getLineaPadreLinea() + "'" +
            ", lineaInvestigacionFacultad=" + getLineaInvestigacionFacultadId() +
            ", lineaInvestigacionFacultad='" + getLineaInvestigacionFacultadFacultad() + "'" +
            "}";
    }
}
