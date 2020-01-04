package controllers.Calendar;

import com.google.inject.Inject;
import controllers.StageInitService;
import controllers.Storage;
import helpers.Moth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class CalendarController {

    @FXML
    private GridPane container_grid;

    @FXML
    private SplitMenuButton moth_menu;

    private final Storage store;

    private int mothIndex = 0;
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
        String moth = ((MenuItem) event.getSource()).getText();
        mothIndex = Moth.getIndex(moth);
        this.NewAction(event);
        moth_menu.setText(moth);
    }

    @FXML
    void NewAction(ActionEvent event) throws IOException {
        if (((MenuItem) event.getSource()).getText().equals("Nuevo")) mothIndex = 0;
        int[][] matrix = GetMatrix();
        container_grid.getChildren().clear();
        for (int i = 0; i < 7; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("../../views/Calendar/CalendarContainer.fxml"));
            Parent root = fxmlLoader.load();
            CalendarContainerController mainController = fxmlLoader.getController();
            mainController.InitialContent(i,matrix,mothIndex);
            container_grid.add(root, i, 0, 1, 1);
        }
        moth_menu.setText("Seleccionar Mes");
    }

    private int[][] GetMatrix(){
        int[][] matrix = new int[6][7];
        for (int[] array : matrix)
            Arrays.fill(array,0);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        int year = c.get(Calendar.YEAR);
            String input_date = (mothIndex == 12) ? "01/01/" + (year+1) : "01/"+(mothIndex+1)+"/" + year;
            try {
                Date dt1=format1.parse(input_date);
                c.setTime(dt1);
            } catch (ParseException e) {e.printStackTrace();}
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
            int fullDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            int position = 0;
            for (int j = 0; j < fullDays; j++)
            {
                matrix[position][dayOfWeek] = (j+1);
                if (dayOfWeek == 6) {
                    dayOfWeek = 0;
                    position++;
                } else dayOfWeek++;
            }
        return matrix;
    }
}