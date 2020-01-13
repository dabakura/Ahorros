package models;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ModelCalendarComponent {
    private Label Title;
    private Label Content;

    public ModelCalendarComponent() {

    }

    public ModelCalendarComponent(Label title, Label content){
        Title = title;
        Content = content;
    }

    public ModelCalendarComponent(String title, String content){
        Title = new Label();
        Content = new Label();
        Title.setText(title);
        Content.setText(content);
    }

    public Label getTitle() {
        return Title;
    }

    public Label getContent() {
        return Content;
    }

    public void setContent(Label content) {
        Content = content;
    }

    public void setTitle(Label title) {
        Title = title;
    }

    public AnchorPane getNode() {
        AnchorPane anchorPane = new AnchorPane();
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(Title);
        box.getChildren().add(Content);
        AnchorPane.setTopAnchor(box, 0.0);
        AnchorPane.setBottomAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);
        anchorPane.getChildren().add(box);
        return anchorPane;
    }
}