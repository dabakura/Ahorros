package controllers.Calendar;

import com.google.inject.Inject;
import controllers.StageInitService;
import helpers.GuiceFXMLLoader;
import models.CalendarItem;
import models.ICalendarObserver;
import models.Storage;
import helpers.Month;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private final Storage store;

    private Month month = Month.JANUARY;
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
        CalendarItem[][] matrix = this.store.getCalendarMatrix().getCalendarMatrix(month);
        container_grid.getChildren().clear();
        GuiceFXMLLoader loader = StageInitService.GetLoader();
        for (int i = 0; i < 7; i++) {
            /*FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("../../views/Calendar/CalendarContainer.fxml"));
            Parent root = fxmlLoader.load();*/
            Parent root = (Parent) loader.load("../../views/Calendar/CalendarContainer.fxml",CalendarContainerController.class);
            CalendarContainerController mainController = loader.getLoader().getController();
            mainController.InitialContent(i,matrix,month);
            container_grid.add(root, i, 0, 1, 1);
        }
        month_menu.setText("Seleccionar Mes");
    }

    @FXML
    void Test(ActionEvent event) {
        this.store.getCalendarMatrix().Test();
    }
}