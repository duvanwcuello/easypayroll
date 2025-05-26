package local.co.EasyPayroll.gestionParametrosLegales;

import java.util.InputMismatchException;
import java.util.Scanner;

import local.co.EasyPayroll.gestionParametrosLegales.parametrosLegalesGenerales.conceptosLegales;
import local.co.EasyPayroll.gestionUtilidades.formateadorTextro;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class editarParametrosLegales {

    
    public static void actualizarSalarioMinimo(){

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("---------------------------");
        System.out.println("|      ¡ADVERTENCIA!      |");
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("Este parametro es un requisito legal y Afecta   \n- Salarios Actuales\n- Calculo Novedades.\n- Generacion de Nominaa.\n        ");
        System.out.println("El Salario Minimo Actual es: "+ formateadorTextro.formatearMoneda(conceptosLegales.getSalarioMinimo()));
        
        boolean continuar = true;
        while (continuar) {
            try{
                while (continuar){ 
                    double salarioMinimoActual = conceptosLegales.getSalarioMinimo();
                    System.out.print("Defina Nuevo salario mínimo: ");         
                    double nuevoSalarioMinimo = scanner.nextDouble();

                    if (salarioMinimoActual == nuevoSalarioMinimo) {
                        System.out.println("El salario Minino no sufrio Actualizacion\n");
                        simulacionPrograma.continuarConTeclado();
                        limpiarPantalla.limpiarConsola();                          
                    }else if (salarioMinimoActual > nuevoSalarioMinimo){
                        System.out.println("EL salario Minimo Ingresado No puede ser Inferior al actual");
                        System.out.println("");
                        System.out.println("INTENTE DE NUEVO.");
                        simulacionPrograma.continuarPrograma();
                        actualizarSalarioMinimo();
                    }else if(salarioMinimoActual < nuevoSalarioMinimo ){
                        conceptosLegales.setSalarioMinimo(nuevoSalarioMinimo);
                        System.out.println("Salario mínimo actualizado.");
                    } 
                }  
            }catch(InputMismatchException e){
                System.err.println("Tipo de Datos No Validos.");
                System.err.println("Ingrese Valores Enteros.");
                simulacionPrograma.continuarConTeclado();
                limpiarPantalla.limpiarConsola();
                actualizarSalarioMinimo();
            }
        }
    }
}
