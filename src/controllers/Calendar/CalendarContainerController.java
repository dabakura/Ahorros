package controllers.Calendar;

import com.google.inject.Inject;
import helpers.Month;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CalendarContainerController implements ICalendarObserver {

    private LinkedList<ModelCalendarComponent> models;

    private Month month;

    private int position;

    private final Storage store;

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

    @Inject
    public CalendarContainerController(Storage store) {
        this.store = store;
    }

    @FXML
    public void initialize() {
        this.models = new LinkedList<>(Arrays.asList(new ModelCalendarComponent(this.dia0,this.titulo0),
                new ModelCalendarComponent(this.dia1,this.titulo1),
                new ModelCalendarComponent(this.dia2,this.titulo2),
                new ModelCalendarComponent(this.dia3,this.titulo3),
                new ModelCalendarComponent(this.dia4,this.titulo4),
                new ModelCalendarComponent(this.dia5,this.titulo5)));
        this.Contenedor = new VBox[]{vbox_0,vbox_1,vbox_2,vbox_3,vbox_4,vbox_5};
    }

    void InitialContent(int position, CalendarItem[][] matrix, Month month){
        this.month = month;
        this.position = position;
        this.refresh(matrix);
        this.store.getCalendarMatrix().ChangeSubscribe(this);
    }

    private void refresh(CalendarItem[][] matrix){
        for (int i = 0; i < models.size(); i++) {
            models.get(i).getContent().setText("");
            if (matrix[i][this.position].get_active()){
                if(matrix[i][this.position].get_title())
                    models.get(i).getContent().setText("Titulos");
            }
            models.get(i).getTitle().setText(matrix[i][this.position].get_day());
            bodyFill(matrix[i][this.position].get_models(),i);
        }
        if (this.position==6)
            models.getLast().getTitle().setText(this.month.getMonth());
    }

    private void bodyFill (List<ModelCalendarComponent> mcc, int index) {
        this.Contenedor[index].getChildren().removeIf(node -> node instanceof AnchorPane );
        mcc.forEach(m -> {
            this.Contenedor[index].getChildren().add(m.getNode());
        });
    }

    @Override
    public void CalendarListener() {
        CalendarItem[][] matrix = this.store.getCalendarMatrix().getCalendarMatrix(this.month);
        this.refresh(matrix);
    }

    @Override
    public Month getMonth() {
        return this.month;
    }
}
