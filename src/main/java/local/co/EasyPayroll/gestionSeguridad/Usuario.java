package local.co.EasyPayroll.gestionSeguridad;

/**
     * Clase usuario 
     */
    class Usuario {
        private String nombreEmpleado;
        private String nombreUsuario;
        private String contrasenia;
        private String rol;

        Usuario(String nombreEmpleado, String nombreUsuario, String contrasena, String rol) {
            this.nombreEmpleado = nombreEmpleado;
            this.nombreUsuario = nombreUsuario;
            this.contrasenia = contrasena;
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
    }

