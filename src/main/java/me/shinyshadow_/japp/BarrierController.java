package me.shinyshadow_.japp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BarrierController {
    @FXML private static Button TheButton;

    public static void createBarrier() {
        Stage mainStage = ButtonController.stage;
        Stage barrier = TheApplication.barrierStage;
        barrier.show();

        barrier.setY(mainStage.getY()+97);
        barrier.setX(mainStage.getX()+107);

    }
}
