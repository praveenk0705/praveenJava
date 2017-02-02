package DsaProgram;

public class Station {
Passenger passengerServed; int stationNumber;
int noOfPassengersServiced; // remaining service time // busy rate
int totalServiceTime; int maxServiceTime; int serviceTime;

// Constructor initializing the values public Station(int stationNumber) {
this.stationNumber = stationNumber; noOfPassengersServiced = 0; totalServiceTime = 0; maxServiceTime = 0; passengerServed = null;
}

// to check if the station is available public boolean isFree() {
return (passengerServed == null); }

public boolean servicePass(Passenger passenger, int serviceTime) { if (this.passengerServed != null)
return false; this.serviceTime = serviceTime; this.passengerServed = passenger;
noOfPassengersServiced++; // increment the no.of passengers totalServiceTime += serviceTime; // calculating total service time

if (serviceTime > maxServiceTime) maxServiceTime = serviceTime;

return true; }

public void time() {
if (passengerServed == null) return;
serviceTime--;
if (serviceTime == 0) passengerServed = null;
}

public String printInfo() {
return "Coach Station " + stationNumber + " - Passengers Serviced: " + noOfPassengersServiced + " - Max Service Time: "
+ maxServiceTime; }
 
}
