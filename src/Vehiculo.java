import java.util.Random;
import java.util.concurrent.Semaphore;

public class Vehiculo extends Thread {

	private int idVehiculo;
	private int tiempoAle;
	private String tipo;
	Semaphore semaforo;
	Parking parking;
	

	public Vehiculo(int idVehiculo, String tipo, Parking parking, Semaphore semaforo) {
		this.idVehiculo = idVehiculo;
		Random r = new Random();
		this.tiempoAle = r.nextInt(1000) + 500;
		this.tipo = tipo;
		this.semaforo= semaforo;
		this.parking = parking;
	}

	@Override
	public void run() {
		while (true) {

			try {
				sleep(tiempoAle);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			parking.entrarEnCola(this);
			try {
				semaforo.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			parking.aparcar(this);
			semaforo.release();
			try {
				sleep(tiempoAle);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parking.salir(idVehiculo);
		}

	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
