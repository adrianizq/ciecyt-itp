
package  co.edu.itp.ciecyt.service.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Clase para representar un reporte.
 * Representa el registro de reportes del sistema REPORT
 * @author JLT
 * 13/05/2010
 *
 */
public class ReportInfo implements Serializable {
	
	/**********************************
	 * Atributos
	 *********************************/
	
	
	/**
	 * ID serializacion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * El codigo de la entidad 
	 */
	private String codeEntity;
	
	/**
	 * El codigo del reporte 
	 */
	private String code;
	
	/**
	 * Nombre fuente 
	 */
	private String nameReport;
	
	/**
	 * Nombre del reporte generado
	 */
	private String nameGenerateReport;
	
	/**
	 * Titulo principal 
	 */
	private String title;
	/**
	 * Nombre del arhivo generado
	 **/
	private String nameGenerated;
	
	/**
	 * Titulo secundario 
	 */
	private String subtitle;
	
	/**
	 * Descripcion del reporte 
	 */
	private String description;
	
	/**
	 * Documentacion 
	 */
	private String documentation;
	
	/**
	 * Codigo Modulo 
	 */
	private String module;
	
	/**
	 * Numero secuencia reporte 
	 */
	private Long secuence;
	
	/**
	 * La fecha del reporte
	 */
	private Date dateReport;
	
	/**
	 * JDBC Provider
	 */
	private String jdbcProvider;
	
	/**
	 * User Id
	 */
	private String userId;
	
	private String logo;
	
	private String bgImage;
	
	/**
	 * Identificador del reporte
	 * Corresponde al identificador del objeto al cual hace referencia
	 */
	private String identifier;
	
	/**
	 * Nombre de la empresa
	 */
	private String companyName;
	
	
	/**
	 * Lista de formatos del Reporte
	 */
	
	private List<FormatReport> formats;
	
	public ReportInfo() {
		formats = new ArrayList<FormatReport>();
	}

	/**
	 * @return the codeEntity
	 */
	public String getCodeEntity() {
		return codeEntity;
	}

	/**
	 * @param codeEntity the codeEntity to set
	 */
	public void setCodeEntity(String codeEntity) {
		this.codeEntity = codeEntity;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the nameReport
	 */
	public String getNameReport() {
		return nameReport;
	}

	/**
	 * @param nameReport the nameReport to set
	 */
	public void setNameReport(String nameReport) {
		this.nameReport = nameReport;
	}

	/**
	 * @return the nameGenerateReport
	 */
	public String getNameGenerateReport() {
		return nameGenerateReport;
	}

	/**
	 * @param nameGenerateReport the nameGenerateReport to set
	 */
	public void setNameGenerateReport(String nameGenerateReport) {
		this.nameGenerateReport = nameGenerateReport;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the documentation
	 */
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * @param documentation the documentation to set
	 */
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the secuence
	 */
	public Long getSecuence() {
		return secuence;
	}

	/**
	 * @param secuence the secuence to set
	 */
	public void setSecuence(Long secuence) {
		this.secuence = secuence;
	}

	/**
	 * @param dateReport the dateReport to set
	 */
	public void setDateReport(Date dateReport) {
		this.dateReport = dateReport;
	}

	/**
	 * @return the dateReport
	 */
	public Date getDateReport() {
		return dateReport;
	}
    /**
     * @return the formats 
     */
	public List<FormatReport> getFormats() {
		return formats;
	}
	/**
	 * 
	 * @param formats
	 */
	public void setFormats(List<FormatReport> formats) {
		this.formats = formats;
	}
	
    public void addFormat(FormatReport f){
    	formats.add( f );
    }

	public String getJdbcProvider() {
		return jdbcProvider;
	}

	public void setJdbcProvider(String jdbcProvider) {
		this.jdbcProvider = jdbcProvider;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNameGenerated() {
		return nameGenerated;
	}

	public void setNameGenerated(String nameGenerated) {
		this.nameGenerated = nameGenerated;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}
	
	
	
	
	
}

