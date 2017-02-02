package cs684.photoAlbum.simpleView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cs684.photoAlbum.control.ControlInterface;



//this class represents the screen user will see when he clicks on adding the album
public class Create_Album_Screen extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JTextField albumNameTF;
	protected JLabel albumName;
//	protected ImageButton create;
	
	protected JButton create;
	protected JPanel right;
	protected JPanel container;
	AlbumsScreen albumScreen;
	ControlInterface control;
	protected FlowLayout fl = new FlowLayout();
	protected clistener cl;
	
	
	public Create_Album_Screen(AlbumsScreen albumsScreen, ControlInterface control){
		
		cl = new clistener();
		
		container = new JPanel();
		container.setLayout(fl);
		
		this.albumScreen = albumsScreen;
		this.control = control;
		
		albumNameTF = new JTextField(20);
		albumNameTF.setEditable(true);
		
		albumName = new JLabel("Please enter name of new album");
		albumName.setSize(120, 100);
		albumName.setFont(Font_Option.componentsFont);
		//albumName.setForeground(Font_Option.themeColor);
		albumName.setBackground(Color.orange);
		
		create = new JButton ("Create");
		//create = new ImageButton("resources/createUP.jpg");
		create.setPressedIcon(new ImageIcon("resources/createDN.jpg"));
		create.setPreferredSize(new Dimension(100,30));
		create.addActionListener(cl);
		
		container.add(albumName);
		container.add(albumNameTF);
		container.add(create);
		container.setBackground(Color.ORANGE);
		this.add(container);
		
	//	container.setBackground(Color.BLACK);
		container.setAlignmentY(Component.CENTER_ALIGNMENT);
		container.setBorder(new EmptyBorder(10, 10, 10, 10));
		setSize(260,145);
		setResizable(false);	
		setLocationRelativeTo(null);
		
		//Add listener to save the state of the user
				addWindowListener(new WindowAdapter(){
					
					public void windowClosing(WindowEvent we){
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					 }
				});
		
	}
	
	
	protected class clistener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			
			String albumName = albumNameTF.getText();
			
			if(albumName.equals("")){
            	final JFrame error = new JFrame("Important Message!");
				JOptionPane.showMessageDialog(error, "Please enter a name for the new Album");
			}
			
			else{
			
				try {
					Create_Album_Screen.this.albumScreen.addAlbum(Create_Album_Screen.this.control, albumName);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
						
		}
		
	}
	
}
