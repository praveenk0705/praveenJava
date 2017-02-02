package cs684.photoAlbum.simpleView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import cs684.photoAlbum.control.ControlInterface;
import cs684.photoAlbum.model.Album;
import cs684.photoAlbum.model.Photo;



//This class represents the screen shown when a user is going to add a new picture to an album.
public class Add_Photo_Options extends JFrame implements ActionListener, PropertyChangeListener {
	
	
	private static final long serialVersionUID = 1L;
	
	
	protected JButton browseBtn;
	protected JButton addBtn;
	protected JButton cancelBtn;
	
	
	
	
	protected JLabel photoNameLbl;	
//	protected ImageButton browseBtn;
//	protected ImageButton addBtn;
//	protected ImageButton cancelBtn;
	protected JPanel right;
	protected JPanel container;
	protected ControlInterface control;
	protected FlowLayout fl = new FlowLayout();
	protected creationListener cl;
	protected PhotosScreen photosScreen;
	protected Album album;
	protected String filename ;
	protected String dir;
	protected JFrame error = new JFrame("Message"); //Frame for errors
	protected ArrayList<File> files = new ArrayList<File>();
	//JScrollPane
	protected JScrollPane photosSP;
	protected DefaultListModel photosModel = new DefaultListModel();
	protected JPanel photoListPanel;
	protected JList photosList;
	
	protected JFileChooser c;
	protected int rVal;
	
	protected JProgressBar progressBar;
	public Task task;
	
	
	public Add_Photo_Options(ControlInterface control,PhotosScreen photosScreen,Album album){
		
		
		cl = new creationListener();
		
		container = new JPanel();
		container.setLayout(fl);	
		
		this.control = control;
		this.photosScreen = photosScreen;
		this.album = album;
		
				
		photoListPanel = new JPanel();
		photoListPanel.setLayout(new BoxLayout(photoListPanel,BoxLayout.Y_AXIS));
		photoListPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		photoListPanel.setOpaque(false);
		photoListPanel.setBorder(new EmptyBorder(20,20,20,20));
		
			
		photosList = new JList(photosModel);
		photosList.setOpaque(true);
		photosList.setAlignmentX(Component.CENTER_ALIGNMENT);
		photosSP = new JScrollPane(photosList);
		photosSP.setOpaque(true);
		photosSP.setAlignmentX(Component.CENTER_ALIGNMENT);
		photosSP.setPreferredSize(new Dimension(220,220));
		
				
		photoListPanel.add(photosSP);
		
		
		
		photoNameLbl = new JLabel("Files: ");
		photoNameLbl.setFont(Font_Option.componentsFont);
		photoNameLbl.setForeground(Font_Option.themeColor);		
		
		
		browseBtn = new JButton("Browse");
		browseBtn.setBackground(Color.GREEN);
		browseBtn.setSize(100, 100);
	//	browseBtn = new ImageButton("resources/browseUP.jpg");
	//	browseBtn.setPressedIcon(new ImageIcon("resources/browseDN.jpg"));
		//browseBtn.setPreferredSize(new Dimension(100,30));
		browseBtn.setActionCommand("browse");
		browseBtn.addActionListener(cl);
		
		//Create the progress bar.
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);
		
		
        addBtn = new JButton("Add");
        addBtn.setBackground(Color.GREEN);
        addBtn.setSize(100,100);
        //addBtn = new ImageButton("resources/addUP.jpg");
		//addBtn.setPressedIcon(new ImageIcon("resources/addDN.jpg"));
		//addBtn.setPreferredSize(new Dimension(100,30));		
		addBtn.addActionListener(this);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setBackground(Color.GREEN);
		cancelBtn.setSize(100,100);
		
		//cancelBtn = new ImageButton("resources/cancelUP.jpg");
		//cancelBtn.setPressedIcon(new ImageIcon("resources/cancelDN.jpg"));
		//cancelBtn.setPreferredSize(new Dimension(100,30));
		cancelBtn.setActionCommand("cancel");
		cancelBtn.addActionListener(cl);
		
		container.add(photoNameLbl);
		container.add(browseBtn);
		container.add(photoListPanel);
		container.add(progressBar);
		container.add(addBtn);
		container.add(cancelBtn);
		this.add(container);
		
