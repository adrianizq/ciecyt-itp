package co.edu.itp.ciecyt.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.Menu} entity.
 */
public class MenuDTO implements Serializable {

    private Long id;

    private String nombre;

    private String url;

    private String icono;

    private Boolean activo;


    private Long menuPadreId;

    private String menuPadreNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getMenuPadreId() {
        return menuPadreId;
    }

    public void setMenuPadreId(Long menuId) {
        this.menuPadreId = menuId;
    }

    public String getMenuPadreNombre() {
        return menuPadreNombre;
    }

    public void setMenuPadreNombre(String menuNombre) {
        this.menuPadreNombre = menuNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuDTO menuDTO = (MenuDTO) o;
        if (menuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", url='" + getUrl() + "'" +
            ", icono='" + getIcono() + "'" +
            ", activo='" + isActivo() + "'" +
            ", menuPadre=" + getMenuPadreId() +
            ", menuPadre='" + getMenuPadreNombre() + "'" +
            "}";
    }
}
