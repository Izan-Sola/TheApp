module me.shinyshadow_.apptest.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;
    requires java.desktop;


    opens me.shinyshadow_.apptest.demo to javafx.fxml;
    exports me.shinyshadow_.apptest.demo;
}