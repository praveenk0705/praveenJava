package fi.pku;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
public class Application extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstClassArrival;
	private JTextField txtCoachArrival;
	private JTextField txtFirstClassService;
	private JTextField txtCoachService;
	private JTextField txtDuration;
	private JTextPane txtOutput;

	private List<Station> stations;

	private boolean debug = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		setTitle("Airline Check-in Counter Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		setBounds(100, 100, 480, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblAverageArrivalRate = new JLabel("Average arrival rate");
		lblAverageArrivalRate.setBounds(10, 11, 204, 14);
		contentPane.add(lblAverageArrivalRate);

		JLabel lblFirstClass = new JLabel("First Class");
		lblFirstClass.setBounds(40, 36, 84, 14);
		contentPane.add(lblFirstClass);

		JLabel lblCoach = new JLabel("Coach");
		lblCoach.setBounds(224, 36, 77, 14);
		contentPane.add(lblCoach);

		JLabel lblMaximumServiceTime = new JLabel("Maximum service time");
		lblMaximumServiceTime.setBounds(10, 61, 204, 14);
		contentPane.add(lblMaximumServiceTime);

		JLabel lblFirstClass_1 = new JLabel("First Class");
		lblFirstClass_1.setBounds(40, 86, 84, 14);
		contentPane.add(lblFirstClass_1);

		JLabel lblCoach_1 = new JLabel("Coach");
		lblCoach_1.setBounds(224, 86, 77, 14);
		contentPane.add(lblCoach_1);

		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(145, 117, 87, 14);
		contentPane.add(lblDuration);

		txtFirstClassArrival = new JTextField();
		txtFirstClassArrival.setText("5");
		txtFirstClassArrival.setBounds(134, 33, 45, 20);
		contentPane.add(txtFirstClassArrival);
		txtFirstClassArrival.setColumns(10);

		txtCoachArrival = new JTextField();
		txtCoachArrival.setText("3");
		txtCoachArrival.setColumns(10);
		txtCoachArrival.setBounds(303, 33, 44, 20);
		contentPane.add(txtCoachArrival);

		txtFirstClassService = new JTextField();
		txtFirstClassService.setText("8");
		txtFirstClassService.setColumns(10);
		txtFirstClassService.setBounds(134, 86, 45, 20);
		contentPane.add(txtFirstClassService);

		txtCoachService = new JTextField();
		txtCoachService.setText("10");
		txtCoachService.setColumns(10);
		txtCoachService.setBounds(303, 83, 44, 20);
		contentPane.add(txtCoachService);

		txtDuration = new JTextField();
		txtDuration.setText("100");
		txtDuration.setBounds(234, 114, 45, 20);
		contentPane.add(txtDuration);
		txtDuration.setColumns(10);

		JButton btnRunSimulation = new JButton("Run Simulation");
		btnRunSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runSimulation();
			}
		});
		btnRunSimulation.setBounds(140, 142, 142, 23);
		contentPane.add(btnRunSimulation);

		txtOutput = new JTextPane();
		txtOutput.setOpaque(false);
		///
		txtOutput.setBounds(23, 179, 352, 250);
		contentPane.add(txtOutput);
	}

	private void runSimulation() {
		// Initialize parameters.
		IQueue first = new Queue();
		IQueue coach = new Queue();

		List<Passenger> allPassengers = new ArrayList<Passenger>();
		stations = new ArrayList<Station>();

		Passenger.count = 1;

		stations.add(new FirstClassStation(1));
		stations.add(new FirstClassStation(2));
		stations.add(new Station(1));
		stations.add(new Station(2));
		stations.add(new Station(3));

		final int R1, R2, S1, S2, D;

		try {
			R1 = Integer.parseInt(txtFirstClassArrival.getText());
			R2 = Integer.parseInt(txtCoachArrival.getText());

			S1 = Integer.parseInt(txtFirstClassService.getText());
			S2 = Integer.parseInt(txtCoachService.getText());

			D = Integer.parseInt(txtDuration.getText());
		} catch (NumberFormatException e) {
			return;
		}

		int duration = 0;

		int maxQueueLengthFirstClass = 0, maxQueueLengthCoach = 0;

		// Begin simulation.
		while (duration++ < D) {
			Random rand = new Random();

			// Check for passengers.
			if (rand.nextDouble() < (1 / (double) R1)) {
				Passenger p = new FirstClassPassenger(duration);
				allPassengers.add(p);
				first.enQueue(p);
			}
			if (rand.nextDouble() < (1 / (double) R2)) {
				Passenger p = new Passenger(duration);
				allPassengers.add(p);
				coach.enQueue(p);
			}

			// Assign passengers to stations if passengers are waiting and
			// stations are available.
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
		int totalServiceTimeCoach = 0, totalServiceTimeFirstClass = 0;
		int totalPassengersCoach = 0, totalPassengersFirstClass = 0, totalCoachPassengersAtFirstClass = 0;
		int maxServiceTimeCoach = 0, maxServiceTimeFirstClass = 0;
		int totalWaitTimeCoach = 0, totalWaitTimeFirstClass = 0;

		for (Passenger p : allPassengers) {
			if (debug)
				System.out.println(p.toString());
			if (p instanceof FirstClassPassenger)
				totalWaitTimeFirstClass += p.waitTime();
			else {
				totalWaitTimeCoach += p.waitTime();
				if (p.station != null && p.station instanceof FirstClassStation)
					totalCoachPassengersAtFirstClass++;
			}
		}

		for (Station s : stations) {
			if (debug)
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

		if (debug) {
			System.out.println("Average First Class Service Time: "
					+ Double.valueOf((new DecimalFormat("#.##"))
							.format((double) totalServiceTimeFirstClass
									/ totalPassengersFirstClass)));
			System.out.println("Average Coach Service Time: "
					+ Double.valueOf((new DecimalFormat("#.##"))
							.format((double) totalServiceTimeCoach
									/ totalPassengersCoach)));
			System.out.println("Max First Class Service Time: "
					+ maxServiceTimeFirstClass);
			System.out
					.println("Max Coach Service Time: " + maxServiceTimeCoach);
			System.out.println("Total First Class Passengers Served: "
					+ totalPassengersFirstClass);
			System.out.println("Total Coach Passengers Served: "
					+ totalPassengersCoach);
			System.out.println("Total Coach Passengers Served at First Class: "
					+ totalCoachPassengersAtFirstClass);
			System.out.println("Max First Class Queue Length: "
					+ maxQueueLengthFirstClass);
			System.out
					.println("Max Coach Queue Length: " + maxQueueLengthCoach);
			System.out.println("Average First Class Wait Time: "
					+ Double.valueOf((new DecimalFormat("#.##"))
							.format((double) totalWaitTimeFirstClass
									/ totalPassengersFirstClass)));
			System.out.println("Average Coach Wait Time: "
					+ Double.valueOf((new DecimalFormat("#.##"))
							.format((double) totalWaitTimeCoach
									/ totalPassengersFirstClass)));
			
			System.out
			.println("Busy Rate at First Class Station: "
			+ Double.valueOf((new DecimalFormat("#.##")) .format((double)
			(totalPassengersFirstClass + totalCoachPassengersAtFirstClass)
			/ D)));

			System.out.println("Busy Rate at Coach Station: "
			+ Double.valueOf((new DecimalFormat("#.##")) .format((double) totalPassengersCoach / D)));

		}

		txtOutput.setText("Average Service Time at First Class Station:  "
				+ Double.valueOf((new DecimalFormat("#.##"))
						.format((double) totalServiceTimeFirstClass
								/ totalPassengersFirstClass))
				+ "\nAverage Service Time at Coach Station:  "
				+ Double.valueOf((new DecimalFormat("#.##"))
						.format((double) totalServiceTimeCoach
								/ totalPassengersCoach))
				+ "\nMaximum Service Time at First Class Station:  "
				+ maxServiceTimeFirstClass
				+ "\nMaximum Service Time at Coach Station:  "
				+ maxServiceTimeCoach
				+ "\nTotal Passengers Serviced at First Class Station:  "
				+ totalPassengersFirstClass
				+ "\nCoach Passengers Serviced at First Class Station:  "
				+ totalCoachPassengersAtFirstClass
				+ "\nTotal Passengers Serviced at Coach Station:  "
				+ totalPassengersCoach
				+ "\nMaximum Queve Length for First Class Passengers:  "
				+ maxQueueLengthFirstClass
				+ "\nMaximum Queue Length for Coach Passengers:  "
				+ maxQueueLengthCoach
				//////////////////
				+ "\n\n Busy Rate at Stations: \n	First Class Station: "
				+ Double.valueOf((new DecimalFormat("#.##")) .format((double)
				(totalPassengersFirstClass + totalCoachPassengersAtFirstClass)
				/ D)) + "\n	Coach Station: "
				+ Double.valueOf((new DecimalFormat("#.##")) .format((double) totalPassengersCoach /
				D))


				////////////////////
				+ "\nAverage Wait Time for First Class Passengers:  "
				+ Double.valueOf((new DecimalFormat("#.##"))
						.format((double) totalWaitTimeFirstClass
								/ totalPassengersFirstClass))
				+ "\nAverage Wait Time for Coach Passengers:  "
				+ Double.valueOf((new DecimalFormat("#.##"))
						.format((double) totalWaitTimeCoach
								/ totalPassengersFirstClass)));
	}

	private Station getFreeStation(boolean firstClassOnly) {
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
