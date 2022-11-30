package co.edu.itp.ciecyt.service.report;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import co.edu.itp.ciecyt.config.ApplicationProperties;
import co.edu.itp.ciecyt.service.dto.FileVO;
import co.edu.itp.ciecyt.service.util.DateUtil;
import co.edu.itp.ciecyt.service.util.DirectoryException;
import co.edu.itp.ciecyt.service.util.FileUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;

/**
 * Clase encargada de generar el reporte con JasperReports
 * 
 * @author JLT
 * 
 */

@Scope("singleton")
@Component
public class ReportManager implements IReportManager{

	private static final Logger log = LoggerFactory.getLogger(ReportManager.class);

	/**************************************
	 * Atributos
	 *************************************/


	/**
	 * Extensi�n del archivo fuente de un reporte
	 */
	public static final String JASPER_SOURCE_EXT = "jrxml";

	/**
	 * Extensi�n del archivo compilado de un reporte
	 */
	public static final String JASPER_COMPILE_EXT = "jasper";

	/**
	 * Separador palabras del nombre de un reporte
	 */
	public static final String REPORT_NAME_SEPARATOR = "_";

	/**
	 * Ruta base del directorio donde se almacenan los reportes
	 */
	public static String REPORT_DIRECTORY = ".";

	/**
	 * Ruta base del directorio donde se almacenan los fuentes de los reportes
	 */
	private static String REPORT_SOURCE_DIRECTORY = ".";

	/**
	 * Ruta base del directorio donde se almacenan las imagenes de los reportes
	 */
	public static String REPORT_IMAGE_DIRECTORY = ".";

	/**
	 * Separador de rutas de Directorio
	 */
	public static final String REPORT_SEPARATOR_DIRECTORY = FileUtil.getInstance().getSeparator();
	/**
	 * Lista de reportes configurados
	 */
	private Map<String, ReportInfo> reports;

	/**
	 * Lista de reportes configurados
	 */
	private List<ReportInfo> listReports;
	/**
	 * ResourceBundle
	 */
	private static ResourceBundle bundle;

	private String reportSourceDirectory;

	private String reportImageDirectory;

	private String reportOutputDirectory;

	private final ApplicationProperties appProperties;

	/**
	 * Constructor
	 */
	public ReportManager(ApplicationProperties appProperties) {
		this.appProperties = appProperties;
		Path filesDir = Paths.get(appProperties.getJasper().getFile().getDir());
		Path sourceDir = Paths.get(appProperties.getJasper().getSource().getDir());
		Path imagesDir = Paths.get(appProperties.getJasper().getImage().getDir());
		setReportSourceDirectory( sourceDir.toFile().getAbsolutePath());
		setReportOutputDirectory( filesDir.toFile().getAbsolutePath());
		setReportImageDirectory( imagesDir.toFile().getAbsolutePath());

	}




	public void reloadParams() {
		try {
			init();

		} catch (Exception e) {

			log.error("Error recuperando parametros de configuracion de los reportes.", e);

		}
	}

	@PostConstruct
	private void init() throws Exception {
		try {
			REPORT_SOURCE_DIRECTORY = getReportSourceDirectory();

			REPORT_IMAGE_DIRECTORY = getReportImageDirectory();

			REPORT_DIRECTORY = getReportOutputDirectory();

		} catch (Exception e) {
			throw e;

		} finally {
			if (reports == null) {
				reports = new HashMap<String, ReportInfo>();
			}
		}

	}





