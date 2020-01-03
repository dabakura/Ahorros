import controllers.StageInitService;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        StageInitService sceneInitService = new StageInitService();
        sceneInitService.init(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
