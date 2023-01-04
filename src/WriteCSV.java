import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WriteCSV {
	public static final String DELIMITADOR = ",";
	
	
	//Funcion para limpiar el archivo de la partida guardada, ya que solo se puede guardar la partida anterior
	public static void limpiarArchivo(String direccionArchivo) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(direccionArchivo));
			BufferedWriter writer = new BufferedWriter(new FileWriter(direccionArchivo));
			String linea = null;
			while ((linea = reader.readLine()) != null) {
				writer.write(linea);
				writer.flush();
				writer.close();
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Funcion para escribir el archivo que guardará la partida
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

	//Funcion que escribirá al archivo top5 de clasificacion
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
			
			ordenarArchivo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	
	//Funcion para ordenar el archivo top5, de tal manera que las partidas con menores intentos se pongan en las primeras lineas
	public static void ordenarArchivo() {
		try {
			//Mete cada linea en una lista de strings
			List<String> lineas = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new FileReader("partidas/top5.csv"))) {
				String linea;
				while ((linea = reader.readLine()) != null) {
					lineas.add(linea);
				}
			}

			// La ordena basandose en la segunda columna
			Collections.sort(lineas, (s1, s2) -> {
				String[] campos1 = s1.split(DELIMITADOR);
				String[] campos2 = s2.split(DELIMITADOR);
				double num1 = Double.parseDouble(campos1[1]);
				double num2 = Double.parseDouble(campos2[1]);
				return Double.compare(num1, num2);
			});

			// Print the sorted list of strings
			try (PrintWriter writer = new PrintWriter("partidas/top5.csv")) {
				for (String linea : lineas) {
					writer.println(linea);
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
