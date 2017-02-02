package DsaProgram;

public class Passenger {

int arrivalTime;
int serviceStartingTime; int serviceTime; Station station;
static int count = 1; final int number = count++;

public Passenger(int arrivalTime) { this.arrivalTime = arrivalTime; station = null;
}

public int waitingTime() {
return (station == null ? 0 : serviceStartingTime - arrivalTime); }

public void serviceStarting(int serviceStartingTime, int serviceTime, Station station) {
this.serviceStartingTime = serviceStartingTime; this.serviceTime = serviceTime;
this.station = station; }

public String printInfo() { if (station == null)
return "Passenger " + number + " - Coach - Arrival Time: " + arrivalTime + " - NOT SERVICED";
else
return "Passenger " + number + " - Coach - Arrival Time: " + arrivalTime + " - Service Starting Time: "
+ serviceStartingTime + " - Service Time: " + serviceTime
 
+ " - Station type: " + station.printInfo(); }
}
