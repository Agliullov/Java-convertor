package ru.diti.converter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import javafx.scene.control.TextField;
import ru.diti.converter.enums.Currency;
import ru.diti.converter.repository.CurrencyRepository;
import ru.diti.converter.service.ConverterService;

import java.io.IOException;
import java.math.BigDecimal;

public class ConverterController {
    private final ConverterService converterService = new ConverterService();

    @FXML
    private TextField actionTarget;

    @FXML
    private TextField money;

    @FXML
    private ComboBox<Currency> currencyFrom;

    @FXML
    private ComboBox<Currency> currencyTo;

    public void handleConvertAction(ActionEvent actionEvent) throws IOException {
        if(!validate()) {
        	return;
        }
        try {
            actionTarget.setText(converterService.convert(new BigDecimal(money.getText()),
                    currencyFrom.getValue(), currencyTo.getValue()));
        } catch (IOException exe) {
            actionTarget.setText("Error");
        }
    }


    private boolean validate() {

    	String text = money.getText();
    	float value = 0.0f;
    	try 
    	{
    		value = Float.parseFloat(text);
    	}
    	catch (Exception exe)
    	{
    	    if (!(text == null)) {
                actionTarget.setText("The sum is not a number");
            }
            return false;
    	}
    	if(value <= 0)
    	{
    		actionTarget.setText("The sum is negative");
    		return false;
    	}
    	return true;
    	}

    public void handleReverseButtonAction(ActionEvent actionEvent) {

        Currency A = currencyFrom.getValue();
        currencyFrom.setValue(currencyTo.getValue());
        currencyTo.setValue(A);

    }
}