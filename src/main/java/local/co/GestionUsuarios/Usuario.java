package local.co.GestionUsuarios;

/**
     * Clase usuario 
     */
    public class Usuario {
        private String nombreEmpleado;
        private String nombreUsuario;
        private String contrasenia;
        private String rol;

        public void setNombreEmpleado(String nombreEmpleado) {
            this.nombreEmpleado = nombreEmpleado;
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

        public void setRol(String rol) {
            this.rol = rol;
        }

        public String getNombreEmpleado() {
            return nombreEmpleado;
        }

        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public String getContrasena() {
            return contrasenia;
        }

        public String getRol() {
            return rol;
        }

          public Usuario(String nombreEmpleado, String nombreUsuario, String contrasena, String rol) {
            this.nombreEmpleado = nombreEmpleado;
            this.nombreUsuario = nombreUsuario;
            this.contrasenia = contrasena;
            this.rol = rol;
        }
    }

