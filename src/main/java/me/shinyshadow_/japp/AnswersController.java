package me.shinyshadow_.japp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private int sequenceNumber = ButtonController.sequenceNumber;

    @FXML
    protected void onAnswerChosen(ActionEvent event) {
        sequenceNumber = ButtonController.sequenceNumber;
        ansStage = TheApplication.answersStage;
        ansStage.hide();
        Button clicked = (Button) event.getSource();
        String answer = clicked.getText();
        System.out.println(sequenceNumber);
        if(sequenceNumber == 1) {

            switch (answer) {
                case "I downloaded it" -> playAnswerDialogue(ans1Dialogue);
                case "Someone shared it" -> playAnswerDialogue(ans2Dialogue);
                case "It just appeared" -> playAnswerDialogue(ans3Dialogue);
            }
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {}
                ButtonController.count = true;
            }).start();

        }
    }

    protected void playAnswerDialogue(List<String> dialogue) {

        Stage mainStage = ButtonController.stage;
        Timeline answerDialogueTimeline = new Timeline();

            for (int i = 0; i < dialogue.size(); i++) {
                int index = i;
                answerDialogueTimeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(i * 2),
                                ae -> mainStage.setTitle(dialogue.get(index))));
        }
        answerDialogueTimeline.play();
    }

}