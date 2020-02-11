package Login;

import Admin.AdminController;
import Patients.PatientsController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    LoginModel loginModel = new LoginModel();
    @FXML
    private Label databaseStatus;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<Option> comboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (loginModel.isDatabaseConnected())
            this.databaseStatus.setText("Database is connected");
        else
            this.databaseStatus.setText("Sorry! Database is not connected, try again");
        this.comboBox.setItems(FXCollections.observableArrayList(Option.values()));
    }

    @FXML
    public void myLogin(ActionEvent event) {
        try{
           if (this.loginModel.checkLogin(this.userName.getText(),this.passwordField.getText(),((Option)this.comboBox.getValue()).toString())) {
               Stage stage = (Stage) this.loginButton.getScene().getWindow();
               stage.close();
               switch(((Option)this.comboBox.getValue()).toString()){
                   case "Admin":
                       admin();
                       break;
                   case"Patients":
                       patients();
                       break;
               }

           }
           else {
               this.loginStatus.setText("Wrong details, verify and try again!");
           }
        }
        catch (Exception localException){
            localException.printStackTrace();

        }

    }

    public void admin() {
        try{
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
            AdminController adminController = (AdminController)loader.getController();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.setTitle("Admin Dashboard");
            window.setResizable(false);
            window.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void patients() {

        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/Patients/Patients.fxml").openStream());
            PatientsController patientsController = (PatientsController) loader.getController();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.setTitle("Patient Dashboard");
            window.setResizable(false);
            window.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }
}