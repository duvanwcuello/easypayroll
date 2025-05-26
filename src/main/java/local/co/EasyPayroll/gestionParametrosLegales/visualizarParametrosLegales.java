package local.co.EasyPayroll.gestionParametrosLegales;

import local.co.EasyPayroll.gestionParametrosLegales.parametrosLegalesGenerales.conceptosLegales;
import local.co.EasyPayroll.gestionUtilidades.formateadorTextro;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class visualizarParametrosLegales {

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

        System.out.println("| Salario mínimo actual:  \t| " + formateadorTextro.formatearMoneda(conceptosLegales.getSalarioMinimo())+"\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Salario integral actual: \t| " + formateadorTextro.formatearMoneda(conceptosLegales.getSalarioIntegral())+"\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Auxilio de transporte: \t| " + formateadorTextro.formatearMoneda(conceptosLegales.getAuxTransporte())+"\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Salud - Empleado: \t\t| " + formateadorTextro.formatoPorcentaje(porcentajeSaludEmpleado)+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Salud - Empleador: \t\t| " + formateadorTextro.formatoPorcentaje(porcentajePensionEmpleador)+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Pensión - Empleado: \t\t| " + formateadorTextro.formatoPorcentaje(porcentajePensionEmpleado)+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Pensión - Empleador: \t\t| " + formateadorTextro.formatoPorcentaje(porcentajePensionEmpleador)+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Periodicidad nómina (días): \t| " + conceptosLegales.getPeriodicidadNomina()+"\t\t\t     |");
        System.out.println("|------------------------------------------------------------|\n");
        simulacionPrograma.continuarConTeclado();
        limpiarPantalla.limpiarConsola();
    }
    
}
