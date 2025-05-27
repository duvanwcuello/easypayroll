package local.co.EasyPayroll.GestionNovedades;

class Novedad {
    

        String identificacion;
        String descripcion;
        int horasExtras;
        int diasAusencia;

        public Novedad(String identificacion, String descripcion, int horasExtras, int diasAusencia) {
            this.identificacion = identificacion;
            this.descripcion = descripcion;
            this.horasExtras = horasExtras;
            this.diasAusencia = diasAusencia;
        }

        @Override
        public String toString() {
            return identificacion + "," + descripcion + "," + horasExtras + "," + diasAusencia;
        }
    }


 class Contrato {
        String identificacion;
        double salario;

        public Contrato(String identificacion, double salario) {
            this.identificacion = identificacion;
            this.salario = salario;
        }
    }

