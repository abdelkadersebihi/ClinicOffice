package addMedicine;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMedicine implements Initializable {

    @FXML
    JFXButton addMedicineButton, resetButton;

    @FXML
    JFXTextField medicineNameTextField, medicineDoseTextField;

    @FXML
    Label errorLabel;

    @FXML
    JFXComboBox formComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setVisible(false);
        formComboBox.getItems().addAll(
                "AERO",
                "AERO NAS",
                "AMP BUV",
                "BAUME",
                "CAPS MOLLES",
                "CAPS ORALE",
                "COLLU",
                "COLLY",
                "COLLY EN SOL",
                "COMP",
                "COMP ENRO",
                "CREME",
                "DRG",
                "GEL",
                "GELL",
                "GRLES",
                "LOTION",
                "LYOPH",
                "MICROGRANULES",
                "OVULE",
                "PATCH",
                "PDE",
                "PDRE",
                "SHAMPOING",
                "SIROP",
                "SUSP INJ",
                "SUSP BUV",
                "SUSPPO",
                "SPRAY NASAL",
                "SOL"
        );
    }

    public void handleClick(ActionEvent event) {
        if (event.getSource().equals(resetButton)) {
            medicineNameTextField.setText(null);
            medicineDoseTextField.setText(null);
            formComboBox.setValue(null);
        } else if (event.getSource().equals(addMedicineButton)) {
            if (medicineNameTextField.getText().trim().isEmpty()|| medicineNameTextField.getText().isEmpty() || medicineDoseTextField.getText().isEmpty() || medicineDoseTextField.getText().trim().isEmpty() || formComboBox.getValue() == null) {
                errorLabel.setVisible(true);
                return;
            }
            errorLabel.setVisible(false);
            DBController.insertMed(medicineNameTextField.getText(), medicineDoseTextField.getText(), formComboBox.getValue().toString());
            ((Node) (event.getSource())).getScene().getWindow().hide();

        }
    }
}
