package cs684.photoAlbum.simpleView;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import cs684.photoAlbum.control.*;
import cs684.photoAlbum.model.*;

import java.beans.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;




//This class is the first screen the user is going to see when it runs the program. 
//It asks for the username and it calls either AlbumScreen or Admin.
public class Main_Login extends JFrame implements MouseListener, PropertyChangeListener{

	private static final long serialVersionUID = 1L;
	protected JTextField usernameT;
	protected JLabel username;
	
	protected JButton logIn;
	
	//protected ImageButton logIn;
	protected JPanel right;
	protected JPanel container;
	protected FlowLayout fl = new FlowLayout(); //Layout for my frame
	protected Admin admin; //This is the Admin Screen
	public AlbumsScreen sesion; //First screen users sees once logged in
	static boolean gate = false;
	JProgressBar progressBar;
	private boolean done = false;
	private Task task;
	
	
	class Task extends SwingWorker<Void, Void> {
        
		Main_Login Main_Login;
		boolean userLoaded = false;
		boolean adminLoaded = false;
		
		@Override
        public Void doInBackground() {
			progressBar.setVisible(true);
			Main_Login.repaint();
			
			BackendInterface backend= new Backend(); //Variable to communicate with Model merely for modification and retrieval of information.
		    try {
				backend= backend.readUser();
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} //Loading program

		    ControlInterface control = new Control(backend); //Variable to communicate with Control and perform all logic and data processes.
		    Hashtable<String,User> users = new Hashtable<String,User>();
		    String user;
			boolean done = false;

				users= control.getUsers();
				
				if(users.size()<0){
					progressBar.setVisible(false);
					Main_Login.repaint();
				}				
				
				try{	
					
					user = usernameT.getText();
					done = control.logIn(user);								
					
					if(user.equalsIgnoreCase("admin")){	
						admin = new Admin(backend,Main_Login);
						adminLoaded=true;
					}
					else if(!done){
						
						userLoaded=false;
					}					
					else{
						
						String userName = control.getUser().getFullName();
						String[] name = new String[2];
						name = userName.split(" ");		
						
						sesion = new AlbumsScreen(name[0],control,backend,Main_Login);
						userLoaded=true;
						
					}
				}
				catch(Exception exp){
					System.out.println("Please input the username, try again..."+exp);
					
				}
				return null;
        }
		
		public void setLoginScreen(Main_Login lg){
			this.Main_Login=lg;
			
		}
 
        
        @Override
        public void done() {
        	progressBar.setVisible(false);
			Main_Login.repaint();
            Toolkit.getDefaultToolkit().beep();
            setCursor(null); //turn off the wait cursor
            if(userLoaded){
	            sesion.setVisible(true);
				sesion.setLocationRelativeTo(null);
				setVisible(false);
            }
            else if(adminLoaded){
	            admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				setVisible(false);
            }
            else{
            	final JFrame error = new JFrame("Important Message!");
				JOptionPane.showMessageDialog(error, "User does not exist!");
            }
        }

		
    }
	
	
	public Main_Login(){
		
		usernameT = new JTextField(20);
		usernameT.setEditable(true);
		username = new JLabel("Please enter the user name below");
		username.setFont(Font_Option.componentsFont);
		username.setForeground(Font_Option.themeColor);
		right = new JPanel();
		//logIn = new ImageButton("resources/loginUP.jpg");
		
		//logIn = new ImageButton("Login");
		logIn = new JButton("Login");
		
		logIn.setPressedIcon(new ImageIcon("resources/loginDN.jpg"));
		logIn.setPreferredSize(new Dimension(100,30));
		container = new JPanel(fl);
			
		right.setLayout(new BorderLayout());
		right.add(logIn,BorderLayout.LINE_END);
		right.setBackground(Color.BLACK);
		
		//Create the progress bar.
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setString("Loading...");
        progressBar.setVisible(false);
		
		container.setBackground(Color.WHITE);
		container.add(username);
		container.add(usernameT);
		container.add(right);
		container.add(progressBar);
		//Adding elements to the Frame
		add(container);
		//setSize(500,500);
		
		logIn.addMouseListener(this);
				
		this.setBackground(Color.WHITE);
		setSize(260,200);
		//setSize(500,500);
		
		setResizable(false);		
	}
	
 
    
    public void propertyChange(PropertyChangeEvent evt) {
    	 if (!done) {
    	        int progress = task.getProgress();
    	        if (progress == 0) {
    	            progressBar.setIndeterminate(true);
    	        } else {
    	            progressBar.setIndeterminate(false); 
    	            progressBar.setString(null);
    	            progressBar.setValue(progress);
    	        }
    	    }
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        task = new Task();
        task.addPropertyChangeListener(this);
        task.setLoginScreen(this);
        task.execute();
       
		
	}

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Main_Login login = new Main_Login();
		    
	    login.addWindowListener(new WindowAdapter() {
			
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}







 



















