//AUTORES: JAVIER CHOUZA Y DANIEL CUESTA

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
				+ CYAN + RESET + BLANCO + RESET + AZUL + RESET + MORADO + RESET + VERDE + RESET);
	}
	
	public static void main(String[] args) {
		// Para el input del usuario
		Scanner in = new Scanner(System.in);
		
		System.out.println("¡Bienvenido a Mastermind! Elija una de las siguientes opciones: \r\n 1. Jugar una nueva partida \r\n 2. Seguir con una partida anterior \r\n 3. Salir \r\n 4. Como Jugar");
		int eleccion1 = in.nextInt();
		
		
		//Switch para las opciones
		switch (eleccion1) {
		case 1:
			limpiarConsola();
			
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
