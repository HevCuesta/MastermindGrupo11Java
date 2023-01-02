import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.Arrays;

public class WriteCSV {
	public static final String DELIMITADOR = ",";
//TODO: FUNCION PARA ORDENAR LOS INTENTOS DE MEJOR A PEOR
	public static void escribirArchivo(String nombreUsuario, int intentos, String direccionArchivo) {
		// String que se quiere escribir en el archivo
		String linea = nombreUsuario.toUpperCase() + DELIMITADOR + " " + intentos;
		try {
			FileWriter fileWriter = new FileWriter(direccionArchivo, true); 														
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(linea);
			bufferedWriter.newLine();

			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void escribirArchivoGuardar(char[] guess, int intentos, String direccionArchivo) {
		// String que se quiere escribir en el archivo
		String guessStr = String.valueOf(guess);
		try {
			FileWriter fileWriter = new FileWriter(direccionArchivo, true); 														
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(guessStr + DELIMITADOR + intentos);
			bufferedWriter.newLine();

			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void flush(String direccionArchivo) {
		try {
			FileWriter fileWriter = new FileWriter(direccionArchivo, true); 														
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int numLineasArchivo(String direccionArchivo) {
		//Se cuentan el numero de lineas para no hacer print a mas de cinco.
		int lineas = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(direccionArchivo))) {
			 while (reader.readLine() != null) lineas++;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lineas;
	}
}
