package DsaProgram;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class AirLineCounterSimulator extends JFrame {

	JPanel contentPane;
	JTextField txtFCArrival;
	JTextField txtCoachArrival;
	JTextField txtFCService;
	JTextField txtCoachService;
	JTextField txtDuration;
	JTextPane txtOutput;
	boolean detailedOutput = false; // to print sops on system

	private List<Station> stations;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AirLineCounterSimulator frame = new AirLineCounterSimulator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

public AirLineCounterSimulator() { setTitle("CS-610: Airline Simulation");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setBounds(300, 300, 700, 700);
contentPane = new JPanel(); getRootPane().setBorder(
BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK)); contentPane.setLayout(null);
contentPane.setBackground(Color.white); setContentPane(contentPane);

JLabel lblAverageArrivalRate = new JLabel(
"** AIRLINE CHECK-IN COUNTER QUEUE SIMULATION **"); lblAverageArrivalRate.setFont(new Font("Arial", Font.BOLD, 14)); lblAverageArrivalRate.setBounds(10, 15, 590, 20); lblAverageArrivalRate.setForeground(Color.BLUE); contentPane.add(lblAverageArrivalRate);

JLabel lblFirstClass = new JLabel("First Class Average arrival rate: "); lblFirstClass.setBounds(10, 51, 300, 14); contentPane.add(lblFirstClass);

txtFCArrival = new JTextField(); txtFCArrival.setText("5"); txtFCArrival.setBounds(303, 51, 44, 20); contentPane.add(txtFCArrival); txtFCArrival.setColumns(10);

JLabel lblCoach = new JLabel("Coach Average arrival rate: "); lblCoach.setBounds(10, 81, 300, 14); contentPane.add(lblCoach);

txtCoachArrival = new JTextField(); txtCoachArrival.setText("3"); txtCoachArrival.setColumns(10); txtCoachArrival.setBounds(303, 81, 44, 20); contentPane.add(txtCoachArrival);

// Maximum service time - First class JLabel lblFirstClass_1 = new JLabel(
"Maximum service time for First Class:"); lblFirstClass_1.setBounds(10, 111, 300, 14); contentPane.add(lblFirstClass_1);

txtFCService = new JTextField(); txtFCService.setText("8"); txtFCService.setColumns(10); txtFCService.setBounds(303, 111, 44, 20); contentPane.add(txtFCService);

// Maximum service time - Coach
JLabel lblCoach_1 = new JLabel("Maximum service time for Coach:"); lblCoach_1.setBounds(10, 140, 300, 14); contentPane.add(lblCoach_1);

txtCoachService = new JTextField(); txtCoachService.setText("10"); txtCoachService.setColumns(10); txtCoachService.setBounds(303, 140, 44, 20); contentPane.add(txtCoachService);
 
// DURATION of Experiment
JLabel lblDuration = new JLabel("Expirement Duration (in mins):"); lblDuration.setBounds(10, 170, 400, 20); contentPane.add(lblDuration);

txtDuration = new JTextField(); txtDuration.setText("1500"); txtDuration.setBounds(303, 170, 45, 20); contentPane.add(txtDuration); txtDuration.setColumns(10);

JButton btnRunSimulation = new JButton("Run Simulation"); btnRunSimulation.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
runSimulation(); }
});
btnRunSimulation.setBounds(150, 220, 142, 23); contentPane.add(btnRunSimulation);

txtOutput = new JTextPane(); txtOutput.setOpaque(false); txtOutput.setBounds(23, 245, 1800, 500); contentPane.setSize(900, 900); contentPane.add(txtOutput);

}

	// To return the station which is free and available for next passenger //
	// service
	private Station getFreeStation(boolean firstClassOnly) {
		if (!firstClassOnly)
			for (Station s : stations)
				if (!(s instanceof FirstClassStation) && s.isFree())
					return s;
		for (Station s : stations)
			if (s instanceof FirstClassStation && s.isFree())
				return s;
		return null;
	}

