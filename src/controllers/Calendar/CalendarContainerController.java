package controllers.Calendar;

import helpers.Moth;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.ModelCalendarComponent;

import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CalendarContainerController {

    private LinkedList<ModelCalendarComponent> models;

    private VBox[] Contenedor;
    @FXML
    private VBox vbox_0;

    @FXML
    private Label dia0;

    @FXML
    private Label titulo0;

    @FXML
    private VBox vbox_1;

    @FXML
    private Label dia1;

    @FXML
    private Label titulo1;

    @FXML
    private VBox vbox_2;

    @FXML
    private Label dia2;

    @FXML
    private Label titulo2;

    @FXML
    private VBox vbox_3;

    @FXML
    private Label dia3;

    @FXML
    private Label titulo3;

    @FXML
    private VBox vbox_4;

    @FXML
    private Label dia4;

    @FXML
    private Label titulo4;

    @FXML
    private VBox vbox_5;

    @FXML
    private Label dia5;

    @FXML
    private Label titulo5;

    @FXML
    public void initialize() {
        this.models = new LinkedList<>(Arrays.asList(new ModelCalendarComponent(this.dia0,this.titulo0),
                new ModelCalendarComponent(this.dia1,this.titulo1),
                new ModelCalendarComponent(this.dia2,this.titulo2),
                new ModelCalendarComponent(this.dia3,this.titulo3),
                new ModelCalendarComponent(this.dia4,this.titulo4),
                new ModelCalendarComponent(this.dia5,this.titulo5)));
    }

    void InitialContent(int position,int[][] matriz, int moth){
        for (int i = 0; i < models.size(); i++) {
            models.get(i).getTitle().setText((matriz[i][position]==0)?"":(matriz[i][position]+""));
            //models[i].getContent().setText("-");
        }
        if (position==6)
            models.getLast().getTitle().setText(Moth.getMoth(moth));
    }

}
