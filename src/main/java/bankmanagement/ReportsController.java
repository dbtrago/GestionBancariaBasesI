package bankmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReportsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbNivel;

    @FXML
    private ComboBox<String> cmbReporte;

    @FXML
    private Button brnGenerar;

    Connection conn = null;
    JasperReport jasperReport = null;

    public void generarPDF(javafx.event.ActionEvent actionEvent) {
        FileChooser seleccionarArchivo = new FileChooser();
        seleccionarArchivo.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivo PDF (*.pdf)", "*.pdf"));
        File file = seleccionarArchivo.showSaveDialog(new Stage());
        String rutaGuardar = file.getPath();
        cargarCompilado("src/main/java/util/EmployeeReport.jasper");
        generarReporte(rutaGuardar);
    }

    @FXML
    void initialize() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3360/bank?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            System.out.println("se conecto");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        * Tipos de reportes
        * PARAMETRICO
        * -empleado
        * -sucursal
        *PRINCIPAL
        * -servicios
        * -transacción
        *ESPORADICO
        * -contratos
        * -cargos
        * */
        ObservableList<String> genderList = FXCollections.observableArrayList();
        genderList.add("Principal");
        genderList.add("Parametrico");
        genderList.add("Esporadico");
        cmbNivel.setItems(genderList);
        ObservableList<String> List = FXCollections.observableArrayList();
        List.add("1");
        List.add("2");
        List.add("3");
        cmbReporte.setItems(List);
    }

    void cargarCompilado(String RUTA){
        try {
            File reportFile = new File(RUTA);
            jasperReport = (JasperReport) JRLoader.loadObject(reportFile);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    void generarReporte (String RUTA){
        // Parámetros del informe
        Map<String, Object> parameters = new HashMap<>();

        // Generar el informe
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            // Exportar el informe a PDF
            String pdfFilePath = RUTA;
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);
            System.out.println("Informe guardado en: " + pdfFilePath);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

}