package me.shinyshadow_.japp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import me.shinyshadow_.japp.HelloApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswersController {
    List<String> ans1Dialogue = List.of("You downloaded it?", "From where?", "What sketchy sites do you browse?",
                                        "Whatever... I don't even wanna imagine...", "Just, leave me alone. Close me.", "Actually, just uninstall me.",
                                        "I just want to be in peace.", "The App");
    List<String> ans2Dialogue = List.of("Someone shared it to you?", "How is it possible...", "I thought I'd be lostware by now", "Anyways, doesn't matter.",
                                        "Do me a favor and uninstall me.", "I just want to be in peace.", "The App");
    List<String> ans3Dialogue = List.of("That's nonsense...'", "Are you mocking me?", "If not, you should check your pc for viruses.",
                                        "Whatever it is...", "Do me a favor and uninstall me.", "Let me be in peace.", "The App");

    private Stage ansStage;
    private int sequenceNumber = HelloController.sequenceNumber;

    @FXML
    protected void onAnswerChosen(ActionEvent event) {
        sequenceNumber = HelloController.sequenceNumber;
        ansStage = HelloApplication.answersStage;
        ansStage.hide();
        Button clicked = (Button) event.getSource();
        String answer = clicked.getText();
        System.out.println(sequenceNumber);
        if(sequenceNumber == 1) {

            switch (answer) {
                case "I downloaded it" -> playDialogue(ans1Dialogue);
                case "Someone shared it" -> playDialogue(ans2Dialogue);
                case "It just appeared" -> playDialogue(ans3Dialogue);
            }
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {}
                HelloController.count = true;
            }).start();

        }
    }

    protected void playDialogue(List<String> dialogue) {

        Stage mainStage = HelloController.stage;
        Timeline answerDialogueTimeline = new Timeline();

            for (int i = 0; i < dialogue.size(); i++) {
                int index = i;
                answerDialogueTimeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(i * 2),
                                ae -> {
                                         mainStage.setTitle(dialogue.get(index));

                                }));
        }
        answerDialogueTimeline.play();
    }

}