package fi.pku;

public class Station {

	protected int number, passengersServiced, totalServiceTime, maxServiceTime;
	protected Passenger passenger;
	protected int serviceTime;
	
	public Station(int number) {
		this.number = number;
		passengersServiced = 0;
		totalServiceTime = 0;
		maxServiceTime = 0;
		passenger = null;
	}
	
	public boolean isAvailable() {
		return (passenger == null);
	}
	
	public boolean servicePassenger(Passenger passenger, int serviceTime) {
		if (this.passenger != null)
			return false;
		this.serviceTime = serviceTime;
		this.passenger = passenger;
		passengersServiced++;
		totalServiceTime += serviceTime;
		if (serviceTime > maxServiceTime)
			maxServiceTime = serviceTime;
		return true;
	}
	
	public void tick() {
		if (passenger == null)
			return;
		serviceTime--;
		if (serviceTime == 0)
			passenger = null;
	}
	
	@Override
	public String toString() {
		return "Coach Station " + number + " - Passengers Serviced: " + passengersServiced + " - Max Service Time: " + maxServiceTime;
	}

}
