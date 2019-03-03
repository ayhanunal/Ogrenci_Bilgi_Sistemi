/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;


import com.pojos.Ogrenciisleri;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ayhan Ünal
 */
@ManagedBean
@SessionScoped
public class OgrisGiris {
    
    private String Tc;
    private String Sifre; 
    
    
    private String Ad;
    private String Soyad;
    private String Email;
    private String Adres;
    private String Yas;

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
    
    private List<Ogrenciisleri> ogrisbilgi;

    public String getTc() {
        return Tc;
    }

    public void setTc(String Tc) {
        this.Tc = Tc;
    }

    public String getSifre() {
        return Sifre;
    }

    public void setSifre(String Sifre) {
        this.Sifre = Sifre;
    }

    public OgrisGiris() {
    }
    public String ogrisGirdi()
    {
        Session ses;
        try
        {
            ses = HibernateUtil.getSessionFactory().openSession();
            ses.beginTransaction();
            Ogrenciisleri ogris = new Ogrenciisleri();
            Criteria criteria = ses.createCriteria(Ogrenciisleri.class);
            criteria.add(Restrictions.eq("tc", Tc));
            List liste = criteria.list();
            List<Ogrenciisleri> ogrisliste = new ArrayList<Ogrenciisleri>();
            
            if (liste != null && liste.size() > 0) 
            {
                for (int i = 0; i < liste.size(); i++) 
                {
                    ogris = (Ogrenciisleri) liste.get(i);
                    ogrisliste.add(ogris);
                }
            } 
            else if (liste == null) 
            {
                ses.close();
                return "ogrenci_isleri_giris?faces-redirect=true";
            }
            Ogrenciisleri gecici = new Ogrenciisleri();
            gecici.setTc(Tc);
            gecici.setSifre(Sifre);
            
            for (int j = 0; j < ogrisliste.size(); j++) 
            {

                if ((ogrisliste.get(j).getSifre().equals(gecici.getSifre())) && (ogrisliste.get(j).getTc().equals(gecici.getTc()))) 
                {
                    ses.close();
                    return "ogrenci_isleri_sayfa?faces-redirect=true";
              
                }
                
            }
            ses.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
        return "ogrenci_isleri_giris?faces-redirect=true";
    }
    public List<Ogrenciisleri> kisiBilgi()
    {
        HibernateUtil hu=null;
        Session ses=null;
        try
        {
            ogrisbilgi=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Ogrenciisleri where Tc=:x");
            qu.setParameter("x",Tc);
            ogrisbilgi=qu.list();
            ses.getTransaction().commit();
            ses.close();
            return ogrisbilgi;
        }
        catch(Exception ex)
        {
            if(ses!=null)
            {
                ses.close();
                
            }
            ex.printStackTrace();
            return null;
        }
    }
    
    
    public String guncelle()
    {
        Session ses=null;
        HibernateUtil hu=null;
        try
        {
            
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu = ses.createQuery("update Ogrenciisleri set Ad=:ad,Soyad=:soyad,Email=:email,Adres=:adres,Yas=:yas"+" where Tc=:tc");
            qu.setParameter("email", Email);
            qu.setParameter("tc", Tc);
            qu.setParameter("ad", Ad);
            qu.setParameter("soyad", Soyad);
            qu.setParameter("adres", Adres);
            qu.setParameter("yas", Yas);
            
           
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
