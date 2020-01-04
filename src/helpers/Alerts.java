package helpers;

import javafx.scene.control.Alert;

public class Alerts {
    public static void Error(String title, String Header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(Header);
        alert.setContentText(content);
        alert.show();
    }
}
