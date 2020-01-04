package controllers.Back;

import com.google.inject.Inject;
import controllers.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Bank;

public class BackController {

    @FXML
    private TextField name_input;

    @FXML
    private TextField nick_input;

    private Bank bank = new Bank();

    private String messageError;

    private Stage dialogStage;

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage - Windows primary
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private final Storage store;



    /**
     * Notice this constructor is using JSR 330 Inject annotation,
     * which makes it "Guice Friendly".
     *
     * @param store - Storage Data
     */
    @Inject
    public BackController(Storage store) {
        this.store = store;
    }

    /**
     * Called when the user clicks ok.
     */
    public void handleApply() {
        this.bank.setName(name_input.getText());
        this.bank.setNick(nick_input.getText());
        if (isInputValid()) {
            this.store.getBanks().add(this.bank);
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falta información que completar");
            alert.setHeaderText("Todos los campos son necesarios");
            alert.setContentText(this.messageError);
            alert.show();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    public void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        this.messageError = (name_input.getText().length() == 0) ? "Falta el campo Nombre\n" : "";
        this.messageError = (nick_input.getText().length() == 0) ? this.messageError+"Falta el campo Alias" : this.messageError;
        return (this.bank.getName().length() > 0)  &&
                (this.bank.getNick().length() > 0);
    }
}