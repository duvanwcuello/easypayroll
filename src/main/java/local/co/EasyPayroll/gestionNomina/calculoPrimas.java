package local.co.EasyPayroll.gestionNomina;
import local.co.EasyPayroll.gestionParametrosLegales.ParametrosLegalesGenerales;

public class CalculoPrimas {
    
    // funcion para calcular Primas.
    public static double calculoPrimas(double salarioEmpleado, int diasLaborados){
           
       double salarioMinimo = ParametrosLegalesGenerales.conceptosLegales.getSalarioMinimo();
       double auxTransporte = ParametrosLegalesGenerales.conceptosLegales.getAuxTransporte();

       if (salarioEmpleado <= (salarioMinimo * 2)){       
           double primaEmpleado = (((salarioEmpleado + auxTransporte) * diasLaborados) / 360);
           return primaEmpleado;
           }else{

            double primaEmpleado = ((salarioEmpleado  * diasLaborados) / 360);
            return primaEmpleado;
        }
    }
}
