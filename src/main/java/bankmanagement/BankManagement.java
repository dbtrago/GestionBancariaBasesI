/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Daniel Buitrago
 */
public class BankManagement extends javafx.application.Application {

    private Stage primaryStage;

    private static BankManagement bankManagement;
    public static BankManagement getInstance() {
        if(bankManagement == null){
            bankManagement = new BankManagement();
        }
        return bankManagement;
    }

    @Override
    public void start(Stage stage) throws Exception {
        bankManagement=this;
        this.primaryStage = stage;
        Login(stage);
    }

    public void Login(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Login.fxml")));
        Scene scene = new Scene(pane);
        stage.getIcons().add(new Image("/Images/icon.png"));
        stage.setScene(scene);
        stage.show();
    }

    public void Dashboard() throws IOException {
        Pane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/BankMain.fxml")));
        Scene scene = new Scene(pane);
        primaryStage.getIcons().add(new Image("/Images/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
