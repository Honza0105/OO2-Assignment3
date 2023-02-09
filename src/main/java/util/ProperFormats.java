package util;

import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class ProperFormats {
    public static String bigDecimalProperFormat(BigDecimal bigDecimal) {
        DecimalFormat decimalFormat = new DecimalFormat("#,### €");
        return decimalFormat.format(bigDecimal);
    }

    public static boolean positiveDecimalFormat(String input){
        if (input.equals("0")){
            return false;
        }
        Pattern positiveDecimal = Pattern.compile("^\\d+(\\.\\d+)?$");
        return positiveDecimal.matcher(input).find();
    }

    public static boolean positiveDoubleFormat(String input){
        if (input.equals("0")){
            return false;
        }
        Pattern positiveDecimal = Pattern.compile("^\\d+(\\.\\d+)?$");
        return positiveDecimal.matcher(input).find();
    }

    public static boolean positiveIntegerFormat(String input){
        if (input.equals("0")){
            return false;
        }
        Pattern positiveInteger = Pattern.compile("^\\d+$");
        return positiveInteger.matcher(input).find();
    }

    public static String isSharedInputValid(TextField nameField, TextField descriptionField, TextField valueField, TextField rentPerWeekField){
        Pattern positiveDecimal = Pattern.compile("^\\d+(\\.\\d+)?$");
        String alertMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            alertMessage += "No valid name!\n";
        }
        if (descriptionField.getText() == null || descriptionField.getText().length() == 0) {
            alertMessage += "No valid description!\n";
        }

        if (!positiveDecimal.matcher(valueField.getText()).find()){
            alertMessage += "Value can only contain decimals!\n";
        }

        if (!positiveDecimal.matcher(rentPerWeekField.getText()).find()){
            alertMessage += "Rent per week can only contain decimals!\n";
        }
        return alertMessage;
    }

}
