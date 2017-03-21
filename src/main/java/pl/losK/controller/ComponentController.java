package pl.losK.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pl.losK.model.BillItem;
import pl.losK.service.BillItemService;
import pl.losK.xml.JsonFactory;

import java.util.List;


/**
 * Created by m.losK on 2017-03-21.
 */
public class ComponentController extends Controller {

    @FXML
    private JFXTextField productNameJFXTextField;

    @FXML
    private JFXTextField amountJFXTextField;

    @FXML
    private JFXTextField priceJFXTextField;

    @FXML
    private JFXTextField taxJFXTextField;

    @FXML
    private JFXButton clearJFXButton;

    @FXML
    private JFXButton addProductJFXButton;

    public void initialize() {
        RequiredFieldValidator fieldValidator = new RequiredFieldValidator();
        productNameJFXTextField.getValidators().add(fieldValidator);
        fieldValidator.setMessage("Please enter product name");

        productNameJFXTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    productNameJFXTextField.validate();
                }
            }
        });

        NumberValidator numberValidator = new NumberValidator();
        amountJFXTextField.getValidators().add(numberValidator);
        numberValidator.setMessage("Something went wrong. Please enter number only");
        amountJFXTextField.focusedProperty().addListener((observable, oldValue, newValue) ->
                amountJFXTextField.validate());
    }

    @FXML
    void clearOnAction(ActionEvent event) {
//        clearJFXButton.setOnAction(e -> {
        productNameJFXTextField.clear();
        amountJFXTextField.clear();
        priceJFXTextField.clear();
        taxJFXTextField.clear();

        addProductJFXButton.setDisable(false);
//        });
    }

    @FXML
    void addProductOnAction(ActionEvent event) {
        JsonFactory jsonFactory = new JsonFactory();
        BillItemService billItemService = BillItemService.getInstance();
        List<BillItem> billItemList = jsonFactory.loadListDataFromJsonFile();
        BillItem billItem = new BillItem();
        billItem.setItemName(productNameJFXTextField.getText());
        billItem.setDescription("HARDCODE");
        billItem.setAmount(Integer.parseInt(amountJFXTextField.getText()));
        billItem.setPrice(Double.parseDouble(priceJFXTextField.getText().replaceAll(",", ".")));
        billItem.setTax(Double.parseDouble(taxJFXTextField.getText().replaceAll(",", ".")));
        billItem.setCode("HARCODE");
        billItemList.add(billItem);
        String listInJSon = jsonFactory.listToJSon(billItemList);
        jsonFactory.saveData(listInJSon);
        addProductJFXButton.setDisable(true);
    }
}
