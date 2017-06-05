package gui;

import root.rootKisi;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import utils.Utils;
import javafaces.FaceRec;
import javafaces.FaceRecError;
import javafaces.MatchResult;
import gui.FaceRecView;
import java.util.ArrayList;

public class FaceRecMain{
	
            public void run() {
            	FaceRecView view=new FaceRecView("FACE RECOGNITION");
            	FaceRec model=new FaceRec();
            	new SimpleController(view,model);
            }
      
	}
	


class SimpleController{
    ArrayList<String> ogrenciler=new ArrayList<String>();
    String ogrencii="";
    String ogrencii2;
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
	private void handleUserInputs(File selectedFile,File selectedFolder,String eigenFaces,String threshold,String no){
		view.displayMatchingImage(null);
		try{
			if (sValidator.validateAllInput(selectedFile,selectedFolder,eigenFaces, threshold)){
				view.displaySelectedImage(selectedFile);
				MatchResult r = model.processSelections(selectedFile.getPath(),selectedFolder.getPath(),eigenFaces, threshold);
				if (r.getMatchSuccess()){
                                       
                                        ogrencii=ogrencii+"Burada olan öğrenciler = "+no+" Hata payı = "+r.getMatchDistance()+"\n";
                                        
					view.displayMatchingImage(new File(r.getMatchFileName()));
					view.displayMessage(selectedFile.getPath() + " matches " + r.getMatchFileName()+" at distance="+r.getMatchDistance());
                                        if(ogrencii != ""){
                                        view.displayMessage(ogrencii);
                                        }
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
		public void actionPerformed(ActionEvent evt) {
			view.clearImageDisplay();
			view.displayMessage("processing images...");
			String intEntry = view.getNumFacesEntry();
                        System.out.println(intEntry);
			String decEntry = view.getThresholdEntry();
                        System.out.println(decEntry);
			File selectedFile = view.getSelectedFile();
                        // System.out.println(selectedFile);
                        //String yol="C:\\Users\\user\\Documents\\NetBeansProjects\\root\\src\\1.jpg";
			//File file_yol = new File(yol);
                        File selectedFolder = view.getSelectedFolder();
                        // System.out.println(selectedFolder);
                         //String klosoryol="C:\\Users\\user\\Documents\\NetBeansProjects\\root\\src\\root\\images";
			//File file_klasor = new File(klosoryol);
                        ArrayList<String> ogrno=new ArrayList<String>();
                        ogrno=rootKisi.getOgrno();
                        for(int i=0; i<ogrno.size(); i++){
                             String yol1="C:\\Users\\user\\Documents\\NetBeansProjects\\root\\src\\root\\images\\"+ogrno.get(i)+".jpg";
                             File file_yol1 = new File(yol1);
                              String klosoryol2="C:\\Users\\user\\Documents\\NetBeansProjects\\root\\resources\\trainingset\\combined";
                                File file_klasor2 = new File(klosoryol2);
                           handleUserInputs(file_yol1,file_klasor2,intEntry,decEntry,ogrno.get(i));
                       }
                        
                        // handleUserInputs(file_yol,file_klasor,intEntry,decEntry);
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
