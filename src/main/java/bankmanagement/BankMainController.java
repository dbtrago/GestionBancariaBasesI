/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Daniel Buitrago
 */
public class BankMainController {
    
    private Label label;
    @FXML
    private ToggleButton acntHoldSceneBtn;
    @FXML
    private ToggleGroup group1;
    @FXML
    private ToggleButton brchEmpSceneBtn;
    @FXML
    private ToggleButton tranSceneBtn;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private BorderPane rootLayout;
    @FXML
    private ToggleButton seviceBtn;
    @FXML
    private ToggleGroup group11;
    @FXML
    private ToggleButton btnSalir;
    private BankManagement bankManagement;
    private LoginController loginController;

    public void initialize() {
        this.bankManagement = BankManagement.getInstance();
        this.loginController = LoginController.getInstance();
         changeScene("/views/AccountHolderScene.fxml");
    }    

    @FXML
    private void setAcntHoldScene(ActionEvent event) {
         changeScene("/views/AccountHolderScene.fxml");
    }

    @FXML
    private void setBrchEmpScene(ActionEvent event) {
         changeScene("/views/BranchEmployeeScene.fxml");
    }

    @FXML
    private void setTranScene(ActionEvent event) {
         changeScene("/views/TransactoinScene.fxml");
    }

    @FXML
    void setActionSalir(ActionEvent event) throws IOException {
        loginController.mensaje("Cerra sesión", "Su sesión ha sido cerrada", "confirmacion");
        bankManagement.Login();
    }
    
     public  void changeScene(String scenePath){
        try{
            Pane pane =FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenePath)));
            rootLayout.setCenter(pane);
        }
        catch(Exception ignored){
        }
    }

    @FXML
    private void setServiceScene(ActionEvent event) {
         changeScene("/views/ServiceScene.fxml");
        
    }
    
}
