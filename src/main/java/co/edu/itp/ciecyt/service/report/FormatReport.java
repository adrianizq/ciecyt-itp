package  co.edu.itp.ciecyt.service.report;

/**
 * Enumeracion para definir los tipos de formatos en que pueden generarse
 * los diferentes reportes del sistema.
 * @author JLT
 *
 */
public enum FormatReport {
	
	/**************************
	 * Definicion de los tipos
	 *************************/
	
	PDF("pdf", "pdf"),
	EXCEL("xls", "excel"),
	WORDX("docx", "word"),
	HTML("html", "html"),
	TEXT("txt", "text"),
	RTF("rtf", "rtf"),
	CSV("csv", "csv"),
	XML("xml", "xml"),
	PPTX("pptx", "ppt"),
	OPENDOCUMENT("odt", "odt"),
	OPENSPREADSHEET("ods", "ods");
	
	/******************************
	 * Atributos
	 *****************************/
	
	/**
	 * El formato del documento
	 */
	private String format;
	
	/**
	 * El identificador
	 */
	private String id;
	
	/**
	 * Constructor
	 * @param format el formato del documento
	 * @param id el identificador 
	 */
	private FormatReport(String format, String id){
		this.format = format;
		this.id = id;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	


}
