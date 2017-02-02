package cs684.photoAlbum.simpleView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;



//abstract class Font_Option {
//	//JFonts
//	
//	public static final Font titleFont = new Font("Charlemagne Std", Font.BOLD, 20); 
//	public static final Font componentsFont = new Font("Charlemagne Std", Font.BOLD, 14);
//	
//	//Foreground color
//	public static Color themeColor = new Color(0x983B34);
//}
//this class shows up the window for creating the user

public class Add_User_Option extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected JLabel fullNameLbl;
	protected JLabel usernameLbl;
	protected JTextField fullNameTF;
	protected JTextField usernameTF;
	
	protected JButton createBtn;
	//protected ImageButton createBtn;
	protected JPanel mainPanel;
	
	protected FlowLayout fl = new FlowLayout();
	protected creationListener cl;
	
	Admin adminScreen;
	
	
	public Add_User_Option(Admin adminScreen){
		this.adminScreen = adminScreen;
		
		cl = new creationListener();
		this.setPreferredSize(new Dimension (250,250));
		mainPanel = new JPanel();
		mainPanel.setLayout(fl);
		mainPanel.setPreferredSize(new Dimension (300,300));
		
		
		fullNameLbl = new JLabel("Please enter the full name:");  //fullNameLbl.setSize(300, 300);
		fullNameLbl.setFont(Font_Option.componentsFont);
		fullNameLbl.setForeground(Font_Option.themeColor);
		fullNameLbl.setBackground(Color.ORANGE);
		
		usernameLbl = new JLabel("Username: ");
		usernameLbl.setFont(Font_Option.componentsFont);
		usernameLbl.setForeground(Font_Option.themeColor);
		
		fullNameTF = new JTextField(15);
		fullNameTF.setEditable(true);
		usernameTF = new JTextField(15);
		usernameTF.setEditable(true);
		
		
		createBtn = new JButton("Add User"); //createBtn.setSize(100, 100);
//		createBtn = new ImageButton("resources/createUP.jpg");
		createBtn.setBackground(Color.yellow);
		createBtn.setPressedIcon(new ImageIcon("resources/createDN.jpg"));
		createBtn.setPreferredSize(new Dimension(100,50));
		createBtn.addActionListener(cl);
		
		this.setBackground(Color.BLACK);
		mainPanel.add(fullNameLbl);
		mainPanel.add(fullNameTF);
		mainPanel.add(usernameLbl);
		mainPanel.add(usernameTF);
		mainPanel.add(createBtn);
				
		mainPanel.setBackground(Color.white);
		mainPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		this.add(mainPanel);
		//setSize(300,300);
		this.pack();
		setLocationRelativeTo(null);

	}
	
	
	protected class creationListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			adminScreen.backend.addUser(Add_User_Option.this.usernameTF.getText(), Add_User_Option.this.fullNameTF.getText());
			adminScreen.usersModel.addElement(Add_User_Option.this.usernameTF.getText());
			Add_User_Option.this.setVisible(false);
		}
		
	}
}










//added class here




class ImageButton extends JButton {

	private static final long serialVersionUID = 1L;

	public ImageButton(String img) {
	    this(new ImageIcon(img));
	  }

	  public ImageButton(ImageIcon icon) {
	    setIcon(icon);
	    setMargin(new Insets(0, 0, 0, 0));
	    setIconTextGap(0);
	    setBorderPainted(false);
	    setBorder(null);
	    setText(null);
	    setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
	  }

	}













