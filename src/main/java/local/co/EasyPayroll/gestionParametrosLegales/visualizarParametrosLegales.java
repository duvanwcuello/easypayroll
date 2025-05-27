package local.co.EasyPayroll.gestionParametrosLegales;

import local.co.EasyPayroll.gestionParametrosLegales.ParametrosLegalesGenerales.conceptosLegales;
import local.co.EasyPayroll.gestionUtilidades.FormateadorTextro;
import local.co.EasyPayroll.gestionUtilidades.LimpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.SimulacionPrograma;

public class VisualizarParametrosLegales {

    /**
     * permite mostrar los conceptos legales que estan parametrizados en el sistema
     * estos parametros son configurados anualmente de acuerdo a la reglamentacion 
     * legal dictada por el gobierno nacional
     */
    public static void parametosLegalesActuales (){


        //formateamos el texto para mostrar los conceptos legales
        double porcentajeSaludEmpleado = conceptosLegales.getSaludEmpleado();
        double porcentajePensionEmpleado = conceptosLegales.getPensionEmpleado();
        double porcentajeSaludEmpleador = conceptosLegales.getSaludEmpleador();
        double porcentajePensionEmpleador = conceptosLegales.getPensionEmpleador();

   
        
        System.out.println("-------------------------------------------------------------");
        System.out.println("|          PARAMETROS LEGALES  DEL SISTEMA.                  |");
        System.out.println("--------------------------------------------------------------");
        System.out.println(" Los parametos legales actualmente configurados en el       \n"+
                           " sistema se muestran a continuacion, estos son actualizados \n"+
                           " anualmente por el area encargada                           ");
        System.out.println("--------------------------------------------------------------");

        System.out.println("| Salario mínimo actual:  \t| " + FormateadorTextro.formatearMoneda(conceptosLegales.getSalarioMinimo())+"\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Salario integral actual: \t| " + FormateadorTextro.formatearMoneda(conceptosLegales.getSalarioIntegral())+"\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Auxilio de transporte: \t| " + FormateadorTextro.formatearMoneda(conceptosLegales.getAuxTransporte())+"\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Salud - Empleado: \t\t| " + FormateadorTextro.formatoPorcentaje(porcentajeSaludEmpleado)+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Salud - Empleador: \t\t| " + FormateadorTextro.formatoPorcentaje(porcentajePensionEmpleador)+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Pensión - Empleado: \t\t| " + FormateadorTextro.formatoPorcentaje(porcentajePensionEmpleado)+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Pensión - Empleador: \t\t| " + FormateadorTextro.formatoPorcentaje(porcentajePensionEmpleador)+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Periodicidad nómina (días): \t| " + conceptosLegales.getPeriodicidadNomina()+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|\n");
        SimulacionPrograma.continuarConTeclado();
        LimpiarPantalla.limpiarConsola();
    }
    
}
