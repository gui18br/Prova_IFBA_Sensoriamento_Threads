package Thread;

import java.util.ArrayList;

import Operacoes.OperacoesMatematicas;
import carro.Motor;
import carro.MotorStatus;
import carro.Painel;
import carro.Sensor;
import carro.Status;

public class MinhaThread implements Runnable {
    private volatile boolean isRunning = true;
	
	private ArrayList<Status> status;
    
    public MinhaThread(ArrayList<Status> status) {
        this.status = status;
    }

    
    /*
     * Método void run que executa um loop 'while' enquando a variavel 'isRunning' for true.
     * Dentro do loop 'while' é chamado o método 'gerarTemperatura()' que gera uma tem-
     * peratura aleatória conforme o metódo executa, após isso essa temperatura é instân-
     * ciada na classe motor juntamente com o valor padrão do RPM e em seguida é cálcula-
     * do o valor do desgaste do motor. Todas essas informações são posteriormente adiciona-
     * das a lista 'status'. É realizado um 'try catch' para pausar a execução da thread por 2 segs
     * e capturar alguma possivel exception.
     */
    public void run() {
    	while (isRunning) {    		
    		int temperatura = OperacoesMatematicas.gerarTemperatura();
            Motor motor = new Motor(temperatura, 2000);
            double desgasteMotor = OperacoesMatematicas.calculoDesgasteMotor(motor);
            synchronized (status) {
				status.add(new Status(temperatura, motor, desgasteMotor));
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