	public void processReport(List<?> dataList, ReportInfo reportConfig, Map<String, Object> parameters,
			String formatOutput) throws JRException {


		// if (reportConfig.getCode() == null)
		// throw new JRException("Code report not found!");

		// String sourceFileName = getSourceName( reportConfig.getCode(),
		// reportConfig.getJdbcProvider() );
		String sourceFileName = reportConfig.getNameReport();

		if (sourceFileName != null)
			sourceFileName = sourceFileName.trim();

		// Adiciona el par�metro SUBREPORT_DIR, que corresponde al directorio
		// base de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("SUBREPORT_DIR", reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY);

		// Adiciona el par�metro IMAGE_PATH, que corresponde al directorio base
		// Imagenes de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("IMAGE_PATH", reportImageDirectory + REPORT_SEPARATOR_DIRECTORY);

		parameters.put("LOGO", reportConfig.getLogo());

		parameters.put("REPORT_TITLE", reportConfig.getTitle());

		parameters.put("COMPANY_NAME", reportConfig.getCompanyName());

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);


		String reportDirectory = reportOutputDirectory + REPORT_SEPARATOR_DIRECTORY;
		// Verifica si el directorio de reportes del m�dulo ya fue creado
		if (!FileUtil.getInstance().existDirectory(reportDirectory)) {
			// crea el directorio para el reporte
			try {
				FileUtil.getInstance().createDirectory(reportDirectory);
			} catch (IllegalArgumentException e) {
				throw new JRException(e);
			} catch (DirectoryException e) {
				throw new JRException(e);
			} catch (Exception e) {
				throw new JRException(e);
			}
		}
		String reportSourceDirectory = REPORT_SOURCE_DIRECTORY;
		// Verifica si el reporte Maestro ya existe
		if (!FileUtil.getInstance().existsFile(
				reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY + sourceFileName + "."
						+ JASPER_COMPILE_EXT)) {
			// Compila el reporte Maestro y los subreportes asociados
			compileReports(REPORT_SOURCE_DIRECTORY, sourceFileName);
		}
		// Recupera el formato de salida
		FormatReport format = getFormat(formatOutput);
		// Genera nombre de archivo de salida
		String nameReportGenerated =  getReportName(reportConfig, format);

