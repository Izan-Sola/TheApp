package me.shinyshadow_.japp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Scene answersScene;
    public static Stage answersStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 460, 320);
        stage.setY(205);
        stage.setX(700);
        stage.setTitle("The App");
        stage.setScene(scene);
        stage.show();
        stage.toFront();

        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("answer-buttons.fxml"));
        answersScene = new Scene(fxmlLoader1.load(), 380, 35);
        answersStage = new Stage();
        answersStage.setY(605);
        answersStage.setX(750);
        answersStage.setTitle("You");
        answersStage.setScene(answersScene);
        answersStage.initStyle(StageStyle.TRANSPARENT);
        answersScene.setFill(Color.TRANSPARENT);


    }

    public static void main(String[] args) {
        launch();
    }


}