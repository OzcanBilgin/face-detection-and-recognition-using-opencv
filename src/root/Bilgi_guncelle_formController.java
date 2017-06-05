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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mur_b
 */
public class Bilgi_guncelle_formController implements Initializable {

    @FXML
    private Label lbl_bilgi_0;
    @FXML
    private Label lbl_bilgi_1;
    @FXML
    private Label lbl_bilgi_3;
    @FXML
    private Label lbl_bilgi_2;
    @FXML
    private Label lbl_bilgi_5;
    @FXML
    private Label lbl_bilgi_4;
    @FXML
    private TextField txt_bilgi_ad;
    @FXML
    private TextField txt_bilgi_soyad;
    @FXML
    private TextField txt_bilgi_unvan;
    @FXML
    private TextField txt_bilgi_ders;
    @FXML
    private ComboBox<String> cmb_bilgi_id;
    @FXML
    private Button btn_anasayfa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> idGetir = new ArrayList<String>();
        baglanti bg = new baglanti();
        bg.veriGetirArayuz(Singleton.getInstance().getTxtField1().getText(), Singleton.getInstance().getTxtField2().getText());
        txt_bilgi_ad.setText(rootKisi.getAd());
        txt_bilgi_soyad.setText(rootKisi.getSoyad());
        txt_bilgi_unvan.setText(rootKisi.getUnvan());
        idGetir = (ArrayList<String>) bg.idGetir(Singleton.getInstance().getTxtField1().getText(), Singleton.getInstance().getTxtField2().getText());
        cmb_bilgi_id.getItems().addAll(idGetir);
        cmb_bilgi_id.setPromptText("DersID");
        cmb_bilgi_id.setEditable(true);
        cmb_bilgi_id.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                //System.out.println(ov);System.out.println(t);
                //System.out.println(t1);
                int donus2 = Integer.parseInt(t1);
                txt_bilgi_ders.setText(bg.dersAdiGetir(donus2));
            }
        });
       
        
//        
        
   }

    @FXML
    private void guncelle(ActionEvent event) {
        baglanti bg = new baglanti();
        int dersID = Integer.parseInt(cmb_bilgi_id.getValue());
         if(bg.bilgiGuncelle(Singleton.getInstance().getTxtField1().getText(), Singleton.getInstance().getTxtField2().getText(),
                    txt_bilgi_ad.getText(), txt_bilgi_soyad.getText(), txt_bilgi_unvan.getText(),txt_bilgi_ders.getText(),dersID)==true){
             JOptionPane.showMessageDialog(null,"Güncelleme Başarılı!","Bilgi",JOptionPane.INFORMATION_MESSAGE);
         }
         else{
             JOptionPane.showMessageDialog(null,"Güncelleme Başarısız!","Bilgi",JOptionPane.ERROR_MESSAGE);
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
    
}
