import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * MemeMagic Graphical User Interface 
 * 
 * This class contains the graphical user interface for the Meme Magic Software
 * 
 * You will need to implement certain portions of this class, marked with comments starting with "TODO" to connect 
 * it with your existing code. 
 * 
 * This class provides an example layout for the GUI. You are encouraged to be creative in your design. 
 * More information about Swing is online at: 
 * https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html.
 */
public class MemeMagic extends JFrame {

    /**
     * Serialization string required by extending JFrame
     */
    private static final long serialVersionUID = 1L;
    
    private User user;
    private GraphicalMeme currentMeme;
    
    private String backgroundImageFilename;

    private BorderLayout panelLayout;
    private JLabel backgroundImageFileNameLabel;
    private JLabel imageDisplayLabel;
    private JPanel controlPanel;
    private JPanel memeViewPanel;
    private JPanel panelPane;
    private JTextField backgroundImageTitleField = new JTextField(20);
    private JTextField backgroundImageDescriptionField = new JTextField(20);
    private JTextField memeCaptionTextField = new JTextField(20);
    String[] align = {"top","middle", "bottom"};
    private JComboBox verticalAlignBox = new JComboBox(align);
    
    public MemeMagic() {
        this.user = new User();
    }
    
    public MemeMagic(User user) {
        this.user = user;
    }


    /**
     * Main method.  This method initializes a PhotoViewer, loads images into a PhotographContainer, then
     * initializes the Graphical User Interface.
     * 
     * @param args  Optional command-line arguments
     */
    public static void main(String[] args) {
        
        // Create a User object for this instance of Meme Magic
        User user = new User();

        // Instantiate the PhotoViewer Class
        MemeMagic myViewer = new MemeMagic(user);
        
        // Invoke and start the Graphical User Interface
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }

