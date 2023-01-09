
import java.lang.Math;
import java.util.Scanner;
/**
*
*@author JAVIER CHOUZA Y DANIEL CUESTA
*@version 1.0
*
*/
public class Mastermind {
	
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
    
    //Variables finales de simbolos que se usarán en la partida
    public static final String CORRECTO = "\033[0;37m✓";
    public static final String SEMICORRECTO = "\033[0;37m⍻";
    
	
	/** 
	*@return Limpiar Consola
	*/
	public static void limpiarConsola() {
		System.out.print("\033[H\033[2J"); 
		System.out.flush();
	}
	
	/**
	*@return Tutorial de como jugar
	*/
	public static void tutorial() {
		System.out.println(
				"BIENVENIDO A MASTERMIND! \r"
				+ "Mastermind es un juego de dos jugadores en el que uno de los jugadores crea una contraseña consistente de colores y el otro tiene que adivinarla \r\n"
				+ "En este caso, usted jugará contra el ordenador \r\n"
				+ "La contraseña que tendrá que adivinar consistirá de 7 colores posibles, que se pondrán en una secuencia de 5 espacios, aqui tiene un ejemplo: \r\n"
				+ CYAN + ' ' + RESET + BLANCO + ' ' + RESET + AZUL + ' ' + RESET + MORADO + ' ' + RESET + VERDE + ' ' + RESET +"\r\n"
				+ " Para intentar adivinar la contraseña, deberá escribir una secuencia de 5 letras, y cada una corresponde a un color: \r\n"
				+ " R = Rojo    C = Cian    V = Verde    A = Amarillo    Z = AZUL    M = Morado    B = BLANCO \r\n"
				+ "Por ejemplo, si escribo RVVAZ estaria intentado adivinar Rojo, Verde, Verde, Amarillo, Azul \r\n"
				+ "En caso de haber acertado tanto la posición como el color, el hueco se rellenará con el simbolo: " + NEGRO + RESET + "\r\n"
				+ "En caso de haber acertado el color pero no la posición, el hueco se rellenará con el simbolo: " + BLANCO + RESET + "\r\n"
				+ "Y en caso de no haber acertado ni el color ni la posición el hueco se rellenará con el simbolo: X\r\n"
				+ "Si quiere guardar la partida para volver a ella en cualquier momento, simplemente salga del programa presionando 0, se guardará el progreso de manera automatica \r\n"
				+ "Para volver al menu principal ponga cualquier caracter y presione enter.");
		
				
	}
	
	/**
	*@return Combinacion Secreta a adivinar
	*@param i Longitud de la combinacion
	*/
	public static char generarCombinacion(int i) {	
		char combinacionSecreta[] = new char[5];
		
		switch( (int) (Math.random()*7)) {
			case 0:	combinacionSecreta[i] = 'C'; break; //cyan
			case 1:	combinacionSecreta[i] = 'R'; break; //rojo
			case 2:	combinacionSecreta[i] = 'V'; break; //verde
			case 3:	combinacionSecreta[i] = 'A'; break; //amarillo
			case 4:	combinacionSecreta[i] = 'Z'; break; //azul
			case 5:	combinacionSecreta[i] = 'M'; break; //morado
			case 6:	combinacionSecreta[i] = 'B'; break; //blanco
		}
		return combinacionSecreta[i];
	}
	
