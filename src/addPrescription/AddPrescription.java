package addPrescription;

import com.jfoenix.controls.*;
import db.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.pdfbox.pdmodel.PDDocument;
import prescriptionPdf.PrescriptionPDF;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddPrescription implements Initializable, EventHandler<KeyEvent> {

    @FXML
    private JFXTextField additionalInfos;
    @FXML
    private JFXTextArea medicinesInPrescriptionTextArea;
    @FXML
    private JFXComboBox patientComboBox,medicineComboBox,qsp,qte;
    @FXML
    private JFXCheckBox qspCheckbox;
    @FXML
    private JFXButton addMedicineInPrescriptionButton, resetButton, addPrescriptionButton;
    @FXML
    private Label error,errorMed;
    private ObservableList<String> patientData,medicineData;
    private boolean moveCaretToPos = false;
    private int caretPos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        qsp.setEditable(false);
        qte.setEditable(false);

        qspCheckbox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(qspCheckbox.isSelected()){
                    qsp.setDisable(false);
                    qte.setDisable(true);
                }else{
                    qsp.setDisable(true);
                    qte.setDisable(false);
                }
            }
        });
        qsp.setDisable(true);
        qsp.getItems().addAll(
                "5 jours",
                "10 jours",
                "1 mois",
                "3 mois"
        );

        qte.getItems().addAll(
                "1",
                "2",
                "3",
                "4",
                "5"
        );
        try {
            ResultSet resultSet = DBController.getPatients();
            while (resultSet.next()) {
                patientComboBox.getItems().add(resultSet.getString("Nom") + " " + resultSet.getString("Prenom")+ " " + resultSet.getString("DateNaissance"));
            }
            resultSet=DBController.getMedicines();
            while (resultSet.next()){
                medicineComboBox.getItems().add(resultSet.getString("Medicament")+" "+resultSet.getString("Dose")+" "+resultSet.getString("Forme"));
            }
        } catch (Exception e) {
        }
        patientData = patientComboBox.getItems();
        medicineData=medicineComboBox.getItems();

        this.patientComboBox.setEditable(true);
        this.patientComboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                patientComboBox.hide();
            }
        });
        this.patientComboBox.setOnKeyReleased(this);


        this.medicineComboBox.setEditable(true);
        this.medicineComboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                medicineComboBox.hide();
            }
        });
        this.medicineComboBox.setOnKeyReleased(this);


        medicinesInPrescriptionTextArea.setEditable(false);
        error.setVisible(false);
        errorMed.setVisible(false);

    }

    public void handleClick(ActionEvent event) throws Exception {
        if (event.getSource().equals(resetButton)) {
            medicinesInPrescriptionTextArea.setText(null);
            patientComboBox.setValue(null);
            medicineComboBox.setValue(null);
            qsp.setValue(null);
            qte.setValue(null);
            additionalInfos.setText("");
        } else if (event.getSource().equals(addPrescriptionButton)) {
            if (patientComboBox.getValue() == null || patientComboBox.getValue().toString().isBlank() || medicinesInPrescriptionTextArea.getText().isEmpty() ) {
                error.setVisible(true);
                return;
            }
            error.setVisible(false);
            PrescriptionPDF.generatePdf(patientComboBox.getValue().toString(), medicinesInPrescriptionTextArea.getText());
            ((Node) (event.getSource())).getScene().getWindow().hide();


        }else if(event.getSource().equals(addMedicineInPrescriptionButton)){
            if (qspCheckbox.isSelected()){
                if (medicineComboBox.getValue()==null ||medicineComboBox.getValue().toString().trim().isEmpty() || qsp.getValue() == null){
                    errorMed.setVisible(true);
                    return;
                }
                errorMed.setVisible(false);
                medicinesInPrescriptionTextArea.setText(medicinesInPrescriptionTextArea.getText()+"\n"+medicineComboBox.getValue().toString()+"qsp"+qsp.getValue().toString()+" \n"+additionalInfos.getText()+" \n");
            }else {
                if (medicineComboBox.getValue()==null ||medicineComboBox.getValue().toString().trim().isEmpty()  || qte.getValue() == null){
                    errorMed.setVisible(true);
                    return;
                }
                errorMed.setVisible(false);
                medicinesInPrescriptionTextArea.setText(medicinesInPrescriptionTextArea.getText()+"\n"+medicineComboBox.getValue().toString()+" "+qte.getValue().toString()+" "+"boit(s)"+" \n"+additionalInfos.getText()+" \n");
            }
            medicineComboBox.setValue(null);
            additionalInfos.setText("");
            qte.setValue(null);
            qsp.setValue(null);
        }
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getSource().equals(patientComboBox)){
            search(event,patientComboBox,patientData);
        }else if (event.getSource().equals(medicineComboBox)){
            search(event,medicineComboBox,medicineData);
        }
    }



    public void search(KeyEvent event,JFXComboBox comboBox,ObservableList<String> data){
        if(event.getCode() == KeyCode.UP) {
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length(),comboBox);
            return;
        } else if(event.getCode() == KeyCode.DOWN) {
            if(!comboBox.isShowing()) {
                comboBox.show();
            }
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length(),comboBox);
            return;
        } else if(event.getCode() == KeyCode.BACK_SPACE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        } else if(event.getCode() == KeyCode.DELETE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        }

        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.isControlDown() || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
            return;
        }
        if (event.getCode()==KeyCode.ENTER){
            comboBox.hide();
            return;
        }

        ObservableList list = FXCollections.observableArrayList();
        for (int i=0; i<data.size(); i++) {
            if(data.get(i).toString().toLowerCase().startsWith(
                    comboBox
                            .getEditor().getText().toLowerCase())) {
                list.add(data.get(i));
            }
        }
        String t = comboBox.getEditor().getText();

        comboBox.setItems(list);
        comboBox.getEditor().setText(t);
        if(!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(t.length(),comboBox);
        if(!list.isEmpty()) {
            comboBox.show();
        }

    }


    private void moveCaret(int textLength,JFXComboBox comboBox) {
        if (caretPos == -1) {
            comboBox.getEditor().positionCaret(textLength);
        } else {
            comboBox.getEditor().positionCaret(caretPos);
        }
        moveCaretToPos = false;
    }
}
