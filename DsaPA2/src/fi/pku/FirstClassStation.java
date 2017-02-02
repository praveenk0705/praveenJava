package fi.pku;

public class FirstClassStation extends Station {

	public FirstClassStation(int number) {
		super(number);
	}

	@Override
	public String toString() {
		return "First Class Station " + number + " - Passengers Serviced: " + passengersServiced + " - Max Service Time: " + maxServiceTime;	
	}
}
