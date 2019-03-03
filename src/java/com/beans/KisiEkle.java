
package com.beans;

import com.util.HibernateUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class KisiEkle {
    
    private String Tc;
    private String Ad;
    private String Soyad;
    private String Sifre;
    private String Email;
    private String Durumu;
    private String Bolum;
    private String Adres;
    private String Yas;
    private String Bolumid;

    public String getBolumid() {
        return Bolumid;
    }

    public void setBolumid(String Bolumid) {
        this.Bolumid = Bolumid;
    }
    

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

    public String getDurumu() {
        return Durumu;
    }

    public void setDurumu(String Durumu) {
        this.Durumu = Durumu;
    }

    public String getBolum() {
        return Bolum;
    }

    public void setBolum(String Bolum) {
        this.Bolum = Bolum;
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
    
    

    
    public KisiEkle() {
    }
    
    public String kisiyiEkle()
    {
        Session ses=null;
        HibernateUtil hu=null;
        if(Bolum.equals("matematik"))
        {
            Bolumid="201";
        }
        else if(Bolum.equals("iktisat"))
        {
            Bolumid="301";
        }
        else if(Bolum.equals("bilgisayar"))
        {
            Bolumid="101";
        }
        else if(Bolum.equals("insaat"))
        {
            Bolumid="102";
        }
        else if(Bolum.equals("tip"))
        {
            Bolumid="900";
        }
        else if(Bolum.equals("biyomuhendislik"))
        {
            Bolumid="103";
        }
        try
        {
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            if(Durumu.equals("ogrenci"))
            {
                Query qu=ses.createSQLQuery("insert into Ogrenci(tc,ad,soyad,sifre,email,bolumid,adres,yas) values(:tc,:ad,:soyad,:sifre,:email,:bolumid,:adres,:yas)")
                .setParameter("tc",Tc)
                .setParameter("ad",Ad)
                .setParameter("soyad",Soyad)
                .setParameter("sifre",Sifre)
                .setParameter("email", Email)
                .setParameter("bolumid",Bolumid)
                .setParameter("adres",Adres)
                .setParameter("yas",Yas);
                int result=qu.executeUpdate();
                ses.getTransaction().commit();
                
            }
            else
            {
                Query qu=ses.createSQLQuery("insert into Akademisyen(tc,ad,soyad,sifre,email,bolumid,adres,yas) values(:tc,:ad,:soyad,:sifre,:email,:bolumid,:adres,:yas)")
                .setParameter("tc",Tc)
                .setParameter("ad",Ad)
                .setParameter("soyad",Soyad)
                .setParameter("sifre",Sifre)
                .setParameter("email", Email)
                .setParameter("bolumid",Bolumid)
                .setParameter("adres",Adres)
                .setParameter("yas",Yas);
                int result=qu.executeUpdate();
                ses.getTransaction().commit();
            }
            
            
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
