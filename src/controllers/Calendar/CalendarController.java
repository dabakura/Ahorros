package controllers.Calendar;

import com.google.inject.Inject;
import controllers.StageInitService;
import helpers.GuiceFXMLLoader;
import helpers.Snapshot;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.CalendarItem;
import models.Storage;
import helpers.Month;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.GridPane;
import java.io.IOException;

public class CalendarController {

    @FXML
    private GridPane container_grid;

    @FXML
    private SplitMenuButton month_menu;

    @FXML
    private AnchorPane Principal_Content;

    private final Storage store;

    private Month month = Month.JANUARY;

    private Stage stage;

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
    public void initialize() {
        month = Month.JANUARY;
        this.stage = StageInitService.stage;
        this.store.getCalendarMatrix().Initialize();
        Reset();
    }

    @FXML
    void ShowCreateBack(ActionEvent event) {
        StageInitService.ShowBank();
    }

    @FXML
    void SelectMoth(ActionEvent event) throws IOException {
        String monthString = ((MenuItem) event.getSource()).getText();
        this.month = Month.getMonth(monthString);
        this.NewAction(event);
        month_menu.setText(monthString);
    }

    @FXML
    void NewAction(ActionEvent event) {
        if (((MenuItem) event.getSource()).getText().equals("Nuevo")) {
            month = Month.JANUARY;
            this.store.getCalendarMatrix().Initialize();
        } else this.store.getCalendarMatrix().NewMonthSelected();
        Reset();
    }

    @FXML
    void Test(ActionEvent event) {
        this.store.getCalendarMatrix().Test();
    }

    @FXML
    void Print(ActionEvent event) {
        Snapshot.CaptureAndSaveDisplay(this.Principal_Content, this.month.getMonth());
    }


    public void ShowCreateCoupon(ActionEvent actionEvent) {StageInitService.ShowCoupon();}

    @FXML
    public void ExitAction(ActionEvent actionEvent)  {
        stage.close();
    }

    private void  Reset()
    {
        CalendarItem[][] matrix = this.store.getCalendarMatrix().getCalendarMatrix(month);
        container_grid.getChildren().clear();
        GuiceFXMLLoader loader = StageInitService.GetLoader();
        for (int i = 0; i < 7; i++) {
            Parent root = (Parent) loader.load("../../views/Calendar/CalendarContainer.fxml",CalendarContainerController.class);
            CalendarContainerController mainController = loader.getLoader().getController();
            mainController.InitialContent(i,matrix,month);
            container_grid.add(root, i, 0, 1, 1);
        }
        month_menu.setText("Seleccionar Mes");
    }

    @FXML
    public void Save(ActionEvent actionEvent) {
        this.store.getCalendarMatrix().save();
    }
}