package gui;
import root.rootKisi;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FaceRecView extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField numEigenFaces;
	private JTextField threshold;
	private JTextArea result;
	private ImageDisplayPanel selectedImagePanel;
	private ImageDisplayPanel matchingImagePanel;
	private JFileChooser filechooser;
	private JFileChooser dirchooser;
	private JButton fileChooseButton;
	private JButton dirChooseButton;
	private JButton ok;
	private JButton quit;
	private JTextArea selectedFileName;
	private JTextArea selectedDirName;
	private JLabel numEigenFacesLabel;
	private JLabel thresholdLabel;
	private static Insets insets=new Insets(0,0,0,0);
	
	public FaceRecView(String name) {
        super(name);
        setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("bg=" + this.getBackground());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setSize(width, height);
        this.addWidgets();
        this.setVisible(true);
    }
	private void addComponent(Component component,int gridx, int gridy, int gridwidth, int gridheight, int anchor,int fill){
			GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
			this.add(component, gbc);
		}
	private void addWidgets() {
		numEigenFaces = new JTextField(10);
		threshold = new JTextField(10);
		result = new JTextArea(10,15);
		
		selectedImagePanel = new ImageDisplayPanel();
		matchingImagePanel = new ImageDisplayPanel();
		
		fileChooseButton = new JButton("Kayıtlı Resimler");
		dirChooseButton = new JButton("Bulunacak öğrenci resimleri");
		fileChooseButton.addActionListener(this);
		dirChooseButton.addActionListener(this);
		
		numEigenFacesLabel = new JLabel("Özyüzlerin sayısı:");
		thresholdLabel    = new JLabel("eşik değeri:");
		
		selectedFileName = new JTextArea();
		selectedDirName = new JTextArea();
		ok = new JButton("YOKLAMAYI BASLAT");
		ok.setBackground(Color.green);
		quit=new JButton("QUIT");
		quit.setBackground(Color.red);
		quit.addActionListener(this);
		
		
		//addComponent(fileChooseButton, 0, 0, 2, 1, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		//addComponent(selectedFileName, 0, 1, 2, 1, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		//addComponent(dirChooseButton, 0, 2, 2, 1, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		//addComponent(selectedDirName, 0, 3, 2, 1, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
               addComponent(numEigenFacesLabel, 0, 0, 1, 1, GridBagConstraints.EAST,GridBagConstraints.NONE);
		addComponent(numEigenFaces, 1, 0, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		addComponent(thresholdLabel, 0, 1, 1, 1, GridBagConstraints.EAST,GridBagConstraints.NONE);
		addComponent(threshold, 1, 1, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		addComponent(selectedImagePanel, 0, 2, 2, 2, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		addComponent(matchingImagePanel, 1, 2, 2, 2, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		addComponent(result, 0, 4, 2, 1, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		addComponent(ok, 0, 5, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.NONE);
		addComponent(quit, 1, 5, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.NONE);
    }
	public void addOKButtonListener(ActionListener okl){
		ok.addActionListener(okl);
		System.out.println(okl + "added to button:"+ok);
	}
	public File getSelectedFile(){
		File f = null;
		if (filechooser!=null){
			f = filechooser.getSelectedFile();
		}
		return f;
	}
	public File getSelectedFolder(){
		File f = null;
		if (dirchooser != null){
			f = dirchooser.getSelectedFile();
		}
		return f;
	}
	public String getNumFacesEntry(){
		return numEigenFaces.getText();
	}
	public String getThresholdEntry(){
		return threshold.getText();
	}
	public void displayMessage(String msg){
		result.setText(msg);
	}
	public void displaySelectedImage(File f){
		selectedImagePanel.setImage(f);
	}
	public void displayMatchingImage(File f){
		matchingImagePanel.setImage(f);
	}
	public void clearImageDisplay(){
		selectedImagePanel.setImage(null);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		int returnVal = 99;
                 ArrayList<String> ogrnoliste=new ArrayList<String>();
                        rootKisi ogrno=new rootKisi();
                       ogrnoliste= ogrno.getOgrno();
                       for(int i=0; i<ogrnoliste.size(); i++){
                           System.out.println(ogrnoliste.get(i));
                       }
		if(evt.getSource() == fileChooseButton){
			selectedFileName.setText("");
			filechooser=new JFileChooser();
                        //String filechoose="C:\\Users\\user\\Documents\\NetBeansProjects\\root\\src\\root\\image\\3.jpg";
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Images", "jpg","JPG","GIF", "gif","JPEG","png","PNG");
			filechooser.setFileFilter(filter);
			ThumbNailView thumbsView = new ThumbNailView();
			filechooser.setFileView(thumbsView);
                       
                        File filee = filechooser.getSelectedFile();
                        
				//selectedFileName.setText(filee.getPath());
			filechooser.setAccessory(new ImagePreview(filechooser));
			returnVal = filechooser.showDialog(FaceRecView.this,"select an image");
                       
                        
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File file = filechooser.getSelectedFile();
				selectedFileName.setText(file.getPath());
			}
		}else if(evt.getSource() == dirChooseButton){
			selectedDirName.setText("");
			dirchooser = new JFileChooser();
			dirchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			returnVal = dirchooser.showDialog(FaceRecView.this,"select a directory");
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File file = dirchooser.getSelectedFile();
				selectedDirName.setText(file.getPath());
			}
		}else if(evt.getSource() == quit){
			this.dispose();
		}
	}

    private File File(String cabcfoldertextfiletxt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	

}

