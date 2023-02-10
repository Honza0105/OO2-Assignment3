package util;

import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;
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

    /**
     * Checks whether the input of Longitude is between -180 to 180 degrees.
     * @param input
     * @return True if input is in correct format.
     */
    public static boolean decimalLongitudeFormat(String input){
        if (input.equals("0")){
            return false;
        }
        Pattern decimal = Pattern.compile("^-?(\\d|[1-9]\\d|1[0-7]\\d)(\\.\\d+)?$");
        return decimal.matcher(input).find();
    }

    /**
     * Checks whether the input of Latitude is between -90 to 90 degrees.
     * @param input
     * @return True if input is in correct format.
     */
    public static boolean decimalLatitudeFormat(String input){
        if (input.equals("0")){
            return false;
        }
        Pattern decimal = Pattern.compile("^-?(\\d|[0-8]\\d)(\\.\\d+)?$");
        return decimal.matcher(input).find();
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

        if (!positiveDecimal.matcher(rentPerWeekField.getText()).find() && !Objects.equals(rentPerWeekField.getText(), "")){
            alertMessage += "Rent per week can only contain decimals!\n";
        }
        return alertMessage;
    }

}
