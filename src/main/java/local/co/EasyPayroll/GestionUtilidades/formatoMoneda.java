package local.co.EasyPayroll.gestionUtilidades;

import java.text.NumberFormat;
import java.util.Locale;

public class formatoMoneda {

  public static String formatear(double cantidad) {
    
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return formatoMoneda.format(cantidad);
    }
}