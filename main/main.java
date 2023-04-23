package main;

import java.util.ArrayList;

import Operacoes.OperacoesMatematicas;
import Thread.MinhaThread;
import carro.MotorStatus;
import ordenation.Ordenador;

public class main {
	public static void main(String[] args) {
		 ArrayList<MotorStatus> statusMotores = new ArrayList<MotorStatus>();
		 ArrayList<Thread> threads = new ArrayList<Thread>();
		 
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new MinhaThread(i, statusMotores));
			threads.add(t);
			t.start();
		}
		
		for(Thread t : threads) {
			try {
				t.join();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		Ordenador.ordenarMotoresPorTemperatura(statusMotores);
		System.out.println("Lista de Motores organizada confome a temperatura dos mesmos: \n");
		for (int i = 0; i < statusMotores.size(); i++) {
			System.out.println((1 + i) + ": " + statusMotores.get(i).getStatus());
			
		}
		
		/*
		 * Chamada do método compararTemperaturaMotores() para realizar a comparação entre 3 mo-
		 * tores aleatórios.
		 */
		System.out.println("Comparação dos motores: \n");
		System.out.println(OperacoesMatematicas.compararTemperaturaMotores(statusMotores));
	}
}
