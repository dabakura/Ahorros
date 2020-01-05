package controllers.Calendar;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.ModelCalendarComponent;

public class CalendarComponentController {

    @FXML
    private Label bank_label;

    @FXML
    private Label amount_label;

    public void InitialContent(ModelCalendarComponent model){
        this.bank_label.setText(model.getTitle().getText());
        this.amount_label.setText(model.getContent().getText());
    }
}
