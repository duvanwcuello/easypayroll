/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.GestionNomina;

/**
 * para calcular las noveades tomamos como referencia la inforacion actual 
 * https://www.gerencie.com/horas-extras-y-recargos-nocturnos-dominicales-y-festivos.html
 * @author duvan Wilchez Cuello
 */
public class CalculoNovedades {
    
    //calculamos el valor de la hora Ordinaria del Empleado
    public static double calculoValorHoraEmpleado(double salarioEmpleado){
        
       //el valor de la hora del empleado la calculamos dividiendo el salario del empleado 
       //entre 30 dias calendario y entre 8 horas laborales
       double valorHoraEmpleado = ((salarioEmpleado/30)/8);
       return valorHoraEmpleado;
        
    }
    
    /* La hora extra diurna es la que se labora luego de la jornada laboral, y entre las 6 de la mañana y las 9 de la noche.
     * Calculamos el total de horas extras diurnas laboradas, La hora extra diurna se paga con un recargo
     * del 25% sobre el valor ordinario de la hora
     */
    public static double calculoHorasExtrasDiurnas(double salarioEmpleado, double numeroHorasExtrasDiurnasLaboradas ){
        
        double valorHoraEmpleado = calculoValorHoraEmpleado(salarioEmpleado);
        double totalHorasExtrasDiurnas = ((valorHoraEmpleado * 1.25) * numeroHorasExtrasDiurnasLaboradas);
        
        return totalHorasExtrasDiurnas;
   
    }
    
    /* El trabajo extra nocturno es aquel que cumple dos condiciones:
     *   - labora luego de cumplida la jornada ordinaria.
     *   - Se labora luego de las 9 de la noche y hasta las 6 de la mañana del día siguiente.
     * Calculamos el total de horas extras nocturnas laboradas, La hora extra nocturna se paga con un recargo del 75% sobre el valor
     * ordinario de la hora 
     */
    public static double calculoHorasExtrasNocturnas(double salarioEmpleado, double numeroHorasExtrasNocturnasLaboradas){
        
        double valorHoraEmpleado = calculoValorHoraEmpleado(salarioEmpleado);
        double totalHorasExtrasNocturnas = ((valorHoraEmpleado * 1.75) * numeroHorasExtrasNocturnasLaboradas);
        
        return totalHorasExtrasNocturnas;
   
    }
    
    /* El recargo nocturno es aquel recargo que se paga al trabajador que desarrolla su jornada ordinaria en la noche, 
     * que en Colombia es entre las 9 de la noche y las 6 de la mañana del día siguiente.
     * El recargo corresponde al 35% sobre la hora ordinaria}
     */
    public static double calculoRecargoNocturno(double salarioEmpleado, double numeroHorasNocturnasLaboradas){
                   
        double valorHoraEmpleado = calculoValorHoraEmpleado(salarioEmpleado);
        double totalRecargosNocturnos = (valorHoraEmpleado * 0.35);
            
        return totalRecargosNocturnos;
    }
    
    /* La hora extra diurna dominical o festiva se da cuando se cumplen las siguientes condiciones:
     *  - Se trabaja en un domingo o festivo.
     *  - Se trabajan más de 8 horas diarias si esa es la jornada ordinaria.
     * En este caso, existen dos recargos individuales que se deben sumar:
     *   - Recargo por trabajo extra diurno del 25%
     *   - Recargo por trabajo dominical o festivo del 75%
     * El valor de la hora extra dominical o festiva es del 200%.
     */
    public static double calculoHorasExtrasDiurnasDomYFest(double salarioEmpleado, int diasLaborados, double numeroHorasExtrasDiurnasDomyFestLaboradas){
        
        double valorHoraEmpleado = calculoValorHoraEmpleado(salarioEmpleado);
        double totalHorasExtrasDiurnasDomyFest = ((valorHoraEmpleado * 2.0) * numeroHorasExtrasDiurnasDomyFestLaboradas);
        
        return totalHorasExtrasDiurnasDomyFest;
    }
    
     /* La hora extra nocturna dominical o festiva se configura cuando se cumplen tres condiciones:
      *      - Se trabaja en un domingo o festivo.
      *      - Se trabaja tiempo extra o suplementario.
      *      - El trabajo extra se desarrolla en horario nocturno (9 p.m. – 6 a.m.).
      *  En este caso igualmente concurren dos recargos que se deben sumar o acumular:
      *      - Recargo extra nocturno del 75%
      *      - Recargo dominical o festivo del 75%
      *  Es decir que tenemos un recargo del 150%, 
      */    
    public static double calculoHorasExtrasNocturnasDomYFest(double salarioEmpleado, int diasLaborados, double numeroHorasExtrasNocturnasDomyFestLaboradas){
        
        double valorHoraEmpleado = calculoValorHoraEmpleado(salarioEmpleado);
        double totalHorasExtrasNocturnasDomyFest = ((valorHoraEmpleado * 1.50) * numeroHorasExtrasNocturnasDomyFestLaboradas);
        
        return totalHorasExtrasNocturnasDomyFest;
    }
    
    /*El recargo dominical y festivo es aquel recargo al trabajador que, por una u otra razón, debe laborar un domingo o un festivo. 
     * El recargo por trabajar una jornada ordinaria en un domingo o festivo es del 75% sobre la hora ordinaria, 
     * por el solo hecho de trabajar en esos días.
     */
    public static double calculoRecargoDomYFest(double salarioEmpleado, int diasLaborados, double numeroHorasDomyFestLaboradas){
        
        double valorHoraEmpleado = calculoValorHoraEmpleado(salarioEmpleado);
        double totalRecargoDiurnoDomYFest = (valorHoraEmpleado * 0.75);
        
        return totalRecargoDiurnoDomYFest;
    }
    
   /* Para que se configure una hora dominical o festiva nocturna, el trabajador debe cumplir dos condiciones:
    *    - Trabajar la jornada ordinaria en un domingo o un festivo.
    *    - Trabajar en horario nocturno (9 p.m. – 6 a.m.).
    *  Aquí concurren dos recargos que se acumulan:
    *    - Recargo dominical o festivo del 75%
    *    - Recargo nocturno del 35%
    *  Esto nos da un recargo total del 110% sobre el valor ordinario de la hora.
    */
    public static double calculoRecargoNocturnoDomYFest(double salarioEmpleado, int diasLaborados, double numeroHorasNocturnasDomYFestLaboradas){
            
        double valorHoraEmpleado = calculoValorHoraEmpleado(salarioEmpleado);
        double totalHorasExtrasDominicales = (valorHoraEmpleado * 1.10);
        
        return totalHorasExtrasDominicales;
    }
}
