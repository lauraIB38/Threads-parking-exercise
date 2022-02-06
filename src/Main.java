import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el numero de plazas");
		int plazas = sc.nextInt();
		System.out.println("Introduzca el numero de coches: ");
		int coche = sc.nextInt();
		System.out.println("Introduzca el numero de camiones: ");
		int camion = sc.nextInt();
		sc.close();
		Parking p = new Parking(plazas);
		Semaphore s = new Semaphore(1);
		Vehiculo[] vehiculos = new Vehiculo[coche + camion];

		for (int i = 0; i < coche; i++) {
			vehiculos[i] = new Vehiculo(i + 1, "coche", p, s);
		}

		for (int i = 0; i < camion; i++) {
			vehiculos[i + coche] = new Vehiculo(i + 100, "camion", p, s);
		}

		for (int i = 0; i < vehiculos.length; i++) {
			vehiculos[i].start();
		}

	}

}
