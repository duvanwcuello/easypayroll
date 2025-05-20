/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.gestionNomina;

import local.co.EasyPayroll.gestionParametrosGenerales.parametrosGenerales;

/**
 * Las cesantías se deben liquidar cuando ocurra uno de los siguientes casos:
 *      - Cuando el contrato de trabajo termine.
 *      - Cuando el trabajador pase de tener un salario normal a un salario integral.
 *      - Cada año con corte a 31 de diciembre.
 * Cuando el contrato de trabajo termine, las cesantías que se liquiden se pagan directamente al trabajador.
 * Las cesantías que se deben liquidar todos los años con corte a 31 de diciembre se consignan en el fondo de cesantías antes del 14 de febrero de cada año.
 * @author Duvan wilchez Cuello
 */

public class calculoCesantias {
    
    /* En la base de liquidación se incluyen todos los conceptos que constituyen salario, como por ejemplo:
           - Salario
           - Comisiones
           - Auxilio de transporte.
           - Horas extras.
           - Recargos nocturnos.
           - Recargos por trabajo dominical y festivo.
       Para calcular las cesantias se utiliza la siguiente formula: Salario base x días laborados/360
    */
    
        
    public static double calculoCesantias(double salarioEmpleado, int diasLaborados){
           
       double salarioMinimo = parametrosGenerales.conceptosLegales.getSalarioMinimo();
       double auxTransporte = parametrosGenerales.conceptosLegales.getAuxTransporte();

       if (salarioEmpleado <= (salarioMinimo * 2)){
           
           double cesantiasEmpleado = (((salarioEmpleado + auxTransporte) * diasLaborados) / 360);
           return cesantiasEmpleado;
           }else{

            double cesantiasEmpleado = ((salarioEmpleado  * diasLaborados) / 360);
            return cesantiasEmpleado;
        }
    }
     
    /*El empleador debe pagar al trabajador intereses sobre las cesantías que haya acumulado cada año, 
     * según el artículo 99 de la ley 50 de 1990, que señala lo siguiente:
     * «El empleador cancelará al trabajador los intereses legales del 12% anual o proporcionales por fracción, 
     * en los términos de las normas vigentes sobre el régimen tradicional de cesantía, con respecto a la suma causada en el año o 
     * en la fracción que se liquide definitivamente.»
     * La fórmula para liquidar los intereses de las cesantías es la siguiente:
     * (Cesantías acumuladas x días trabajados x 0.12 )/360
     */
    
    public static double calculoInterecesCesantias (double cesantiasEmpleado, int diasTrabajados){
        
        double interecesCesantiasEmpleado = ((cesantiasEmpleado * diasTrabajados * 0.12 ) / 360);
        return interecesCesantiasEmpleado;
    }
}

