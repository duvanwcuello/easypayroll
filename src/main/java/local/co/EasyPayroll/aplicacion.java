package local.co.EasyPayroll;

import local.co.EasyPayroll.seguridad.logginUsuario;
import local.co.EasyPayroll.seguridad.menuUsuarios;
import local.co.EasyPayroll.utilidades.ContinuaEnter;
import local.co.EasyPayroll.utilidades.limpiarPantalla;

public class aplicacion {
    public static void main(String[] args) {
        
        //Variables globales
        String rolUsuarioIngresado = null ;
        
        //Instanciamos la clase limpiarPantalla
        limpiarPantalla.limpiarConsola();

        //Llamamos al metodo que muestra la bienvenida
        mostrarBienvenida();

        //presionamos una tecla para continuar
        ContinuaEnter.PressEnter('C');

        //Solicitamos Inicio de Sesion al usuario
        logginUsuario.solicitarDatosSesionInicial();

        //Limpiamos la pantalla
        limpiarPantalla.limpiarConsola();

        //Cargamos el menu del usuario
        menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);

    }

    private static void mostrarBienvenida() {

        System.out.println("|===================================================================|");
        System.out.println("|               BIENVENIDO AL SISTEMA EASY PAYROLL                  |");
        System.out.println("|===================================================================|");
        System.out.println("|       'Una solución inteligente para empresas inteligentes'       |");
        System.out.println("|-------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------|");
        System.out.println("| - Realizar registros de contrataciones.                           |");
        System.out.println("| - Seguridad y control para cada empleado.                         |");
        System.out.println("| - Calcular automáticamente recargos, auxilios y deducciones.      |");
        System.out.println("|-------------------------------------------------------------------|");
        System.out.println("|            ¡Optimiza tu gestión de nómina desde aquí!             |");
        System.out.println("|===================================================================|");
        System.out.println("\n");

    }
}