import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Parking {

	int plazasParking[];
	Queue<Integer> colaEntrada;

	public Parking(int nPlazas) {

		plazasParking = new int[nPlazas];
		for (int i = 0; i < nPlazas; i++) {
			plazasParking[i] = 0;
		}
		colaEntrada = new LinkedList<Integer>();

	}

	public synchronized void entrarEnCola(Vehiculo vehiculo) {
		colaEntrada.add(vehiculo.getIdVehiculo());
		System.out.println("El " + vehiculo.getTipo() + " " + vehiculo.getIdVehiculo() + " ha entrado a la cola");
		

	}

	public synchronized void aparcar(Vehiculo v) {
		int id = colaEntrada.poll();

		if (v.getTipo().equals("coche")) {

			while (comprobarPlaza() == -1) {
				try {
					wait();
				} catch (InterruptedException e) {
					
				}
			}
			int plazaLibre = comprobarPlaza();
			plazasParking[plazaLibre] = id;

		}

		if (v.getTipo().equals("camion")) {
			while (comprobarPlazaCamion() == -1) {
				try {
					wait();
				} catch (InterruptedException e) {

				}
			}
			int plazaLibre = comprobarPlazaCamion();
			plazasParking[plazaLibre] = id;
			plazasParking[plazaLibre + 1] = id;

		}

		System.out.println("El vehiculo " + id + " ha aparcado");
		System.out.println(Arrays.toString(plazasParking));
		System.out.println("El numero de plazas libre es " + comprobarNumeroPlazas());

	}

	public synchronized void salir(int id) {
		for (int i = 0; i < plazasParking.length; i++) {
			if (plazasParking[i] == id) {
				plazasParking[i] = 0;
			}
		}
		System.out.println("El vehiculo " + id + " ha salido del parking");
		notifyAll();
	}

	public int comprobarPlazaCamion() {
		for (int i = 0; i < plazasParking.length; i++) {
			if (plazasParking[i] == 0 && i < plazasParking.length - 1 && plazasParking[i + 1] == 0) {
				return i;
			}
		}
		return -1;
	}

	public int comprobarPlaza() {
		for (int i = 0; i < plazasParking.length; i++) {
			if (plazasParking[i] == 0) {
				return i;
			}
		}
		return -1;
	}

	public int comprobarNumeroPlazas() {
		int n = 0;
		for (int i = 0; i < plazasParking.length; i++) {
			if (plazasParking[i] == 0) {
				n++;
			}

		}
		return n;
	}

}
