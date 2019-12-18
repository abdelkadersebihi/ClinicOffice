package addPatient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import db.DBController;
import homepage.HomePage;

public class AddPatient implements Initializable {

    @FXML
    JFXComboBox patientDepartmentComboBox;

    @FXML
    JFXButton addPatientButton,resetButton;

    @FXML
    JFXTextField patientNameTextField,patientFamilyNameTextField;

    @FXML
    JFXDatePicker patientBirthdayDatePicker;

    @FXML
    Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientBirthdayDatePicker.setValue(LocalDate.of(2001,1,1));
        errorLabel.setVisible(false);
        patientDepartmentComboBox.getItems().addAll("Sciences","Médcine","Biologie","Réctorat","Gestion et Economie","Architechture et Sciences de Terre");
    }

    public void handleClick(ActionEvent event) {
        if(event.getSource().equals(resetButton)){
            patientNameTextField.setText(null);
            patientFamilyNameTextField.setText(null);
            patientBirthdayDatePicker.setValue(null);
            patientDepartmentComboBox.setValue(null);
        }else if(event.getSource().equals(addPatientButton)){
            if(patientNameTextField.getText().isEmpty()  || patientNameTextField.getText().trim().isEmpty() || patientFamilyNameTextField.getText().isEmpty() || patientFamilyNameTextField.getText().trim().isEmpty()|| patientDepartmentComboBox.getValue()==null || patientBirthdayDatePicker.getValue()== null){
                errorLabel.setVisible(true);
                return;
            }
            errorLabel.setVisible(false);
            DBController.insertPatient(patientNameTextField.getText().toUpperCase(),patientFamilyNameTextField.getText().toUpperCase(),patientBirthdayDatePicker.getValue().toString(),patientDepartmentComboBox.getValue().toString());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../homepage/HomePage.fxml"));
            try {
                Parent root = loader.load();
            }catch (Exception e){
            }
            HomePage home= (HomePage)loader.getController();
            home.getData(DBController.getPatients(),home.patientTableView,home.patientObservableList);
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }
}


