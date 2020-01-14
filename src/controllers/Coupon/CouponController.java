package controllers.Coupon;

import com.google.inject.Inject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Bank;
import models.CalculatedAmount;
import models.Coupon;
import models.Storage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;


public class CouponController {

    @FXML
    private ComboBox<Bank> cbBank;

    @FXML
    private TextField tfAmount;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private Spinner<Integer> spNumberCoupons;

    @FXML
    private Spinner<Integer> spMonthsCollect;

    @FXML
    private TextField tfIva;

    @FXML
    private TextField tfCapitalizable;

    @FXML
    private TextField tfInterest;

    @FXML
    private TextField tfMonths;

    @FXML
    private TextField tfCapital;

    @FXML
    private TextField tfTotal;

    @FXML
    private TextField tfCCapital;

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

    private CalculatedAmount calculatedAmount = new CalculatedAmount();

    private Coupon coupon = new Coupon();

    /**
     * Notice this constructor is using JSR 330 Inject annotation,
     * which makes it "Guice Friendly".
     *
     * @param store - Storage Data
     */
    @Inject
    public CouponController(Storage store) {
        this.store = store;
    }

    @FXML
    public void initialize() {
        dpStartDate.setValue(NOW_LOCAL_DATE());
        ObservableList<Bank> options =
                FXCollections.observableArrayList(store.getBanks().getList());
        cbBank.setItems(options);
        cbBank.setConverter(new StringConverter<Bank>() {
            @Override
            public String toString(Bank bank) {
                if (bank == null)
                    return null;
                return bank.getName();
            }

            @Override
            public Bank fromString(String s) {
                return store.getBanks().Find(s);
            }
        });
        cbBank.setPromptText("Seleccione la Entidad");
        tfAmount.textProperty().bindBidirectional(this.coupon.amountProperty(), convertNumberString());
        tfAmount.textProperty().addListener(ChangeListenerDoubleString(tfAmount));
        tfCCapital.textProperty().bindBidirectional(this.coupon.capitalProperty(), convertNumberString());
        tfCCapital.textProperty().addListener(ChangeListenerIntegerString(tfCCapital));
        tfIva.textProperty().bindBidirectional(this.calculatedAmount.ivaProperty(), convertNumberString());
        tfIva.textProperty().addListener(ChangeListenerDoubleString(tfIva));
        tfCapitalizable.textProperty().bindBidirectional(this.calculatedAmount.capitalizableProperty(), convertNumberString());
        tfCapitalizable.textProperty().addListener(ChangeListenerDoubleString(tfCapitalizable));
        tfInterest.textProperty().bindBidirectional(this.calculatedAmount.interestProperty(), convertNumberString());
        tfInterest.textProperty().addListener(ChangeListenerDoubleString(tfInterest));
        tfMonths.textProperty().bindBidirectional(this.calculatedAmount.monthsProperty(), convertNumberString());
        tfMonths.textProperty().addListener(ChangeListenerIntegerString(tfMonths));
        tfCapital.textProperty().bindBidirectional(this.calculatedAmount.capitalProperty(), convertNumberString());
        tfCapital.textProperty().addListener(ChangeListenerIntegerString(tfCapital));
        tfTotal.textProperty().bindBidirectional(this.calculatedAmount.totalProperty(), convertNumberString());
        tfTotal.textProperty().addListener(ChangeListenerDoubleString(tfTotal));
    }

    private ChangeListener<String> ChangeListenerDoubleString(TextField tf){
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tf.setText(oldValue);
                }
            }
        };
    }

    private ChangeListener<String> ChangeListenerIntegerString(TextField tf){
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}")) {
                    tf.setText(oldValue);
                }
            }
        };
    }

    private StringConverter<Number> convertNumberString(){
        return new StringConverter<Number>() {
            @Override
            public String toString(Number n) {
                return n.toString();
            }

            @Override
            public Number fromString(String s) {
                try {
                    return NumberFormat.getInstance(Locale.ENGLISH).parse(s);
                } catch (ParseException e) {
                    return null;
                }
            }
        };
    }

    private static LocalDate NOW_LOCAL_DATE(){
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date , formatter);
    }

    private boolean BankIsValid(){
        Bank bank = cbBank.getValue();
        if (bank != null){
            this.coupon.setBank(bank.getNick());
            return true;
        }
        return false;
    }

    private boolean CouponIsValid(){
        return (BankIsValid() && !(this.coupon.getBank()).isEmpty() && this.coupon.getAmount() != 0.0  && this.coupon.getCapital() != 0);
    }

    @FXML
    void Calculated(ActionEvent event) {
        double interest_net = (((double) calculatedAmount.getCapital() * (calculatedAmount.getInterest()/100))/ (double) calculatedAmount.getMonths());
        double interest_gross = (interest_net - ((interest_net+calculatedAmount.getCapitalizable())*(calculatedAmount.getIva()/100)));
        if (((Double) interest_gross).isNaN()) return;
        BigDecimal rounding = new BigDecimal(interest_gross).setScale(2, RoundingMode.HALF_UP);
        interest_gross = rounding.doubleValue();
        calculatedAmount.setTotal(interest_gross);
        coupon.setAmount(interest_gross);
        coupon.setCapital(calculatedAmount.getCapital());
    }

    @FXML
    void cancel(ActionEvent event) {dialogStage.close();}

    @FXML
    void save(ActionEvent event) {
        if (CouponIsValid()){
            this.coupon.setMonthsCollect(spMonthsCollect.getValue());
            this.coupon.setNumberCoupons(spNumberCoupons.getValue());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.coupon.setStartDate(dpStartDate.getValue().format(formatter));
            this.store.getCalendarMatrix().setCoupon(this.coupon);
            dialogStage.close();
        }
    }

}