		container.setBackground(Color.GRAY);
		container.setAlignmentY(Component.CENTER_ALIGNMENT);
		container.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setPreferredSize(new Dimension(260,420));
		this.pack();
		setResizable(false);	
		setLocationRelativeTo(null);
	}
	
	private void fillPhotosModel() {
		for(File file:files){
			if(!this.photosModel.contains(file.getName()))
			this.photosModel.addElement(file.getName());
		}
		
	}
	
	/**
     * Invoked when the user presses the add button.
     */
    public void actionPerformed(ActionEvent evt) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//addBtn.setEnabled(false);
		task = new Task();
		task.setLoginScreen(Add_Photo_Options.this);	        
        task.addPropertyChangeListener(Add_Photo_Options.this);
        task.execute();
		
	}
	
	

protected class creationListener implements ActionListener {
	
	
	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();
		
		if(action.equals("browse")){
			  c = new JFileChooser();
			  ImageFilter filter = new ImageFilter();
			  c.addChoosableFileFilter(filter);
			  c.setAcceptAllFileFilterUsed(false);
			  c.setFileFilter(filter);	
			  // Enable multiple selections
			  c.setMultiSelectionEnabled(true);
			  //Add the preview pane.
	          c.setAccessory(new ImagePreview(c));
	          if(photosScreen.currentDirectory!=null){
	        	  c.setCurrentDirectory(photosScreen.currentDirectory);
	          }
		      // Demonstrate "Open" dialog:
		      rVal = c.showOpenDialog(Add_Photo_Options.this);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		    	  for(File file: c.getSelectedFiles()){
		    		  if(!files.contains(file))
		    		  files.add(file);
		    	  }
		    	    
		    	    fillPhotosModel();
		    	    photosList.revalidate();
		    	    photosList.repaint();
		    	    photosSP.revalidate();
		    	    photosSP.repaint();
		    	    photoListPanel.revalidate();
		    	    photoListPanel.repaint();
			        photosScreen.currentDirectory = c.getCurrentDirectory();
			      }
		      else{
		    	  photosScreen.currentDirectory = c.getCurrentDirectory();
		      }
		}		
		else if(action.equals("cancel")){
			Add_Photo_Options.this.setVisible(false);
		}
		
					
	}
	
			
	}

public class ImagePreview extends JComponent
	implements PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon thumbnail = null;
	File file = null;
	
	public ImagePreview(JFileChooser fc) {
		setPreferredSize(new Dimension(100, 50));
		fc.addPropertyChangeListener(this);
	}
	
	public void loadImage() {
		if (file == null) {
			thumbnail = null;
		return;
	}
	
	//Don't use createImageIcon (which is a wrapper for getResource)
	//because the image we're trying to load is probably not one
	//of this program's own resources.
	ImageIcon tmpIcon = new ImageIcon(file.getPath());
		if (tmpIcon != null) {
			if (tmpIcon.getIconWidth() > 90) {
				thumbnail = new ImageIcon(tmpIcon.getImage().
	                getScaledInstance(90, -1,
	                            Image.SCALE_DEFAULT));
			} else { //no need to miniaturize
				thumbnail = tmpIcon;
			}
		}
	}
	
	public void propertyChange(PropertyChangeEvent e) {
		boolean update = false;
		String prop = e.getPropertyName();
	
		//If the directory changed, don't show an image.
		if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
			file = null;
			update = true;
	
			//If a file became selected, find out which one.
		} else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
			file = (File) e.getNewValue();
			update = true;
		}
	
		//Update the preview accordingly.
		if (update) {
			thumbnail = null;
			if (isShowing()) {
				loadImage();
				repaint();
			}
		}
	}

	protected void paintComponent(Graphics g) {
		if (thumbnail == null) {
			loadImage();
		}
		if (thumbnail != null) {
			int x = getWidth()/2 - thumbnail.getIconWidth()/2;
			int y = getHeight()/2 - thumbnail.getIconHeight()/2;

			if (y < 0) {
				y = 0;
			}

			if (x < 5) {
				x = 5;
			}
			thumbnail.paintIcon(this, g, x, y);
		}
	}
}
 

public class ImageFilter extends FileFilter {
 
    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        Utils utils = new Utils() ;
        String extension = utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.tiff) ||
                extension.equals(Utils.tif) ||
                extension.equals(Utils.gif) ||
                extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg) ||
                extension.equals(Utils.png)) {
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
    }
 
    //The description of this filter
    public String getDescription() {
        return "Just Images";
    }
}

