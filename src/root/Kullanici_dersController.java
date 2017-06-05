/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mur_b
 */
public class Kullanici_dersController implements Initializable {

    @FXML
    private Button btn_geri;
    @FXML
    private Label lbl_baslik;
    @FXML
    private ComboBox<String> cmb_bolum;
    @FXML
    private Label lbl_1_ders;
    @FXML
    private Button btn_sirala;
    @FXML
    private RadioButton radio_1;
    @FXML
    private RadioButton radio_5;
    @FXML
    private RadioButton radio_4;
    @FXML
    private RadioButton radio_3;
    @FXML
    private RadioButton radio_2;
    @FXML
    private RadioButton radio_6;
    @FXML
    private RadioButton radio_7;
    @FXML
    private RadioButton radio_8;
    @FXML
    private RadioButton radio_9;
    @FXML
    private RadioButton radio_10;
    @FXML
    private RadioButton radio_11;
    @FXML
    private RadioButton radio_12;
    @FXML
    private Label lbl_2_ders;
    @FXML
    private ComboBox<String> cmb_sinif;
    @FXML
    private Button btn_tamamla;
    @FXML
    private Button btn_ekle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> bolumGetir = new ArrayList<String>();
        radio_1.setVisible(false);
        radio_2.setVisible(false);
        radio_3.setVisible(false);
        radio_4.setVisible(false);
        radio_5.setVisible(false);
        radio_6.setVisible(false);
        radio_7.setVisible(false);
        radio_8.setVisible(false);
        radio_9.setVisible(false);
        radio_10.setVisible(false);
        radio_11.setVisible(false);
        radio_12.setVisible(false);
        baglanti bg = new baglanti();
        bolumGetir = (ArrayList<String>) bg.bolumGetir();
        cmb_bolum.getItems().addAll(bolumGetir);
        cmb_sinif.getItems().addAll("1.Sınıf","2.Sınıf","3.Sınıf","4.Sınıf");
        cmb_bolum.setPromptText("Bölüm Seçin..");
        cmb_sinif.setPromptText("Sınıf seçin..");
        cmb_bolum.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 rootKullanici.setBolum(newValue);
            }
        });
        cmb_sinif.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                rootKullanici.setSinif(newValue);
            }
        });
        
        
        
      
        
    }    

    @FXML
    private void geri_git(ActionEvent event) {
        try{
                Node  source = (Node)  event.getSource();
                Stage stage = (Stage)source.getScene().getWindow();
                stage.close();
              
          
            Parent root = FXMLLoader.load(getClass().getResource("kullanici_kayit.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            
     
            
            
            }
            catch(Exception e){
            System.out.println(e.toString());
        }
    }

    @FXML
    private void dersleri_sirala(ActionEvent event) {
        ArrayList<String> dersler = new ArrayList<String>();
        baglanti bg = new baglanti();
        dersler = (ArrayList<String>) bg.dersler(rootKullanici.getBolum(), rootKullanici.getSinif());
        
        
        
    }

    @FXML
    private void ekleme_bitir(ActionEvent event) {
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
    private void ders_ekle(ActionEvent event) {
    }
    
}
