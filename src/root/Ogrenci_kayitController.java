/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mur_b
 */
public class Ogrenci_kayitController implements Initializable {

    @FXML
    private TextField ogr_No;
    @FXML
    private TextField ogr_Soyad;
    @FXML
    private TextField ogr_Ad;
    @FXML
    private ComboBox<String> cinsiyet_combo;
    private Label resim1;
    @FXML
    private Button btn_ekle;
    @FXML
    private Button btn_resimCek;
    @FXML
    private Button btn_ana;
    @FXML
    private Label lbl_d;
    @FXML
    private ComboBox<String> cmb_ders;
    @FXML
    private Button btn_ders_ekle;
    private ArrayList<String> ders_secimleri = new ArrayList<String>();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ArrayList<String> asd = new ArrayList<String>();
                 ArrayList<String> dersGetir = new ArrayList<String>();

        cinsiyet_combo.getItems().addAll("Kadın","Erkek");
        cinsiyet_combo.setPromptText("Cinsiyet Seçin..");
        cinsiyet_combo.setEditable(true);
        cinsiyet_combo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                rootKisi.setCinsiyet(newValue);
            }
        });
        baglanti bg = new baglanti();
        
        dersGetir = (ArrayList<String>) bg.dersGetir(Singleton.getInstance().getTxtField1().getText(), Singleton.getInstance().getTxtField2().getText());
        cmb_ders.setPromptText("Ders Seçin..");
        cmb_ders.getItems().addAll(dersGetir);
        cmb_ders.setEditable(true);
        cmb_ders.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 rootKisi.setDersSecim(newValue);
            }
        });
        
        
       
    }    

    @FXML
    private void btn_resim_action(ActionEvent event) {
        rootKisi.setNosu(ogr_No.getText());
        try {
            
            JOptionPane.showMessageDialog(null,"Lütfen resimlerin ismini ÖğrenciNo_1.jpg ÖğrenciNo_2.jpg ÖğrenciNo_3.jpg şeklinde veriniz!!!","Bilgi",JOptionPane.INFORMATION_MESSAGE);
            Runtime.getRuntime().exec("C:\\Users\\user\\Documents\\NetBeansProjects\\root\\src\\root\\visual-control.exe", null, new File("C:\\Users\\user\\Documents\\NetBeansProjects\\root\\src\\root\\"));
        } catch (IOException ex) {
            Logger.getLogger(Ogrenci_kayitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void btn_ekle_action(ActionEvent event) {
        baglanti bg = new baglanti();
        int ogrno = Integer.parseInt(ogr_No.getText());
        if(bg.ogrenciEkle(ogrno, ogr_Ad.getText(), ogr_Soyad.getText(), rootKisi.getCinsiyet(), bg.bolumId(rootKisi.getBolumAciklama()), ders_secimleri)==true) {
        
            JOptionPane.showMessageDialog(null, "Öğrenci Başarıyla eklendi!","Başarı!",JOptionPane.INFORMATION_MESSAGE);
//            String img1 = "images/"+ogr_No.getText()+"_1.jpg";
//            String img2 = "images/"+ogr_No.getText()+"_2.jpg";
//            String img3 = "images/"+ogr_No.getText()+"_3.jpg";
//            int a = Integer.parseInt(ogr_No.getText());
//            String aa=bg.ogrGetir(a);
//            System.out.println(aa);
//            Image imaged1 = new Image(getClass().getResource("images/1211012040.jpg").toExternalForm());
//            Image imaged2 = new Image(getClass().getResource("images/1211012040.jpg").toExternalForm());
//            Image imaged3 = new Image(getClass().getResource("images/1211012040.jpg").toExternalForm());
//            resim_img.setImage(imaged1);
//            resim_img_2.setImage(imaged2);
//            resim_img_3.setImage(imaged3);
            
        }
        else{
            JOptionPane.showMessageDialog(null,"Hata","Ekleme Başarısız!", JOptionPane.ERROR_MESSAGE);
        }
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
    private void ders_ekle(ActionEvent event) {
        if(rootKisi.getDersSecim()!=null){
            ders_secimleri.add(rootKisi.getDersSecim());
        }
    }
    
}
