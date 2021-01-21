package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.config.Constants;
import co.edu.itp.ciecyt.service.OrganizationQueryService;
import co.edu.itp.ciecyt.service.OrganizationService;
import co.edu.itp.ciecyt.service.dto.OrganizationCriteria;
import co.edu.itp.ciecyt.service.dto.OrganizationDTO;
import co.edu.itp.ciecyt.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link co.edu.itp.ciecyt.domain.Organization}.
 */
@RestController
@RequestMapping("/api")
@Api(description = "Registro de empresas a la Plataforma", value = "Empresas", tags = { "12. Api Empresas u Organizaciones" })
public class OrganizationResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationResource.class);

    private static final String ENTITY_NAME = "organization";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganizationService organizationService;

    private final OrganizationQueryService organizationQueryService;

    public OrganizationResource(OrganizationService organizationService, OrganizationQueryService organizationQueryService) {
        this.organizationService = organizationService;
        this.organizationQueryService = organizationQueryService;
    }

    /**
     * {@code POST  /organizations} : Create a new organization.
     *
     * @param organizationDTO the organizationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organizationDTO, or with status {@code 400 (Bad Request)} if the organization has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organizations")
    @ApiOperation(value = "Registrar una empresa en la plataforma", notes = "")
    @ApiResponses(
        {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "REGISTRO PROCESADO CON EXITO"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "BAD REQUEST: INFORMACION INVALIDA"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED: TOKEN INVALIDO"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN: RECURSO RESTRINGIDO"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "NOT FOUND: USUARIO NO ENCONTRADO"),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "METHOD NOT ALLOWED: METODO NO PERMITIDO"),
        }
    )
    public ResponseEntity<OrganizationDTO> createOrganization(@Valid @RequestBody OrganizationDTO organizationDTO)
        throws URISyntaxException {
        //log.debug("REST request to save Organization : {}, file: {}", organizationDTO, file);
        log.debug("REST request to save Organization : {}", organizationDTO);
        if (organizationDTO.getId() != null) {
            throw new BadRequestAlertException("A new organization cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganizationDTO result = organizationService.save(organizationDTO);

        byte[] file = organizationDTO.getLogoImage();
        if (file != null) {
            organizationService.attachFile(result, file, organizationDTO.getLogoImageContentType());
        }

        return ResponseEntity
            .created(new URI("/api/organizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /organizations} : Create a new organization.
     *
     * @param organizationDTO the organizationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organizationDTO, or with status {@code 400 (Bad Request)} if the organization has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    //@PostMapping("/organizations")
    @RequestMapping(
        value = "/organizations/createUpload",
        method = RequestMethod.POST,
        consumes = "multipart/mixed",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @ApiOperation(value = "Registrar una empresa en la plataforma", notes = "")
    @ApiResponses(
        {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "REGISTRO PROCESADO CON EXITO"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "BAD REQUEST: INFORMACION INVALIDA"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED: TOKEN INVALIDO"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN: RECURSO RESTRINGIDO"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "NOT FOUND: USUARIO NO ENCONTRADO"),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "METHOD NOT ALLOWED: METODO NO PERMITIDO"),
        }
    )
    public ResponseEntity<OrganizationDTO> createOrganization(
        @Valid @RequestBody OrganizationDTO organizationDTO,
        @RequestParam("file") MultipartFile file
    )
        throws URISyntaxException {
        log.debug("REST request to save Organization : {}, file: {}", organizationDTO, file);

        if (organizationDTO.getId() != null) {
            throw new BadRequestAlertException("A new organization cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganizationDTO result = organizationService.save(organizationDTO);

        //Procede a guardar el archivo en el directorio asignado
        if (file != null) {
            organizationService.attachFile(result, file);
        }

        return ResponseEntity
            .created(new URI("/api/organizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organizations} : Upload image Organization and Updates an existing organization.
     *
     * @param id the organizationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organizationDTO,
     * or with status {@code 400 (Bad Request)} if the organizationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organizationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    //@PutMapping("/organizations/image")
    @RequestMapping(
        value = "/organizations/image/{id}",
        method = RequestMethod.PUT,
        consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @ApiOperation(value = "Carga la imagen de una organizacion", notes = "")
    @ApiResponses(
        {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "REGISTRO PROCESADO CON EXITO"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "BAD REQUEST: INFORMACION INVALIDA"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED: TOKEN INVALIDO"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN: RECURSO RESTRINGIDO"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "NOT FOUND: USUARIO NO ENCONTRADO"),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "METHOD NOT ALLOWED: METODO NO PERMITIDO"),
        }
    )
    public ResponseEntity<OrganizationDTO> putImageOrganization(@PathVariable Long id, @RequestParam("file") MultipartFile file)
        throws URISyntaxException {
        log.debug("REST request to get Organization : {}", id);
        Optional<OrganizationDTO> organizationDTO = organizationService.findOne(id);

        //Procede a guardar el archivo en el directorio asignado
        if (file != null) {
            organizationService.attachFile(organizationDTO.get(), file);
        }

        OrganizationDTO result = organizationService.save(organizationDTO.get());
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organizations} : Updates an existing organization.
     *
     * @param organizationDTO the organizationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organizationDTO,
     * or with status {@code 400 (Bad Request)} if the organizationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organizationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organizations")
    @ApiOperation(value = "Actualizar una empresa en la plataforma", notes = "")
    @ApiResponses(
        {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "REGISTRO PROCESADO CON EXITO"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "BAD REQUEST: INFORMACION INVALIDA"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED: TOKEN INVALIDO"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN: RECURSO RESTRINGIDO"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "NOT FOUND: USUARIO NO ENCONTRADO"),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "METHOD NOT ALLOWED: METODO NO PERMITIDO"),
        }
    )
    public ResponseEntity<OrganizationDTO> updateOrganization(@Valid @RequestBody OrganizationDTO organizationDTO)
        throws URISyntaxException {
        log.debug("REST request to update Organization : {}", organizationDTO);
        if (organizationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganizationDTO result = organizationService.save(organizationDTO);

        byte[] file = organizationDTO.getLogoImage();
        if (file != null) {
            organizationService.attachFile(result, file, organizationDTO.getLogoImageContentType());
        }

        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, organizationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organizations} : get all the organizations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organizations in body.
     */
    @GetMapping("/organizations")
    @ApiOperation(value = "Consultar la lista de empresas por criterios", notes = "")
    @ApiResponses(
        {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "LISTA DE REGISTROS"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "BAD REQUEST: INFORMACION INVALIDA"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED: TOKEN INVALIDO"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN: RECURSO RESTRINGIDO"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "NOT FOUND: USUARIO NO ENCONTRADO"),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "METHOD NOT ALLOWED: METODO NO PERMITIDO"),
        }
    )
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations(OrganizationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Organizations by criteria: {}", criteria);
        Page<OrganizationDTO> page = organizationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /organizations/count} : count all the organizations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/organizations/count")
    @ApiOperation(
        value = "Contar número de empresas en la plataforma",
        notes = "Retorna el número de empresas en la paltaforma dado un criterio de búsqueda"
    )
    @ApiResponses(
        {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "REGISTRO PROCESADO CON EXITO"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "BAD REQUEST: INFORMACION INVALIDA"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED: TOKEN INVALIDO"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN: RECURSO RESTRINGIDO"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "NOT FOUND: USUARIO NO ENCONTRADO"),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "METHOD NOT ALLOWED: METODO NO PERMITIDO"),
        }
    )
    public ResponseEntity<Long> countOrganizations(OrganizationCriteria criteria) {
        log.debug("REST request to count Organizations by criteria: {}", criteria);
        return ResponseEntity.ok().body(organizationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /organizations/:id} : get the "id" organization.
     *
     * @param id the id of the organizationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organizationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organizations/{id}")
    @ApiOperation(value = "Consultar Empresa por Id", notes = "")
    @ApiResponses(
        {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "REGISTRO CONSULTADO CON EXITO"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "BAD REQUEST: INFORMACION INVALIDA"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED: TOKEN INVALIDO"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN: RECURSO RESTRINGIDO"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "NOT FOUND: USUARIO NO ENCONTRADO"),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "METHOD NOT ALLOWED: METODO NO PERMITIDO"),
        }
    )
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Long id) {
        log.debug("REST request to get Organization : {}", id);
        Optional<OrganizationDTO> organizationDTO = organizationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizationDTO);
    }

    /**
     * {@code GET  /organizations/info/:id} : get the "slug" organization.
     *
     * @param id the id of the organizationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organizationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping({ "/organizations/info", "/organizations/info/{slug}" })
    @ApiOperation(value = "Consultar Empresa por Slug", notes = "")
    @ApiResponses(
        {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "REGISTRO CONSULTADO CON EXITO"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "BAD REQUEST: INFORMACION INVALIDA"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED: TOKEN INVALIDO"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN: RECURSO RESTRINGIDO"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "NOT FOUND: USUARIO NO ENCONTRADO"),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "METHOD NOT ALLOWED: METODO NO PERMITIDO"),
        }
    )
    public ResponseEntity<OrganizationDTO> getOrganizationBySlug(@PathVariable Optional<String> slug) {
        log.debug("REST request to get Organization by slug : {}", slug);
        Optional<OrganizationDTO> organizationDTO = null;
        if (slug.isPresent()) {
            organizationDTO = organizationService.findOneBySlug(slug.get());
        } else {
            organizationDTO = organizationService.findOne(Constants.DEFAULT_ORG_ID);
        }
        return ResponseUtil.wrapOrNotFound(organizationDTO);
    }

    /**
     * {@code DELETE  /organizations/:id} : delete the "id" organization.
     *
     * @param id the id of the organizationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organizations/{id}")
    @ApiOperation(value = "Eliminar una Empresa por id", notes = "")
    @ApiResponses(
        {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "REGISTRO ELIMINADO CON EXITO"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "BAD REQUEST: INFORMACION INVALIDA"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED: TOKEN INVALIDO"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN: RECURSO RESTRINGIDO"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "NOT FOUND: USUARIO NO ENCONTRADO"),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "METHOD NOT ALLOWED: METODO NO PERMITIDO"),
        }
    )
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        log.debug("REST request to delete Organization : {}", id);
        organizationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
