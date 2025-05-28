package local.co;

import local.co.GestionSeguridad.Loggin;
import local.co.GestionSeguridad.MenuUsuarios;
import local.co.GestionUsuarios.NuevoUsuario;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.MensajesparaUsuarios;

public class Aplicacion {
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
        LimpiarPantalla.limpiarConsola();
        
        // Llamamos al metodo que muestra la bienvenida
        MensajesparaUsuarios.mostrarBienvenida();

        //Solicitamos Inicio de Sesion al usuario
        Loggin.solicitarDatosSesionInicial();

        //Cargamos el menu del usuario
        MenuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);

    }

    private static void pruebasModulos(String rolUsuarioIngresado, String usuarioEnSesion){

        //prueba gestion de nomina
        //gestionNominas.gestionNomina();
        
        // GESTION CONTRATOS 
        //gestionContratos.gestionContrato();

        // GESTION EMPLEADO 
        // gestionEmpleados.gestionEmpleado(usuarioEnSesion);

        // editarUsuarios.editarUsuarioExistente();
        // NuevoUsuario.obtenerPrimerIdDisponible();

        // gestiondeContrasenias.cambiarContraseñiaUsuario();

        // Solicitamos Inicio de Sesion al usuario
        // logginUsuario.solicitarDatosSesionInicial();
                
        // Cargamos el menu del usuario
        // menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);
        
        // menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);;
    
    }
   
}


    