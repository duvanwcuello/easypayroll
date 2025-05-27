package local.co.EasyPayroll.GestionUtilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class FormatoFecha {
    
    public static LocalDate solicitarFechaNacimiento() {

        Scanner sc = new Scanner(System.in);
        LocalDate fechaNacimiento = null;
        boolean fechaValida = false;

        while (!fechaValida) {

            String inputFecha = sc.nextLine();

            try {

                fechaNacimiento = LocalDate.parse(inputFecha, DateTimeFormatter.ISO_LOCAL_DATE);
                fechaValida = true;

            } catch (DateTimeParseException e) {
                
                System.out.println("Formato inválido. Por favor use YYYY-MM-DD.");
                System.out.print("Ingrese la fecha de nacimiento nuevamente: ");
            }

            sc.close();
        }

        return fechaNacimiento;
    }
}
   

