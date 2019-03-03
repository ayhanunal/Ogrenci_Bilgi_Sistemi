
package com.beans;

import com.util.HibernateUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;

@ManagedBean
@RequestScoped
public class AkademisyenSifre {

    private String Tc;
    private String Ad;
    private String Soyad;
    
    private String YeniSifre;

    public String getYeniSifre() {
        return YeniSifre;
    }

    public void setYeniSifre(String YeniSifre) {
        this.YeniSifre = YeniSifre;
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
    
    
    
    public AkademisyenSifre() {
    }
    
    public String sifreDegistir()
    {
        Session ses=null;
        HibernateUtil hu=null;
        try
        {
            
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu = ses.createQuery("update Akademisyen set Sifre=:sifre"+" where Tc=:tc AND Ad=:ad AND Soyad=:soyad");
            qu.setParameter("sifre", YeniSifre);
            qu.setParameter("tc", Tc);
            qu.setParameter("ad", Ad);
            qu.setParameter("soyad", Soyad);
            
           
            int result = qu.executeUpdate();
            ses.getTransaction().commit();
            ses.clear();
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
