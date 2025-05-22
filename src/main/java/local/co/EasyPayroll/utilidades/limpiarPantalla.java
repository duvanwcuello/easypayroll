package local.co.EasyPayroll.utilidades;

public class limpiarPantalla {
    
    public static void limpiarConsola() {
        try {
            // Limpiar la consola en Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Limpiar la consola en Unix/Linux/Mac
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la consola: " + e.getMessage());
        }
    }
}