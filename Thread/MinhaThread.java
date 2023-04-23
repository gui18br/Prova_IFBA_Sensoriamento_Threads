package Thread;

import java.util.ArrayList;

import Operacoes.OperacoesMatematicas;
import carro.Motor;
import carro.MotorStatus;
import carro.Painel;
import carro.Sensor;

public class MinhaThread implements Runnable{
	private int id;
	private ArrayList<MotorStatus> statusMotores;
	
	 public MinhaThread(int id, ArrayList<MotorStatus> statusMotores) {
	        this.id = id;
	        this.statusMotores = statusMotores;
	 }

	public void run() {
		Sensor sensor = new Sensor();
		Painel painel = new Painel(false);
		
		int temperatura = OperacoesMatematicas.gerarTemperatura();
		Motor motor = new Motor(temperatura, 2000);
		OperacoesMatematicas.calculoDesgasteMotor(motor);
		String status = (String) sensor.tratarTemperatura(motor, painel);
		
		synchronized(statusMotores) {
			statusMotores.add(new MotorStatus(temperatura, status));
		}
		
	}
}
