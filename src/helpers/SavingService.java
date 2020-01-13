package helpers;

import com.google.inject.Inject;
import models.Bank;
import models.Storage;

import java.io.IOException;
import java.util.Set;

public class SavingService {

    @Inject
    private SavingService(Storage store, IOFileSerializable ioFileSerializable){
        this.ioFileSerializable = ioFileSerializable;
        this.store = store;
    }

    private  IOFileSerializable ioFileSerializable;
    private  Storage store;

    public void init(){
        loadingBankList();
    }

    private void loadingBankList(){
        try {
            store.getBanks().clear();
            store.getBanks().addAll(ConvertToSerializer.ConvertToBankList(
                    ioFileSerializable.ImportBacks(Path.BANKS.getPath())
            ));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
