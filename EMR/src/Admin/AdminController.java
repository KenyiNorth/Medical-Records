package Admin;

import Login.Database;
import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private  TextField firstname;
    @FXML
    private TextField lastname;
    @FXML TextField sex;
    @FXML
    private  TextField email;
    @FXML
    private DatePicker dob;

    @FXML
    private TableView<PatientsData> patientsDataTableView;

    @FXML
    private TableColumn<PatientsData, String> idColumn;
    @FXML
    private TableColumn<PatientsData, String> firstNameColumn;
    @FXML
    private TableColumn<PatientsData, String> lastNameColumn;
    @FXML
    private TableColumn<PatientsData, String> SexColumn;

    @FXML
    private TableColumn<PatientsData, String> emailColumn;
    @FXML
    private TableColumn<PatientsData, String> dobColumn;

    private Database mydatabase;
    private ObservableList<PatientsData> data;
    private String sql = "SELECT * FROM patients";



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mydatabase = new Database();

    }
    @FXML
    private  void loadPatientData(ActionEvent event){
        try{
            Connection conn =  mydatabase.connectToMyDatabase();
            this.data = FXCollections.observableArrayList();

            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM patients");
            while (resultSet.next()){
                this.data.add(new PatientsData(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
            }
        }
        catch (SQLException ex){
            System.err.println("Erro"+ex);

        }
        this.idColumn.setCellValueFactory(new PropertyValueFactory<PatientsData,String>("ID"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<PatientsData,String>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<PatientsData,String>("lastName"));
        this.SexColumn.setCellValueFactory(new PropertyValueFactory<PatientsData,String>("Sex"));

        this.emailColumn.setCellValueFactory(new PropertyValueFactory<PatientsData,String>("email"));
        this.dobColumn.setCellValueFactory(new PropertyValueFactory<PatientsData,String>("DOB"));

        this.patientsDataTableView.setItems(null);
        this.patientsDataTableView.setItems(this.data );

    }
    @FXML
    private void addPatient(ActionEvent event){
        Database database = new Database();
        String sqlInsert = "INSERT INTO patients (id, fname, lname, sex, email, dob) VALUES (?,?,?,?,?,?)";
        try{
           Connection conn = database.connectToMyDatabase();
           PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);

           preparedStatement.setString(1,id.getText());
           preparedStatement.setString(2,firstname.getText());
           preparedStatement.setString(3, lastname.getText());
           preparedStatement.setString(4,sex.getText());
           preparedStatement.setString(5, email.getText());
           preparedStatement.setString(6,dob.getEditor().getText());

           preparedStatement.execute();
           preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void clearForm(ActionEvent event){
        this.id.setText("");
        this.firstname.setText("");
        this.lastname.setText("");
        this.sex.setText("");
        this.email.setText("");
        this.dob.setValue(null);
    }
}
