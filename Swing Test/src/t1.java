import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.Panel;
import java.awt.Button;

public class t1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtUsername;
	private JPanel panel;
	private JScrollPane scrollPane;
	private Panel panel_1;
	private JPanel panel_2;
	private Button button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					t1 frame = new t1();
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
	public t1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new Button("New button");
		contentPane.add(button);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		panel_1 = new Panel();
		panel_2.add(panel_1);
		
		panel = new JPanel();
		contentPane.add(panel);
		
		txtUsername = new JTextField();
		txtUsername.setText("UserName");
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
	}

}
