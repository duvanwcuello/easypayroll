package local.co.EasyPayroll.GestionNomina;

public class EmpleadoNomina {

    private String identificacion;
    private String nombre;
    private double salarioBase;
    public String getidentificacion;

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public EmpleadoNomina(String identificacion, String nombre, double salarioBase) {
        
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
}