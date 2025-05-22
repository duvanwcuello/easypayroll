package local.co.EasyPayroll;

import local.co.EasyPayroll.gestionSeguridad.logginUsuario;
import local.co.EasyPayroll.gestionSeguridad.menuPorRolUsuario;
import local.co.EasyPayroll.utilidades.limpiarPantalla;
import local.co.EasyPayroll.utilidades.mensajesparaUsuarios;

public class aplicacion {
    public static void main(String[] args) {
        
        //Variables globales
        String rolUsuarioIngresado = null ;

        // Instanciamos la clase limpiarPantalla
        limpiarPantalla.limpiarConsola();
        
        // Llamamos al metodo que muestra la bienvenida
        mensajesparaUsuarios.mostrarBienvenida();
        
        // presionamos una tecla para continuar
       // continuarEjecucionPrograma.continuarPrograma();
        
        // Instanciamos la clase limpiarPantalla
       // limpiarPantalla.limpiarConsola();

        //Solicitamos Inicio de Sesion al usuario
        logginUsuario.solicitarDatosSesionInicial();

        //Limpiamos la pantalla
        limpiarPantalla.limpiarConsola();

        //Cargamos el menu del usuario
        menuPorRolUsuario.menuPrincipalUsuario(rolUsuarioIngresado);

    }

   
}