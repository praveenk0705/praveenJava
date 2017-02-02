package fi.pku;

public class Passenger {
	
	protected static int count = 1;
	protected final int number = count++;
	
	protected int arrivalTime, serviceStartingTime, serviceTime;
	protected Station station;
	
	public Passenger(int arrivalTime) {
		this.arrivalTime = arrivalTime;
		station = null;
	}
	
	public void serviceStarting(int serviceStartingTime, int serviceTime, Station station) {
		this.serviceStartingTime = serviceStartingTime;
		this.serviceTime = serviceTime;
		this.station = station;
	}
	
	public int waitTime() {
		return (station == null ? 0 : serviceStartingTime - arrivalTime);
	}
	
	@Override
	public String toString() {
		if (station == null)
			return "Passenger " + number + " - Coach - Arrival Time: " + arrivalTime + " - NOT SERVICED";
		else
			return "Passenger " + number + " - Coach - Arrival Time: " + arrivalTime + " - Service Starting Time: " + serviceStartingTime + " - Service Time: " + serviceTime + " - Station type: " + station.toString();
	}

}
