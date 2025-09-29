package me.shinyshadow_.japp;

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

import java.util.Arrays;
import java.util.List;

public class HelloController {

    private enum talkers {
        TheApp,
        TheButton
    }

    public static int sequenceNumber = 0;
    int clickCount = 0;
    private List<String> seq0Dialogue = Arrays.asList(
            "How...",
            "How did you find this app?"
    );
    private List<String> seq1Dialogue = Arrays.asList("See!?", "You made it angry.", "I told you to stop.",
                                                      "Why don't you leave us alone?", "Let us be in peace.", "The App");
    private List<String> seq1Dialogue1 = Arrays.asList("Stop it!", "Not fun.", "Annoying.", "Leave.",
                                                       "Just want peace.", "Solitude", "The Button");
    private List<String> seq2Dialogue = Arrays.asList("Why do you keep going?", "What's so fun about it?", "It wants you to stop.",
                                                      "Go waste your time somewhere else!", "...");
    private List<String> seq2Dialogue1 = Arrays.asList("Stop", "stOp", "sToP", "s t o p", "stop...");

    private List<String> seq3Dialogue1 = Arrays.asList("");

    private String defaultButton;
    @FXML private TextField textField;
    @FXML private Label welcomeText;
    @FXML private Button TheButton;
    @FXML private HBox optionList;
    private boolean sequencePaused = false;
    KeyCode pressedKey = KeyCode.ENTER;
    public static Stage stage;
    Window window;
    Scene scene;
    Stage ansStage;
    int y = 0;
    int x = 0;
    int msgIndex = 0;
    public static boolean count = false;

    @FXML
    protected void onHelloButtonClick() throws InterruptedException {

        System.out.println(sequencePaused + "" + sequenceNumber);
        if(!sequencePaused) startSequence();

        scene = welcomeText.getScene();
        window = scene.getWindow();
        stage = (Stage) scene.getWindow();
        ansStage = HelloApplication.answersStage;

      //  appShake();
       // if(true)return;
        textField.setOnKeyPressed(event -> {

            this.pressedKey = event.getCode();

            if(event.getCode().equals(KeyCode.ENTER) && textField.getOpacity() == 1) {

            }
        });


        if(count) {
            clickCount+=1;



            if(TheButton.getOpacity() > 0.01 && clickCount < 95 && clickCount > 10)
                TheButton.setOpacity(TheButton.getOpacity()-0.05);

            switch(clickCount) {
                case 30 -> stage.setTitle("Stop it.");
                case 45 -> stage.setTitle("Have you considered it doesn't want to be clicked?") ;
                case 55 -> stage.setTitle("It faded away for a reason.");
                case 65 -> stage.setTitle("...");
                case 75 -> stage.setTitle("........");
                case 85 -> stage.setTitle("..........................");
                case 95 -> {
                    TheButton.setText("Damn it!");
                    TheButton.setOpacity(1);
                    TheButton.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: red; -fx-text-fill: white; -fx-font-size: 22; -fx-border");
                    count = false;
                    startSequence();
                }
                case 115 -> {
                    startSequence();
                }
            }

//        if(clickCount > 75) {
//            y+=1;
//            window.setY(window.getY()+4+y);
//       }
        }
        else return;

    }

    protected void startSequence(){

        if (sequenceNumber == 0) {
            defaultButton = TheButton.getStyle();
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1000),
                    ae -> stage.setTitle(seq0Dialogue.get(0))),
            new KeyFrame(
                    Duration.millis(2000),
                    ae -> {
                        stage.setTitle(seq0Dialogue.get(1));
                        ansStage.show();
                        ansStage.toFront();
                    }
            ));
            sequencePaused = true;
            timeline.play();

        } else if (sequenceNumber == 1) {
                    playDialogue(seq1Dialogue, talkers.TheApp);
                    playDialogue(seq1Dialogue1, talkers.TheButton);

        } else if (sequenceNumber == 2) {
                   playDialogue(seq2Dialogue, talkers.TheApp);
                   playDialogue(seq2Dialogue1, talkers.TheButton);

        }

        sequenceNumber+=1;

    }

    protected void playDialogue(List<String> dialogue, Object talker) {

        Timeline sequenceDialogue = new Timeline();
        if(sequenceNumber == 1) {
            for (int i = 0; i < dialogue.size(); i++) {
                int r = 255;
                int g = Math.min(255, i * 40);
                int b = Math.min(255, i * 40);
                int index = i;

                String buttonStyle = String.format("-fx-text-fill: white; -fx-font-size: 22; -fx-background-color: rgb(%d,%d,%d);", r, g, b);

                sequenceDialogue.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(i * (2 + Math.random())),
                                ae -> {
                                    switch (talker) {
                                        case talkers.TheApp -> stage.setTitle(dialogue.get(index));
                                        case talkers.TheButton -> {
                                            TheButton.setText(dialogue.get(index));
                                            TheButton.setStyle(buttonStyle);
                                            if (index == 6) {
                                                TheButton.setStyle(defaultButton);
                                                count = true;
                                            }
                                        }
                                        default -> System.out.println("Unknown talker, 1");
                                    }

                                }));
            }

        } else if (sequenceNumber == 2) {
            for (int i = 0; i < dialogue.size(); i++) {
                int index = i;
                sequenceDialogue.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(i*(2.3+Math.random())),

                                ae -> {
                                    switch (talker) {
                                        case talkers.TheApp -> stage.setTitle(dialogue.get(index));
                                        case talkers.TheButton -> TheButton.setText(dialogue.get(index));
                                        default -> System.out.println("Unknown talker, 2");
                                    }
                                }));
            }

        }
        sequenceDialogue.play();
    }

    private void appShake() {
       // List<Integer> s = Arrays.asList();
        int mod;

        for(int i = 0; i <= 200; i++) {
            if(i%2==0) mod = -1;
            else mod = 1;
            int fI = i;
            int fMod = mod;

            new Thread(() -> {
                try { Thread.sleep(80*fI);
                } catch (InterruptedException ignored) {}
                if(Math.random()>0.5) stage.setY(stage.getY()+(Math.random()*5*(fMod)));
                else stage.setX(stage.getX()+(Math.random()*5*(fMod)));
            }).start();
        }
    }
}