//AUTORES: JAVIER CHOUZA Y DANIEL CUESTA
import java.lang.Math;
import java.util.Scanner;

public class Mastermind {
	
	// Se declaran las variables de todos los colores que se usarán para el juego
    public static final String CYAN = "\033[0;36m ○";
    public static final String ROJO = "\033[0;31m ○";
    public static final String VERDE = "\033[0;32m ○";
    public static final String AMARILLO = "\033[0;33m ○";
    public static final String AZUL = "\033[0;34m ○";  
    public static final String MORADO = "\033[0;35m ○";
    public static final String BLANCO = "\033[0;37m ○";
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
				+ CYAN + RESET + BLANCO + RESET + AZUL + RESET + MORADO + RESET + VERDE + RESET
				+ "Para intentar adivinar la contraseña, deberá escribir una secuencia de 5 letras, y cada una corresponde a un color: \r"
				+ " R = Rojo    C = Cian    V = Verde    A = Amarillo    Z = AZUL    M = Morado    B = BLANCO \r"
				+ "Por ejemplo, si escribo RVVAZ estaria intentado adivinar Rojo, Verde, Verde, Amarillo, Azul \r"
				+ "En caso de haber acertado tanto la posición como el color, el hueco se rellenará con el simbolo: ◉ \r"
				+ "En caso de haber acertado el color pero no la posición, el hueco se rellenará con el simbolo: • \r"
				+ "Y en caso de no haber acertado ni el color ni la posición el hueco se rellenará con el simbolo: X\r"
				+ "Para volver al menu principal ponga cualquier caracter y presione enter.");
		
				
	}
	
	
	public static void nuevoJuego() {
		limpiarConsola();
		
		String combinacionSecreta[] = new String[5];	
		
		for (int i = 0; i < 5; i++) {	
		
			switch( (int) (Math.random()*7)) {
		
			case 0:	combinacionSecreta[i] = CYAN + RESET; break;
			case 1:	combinacionSecreta[i] = ROJO +  RESET; break;
			case 2:	combinacionSecreta[i] = VERDE +  RESET; break;
			case 3:	combinacionSecreta[i] = AMARILLO + RESET; break;
			case 4:	combinacionSecreta[i] = AZUL + RESET; break;
			case 5:	combinacionSecreta[i] = MORADO + RESET; break;
			case 6:	combinacionSecreta[i] = BLANCO + RESET; break;
			}
		}
	
		
		Scanner in = new Scanner (System.in);
		
		
		char combinacion[] = new char[5];
		
		System.out.println("Introduce combinación de colores:");
		
	
		combinacion = in.next().toCharArray();
		
		for(int i = 0; i < 5; i++) {
			
			System.out.println(combinacion[i]);
			
		}

	}
	
	

	
	
	
	
	
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

		
	}

}
