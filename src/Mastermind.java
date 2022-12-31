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
	
	// Se declaran las variables de 
	//A
	
	//Funcion para limpiar consola, solo funciona cuando se ejecuta desde cmd o bash, ningun tipo de clear funciona con la consola de eclipse
	public static void limpiarConsola() {
		System.out.print("\033[H\033[2J"); 
		System.out.flush();
	}
	
	public static void tutorial() {
		System.out.println(
				"BIENVENIDO A MASTERMIND! \r"
				+ "Mastermind es un juego de dos jugadores en el que uno de los jugadores creará una contraseña consistente de colores y el otro tiene que adivinarla \r"
				+ "En este caso, usted jugará contra el ordenador \r"
				+ "La contraseña que tendrá que adivinar consistirá de 7 colores posibles, que se pondrán en una secuencia de 5 espacios, aqui tiene un ejemplo: \r"
				+ CYAN + ' ' + RESET + BLANCO + ' ' + RESET + AZUL + ' ' + RESET + MORADO + ' ' + RESET + VERDE + ' ' + RESET +'\r'
				+ " Para intentar adivinar la contraseña, deberá escribir una secuencia de 5 letras, y cada una corresponde a un color: \r"
				+ " R = Rojo    C = Cian    V = Verde    A = Amarillo    Z = AZUL    M = Morado    B = BLANCO \r"
				+ "Por ejemplo, si escribo RVVAZ estaria intentado adivinar Rojo, Verde, Verde, Amarillo, Azul \r"
				+ "En caso de haber acertado tanto la posición como el color, el hueco se rellenará con el simbolo: ◉ \r"
				+ "En caso de haber acertado el color pero no la posición, el hueco se rellenará con el simbolo: • \r"
				+ "Y en caso de no haber acertado ni el color ni la posición el hueco se rellenará con el simbolo: X\r"
				+ "Para volver al menu principal ponga cualquier caracter y presione enter.");
		
				
	}
	
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
	
	
	public static boolean posibleAcierto (char resultado[]) {
		boolean acertado = true;
		
		for (int i = 0; i < resultado.length; i++) if (resultado[i] != 'N') acertado = false; //anula la veracidad de haber acertado si hay almenos un color incorrecto.
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
		
			for (int i = 0; i < 5; i++) {
				switch(resultado[i]) {
					case 'B': System.out.print(BLANCO + RESET); break;
					case 'N': System.out.print(NEGRO + RESET); break; 
					default: System.out.print('_');
				}
			}
		
		return resultado;
	}//fin función analizarRespuesta
	
	
	public static void nuevoJuego() {
		
		Scanner in = new Scanner (System.in);
		
		char combinacionSecreta[] = new char[5];	
		char combinacion[] = new char[5];
		char resultado[] = new char [5];
		

	
		
		
		for(int i = 0; i < combinacionSecreta.length; i++) combinacionSecreta[i] = generarCombinacion(i);
		
		
		
	
	
	do {	
		
		System.out.println(" Introduce combinación de colores:");
		
		combinacion = in.next().toUpperCase().toCharArray();
		
		if(validarRespuesta(combinacion) == true) resultado = analizarRespuesta(combinacion, combinacionSecreta);
		
	
		
	
	} while(posibleAcierto(resultado) == false);
	
	System.out.println("¡¡Has acertado!! ¿Quieres guardar la partida?");
	
	in.close();
		
	}//fin void nuevoJuego();
	
	
	
	public static void main(String[] args) {
		// Para el input del usuario
		Scanner in = new Scanner(System.in);
		
		System.out.println("¡Bienvenido a Mastermind! Elija una de las siguientes opciones: \r\n 1. Jugar una nueva partida \r\n 2. Seguir con una partida anterior \r\n 3. Salir \r\n 4. Como Jugar");
		
		int eleccion1;
		
		try {
		
		 eleccion1 = in.nextInt();
		
		} catch (Exception InputMismatchException) {
		
			eleccion1 = 99;
			
		}
		//Switch para las opciones

		switch (eleccion1) {
		case 1:
			limpiarConsola();
			nuevoJuego();
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			limpiarConsola();
			tutorial();
			break;
		default:
			System.out.println("Eleccion no valida, pruebe otra vez.");
		}

		in.close();
	}

}
