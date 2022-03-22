package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPrato;

public class OverCooked {

	public static void main(String[] args) {
		
		int permissoes = 3;
		Semaphore semaforo = new Semaphore(permissoes);
		Semaphore semaforoEntrega = new Semaphore(1);
		
		for (int idPrato = 0 ; idPrato < 5 ; idPrato++) {
			Thread tPrato = new ThreadPrato(idPrato, semaforo, semaforoEntrega);
			tPrato.start();
		}
	}
}
