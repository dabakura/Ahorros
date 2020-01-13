package controllers.Coupon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class CouponController {

    @FXML
    private ComboBox<String> cbBank;

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
    void Calculated(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

}
