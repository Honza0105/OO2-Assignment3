package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ProperFormats {
    public static String bigDecimalProperFormat(BigDecimal bigDecimal) {
        DecimalFormat decimalFormat = new DecimalFormat("#,### €");
        return decimalFormat.format(bigDecimal);
    }
}
