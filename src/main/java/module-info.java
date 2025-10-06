module me.shinyshadow_.japp {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.shinyshadow_.japp to javafx.fxml;
    exports me.shinyshadow_.japp;
}