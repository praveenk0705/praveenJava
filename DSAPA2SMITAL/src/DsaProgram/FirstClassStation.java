package DsaProgram;

public class FirstClassStation extends Station {

public FirstClassStation(int stationNumber) { super(stationNumber);
}

@Override
public String printInfo() {
return "First Class Station " + stationNumber
+ " - Passengers Serviced: " + noOfPassengersServiced + " - Max Service Time: " + maxServiceTime;
} }
