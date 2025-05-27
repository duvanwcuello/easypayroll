package local.co.GestionParametrosLegales;

import java.util.InputMismatchException;
import java.util.Scanner;

import local.co.GestionParametrosLegales.ParametrosLegalesGenerales.conceptosLegales;
import local.co.GestionUtilidades.FormateadorTextro;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;



public class EditarParametrosLegales {

    
    public static void actualizarSalarioMinimo(){

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("---------------------------");
        System.out.println("|      ¡ADVERTENCIA!      |");
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("Este parametro es un requisito legal y Afecta   \n- Salarios Actuales\n- Calculo Novedades.\n- Generacion de Nominaa.\n        ");
        System.out.println("El Salario Minimo Actual es: "+ FormateadorTextro.formatearMoneda(conceptosLegales.getSalarioMinimo()));
        
        boolean continuar = true;
        while (continuar) {
            try{
                while (continuar){ 
                    double salarioMinimoActual = conceptosLegales.getSalarioMinimo();
                    System.out.print("Defina Nuevo salario mínimo: ");         
                    double nuevoSalarioMinimo = scanner.nextDouble();

                    if (salarioMinimoActual == nuevoSalarioMinimo) {
                        System.out.println("El salario Minino no sufrio Actualizacion\n");
                        SimulacionPrograma.continuarConTeclado();
                        LimpiarPantalla.limpiarConsola();                          
                    }else if (salarioMinimoActual > nuevoSalarioMinimo){
                        System.out.println("EL salario Minimo Ingresado No puede ser Inferior al actual");
                        System.out.println("");
                        System.out.println("INTENTE DE NUEVO.");
                        SimulacionPrograma.continuarPrograma();
                        actualizarSalarioMinimo();
                    }else if(salarioMinimoActual < nuevoSalarioMinimo ){
                        conceptosLegales.setSalarioMinimo(nuevoSalarioMinimo);
                        System.out.println("Salario mínimo actualizado.");
                    } 
                }  
            }catch(InputMismatchException e){
                System.err.println("Tipo de Datos No Validos.");
                System.err.println("Ingrese Valores Enteros.");
                SimulacionPrograma.continuarConTeclado();
                LimpiarPantalla.limpiarConsola();
                actualizarSalarioMinimo();
            }
        }
    }
}