    /**
     * Initialize all the GUI components.  This method will be called by
     * SwingUtilities when the application is started.
     */
    private void initialize() {

        // Tell Java to exit the program when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Tell Java to title the window to Meme Magic
        this.setTitle("Meme Magic");

        // We will use border layout on the main panel, since it is much easier for organizing panels.
        panelLayout = new BorderLayout();
        panelPane = new JPanel(panelLayout);

        // Create a label to display the full image.
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setPreferredSize(new Dimension(550, 550));

        // Create a panel on which to display the full image
        memeViewPanel = new JPanel(new BorderLayout());
       
        memeViewPanel.setPreferredSize(new Dimension(550, 550));
        memeViewPanel.add(imageDisplayLabel, BorderLayout.CENTER);
        
        //meme subpanel
        JPanel memeSubPanel = new JPanel(new GridLayout(2,1));
        memeSubPanel.setBorder(BorderFactory.createTitledBorder("Meme"));
        JPanel memeFirstPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel memeSecondPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        
        
        //meme caption label
        JLabel memeCaptionLabel = new JLabel("Caption: ");
        memeFirstPanel.add(memeCaptionLabel);
        
        //meme caption text field
        
        memeFirstPanel.add(memeCaptionTextField);
        
        //meme vertical align label
        JLabel memeAlignLabel = new JLabel("Vertical Align: ");
        memeSecondPanel.add(memeAlignLabel);
        
        //vertical align combo box

        memeSecondPanel.add(verticalAlignBox);
        
        memeSubPanel.add(memeFirstPanel);
        memeSubPanel.add(memeSecondPanel);
        

        // Create a panel on which to display the controls for building a Meme
        controlPanel = new JPanel(new BorderLayout());
        
        // Create a panel that holds BackgroundImage information and give it a title
        JPanel backgroundImagePanel = new JPanel(new GridLayout(3,1));
        backgroundImagePanel.setBorder(BorderFactory.createTitledBorder("Background Image"));
        

        // Create a panel that provides input for the BackgroundImage fileName
        JPanel backgroundImageFilePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel backgroundImageTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel backgroundImageDescriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        
    
        // file name Label
        JLabel backgroundImageFileLabel = new JLabel("Filename: ");
        backgroundImageFileLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageFilePanel.add(backgroundImageFileLabel);
        
        // browse Button
        JButton backgroundImageButton = new JButton("Browse");
        backgroundImageFilePanel.add(backgroundImageButton);
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        
        ActionListener browseButton = new OpenButtonListener();
        backgroundImageButton.addActionListener(browseButton); 
        	
        	
        
        
        // Label that will contain the filename of the image
        backgroundImageFileNameLabel = new JLabel("<choose>");
        backgroundImageFilePanel.add(backgroundImageFileNameLabel);
        
        //title label
        JLabel backgroundImageTitleLabel = new JLabel("Title: ");
        backgroundImageTitleLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageTitlePanel.add(backgroundImageTitleLabel);
        
        
      //title textfield
        
        backgroundImageTitlePanel.add(backgroundImageTitleField);
        
        //description label
        JLabel backgroundImageDescriptionLabel = new JLabel("Description: ");
        backgroundImageDescriptionLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageDescriptionPanel.add(backgroundImageDescriptionLabel);
        
        //description textfield
      
        backgroundImageDescriptionPanel.add(backgroundImageDescriptionField);
        
        
        // Add the panel about the BackgroundImage fileName to the BackgroundImage information panel
        
        backgroundImagePanel.add(backgroundImageFilePanel);
        backgroundImagePanel.add(backgroundImageTitlePanel);
        backgroundImagePanel.add(backgroundImageDescriptionPanel);
        backgroundImagePanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        
        controlPanel.add(memeSubPanel, BorderLayout.LINE_START);

        
        
        
        
        
        // Add the BackgroundImage information panel to the control panel
        controlPanel.add(backgroundImagePanel, BorderLayout.NORTH);
        
        // Add all the panels to the main display based on BorderLayout
        controlPanel.setPreferredSize(new Dimension(500,570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);

        // Add the panelPane to the contentPane of the Frame (Window)
        this.getContentPane().add(panelPane);

        // Set the preferred size and show the main application window
        this.setPreferredSize(new Dimension(1150, 570));
        this.pack();
        this.setVisible(true);
        
        //generate button
        JPanel gAndSButtonPanel = new JPanel();
        JButton generateButton = new JButton("Generate");
        generateButton.setPreferredSize(new Dimension(100,20));
        gAndSButtonPanel.add(generateButton);
        ActionListener GButton = new generateButtonListener();
        generateButton.addActionListener(GButton);
        
        //save button
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(85,20));
        gAndSButtonPanel.add(saveButton);
        ActionListener SButton = new SaveButtonListener();
        saveButton.addActionListener(SButton);
        
        controlPanel.add(gAndSButtonPanel);
    }
    
    
    /**
     * ActionListener for the open button.  When the button is pressed, this ActionListener
     * opens a FileChooser, asks the user to choose a JPG image file, then
     * sets the field backgroundImageFilename in the main class.
     */
    private class OpenButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose a JPG image file, then
         * sets the field backgroundImageFilename in the main class.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Choose a Background Image");
            chooser2.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
            int returnVal = chooser2.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                backgroundImageFilename = chooser2.getSelectedFile().getAbsolutePath();
                backgroundImageFileNameLabel.setText(backgroundImageFilename);
            }

        }
    }
    
    /**
     * ActionListener for the save button.  When the button is pressed, this ActionListener
     * opens a save FileChooser, asks the user to choose a location and filename, then
     * writes the graphical meme data to a PNG image file.
     */
    private class SaveButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose
         * a location and filename, then writes the graphical meme data to a PNG file.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Save Meme");
            chooser2.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int returnVal = chooser2.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String destinationFile = chooser2.getSelectedFile().getAbsolutePath();
                if (!destinationFile.contains(".png"))
                    destinationFile += ".png";
                
 
                 try {
					ImageIO.write(currentMeme.compileMeme(), "png", new File(destinationFile));
				} catch (IOException e) {
					System.err.print("There was no file chosen for the meme");
				}
                 catch(NullPointerException e) {
                	 System.err.print("The file chosen was the wrong file format (not png)");
                 }
                 catch (Exception e) {
					System.err.print("There was an error");
				}

            }

        }
    }
    
    private class generateButtonListener implements ActionListener{
    	@Override
    	public void actionPerformed(ActionEvent click) {
    		String title = backgroundImageTitleField.getText();
    		String description = backgroundImageDescriptionField.getText();
    		String caption = memeCaptionTextField.getText();
    		String alignment = (String)verticalAlignBox.getSelectedItem();
    		BackgroundImage backgroundImage = new BackgroundImage(backgroundImageFilename, title, description);
    		currentMeme = new GraphicalMeme(backgroundImage, caption,user);
    		currentMeme.setCaptionVerticalAlign(alignment);
    		BufferedImage meme = null;
			try {
				meme = currentMeme.compileMeme();
			} catch(NullPointerException e) {
				System.err.println("Caught Null pointer exception");
			}
			catch (IOException e) {
				System.err.println("Caught IOExcpetion");
			}catch (Exception e) {
				System.err.println("Caught exception" );
			}
			
    		imageDisplayLabel.setIcon(new ImageIcon(meme));
    		memeViewPanel.add(imageDisplayLabel);
    		memeViewPanel.repaint();
    	}
    }
}