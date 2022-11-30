package  co.edu.itp.ciecyt.service.report;

import java.io.File;
import java.text.DecimalFormat;

import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class TestReport {

	public static void main(String args[]) throws Exception {
		DecimalFormat df = new DecimalFormat("###.#");
		System.out.println(df.parse( "6.0"));
		//Integer.parseInt("0.0");
		try {
			String sourcename = "/home/adrian/workspace/cygnus-plus/WebContent/WEB-INF/conf/jasper/cert001.jrxml";
			File file = new File( sourcename );
			System.out.println(file.exists());
			//JasperDesign jasperDesign = JRXmlLoader.load(file);
			System.out.println("--jasperDesign---");
			//JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperCompileManager.compileReportToFile( sourcename );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
