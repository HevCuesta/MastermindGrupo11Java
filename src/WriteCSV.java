import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class WriteCSV {
	public static final String DELIMITADOR = ",";

	public static void escribirArchivo(String nombreUsuario, int intentos, String direccionArchivo) {
		// String que se quiere escribir
		String linea = nombreUsuario.toUpperCase() + DELIMITADOR + " " + intentos + " intentos";
		//Se cuentan el numero de lineas para no tener mas de cinco.
		int lineas = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(direccionArchivo))) {
			 while (reader.readLine() != null) lineas++;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			FileWriter fileWriter = new FileWriter(direccionArchivo, true); // El segundo parametro es true para que se añada al final del archivo
																			
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(linea);
			bufferedWriter.newLine();

			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
