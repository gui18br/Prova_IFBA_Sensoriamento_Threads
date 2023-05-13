package carro;

public class MotorStatus {

	private String status;
	private int temperatura;
	
	public MotorStatus() {
		
	}
	
	public MotorStatus(String status, int temperatura) {
		super();
		this.status = status;
		this.temperatura = temperatura;
	}
	
	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}