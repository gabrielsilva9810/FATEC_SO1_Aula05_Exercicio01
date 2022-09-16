package controller;

import java.util.concurrent.Semaphore;

public class ThreadController extends Thread {

	// DECLARANDO VARIAVEL PRIVADA
	private int IDThread;
	private Semaphore semaforo;

	// CONSTRUTOR - RECEBENDO PARAMÊTROS ACIMA
	public ThreadController(int IDThread, Semaphore semaforo) {
		this.IDThread = IDThread;
		this.semaforo = semaforo;
	}

	// METODO RUN -> RODANDO AS 3 THREADS
	@Override
	public void run() {
	
		thread1();
		thread2();
		thread3();
}

	// THREADS
	private void thread1(){

		IDThread = (int) getId();

		if (IDThread % 3 == 1) {

			for (int i = 0; i < 3; i++) {
				
				//CHAMA CALCULOS
				calculos();


				// -----------SEÇÃO CRITICA
				try {
					semaforo.acquire();
					transacao();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
				// -----------SEÇÃO CRITICA
			}
		}

	}

	private void thread2() {

		IDThread = (int) getId();
		if (IDThread % 3 == 2) {
			
			for (int i = 0; i < 3; i++) {
				
					calculos();

				// -----------SEÇÃO CRITICA
				try {
					semaforo.acquire();
					transacao();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
				// -----------SEÇÃO CRITICA
			}
		}
	}

	private void thread3() {
		IDThread = (int) getId();
		if (IDThread % 3 == 0) {
			
			for (int i = 0; i < 3; i++) {

					calculos();

				// -----------SEÇÃO CRITICA
				try {
					semaforo.acquire();
					transacao();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
				// -----------SEÇÃO CRITICA
		}

		}
	}

	// CALCULOS
	private void calculos() {
		int tempo = (int) (Math.random() * 11);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("#THREAD: " + IDThread + " está em calculo.");
	}

	// TRANSAÇÕES BD
	private void transacao() {
		long tempo = 100;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("#THREAD: " + IDThread + " está em transação.");
	}
}
