import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class ReadCSV {
	public static final String DELIMITADOR = ",";
			
	
	public static void ordenarArchivo(String direccionArchivo, int numLineas) {

	}
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
	
	
	
}
