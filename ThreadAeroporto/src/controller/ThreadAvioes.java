package controller;

import java.util.concurrent.Semaphore;

public class ThreadAvioes extends Thread {
	private int idAviao;
	private static Semaphore semaforoNorte = new Semaphore(1);
	private static Semaphore semaforoSul = new Semaphore(1); //um avião por cada pista
	private String pista;
	
	public ThreadAvioes(int idAviao) {
		this.idAviao = idAviao;
	}

	@Override
	public void run() {
		try {
			
			if (Math.random() < 0.5) {
				pista = "Norte";
				System.out.println("O avião #" + idAviao + " está na pista " + pista);
			} else {
				pista = "Sul";
				System.out.println("O avião #" + idAviao + " está na pista " + pista);
			}

			manobragem();
			taxiamento();
			decolagem();
			afastamento();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void manobragem() {
		try {
			int timeManobra = (int) (Math.random() * 401) + 300;
			System.out.println("o avião #" + idAviao + " levou " + timeManobra + " milisegundos para manobrar.");
			sleep(timeManobra);
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}

	private void taxiamento() {
		try {
			int timeTaxi = (int) (Math.random() * 501) + 500;
			System.out.println("o avião #" + idAviao + " levou " + timeTaxi + " milisegundos para taxiar.");
			sleep(timeTaxi);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void decolagem() {
		try {
			if (pista.equals("Norte")) {
				semaforoNorte.acquire(); //avião na pista norte entra no seu semáforo
			} else {
				semaforoSul.acquire(); //avião na pista sul entra no seu semáforo
			}
			int timeDecolar = (int) (Math.random() * 201) + 600;
			System.out.println("o avião #" + idAviao + " levou " + timeDecolar + " milisegundos para decolar da pista " + pista);
			sleep(timeDecolar);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pista.equals("Norte")) {
				semaforoNorte.release(); //avião na pista norte entra no seu semáforo
			} else {
				semaforoSul.release(); //avião na pista sul entra no seu semáforo
			}
		}

	}

	private void afastamento() {
		try {
			int timeAfastar = (int) (Math.random() * 501) + 300;
			System.out.println("o avião #" + idAviao + " levou " + timeAfastar + " milisegundos para se afastar.");
			sleep(timeAfastar);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
