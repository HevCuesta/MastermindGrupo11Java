//AUTORES: JAVIER CHOUZA Y DANIEL CUESTA
import java.lang.Math;
import java.util.Scanner;

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
    
	
	//Funcion para limpiar consola, solo funciona cuando se ejecuta desde cmd o bash, ningun tipo de clear funciona con la consola de eclipse
	public static void limpiarConsola() {
		System.out.print("\033[H\033[2J"); 
		System.out.flush();
	}
	
	//Funcion Tutorial como jugar
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
				+ "Y en caso de no haber acertado ni el color ni la posición el hueco se rellenará con el simbolo: _\r\n"
				+ "Para volver al menu principal ponga cualquier caracter y presione enter.");
		
				
	}
	
	//Funcion para generar combinacion aleatoria a adivinar
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
	
	//Funcion para ver si hay un posible acierto
	public static boolean posibleAcierto (char resultado[]) {
		boolean acertado = true;
		
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] != 'N') {
				acertado = false; //anula la veracidad de haber acertado si hay almenos un color incorrecto.
			}
		}
		return acertado;
		
	}//fin función posibleAcierto.
	
	
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
	}//fin función validarResuesta.
	
	
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
	}//fin función analizarRespuesta
	
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
				default: System.out.print('_');
			}
		
		
		System.out.print('\n');
		}	

	}
	
	
	public static void nuevoJuego() {
		Scanner in = new Scanner (System.in);
		
		String nombreUsuario;
		
		
		char tablero[][] = new char [10][5];
		char combinacionSecreta[] = new char[5];	
		char combinacion[] = new char[5];
		char resultado[] = new char [5];
		int intentos = 0;
		char guardarPartida = 'ඞ';
		boolean aux = true; //para posibles errores de caracteres no correspondientes.
		
		char resultadoTablero[][] = new char[10][5];
		
		for(int i = 0; i < combinacionSecreta.length; i++) combinacionSecreta[i] = generarCombinacion(i);
		
	    do {	
			System.out.println("Introduce la combinación de colores a adivinar:");
			combinacion = in.next().toUpperCase().toCharArray();
			
			
		
		
			if(validarRespuesta(combinacion) == true) {
				for(int i = 0; i < 5; i++)	tablero[intentos][i] = combinacion[i];
						
				resultado = analizarRespuesta(combinacion, combinacionSecreta);	
				
			
				for(int i = 0; i < 5; i++)	resultadoTablero[intentos][i] = resultado[i]; //conversión de las fichas blancas y negras a una matriz para mayor facilidad.
					
				intentos++;
				pintarTablero(tablero, intentos, resultadoTablero);
				
				
			}
			
			WriteCSV.escribirArchivoGuardar(resultado ,intentos, "partidas/PartidaGuardada.csv"); 
	   } while(posibleAcierto(resultado) == false);
	
	    	
	    	System.out.println("\r\n¡¡Has acertado!! ¿Quieres guardar la partida? (s/n) ");
	    	
	    	
	    do {	
	    	
	    	aux = false;
	    	try {
	    		
	    		guardarPartida = in.next().charAt(0);
	    		
	    		
	    	} catch(Exception InputMismatchException) {
	    		
	    		aux = true;
	    		System.out.println("Por favor introduzca2" + "'s'" + "o" + "'n'" );
	    	}
	    	
	    	
	    	switch (guardarPartida) {
	    	
	    	case 's': 
	    	System.out.println("Introduzca su nombre:");
	    	nombreUsuario = in.next().toLowerCase();
	    	WriteCSV.escribirArchivo(nombreUsuario, intentos, "partidas/top5.csv");
	    	break;
	    	
	    	case 'n':
	    	break;
	    	
	    	default:
	    	aux = true;	
	    	System.out.println("Por favor introduzca2" + "'s'" + "o" + "'n'");
	    	}
	    	
	    	
	    }while (aux);
	    
	    

		
	}//fin void nuevoJuego();
	
	
	
	public static void main(String[] args) {
		// Para el input del usuario
		Scanner in = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		
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
				
				break;
			case 3:
				limpiarConsola();
				int numLineas = 5;
				ReadCSV.leerArchivo("partidas/top5.csv", numLineas);
				System.out.println("Presione 3 y enter otra vez si quiere ver todos los resultados, presione cualquier otra tecla para volver al menu.");
				if (in.next().equals("3")) {
					numLineas = WriteCSV.numLineasArchivo("partidas/top5.csv");
					ReadCSV.leerArchivo("partidas/top5.csv", numLineas);
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
			//Case para hacer pruebas
			case 6:
				WriteCSV.ordenarArchivo();
				break;
			default:
				System.err.println("Eleccion no valida, pruebe otra vez.");
				restart = true;
				break;
			}
			
		}

	}

}