	/**
	*@return boolean que determina si se acierta o no la combinacion
	*@param resultado Combinacion a adivinar
	*/
	public static boolean posibleAcierto (char resultado[]) {
		boolean acertado = true;
		
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] != 'N') {
				acertado = false; //anula la veracidad de haber acertado si hay almenos un color incorrecto.
			}
		}
		return acertado;
		
	}
	
	
	/**
	*@return boolean para validar si la respuesta tiene los colores disponibles y es de la longitud necesaria
	*@param combinacion Intento de adivinar
	*/
	public static boolean validarRespuesta (char combinacion[]) {
		
	boolean combinacionValida = false; //bool para asegurarse de que las combinaciones introducidas sean válidas.
		try {
				
			for(int i = 0; i < 5; i++) {
				combinacionValida = false;
				if (combinacion[i] != 'R' && combinacion[i] != 'C' && combinacion[i] != 'V' && combinacion[i] != 'A' &&
						combinacion[i] != 'Z' && combinacion[i] != 'M' && combinacion[i] != 'B') { System.out.println("Combinación no válida, inténtelo de nuevo. \r"
																													+ " Pst!: R = Rojo    C = Cian    V = Verde    A = Amarillo    Z = AZUL    M = Morado    B = BLANCO ");
						i = 5;
				}else combinacionValida = true;
			}
		}
		
		catch (Exception ArrayIndexOutOfBoundsException) {
			System.out.println("Combinación no válida, inténtelo de nuevo.");
			combinacionValida = false;
		}	
		return combinacionValida;
	}
	
	/**
	*@return Analiza la respuesta
	*@param combinacion Combinacion introducida por el usuario
	*@param combinacionSecreta Combinacion que se tiene que adivinar
	*/
	public static char[] analizarRespuesta (char combinacion[], char combinacionSecreta[]){
		char resultado[] = new char [5];
		
			for (int i = 0; i < combinacionSecreta.length; i++) {
				 for (int o = 0; o < combinacionSecreta.length; o++) {
					 if (combinacion[i] == combinacionSecreta[o]) {
						 resultado[i] = 'B';
						 o = 5;
					 } else resultado [i] = '_';	
				  }
				 
				 	if (combinacion[i] == combinacionSecreta[i]) resultado[i] = 'N';	
			}
		
			
		
		return resultado;
	}
	
	/**
	*@return Printea tablero de juego cada intento 
	*@param tablero Parte del tablero que contiene los intentos
	*@param intentos Numero de intentos, para añadir mas filas
	*@param resultadoTablero Parte del tablero dedicada a la validacion de la combinacion
	*/
	public static void pintarTablero(char tablero[][], int intentos, char resultadoTablero[][]) {
		
		for (int q = 0; q < intentos+1; q++) {
		for (int i = 0; i < 5; i++) {
			
			 switch(tablero[q][i]) {
			 
			 	case 'C': System.out.print(CYAN + RESET); break; //cyan
				case 'R': System.out.print(ROJO + RESET); break; //rojo
				case 'V': System.out.print(VERDE + RESET); break;//verde
				case 'A': System.out.print(AMARILLO + RESET); break; //amarillo
				case 'Z': System.out.print(AZUL + RESET); break; //azul
				case 'M': System.out.print(MORADO + RESET); break; //morado
				case 'B': System.out.print(BLANCO + RESET); break; //blanco 
			 }
	     	
		}
		
		System.out.print(' ');
			for (int i = 0; i < 5; i++) switch(resultadoTablero[q][i]) {
				case 'B': System.out.print(BLANCO + RESET); break;
				case 'N': System.out.print(NEGRO + RESET); break; 
				case '_': System.out.print("X"); break;
				
			}
		
		System.out.println();
		}	

	}
	
	/**
	*@return Array de chars para almacenar en el archivo de guardado
	*@param combinacion Intento de combinaciona a adivinar
	*@param combinacionSecreta Combinacion generada por el programa
	*/
	public static char[] letrasAdivinadas (char combinacion[], char combinacionSecreta[]) {
		char adivinadas[] = new char [combinacionSecreta.length];
		try {
		for (int i = 0; i < combinacionSecreta.length; i++) {
				 if (combinacionSecreta[i] == combinacion[i]) {
					 adivinadas[i] = combinacionSecreta[i];
				 } else adivinadas [i] = '_';	
			  
			 
		}
		} catch (Exception e) {
			System.out.print("");
			
		}
		return adivinadas;
		
		}
	
	/**
	*@return Partida nueva
	*/
	public static void nuevoJuego() {
		//ESTE SCANNER, NO SE PUEDE CERRAR DE NINGUNA MANERA, DA IGUAL COMO LO INTENTEMOS, NO PODEMOS
		Scanner in = new Scanner (System.in);
		
		String nombreUsuario;
		
		System.out.println("Introduzca su nombre de usuario");
	
		 nombreUsuario = in.next().toLowerCase();
		
		while(WriteCSV.nuevaPartidaUsuario(nombreUsuario)) nombreUsuario = in.next();
		

		char tablero[][] = new char [50][5];
		char combinacionSecreta[] = new char[5];	
		char combinacion[] = new char[5];
		char resultado[] = new char [5];
		int intentos = 0;
		char guardarPartida = '-';
		boolean aux = true; //para posibles errores de caracteres no correspondientes.
		
		char resultadoTablero[][] = new char[50][5];
		
		for(int i = 0; i < combinacionSecreta.length; i++) combinacionSecreta[i] = generarCombinacion(i);

		
	    do {	
	    	limpiarConsola();
			System.out.println("Introduce la combinación de colores a adivinar:");
			combinacion = in.next().toUpperCase().toCharArray();
			
			
			
			if(validarRespuesta(combinacion) == true) {
				for(int i = 0; i < 5; i++)	tablero[intentos][i] = combinacion[i];
						
				resultado = analizarRespuesta(combinacion, combinacionSecreta);	
				
			
				for(int i = 0; i < 5; i++)	resultadoTablero[intentos][i] = resultado[i]; //conversión de las fichas blancas y negras a una matriz para mayor facilidad.
					
				pintarTablero(tablero, intentos, resultadoTablero);	
				
			}



			intentos++;
			WriteCSV.guardarTablero(tablero, intentos, resultadoTablero, "partidas/Usuarios/" + nombreUsuario + "/TableroGuardado.csv", false);
			WriteCSV.escribirArchivoGuardar(combinacionSecreta, intentos, "partidas/Usuarios/" + nombreUsuario + "/PartidaGuardada.csv"); 

	   } while(posibleAcierto(resultado) == false);
	
	    	
	    	System.out.println("\r\n¡¡Has acertado!! ¿Quieres guardar la partida? (s/n) ");
	    	
			do {

				aux = false;
				try {

					guardarPartida = in.next().charAt(0);

				} catch (Exception InputMismatchException) {

					aux = true;
					System.out.println("Por favor introduzca2" + "'s'" + "o" + "'n'");
				}

				switch (guardarPartida) {
				case 's':
					WriteCSV.escribirArchivo(nombreUsuario, intentos, "partidas/Top5.csv");
					System.out.println("Partida guardada correctamente.");
					break;
				case 'n':
					WriteCSV.borrarPartidaUsuario(nombreUsuario);
					break;
				default:
					aux = true;
					System.out.println("Por favor introduzca2" + "'s'" + "o" + "'n'");
				}
			} while (aux);
	}

	/**
	*@return Jugar partida guardada
	*@param combinacionSecretaGuardada Combinacion secreta guardada de la anterior partida
	*@param intentosGuardados Numero de intentos guardados de la anterior partida
	*/
	public static void partidaGuardada(String usuario) {
		//ESTE SCANNER, NO SE PUEDE CERRAR DE NINGUNA MANERA, DA IGUAL COMO LO INTENTEMOS, NO PODEMOS
		Scanner in = new Scanner(System.in);
		String nombreUsuario = usuario.toLowerCase();
		int intentosGuardados = ReadCSV.intentosPartidaGuardada("partidas/Usuarios/"+ nombreUsuario + "/PartidaGuardada.csv" );
		// En caso de que el archivo no exista, la funcion nos devuelve 9999 y eso nos lleva de vuelta al menu
		if (intentosGuardados==9999) return;
		
		
		
		char[] combinacionSecretaGuarda = ReadCSV.leerCombinacionGuardada("partidas/Usuarios/"+ nombreUsuario + "/PartidaGuardada.csv");
		
		
		
		System.out.println("¡Bienvenido de nuevo! Tu última partida guardada tiene las siguientes estadisticas: ");
		System.out.println("Intentos: " + intentosGuardados);
		System.out.println("El tablero de tu partida pasada es el siguiente: ");
		
		ReadCSV.leerTablero("partidas/Usuarios/"+ nombreUsuario + "/TableroGuardado.csv", ReadCSV.numLineasArchivo("partidas/Usuarios/" + nombreUsuario + "/TableroGuardado.csv"));
		
		
		
		char tablero[][] = new char [50][5];
		char combinacionSecreta[] = new char [5];
		for(int i =0; i < 5; i++) {	combinacionSecreta[i] = combinacionSecretaGuarda[i];
		
		}
		
		char combinacion[] = new char[5];
		
		
		char resultado[] = new char [5];
		int intentos = 0;
		char guardarPartida = '-';
		boolean aux = true; //para posibles errores de caracteres no correspondientes.
		boolean salir = false;
		
		
		char resultadoTablero[][] = new char[50][5];
		
	    do {	
			System.out.println("Introduce la combinación de colores a adivinar (Para salir en cualquier momento, presione 0):");
			combinacion = in.next().toUpperCase().toCharArray();
			
			if (combinacion[0] != '0') {
			
				salir = false;
			if(validarRespuesta(combinacion) == true) {
				for(int i = 0; i < 5; i++)	tablero[intentos][i] = combinacion[i];
						
				resultado = analizarRespuesta(combinacion, combinacionSecreta);	
				
			
				for(int i = 0; i < 5; i++)	resultadoTablero[intentos][i] = resultado[i]; //conversión de las fichas blancas y negras a una matriz para mayor facilidad.
					
				pintarTablero(tablero, intentos, resultadoTablero);	
			

			}

			intentos++;

			
			WriteCSV.escribirArchivoGuardar(combinacionSecreta, intentos+intentosGuardados, "partidas/Usuarios/"+ nombreUsuario +"/PartidaGuardada.csv"); 

			}else salir = true; 
			 
	   } while(posibleAcierto(resultado) == false && salir == false);
	
	    WriteCSV.guardarTablero(tablero, intentos, resultadoTablero, "partidas/Usuarios/" + nombreUsuario + "/TableroGuardado.csv", true);	
	    
	    	if (salir == false) { System.out.println("\r\n¡¡Has acertado!! ¿Quieres guardar la partida? (s/n) ");
	    	
			do {

				aux = false;
				try {

					guardarPartida = in.next().toLowerCase().charAt(0);

				} catch (Exception InputMismatchException) {

					aux = true;
					System.out.println("Por favor introduzca2" + "'s'" + "o" + "'n'");
				}

				switch (guardarPartida) {
				case 's':
					
					WriteCSV.escribirArchivo(nombreUsuario, intentos+intentosGuardados, "partidas/Top5.csv");
					break;
				case 'n':
					break;
				default:
					aux = true;
					System.out.println("Por favor introduzca2" + "'s'" + "o" + "'n'");
				}
			} while (aux);
	
	    	}
	}
	
	/**
	*@return Metodo main
	*/
	public static void main(String[] args) {
		// Para el input del usuario
		Scanner in = new Scanner(System.in);
		
		//Bucle while para volver a comenzar cuando se termine una partida
		boolean restart = true;
		while (restart) {
			restart = false;
			System.out.println("¡Bienvenido a Mastermind! Elija una de las siguientes opciones: \r\n "
					+ "1. Jugar una nueva partida \r\n "
					+ "2. Seguir con una partida anterior \r\n "
					+ "3. Clasificación de los mejores jugadores \r\n "
					+ "4. Como Jugar \r\n "
					+ "5. Salir");

			int eleccion1;

			try {

				eleccion1 = in.nextInt();

			} catch (Exception InputMismatchException) {

				System.err.println("Eleccion no valida, pruebe otra vez.");
				restart=true;
				in.next();
				continue;

			}

			// MENU PRINCIPAL
			String eleccion2;
			switch (eleccion1) {
			case 1:
				limpiarConsola();
				nuevoJuego();
				restart=true;
				break;
			case 2:
				limpiarConsola();
				System.out.println("Introduce el nombre del usuario:");
				
				String nombreUsuario = in.next().toLowerCase();
				partidaGuardada(nombreUsuario);
				
				restart=true;
				break;
			case 3:
				limpiarConsola();
				int numLineas = 5;
				ReadCSV.leerArchivo("partidas/Top5.csv", numLineas);
				System.out.println("Presione 3 y enter otra vez si quiere ver todos los resultados, presione cualquier otra tecla para volver al menu.");
				if (in.next().equals("3")) {
					numLineas = ReadCSV.numLineasArchivo("partidas/Top5.csv");
					ReadCSV.leerArchivo("partidas/Top5.csv", numLineas);
					System.out.println("Recuerda que el numero de la derecha son los intentos. Menos intentos = Mejor resultado");
					break;
					
				} else
					in.close();
				restart=true;
				break;
			case 4:
				limpiarConsola();
				tutorial();
				eleccion2 = in.next();
				limpiarConsola();
				if (eleccion2!=null) {
					restart=true;
					break;
				} else 
				break;
			case 5:
				
				break;
			case 6:
				//WriteCSV.nuevaPartidaUsuario("Paco");
				WriteCSV.borrarPartidaUsuario("p4");
				break;
			default:
				System.err.println("Eleccion no valida, pruebe otra vez.");
				restart = true;
				break;
			}
			
		}

	}

}
