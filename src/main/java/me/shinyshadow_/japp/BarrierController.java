package me.shinyshadow_.japp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BarrierController {
    @FXML private static Button TheButton;

    private static volatile boolean follow = true;
    static Stage mainStage = ButtonController.stage;
    static Stage barrier = TheApplication.barrierStage;
    public static void createBarrier() {
        mainStage = ButtonController.stage;
        barrier = TheApplication.barrierStage;
        barrier.show();

        barrier.setY(mainStage.getY()+97);
        barrier.setX(mainStage.getX()+107);

    }

    public static void followMainWindow() {

       for(int i=0; i<1000; i++) {
           int fI = i;
            new Thread(() -> {
                try { Thread.sleep(200*fI);
                } catch (InterruptedException ignored) {}
                barrier.setY(mainStage.getY()+97);
                barrier.setX(mainStage.getX()+107);
            }).start();
        }
       //smthing-----------
    }

    public static void destroyBarrier() {

    }

}
