/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafaces;

import gui.*;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FaceRecMainController implements Initializable {

    @FXML
    private Button dosya_sec;
    @FXML
    private Button dosya_sec_2;
    @FXML
    private TextField text_1;
    @FXML
    private TextField text_2;
    @FXML
    private Button Ok;
    @FXML
    private Button cikis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                FaceRecView view=new FaceRecView("FACE RECOGNITION");
                FaceRec model=new FaceRec();
            	new SimpleController(view,model);
    }    

    @FXML
    private void dosya_sec_action(ActionEvent event) {
    }

    @FXML
    private void dosya_sec_action_2(ActionEvent event) {
    }

    @FXML
    private void ok_action(ActionEvent event) {
    }

    @FXML
    private void cikis_action(ActionEvent event) {
    }
    class SimpleController{
	private FaceRecView view;
	private FaceRec model;
	private SimpleValidator sValidator;
	private StringBuilder errorMsg;
	public SimpleController(FaceRecView v,FaceRec m){
		view = v;
		view.addOKButtonListener(new SimpleButtonListener());
		model = m;
		sValidator = new SimpleValidator();
	}
	private void handleUserInputs(File selectedFile,File selectedFolder,String eigenFaces,String threshold){
		view.displayMatchingImage(null);
		try{
			if (sValidator.validateAllInput(selectedFile,selectedFolder,eigenFaces, threshold)){
				view.displaySelectedImage(selectedFile);
				MatchResult r = model.processSelections(selectedFile.getPath(),selectedFolder.getPath(),eigenFaces, threshold);
				if (r.getMatchSuccess()){
					view.displayMatchingImage(new File(r.getMatchFileName()));
					view.displayMessage(selectedFile.getPath() + " matches " + r.getMatchFileName()+" at distance="+r.getMatchDistance());
				}else{
					view.displayMessage("match failed:"+r.getMatchMessage());
				}
			}else{
				view.displayMessage(errorMsg.toString());
			}
		}catch (FaceRecError e) {
			view.displayMessage(e.getMessage());
		}
	}
	class SimpleButtonListener implements ActionListener{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			view.clearImageDisplay();
			view.displayMessage("processing images...");
			String intEntry = view.getNumFacesEntry();
			String decEntry = view.getThresholdEntry();
			File selectedFile = view.getSelectedFile();
			File selectedFolder = view.getSelectedFolder();
			handleUserInputs(selectedFile,selectedFolder,intEntry,decEntry);
		}
	}
	
	class SimpleValidator{
		public boolean validateAllInput(File file,File dir,String nField,String dField) throws FaceRecError{
			boolean allvalidated = false;
			errorMsg = new StringBuilder();
			boolean textFieldEntriesValid = validateTextFields(nField,dField);
			boolean selectedFileValid;
			try {
				selectedFileValid = validateFileSelection(file);
			} catch (IOException e) {
				throw new FaceRecError(e.getMessage());
			}
			boolean selectedDirectoryValid = validateFolderSelection(dir);
			allvalidated = textFieldEntriesValid && selectedFileValid && selectedDirectoryValid   ;
			return allvalidated;
		}
		private boolean validateTextFields(String nField,String dField){
			boolean isValid = false;
			try{
				Integer.parseInt(nField);
				Double.parseDouble(dField);
				isValid = true;
			}catch(NumberFormatException e){
				errorMsg.append("enter correct values in textfields");
			}
			return isValid;
		}
		private boolean validateFolderSelection(File dir){
			boolean isValidFolder = false;
			if ( (dir!=null) && (dir.isDirectory()) ){
				isValidFolder = true;
			}else{
				errorMsg.append("\nselect a directory");
				isValidFolder = false;
			}
			return isValidFolder;
		}
		private boolean validateFileSelection(File file) throws IOException{
			boolean isValidFile = false;
			if ( (file!=null) && ( file.isFile() ) && ( Utils.isImageFile(file.getPath() ))  ){
				isValidFile = true;
			}else{
				errorMsg.append("\nselect an image file");
				isValidFile = false;
			}
			return isValidFile;
		}
	}
}

}
