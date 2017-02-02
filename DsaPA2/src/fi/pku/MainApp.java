package fi.pku;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainApp {

	private static List<Station> stations = new ArrayList<Station>();

	public static void main(String[] args) {
		// Initialize parameters.
		IQueue first = new Queue();
		IQueue coach = new Queue();
		
		List<Passenger> allPassengers = new ArrayList<Passenger>();

		stations.add(new FirstClassStation(1));
		stations.add(new FirstClassStation(2));
		stations.add(new Station(1));
		stations.add(new Station(2));
		stations.add(new Station(3));

		final int R1 = getParameter("Enter average arrival rate for first class: ");
		final int R2 = getParameter("Enter average arrival rate for coach: ");

		final int S1 = getParameter("Enter maximum service time for first class: ");
		final int S2 = getParameter("Enter maximum service time for coach: ");
		
		final int D = getParameter("Enter duration of simulation: ");
		
		int duration = 0;
		
		int maxQueueLengthFirstClass = 0, maxQueueLengthCoach = 0;
		
		// Begin simulation.
		while (duration++ < D) {
			Random rand = new Random();
			
			// Check for passengers.
			if (rand.nextDouble() < (1 / (double)R1)) {
				Passenger p = new FirstClassPassenger(duration);
				allPassengers.add(p);
				first.enQueue(p);
			} 
			if (rand.nextDouble() < (1 / (double)R2)) {
				Passenger p = new Passenger(duration);
				allPassengers.add(p);
				coach.enQueue(p);
			}
			
			// Assign passengers to stations if passengers are waiting and stations are available.
			while (!first.isEmptyQueue()) {
				Station s = getFreeStation(true);
				if (s == null)
					break;
				
				Passenger p = (Passenger) first.deQueue();
				int serviceTime = rand.nextInt(S1) + 1;
				
				p.serviceStarting(duration, serviceTime, s);
				s.servicePassenger(p, serviceTime);
			}
			while (!coach.isEmptyQueue()) {
				Station s = getFreeStation(false);
				if (s == null)
					break;
				
				Passenger p = (Passenger) coach.deQueue();
				int serviceTime = rand.nextInt(S2) + 1;
				
				p.serviceStarting(duration, serviceTime, s);
				s.servicePassenger(p, serviceTime);
			}
			
			// Pass one unit of time.
			for (Station s : stations)
				s.tick();
			
			// Check statistics.
			if (first.queueSize() > maxQueueLengthFirstClass)
				maxQueueLengthFirstClass = first.queueSize();
			if (coach.queueSize() > maxQueueLengthCoach)
				maxQueueLengthCoach = coach.queueSize();
		}
		
		// Print statistics.
		for (Passenger p : allPassengers)
			System.out.println(p.toString());
		
		int totalServiceTimeCoach = 0, totalServiceTimeFirstClass = 0;
		int totalPassengersCoach = 0, totalPassengersFirstClass = 0;
		int maxServiceTimeCoach = 0, maxServiceTimeFirstClass = 0;
		
		for (Station s : stations) {
			System.out.println(s.toString());
			if (s instanceof FirstClassStation) {
				totalServiceTimeFirstClass += s.totalServiceTime;
				totalPassengersFirstClass += s.passengersServiced;
				if (s.maxServiceTime > maxServiceTimeFirstClass)
					maxServiceTimeFirstClass = s.maxServiceTime;
			} else {
				totalServiceTimeCoach += s.totalServiceTime;
				totalPassengersCoach += s.passengersServiced;
				if (s.maxServiceTime > maxServiceTimeCoach)
					maxServiceTimeCoach = s.maxServiceTime;
			}
		}

		System.out.println("Average First Class Service Time: " + ((double)totalServiceTimeFirstClass / totalPassengersFirstClass));
		System.out.println("Average Coach Service Time: " + ((double)totalServiceTimeCoach / totalPassengersCoach));
		System.out.println("Max First Class Service Time: " + maxServiceTimeFirstClass);
		System.out.println("Max Coach Service Time: " + maxServiceTimeCoach);
		System.out.println("Total First Class Passengers: " + totalPassengersFirstClass);
		System.out.println("Total Coach Passengers: " + totalPassengersCoach);
		System.out.println("Max First Class Queue Length: " + maxQueueLengthFirstClass);
		System.out.println("Max Coach Queue Length: " + maxQueueLengthCoach);
		
		System.out
		.println("Busy Rate at First Class Station: "
		+ Double.valueOf((new DecimalFormat("#.##")) .format((double)
		(totalPassengersFirstClass)
		/ D)));

		System.out.println("Busy Rate at Coach Station: "
		+ Double.valueOf((new DecimalFormat("#.##")) .format((double) totalPassengersCoach / D)));

		
	}
	
	private static int getParameter(String message) {
		System.out.println(message);
		try {
			Scanner scanIn = new Scanner(System.in);
			return scanIn.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("An integer input is required.");
			return getParameter(message);
		}
	}
	
	private static Station getFreeStation(boolean firstClassOnly) {
		if (!firstClassOnly)
			for (Station s : stations)
				if (!(s instanceof FirstClassStation) && s.isAvailable())
					return s;
		for (Station s : stations)
			if (s instanceof FirstClassStation && s.isAvailable())
				return s;
		return null;
	}

}
