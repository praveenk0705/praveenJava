package cs684.photoAlbum.simpleView;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import cs684.photoAlbum.control.Control;
import cs684.photoAlbum.control.ControlInterface;
import cs684.photoAlbum.model.*;



// this is the class which shows what admin will see. Admin will be able to add/delete users


public class Admin extends JFrame{

	private static final long serialVersionUID = 1L;
	
	protected JLabel title;
//	protected ImageButton addUserBtn; 
	protected JButton addUserBtn;
//	protected ImageButton deleteUserBtn;
	
	
	protected JButton deleteUserBtn;
	
//	protected ImageButton logOutBtn;
	
	protected JButton logOutBtn;
	
	protected JPanel buttonsPanel;
	protected JList users;
	protected DefaultListModel usersModel = new DefaultListModel();
	protected JScrollPane scrollPane;
	protected JPanel mainPanel;
	protected Add_User_Option createUser;
	
	protected actionlistener ol;
	protected BackendInterface backend;
	protected Hashtable<String,User> listUsers = new Hashtable<String,User>();
	protected Main_Login login;
	//
	protected ControlInterface control;
	
	
	public Admin(BackendInterface backend, final Main_Login login){
		
		
		this.control = new Control(backend);
		this.login = login;
		this.setBackground(Color.BLACK);
		listUsers = backend.getUsers();
		fillListUsers();
		
		ol = new actionlistener();
		this.backend = backend;
		
		title = new JLabel("Administrative Mode");
		title.setFont(Font_Option.titleFont);
		title.setForeground(Font_Option.themeColor);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setBorder(new EmptyBorder(0,5,10,5));
		
//		addUserBtn = new ImageButton("resources/addUserUP.jpg");
		
	//	addUserBtn = new JButton("resources/addUserUP.jpg");
		
		addUserBtn = new JButton("Click here to add a user");
		
		addUserBtn.setBackground(Color.green);
		
		addUserBtn.setActionCommand("Add user");
		addUserBtn.setPreferredSize(new Dimension(200,200));
		addUserBtn.setPressedIcon(new ImageIcon("resources/addUserDN.jpg"));
		addUserBtn.addActionListener(ol);
		addUserBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
//		deleteUserBtn = new ImageButton("resources/deleteUserUP.jpg");
		
		
		deleteUserBtn = new JButton("To delete a user, click here");
		
		
		deleteUserBtn.setActionCommand("Delete User");
		deleteUserBtn.setPreferredSize(new Dimension(200,200));
		deleteUserBtn.setPressedIcon(new ImageIcon("resources/deleteUserDN.jpg"));
		deleteUserBtn.addActionListener(ol);
		deleteUserBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteUserBtn.setBackground(Color.RED);
		
		//logOutBtn = new ImageButton("resources/logoutUP.jpg");
		
		logOutBtn = new JButton("To logout,click here");
		
		logOutBtn.setActionCommand("Log Out");
		logOutBtn.setPreferredSize(new Dimension(250,250));
		logOutBtn.setPressedIcon(new ImageIcon("resources/logoutDN.jpg"));
		logOutBtn.addActionListener(ol);
		logOutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		logOutBtn.setBackground(Color.WHITE);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS));
		buttonsPanel.add(addUserBtn);
		buttonsPanel.add(deleteUserBtn);
		buttonsPanel.add(logOutBtn);
		buttonsPanel.setBackground(Color.GRAY);
		buttonsPanel.setBorder(new EmptyBorder(20,40,20,20));
		
//		buttonsPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		
		users = new JList(usersModel);
		
//		users.setBorder(new EmptyBorder(40,20,30,40));
		
		
		users.setBorder(new EmptyBorder(0,20,20,30));
		
	//	JLabel label = new JLabel("List of users:");
		
		//users.setBorder(new EmptyBorder(20,10,40,20));
		//scrollPane.add(label);
		scrollPane = new JScrollPane(users);
		scrollPane.setBackground(Color.DARK_GRAY);
				
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setLayout( new BorderLayout());
		
		this.add(mainPanel);
//		mainPanel.add(title,BorderLayout.PAGE_START);
//		mainPanel.add(buttonsPanel,BorderLayout.LINE_START);
//		mainPanel.add(scrollPane,BorderLayout.LINE_END);
//		mainPanel.setBorder(new EmptyBorder(20,20,20,20));
		
		mainPanel.add(title,BorderLayout.PAGE_START);
		mainPanel.add(buttonsPanel,BorderLayout.LINE_END);
		mainPanel.add(scrollPane,BorderLayout.LINE_START);
		mainPanel.setBorder(new EmptyBorder(20,20,20,20));
		
		
		
//		this.setPreferredSize(new Dimension(430,250));
		this.setPreferredSize(new Dimension(430,300));
		
		this.pack();
		
		addWindowListener(new WindowAdapter(){
			
			public void windowClosing(WindowEvent we){
				Admin.this.saveState();
				System.exit(0);
				
			 }
		});
	}
	
	
	public void fillListUsers(){

		for(User user:listUsers.values()){
			usersModel.addElement(user.getUsername());
		}
		
	}
	
	//save the state of the object
	public void saveState(){
		try {
			this.backend.writeUser(this.backend);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//delete all the pics of a given user
	public void deleteUserPhotos(String user){
		
		String PathResources = "pictures";
		String PathThumnails = "thumbnails";
		
		control.logIn(user);
		
		Hashtable<String,Photo> photos = new Hashtable<String,Photo>();
		photos = control.listPhotos();
		
		for(Photo photo:photos.values()){
			if(photo.numberOfAlbumsBelongingTo()==1){
				String file =photo.getFilename();
				String[] fileInfo = new String[2];
				fileInfo=file.split("\\.");
				String fileName = fileInfo[0];
				String fileExtension = fileInfo[1];
				
				File photoF = new File(PathResources+File.separator+fileName+"."+fileExtension);
				File photoThumbF = new File(PathThumnails+File.separator+fileName+"thumb."+fileExtension);
				
				photoF.delete();
				photoThumbF.delete();
			}			
		}						
		
		
	}
	
	
	protected class actionlistener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			String action = e.getActionCommand();
			
			if(action.equals("Delete User")){
				String username = (String) users.getSelectedValue();
				if(username!=null&&!username.equals("")){
				int index = users.getSelectedIndex();
				deleteUserPhotos(username);
				Admin.this.backend.deleteUser(username);
				usersModel.remove(index);
				}
			}
			
			if(action.equals("Add user")){
				createUser = new Add_User_Option(Admin.this);
				createUser.setVisible(true);
			}
			
			if(action.endsWith("Log Out")){
				Admin.this.saveState();
				Admin.this.setVisible(false);
				Admin.this.login.setVisible(true);
			}
			
		}
		
		
	}
	
}


















