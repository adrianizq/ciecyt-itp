package co.edu.itp.ciecyt.service.report;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

public interface IReportManager {
	
	
	 public void processReport(List<?> dataList, ReportInfo reportConfig, Map<String, Object> parameters,
		      String formatOutput) throws Exception;
	 
	 public void processReport(String reportDirectory, String imagesDirectory, List<?> parametersList, ReportInfo reportConfig, Map<String, Object> parameters,
		      String formatOutput) throws Exception;
	 
	 public void processReport(String reportDirectory, String imagesDirectory, String bgImagePath, List<?> parametersList, ReportInfo reportConfig, Map<String, Object> parameters,
		      String formatOutput) throws Exception;
	 
	 public void processReport(String reportDirectory, List<?> parametersList, ReportInfo reportConfig, Map<String, Object> parameters,
		      String formatOutput) throws Exception;
	 
	 public void processReportUser(String userfileName, List<?> dataList, ReportInfo reportConfig, Map<String, Object> parameters,
				String formatOutput) throws JRException ;

}
