package models;

import javafx.scene.control.Label;

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
}