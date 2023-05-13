package carro;

public class Status {
	
	public int temperatura;
	public Motor motor;
	public double desgasteMotor;
	
	public Status(int temperatura, Motor motor, double desgasteMotor) {
		super();
		this.temperatura = temperatura;
		this.motor = motor;
		this.desgasteMotor = desgasteMotor;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	public double getDesgasteMotor() {
		return desgasteMotor;
	}

	public void setDesgasteMotor(double desgasteMotor) {
		this.desgasteMotor = desgasteMotor;
	}
	
}
