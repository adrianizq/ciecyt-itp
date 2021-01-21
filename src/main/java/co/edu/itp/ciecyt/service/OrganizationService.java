package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.repository.OrganizationRepository;
import co.edu.itp.ciecyt.service.dto.OrganizationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link com.kamila.dudoapp.domain.Organization}.
 */
public interface OrganizationService {
    /**
     * Save a organization.
     *
     * @param organizationDTO the entity to save.
     * @return the persisted entity.
     */
    OrganizationDTO save(OrganizationDTO organizationDTO);

    /**
     * Get all the organizations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrganizationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" organization.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrganizationDTO> findOne(Long id);

    /**
     * Get the "id" organization.
     *
     * @param slug the slug of the entity.
     * @return the entity.
     */
    Optional<OrganizationDTO> findOneBySlug(String slug);

    /**
     * Delete the "id" organization.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Ajunta un arhivo de imagen a la organizacion
     *
     * @param dto the organization dto
     * @param file the file multipart image upload
     *
     */
    public void attachFile(OrganizationDTO dto, MultipartFile file);

    /**
     * Ajunta un arhivo de imagen a la organizacion
     *
     * @param dto the organization dto
     * @param file the byte file image upload
     * @param contentType the contenttype file image
     *
     */
    public void attachFile(OrganizationDTO dto, byte[] file, String contentType);
}
