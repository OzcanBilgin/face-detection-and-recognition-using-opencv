/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;
import userrecognition.*;
import gui.FaceRecMain;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.opencv.core.Core;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Giris_formController implements Initializable {
     static userrecognition.FXController fxmlCont;
    @FXML
    private Label sınıf_mevcut;
    @FXML
    private Button yoklama;
    @FXML
    private Button guncelle;
    @FXML
    private Button ekle;
    @FXML
    private Button sil;
    @FXML
    private Label unvan;
    @FXML
    private ComboBox<String> cmb_ders;
    @FXML
    private Label kullanici_lbl;
    @FXML
    private Label ders_lbl;
    @FXML
    private Label sinif_lbl;
    @FXML
    private Label kullanici;
    @FXML
    private Label unvan_lbl;
    @FXML
    private Button btn_new;
    @FXML
    private Button btn_ogr_guncelle;
    @FXML
    private Label bolum_lbl;
    @FXML
    private Label bolum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> dersGetir = new ArrayList<String>();

//        System.out.println(Singleton.getInstance().getTxtField1().getText());
//        System.out.println(Singleton.getInstance().getTxtField2().getText());
        baglanti bg = new baglanti();
        bg.bolumGet(Singleton.getInstance().getTxtField1().getText(), Singleton.getInstance().getTxtField2().getText());
        bg.veriGetirArayuz(Singleton.getInstance().getTxtField1().getText(), Singleton.getInstance().getTxtField2().getText());
        kullanici.setText(rootKisi.getAd() + " " + rootKisi.getSoyad());
        unvan.setText(rootKisi.getUnvan());
        dersGetir = (ArrayList<String>) bg.dersGetir(Singleton.getInstance().getTxtField1().getText(), Singleton.getInstance().getTxtField2().getText());
        cmb_ders.getItems().addAll(dersGetir);
        bolum.setText(rootKisi.getBolumAciklama());

        cmb_ders.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ArrayList<String> ogrno = new ArrayList<String>();
                int mevcut = bg.mevcut(newValue);
                String mevcut2 = Integer.toString(mevcut);
                sınıf_mevcut.setText(mevcut2);
                ogrno = (ArrayList<String>) bg.ogrNo(newValue);
                rootKisi.setOgrno(ogrno);
                for (int i = 0; i < ogrno.size(); i++) {
                    System.out.println(ogrno.get(i));
                }
            }
        });
    }

    @FXML
    private void yoklama_baslat(ActionEvent event) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
        FaceRecMain faceRecMain = new FaceRecMain();
        faceRecMain.run();
   try{
              
              Node  source = (Node)  event.getSource();
                Stage stage = (Stage)source.getScene().getWindow();
                stage.close();
                
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/userrecognition/JFXoverlay.fxml"));
                 Parent root = fxml.load();
                 fxmlCont = (userrecognition.FXController)fxml.getController();
                 fxmlCont.init();
                 
            stage.setScene(new Scene(root));
            stage.show();
            }
            catch(Exception e){
            System.out.println(e.toString());
        }
        /*
         try{
              System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
              Node  source = (Node)  event.getSource();
                Stage stage = (Stage)source.getScene().getWindow();
                stage.close();
                
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/userrecognition/JFXoverlay.fxml"));
                 Parent root = fxml.load();
                 fxmlCont = (userrecognition.FXController)fxml.getController();
                 fxmlCont.init();
                 
            stage.setScene(new Scene(root));
            stage.show();
            }
            catch(Exception e){
            System.out.println(e.toString());
        }*/
       
        /*
        try{
               
              Node  source = (Node)  event.getSource();
                Stage stage = (Stage)source.getScene().getWindow();
                stage.close();
                
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/userrecognition/JFXoverlay.fxml"));
                 Parent root = fxml.load();
                 fxmlCont = (userrecognition.FXController)fxml.getController();
                 fxmlCont.init();
                 
            stage.setScene(new Scene(root));
            stage.show();
            }
            catch(Exception e){
            System.out.println(e.toString());
        }
        
        userrecognition.Main main=new userrecognition.Main();
        Stage primaryStage=new Stage();
        main.start(primaryStage);
        userrecognition.FXController uf=new userrecognition.FXController();
        uf.init();
        
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/userrecognition/JFXoverlay.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        userrecognition.FXController uf = new userrecognition.FXController();

        
        
		try
		{
                        Stage primaryStage=new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/userrecognition/JFXoverlay.fxml"));
			BorderPane root = (BorderPane) loader.load();
			// set a whitesmoke background
			root.setStyle("-fx-background-color: whitesmoke;");
			// create and style a scene
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// create the stage with the given title and the previously created
			// scene
			primaryStage.setTitle("Face Detection and Tracking");
			primaryStage.setScene(scene);
			// show the GUI
			primaryStage.show();
			
			// init the controller
			FXController controller = loader.getController();
			controller.init();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
          */     
               
    }

    @FXML
    private void bilgi_guncelle(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("bilgi_guncelle_form.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    private void ogrenci_ekle(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("ogrenci_kayit.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    private void ogrenci_sil(ActionEvent event) {
    }

    @FXML
    private void new_account(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("kullanici_kayit.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
           

        } catch (Exception e) {
            System.out.println(e.toString());
        }
     
    }

    @FXML
    private void ogrenci_guncelle(ActionEvent event) {
    }
 


}
