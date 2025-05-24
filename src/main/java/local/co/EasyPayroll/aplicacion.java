package local.co.EasyPayroll;

import local.co.EasyPayroll.gestionSeguridad.logginUsuario;
import local.co.EasyPayroll.gestionSeguridad.menuUsuarios;
import local.co.EasyPayroll.gestionSeguridad.recuperarContrasenia;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.mensajesparaUsuarios;

public class aplicacion {
    public static void main(String[] args) {

        String rolUsuarioIngresado= null;
        String usuarioEnSesion = null;
        //Produccion
          ejecutarProduccion(rolUsuarioIngresado);

        //pruebas
        //pruebasModulos(rolUsuarioIngresado, usuarioEnSesion);

    }

    private static void ejecutarProduccion(String rolUsuarioIngresado){
        
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
        menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);

    }

    private static void pruebasModulos(String rolUsuarioIngresado, String usuarioEnSesion){

        //prueba gestion de nomina
        //gestionNominas.gestionNomina();
        
        //GESTION CONTRATOS TODO OK
        //gestionContratos.gestionContrato();

        //GESTION EMPLEADO TODO OK
        //gestionEmpleados.gestionEmpleado(usuarioEnSesion);

        recuperarContrasenia.recuperarContrasena();

        //Solicitamos Inicio de Sesion al usuario
        //logginUsuario.solicitarDatosSesionInicial();
                
        //Cargamos el menu del usuario
        // menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);
        
        // menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);;
    
    }
   
}


    