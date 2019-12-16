package sample;

import db.DBController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage mainStage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        DBController.getConnection();
        Parent root = FXMLLoader.load(getClass().getResource("../authentication/Authentication.fxml"));
        mainStage.getIcons().add(new Image("/icons/icon.png"));
        mainStage.setTitle("UFAS MedicalOffice");
        mainStage.setResizable(false);
        mainStage.setAlwaysOnTop(true);
        mainStage.setScene(new Scene(root, 340, 360));
        mainStage.show();
    }
}
