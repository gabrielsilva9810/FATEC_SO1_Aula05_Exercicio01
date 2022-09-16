package view;

import java.util.concurrent.Semaphore;

import controller.ThreadController;

public class Principal {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);

		// EXIBIR 25 THREADS -> CLASSE ThreadID
		for (int idThread = 1; idThread < 22; idThread++) {

			ThreadController threadCont = new ThreadController(idThread, semaforo);
			threadCont.start(); // STARTA A THREAD
		}

	}

}
