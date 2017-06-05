/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mur_b
 */
public class Kullanici_kayitController implements Initializable {

    @FXML
    private Button btn_anasayfa;
    @FXML
    private Label lbl_1_baslik;
    @FXML
    private Label lbl_6_kul;
    @FXML
    private Label lbl_5_kul;
    @FXML
    private Label lbl_4_kul;
    @FXML
    private Label lbl_3_kul;
    @FXML
    private Label lbl_2_kul;
    @FXML
    private TextField txt_ad;
    @FXML
    private TextField txt_unvan;
    @FXML
    private TextField txt_kullaniciAdi;
    @FXML
    private TextField txt_soyad;
    @FXML
    private Button btn_devam;
    @FXML
    private TextField txt_kullaniciSifre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void git_anasayfa(ActionEvent event) {
        try{
                Node  source = (Node)  event.getSource();
                Stage stage = (Stage)source.getScene().getWindow();
                stage.close();
              
          
            Parent root = FXMLLoader.load(getClass().getResource("giris_form.fxml"));
            stage.setScene(new Scene(root));
            stage.show();       
            }
            catch(Exception e){
            System.out.println(e.toString());
        }
    }

    @FXML
    private void devam_et(ActionEvent event) {
        if(txt_ad.getText().trim().equals("")||txt_soyad.getText().trim().equals("")||txt_kullaniciAdi.getText().trim().equals("")||txt_kullaniciSifre.getText().trim().equals("")||txt_unvan.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Alanlar boş bırakılamaz!","Uyarı!", JOptionPane.ERROR_MESSAGE);
        }
        else{
        rootKullanici.setAd(txt_ad.getText());
        rootKullanici.setSoyad(txt_soyad.getText());
        rootKullanici.setKullaniciAd(txt_kullaniciAdi.getText());
        rootKullanici.setKullaniciSifre(txt_kullaniciSifre.getText());
        rootKullanici.setUnvan(txt_unvan.getText());
        
        try{
                Node  source = (Node)  event.getSource();
                Stage stage = (Stage)source.getScene().getWindow();
                stage.close();
              
          
            Parent root = FXMLLoader.load(getClass().getResource("kullanici_ders.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            
     
            
            
            }
            catch(Exception e){
            System.out.println(e.toString());
        }
        }
    }
    
}
