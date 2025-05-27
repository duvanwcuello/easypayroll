package local.co.GestionNomina;

import local.co.GestionParametrosLegales.ParametrosLegalesGenerales;

public class CalculoCesantias {
    
    /* En la base de liquidación se incluyen todos los conceptos que constituyen salario, como por ejemplo:
           - Salario
           - Comisiones
           - Auxilio de transporte.
           - Horas extras.
           - Recargos nocturnos.
           - Recargos por trabajo dominical y festivo.
       Para calcular las cesantias se utiliza la siguiente formula: Salario base x días laborados/360
    */
            
    public static double calculoCesantias (double salarioEmpleado, int diasLaborados){
           
       double salarioMinimo = ParametrosLegalesGenerales.conceptosLegales.getSalarioMinimo();
       double auxTransporte = ParametrosLegalesGenerales.conceptosLegales.getAuxTransporte();

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

