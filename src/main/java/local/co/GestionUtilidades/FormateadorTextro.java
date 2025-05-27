package local.co.GestionUtilidades;

import java.text.NumberFormat;
import java.util.Locale;

public class FormateadorTextro {

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