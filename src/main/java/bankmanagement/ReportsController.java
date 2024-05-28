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

    ObservableList<String> tipoList = FXCollections.observableArrayList();
    ObservableList<String> principal = FXCollections.observableArrayList();
    ObservableList<String> parametrico = FXCollections.observableArrayList();
    ObservableList<String> esporadico = FXCollections.observableArrayList();
    ObservableList<String> vacio = FXCollections.observableArrayList();

    Connection conn = null;
    JasperReport jasperReport = null;

    String compiladoCargo = "src/main/resources/Reports/CargosReport.jasper";
    String compiladoContrato = "src/main/resources/Reports/ContratosReport.jasper";
    String compiladoEmpleado = "src/main/resources/Reports/EmployeeReport.jasper";
    String compiladoServicios = "src/main/resources/Reports/ServiciosReport.jasper";
    String compiladoSucursal = "src/main/resources/Reports/SucursalReport.jasper";
    String compiladoTransaccion = "src/main/resources/Reports/TransaccionReport.jasper";

    public void generarPDF(javafx.event.ActionEvent actionEvent) {
        FileChooser seleccionarArchivo = new FileChooser();
        seleccionarArchivo.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivo PDF (*.pdf)", "*.pdf"));
        File file = seleccionarArchivo.showSaveDialog(new Stage());
        String rutaGuardar = file.getPath();
        cargarCompilado(cargarRuta());
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
        tipoList.add("Principal");
        tipoList.add("Parametrico");
        tipoList.add("Esporadico");
        cmbNivel.setItems(tipoList);

        cmbNivel.setOnAction(event -> llenarCombos());
    }

    void llenarCombos() {
        String nivelSeleccionado = cmbNivel.getValue();
        if (nivelSeleccionado != null) {
            switch (nivelSeleccionado) {
                case "Principal":
                    principal.clear();
                    principal.addAll("Servicios", "Transacciones");
                    cmbReporte.setItems(principal);
                    break;
                case "Parametrico":
                    parametrico.clear();
                    parametrico.addAll("Empleados", "Sucursales");
                    cmbReporte.setItems(parametrico);
                    break;
                case "Esporadico":
                    esporadico.clear();
                    esporadico.addAll("Contratos", "Cargos");
                    cmbReporte.setItems(esporadico);
                    break;
                default:
                    vacio.clear();
                    vacio.add("Debe seleccionar un Nivel de Usuario");
                    cmbReporte.setItems(vacio);
            }
        }
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

    String cargarRuta(){
        String RUTACOMPILADA = null;
        if (cmbReporte.getValue()=="Servicios"){
            RUTACOMPILADA = new String(compiladoServicios);
        } if (cmbReporte.getValue()=="Transacciones"){
            RUTACOMPILADA = new String(compiladoTransaccion);
        } if (cmbReporte.getValue()=="Empleados"){
            RUTACOMPILADA = new String(compiladoEmpleado);
        } if (cmbReporte.getValue()=="Contratos"){
            RUTACOMPILADA = new String(compiladoContrato);
        } if (cmbReporte.getValue()=="Cargos"){
            RUTACOMPILADA = new String(compiladoCargo);
        } if (cmbReporte.getValue()=="Sucursales"){
            RUTACOMPILADA = new String(compiladoSucursal);
        }
        return RUTACOMPILADA;
    }

}