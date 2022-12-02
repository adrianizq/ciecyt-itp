package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.repository.ProyectoRepository;
import co.edu.itp.ciecyt.service.ReportService;
import javassist.ClassPath;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ProyectoRepository proyectoRepository;

    public String exportReport (String reportFormat) throws FileNotFoundException, JRException {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        File file = ResourceUtils.getFile("classpath:proyectos.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(proyectos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("created by","Adrian");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
        String path = "/home/adrian/reports/";
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path + "proyectos.html");
        }

        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path + "proyectos.pdf");
        }
        return "Report generated in path: " + path;
    }
}
