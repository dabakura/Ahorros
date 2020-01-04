package controllers;
import com.google.inject.Guice;
import com.google.inject.Injector;
import controllers.Back.BackController;
import controllers.Calendar.CalendarController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

/**
 * @author David
 */

public class StageInitService {
    private static final Injector injector = Guice.createInjector(new Module());
    private static final GuiceFXMLLoader loader = new GuiceFXMLLoader(injector);

    public void init(Stage stage) {
        // Ask to load the Calendar.fxml file, injecting an instance of a CalendarController
        Parent root = (Parent) loader.load("/views/Calendar/Calendar.fxml", CalendarController.class);
        // Finish constructing the scene
        final Scene scene = new Scene(root, 1200, 700);
        // Load up the CSS stylesheet
        //scene.getStylesheets().add(getClass().getResource("fxmlapp.css").toString());
        // Show the window

        stage.setScene(scene);
        stage.setTitle("Ahorros");
        stage.show();
    }

    public static void ShowBank() {
        // Ask to load the Back.fxml file, injecting an instance of a BackController
        DialogPane root = (DialogPane) loader.load("/views/Back/Back.fxml", BackController.class);
        // Finish constructing the scene
        final Scene scene = new Scene(root, 480, 300);
        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setScene(scene);
        dialogStage.setTitle("Bancos");
        dialogStage.setResizable(false);
        // Get controller
        FXMLLoader load = loader.getLoader();
        BackController controller = load.getController();
        controller.setDialogStage(dialogStage);
        // Add Event Handler.
        root.lookupButton(ButtonType.APPLY).addEventHandler(ActionEvent.ACTION, e -> {
            controller.handleApply();
            e.consume();
        });
        root.lookupButton(ButtonType.CANCEL).addEventHandler(ActionEvent.ACTION, e -> {
            controller.handleCancel();
            e.consume();
        });
        // Show the window
        dialogStage.show();
    }
}

