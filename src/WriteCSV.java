import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class WriteCSV {
	public static final String DELIMITADOR = ",";
	
	public static void escribirArchivo (String nombreUsuario, String intentos, String direccionArchivo) {
		
		BufferedReader Lectura = null;
		FileWriter fichero = null;
		Scanner inputStream = null;
		try{
			fichero = new FileWriter(direccionArchivo);
			Lectura = new BufferedReader(new FileReader(direccionArchivo));
			inputStream = new Scanner(direccionArchivo);
			
			String linea = Lectura.readLine();
			
            while(linea != null){
            	if (inputStream.nextLine() == null) {
                	fichero.write(nombreUsuario + "," + intentos);
            	} else {
                	inputStream.nextLine();
            	}

            }
			
			fichero.close();
			
			System.out.println("¡Escritura finalizada!");
			
			} catch(Exception ex) {
			System.out.println("ERROR: \n"+ ex.getMessage());
			}
	}
}
