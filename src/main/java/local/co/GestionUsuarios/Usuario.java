package local.co.GestionUsuarios;

/**
     * Clase usuario 
     */

     /* 
    * Estructura del Archivo Usuario:
    * 0 Id = idDisponible 
    * 1 Estado Usuario = estadoUsuario
    * 2 Nombre Empleado = nombreEmpleado 
    * 3 Usuario Asignado = nuevoUsuarioAsignado 
    * 4 Contraseña Usuario = contrasenaNuevoUsuario
    * 5 Rol Asignado = rolNuevoUsuario 
    * 6 Fecha de creacion del Usuario = fechaCreacionUsuario
    * 7 Fecha de cambio de contraseña = fechaModificacionContrasena 
    * 8 Ultimo inicio de sesion del usuario en el sistema = ultimoInicioSesion
    */


    public class Usuario {
        private String idUsuario;
        private String estadoUsuario;
        private String nombreEmpleado;
        private String nombreUsuario;
        private String contrasenia;
        private String rolUsuario;
        private String fechaCreacionUsuario;
        private String fechaModificacionContrasena;
        private String ultimoAcceso;

        

          public Usuario(String idUsuario, String estadoUsuario, String nombreEmpleado, String nombreUsuario,
                String contrasenia, String rolUsuario, String fechaCreacionUsuario, String fechaModificacionContrasena,
                String ultimoAcceso) {
            this.idUsuario = idUsuario;
            this.estadoUsuario = estadoUsuario;
            this.nombreEmpleado = nombreEmpleado;
            this.nombreUsuario = nombreUsuario;
            this.contrasenia = contrasenia;
            this.rolUsuario = rolUsuario;
            this.fechaCreacionUsuario = fechaCreacionUsuario;
            this.fechaModificacionContrasena = fechaModificacionContrasena;
            this.ultimoAcceso = ultimoAcceso;
        }



          public String getIdUsuario() {
            return idUsuario;
        }



        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }



        public String getEstadoUsuario() {
            return estadoUsuario;
        }



        public void setEstadoUsuario(String estadoUsuario) {
            this.estadoUsuario = estadoUsuario;
        }



        public String getNombreEmpleado() {
            return nombreEmpleado;
        }



        public void setNombreEmpleado(String nombreEmpleado) {
            this.nombreEmpleado = nombreEmpleado;
        }



        public String getNombreUsuario() {
            return nombreUsuario;
        }



        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }



        public String getContrasenia() {
            return contrasenia;
        }



        public void setContrasenia(String contrasenia) {
            this.contrasenia = contrasenia;
        }



        public String getRolUsuario() {
            return rolUsuario;
        }



        public void setRolUsuario(String rolUsuario) {
            this.rolUsuario = rolUsuario;
        }



        public String getFechaCreacionUsuario() {
            return fechaCreacionUsuario;
        }



        public void setFechaCreacionUsuario(String fechaCreacionUsuario) {
            this.fechaCreacionUsuario = fechaCreacionUsuario;
        }



        public String getFechaModificacionContrasena() {
            return fechaModificacionContrasena;
        }



        public void setFechaModificacionContrasena(String fechaModificacionContrasena) {
            this.fechaModificacionContrasena = fechaModificacionContrasena;
        }



        public String getUltimoAcceso() {
            return ultimoAcceso;
        }



        public void setUltimoAcceso(String ultimoAcceso) {
            this.ultimoAcceso = ultimoAcceso;
        }

    }

