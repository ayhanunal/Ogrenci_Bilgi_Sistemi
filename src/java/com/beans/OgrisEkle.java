
package com.beans;

import com.util.HibernateUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class OgrisEkle {
    
    private String Tc;
    private String Ad;
    private String Soyad;
    private String Sifre;
    private String Email;
    private String Adres;
    private String Yas;

    public String getTc() {
        return Tc;
    }

    public void setTc(String Tc) {
        this.Tc = Tc;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String Ad) {
        this.Ad = Ad;
    }

    public String getSoyad() {
        return Soyad;
    }

    public void setSoyad(String Soyad) {
        this.Soyad = Soyad;
    }

    public String getSifre() {
        return Sifre;
    }

    public void setSifre(String Sifre) {
        this.Sifre = Sifre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String Adres) {
        this.Adres = Adres;
    }

    public String getYas() {
        return Yas;
    }

    public void setYas(String Yas) {
        this.Yas = Yas;
    }
    
    public OgrisEkle() {
    }
    public String ogrisEklendi()
    {
        Session ses=null;
        HibernateUtil hu=null;
        try
        {
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu=ses.createSQLQuery("insert into Ogrenciisleri(tc,ad,soyad,sifre,email,adres,yas) values(:tc,:ad,:soyad,:sifre,:email,:adres,:yas)")
                .setParameter("tc",Tc)
                .setParameter("ad",Ad)
                .setParameter("soyad",Soyad)
                .setParameter("sifre",Sifre)
                .setParameter("email", Email)
                .setParameter("adres",Adres)
                .setParameter("yas",Yas);
                int result=qu.executeUpdate();
                ses.getTransaction().commit();
            ses.close();
            return "basarili?faces-redirect=true";
        }
        catch(Exception ex)
        {
            if(ses!=null)
            {
                ses.close();
            }
            ex.printStackTrace();
            return "basarisiz?faces-redirect=true";
        }
    }
    
}
