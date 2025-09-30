package me.shinyshadow_.japp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TheApplication extends Application {
    public static Scene answersScene;
    public static Stage answersStage;

    public static Scene barrierScene;
    public static Stage barrierStage;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(TheApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 460, 320);
        stage.setY(205);
        stage.setX(700);
        stage.setTitle("The App");
        stage.setScene(scene);
        stage.show();
        stage.toFront();

        FXMLLoader fxmlLoader1 = new FXMLLoader(TheApplication.class.getResource("answer-buttons.fxml"));
        answersScene = new Scene(fxmlLoader1.load(), 380, 35);
        answersStage = new Stage();
        answersStage.setY(605);
        answersStage.setX(750);
        answersStage.setTitle("You");
        answersStage.setScene(answersScene);
        answersStage.initStyle(StageStyle.TRANSPARENT);
        answersScene.setFill(Color.TRANSPARENT);
        answersStage.toFront();

        FXMLLoader fxmlLoader2 = new FXMLLoader(TheApplication.class.getResource("barrier.fxml"));
        Scene barrierScene = new Scene(fxmlLoader2.load(), 260, 180);
        barrierStage = new Stage();
        barrierStage.setY(205);
        barrierStage.setX(700);
        barrierStage.setTitle("The App");
        barrierStage.setScene(barrierScene);
        barrierStage.initStyle(StageStyle.UNDECORATED);
        barrierStage.setOpacity(0.75);
        barrierStage.setResizable(false);
        barrierStage.setAlwaysOnTop(true);
        barrierStage.toFront();



    }

    public static void main(String[] args) {
        launch();
    }


}