private void runSimulation() {

// to get the values of arrival and service rate from the screen final int R1, R2, S1, S2, D;

try {
R1 = Integer.parseInt(txtFCArrival.getText());
R2 = Integer.parseInt(txtCoachArrival.getText());

S1 = Integer.parseInt(txtFCService.getText());
S2 = Integer.parseInt(txtCoachService.getText());

D = Integer.parseInt(txtDuration.getText()); } catch (NumberFormatException e) {
return; }

// Initialize parameters

// First Class & Coach Passenger Queue:
PassengerQueue firstClass_Q = new PassengerQueue(5000);
 
PassengerQueue coachClass_Q = new PassengerQueue(5000);

// Two service stations for first class, and three for coach stations = new ArrayList<Station>();

stations.add(new FirstClassStation(1)); stations.add(new FirstClassStation(2)); stations.add(new Station(1)); stations.add(new Station(2)); stations.add(new Station(3));

// Variables needed to Print statistics int totalServiceTimeCoach = 0;
int totalServiceTimeFirstClass = 0; int totalPassengersCoach = 0;
int totalPassengersFirstClass = 0;
int totalCoachPassengersAtFirstClass = 0; int maxServiceTimeCoach = 0;
int maxServiceTimeFirstClass = 0; int totalWaitTimeCoach = 0;
int totalWaitTimeFirstClass = 0; int maxQueueLengthFirstClass = 0; int maxQueueLengthCoach = 0;

List<Passenger> newPassenger = new ArrayList<Passenger>(); Passenger.count = 1;
int duration = 0; // Starting the timer

// Begin simulation. while (duration++ < D) {

/*
* Check for arrival of new passengers: Using Random Numbers -* Return a value between 0 and 1 if true then create a new
* passenger add that passenger to the passenger_Queue with * duration/timer as passenger Arrival Time
*/

Random randomNo = new Random(); double random = randomNo.nextDouble();

if (random < (1 / (double) R1)) {
Passenger p = new FirstClassPassenger(duration); newPassenger.add(p);
firstClass_Q.enqueue(p); }
if (random < (1 / (double) R2)) {
Passenger p = new Passenger(duration); newPassenger.add(p); coachClass_Q.enqueue(p);
}

/*
* Assign passengers to stations if passengers are waiting and * stations are available.
*
* If the passenger_queue is not empty; check for the free station, * if free; remove the passenger from the queue; S
*/

while (!firstClass_Q.isEmpty()) { Station s = getFreeStation(true);
 
if (s == null) break;
Passenger p = (Passenger) firstClass_Q.dequeue(); int serviceTime = randomNo.nextInt(S1) + 1; p.serviceStarting(duration, serviceTime, s); s.servicePass(p, serviceTime);
}

while (!coachClass_Q.isEmpty()) { Station s = getFreeStation(false); if (s == null)
break;
Passenger p = (Passenger) coachClass_Q.dequeue(); int serviceTime = randomNo.nextInt(S2) + 1; p.serviceStarting(duration, serviceTime, s); s.servicePass(p, serviceTime);
}

// Pass one unit of time;
// to calculate serviceTime when passenger is served for (Station s : stations)
s.time();

// Check statistics.
// To get the MAXIMUM QUEUE LENGTH
if (firstClass_Q.size() > maxQueueLengthFirstClass) maxQueueLengthFirstClass = firstClass_Q.size();
if (firstClass_Q.size() > maxQueueLengthCoach) maxQueueLengthCoach = coachClass_Q.size();
}

	if(detailedOutput)System.out.println("------------------- Passenger Class info ---------------------------------");for(

	Passenger p:newPassenger)
	{
		if (detailedOutput)
			System.out.println(p.printInfo());

		if (p instanceof FirstClassPassenger)
			totalWaitTimeFirstClass += p.waitingTime();
		else {
			totalWaitTimeCoach += p.waitingTime();
			if (p.station != null && p.station instanceof FirstClassStation)
				totalCoachPassengersAtFirstClass++;
		}
	}

	if(detailedOutput)System.out.println("------------------- Station Class Info ---------------------------------");for(
	Station s:stations)
	{
		if (detailedOutput)
			System.out.println(s.printInfo());

		if (s instanceof FirstClassStation) {
			totalServiceTimeFirstClass += s.totalServiceTime;
			totalPassengersFirstClass += s.noOfPassengersServiced;
			if (s.maxServiceTime > maxServiceTimeFirstClass)
				maxServiceTimeFirstClass = s.maxServiceTime;
		} else {

			totalServiceTimeCoach += s.totalServiceTime;
			totalPassengersCoach += s.noOfPassengersServiced;
			if (s.maxServiceTime > maxServiceTimeCoach)
				maxServiceTimeCoach = s.maxServiceTime;
		}
	}

	if(detailedOutput)
	{
		System.out.println("------------------- Statistics ---------------------------------");
		System.out.println("Average First Class Service Time: " + Double.valueOf(
				(new DecimalFormat("#.##")).format((double) totalServiceTimeFirstClass / totalPassengersFirstClass)));
		System.out.println("Average Coach Service Time: " + Double
				.valueOf((new DecimalFormat("#.##")).format((double) totalServiceTimeCoach / totalPassengersCoach)));
		System.out.println("Max First Class Service Time: " + maxServiceTimeFirstClass);
		System.out.println("Max Coach Service Time: " + maxServiceTimeCoach);
		System.out.println("Total First Class Passengers Served: " + totalPassengersFirstClass);
		System.out.println("Total Coach Passengers Served: " + totalPassengersCoach);
		System.out.println("Total Coach Passengers Served at First Class: " + totalCoachPassengersAtFirstClass);
		System.out.println("Max First Class Queue Length: " + maxQueueLengthFirstClass);
		System.out.println("Max Coach Queue Length: " + maxQueueLengthCoach);
		System.out.println("Average First Class Wait Time: " + Double.valueOf(
				(new DecimalFormat("#.##")).format((double) totalWaitTimeFirstClass / totalPassengersFirstClass)));
		System.out.println("Average Coach Wait Time: " + Double
				.valueOf((new DecimalFormat("#.##")).format((double) totalWaitTimeCoach / totalPassengersFirstClass)));

		System.out.println("Busy Rate at First Class Station: " + Double.valueOf((new DecimalFormat("#.##"))
				.format((double) (totalPassengersFirstClass + totalCoachPassengersAtFirstClass) / D)));

		System.out.println("Busy Rate at Coach Station: "
				+ Double.valueOf((new DecimalFormat("#.##")).format((double) totalPassengersCoach / D)));

	}

	// AVERAGE SERVICE TIME = Total Service time/Total No.of passengers //
	// MAXIMUM QUEUE LENGTH = PASSENGER_QUEUE Size ....
	// BUSY RATE = Total passengers served/Experiment Duration

	txtOutput.setForeground(Color.blue);txtOutput.setText("Average Service Time: \n	First Class Station:

	"+Double.valueOf((new DecimalFormat("#.##")).format((double)totalServiceTimeFirstClass/totalPassengersFirstClass))+"\n	Coach Station: "+Double.valueOf((new DecimalFormat("#.##")).format((double)totalServiceTimeCoach/totalPassengersCoach))+"\n\nMaximum Service Time: \n	First Class Station: "+maxServiceTimeFirstClass+"\n	Coach Station: "+maxServiceTimeCoach+"\n\nMaximum Queue Length: \n	First Class Passengers: "+maxQueueLengthFirstClass+"\n	Coach Passengers: "+maxQueueLengthCoach+"\n\nAverage Wait Time: \n	First Class Passengers: "+Double.valueOf((new DecimalFormat("#.##")).format((double)totalWaitTimeFirstClass/totalPassengersFirstClass))+"\n	Coach Passengers: "+Double.valueOf((new DecimalFormat("#.##")).format((double)totalWaitTimeCoach/totalPassengersFirstClass))+"\n\nTotal Passengers Serviced at respectivestations:\
	n First
	Class Station:"+totalPassengersFirstClass+"\n	Coach Station: "+totalPassengersCoach+"\n\nCoach Passengers Serviced at First Class Station: "+totalCoachPassengersAtFirstClass+"\n\n Busy Rate at Stations: \n	First Class Station: "+Double.valueOf((new DecimalFormat("#.##")).format((double)(totalPassengersFirstClass+totalCoachPassengersAtFirstClass)/D))+"\n	Coach Station: "+Double.valueOf((new DecimalFormat("#.##")).format((double)totalPassengersCoach/D))

	);

}}
