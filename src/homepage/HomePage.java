package homepage;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import db.DBController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import models.Medicine;
import models.Patient;
import prescriptionPdf.PrescriptionPDF;

import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    public TableView<Patient> patientTableView;
    public ObservableList<Patient> patientObservableList;
    @FXML
    public TableView<Medicine> medicineTableView;
    public ObservableList<Medicine> medicineObservableList;
    @FXML
    Stage addPatientStage, addMedicineStage, addPrescriptionStage;
    @FXML
    private JFXButton quickAccessMenuButton, patientMenuButton, medicineMenuButton, quickAddPatientButton, quickAddMedicineButton, quickAddPrescriptionButton, addPatientButton, addMedicineButton, addPrescriptionButton, patientRefresh, medicineRefresh;
    @FXML
    private BorderPane quickAccessPane, patientPane, medicinePane;
    @FXML
    private Label timeLabel, dateLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        patientTableView.setEditable(true);
        medicineTableView.setEditable(true);

        getData(DBController.getPatients(), patientTableView, patientObservableList);
        getData(DBController.getMedicines(), medicineTableView, medicineObservableList);

        initTime();
        initDate();

        uncheckAll();
        hideAll();

        check(quickAccessMenuButton);
        quickAccessPane.setVisible(true);
    }

    public void handleClick(ActionEvent event) throws Exception {
        if (event.getSource().equals(quickAccessMenuButton)) {
            uncheckAll();
            hideAll();
            check(quickAccessMenuButton);
            quickAccessPane.setVisible(true);
        } else if (event.getSource().equals(patientMenuButton)) {
            uncheckAll();
            hideAll();
            check(patientMenuButton);
            patientPane.setVisible(true);
        } else if (event.getSource().equals(medicineMenuButton)) {
            uncheckAll();
            hideAll();
            check(medicineMenuButton);
            medicinePane.setVisible(true);
        } else if (event.getSource().equals(quickAddPatientButton) || event.getSource().equals(addPatientButton)) {
            addPatient();
           // PrescriptionPDF.generatePdf("salah", "moussaoui", "14/12/2019");
            //PrescriptionPDF.generatePdf("kader", "sebihi", "14/12/2019");
        } else if (event.getSource().equals(quickAddMedicineButton) || event.getSource().equals(addMedicineButton)) {
            addMedicine();
        } else if (event.getSource().equals(quickAddPrescriptionButton) || event.getSource().equals(addPrescriptionButton)) {
            addPrescription();
        } else if (event.getSource().equals(patientRefresh)) {
            getData(DBController.getPatients(), patientTableView, patientObservableList);
        } else if (event.getSource().equals(medicineRefresh)) {
            getData(DBController.getMedicines(), medicineTableView, medicineObservableList);
        }
    }

    private void addPatient() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../addPatient/AddPatient.fxml"));
        addPatientStage = new Stage();
        addPatientStage.setTitle("UFAS MedicalOffice");
        addPatientStage.initStyle(StageStyle.UTILITY);
        addPatientStage.initModality(Modality.APPLICATION_MODAL);
        addPatientStage.setScene(new Scene(root, 360, 465));
        addPatientStage.show();
    }

    private void addMedicine() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../addMedicine/AddMedicine.fxml"));
        addMedicineStage = new Stage();
        addMedicineStage.setTitle("UFAS MedicalOffice");
        addMedicineStage.initStyle(StageStyle.UTILITY);
        addMedicineStage.initModality(Modality.APPLICATION_MODAL);
        addMedicineStage.setScene(new Scene(root, 320, 410));
        addMedicineStage.show();
    }

    private void addPrescription() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../addPrescription/AddPrescription.fxml"));
        addPrescriptionStage = new Stage();
        addPrescriptionStage.setTitle("UFAS MedicalOffice");
        addPrescriptionStage.initStyle(StageStyle.UTILITY);
        addPrescriptionStage.initModality(Modality.APPLICATION_MODAL);
        addPrescriptionStage.setScene(new Scene(root, 460, 680));
        addPrescriptionStage.show();
    }

    private void initDate() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateLabel.setFont(new Font(dateLabel.getFont().getName(), 48));
            dateLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void initTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime currentTime = LocalTime.now();
            String var;
            if (currentTime.getHour() >= 12) {
                var = "PM";
            } else {
                var = "AM";
            }
            timeLabel.setFont(new Font(timeLabel.getFont().getName(), 54));
            timeLabel.setText(LocalTime.now().format(formatter) + " " + var);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void hideAll() {
        quickAccessPane.setVisible(false);
        patientPane.setVisible(false);
        medicinePane.setVisible(false);
    }

    private void check(JFXButton button) {
        button.setStyle("-fx-background-color: #079992 ; -fx-background-radius: 0;");
        button.setButtonType(JFXButton.ButtonType.FLAT);
    }


    private void uncheckAll() {
        quickAccessMenuButton.setStyle(null);
        quickAccessMenuButton.setRipplerFill(Color.valueOf("565c5e"));
        quickAccessMenuButton.setButtonType(JFXButton.ButtonType.FLAT);
        quickAccessMenuButton.setDisableVisualFocus(true);

        medicineMenuButton.setStyle(null);
        medicineMenuButton.setRipplerFill(Color.valueOf("565c5e"));
        medicineMenuButton.setButtonType(JFXButton.ButtonType.FLAT);
        quickAccessMenuButton.setDisableVisualFocus(true);
        quickAccessMenuButton.setDisableVisualFocus(true);


        patientMenuButton.setStyle(null);
        patientMenuButton.setRipplerFill(Color.valueOf("565c5e"));
        patientMenuButton.setButtonType(JFXButton.ButtonType.FLAT);
        quickAccessMenuButton.setDisableVisualFocus(true);


        quickAddPatientButton.setDisableVisualFocus(true);
        quickAddPatientButton.setRipplerFill(Color.valueOf("565c5e"));

        quickAddMedicineButton.setDisableVisualFocus(true);
        quickAddMedicineButton.setRipplerFill(Color.valueOf("565c5e"));

        quickAddPrescriptionButton.setDisableVisualFocus(true);
        quickAddPrescriptionButton.setRipplerFill(Color.valueOf("565c5e"));

        addPatientButton.setDisableVisualFocus(true);
        addMedicineButton.setDisableVisualFocus(true);
        addPrescriptionButton.setDisableVisualFocus(true);
        patientRefresh.setDisableVisualFocus(true);
    }


    public void getData(ResultSet resultSet, TableView tableView, ObservableList data) {
        data = FXCollections.observableArrayList();
        data.removeAll(data);
        tableView.getColumns().removeAll(tableView.getColumns());
        try {
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableView.getColumns().addAll(col);
            }
            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
            tableView.setItems(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
