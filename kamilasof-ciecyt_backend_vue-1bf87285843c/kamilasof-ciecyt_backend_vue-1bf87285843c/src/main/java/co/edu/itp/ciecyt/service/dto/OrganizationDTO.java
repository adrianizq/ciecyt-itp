package co.edu.itp.ciecyt.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.Organization} entity.
 */
public class OrganizationDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 256)
    private String slug;

    private String logo;

    @NotNull
    private Boolean activated;

    private byte[] logoImage;

    private String logoImageContentType;

    private String imageUrl;

    public Boolean getActivated() {
        return this.activated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public byte[] getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(byte[] logoImage) {
        this.logoImage = logoImage;
    }

    public String getLogoImageContentType() {
        return logoImageContentType;
    }

    public void setLogoImageContentType(String logoImageContentType) {
        this.logoImageContentType = logoImageContentType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganizationDTO)) {
            return false;
        }

        return id != null && id.equals(((OrganizationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganizationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", slug='" + getSlug() + "'" +
            ", logo='" + getLogo() + "'" +
            ", activated='" + isActivated() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", logoImageContentType='" + getLogoImageContentType() + "'" +
            "}";
    }
}
