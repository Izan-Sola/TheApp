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

public class AppController {

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
                                                       "Stop", "Solitude", "The Button");
    private List<String> seq2Dialogue = Arrays.asList("Why do you keep going?", "What's so fun about it?", "It wants you to stop.",
                                                      "Go waste your time somewhere else!", "...", "The App");
    private List<String> seq2Dialogue1 = Arrays.asList("Stop", "sToP", "s t o p", "stop...", "The Button");

    private List<String> seq3Dialogue = Arrays.asList("...", "Can you not???", "...", "Bam.", "Try to click it now, jerk.");
    private List<String> seq4Dialogue = Arrays.asList( "....", "..........", "You think you are smart?", "What now?");

    private List<String> seq5Dialogue = Arrays.asList("Ok maybe you are...", "Whatever.", "Keep clicking if you want.", "Im not who will have to deal with the consequences!", "You do you...", "The App");
    private String defaultButton;
    @FXML private TextField textField;
    @FXML private Label welcomeText;
    @FXML private Button TheButton;
    @FXML private HBox optionList;
    private boolean start = false;
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
    private void onHelloButtonClick() {



        System.out.println("" + clickCount + count + " -- " + sequenceNumber);


        if (!start) {
           // clickCount=225;
            // sequenceNumber == 0 block
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
            start = true;
            timeline.play();

            sequenceNumber += 1;
        }

        scene = welcomeText.getScene();
        window = scene.getWindow();
        stage = (Stage) scene.getWindow();
        ansStage = TheApplication.answersStage;
        ButtonController.randomMovement(TheButton, scene);
        if (count) {
            clickCount += 1;

            if (TheButton.getOpacity() > 0.01 && clickCount < 95 && clickCount > 10)
                TheButton.setOpacity(TheButton.getOpacity() - 0.05);

            switch (clickCount) {
                case 30 -> stage.setTitle("Stop it.");
                case 45 -> stage.setTitle("Have you considered it doesn't want to be clicked?");
                case 55 -> stage.setTitle("It faded away for a reason.");
                case 65 -> stage.setTitle("...");
                case 75 -> stage.setTitle("........");
                case 85 -> stage.setTitle("..........................");
                case 95 -> {
                    TheButton.setText("Damn it!");
                    TheButton.setOpacity(1);
                    TheButton.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: red; -fx-text-fill: white; -fx-font-size: 21; ");
                    count = false;
                    clickCount += 1;

                    // sequenceNumber == 1 block
                    playDialogue(seq1Dialogue, talkers.TheApp);
                    playDialogue(seq1Dialogue1, talkers.TheButton);

                    sequenceNumber += 1;
                }
                case 115 -> {
                    count = false;
                    clickCount += 1;

                    // sequenceNumber == 2 block
                    appShake(5, 200, 65);
                    playDialogue(seq2Dialogue, talkers.TheApp);
                    playDialogue(seq2Dialogue1, talkers.TheButton);

                    sequenceNumber += 1;
                }
                case 130 -> {
                    new Thread(() -> {
                        try { Thread.sleep(5000);
                        } catch (InterruptedException ignored) {}
                        BarrierController.createBarrier();
                    }).start();

                    // sequenceNumber == 3 block
                    playDialogue(seq3Dialogue, talkers.TheApp);

                    sequenceNumber += 1;
                    count = false;
                }
                case 132 -> {

                    // sequenceNumber == 4 block
                    playDialogue(seq4Dialogue, talkers.TheApp);

                    sequenceNumber += 1;
                    count = false;
                }
                case 134 -> {

                    BarrierController.destroyBarrier();

                    // sequenceNumber == 5 block
                    playDialogue(seq5Dialogue, talkers.TheApp);

                    sequenceNumber += 1;
                    count = false;
                }
                case 145 -> {
                    appShake(5, 15, 50);
                    TheButton.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold; " +
                            "-fx-background-color: rgb(240,220,220); -fx-border-color: black; -fx-border-width: 2;");
                }
                case 160 -> {
                    appShake(6, 22, 48);
                    TheButton.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold; " +
                            "-fx-background-color: rgb(255,180,180); -fx-border-color: black; -fx-border-width: 2;");
                }
                case 175 -> {
                    appShake(8, 27, 45);
                    TheButton.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold; " +
                            "-fx-background-color: rgb(255,130,130); -fx-border-color: black; -fx-border-width: 2;");
                }
                case 190 -> {
                    appShake(10, 40, 40);
                    TheButton.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold; " +
                            "-fx-background-color: rgb(255,80,80); -fx-border-color: black; -fx-border-width: 2;");
                }
                case 215 -> {
                    appShake(10, 40, 40);
                    TheButton.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold; " +
                            "-fx-background-color: rgb(255,40,40); -fx-border-color: black; -fx-border-width: 2;");
                }
                case 230 -> {
                    appShake(16, 35, 25);
                    TheButton.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold; " +
                            "-fx-background-color: rgb(255,0,0); -fx-border-color: black; -fx-border-width: 2;");

                    new Thread(() -> {
                        try { Thread.sleep(600);
                        } catch (InterruptedException ignored) {}

                        ButtonController.wallSlams(TheButton, scene);
                    }).start();
                }

            }
        } else
            return;

    }

    protected void playDialogue(List<String> dialogue, Object talker) {

        Timeline sequenceDialogue = new Timeline();

        if(sequenceNumber == 1) {
            new Thread(() -> {
                try { Thread.sleep(9000); } catch (InterruptedException ignored) {}
                TheButton.setStyle(defaultButton);
                count = true;
            }).start();

            for (int i = 0; i < dialogue.size(); i++) {
                int index = i;
                sequenceDialogue.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(i * (2 + Math.random())),
                                ae -> {
                                    switch (talker) {
                                        case talkers.TheApp -> stage.setTitle(dialogue.get(index));
                                        case talkers.TheButton -> TheButton.setText(dialogue.get(index));
                                        default -> System.out.println("Unknown talker, 1");
                                    }
                                }));
            }

        } else if (sequenceNumber == 2) {
            System.out.println("Seq Number 2");
            new Thread(() -> {
                try { Thread.sleep(12000); } catch (InterruptedException ignored) {}

                TheButton.setStyle(defaultButton);
                count = true;
            }).start();
            for (int i = 0; i < dialogue.size(); i++) {
                int index = i;
                int r = 255;
                int g = Math.min(255, i * 30);
                int b = Math.min(255, i * 30);
                String buttonStyle = String.format("-fx-text-fill: white; -fx-font-size: 22; -fx-background-color: rgb(%d,%d,%d);", r, g, b);

                sequenceDialogue.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(i*(2.3+Math.random())),
                                ae -> {
                                    switch (talker) {
                                        case talkers.TheApp -> stage.setTitle(dialogue.get(index));
                                        case talkers.TheButton -> {
                                            TheButton.setText(dialogue.get(index));
                                            TheButton.setStyle(buttonStyle);
                                        }
                                        default -> System.out.println("Unknown talker, 2");
                                    }
                                }));
            }

        } else if (sequenceNumber == 3) {
            new Thread(() -> {
                try { Thread.sleep(6000); } catch (InterruptedException ignored) {}
                count = true;
            }).start();

            for (int i = 0; i < dialogue.size(); i++) {
                int index = i;
                sequenceDialogue.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(i * (2)),
                                 ae -> stage.setTitle(dialogue.get(index))));
            }
        } else if (sequenceNumber == 4) {
            new Thread(() -> {
                try { Thread.sleep(6000); } catch (InterruptedException ignored) {}
                count = true;
                BarrierController.followMainWindow();
            }).start();

            for (int i = 0; i < dialogue.size(); i++) {
                int index = i;
                sequenceDialogue.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(i * (1.5)),
                                ae -> stage.setTitle(dialogue.get(index))));
            }
        } else if (sequenceNumber == 5) {

            new Thread(() -> {
                try { Thread.sleep(6000); } catch (InterruptedException ignored) {}
                count = true;
            }).start();
            for (int i = 0; i < dialogue.size(); i++) {
                int index = i;
                sequenceDialogue.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(i * (2)),
                                ae -> stage.setTitle(dialogue.get(index))));
            }
        }
        sequenceDialogue.play();
    }

    public static void appShake(int strength, int duration, int speed) {
        int mod;
        for(int i = 0; i <= duration; i++) {
            if(i%2==0) mod = -1;
            else mod = 1;
            int fI = i;
            int fMod = mod;

            new Thread(() -> {
                try { Thread.sleep((long) speed *fI);
                } catch (InterruptedException ignored) {}
                if(Math.random()>0.5) stage.setY(stage.getY()+(Math.random()*strength*(fMod)));
                else stage.setX(stage.getX()+(Math.random()*strength*(fMod)));
            }).start();
        }
    }

}