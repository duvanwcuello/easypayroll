package local.co.EasyPayroll.gestionUtilidades;

import java.util.Scanner;

public class simulacionPrograma {

    //para usar en el loggin y salida del programa
    public static void continuarPrograma(){
        try {   
                // Pausamos por 1500 milisegundos (1.5 segundos)
                        Thread.sleep(1500); 
            } catch (InterruptedException e) {
                System.out.println("------ Interrumpida.");
            }
    }

    public  static void continuarConTeclado(){
        Scanner scanner = new Scanner (System.in);
        System.out.print("Presione ENTER para Retornar... ");
        scanner.nextLine();
    }

    // para usarse en el retorno al los menu
     public static void simulaEjecucion(){
             try {
                // Pausar por 500 milisegundos (0.30 segundos)
                Thread.sleep(500); 
                limpiarPantalla.limpiarConsola(); 
            } catch (InterruptedException e) {
                System.out.println("------ Interrumpida.");
            }
        
    }
}