		generateReport(ds, reportConfig, reportSourceDirectory, reportDirectory, nameReportGenerated, sourceFileName, format, parameters);


	}

	public void processReportUser(String userfileName, List<?> dataList, ReportInfo reportConfig, Map<String, Object> parameters,
			String formatOutput) throws JRException {


		// if (reportConfig.getCode() == null)
		// throw new JRException("Code report not found!");

		// String sourceFileName = getSourceName( reportConfig.getCode(),
		// reportConfig.getJdbcProvider() );
		String sourceFileName = reportConfig.getNameReport();

		if (sourceFileName != null)
			sourceFileName = sourceFileName.trim();

		// Adiciona el par�metro SUBREPORT_DIR, que corresponde al directorio
		// base de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("SUBREPORT_DIR", reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY);

		// Adiciona el par�metro IMAGE_PATH, que corresponde al directorio base
		// Imagenes de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("IMAGE_PATH", reportImageDirectory + REPORT_SEPARATOR_DIRECTORY);

		parameters.put("LOGO", reportConfig.getLogo());

		parameters.put("REPORT_TITLE", reportConfig.getTitle());

		parameters.put("COMPANY_NAME", reportConfig.getCompanyName());

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);


		//String reportDirectory = reportOutputDirectory + REPORT_SEPARATOR_DIRECTORY;
		String reportDirectory = reportOutputDirectory ;
		// Verifica si el directorio de reportes del m�dulo ya fue creado
		if (!FileUtil.getInstance().existDirectory(reportDirectory)) {
			// crea el directorio para el reporte
			try {
				FileUtil.getInstance().createDirectory(reportDirectory);
			} catch (IllegalArgumentException e) {
				throw new JRException(e);
			} catch (DirectoryException e) {
				throw new JRException(e);
			} catch (Exception e) {
				throw new JRException(e);
			}
		}
		
		// Verifica si el reporte Maestro ya existe
		if (!FileUtil.getInstance().existsFile(
				reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY + sourceFileName + "."
						+ JASPER_COMPILE_EXT)) {
			// Compila el reporte Maestro y los subreportes asociados
			compileReports(reportSourceDirectory, sourceFileName);
		}
		// Recupera el formato de salida
		FormatReport format = getFormat(formatOutput);
		// Genera nombre de archivo de salida
		String nameReportGenerated =  getReportName(userfileName, format);

		generateReport(ds, reportConfig, reportSourceDirectory, reportDirectory, nameReportGenerated, sourceFileName, format, parameters);


	}


	public void processReport(String reportDirectory, String imagesDirectory, List<?> parametersList, ReportInfo reportConfig, Map<String, Object> parameters,
			String formatOutput) throws JRException {


		// if (reportConfig.getCode() == null)
		// throw new JRException("Code report not found!");

		// String sourceFileName = getSourceName( reportConfig.getCode(),
		// reportConfig.getJdbcProvider() );
		String sourceFileName = reportConfig.getNameReport();

		if (sourceFileName != null)
			sourceFileName = sourceFileName.trim();

		// Adiciona el par�metro SUBREPORT_DIR, que corresponde al directorio
		// base de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("SUBREPORT_DIR", REPORT_SOURCE_DIRECTORY + REPORT_SEPARATOR_DIRECTORY);

		// Adiciona el par�metro IMAGE_PATH, que corresponde al directorio base
		// Imagenes de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("IMAGE_PATH", imagesDirectory + REPORT_SEPARATOR_DIRECTORY);

		parameters.put("BG_IMAGE_PATH", imagesDirectory + REPORT_SEPARATOR_DIRECTORY);

		parameters.put("LOGO", reportConfig.getLogo());

		parameters.put("REPORT_TITLE", reportConfig.getTitle());

		parameters.put("COMPANY_NAME", reportConfig.getCompanyName());

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(parametersList);


		// Verifica si el directorio de reportes ya fue creado
		if (!FileUtil.getInstance().existDirectory(reportDirectory)) {
			// crea el directorio para el reporte
			try {
				FileUtil.getInstance().createDirectory(reportDirectory);
			} catch (IllegalArgumentException e) {
				throw new JRException(e);
			} catch (DirectoryException e) {
				throw new JRException(e);
			} catch (Exception e) {

				throw new JRException(e);
			}
		}
		String reportSourceDirectory = REPORT_SOURCE_DIRECTORY;
		// Verifica si el reporte Maestro ya existe
		if (!FileUtil.getInstance().existsFile(
				reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY + sourceFileName + "."
						+ JASPER_COMPILE_EXT)) {
			log.debug("Compila reports {}", reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY + sourceFileName + "."
			+ JASPER_COMPILE_EXT);				
			// Compila el reporte Maestro y los subreportes asociados
			compileReports(REPORT_SOURCE_DIRECTORY, sourceFileName);
		}
		// Recupera el formato de salida
		FormatReport format = getFormat(formatOutput);
		// Genera nombre de archivo de salida
		String nameReportGenerated =  getReportName(reportConfig, format);

		generateReport(ds, reportConfig, reportSourceDirectory, reportDirectory, nameReportGenerated, sourceFileName, format, parameters);


	}

	public void processReport(String reportDirectory, String imagesDirectory, String backgroundDir, List<?> parametersList, ReportInfo reportConfig, Map<String, Object> parameters,
			String formatOutput) throws JRException {


		// if (reportConfig.getCode() == null)
		// throw new JRException("Code report not found!");

		// String sourceFileName = getSourceName( reportConfig.getCode(),
		// reportConfig.getJdbcProvider() );
		String sourceFileName = reportConfig.getNameReport();

		if (sourceFileName != null)
			sourceFileName = sourceFileName.trim();

		// Adiciona el par�metro SUBREPORT_DIR, que corresponde al directorio
		// base de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("SUBREPORT_DIR", REPORT_SOURCE_DIRECTORY + REPORT_SEPARATOR_DIRECTORY);

		// Adiciona el par�metro IMAGE_PATH, que corresponde al directorio base
		// Imagenes de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("IMAGE_PATH", imagesDirectory + REPORT_SEPARATOR_DIRECTORY);

		parameters.put("BG_IMAGE_PATH", backgroundDir + REPORT_SEPARATOR_DIRECTORY + reportConfig.getBgImage() );

		parameters.put("LOGO", reportConfig.getLogo());

		parameters.put("REPORT_TITLE", reportConfig.getTitle());

		parameters.put("COMPANY_NAME", reportConfig.getCompanyName());

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(parametersList);


		// Verifica si el directorio de reportes ya fue creado
		if (!FileUtil.getInstance().existDirectory(reportDirectory)) {
			// crea el directorio para el reporte
			try {
				FileUtil.getInstance().createDirectory(reportDirectory);
			} catch (IllegalArgumentException e) {
				throw new JRException(e);
			} catch (DirectoryException e) {
				throw new JRException(e);
			} catch (Exception e) {

				throw new JRException(e);
			}
		}
		String reportSourceDirectory = REPORT_SOURCE_DIRECTORY;
		// Verifica si el reporte Maestro ya existe
		if (!FileUtil.getInstance().existsFile(
				reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY + sourceFileName + "."
						+ JASPER_COMPILE_EXT)) {
			// Compila el reporte Maestro y los subreportes asociados
			compileReports(REPORT_SOURCE_DIRECTORY, sourceFileName);
		}
		// Recupera el formato de salida
		FormatReport format = getFormat(formatOutput);
		// Genera nombre de archivo de salida
		String nameReportGenerated =  getReportName(reportConfig, format);

		generateReport(ds, reportConfig, reportSourceDirectory, reportDirectory, nameReportGenerated, sourceFileName, format, parameters);


	}


	public void processReport(String reportDirectory, List<?> parametersList, ReportInfo reportConfig, Map<String, Object> parameters,
			String formatOutput) throws JRException {


		// if (reportConfig.getCode() == null)
		// throw new JRException("Code report not found!");

		// String sourceFileName = getSourceName( reportConfig.getCode(),
		// reportConfig.getJdbcProvider() );
		String sourceFileName = reportConfig.getNameReport();

		if (sourceFileName != null)
			sourceFileName = sourceFileName.trim();

		// Adiciona el par�metro SUBREPORT_DIR, que corresponde al directorio
		// base de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("SUBREPORT_DIR", REPORT_SOURCE_DIRECTORY + REPORT_SEPARATOR_DIRECTORY);

		// Adiciona el par�metro IMAGE_PATH, que corresponde al directorio base
		// Imagenes de los reportes
		// Jasper exige que se envie el separador de path al final
		parameters.put("IMAGE_PATH", REPORT_IMAGE_DIRECTORY + REPORT_SEPARATOR_DIRECTORY);

		parameters.put("LOGO", reportConfig.getLogo());

		parameters.put("REPORT_TITLE", reportConfig.getTitle());

		parameters.put("COMPANY_NAME", reportConfig.getCompanyName());

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(parametersList);


		// Verifica si el directorio de reportes ya fue creado
		if (!FileUtil.getInstance().existDirectory(reportDirectory)) {
			// crea el directorio para el reporte
			try {
				FileUtil.getInstance().createDirectory(reportDirectory);
			} catch (IllegalArgumentException e) {
				throw new JRException(e);
			} catch (DirectoryException e) {
				throw new JRException(e);
			} catch (Exception e) {

				throw new JRException(e);
			}
		}
		String reportSourceDirectory = REPORT_SOURCE_DIRECTORY;
		// Verifica si el reporte Maestro ya existe
		if (!FileUtil.getInstance().existsFile(
				reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY + sourceFileName + "."
						+ JASPER_COMPILE_EXT)) {
			// Compila el reporte Maestro y los subreportes asociados
			compileReports(REPORT_SOURCE_DIRECTORY, sourceFileName);
		}
		// Recupera el formato de salida
		FormatReport format = getFormat(formatOutput);
		// Genera nombre de archivo de salida
		String nameReportGenerated =  getReportName(reportConfig, format);

		generateReport(ds, reportConfig, reportSourceDirectory, reportDirectory, nameReportGenerated, sourceFileName, format, parameters);


	}

	/**
	 * @param input
	 * @param out
	 * @param parameters
	 * @param formatOuput
	 * @param connection
	 */


	public void processReport(InputStream input, OutputStream out, Map<String, Object> parameters,
			String formatOuput, Connection connection) throws JRException {
		// TODO se puede borrar?
		// Recupera el formato de salida
		FormatReport format = getFormat(formatOuput);
		switch (format) {
		case PDF:
			JasperRunManager.runReportToPdfStream(input, out, parameters, connection);
			break;
		case EXCEL:

			break;
		default:

			break;
		}

	}

	/**
	 * Genera Nombre del Reporte CodigoModulo_CodigoReporte_Fecha_Secuencia
	 * 
	 * @param config
	 * @return
	 */
	private String getReportName(ReportInfo config, FormatReport format) {

		String name = "";
		Date d = DateUtil.getInstance().getDateCurrent();
		config.setDateReport(d);
		String id = config.getIdentifier();

		if( null == id){
			id = "";
		}else{
			id = id + REPORT_NAME_SEPARATOR;
		}

		String dateFormat = DateUtil.getInstance().format("yyyyMMddhhmmss", d);
		name = config.getUserId() + REPORT_NAME_SEPARATOR + config.getCode() + REPORT_NAME_SEPARATOR
				+ id + dateFormat;

		name = name.toUpperCase() + "." + format.getFormat();
		name = StringUtils.deleteWhitespace( name );
		return name;
	}
	
	private String getReportName(String userFileName, FormatReport format) {

		String name = userFileName + "." + format.getFormat();
		name = StringUtils.deleteWhitespace( name );
		return name;
	}

	/**
	 * Recupera el tipo de Formato de un reporte
	 * 
	 * @param format
	 * @return
	 */
	private FormatReport getFormat(String id) {
		return FormatTypeReport.getFormat(id);
	}

	/**
	 * Compila el reporte Maestro y los subreportes anidados Los Subreportes se deben llamar igual al
	 * reporte Maestro seguido por un consecutivo
	 * 
	 * @param rootReport
	 * @param nameReport
	 */
	private void compileReports(String rootReport, String nameReport) {
		// Recupera el patr�n del nombre del reporte
		String patternReport = getPatternReport(nameReport);
		// Recupera la lista de archivos que contengan el nombre de reportes
		List<FileVO> fileReports = FileUtil.getInstance().getPatternFiles(rootReport, patternReport,
				JASPER_SOURCE_EXT);
		for (Iterator<FileVO> iterator = fileReports.iterator(); iterator.hasNext();) {
			FileVO f = (FileVO) iterator.next();
			String sourceReport = rootReport + REPORT_SEPARATOR_DIRECTORY
					+ FileUtil.getInstance().removeExtension(f.getName());
			try {
				JasperCompileManager.compileReportToFile(sourceReport + "." + JASPER_SOURCE_EXT,
						sourceReport + "." + JASPER_COMPILE_EXT);
			} catch (JRException e) {
				log.error("Error compilando reporte: " + f.getName(),e);
			}
		}
	}

	private void generateReport( JRBeanCollectionDataSource ds, ReportInfo reportConfig, String reportSourceDirectory, String reportDirectory, 
			String nameReportGenerated, String sourceFileName, FormatReport format, Map<String, Object> parameters ) throws JRException {

		if (FileUtil.getInstance().existsFile(
				reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY + sourceFileName + "."
						+ JASPER_COMPILE_EXT)) {


			String reportPath = reportDirectory
					+ REPORT_SEPARATOR_DIRECTORY + nameReportGenerated;

			reportConfig.setNameGenerateReport( reportPath );
			reportConfig.setNameGenerated(nameReportGenerated);
			// Genera el archivo jasper compilado
			JasperReport reportJasper = (JasperReport) JRLoader
					.loadObject( new File(reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY
							+ sourceFileName + "." + JASPER_COMPILE_EXT));

			// JasperCompileManager.compileReport(REPORT_SOURCE_DIRECTORY +
			// REPORT_SEPARATOR_DIRECTORY +
			// sourceFileName+"."+JASPER_SOURCE_EXT);
			JasperPrint print = JasperFillManager.fillReport(reportJasper, parameters, ds);
			exporterReport(print, format, reportPath);
		}

	}

	private void generateReport( Connection conn, ReportInfo reportConfig, String reportSourceDirectory, String reportDirectory, 
			String nameReportGenerated, String sourceFileName, FormatReport format, Map<String, Object> parameters ) throws JRException {

		if (FileUtil.getInstance().existsFile(
				reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY + sourceFileName + "."
						+ JASPER_COMPILE_EXT)) {


			String reportPath = reportDirectory
					+ REPORT_SEPARATOR_DIRECTORY + nameReportGenerated;

			reportConfig.setNameGenerateReport( reportPath );
			reportConfig.setNameGenerated(nameReportGenerated);
			// Genera el archivo jasper compilado
			JasperReport reportJasper = (JasperReport) JRLoader
					.loadObject( new File(reportSourceDirectory + REPORT_SEPARATOR_DIRECTORY
							+ sourceFileName + "." + JASPER_COMPILE_EXT) );

			// JasperCompileManager.compileReport(REPORT_SOURCE_DIRECTORY +
			// REPORT_SEPARATOR_DIRECTORY +
			// sourceFileName+"."+JASPER_SOURCE_EXT);
			JasperPrint print = JasperFillManager.fillReport(reportJasper, parameters, conn );
			exporterReport(print, format, reportPath);
		}

	}

	private void exporterReport(JasperPrint jasperPrint, FormatReport format, String reportPath ) throws JRException{
		switch (format) {
		case PDF:
			JRPdfExporter exportPDF = new JRPdfExporter();
			// JasperExportManager.exportReportToPdfFile(print,
			// reportDirectory + REPORT_SEPARATOR_DIRECTORY+
			// reportName+"."+format.getFormat() );
			exportPDF.setExporterInput(new SimpleExporterInput(jasperPrint));
			exportPDF.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath));
			exportPDF.exportReport();
			break;
		case EXCEL:
			JRXlsxExporter exporterXLS = new JRXlsxExporter();
			exporterXLS.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath));
			exporterXLS.exportReport();
			break;
		case HTML:
			HtmlExporter exporterXHTML = new HtmlExporter();
			exporterXHTML.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterXHTML.setExporterOutput(new SimpleHtmlExporterOutput(reportPath));
			exporterXHTML.exportReport();
			break;
		case WORDX:
			JRDocxExporter exporterWord = new JRDocxExporter();
			exporterWord.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterWord.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath));

			SimpleDocxReportConfiguration configuration = new SimpleDocxReportConfiguration();
			configuration.setFlexibleRowHeight( true );

			exporterWord.setConfiguration(configuration);

			//SimpleDocxExporterConfiguration configExport = new SimpleDocxExporterConfiguration();

			//exporterWord.setConfiguration(configExport);
			//exporterWord.setParameter(JRDocxExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE);
			exporterWord.exportReport();
			break;
		case PPTX:
			JRPptxExporter exporterPptx = new JRPptxExporter();
			exporterPptx.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterPptx.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath));
			exporterPptx.exportReport();
			break;

		case RTF:
			JRRtfExporter exporterRTF = new JRRtfExporter();
			exporterRTF.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterRTF.setExporterOutput(new SimpleWriterExporterOutput(reportPath));
			exporterRTF.exportReport();
			break;
		case TEXT:
			JRTextExporter exporterTEXT = new JRTextExporter();
			exporterTEXT.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterTEXT.setExporterOutput(new SimpleWriterExporterOutput(reportPath));
			exporterTEXT.exportReport();
			break;
		case CSV:
			JRCsvExporter exporterCSV = new JRCsvExporter();
			exporterCSV.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterCSV.setExporterOutput(new SimpleWriterExporterOutput(reportPath));
			exporterCSV.exportReport();
			break;
		case XML:
			JRXmlExporter exporterXML = new JRXmlExporter();
			exporterXML.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterXML.setExporterOutput(new SimpleXmlExporterOutput(reportPath));
			exporterXML.exportReport();
			break;
		case OPENDOCUMENT:
			JROdtExporter exporterODT = new JROdtExporter();
			exporterODT.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterODT.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath));
			exporterODT.exportReport();
			break;
		case OPENSPREADSHEET:
			JROdsExporter exporterODS = new JROdsExporter();
			exporterODS.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporterODS.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath));
			exporterODS.exportReport();
			break;
		default:
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath );
			break;
		}

	}

	/**
	 * Retorna el nombre del archivo fuente del reporte dado el JdbcProvider
	 * 
	 * @param codeReport
	 *          C�digo del Reporte (8 caracteres)
	 * @param jdbcProvider
	 *          Proveedor de JDBC (ORA: Oracle , SQL: SQL Server)
	 */
	private String getSourceName(String codeReport, String jdbcProvider) {
		// Recupera el patr�n del nombre del reporte
		String nameReport = codeReport.substring(0, 4) + jdbcProvider + codeReport.substring(4);

		return nameReport;
	}

	/**
	 * Identifica cu�l es el patr�n de los archivos de un reporte cuando este contenga subreportes
	 * incluidos. El nombre del reporte est� formado de la siguiente manera: J AA R PRE NN SS J =
	 * Programa JAVA (1 caracter) AA = C�digo del M�dulo (2 caracteres) R = Indicador de Reporte (1
	 * caracter) PRE = Prefijo del JDBC Provider ORA(Oracle) SQL(MSSQL Server) NN = Consecutivo para
	 * el reporte. Inicia en 00 (2 caracteres) SS = Consecutivo para subreportes. Inicia en 01 (2
	 * caracter)
	 * 
	 * @param nameReport
	 *          El nombre del reporte
	 * @return Patr�n del Reporte
	 */
	private String getPatternReport(String nameReport) {
		String pattern = nameReport;
		if (nameReport != null && nameReport.length() > 9 ) {
			// Las primeras 9 posiciones del nombre, corresponden al patr�n del los subreportes
			pattern = nameReport.substring(0, 9);
		}
		else if(nameReport != null) {
			pattern = nameReport.trim();
		}

		return pattern;
	}

	public ReportInfo getReportByCode(String code) {
		return reports.get(code);
	}

	public static void main(String[] args) {
		//System.out.println(ReportManager.getInstance().getPatternReport("JBBRORA003"));
		//System.out.println(ReportManager.getInstance().getSourceName("JBBR0001", "ORA"));

		//System.out.println("Clean spaces: "+ StringUtils.deleteWhitespace("ADMIN_JRACAD002_EASF00C02-P01-EASF00CO2A02M02	-2013-1-2_20130826092230.pdf"));
	}

	public Map<String, ReportInfo> getReports() {

		return reports;
	}

	public void setReports(Map<String, ReportInfo> reports) {
		this.reports = reports;
	}

	public List<ReportInfo> getListReports() {
		return listReports;
	}

	public void setListReports(List<ReportInfo> listReports) {
		this.listReports = listReports;
	}




	public String getReportSourceDirectory() {
		return reportSourceDirectory;
	}




	public void setReportSourceDirectory(String reportSourceDirectory) {
		this.reportSourceDirectory = reportSourceDirectory;
	}




	public String getReportImageDirectory() {
		return reportImageDirectory;
	}




	public void setReportImageDirectory(String reportImageDirectory) {
		this.reportImageDirectory = reportImageDirectory;
	}




	public String getReportOutputDirectory() {
		return reportOutputDirectory;
	}




	public void setReportOutputDirectory(String reportOutputDirectory) {
		this.reportOutputDirectory = reportOutputDirectory;
	}

}
