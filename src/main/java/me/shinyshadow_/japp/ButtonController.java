package me.shinyshadow_.japp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;

public class ButtonController {

    public static void wallSlams(Button TheButton, Scene scene) {
        System.out.println("" + scene.widthProperty());
        Point2D initialLoc = TheButton.localToScene(0, 0);
        Timeline timeline = new Timeline(
                new KeyFrame( Duration.millis(600),
                        ae -> {
                            doSlam(initialLoc, TheButton, 205, -335, 0);
                            expandAppBorder(scene, 0, 0);
                        }),
                new KeyFrame( Duration.millis(1200),
                        ae -> {
                            doSlam(initialLoc, TheButton, 355, 75, 0);
                            expandAppBorder(scene, 0, 1);
                        }),
                new KeyFrame( Duration.millis(1800),
                        ae -> {
                            doSlam(initialLoc, TheButton, 300, 75, 1);
                            expandAppBorder(scene, 1, 1);
                        }),
                new KeyFrame( Duration.millis(2400),
                        ae -> {
                            doSlam(initialLoc, TheButton, 300, -215, 1);
                            expandAppBorder(scene, 1, 0);
                        }));
        timeline.play();

    }

    private static void expandAppBorder(Scene scene, int d1, int d2) {

        new Thread(() -> {
            try {Thread.sleep(150); } catch (InterruptedException ignored) {}

            Stage stage = (Stage) scene.getWindow();
            double extraSize = 125;

            if(d1==0) {
                if(d2==0) stage.setX(stage.getX() - extraSize);
                else stage.setX(stage.getX() + extraSize);
                stage.setWidth(stage.getWidth() + extraSize);
            } else if (d1==1) {
                if(d2==0) stage.setY(stage.getY() - extraSize);
                else stage.setY(stage.getY() + extraSize);
                stage.setHeight(stage.getHeight() + extraSize);
            }

        }).start();
    }

    private static void doSlam(Point2D loc, Button TheButton, int duration, int Offset, int t) {

        TranslateTransition slam = new TranslateTransition(Duration.millis(duration), TheButton);

        if (t==0) slam.setToX(loc.getX()+(Offset));
        else if (t==1) slam.setToY(loc.getY()+(Offset));

        slam.setInterpolator(Interpolator.EASE_IN);
        slam.setAutoReverse(true);
        slam.setCycleCount(2);
        slam.play();
        new Thread(() -> { try { Thread.sleep(300); } catch(InterruptedException ignored) {}
            AppController.appShake(6, 12, 20);
        }).start();
    }
}
