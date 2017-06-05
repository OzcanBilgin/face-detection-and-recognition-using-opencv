/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import com.mysql.jdbc.Blob;
import java.awt.Image;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class rootKisi {

    /**
     * @return the bolumAd
     */
    public static String getBolumAd() {
        return bolumAd;
    }

    /**
     * @param aBolumAd the bolumAd to set
     */
    public static void setBolumAd(String aBolumAd) {
        bolumAd = aBolumAd;
    }

    /**
     * @return the bolumAciklama
     */
    public static String getBolumAciklama() {
        return bolumAciklama;
    }

    /**
     * @param aBolumAciklama the bolumAciklama to set
     */
    public static void setBolumAciklama(String aBolumAciklama) {
        bolumAciklama = aBolumAciklama;
    }

    /**
     * @return the bolumId
     */
    public static int getBolumId() {
        return bolumId;
    }

    /**
     * @param aBolumId the bolumId to set
     */
    public static void setBolumId(int aBolumId) {
        bolumId = aBolumId;
    }

  

    /**
     * @return the dersSecim
     */
    public static String getDersSecim() {
        return dersSecim;
    }

    /**
     * @param aDersSecim the dersSecim to set
     */
    public static void setDersSecim(String aDersSecim) {
        dersSecim = aDersSecim;
    }

    /**
     * @return the nosu
     */
    public static String getNosu() {
        return nosu;
    }

    /**
     * @param aNosu the nosu to set
     */
    public static void setNosu(String aNosu) {
        nosu = aNosu;
    }

    /**
     * @return the imgs
     */
    public static Image getImgs() {
        return imgs;
    }

    /**
     * @param aImgs the imgs to set
     */
    public static void setImgs(Image aImgs) {
        imgs = aImgs;
    }

    /**
     * @return the is
     */
    public static InputStream getIs() {
        return is;
    }

    /**
     * @param aIs the is to set
     */
    public static void setIs(InputStream aIs) {
        is = aIs;
    }

    /**
     * @return the img
     */
    public static Blob getImg() {
        return img;
    }

    /**
     * @param aImg the img to set
     */
    public static void setImg(Blob aImg) {
        img = aImg;
    }

    /**
     * @return the cinsiyet
     */
    public static String getCinsiyet() {
        return cinsiyet;
    }

    /**
     * @param aCinsiyet the cinsiyet to set
     */
    public static void setCinsiyet(String aCinsiyet) {
        cinsiyet = aCinsiyet;
    }

    /**
     * @return the dersAdi
     */
    public static String getDersAdi() {
        return dersAdi;
    }

    /**
     * @param aDersAdi the dersAdi to set
     */
    public static void setDersAdi(String aDersAdi) {
        dersAdi = aDersAdi;
    }

    /**
     * @return the comboSec
     */
    public static String getComboSec() {
        return comboSec;
    }

    /**
     * @param aComboSec the comboSec to set
     */
    public static void setComboSec(String aComboSec) {
        comboSec = aComboSec;
    }


    /**
     * @return the dersid
     */
    public static int getDersid() {
        return dersid;
    }

    /**
     * @param aDersid the dersid to set
     */
    public static void setDersid(int aDersid) {
        dersid = aDersid;
    }

    /**
     * @return the id
     */
    public static int getId() {
        return id;
    }

    /**
     * @param aId the id to set
     */
    public static void setId(int aId) {
        id = aId;
    }

   

    
    private static String kullanici_adi;
    private static String kullanici_sifre;
    private static String ad;
    private static String soyad;
    private static String unvan;
    private static int id;
    private static int dersid;
    private static String comboSec;
    private static String dersAdi;
    private static String cinsiyet;
    private static Blob img;
    private static InputStream is;
    private static Image imgs;
    private static String nosu;
    private static String dersSecim;
    private static String bolumAd;
    private static String bolumAciklama;
    private static int bolumId;
    private static ArrayList<String> ogrno;

    /**
     * @return the ogrno
     */
    public static ArrayList<String> getOgrno() {
        return ogrno;
    }

    /**
     * @param aOgrno the ogrno to set
     */
    public static void setOgrno(ArrayList<String> aOgrno) {
        ogrno = aOgrno;
    }
    
    public void kisiler(String kullamiciadi, String kullanicisifre){
       this.kullanici_adi=kullamiciadi;
       this.kullanici_sifre=kullanicisifre;
        
    }
    /**
     * @return the soyad
     */
    public static String getSoyad() {
        return soyad;
    }

    /**
     * @param aSoyad the soyad to set
     */
    public static void setSoyad(String aSoyad) {
        soyad = aSoyad;
    }

    /**
     * @return the kullanici_adi
     */
    public String getKullanici_adi() {
        return kullanici_adi;
    }

    /**
     * @param kullanici_adi the kullanici_adi to set
     */
    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    /**
     * @return the kullanici_sifre
     */
    public String getKullanici_sifre() {
        return kullanici_sifre;
    }

    /**
     * @param kullanici_sifre the kullanici_sifre to set
     */
    public void setKullanici_sifre(String kullanici_sifre) {
        this.kullanici_sifre = kullanici_sifre;
    }
 /**
     * @return the ad
     */
    public static String getAd() {
        return ad;
    }

    /**
     * @param aAd the ad to set
     */
    public static void setAd(String aAd) {
        ad = aAd;
    }

    /**
     * @return the unvan
     */
    public static String getUnvan() {
        return unvan;
    }

    /**
     * @param aUnvan the unvan to set
     */
    public static void setUnvan(String aUnvan) {
        unvan = aUnvan;
    }
    
}
