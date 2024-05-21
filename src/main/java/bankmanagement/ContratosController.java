package bankmanagement;

import DB.DisplayDatabase;
import DB.QueryDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContratosController implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private TableView<?> eTableView;

    @FXML
    private TextField txtCedulaBuscar;

    ObservableList<String> branchList = FXCollections.observableArrayList();
    DisplayDatabase empData = new DisplayDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = QueryDatabase.query("Select * from contratos;");
        if(rs!=null){
            try {
                while(rs.next()){
                    branchList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(BranchEmployeeSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        empData.buildData(eTableView, "Select * from employeetable;");
    }
    @FXML
    private void searchEmployee(ActionEvent event) {

        String query="";
        String name = txtCedulaBuscar.getText();
        if(name!=null && !name.isEmpty()){
            query = "Select * from employeetable where cedula Like '%"+name+"%';";

        }else{
            query = "Select * from employeetable;";
        }

        empData.buildData(eTableView,query);
    }
}
