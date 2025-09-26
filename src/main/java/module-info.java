module me.shinyshadow_.apptest.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.shinyshadow_.apptest.demo to javafx.fxml;
    exports me.shinyshadow_.apptest.demo;
}