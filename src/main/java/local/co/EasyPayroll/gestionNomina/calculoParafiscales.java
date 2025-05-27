package local.co.EasyPayroll.gestionNomina;
import local.co.EasyPayroll.gestionParametrosLegales.ParametrosLegalesGenerales;

public class CalculoParafiscales {
    // Porcentajes de aportes parafiscales
    private static final double ICBF = 0.03; // 3%
    private static final double SENA = 0.02; // 2%
    private static final double CAJA_COMPENSACION = 0.04; // 4%

    double salarioMinimo = ParametrosLegalesGenerales.conceptosLegales.getSalarioMinimo();

    public static double TotalParafiscales(double salarioMinimo) {
        double total = salarioMinimo * (ICBF + SENA + CAJA_COMPENSACION);
        return total;
    }

    public static double CalcularICBF(double salarioMinimo) {
        return salarioMinimo * ICBF;
    }

    public static double CalcularSENA(double salarioMinimo) {
        return salarioMinimo * SENA;
    }

    public static double CalcularCajaCompensacion(double salarioMinimo) {
        return salarioMinimo * CAJA_COMPENSACION;
    }
}
