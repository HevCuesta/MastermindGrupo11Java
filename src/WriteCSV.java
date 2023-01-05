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
	
	// Se declaran las variables de todos los colores que se usarán para el juego
    public static final String CYAN = "\033[0;36m○";
    public static final String ROJO = "\033[0;31m○";
    public static final String VERDE = "\033[0;32m○";
    public static final String AMARILLO = "\033[0;33m○";
    public static final String AZUL = "\033[0;34m○";  
    public static final String MORADO = "\033[0;35m○";
    public static final String BLANCO = "\033[0;37m○";
    public static final String NEGRO = "\u001B[30m○";
    public static final String RESET = "\033[0m";
	
	
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
	public static void escribirArchivoGuardar(char[] combinacionSecreta, int intentos, String direccionArchivo) {
		// String que se quiere escribir en el archivo
		String secretoStr = String.valueOf(combinacionSecreta);
		try {
			
			FileWriter fileWriter = new FileWriter(direccionArchivo, true); 														
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(secretoStr + DELIMITADOR + intentos);
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
	
	public static void guardarTablero(char tablero[][], int intentos, char resultadoTablero[][], String direccionArchivo) {

		try {
		FileWriter fileWriter = new FileWriter(direccionArchivo); 														
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		for (int q = 0; q < intentos+1; q++) {
		for (int i = 0; i < 5; i++) {
			
			 switch(tablero[q][i]) {
			 
			 	case 'C': bufferedWriter.write(CYAN + RESET); break; //cyan
				case 'R': bufferedWriter.write(ROJO + RESET); break; //rojo
				case 'V':bufferedWriter.write(VERDE + RESET); break;//verde
				case 'A': bufferedWriter.write(AMARILLO + RESET); break; //amarillo
				case 'Z': bufferedWriter.write(AZUL + RESET); break; //azul
				case 'M': bufferedWriter.write(MORADO + RESET); break; //morado
				case 'B': bufferedWriter.write(BLANCO + RESET); break; //blanco 
			 }
	     	
		}
		
		bufferedWriter.write(' ');
			for (int i = 0; i < 5; i++) switch(resultadoTablero[q][i]) {
				case 'B': bufferedWriter.write(BLANCO + RESET); break;
				case 'N': bufferedWriter.write(NEGRO + RESET); break; 
				case '_': bufferedWriter.write("X"); break;
				
			}
			bufferedWriter.newLine();
		}

		bufferedWriter.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		
		}
		System.out.println();
		}

	}

