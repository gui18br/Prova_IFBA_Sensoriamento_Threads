package Thread;

import java.util.ArrayList;

import Operacoes.OperacoesMatematicas;
import carro.Motor;
import carro.MotorStatus;
import carro.Painel;
import carro.Sensor;
import ordenation.Ordenador;

public class MinhaThread implements Runnable {

    private ArrayList<MotorStatus> statusMotores;
    
    private volatile boolean isRunning = true;
    
    public MinhaThread( ArrayList<MotorStatus> statusMotores) {
        this.statusMotores = statusMotores;
    }
    Sensor sensor = new Sensor();
    Painel painel = new Painel(false);

    /*
     * O método void run() executa um loop while enquanto a variavel 'isRunning' for true. Dentro do loop
     * é gerada a temperatura aleatoria pela chamada do metodo 'gerarTemperatura(), essa mesma tem-
     * peratura é passada como parametro para a instanciação do motor juntamente com o RPM padrão.
     * Após isso é chamado o sensor para analisar o motor atual, e essa o resultado dessa analise é passa-
     * do para dentro da lista statusMotores. Tudo isso ocorre durante intervalos de 2 segundos.
     */
    public void run() {
    	while (isRunning) {
    		int temperatura = OperacoesMatematicas.gerarTemperatura();
            Motor motor = new Motor(temperatura, 2000);
            OperacoesMatematicas.calculoDesgasteMotor(motor);
            String status = (String) sensor.tratarTemperatura(motor, painel);
            synchronized (statusMotores) {
            	statusMotores.add(new MotorStatus(temperatura, status));
			}
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /*
     * Método void que determina o valor da variavel 'isRunning' para false.
     */
    public void parar() {
    	isRunning = false;
    }
}

