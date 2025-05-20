/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.utilidades;

/**
 *
 * @author sistemas.ctg
 */

import java.text.NumberFormat;
import java.util.Locale;

public class formatoMoneda {
    
    
  public static String formatear(double cantidad) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return formatoMoneda.format(cantidad);
    }
}
    

