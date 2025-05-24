package local.co.EasyPayroll.gestionNomina;
import local.co.EasyPayroll.gestionParametrosGenerales.parametrosGenerales;

public class calculoPrimas {
    
    // funcion para calcular Primas.
    public static double calculoPrimas(double salarioEmpleado, int diasLaborados){
           
       double salarioMinimo = parametrosGenerales.conceptosLegales.getSalarioMinimo();
       double auxTransporte = parametrosGenerales.conceptosLegales.getAuxTransporte();

       if (salarioEmpleado <= (salarioMinimo * 2)){       
           double primaEmpleado = (((salarioEmpleado + auxTransporte) * diasLaborados) / 360);
           return primaEmpleado;
           }else{

            double primaEmpleado = ((salarioEmpleado  * diasLaborados) / 360);
            return primaEmpleado;
        }
    }
}
