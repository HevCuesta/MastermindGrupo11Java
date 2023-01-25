import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author JAVIER CHOUZA Y DANIEL CUESTA
 * @since 1.0
 */
public class ReadCSV {
	public static final String DELIMITADOR = ",";
	/**
	 * @param direccionArchivo Direccion del archivo
	 * @return Array de chars que contiene la combinacion de la partida guardada
	 */
	public static char[] leerCombinacionGuardada(String direccionArchivo) {
		BufferedReader Lectura = null;
		String combinacionGuardada = null;
		char combinacionArray[] = new char[5];
		try {
			Lectura = new BufferedReader(new FileReader(direccionArchivo));
			String linea = Lectura.readLine();
			while (linea != null) {
				String[] campos = linea.split(DELIMITADOR);

				combinacionGuardada = campos[0].toString();
				linea = Lectura.readLine();
			}
			combinacionArray = combinacionGuardada.toCharArray();
		} catch (IOException e) {
			System.err.println("Archivo no encontrado");
		}
		return combinacionArray;
	}

	/**
	 * @return Numero de intentos de la partida guardada
	 * @param direccionArchivo DIreccion del archivos
	 */
	public static int intentosPartidaGuardada(String direccionArchivo) {
		BufferedReader Lectura = null;
		int intentos = 0;
		try {
			Lectura = new BufferedReader(new FileReader(direccionArchivo));
			String linea = Lectura.readLine();

			while (linea != null) {
				String[] campos = linea.split(DELIMITADOR);
				try {
					intentos = Integer.parseInt(campos[1]);
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
				linea = Lectura.readLine();
			}

			Lectura.close();
		} catch (IOException e) {
			System.err.println("Archivo no encontrado");
			return 9999;
		} catch (Exception ex) {
			return 9999;
		}
		return intentos;
	}
	/**
	 * @return tabla top5 (archivo csv)
	 * @param direccionArchivo ubicación del archivo
	 * @param numLineas cantidad de líneas presente en el archivo
	 */
	public static void leerArchivo(String direccionArchivo, int numLineas) {
		// Se abre el archivo en un bufer de lectura
		BufferedReader Lectura = null;
		try {
			File csvFile = new File(direccionArchivo);
			if (csvFile.isFile()) {
				Lectura = new BufferedReader(new FileReader(csvFile));
				// Lector linea
				String linea = Lectura.readLine();
				// numLineas es una variable que permite ver mas resultados
				for (int o = 0; o < numLineas; o++) {
					// Se lee linea a linea, con las comas como delimitadores de campo en cada linea
					String[] campos = linea.split(DELIMITADOR);

					for (int i = 0; i < campos.length; i++) {
						System.out.print(campos[i] + "\t");
					}
					System.out.print("\n");

					// Volver a leer otra línea del fichero para cerrar el while
					linea = Lectura.readLine();

				}
			}
		}

		// En caso de que el archivo no exista
		catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			// Cierro el buffer de lectura
			if (Lectura != null) {
				try {
					Lectura.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
/**
 * @return Imprime el tablero por consola
 * @param direccionArchivo Direccion del archivo guardado
 * @param numLineas Numero de lineas del archivo
 */
	public static void leerTablero(String direccionArchivo, int numLineas) {
		// Se abre el archivo en un bufer de lectura
		BufferedReader Lectura = null;
		try {
			File csvFile = new File(direccionArchivo);
			if (csvFile.isFile()) {
				Lectura = new BufferedReader(new FileReader(csvFile));
				// Lector linea
				String linea;
				// numLineas es una variable que permite ver mas resultados
				while ((linea = Lectura.readLine()) != null) {
					// Se lee linea a linea, con las comas como delimitadores de campo en cada linea
					System.out.print(linea);
					System.out.print("\n");

					// Volver a leer otra línea del fichero para cerrar el while

				}
				Lectura.close();
			}
		}

		// En caso de que el archivo no exista
		catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			// Cierro el buffer de lectura
			if (Lectura != null) {
				try {
					Lectura.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @param direccionArchivo Direccion del archivo
	 * @return Numero de lineas de un archivo
	 */
	public static int numLineasArchivo(String direccionArchivo) {
		// Se cuentan el numero de lineas para no hacer print a mas de cinco.
		int lineas = 0;
		File archivo = new File(direccionArchivo);
		if (archivo.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(direccionArchivo))) {
				while (reader.readLine() != null)
					lineas++;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return lineas;
		} else {
			return 999;
		}

	}
}
