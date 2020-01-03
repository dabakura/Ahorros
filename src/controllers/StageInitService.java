package controllers;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * @author David
 */

public class StageInitService {
    private final Injector injector = Guice.createInjector(new Module());

    public void init(Stage stage) {
        GuiceFXMLLoader loader = new GuiceFXMLLoader(injector);
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

}

