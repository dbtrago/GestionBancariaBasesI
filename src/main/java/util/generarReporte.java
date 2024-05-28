package util;

import DB.QueryDatabase;
import bankmanagement.AccountHolderSceneController;
import bankmanagement.model.Cliente;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class generarReporte {
    public static void main(String[] args) {

        // Configurar la conexión a la base de datos
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3360/bank?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            System.out.println("se conecto");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Cargar el archivo JasperReport compilado
        JasperReport jasperReport = null;
        try {
            File reportFile = new File("src/main/resources/Reports/TransaccionReport.jasper");
            jasperReport = (JasperReport) JRLoader.loadObject(reportFile);
        } catch (JRException e) {
            e.printStackTrace();
        }
/*
* src/main/resources/Reports/ContratosReport.jasper
* src/main/resources/Reports/CargosReport.jasper
* src/main/resources/Reports/EmployeeReport.jasper
* src/main/resources/Reports/ServiciosReport.jasper
* src/main/resources/Reports/SucursalReport.jasper
* src/main/resources/Reports/TransaccionReport.jasper
* */
        // Parámetros del informe
        Map<String, Object> parameters = new HashMap<>();

        // Generar el informe
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            // Exportar el informe a PDF
            String pdfFilePath = "src/main/java/util/EmployeeReport.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);
            System.out.println("Informe guardado en: " + pdfFilePath);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
