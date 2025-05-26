package local.co.EasyPayroll.gestionUtilidades;

import java.text.NumberFormat;
import java.util.Locale;

public class formateadorTextro {

  public static String formatearMoneda(double cantidad) {
    
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return formatoMoneda.format(cantidad);
    }

    public static String formatoPorcentaje (double cantidad){
      
        NumberFormat formatoPorcentaje = NumberFormat.getPercentInstance(Locale.getDefault());
        formatoPorcentaje.setMinimumFractionDigits(1); 
        return formatoPorcentaje.format(cantidad); 
    }

   
}