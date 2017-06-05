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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField kullanici_ad;
    @FXML
    private PasswordField kullanici_pasword;
    @FXML
    private Button button;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Singleton.getInstance().setTxtField1(kullanici_ad);
        Singleton.getInstance().setTxtField2(kullanici_pasword);
        
    }    

    @FXML
    private void btn_giris(ActionEvent event) {
        if(kullanici_ad.getText().length()==0)  // Checking for empty field
            JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
        else if(kullanici_pasword.getText().length()==0)  // Checking for empty field
            JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
    else{
         String user = kullanici_ad.getText();   // Collecting the input
             String pass = kullanici_pasword.getText(); // Collecting the input
//             System.out.println(kullanici_ad.getText());
//             System.out.println(kullanici_pasword.getText());
        String pwd = null ;  // converting from array to string
        baglanti bag=new baglanti();
        if(bag.validate_login(user,pass))
           try{
                Node  source = (Node)  event.getSource();
                Stage stage = (Stage)source.getScene().getWindow();
                
                stage.close();
               
          
            Parent root = FXMLLoader.load(getClass().getResource("giris_form.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Yüz Tanıma");
            stage.show();
            rootKisi rk=new rootKisi();
            rk.kisiler(user, pass);
     
            bag.baglantiKapat();
            
            }
            catch(Exception e){
            System.out.println(e.toString());
        }      
       else
          JOptionPane.showMessageDialog(null, "basarısız");
   }        
    }

    
}
