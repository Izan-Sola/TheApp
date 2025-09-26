package me.shinyshadow_.apptest.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import me.shinyshadow_.apptest.demo.HelloApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloController {




    int clickCount = 0;
    private List<String> startTitleMessages = Arrays.asList(
            "How...",
            "How did you find this app?"
    );

    @FXML private TextField textField;
    @FXML private Label welcomeText;
    @FXML private Button TheButton;
    @FXML private HBox optionList;
    KeyCode pressedKey = KeyCode.ENTER;
    Stage stage;
    Window window;
    Scene scene;
    int y = 0;
    int x = 0;
    int msgIndex = 0;
    boolean count = false;
    boolean test = true;
    @FXML


    protected void onHelloButtonClick() throws InterruptedException {
        scene = welcomeText.getScene();
        window = scene.getWindow();
        stage = (Stage) scene.getWindow();



        textField.setOnKeyPressed(event -> {

            this.pressedKey = event.getCode();

            if(event.getCode().equals(KeyCode.ENTER) && textField.getOpacity() == 1) {

            }
        });

        startSecuence();

        if(count) clickCount+=1;
        else return;


        if(TheButton.getOpacity() > 0.01)
            TheButton.setOpacity(TheButton.getOpacity()-0.05);

        switch(clickCount) {
            case 30 -> stage.setTitle("Stop it.");
            case 45 -> stage.setTitle("Have you considered I don't you to click it.") ;
            case 55 -> stage.setTitle("I made it invisible for a reason.");
            case 65 -> stage.setTitle("...");
            case 70 -> stage.setTitle("........");
            case 75 -> stage.setTitle("..........................");
        }

        if(clickCount > 75) {
            y+=1;
            window.setY(window.getY()+4+y);
       }
    }

    protected void startSecuence() throws InterruptedException {


        if (test) {
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1000),
                    ae -> stage.setTitle(startTitleMessages.get(0))),
            new KeyFrame(
                    Duration.millis(2000),
                    ae -> {
                        stage.setTitle(startTitleMessages.get(1));
                        optionList.setOpacity(1);
                    }

            ));


            timeline.play();








        }

    }
}