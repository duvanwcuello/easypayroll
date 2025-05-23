package local.co.EasyPayroll;

import local.co.EasyPayroll.GestionUsuario.gestionUsuarios;
import local.co.EasyPayroll.GestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.GestionUtilidades.mensajesparaUsuarios;
import local.co.EasyPayroll.gestionNomina.procesarNominaMes;
import local.co.EasyPayroll.gestionSeguridad.logginUsuario;
import local.co.EasyPayroll.gestionSeguridad.menuPorRolUsuario;

public class aplicacion {
    public static void main(String[] args) {

         String rolUsuarioIngresado= "administrador" ;
         String usuarioEnSesion = null;
        
        //  Produccion
        //  ejecutandoProduccion(rolUsuarioIngresado);

        //  pruebas
            pruebasModulos(rolUsuarioIngresado, usuarioEnSesion);

    

    }


        private static void ejecutandoProduccion(String rolUsuarioIngresado){
                //Variables globales
            

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

        private static void pruebasModulos(String rolUsuarioIngresado, String usuarioEnSesion){

            //prueba gestion de nomina
            //gestionNominas.gestionNomina();
            
            //GESTION CONTRATOS TODO OK
            //gestionContratos.gestionContrato();

            //GESTION EMPLEADO TODO OK
            //gestionEmpleados.gestionEmpleado(usuarioEnSesion);

            //Solicitamos Inicio de Sesion al usuario
            //logginUsuario.solicitarDatosSesionInicial();
                    
            //Cargamos el menu del usuario
            // menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);
            
             menuPorRolUsuario.menuPrincipalUsuario(rolUsuarioIngresado);;
        
        }
   
}


    