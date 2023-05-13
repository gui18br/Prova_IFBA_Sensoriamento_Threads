package main;

import java.util.ArrayList;

import Operacoes.OperacoesMatematicas;
import Thread.MinhaThread;
import carro.MotorStatus;
import carro.Painel;
import carro.Sensor;
import carro.Status;
import ordenation.Ordenador;

public class main {
	public static void main(String[] args) {
		 ArrayList<Status> status = new ArrayList<Status>();
		 ArrayList<MotorStatus> statusMotores = new ArrayList<MotorStatus>();
		 
		 Sensor sensor = new Sensor();
		 Painel painel = new Painel(false);
		 
		MinhaThread minhaThread = new MinhaThread(status);
		
		Thread thread = new Thread(minhaThread);
		
		thread.start();
		int contador = 0;
		int id = 0;
		
		/*
		 * Após a thread ser inicializada o loop 'do while' é utilizado 10 vezes. Dentro do 'do while' é
		 * chamado o sensor que recebe os dados passados pela thread atual e convertida as suas 
		 * informações para string e as mesmas são exibidas no console. Após isso todas essas 
		 * informações são adicionadas na implementação de uma nova classe MotorStatus para 
		 * dentro da lista statusMotores. Tudo isso é realizado enquanto o contador não chega a 10 
		 * e leva uma pausa de 2 segundos.
		 */
		do {
			contador++;
			id++;
		    synchronized(status) {
		        if (!status.isEmpty()) {
		            Status newStatus = status.remove(0);
		            String tratamentoTemp = (String) sensor.tratarTemperatura(newStatus.getMotor(), painel);
		            System.out.println(id-1 +": " +tratamentoTemp);
		            synchronized (statusMotores) {
		                statusMotores.add(new MotorStatus(tratamentoTemp, newStatus.getTemperatura()));              
		            }       
		        }
		    }
		    
		    try {
		        Thread.sleep(2000);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}while(contador <= 10);
		minhaThread.parar();

		/*
		 * Chamada do método compararTemperaturaMotores() para realizar a comparação entre 3 mo-
		 * tores aleatórios.
		 */
		System.out.println("Comparação dos motores: \n");
		System.out.println(OperacoesMatematicas.compararTemperaturaMotores(statusMotores));

		/*
		 * Chamada do método ordenarMotoresPorTemperatura() para ordenar os motores em ordem
		 *crescente.
		 */
		Ordenador.ordenarMotoresPorTemperatura(statusMotores);
		System.out.println("Lista de Motores organizada confome a temperatura dos mesmos: \n");
		for (int i = 0; i < statusMotores.size(); i++) {
			System.out.println((1 + i) + ": " + statusMotores.get(i).getStatus());
			
		}	
	}
}