public class Utils {
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";
 
    /*
     * Get the extension of a file.
     */
    public String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

 public void transform(String originalFile, String thumbnailFile, int thumbWidth, int thumbHeight, int quality) throws Exception 
 {
   Image image = javax.imageio.ImageIO.read(new File(originalFile));
     
     double thumbRatio = (double)thumbWidth / (double)thumbHeight;
     int imageWidth    = image.getWidth(null);
     int imageHeight   = image.getHeight(null);
     double imageRatio = (double)imageWidth / (double)imageHeight;
     if (thumbRatio < imageRatio) 
     {
       thumbHeight = (int)(thumbWidth / imageRatio);
     } 
     else 
     {
         thumbWidth = (int)(thumbHeight * imageRatio);
     }
     
   if(imageWidth < thumbWidth && imageHeight < thumbHeight)
   {
     thumbWidth = imageWidth;
     thumbHeight = imageHeight;
   }
   else if(imageWidth < thumbWidth)
     thumbWidth = imageWidth;
   else if(imageHeight < thumbHeight)
     thumbHeight = imageHeight;

     BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
     Graphics2D graphics2D = thumbImage.createGraphics();
     graphics2D.setBackground(Color.WHITE);
     graphics2D.setPaint(Color.WHITE); 
     graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
     graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
     graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
     
   javax.imageio.ImageIO.write(thumbImage, "JPG", new File(thumbnailFile));
 }

@Override
public void propertyChange(PropertyChangeEvent evt) {
	if ("progress" == evt.getPropertyName()) {
        int progress = (Integer) evt.getNewValue();
        progressBar.setValue(progress);        
    } 
	
}

	
}

class Task extends SwingWorker<Void, Void> {
    /*
     * Main task. Executed in background thread.
     */
	Add_Photo_Options popUpScreen;
	boolean userLoaded = false;
	boolean adminLoaded = false;
	
	@Override
    public Void doInBackground() {
		popUpScreen.progressBar.setVisible(true);
		popUpScreen.repaint();
		
		int progress = 0;
		Photo photo = null;
		if((popUpScreen.photosModel!=null&&popUpScreen.photosModel.size()>0)){
			int numOfPics = popUpScreen.files.size();
			for(File file : popUpScreen.files){
			    photo = popUpScreen.album.getPhotoInAlbum(file.getName(), popUpScreen.control.getUser());
				if(photo==null){
					try {
						addPhoto(file);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else{
					final JFrame error = new JFrame("Important Message!");
		        	JOptionPane.showMessageDialog(error, "Picture "+file.getName()+" already in album!");
				}
				
				double div=0;				
				div = 100/(double)numOfPics;
				progress += Math.ceil(div);
		        setProgress(progress);
		        
			}			
						
		}
		else{
			final JFrame error = new JFrame("Important Message!");
        	JOptionPane.showMessageDialog(error, "Please input name and caption for your Photo.");
		}
		
		return null;
    }
	
	protected void addPhoto(File file) throws Exception{
		
		popUpScreen.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if(popUpScreen.c!=null){
				if (popUpScreen.rVal == JFileChooser.APPROVE_OPTION) {
		        
		    	
					popUpScreen.filename=file.getName();
					popUpScreen.dir=file.toString();
			        
			        String fileSplit = popUpScreen.filename;
					String[] fileInfo = new String[2];
					fileInfo=fileSplit.split("\\.");
					String fileName = fileInfo[0];
					String fileExtension = fileInfo[1];
					
					System.out.println("File name" + fileName);
					System.out.println("File Extension" + fileExtension);
     
					popUpScreen.transform(popUpScreen.dir,"pictures"+File.separator+fileName+"."+fileExtension,505,379,10); 
			        
					popUpScreen.photosScreen.addPhoto(popUpScreen.filename,"Click Edit To Add Caption");
			        
			        
				}
		      }
		      
			
			
	}
	
	public void setLoginScreen(Add_Photo_Options ps){
		this.popUpScreen=ps;
		
	}

    
    @Override
    public void done() {
    	popUpScreen.progressBar.setVisible(false); 	
    	popUpScreen.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));		
        Toolkit.getDefaultToolkit().beep();
        popUpScreen.setVisible(false);
        try {
			popUpScreen.photosScreen.reDrawPhotos();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }

	
}
