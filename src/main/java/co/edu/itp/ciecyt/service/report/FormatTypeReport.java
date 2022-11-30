package  co.edu.itp.ciecyt.service.report;

/**
 * Clase para el manejo de los tipos de formatos en los reportes 
 * 
 * @author JLT
 *
 */
public class FormatTypeReport {
	
	/***************************
	 * Atributos
	 **************************/
	
	/**
	 * El formato del reporte
	 */
	private FormatReport format;

	/**
	 * Ruta donde se encuentra el icono de este formato
	 */
	private String icon;
	
	/**
	 * Identificador del tipo de formato
	 */
	private String id;
	
	

	/**
	 * Metodo para recupera el tipo de Formato de un reporte
	 * @param id el identificador del formato
	 * @return el formato del reporte que tiene ese identificador
	 */
	public static FormatReport getFormat( String id ){
		FormatReport f_format = FormatReport.EXCEL;
		for(FormatReport f: FormatReport.values()){
			if( f.getId().equals( id )){
				f_format = f;
				break;
			}
		}
		return f_format;
	}



	/**
	 * @return the format
	 */
	public FormatReport getFormat() {
		return format;
	}



	/**
	 * @param format the format to set
	 */
	public void setFormat(FormatReport format) {
		this.format = format;
	}



	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}



	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
		this.format = FormatTypeReport.getFormat(id);
	}
}
