package local.co.EasyPayroll.gestionParametrosGenerales;

import java.util.Scanner;

/**
 * Esta clase centraliza los parámetros legales y financieros
 * para el cálculo de la nómina.
 * @author Duván
 */

 public class parametrosGenerales {

    

    /**
     * Contiene constantes legales aplicables al sistema de nómina.
     */
    public static class conceptosLegales {
        // Valores de ley (2024)
        private static double SALARIO_MINIMO = 1423500;
        private static double SALARIO_INTEGRAL = 18505500;
        private static double AUX_TRANSPORTE = 200000;

        // Porcentajes de aportes a salud y pensión
        private static double SALUD_EMPLEADOR = 0.085;  // Correcto: 8.5%
        private static double SALUD_EMPLEADO = 0.04;    // Correcto: 4%

        private static double PENSION_EMPLEADOR = 0.12;
        private static double PENSION_EMPLEADO = 0.04;

        // Periodicidad estándar de la nómina (15 o 30 días)
        private static int PERIODICIDAD_NOMINA = 15;

        // Métodos para acceder desde otras clases
        public static double getSalarioMinimo(){ 
            return SALARIO_MINIMO; 
        }
        public static double getSalarioIntegral(){ 
            return SALARIO_INTEGRAL; 
        }
        public static double getAuxTransporte(){ 
            return AUX_TRANSPORTE; 
        }
        public static double getSaludEmpleador(){ 
            return SALUD_EMPLEADOR;
        }
        public static double getSaludEmpleado(){ 
            return SALUD_EMPLEADO; 
        }
        public static double getPensionEmpleador(){ 
            return PENSION_EMPLEADOR; 
        }
        public static double getPensionEmpleado(){ 
            return PENSION_EMPLEADO; 
        }
        public static int getPeriodicidadNomina(){ 
            return PERIODICIDAD_NOMINA; 
        }
       

        // Setters
        public static void setSalarioMinimo(double salarioMinimo) {
            SALARIO_MINIMO = salarioMinimo;
        }
        public static void setSalarioIntegral(double salarioIntegral) {
            SALARIO_INTEGRAL = salarioIntegral;
        }
        public static void setAuxTransporte(double auxTransporte) {
            AUX_TRANSPORTE = auxTransporte;
        }
        public static void setPeriodicidadNomina(int periodicidad) {
            PERIODICIDAD_NOMINA = periodicidad;
        }
        public static void setSaludEmpleador(double saludEmpleador) {
            SALUD_EMPLEADOR = saludEmpleador;
        }
        public static void setSaludEmpleado(double saludEmpleado) {
            SALUD_EMPLEADO = saludEmpleado;
        }
        public static void setPensionEmpleador(double pensionEmpleador) {
            PENSION_EMPLEADOR = pensionEmpleador;
        }
        public static void setPensionEmpleado(double pensionEmpleado) {
            PENSION_EMPLEADO = pensionEmpleado;
        }
    }

    /**
     * Representa todos los conceptos de devengo que recibe un empleado.
     * Ejemplo: sueldo, horas extra, subsidios, etc.
     */
    public class conceptosDevengos {
        private double sueldoBasico;
        private double subsidioTransporte;
        private double sostenimiento;
        private double recargoNocturno;
        private double horasDominicales;
        private double recargoNoctFestivo;
        private double horasExtraDiurnas;
        private double horasExtraNocturnas;
        private double horasExtraDomDiurnas;
        private double horasExtraDomNocturnas;
        private double sueldoRetroactivo;
        private double horasCompensadas;
        private double salarioIntegral;

        // Constructor completo
        public conceptosDevengos(double sueldoBasico, double subsidioTransporte, double sostenimiento, double recargoNocturno,
                                 double horasDominicales, double recargoNoctFestivo, double horasExtraDiurnas, double horasExtraNocturnas,
                                 double horasExtraDomDiurnas, double horasExtraDomNocturnas, double sueldoRetroactivo,
                                 double horasCompensadas, double salarioIntegral) {
            this.sueldoBasico = sueldoBasico;
            this.subsidioTransporte = subsidioTransporte;
            this.sostenimiento = sostenimiento;
            this.recargoNocturno = recargoNocturno;
            this.horasDominicales = horasDominicales;
            this.recargoNoctFestivo = recargoNoctFestivo;
            this.horasExtraDiurnas = horasExtraDiurnas;
            this.horasExtraNocturnas = horasExtraNocturnas;
            this.horasExtraDomDiurnas = horasExtraDomDiurnas;
            this.horasExtraDomNocturnas = horasExtraDomNocturnas;
            this.sueldoRetroactivo = sueldoRetroactivo;
            this.horasCompensadas = horasCompensadas;
            this.salarioIntegral = salarioIntegral;
        }

        // Getters y setters 
        public double getSueldoBasico(){ 
            return sueldoBasico; 
        } 
        public void setSueldoBasico(double sueldoBasico){ 
            this.sueldoBasico = sueldoBasico; 
        }
        
        public double getSubsidioTransporte(){ 
            return subsidioTransporte; 
        }
        public void setSubsidioTransporte(double subsidioTransporte){ 
            this.subsidioTransporte = subsidioTransporte; 
        }
        
        public double getSostenimiento(){ 
            return sostenimiento; 
        }
        public void setSostenimiento(double sostenimiento){ 
            this.sostenimiento = sostenimiento; 
        }
        
        public double getRecargoNocturno(){ 
            return recargoNocturno; 
        }
        public void setRecargoNocturno(double recargoNocturno){ 
            this.recargoNocturno = recargoNocturno; 
        }
        
        public double getHorasDominicales(){ 
            return horasDominicales; 
        }
        public void setHorasDominicales(double horasDominicales){ 
            this.horasDominicales = horasDominicales; 
        }
        
        public double getRecargoNoctFestivo(){ 
            return recargoNoctFestivo; 
        }
        public void setRecargoNoctFestivo(double recargoNoctFestivo){ 
            this.recargoNoctFestivo = recargoNoctFestivo; 
        }
       
        public double getHorasExtraDiurnas(){ 
            return horasExtraDiurnas; 
        }
        public void setHorasExtraDiurnas(double horasExtraDiurnas){ 
            this.horasExtraDiurnas = horasExtraDiurnas; 
        }
       
        public double getHorasExtraNocturnas(){ 
            return horasExtraNocturnas; 
        }
        public void setHorasExtraNocturnas(double horasExtraNocturnas){ 
            this.horasExtraNocturnas = horasExtraNocturnas; 
        }
       
        public double getHorasExtraDomDiurnas(){
            return horasExtraDomDiurnas; 
        }
        public void setHorasExtraDomDiurnas(double horasExtraDomDiurnas){ 
            this.horasExtraDomDiurnas = horasExtraDomDiurnas; 
        }

        public double getHorasExtraDomNocturnas(){ 
            return horasExtraDomNocturnas;
        }
        public void setHorasExtraDomNocturnas(double horasExtraDomNocturnas){ 
            this.horasExtraDomNocturnas = horasExtraDomNocturnas; 
        }

        public double getSueldoRetroactivo(){ 
            return sueldoRetroactivo;
        }
        public void setSueldoRetroactivo(double sueldoRetroactivo){ 
            this.sueldoRetroactivo = sueldoRetroactivo; 
        }

        public double getHorasCompensadas(){ 
            return horasCompensadas; 
        }
        public void setHorasCompensadas(double horasCompensadas){ 
            this.horasCompensadas = horasCompensadas; 
        }

        public double getSalarioIntegral(){ 
            return salarioIntegral; 
        }
        public void setSalarioIntegral(double salarioIntegral){ 
            this.salarioIntegral = salarioIntegral; 
        }
    }

    /**
     * Representa conceptos de descuento como salud y pensión.
     */
    public class conceptosDescuentos {
        private double salud;
        private double pension;

        public conceptosDescuentos(double salud, double pension) {
            this.salud = salud;
            this.pension = pension;
        }

        public double getSalud() { return salud; }
        public void setSalud(double salud) { this.salud = salud; }

        public double getPension() { return pension; }
        public void setPension(double pension) { this.pension = pension; }
    }
 }

