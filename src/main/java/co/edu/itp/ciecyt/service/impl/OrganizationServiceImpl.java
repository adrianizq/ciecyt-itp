package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.config.ApplicationProperties;
import co.edu.itp.ciecyt.domain.Organization;
import co.edu.itp.ciecyt.repository.OrganizationRepository;
import co.edu.itp.ciecyt.service.OrganizationService;
import co.edu.itp.ciecyt.service.dto.OrganizationDTO;
import co.edu.itp.ciecyt.service.mapper.OrganizationMapper;
import co.edu.itp.ciecyt.service.util.FileUtils;
import co.edu.itp.ciecyt.service.util.MimeTypes;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

//import liquibase.util.file.FilenameUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing {@link Organization}.
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper;

    private final ApplicationProperties appProperties;

    public OrganizationServiceImpl(
        OrganizationRepository organizationRepository,
        OrganizationMapper organizationMapper,
        ApplicationProperties appProperties
    ) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.appProperties = appProperties;
    }

    /**
     * Save a organization.
     *
     * @param organizationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrganizationDTO save(OrganizationDTO organizationDTO) {
        log.debug("Request to save Organization : {}", organizationDTO);
        Organization organization = organizationMapper.toEntity(organizationDTO);
        if (organization.getId() == null) {
            //organization.set
        }
        organization = organizationRepository.save(organization);

        OrganizationDTO dto = organizationMapper.toDto(organization);
        if (dto.getLogoImage() != null) {
            String urlImage = FileUtils.buildURLImage(
                appProperties.getImagePath(),
                dto.getImageUrl(),
                appProperties.getUpload().getOrganization()
            );
            dto.setImageUrl(urlImage);
        }
        return dto;
    }

    /**
     * Ajunta un arhivo de imagen a la organizacion
     *
     * @param dto the organization dto
     * @param file the file multipart image upload
     *
     */
    @Override
    public void attachFile(OrganizationDTO dto, MultipartFile file) {
        Organization entity = organizationMapper.toEntity(dto);
        //Guarda el archivo en el directorio asignado
        //File rootDir = new File("/home/jltovarg/test/dudo");
        try {
            log.debug(
                "Root upload dir: {}, orgDir: {}",
                appProperties.getUpload().getRoot().getDir(),
                appProperties.getUpload().getOrganization().getDir()
            );
            Path rootDir = Paths.get(appProperties.getUpload().getRoot().getDir());
            Path announcementDir = rootDir.resolve(appProperties.getUpload().getOrganization().getDir());
            log.debug("Dir upload: {}, file: {}", announcementDir, file.getOriginalFilename());
            String nameFile = FileUtils.buildFileName(entity.getId(), FilenameUtils.getExtension(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), announcementDir.resolve(nameFile));
            entity.setLogo(nameFile);
            organizationRepository.save(entity);
        } catch (Exception e) {
            log.error("Error attah file", e);
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    /**
     * Ajunta un arhivo de imagen a la organizacion
     *
     * @param dto the organization dto
     * @param file the byte file image upload
     * @param contentType the contenttype file image
     *
     */
    @Override
    public void attachFile(OrganizationDTO dto, byte[] file, String contentType) {
        Organization entity = organizationMapper.toEntity(dto);
        //Guarda el archivo en el directorio asignado
        //File rootDir = new File("/home/jltovarg/test/dudo");
        try {
            Path rootDir = Paths.get(appProperties.getUpload().getRoot().getDir());
            Path orgDir = rootDir.resolve(appProperties.getUpload().getOrganization().getDir());
            log.debug("Dir upload: {}, , orgDir: {}, mimetype: {}", rootDir, orgDir, contentType);
            String ext = MimeTypes.getDefaultExt(contentType);
            String nameFile = FileUtils.buildFileName(entity.getId(), ext);

            //FileUtils.writeByteArrayToFile(announcementDir.resolve(nameFile), file );
            Files.write(orgDir.resolve(nameFile), file, StandardOpenOption.CREATE);

            entity.setLogo(nameFile);
            organizationRepository.save(entity);
        } catch (Exception e) {
            log.error("Error attah file", e);
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    /**
     * Get all the organizations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrganizationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Organizations");
        return organizationRepository
            .findAll(pageable)
            .map(organizationMapper::toDto)
            .map(
                dto -> {
                    if (dto.getLogo() != null) {
                        String urlImage = FileUtils.buildURLImage(
                            appProperties.getImagePath(),
                            dto.getLogo(),
                            appProperties.getUpload().getOrganization()
                        );
                        dto.setImageUrl(urlImage);
                    }
                    return dto;
                }
            );
    }

    /**
     * Get one organization by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationDTO> findOne(Long id) {
        log.debug("Request to get Organization : {}", id);
        return organizationRepository
            .findById(id)
            .map(organizationMapper::toDto)
            .map(
                dto -> {
                    if (dto.getLogo() != null) {
                        String urlImage = FileUtils.buildURLImage(
                            appProperties.getImagePath(),
                            dto.getLogo(),
                            appProperties.getUpload().getOrganization()
                        );
                        dto.setImageUrl(urlImage);
                    }
                    return dto;
                }
            );
    }

    /**
     * Get one organization by slug.
     *
     * @param slug the slug of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationDTO> findOneBySlug(String slug) {
        log.debug("Request to get Organization by Slug : {}", slug);
        return organizationRepository
            .findOneBySlug(slug)
            .map(organizationMapper::toDto)
            .map(
                dto -> {
                    if (dto.getLogo() != null) {
                        String urlImage = FileUtils.buildURLImage(
                            appProperties.getImagePath(),
                            dto.getLogo(),
                            appProperties.getUpload().getOrganization()
                        );
                        dto.setImageUrl(urlImage);
                    }
                    return dto;
                }
            );
    }

    /**
     * Delete the organization by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Organization : {}", id);
        organizationRepository.deleteById(id);
    }
}
