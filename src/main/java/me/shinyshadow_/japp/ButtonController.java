package me.shinyshadow_.japp;
import javafx.animation.*;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.lang.Math.random;

public class ButtonController {

    public static void wallSlams(Button TheButton, Scene scene) {

        Point2D initialLoc = TheButton.localToScene(0, 0);

        Timeline timeline = new Timeline(
                new KeyFrame( Duration.millis(600),
                        ae -> {
                            doSlam(initialLoc, TheButton, 200, -375, 0);
                            expandAppBorder(scene, 0, 0);
                        }),
                new KeyFrame( Duration.millis(1200),
                        ae -> {
                            doSlam(initialLoc, TheButton, 200, 105, 0);
                            expandAppBorder(scene, 0, 1);
                        }),
                new KeyFrame( Duration.millis(1800),
                        ae -> {
                            doSlam(initialLoc, TheButton, 200, 105, 1);
                            expandAppBorder(scene, 1, 1);
                        }),
                new KeyFrame( Duration.millis(2400),
                        ae -> {
                            doSlam(initialLoc, TheButton, 210, -300, 1);
                            expandAppBorder(scene, 1, 0);

                        }),
                new KeyFrame( Duration.millis(2800),
                        ae -> {
                                    randomMovement(TheButton, scene);
                        }));

        timeline.play();

    }

    private static void expandAppBorder(Scene scene, int d1, int d2) {
        Stage stage = (Stage) scene.getWindow();
        double extraSize = 300;
        double durationMillis = 100;
        int frames = 60;

        double startX = stage.getX();
        double startY = stage.getY();
        double startWidth = stage.getWidth();
        double startHeight = stage.getHeight();

        double targetX = startX;
        double targetY = startY;
        double targetWidth = startWidth;
        double targetHeight = startHeight;

        if (d1 == 0) {
            targetWidth += extraSize;
            targetX += (d2 == 0) ? -extraSize : extraSize;
        } else if (d1 == 1) {
            targetHeight += extraSize;
            targetY += (d2 == 0) ? -extraSize : extraSize;
        }

        Timeline timeline = new Timeline();

        for (int i = 0; i <= frames; i++) {
            double progress = (double) i / frames;
            double interpolatedX = startX + (targetX - startX) * progress;
            double interpolatedY = startY + (targetY - startY) * progress;
            double interpolatedWidth = startWidth + (targetWidth - startWidth) * progress;
            double interpolatedHeight = startHeight + (targetHeight - startHeight) * progress;

            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(progress * durationMillis),
                    e -> {
                        stage.setX(interpolatedX);
                        stage.setY(interpolatedY);
                        stage.setWidth(interpolatedWidth);
                        stage.setHeight(interpolatedHeight);
                    }
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(1);
        timeline.play();
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
            AppController.appShake(16, 15, 12);
        }).start();
    }

    public static void randomMovement(Button TheButton, Scene scene) {

        TranslateTransition move = new TranslateTransition(Duration.millis(1000), TheButton);
        move.setInterpolator(Interpolator.LINEAR);

        Stage stage = (Stage) scene.getWindow();
        System.out.println(stage.getX());

        double limitX = stage.getX();
        double limitY = stage.getY();

        double posX = Math.random() * limitX;
        double posY = Math.random() * limitY;

        move.setToX( (Math.random() < 0.5) ? posX : -posX );
        move.setToY( (Math.random() < 0.5) ? posY : -posY );

        move.play();

    }
}
