package local.co.EasyPayroll.gestionNomina;
import local.co.EasyPayroll.gestionParametrosLegales.parametrosLegalesGenerales;

public class calculoPrimas {
    
    // funcion para calcular Primas.
    public static double calculoPrimas(double salarioEmpleado, int diasLaborados){
           
       double salarioMinimo = parametrosLegalesGenerales.conceptosLegales.getSalarioMinimo();
       double auxTransporte = parametrosLegalesGenerales.conceptosLegales.getAuxTransporte();

       if (salarioEmpleado <= (salarioMinimo * 2)){       
           double primaEmpleado = (((salarioEmpleado + auxTransporte) * diasLaborados) / 360);
           return primaEmpleado;
           }else{

            double primaEmpleado = ((salarioEmpleado  * diasLaborados) / 360);
            return primaEmpleado;
        }
    }
}
