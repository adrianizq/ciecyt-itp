package co.edu.itp.ciecyt.service.report;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

import co.edu.itp.ciecyt.service.util.DateUtil;


/**
 * Clase para manejar utilidades de los campos y tipos de campos de los reportes
 * @author ESF - JLT
 */
public class ReportHelper {

	
	/**
	 *  Metodo para convertir el campo a su formato estandar de tipo de Dato
	 *  A: alfanum�rico
	 *  N: num�rico entero
	 *  D: decimal
	 *  P: porcentaje
	 *  M: valor monetario
	 *  P: porcentaje
	 *  F: fecha
	 *  S: tipo booleano (falso o verdadero)
	 *  V: lista de valores
	 *  B: binario
	 *  H: hexadecimal
	 *  J: juliano
	 *  T: hora
	 *  E: encriptado
	 *  I: imagen
	 *  @param field el campo. El nombre del campo en la base de datos. Ej: ENTCDENAK
	 *  @param value el valor que tiene ese campo, Ej: E001
	 */
	public static Object getParameter( String field, String value){
		Object param = null;
		if( field != null && !field.equals("")){
				
			FieldType f_key = getDataType(field);
			switch ( f_key ) {
			case ALPHANUMERIC:
				param = new String( value );
				break;
			case INTEGER:
				try {
					param = new Integer( value );
				} catch (NumberFormatException e) {
					param = new String( value );
				}
				break;
			case DECIMAL:
				try {
					param = new Double( value );
				} catch (NumberFormatException e) {
					param = new String( value );
				}
				break;
			case PERCENT:
				try {
					param = new Double( value );
				} catch (NumberFormatException e) {
					param = new String( value );
				}
				break;
			case MONETARY:
				try {
					param = new Double( value );
				} catch (NumberFormatException e) {
					param = new String( value );
				}
				break;
			case JULIAN:
				try {
					param = new Integer( value );
				} catch (NumberFormatException e) {
					param = new String( value );
				}
				break;
			case DATE:
				try {
					param = DateUtil.getInstance().parse("yyyy-MM-dd", value);
				} catch (ParseException e) {
					param = new String( value );
				}
				break;
			case TIME:
				try {
					Date date = DateUtil.getInstance().parse("hh:mm:ss", value);
					param = new Time( date.getTime());
				} catch (ParseException e) {
					param = new String( value );
				}
				catch (Exception e) {
					param = new String( value );
				}
				break;
			default:
				param = new String( value );
				break;
			}
		}
		return param;
	}
	
	/**
	 * Metodo para recuperar la primera letra del campo, que define su tipo de dato
	 * @param field el campo del cual se va a saber su tipo de dato
	 * @return el tipo de campo de el campo que ha llegado
	 */
	public static FieldType getDataType( String field ){
		String type = "A";
		FieldType f_type = FieldType.ALPHANUMERIC;
		int length = field.length();
		if( length > 0){
			field = field.toUpperCase();
			type = field.substring( 0, 1);
			for(FieldType f: FieldType.values()){
				if( f.getType().equals( type )){
					f_type = f;
					break;
				}
			}
		}
		return f_type;
	}
	
	

}
