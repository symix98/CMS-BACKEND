package net.ccc.apps.core.service.reports;

import net.ccc.apps.core.domain.Attachement;
import net.ccc.apps.core.domain.Report;

import net.ccc.apps.core.domain.enumeration.AttachementType;
import net.ccc.apps.core.domain.enumeration.JasperExportType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractReport {
    private final Logger log = LoggerFactory.getLogger(AbstractReport.class);
    String reportName;
    String reportFormat;
    String reportPath="classpath:reports/jrxml/";
    Report report;

    Connection conn;
    JasperReport jasperReport;
    HttpServletResponse response;
    Map<String, Object> parameterMap=new HashMap<>();
    List<Attachement> logosList;

    ReportParameters reportParameters;

    Boolean isInDirectory;


    public JasperReport init(String reportFormat, List<Attachement> logosList, Optional<Report> optionalReport, ReportParameters reportParameters, Connection conn, HttpServletResponse response) throws IOException, JRException {
        this.response=response;
        this.reportFormat=reportFormat;
        this.conn=conn;
        this.logosList=logosList;
        this.reportParameters=reportParameters;

        if(optionalReport.isPresent()) {
            this.report= optionalReport.get();
            this.reportName=this.report.getName();
        }
        addReportParameter();
        generateHeaderLogos();
        generateHeaderSubReport("header.jrxml");

        File mainFile = null;



        if( isInDirectory( reportName)){
            mainFile = ResourceUtils.getFile(reportPath +"/"+reportName+"/"+ report.getReportPath());

        }else{

            mainFile = ResourceUtils.getFile( reportPath + report.getReportPath());
        }



        JasperDesign design = JRXmlLoader.load(mainFile);
        jasperReport = JasperCompileManager.compileReport(design);

        if( isInDirectory( reportName)) {
            generateSubReports();
        }
        generateReport();

        return jasperReport;
    }


    void addReportParameter(){

        Class c = reportParameters.getClass();
        for (Field f : c.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object paramValue = f.get(reportParameters);
                if(paramValue!=null) {
                    parameterMap.put(f.getName(), paramValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public  boolean isInDirectory(String reportName){
        Boolean isDirectory = false;
        try {
            isDirectory= Files.isDirectory(Path.of(ResourceUtils.getFile( reportPath + reportName).toURI()));
        } catch (FileNotFoundException e) {
        //    e.printStackTrace();
        }
       return isDirectory;
    }




    void generateSubReports(){

        try {
            log.info("Start generate {} in path {}",reportName,Paths.get(ResourceUtils.getFile( reportPath + reportName).toURI()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Stream<Path> paths = Files.walk(Paths.get(ResourceUtils.getFile( reportPath + reportName).toURI()))) {
            paths
                .filter(Files::isRegularFile)
                .filter(f->f.getFileName().toString().toLowerCase(Locale.ROOT).contains(".jrxml"))
                .filter(f->f.getFileName().toString().toLowerCase(Locale.ROOT).contains("sub"))
                .forEach(f->{
                    System.out.println(" generateSubReports :  "+f.getFileName() +"\n"+ f.toString());
                    addSubReport(f.toString());


                });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    void addSubReport(String subReportName)   {
        log.info("add Sub Report ** {}",subReportName);

        try {
            File headerFile = ResourceUtils.getFile(subReportName);
            JasperDesign subReportDesign = JRXmlLoader.load(headerFile);
            JasperReport subReport = JasperCompileManager.compileReport(subReportDesign);

            Arrays.stream(jasperReport.getParameters())
                .filter(jrParameter ->jrParameter.getValueClassName().equals("net.sf.jasperreports.engine.JasperReport") )
                .filter(jrParameter -> !jrParameter.getName().equals("SubReportHeader"))
                .filter(jrParameter ->!jrParameter.isSystemDefined())
                .filter(jrParameter ->subReport.getName().equalsIgnoreCase(jrParameter.getDescription()))
                .forEach(jrParameter -> {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println(subReport.getName()+" -- "+jrParameter.getName() +" -- "+jrParameter.getDescription() +" -- ");
                    parameterMap.put(jrParameter.getName(),subReport);
                    System.out.println("-------------------------------------------------------------------");
            });


        }catch(FileNotFoundException| JRException e){

            e.printStackTrace();
        }

    }

    void getGeneratedReport(String reportFormat,JasperPrint jasperPrint) throws JRException, IOException {

        if (reportFormat.equals("html")) {

            response.setHeader("Content-Disposition", "inline; filename=" + (reportName.substring(0, 1).toLowerCase() + reportName.substring(1))+".html");
            response.setContentType("text/html");
            this.getReportType(JasperExportType.HTML, jasperPrint, response);
        }

        if (reportFormat.equals("pdf")) {

            response.setHeader("Content-Disposition", "inline; filename="+ (reportName.substring(0, 1).toLowerCase() + reportName.substring(1))+".pdf");
            response.setContentType("application/pdf");
            getReportType(JasperExportType.PDF, jasperPrint, response);

        }

        if (reportFormat.equals("xlsx")) {
            response.setHeader("Content-Disposition", "attachment; filename="+ (reportName.substring(0, 1).toLowerCase() + reportName.substring(1)) +".xlsx");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            getReportType(JasperExportType.XLSX, jasperPrint, response);
        }
        if (reportFormat.equals("xls")) {
            response.setHeader("Content-Disposition", "attachment; filename="+ (reportName.substring(0, 1).toLowerCase() + reportName.substring(1)) +".xls");
            response.setContentType("application/vnd.ms-excel");
            getReportType(JasperExportType.XLS, jasperPrint, response);
        }

            response.getOutputStream().flush();
            response.getOutputStream().close();

    }

    void getReportType(JasperExportType reportFormat, JasperPrint jasperPrint, HttpServletResponse response) throws IOException, JRException {

        if(reportFormat.equals(JasperExportType.PDF)){
            JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
        }
        if(reportFormat.equals(JasperExportType.XLSX) ||reportFormat.equals(JasperExportType.XLS)){

            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true); // Detect cell types (date and etc.)
            configuration.setWhitePageBackground(false); // No white background!
            configuration.setFontSizeFixEnabled(false);

            // No spaces between rows and columns
            configuration.setRemoveEmptySpaceBetweenRows(true);
            configuration.setRemoveEmptySpaceBetweenColumns(true);

            final JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setConfiguration(configuration);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(response.getOutputStream());
            exporter.setExporterOutput(exporterOutput);
            exporter.exportReport();
        }

        if(reportFormat.equals(JasperExportType.HTML)){
            HtmlExporter exporter=new HtmlExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
            exporter.exportReport();
        }
    }


    void generateHeaderSubReport(String headerSubReportName) throws FileNotFoundException, JRException {
        File headerFile = ResourceUtils.getFile( reportPath + headerSubReportName);
        JasperDesign headerDesign = JRXmlLoader.load(headerFile);
        JasperReport headerSubReport = JasperCompileManager.compileReport(headerDesign);
        parameterMap.put("SubReportHeader", headerSubReport);
    }

    void generateReport() throws JRException, IOException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, conn);
        this.getGeneratedReport(this.reportFormat,jasperPrint);
    }

     void generateHeaderLogos() throws FileNotFoundException{

         byte[] companyLogo=null,clientLogo=null;
         if(logosList.size()>0){

             Optional<Attachement> xx = logosList.stream()
                 .filter(logo -> logo.getType().equals(AttachementType.CompanyLogo))
                 .findFirst();
             if(xx.isPresent()){
                 companyLogo=  xx.get().getData();
             }

             Optional<Attachement> yy = logosList.stream()
                 .filter(logo -> logo.getType().equals(AttachementType.ClientLogo))
                 .findFirst();
             if(yy.isPresent()){
                 clientLogo=  yy.get().getData();
             }
         }


         //need to initialize
         ImageIcon imageIcon;

         if(clientLogo!=null){
             imageIcon = new ImageIcon(clientLogo);
         }else{
             imageIcon = new ImageIcon(ResourceUtils.getFile("classpath:reports/emptyLogo.png").getPath());
         }
         parameterMap.put("Company_Logo", imageIcon.getImage());

         if(clientLogo!=null){
             imageIcon = new ImageIcon(clientLogo);
         }else{
             imageIcon = new ImageIcon(ResourceUtils.getFile("classpath:reports/emptyLogo.png").getPath());
         }
         parameterMap.put("Client_Logo", imageIcon.getImage());

     }




     }


