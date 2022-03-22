package controller;

import java.util.concurrent.Semaphore;

public class ThreadPrato extends Thread {

	private int idPrato;
	private Semaphore semaforo;
	private Semaphore semaforoEntrega;

	public ThreadPrato(int idPrato, Semaphore semaforo, Semaphore semaforoEntrega) {
		this.semaforoEntrega = semaforoEntrega;
		this.idPrato = idPrato;
		this.semaforo = semaforo;
	}

	public void run(int idPrato) {

		inicioPrato(idPrato);
	}

	private void inicioPrato(int idPrato) {

		double percent;
		int tempo = 0;
		if (idPrato % 2 == 0) {

			System.out.println("O prato#" + idPrato + " Lasanha a Bolonhesa se iniciou.");
			int tempoTotal = (int) ((Math.random() * 301) + 500);
			while (tempo < tempoTotal) {
				tempo += 100;
				try {
					sleep(tempoTotal);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				percent = tempo / tempoTotal;
				System.out.println("Percentual de cozimento: " + percent + "%.");
			}
			System.out.println("O prato#" + idPrato + " Lasanha a Bolonhesa finalizado.");
			try {
				semaforoEntrega.acquire();
				entregar(idPrato);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoEntrega.release();
			}		
		} else {
			System.out.println("O prato#" + idPrato + " Sopa de Cebola se iniciou.");
			// int tempo = (int) ((Math.random() * 301) + 500);
			int tempoTotal = (int) ((Math.random() * 601) + 600);
			while (tempo < tempoTotal) {
				tempo += 100;
				try {
					sleep(tempoTotal);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				percent = tempo / tempoTotal;
				System.out.println("Percentual de cozimento: " + percent + "%.");
			}
			System.out.println("O prato#" + idPrato + " Sopa de Cebola finalizado.");
			try {
				semaforoEntrega.acquire();
				entregar(idPrato);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoEntrega.release();
			}
		}
	}

	private void entregar(int idPrato) {
		try {
			Thread.sleep(500);
			System.out.println("Prato#" + idPrato + " entregue.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}
