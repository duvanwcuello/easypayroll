package local.co.EasyPayroll.GestionContrato;
import java.time.*;

class Contrato {

    // Atributos de la clase Contrato
    private int idContrato;
    private String estado;
    private String numeroContrato;
    private String idEmpleado;
    private String identificacionEmpleado;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String direccionResidenciaEmpleado;
    private String barrioEmpleado;
    private String ciudadEmpleado;
    private String departamentoEmpleado;
    private String tipoContrato;
    private LocalDate fechaInicioContrato;
    private String cargoEmpleado;
    private Double salarioEmpleado;

    // Getters y setters para cada atributo del contrato
    public int getIdContrato(){ 
        return idContrato; 
    }
    public void setIdContrato(int idContrato){ 
        this.idContrato = idContrato; 
    }

    public String getEstado(){ 
        return estado; 
    }
    public void setEstado(String estado){ 
        this.estado = estado; 
    }

    public String getNumeroContrato(){ 
        return numeroContrato; 
    }
    public void setNumeroContrato(String numeroContrato){ 
        this.numeroContrato = numeroContrato; 
    }

    public String getIdEmpleado(){ 
        return idEmpleado; 
    }
    
    public void setIdEmpleado(String idEmpleado){ 
        this.idEmpleado = idEmpleado; 
    }

    public String getIdentificacionEmpleado(){ 
        return identificacionEmpleado; }
    
    public void setIdentificacionEmpleado(String identificacionEmpleado){ 
        this.identificacionEmpleado = identificacionEmpleado; 
    }

    public String getPrimerNombre(){ 
        return primerNombre; 
    }

    public void setPrimerNombre(String primerNombre){ 
        this.primerNombre = primerNombre; 
    }

    public String getSegundoNombre(){ 
        return segundoNombre; 
    }

    public void setSegundoNombre(String segundoNombre){
         this.segundoNombre = segundoNombre; 
    }

    public String getPrimerApellido(){ 
        return primerApellido; 
    }
    public void setPrimerApellido(String primerApellido){ 
        this.primerApellido = primerApellido; 
    }

    public String getSegundoApellido(){ 
        return segundoApellido; 
    }

    public void setSegundoApellido(String segundoApellido){ 
        this.segundoApellido = segundoApellido; 
    }

    public LocalDate getFechaNacimiento(){ 
        return fechaNacimiento; 
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento){ 
        this.fechaNacimiento = fechaNacimiento; 
    }

    public String getDireccionResidenciaEmpleado(){ 
        return direccionResidenciaEmpleado; 
    }

    public void setDireccionResidenciaEmpleado(String direccionResidenciaEmpleado){ 
        this.direccionResidenciaEmpleado = direccionResidenciaEmpleado; 
    }

    public String getBarrioEmpleado(){ 
        return barrioEmpleado; 
    }

    public void setBarrioEmpleado(String barrioEmpleado){ 
        this.barrioEmpleado = barrioEmpleado; 
    }

    public String getCiudadEmpleado(){ 
        return ciudadEmpleado; 
    }

    public void setCiudadEmpleado(String ciudadEmpleado){ 
        this.ciudadEmpleado = ciudadEmpleado; 
    }

    public String getDepartamentoEmpleado(){ 
        return departamentoEmpleado; 
    }

    public void setDepartamentoEmpleado(String departamentoEmpleado){ 
        this.departamentoEmpleado = departamentoEmpleado; 
    }

    public String getTipoContrato(){ 
        return tipoContrato; 
    }

    public void setTipoContrato(String tipoContrato){ 
        this.tipoContrato = tipoContrato; 
    }

    public LocalDate getFechaInicioContrato(){ 
        return fechaInicioContrato; 
    }

    public void setFechaInicioContrato(LocalDate fechaInicioContrato){ 
        this.fechaInicioContrato = fechaInicioContrato; 
    }

    public String getCargoEmpleado(){ 
        return cargoEmpleado; 
    }

    public void setCargoEmpleado(String cargoEmpleado){ 
        this.cargoEmpleado = cargoEmpleado; 
    }

    public Double getSalarioEmpleado(){ 
        return salarioEmpleado; 
    }

    public void setSalarioEmpleado(Double salarioEmpleado){ 
        this.salarioEmpleado = salarioEmpleado; 
    }

    public String toString() {

        return idContrato + "," + estado + "," + numeroContrato + "," + idEmpleado + "," + identificacionEmpleado + "," +
               primerNombre + "," + segundoNombre + "," + primerApellido + "," + segundoApellido + "," +
               fechaNacimiento + "," + direccionResidenciaEmpleado + "," + barrioEmpleado + "," + ciudadEmpleado + "," +
               departamentoEmpleado + "," + tipoContrato + "," + fechaInicioContrato + "," + cargoEmpleado + "," + salarioEmpleado;
    }
}