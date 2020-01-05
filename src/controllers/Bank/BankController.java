package controllers.Bank;

import com.google.inject.Inject;
import models.Storage;
import helpers.Alerts;
import helpers.ConvertToSerializer;
import helpers.IOFileSerializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Bank;

import java.io.IOException;
import java.util.Set;

public class BankController {

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

    private final IOFileSerializable ioFileSerializable;

    /**
     * Notice this constructor is using JSR 330 Inject annotation,
     * which makes it "Guice Friendly".
     *
     * @param store - Storage Data
     */
    @Inject
    public BankController(Storage store, IOFileSerializable ioFileSerializable) {
        this.store = store;
        this.ioFileSerializable = ioFileSerializable;
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
            try {
                ioFileSerializable.Export(
                        ConvertToSerializer.ConvertToBankSimpleList(this.store.getBanks()),"Bancos"
                );
                Set<Bank> let = ConvertToSerializer.ConvertToBankList(
                        ioFileSerializable.ImportBacks("Bancos")
                );
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                Alerts.Error(
                        "Un error ha ocurrido",
                        "No se puede gusrdar lista de bancos",
                        "Puede ser que el documento este dañado"
                );
            }
        } else
            Alerts.Error(
                    "Falta información que completar",
                    "Falta información que completar",
                    this.messageError
            );
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