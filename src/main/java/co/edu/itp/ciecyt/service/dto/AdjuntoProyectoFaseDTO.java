package co.edu.itp.ciecyt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Lob;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.AdjuntoProyectoFase} entity.
 */
public class AdjuntoProyectoFaseDTO implements Serializable {

    private Long id;

    private String nombreAdjunto;

    private LocalDate fechaCreacion;

    private LocalDate fechaModificacion;

    private Integer estadoAdjunto;

    private String adjuntoProyectoFase;

    private String nombreArchivoOriginal;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Lob
    private byte[] archivo;

    private String archivoContentType;

    private Long adjuntoProyectoFaseProyectoFaseId;

    private String adjuntoProyectoFaseProyectoFaseTitulo;

    private Long proyectoFaseProyectoId;

    private String proyectoFaseProyectoTitulo;
    
    private String file;
    
    private String urlFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getEstadoAdjunto() {
        return estadoAdjunto;
    }

    public void setEstadoAdjunto(Integer estadoAdjunto) {
        this.estadoAdjunto = estadoAdjunto;
    }

    public String getAdjuntoProyectoFase() {
        return adjuntoProyectoFase;
    }

    public void setAdjuntoProyectoFase(String adjuntoProyectoFase) {
        this.adjuntoProyectoFase = adjuntoProyectoFase;
    }

    public String getNombreArchivoOriginal() {
        return nombreArchivoOriginal;
    }

    public void setNombreArchivoOriginal(String nombreArchivoOriginal) {
        this.nombreArchivoOriginal = nombreArchivoOriginal;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getArchivoContentType() {
        return archivoContentType;
    }

    public void setArchivoContentType(String archivoContentType) {
        this.archivoContentType = archivoContentType;
    }

    public Long getAdjuntoProyectoFaseProyectoFaseId() {
        return adjuntoProyectoFaseProyectoFaseId;
    }

    public void setAdjuntoProyectoFaseProyectoFaseId(Long proyectoFaseId) {
        this.adjuntoProyectoFaseProyectoFaseId = proyectoFaseId;
    }

    public String getAdjuntoProyectoFaseProyectoFaseTitulo() {
        return adjuntoProyectoFaseProyectoFaseTitulo;
    }

    public void setAdjuntoProyectoFaseProyectoFaseTitulo(String proyectoFaseTitulo) {
        this.adjuntoProyectoFaseProyectoFaseTitulo = proyectoFaseTitulo;
    }

    public Long getProyectoFaseProyectoId() {
        return proyectoFaseProyectoId;
    }

    public void setProyectoFaseProyectoId(Long proyectoId) {
        this.proyectoFaseProyectoId = proyectoId;
    }

    public String getProyectoFaseProyectoTitulo() {
        return proyectoFaseProyectoTitulo;
    }

    public void setProyectoFaseProyectoTitulo(String proyectoTitulo) {
        this.proyectoFaseProyectoTitulo = proyectoTitulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjuntoProyectoFaseDTO)) {
            return false;
        }

        return id != null && id.equals(((AdjuntoProyectoFaseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjuntoProyectoFaseDTO{" +
            "id=" + getId() +
            ", nombreAdjunto='" + getNombreAdjunto() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaModificacion='" + getFechaModificacion() + "'" +
            ", estadoAdjunto=" + getEstadoAdjunto() +
            ", adjuntoProyectoFase='" + getAdjuntoProyectoFase() + "'" +
            ", nombreArchivoOriginal='" + getNombreArchivoOriginal() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", archivo='" + getArchivo() + "'" +
            ", archivoContentType='" + getArchivoContentType() + "'" +
            ", adjuntoProyectoFaseProyectoFaseId=" + getAdjuntoProyectoFaseProyectoFaseId() +
            ", adjuntoProyectoFaseProyectoFaseTitulo='" + getAdjuntoProyectoFaseProyectoFaseTitulo() + "'" +
            ", proyectoFaseProyectoId=" + getProyectoFaseProyectoId() +
            ", proyectoFaseProyectoTitulo='" + getProyectoFaseProyectoTitulo() + "'" +
            "}";
    }

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getUrlFile() {
		return urlFile;
	}

	public void setUrlFile(String ulrFile) {
		this.urlFile = ulrFile;
	}
}
