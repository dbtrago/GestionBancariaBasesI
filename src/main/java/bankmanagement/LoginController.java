package bankmanagement;

        import DB.DBConnection;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.image.Image;
        import javafx.stage.Stage;
        import java.io.IOException;
        import java.net.URL;
        import java.sql.*;
        import java.util.ResourceBundle;

public class LoginController {
    String passParametrico  = "parametrico";
    String passEsporadico = "esporadico";

    String user;
    String pass;

    @FXML
    private ComboBox<String> UserCombo;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtContra;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnx;

    private BankManagement bankManagement;

    private Alert alert;

    private DialogPane dialog;

    private static LoginController loginController;

    @FXML
    void abrirDashboard(ActionEvent event) throws IOException {
        if (recibirDatos()) {
            if (confirmarIngreso()) {
                bankManagement.Dashboard();
            } else {
                mensaje("¡Datos incorrectos!", "El tipo de usuario no coincide con la contraseña ingresada.", "error");
                limpiar();
            }
        }

    }

    private boolean confirmarIngreso (){
        if (user.equals("Principal") && pass.equals(consultaPass("principal"))){
            mensaje("¡Acceso confirmado!", "Bienvenido al sistema, ha iniciado sesión como Principal", "confirmacion");
            return true;
        }
        if (user.equals("Parametrico") && pass.equals(consultaPass("parametrico"))){
            mensaje("¡Acceso confirmado!", "Bienvenido al sistema, ha iniciado sesión como Parametrico", "confirmacion");
            return true;
        }
        if (user.equals("Esporadico") && pass.equals(consultaPass("esporadico"))){
            mensaje("¡Acceso confirmado!", "Bienvenido al sistema, ha iniciado sesión como Esporadico", "confirmacion");
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void initialize() {
        this.bankManagement = BankManagement.getInstance();
        ObservableList<String> genderList = FXCollections.observableArrayList();
        genderList.add("Principal");
        genderList.add("Parametrico");
        genderList.add("Esporadico");
        UserCombo.setItems(genderList);
    }

    public boolean recibirDatos(){
        pass = txtContra.getText();
        user = UserCombo.getValue();
        if (pass != null && user != null){
            return true;
        }
        mensaje("¡Advertencia!", "Campos vacíos", "advertencia");
        return false;
    }

    public String consultaPass (String usuarioBuscado){

        String passEncontrada = null;

        // Establecer la conexión con la base de datos
        try (Connection connection = DBConnection.getConnection()) {
            // Crear la sentencia SQL
            String sql = "SELECT clave FROM login WHERE usuario = ?";

            // Preparar la sentencia
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuarioBuscado);

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Obtener la contraseña del resultado
                        passEncontrada = resultSet.getString("clave");

                        // Mostrar la contraseña por consola
                        System.out.println("La contraseña para el usuario " + usuarioBuscado + " es: " + passEncontrada);
                    } else {
                        System.out.println("No se encontró ningún usuario con el nombre: " + usuarioBuscado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passEncontrada;
    }

    void limpiar (){txtContra.setText(null);}

    public static LoginController getInstance() {
        if(loginController == null){
            loginController = new LoginController();
        }
        return loginController;
    }
    public void mensaje (String titulo, String contenido, String Tipo){


        if (Tipo.equals("confirmacion")){
            alert = new Alert(Alert.AlertType.CONFIRMATION, contenido, ButtonType.OK);
        }
        if (Tipo.equals("error")){
            alert = new Alert(Alert.AlertType.ERROR, contenido, ButtonType.OK);
        }
        if (Tipo.equals("advertencia")){
            alert = new Alert(Alert.AlertType.WARNING, contenido, ButtonType.OK);
        }
        if (Tipo.equals("informacion")){
            alert = new Alert(Alert.AlertType.INFORMATION, contenido, ButtonType.OK);
        }

        alert.setHeaderText(titulo);
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("/Styles/style.css").toString());
        dialog.getStyleClass().add("dialog");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/Images/icon.png"));
        alert.showAndWait();
    }
}
