/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.gestionEmpleado;

import java.time.*;

class Empleado {
    private int id;
    private String identificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String tipoSangre;
    private String sexo;
    private String estadoCivil;
    private String nivelEstudio;
    private String correoElectronico;
    private String direccionResidencia;
    private String barrio;
    private String ciudad;
    private String departamento;

    public int getId() {
        return id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Empleado(int id, String identificacion, String primerNombre, String segundoNombre, String primerApellido,
                    String segundoApellido, LocalDate fechaNacimiento, String tipoSangre,
                    String sexo, String estadoCivil, String nivelEstudio, String correoElectronico,
                    String direccionResidencia, String barrio, String ciudad, String departamento) {
        this.id = id;
        this.identificacion = identificacion;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.nivelEstudio = nivelEstudio;
        this.correoElectronico = correoElectronico;
        this.direccionResidencia = direccionResidencia;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.departamento = departamento;
    }
}

    
   