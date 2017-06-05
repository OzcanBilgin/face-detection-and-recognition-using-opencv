/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.javafx.tk.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class baglanti {
    private Connection conn = null;
    private String url = "jdbc:mysql://localhost:3350/";
    private String dbName = "y_tdb_1";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "toor";
    private String properties= "?useUnicode=true&characterEncoding=utf8"; 
    private ResultSet rs;
    private Statement st;
    private PreparedStatement ps;
    public Statement baglantiAc() throws Exception{
        Class.forName(driver).newInstance();
        conn = (Connection) DriverManager.getConnection(url + dbName , userName, password);
        return (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    }
    public void baglantiKapat() throws Exception{
        conn.close();
    }
    public void veriGosterYonetici() throws Exception{
       st = baglantiAc();
       String sorgu = "SELECT * FROM yonetici";
       rs = st.executeQuery(sorgu);
       while(rs.next()){
             System.out.println(rs.getString("ID")+" "+rs.getString("Adi")+" "+rs.getString("Soyadi")+" "+rs.getString("Unvan")+" "+rs.getString("KullaniciAdi")+" "+rs.getString("KullaniciSifre"));
       }
       baglantiKapat();
    }
    public void veriGosterDers() throws Exception{
        st = baglantiAc();
        String sorgu = "SELECT * FROM ders";
        rs = st.executeQuery(sorgu);
        while(rs.next()){
             System.out.println(rs.getString("DersID")+" "+rs.getString("DersAdi")+" "+rs.getString("ID"));
        }
    }
    public void veriEkleYonetici(String ad, String soyad, String unvan, String kullaniciAdi, String kullaniciSifre ) throws Exception{
        st = baglantiAc();
        String sorgu = "INSERT INTO yonetici(Adi, Soyadi, Unvan, KullaniciAdi, KullaniciSifre) "
                    + "VALUES(?, ?, ?, ?,?)";
        ps = conn.prepareStatement(sorgu);
        ps.setString(1, ad);
        ps.setString(2, soyad);
        ps.setString(3, unvan);
        ps.setString(4, kullaniciAdi);
        ps.setString(5, kullaniciSifre);
        ps.execute();
        baglantiKapat();
        
    }
    public void veriEkleDers(int dersId, String dersAdi) throws Exception{
        st = baglantiAc();
        String sorgu = "INSERT INTO ders(DersAdi) "
                    + "VALUES(?)";
        ps = conn.prepareStatement(sorgu);
        ps.setInt(1, dersId);
        ps.setString(2, dersAdi);
        ps.execute();
        baglantiKapat();
    }
    public void oto(String kulAdi, String kulSifre) throws Exception{
        st = baglantiAc();
        String sorgu = "SELECT * FROM yonetici where KullaniciAdi=? and KullaniciSifre=?";
        ps = conn.prepareStatement(sorgu);
        ps.setString(1,kulAdi);
        ps.setString(2, kulSifre);
        rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("ad"));
        }
        baglantiKapat();
    }
    
    public boolean validate_login(String username,String password) {
   try{           
        st=baglantiAc();
       PreparedStatement pst = conn.prepareStatement("Select * from yonetici where KullaniciAdi=? and KullaniciSifre=?");
       pst.setString(1, username); 
       pst.setString(2, password);
       ResultSet rs = pst.executeQuery(); 
       
     
       if(rs.next()){ 
       
           return true;   
           
       }
       else{
           return false;
       }
           
   }
    
   catch(Exception e){
       e.printStackTrace();
       return false;
   }
  
 
}
    public void veriGetirArayuz(String kullaniciAdi,String kullaniciSifre){
        try{           
        st=baglantiAc();
       PreparedStatement pst = conn.prepareStatement("Select * from yonetici where KullaniciAdi=? and KullaniciSifre=?");
       pst.setString(1, kullaniciAdi); 
       pst.setString(2, kullaniciSifre);
       ResultSet rs = pst.executeQuery(); 
       while(rs.next()){
         String ad = rs.getString("Adi");
         String soyad = rs.getString("Soyadi");
         String unvan = rs.getString("Unvan");
            int id = rs.getInt("ID");
          rootKisi.setAd(ad);
          rootKisi.setSoyad(soyad);
          rootKisi.setUnvan(unvan);
          rootKisi.setId(id);
                  
      }
           
   }
   catch(Exception e){
       e.printStackTrace();
   }
    }
    
   public List<String> dersGetir(String kullaniciAd, String kullaniciSifre){
       ArrayList<String> idGetir = new ArrayList<String>();
       try{
           st = baglantiAc();
           String sorgu = "SELECT ders.DersAdi FROM ders INNER JOIN yonetici_ders ON yonetici_ders.DersID=ders.DersID "
                   + "INNER JOIN yonetici ON yonetici.ID=yonetici_ders.ID WHERE yonetici.KullaniciAdi=? AND "
                   + "yonetici.KullaniciSifre=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setString(1, kullaniciAd);
           pst.setString(2, kullaniciSifre);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               idGetir.add(rs.getString("DersAdi"));     
           }
           
       }catch(Exception e){
           e.printStackTrace();
       }
       return idGetir;
    }
   public List<String> idGetir(String kullaniciAd, String kullaniciSifre){
       ArrayList<String> idGetir = new ArrayList<String>();
       try{
           st = baglantiAc();
           String sorgu = "SELECT ders.DersID FROM ders INNER JOIN yonetici_ders ON yonetici_ders.DersID=ders.DersID "
                   + "INNER JOIN yonetici ON yonetici.ID=yonetici_ders.ID WHERE yonetici.KullaniciAdi=? AND "
                   + "yonetici.KullaniciSifre=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setString(1, kullaniciAd);
           pst.setString(2, kullaniciSifre);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               int a = rs.getInt("DersId");
               String b = Integer.toString(a);
               idGetir.add(b);     
           }
           
       }catch(Exception e){
           e.printStackTrace();
       }
       return idGetir;
   }
    public String dersAdiGetir(int dersid){
        String ad = null;
        try{
            st = baglantiAc();
            String sorgu = "SELECT ders.DersAdi FROM ders INNER JOIN yonetici_ders ON yonetici_ders.DersID=ders.DersID "
                   + "INNER JOIN yonetici ON yonetici.ID=yonetici_ders.ID WHERE ders.DersID=?";
            PreparedStatement pst = conn.prepareStatement(sorgu);
            pst.setInt(1, dersid);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ad = rs.getString("DersAdi");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ad;
    }
   public boolean bilgiGuncelle(String kullaniciAdi, String kullaniciSifre,String ad, String soyad, String unvan,String dersAd,int dersId){
       try{
           st = baglantiAc();
           String sorgu = "UPDATE yonetici SET Adi=?, Soyadi=?, Unvan=? WHERE KullaniciAdi=? AND KullaniciSifre=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setString(1, ad);
           pst.setString(2, soyad);
           pst.setString(3, unvan);
           pst.setString(4, kullaniciAdi);
           pst.setString(5, kullaniciSifre);
           pst.executeUpdate();
//           String sorgu2 = "SELECT ders.DersID FROM ders INNER JOIN yonetici_ders ON yonetici_ders.DersID=ders.DersID "
//                   + "INNER JOIN yonetici ON yonetici.ID=yonetici_ders.ID WHERE yonetici.KullaniciAdi=? AND yonetici.KullaniciSİfre=?";
//           PreparedStatement pst2 = conn.prepareStatement(sorgu2);
//           pst2.setString(1, kullaniciAdi);
//           pst2.setString(2, kullaniciSifre);
//           ResultSet rs = pst2.executeQuery();
//           while(rs.next()){
               String sorgu3 = "UPDATE ders SET DersAdi=? WHERE DersID=?";
               PreparedStatement pst3 = conn.prepareStatement(sorgu3);
               pst3.setString(1, dersAd);
               pst3.setInt(2, dersId);
               pst3.executeUpdate();
           
            return true;
           
           
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
      
   }
   public boolean ogrenciEkle(int ogrno, String ograd, String ogrsoyad,String cinsiyet,int dersID){
       try{
           st = baglantiAc();
           String sorgu = "INSERT INTO ogrenci(OgrNo, OgrAdi, OgrSoyadi, Cinsiyet)"
                    + "VALUES(?, ?, ?,?)";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setInt(1,ogrno);
           pst.setString(2, ograd);
           pst.setString(3, ogrsoyad);
           pst.setString(4, cinsiyet);
           pst.execute();
           String sorgu3 = "INSERT INTO ders_ogrenci(OgrNo, DersID) VALUES(?,?)";
           PreparedStatement ps = conn.prepareStatement(sorgu3);
           ps.setInt(1,ogrno);
           ps.setInt(2,dersID);
           ps.execute();
           String ogrnosu = Integer.toString(ogrno);
           String imgYol = "images/"+ogrnosu+".jpg";
           String sorgu2 = "INSERT INTO album(image, OgrNo) VALUES(?,?,?)";
           PreparedStatement pt = conn.prepareStatement(sorgu2);
           pt.setString(2, imgYol);
           pt.setInt(3, ogrno);
           pt.execute();
           return true;
           
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
   }
   public List<String> yolCek(int ogrno){
      ArrayList<String> idGetir = new ArrayList<String>();
       try{
           st = baglantiAc();
           String sorgu = "SELECT * from album where OgrNo=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setInt(1, ogrno);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               String a = rs.getString("image");
               idGetir.add(a);     
           }
           
       }catch(Exception e){
           e.printStackTrace();
       }
       return idGetir;
   }
   public String ogrGetir(int ogrno){
       String a = null;
       try{
           st = baglantiAc();
           String sorgu = "SELECT * FROM album WHERE OgrNo=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setInt(1, ogrno);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               a = rs.getString("image");
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return a;
   }
   public int dersId(String dersAdi){
       int id = 0;
       try{
           st = baglantiAc();
           String sorgu = "SELECT DersID FROM ders WHERE DersAdi=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setString(1, dersAdi);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               id = rs.getInt("DersID");
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return id;
   }
   public void bolumGet(String kulad, String kulsif){
       try{
           st = baglantiAc();
           String sorgu = "SELECT bolum.Aciklama, bolum.BolumAdi, bolum.BolumID FROM bolum "
                   + "INNER JOIN yonetici ON yonetici.BolumID=bolum.BolumID "
                   + "WHERE yonetici.KullaniciAdi=? AND yonetici.KullaniciSİfre=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setString(1, kulad);
           pst.setString(2, kulsif);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               rootKisi.setBolumAciklama(rs.getString("Aciklama"));
               rootKisi.setBolumAd(rs.getString("BolumAdi"));
               rootKisi.setBolumId(rs.getInt("BolumID"));
           }
           
       }catch(Exception e){
           e.printStackTrace();
       }
   }
   public List<String> bolumGetir(){
       ArrayList<String> bolum = new ArrayList<String>();
       try{
           st = baglantiAc();
           String sorgu = "SELECT Aciklama FROM bolum";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               bolum.add(rs.getString("Aciklama"));
           }
           
       }catch(Exception e){
           e.printStackTrace();
       }
       return bolum;
   }
   public List<String> dersler(String bolumAd, String sinif){
       ArrayList<String> dersler = new ArrayList<String>();
       try{
           st = baglantiAc();
           String sorgu = "SELECT ders.DersAdi FROM ders "
                   + "INNER JOIN bolum ON bolum.BolumID=ders.BolumID "
                   + "WHERE bolum.Aciklama=? AND ders.Sinif=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setString(1, bolumAd);
           pst.setString(2, sinif);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               dersler.add(rs.getString("DersAdi"));
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return dersler;
   }
   public List<String> resimyollarinigetir(){
        ArrayList<String> dersler = new ArrayList<String>();
       try{
           st = baglantiAc();
           String sorgu = "SELECT ders.DersAdi FROM ders "
                   + "INNER JOIN bolum ON bolum.BolumID=ders.BolumID "
                   + "WHERE bolum.Aciklama=? AND ders.Sinif=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
         //  pst.setString(1, bolumAd);
         //  pst.setString(2, sinif);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               dersler.add(rs.getString("DersAdi"));
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return dersler;
   }
   public int mevcut(String dersadi){
       int mevcut=0,dersid = 0;
       
       try{
           st = baglantiAc();
           String sorgu1 = "SELECT DersID FROM ders WHERE DersAdi=?";
           PreparedStatement pst = conn.prepareStatement(sorgu1);
           pst.setString(1, dersadi);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               dersid = rs.getInt("DersID");
           }
           String sorgu2 = "SELECT COUNT(OgrNo) FROM ders_ogrenci WHERE DersID=?";
           PreparedStatement pt = conn.prepareStatement(sorgu2);
           pt.setInt(1, dersid);
           ResultSet rs1 = pt.executeQuery();
           while(rs1.next()){
               mevcut = rs1.getInt("COUNT(OgrNo)");
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return mevcut;
   }
   
    public List<String> ogrNo(String dersadi){
        ArrayList<String> ogrno = new ArrayList<String>();
       try{
           st = baglantiAc();
           String sorgu = "SELECT ders_ogrenci.OgrNo FROM ders_ogrenci "
                   + "INNER JOIN ders ON ders.DersID=ders_ogrenci.DersID "
                   + "WHERE DersAdi=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setString(1, dersadi);
         //  pst.setString(2, sinif);
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               ogrno.add(rs.getString("OgrNo"));
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return ogrno;
   }
    public boolean ogrenciEkle(int ogrno, String ograd, String ogrsoyad,String cinsiyet,int bolumid,ArrayList<String> dersler){
       try{
           st = baglantiAc();
           String sorgu = "INSERT INTO ogrenci(OgrNo, OgrAdi, OgrSoyadi, Cinsiyet,BolumID)"
                    + "VALUES(?, ?, ?,?,?)";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setInt(1,ogrno);
           pst.setString(2, ograd);
           pst.setString(3, ogrsoyad);
           pst.setString(4, cinsiyet);
           pst.setInt(5, bolumid);
           pst.execute();
           for(int i=0;i<dersler.size();i++){
           String sorgu3 = "INSERT INTO ders_ogrenci(OgrNo, DersID) VALUES(?,?)";
           PreparedStatement ps = conn.prepareStatement(sorgu3);
           ps.setInt(1,ogrno);
           ps.setInt(2,dersId(dersler.get(i)));
           ps.execute();
           }
           String ogrnosu = Integer.toString(ogrno);
           String imgYol = "images/"+ogrnosu+".jpg";
           String sorgu2 = "INSERT INTO album(OgrNo, imageYol) VALUES(?,?)";
           PreparedStatement pt = conn.prepareStatement(sorgu2);
           pt.setInt(1, ogrno);
           pt.setString(2, imgYol);
           pt.execute();
           return true;
           
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
   }
    public int bolumId(String bolumAdi){
       int id=0;
       try{
           st = baglantiAc();
           String sorgu = "SELECT BolumID FROM bolum WHERE Aciklama=?";
           PreparedStatement pst = conn.prepareStatement(sorgu);
           pst.setString(1, bolumAdi);
           ResultSet rs = pst.executeQuery();
           
           while(rs.next()){
               id = rs.getInt("BolumID");
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return id;
   }
}

    