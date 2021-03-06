package controllers;
import com.google.inject.Guice;
import com.google.inject.Injector;
import controllers.Bank.BankController;
import controllers.Calendar.CalendarController;
import controllers.Coupon.CouponController;
import helpers.GuiceFXMLLoader;
import helpers.SavingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import helpers.Module;


/**
 * @author David
 */

public class StageInitService {
    private static final Injector injector = Guice.createInjector(new Module());
    private static final GuiceFXMLLoader loader = new GuiceFXMLLoader(injector);

    public static GuiceFXMLLoader GetLoader(){
        return loader;
    }

    public static Stage stage;

    public void init(Stage stage) {
        // Set primary stage
        StageInitService.stage = stage;
        // Ask to load the Calendar.fxml file, injecting an instance of a CalendarController
        Parent root = (Parent) loader.load("/views/Calendar/Calendar.fxml", CalendarController.class);
        // Finish constructing the scene
        final Scene scene = new Scene(root, 1200, 840);
        // Load up the CSS stylesheet
        //scene.getStylesheets().add(getClass().getResource("fxmlapp.css").toString());
        injector.getInstance(SavingService.class).init();
        stage.setScene(scene);
        stage.setTitle("Ahorros");
        // Show the window
        stage.show();
    }

    public static void ShowCoupon() {
        // Ask to load the Bank.fxml file, injecting an instance of a BackController
        Parent root = (Parent) loader.load("/views/Coupon/Coupon.fxml", CouponController.class);
        // Finish constructing the scene
        final Scene scene = new Scene(root, 500, 300);
        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setScene(scene);
        dialogStage.setTitle("Cupones");
        dialogStage.setResizable(false);
        // Get controller
        FXMLLoader load = loader.getLoader();
        CouponController controller = load.getController();
        controller.setDialogStage(dialogStage);
        // Show the window
        dialogStage.show();
    }

    public static void ShowBank() {
        // Ask to load the Bank.fxml file, injecting an instance of a BackController
        DialogPane root = (DialogPane) loader.load("/views/Bank/Bank.fxml", BankController.class);
        // Finish constructing the scene
        final Scene scene = new Scene(root, 480, 300);
        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setScene(scene);
        dialogStage.setTitle("Bancos");
        dialogStage.setResizable(false);
        // Get controller
        FXMLLoader load = loader.getLoader();
        BankController controller = load.getController();
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

