package controllers;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class CalendarController {

    private String nombre;

    @FXML
    private Label dia0;

    @FXML
    private Label titulo0;

    private final Storage store;

    /**
     * Notice this constructor is using JSR 330 Inject annotation,
     * which makes it "Guice Friendly".
     * @param store - Storage Data
     */
    @Inject
    public CalendarController(Storage store) {
        this.store = store;
    }

    @FXML
    void ShowCreateBack(ActionEvent event) {
        StageInitService.ShowBank();
    }
}