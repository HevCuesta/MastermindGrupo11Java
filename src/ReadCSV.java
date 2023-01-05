import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class ReadCSV {
	public static final String DELIMITADOR = ",";
	
	public static char[] leerCombinacionGuardada (String direccionArchivo) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return combinacionArray;
	}
	
	public static int intentosPartidaGuardada (String direccionArchivo) {
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
				Lectura.close();
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intentos;
	}
	
	
	//Funcion para leer un archivo, en este caso el de top5
	public static void leerArchivo(String direccionArchivo, int numLineas) {
		// Se abre el archivo en un bufer de lectura
		BufferedReader Lectura = null;
		try {
			 File csvFile = new File(direccionArchivo);
			 if (csvFile.isFile()) {
				 Lectura = new BufferedReader(new FileReader(csvFile));
				 // Lector linea
				 String linea = Lectura.readLine();
				 //numLineas es una variable que permite ver mas resultados
				 for (int o = 0; o < numLineas; o++) {
					 // Se lee linea a linea, con las comas como delimitadores de campo en cada linea
					 String[] campos = linea.split(DELIMITADOR); 
		   
					 for(int i = 0; i < campos.length; i++) {
						 	System.out.print(campos[i]+"\t");
					 }
					 System.out.print("\n");
					 
					 // Volver a leer otra línea del fichero para cerrar el while
					 linea = Lectura.readLine();
					 Lectura.close();
				 }
			 }
		 } 
		
		//En caso de que el archivo no exista
		 catch (IOException e) {
		  e.printStackTrace();
		 }
		
		 finally {
		  // Cierro el buffer de lectura
		  if (Lectura != null) {
		   try {
		    Lectura.close();
		   } 
		   catch (IOException e) {
		    e.printStackTrace();
		   	}
		  }
		 }

	}
	
	
	
	public static void leerTablero(String direccionArchivo, int numLineas) {
		// Se abre el archivo en un bufer de lectura
		BufferedReader Lectura = null;
		try {
			 File csvFile = new File(direccionArchivo);
			 if (csvFile.isFile()) {
				 Lectura = new BufferedReader(new FileReader(csvFile));
				 // Lector linea
				 String linea;
				 //numLineas es una variable que permite ver mas resultados
				 while ((linea = Lectura.readLine()) != null) {
					 // Se lee linea a linea, con las comas como delimitadores de campo en cada linea
					System.out.print(linea);
					System.out.print("\n");
					 
					 // Volver a leer otra línea del fichero para cerrar el while
					
					 
				 }
				 Lectura.close();
			 }
		 } 
		
		//En caso de que el archivo no exista
		 catch (IOException e) {
		  e.printStackTrace();
		 }
		
		 finally {
		  // Cierro el buffer de lectura
		  if (Lectura != null) {
		   try {
		    Lectura.close();
		   } 
		   catch (IOException e) {
		    e.printStackTrace();
		   	}
		  }
		 }

	}
	
	//Devuelve el numero de lineas de un archivo
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
