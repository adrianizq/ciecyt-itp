package  co.edu.itp.ciecyt.service.report;

/**
 * Enumeracion para la definicion de los tipos de campos
 * que existen en los reportes
 * @author JLT
 *
 */
public enum FieldType {
	
	/**************************
	 * Definicion de los tipos
	 *************************/
	
	ALPHANUMERIC("A"), 
	INTEGER("N"), 
	DECIMAL("D"), 
	MONETARY("M"), 
	PERCENT("p"),
	DATE("F"),
	BOOLEAN("S"),
	MULTIVALUE("V"),
	BINARY("B"),
	HEXAGECIMAL("H"),
	JULIAN("J"),
	TIME("T"),
	ENCRYPT("E"),
	IMAGE("I");

	/******************************
	 * Atributos
	 *****************************/
	
	/**
	 * El tipo de campo
	 */
	private String type;

	/**
	 * Constructor
	 * @param type el tipo
	 */
	private FieldType(String type){
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	
	

}
