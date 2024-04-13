package fi.metropolia.mongodblocalization.view;

import fi.metropolia.mongodblocalization.controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        fxmlLoader.setController(new HelloController(this));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void openPopup(String labelMessage) {
        // Create a new stage for the popup
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Popup");

        // Create the layout for the popup
        VBox popupLayout = new VBox();
        popupLayout.setSpacing(10);
        popupLayout.setAlignment(javafx.geometry.Pos.CENTER);
        Label messageLabel = new Label(labelMessage);
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> popupStage.close());

        // Add components to the layout
        popupLayout.getChildren().addAll(messageLabel, okButton);

        // Set up the scene and show the popup
        Scene popupScene = new Scene(popupLayout, 200, 100);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}