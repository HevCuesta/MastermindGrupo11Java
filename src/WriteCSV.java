import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;

/**
 * @author JAVIER CHOUZA Y DANIEL CUESTA
 * @since 1.0
 */
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

	/**
	 * @return Borra todas las lineas del archivo
	 * @param direccionArchivo Direccion del archivo
	 */
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

	/**
	 * @return Guarda el archivo con la combinacion secreta y el numero de intentos
	 * @param combinacionSecreta Combinacion secreta a guardar
	 * @param intentos           Numero de intentos de la partida
	 * @param direccionArchivo   Direccion del archivo a guardar
	 */
	public static void escribirArchivoGuardar(char[] combinacionSecreta, int intentos, String direccionArchivo) {
		// String que se quiere escribir en el archivo
		String secretoStr = String.valueOf(combinacionSecreta);

		// System.out.println(secretoStr);

		try {

			FileWriter fileWriter = new FileWriter(direccionArchivo, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			limpiarArchivo(direccionArchivo);
			bufferedWriter.write(secretoStr + DELIMITADOR + intentos);
			// bufferedWriter.newLine();

			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return Escribe el archivo de top5.csv, escribiendo el nombre de usuario y
	 *         sus intentos
	 * @param nombreUsuario    Nombre de usuario introducido
	 * @param intentos         Numero de intentos de ese usuario
	 * @param direccionArchivo Direccion del archivo, en este caso Top5.csv
	 */
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

	/**
	 * @return Ordena el archivo de Top5.csv, haciendo una lista de strings, en el
	 *         que ordena de menor a mayor la segunda columna, que contiene los
	 *         intentos de cada usuario
	 */
	public static void ordenarArchivo() {

		try {
			// Mete cada linea en una lista de strings
			List<String> lineas = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new FileReader("partidas/Top5.csv"))) {
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
			try (PrintWriter writer = new PrintWriter("partidas/Top5.csv")) {
				for (String linea : lineas) {
					writer.println(linea);
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	/**
	 * @return Guarda el tablero en un archivo para
	 * @param tablero          Parte del tablero que representa los intentos
	 * @param intentos         Numero de intentos
	 * @param resultadoTablero Parte del tablero que representa los resultado
	 * @param direccionArchivo Direccion del archivo donde guardar
	 */
	public static void guardarTablero(char tablero[][], int intentos, char resultadoTablero[][],
			String direccionArchivo, boolean guardado) {
		if (guardado == false) {
			try {
				FileWriter fileWriter = new FileWriter(direccionArchivo);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				for (int q = 0; q < intentos + 1; q++) {
					for (int i = 0; i < 5; i++) {

						switch (tablero[q][i]) {

						case 'C':
							bufferedWriter.write(CYAN + RESET);
							break; // cyan
						case 'R':
							bufferedWriter.write(ROJO + RESET);
							break; // rojo
						case 'V':
							bufferedWriter.write(VERDE + RESET);
							break;// verde
						case 'A':
							bufferedWriter.write(AMARILLO + RESET);
							break; // amarillo
						case 'Z':
							bufferedWriter.write(AZUL + RESET);
							break; // azul
						case 'M':
							bufferedWriter.write(MORADO + RESET);
							break; // morado
						case 'B':
							bufferedWriter.write(BLANCO + RESET);
							break; // blanco
						}

					}

					bufferedWriter.write(' ');
					for (int i = 0; i < 5; i++)
						switch (resultadoTablero[q][i]) {
						case 'B':
							bufferedWriter.write(BLANCO + RESET);
							break;
						case 'N':
							bufferedWriter.write(NEGRO + RESET);
							break;
						case '_':
							bufferedWriter.write("X");
							break;

						}
					bufferedWriter.newLine();
				}

				bufferedWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();

			}
			// System.out.println();
		} else {
			try {
				FileWriter fileWriter = new FileWriter(direccionArchivo, true);
				BufferedReader reader = new BufferedReader(new FileReader(direccionArchivo));
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				for (int q = 0; q < intentos + 1; q++) {
					for (int i = 0; i < 5; i++) {

						switch (tablero[q][i]) {

						case 'C':
							bufferedWriter.write(CYAN + RESET);
							break; // cyan
						case 'R':
							bufferedWriter.write(ROJO + RESET);
							break; // rojo
						case 'V':
							bufferedWriter.write(VERDE + RESET);
							break;// verde
						case 'A':
							bufferedWriter.write(AMARILLO + RESET);
							break; // amarillo
						case 'Z':
							bufferedWriter.write(AZUL + RESET);
							break; // azul
						case 'M':
							bufferedWriter.write(MORADO + RESET);
							break; // morado
						case 'B':
							bufferedWriter.write(BLANCO + RESET);
							break; // blanco
						}

					}

					bufferedWriter.write(' ');
					for (int i = 0; i < 5; i++)
						switch (resultadoTablero[q][i]) {
						case 'B':
							bufferedWriter.write(BLANCO + RESET);
							break;
						case 'N':
							bufferedWriter.write(NEGRO + RESET);
							break;
						case '_':
							bufferedWriter.write("X");
							break;

						}
					bufferedWriter.newLine();
				}

				bufferedWriter.close();
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();

			}
		}
	}

	public static boolean nuevaPartidaUsuario(String Usuario) {

		File directorio = new File("partidas/Usuarios/" + Usuario);

		if (!directorio.exists()) {
		}

		if (directorio.mkdirs()) {
			System.out.println("Partida creada");

			return false;

		} else {
			System.out.println("Error al crear la partida. Pruebe a introducir otro nombre.");
			return true;
		}
	}

	public static void borrarPartidaUsuario(String usuario) {

		File directorio = new File("partidas/Usuarios/" + usuario + "/");
		File Tablero = new File("partidas/Usuarios/" + usuario + "/TableroGuardado.csv");
		File Partida = new File("partidas/Usuarios/" + usuario + "/PartidaGuardada.csv");

		if (directorio.exists()) {
			Tablero.delete();
			Partida.delete();
			directorio.delete();
		} else
			System.out.println("No hay carpeta");

		if (!directorio.exists())
			System.out.println("Partida borrada.");

	}

}
