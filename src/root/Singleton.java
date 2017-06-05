/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author user
 */
public class Singleton {

    
    private static Singleton instance = new Singleton();
        public static Singleton getInstance(){
            return instance;
        }

        private TextField txtField1;
        private TextField txtField2;
        private Label lbl_1;
        private Label lbl_2;
        
        
        public Label getLbl_1(){
            return lbl_1;
        }
        public void setLbl_1(Label lbl_1){
            this.lbl_1 = lbl_1;
        }
        public Label getLbl_2(){
            return lbl_2;
        }
        public void setLbl_2(Label lbl_2){
            this.lbl_2 = lbl_2;
        }
        

        public TextField getTxtField2() {
            return txtField2;
        }

        public void setTxtField2(TextField txtField2) {
           this.txtField2 = txtField2;
        }

        public TextField getTxtField1() {
           return txtField1;
        }

       public void setTxtField1(TextField txtField1) {
           this.txtField1 = txtField1;
       }

}
