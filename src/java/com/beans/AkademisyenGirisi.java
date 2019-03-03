/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.pojos.Akademisyen;
import com.pojos.Akademisyenders;
import com.pojos.Ders;
import com.pojos.Ogrenci;
import com.pojos.Ogrenciders;
import com.util.HibernateUtil;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
public class AkademisyenGirisi {

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
    
    private int ogrsayi;
    private List<Ogrenci> listee;

    public int getOgrsayi() {
        return ogrsayi;
    }

    public void setOgrsayi(int ogrsayi) {
        this.ogrsayi = ogrsayi;
    }
    
    
    
    
    private Ogrenci akdogr;
    private List<Akademisyenders> akdders;
    private List<Ogrenciders> ogrders;
    private List<Ogrenci> ogrencilerim;
  
    
    
    private Ders verilenders;
    private List<Ders> verilendersliste;
    
    
    private List<Akademisyen> akdbilgi;
    

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
    
    
    public AkademisyenGirisi() {}
    
    public String akademisyenGirdi(){
        Session ses;
        try
        {
            ses = HibernateUtil.getSessionFactory().openSession();
            ses.beginTransaction();
            Akademisyen akd = new Akademisyen();
            Criteria criteria = ses.createCriteria(Akademisyen.class);
            criteria.add(Restrictions.eq("tc", Tc));
            List liste = criteria.list();
            List<Akademisyen> akdliste = new ArrayList<Akademisyen>();
            
            if (liste != null && liste.size() > 0) 
            {
                for (int i = 0; i < liste.size(); i++) 
                {
                    akd = (Akademisyen) liste.get(i);
                    akdliste.add(akd);
                }
            } 
            else if (liste == null) 
            {
                ses.close();
                return "akademisyen_girisi?faces-redirect=true";
            }
            Akademisyen gecici = new Akademisyen();
            gecici.setTc(Tc);
            gecici.setSifre(Sifre);
            
            for (int j = 0; j < akdliste.size(); j++) 
            {

                if ((akdliste.get(j).getSifre().equals(gecici.getSifre())) && (akdliste.get(j).getTc().equals(gecici.getTc()))) 
                {
                    ses.close();
                    return "akademisyen_sayfa?faces-redirect=true";
              
                }
              
            }
            ses.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
        return "akademisyen_girisi?faces-redirect=true";
    }
    
    public List<Ogrenci> akademisyenOgr()
    {
        
        HibernateUtil hu=null;
        Session ses=null;
        try
        {
            akdders=new ArrayList<>();
            ogrders=new ArrayList<>();
            ogrencilerim=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Akademisyenders where Tc=:x");
            qu.setParameter("x",Tc);
            akdders=qu.list();
            
            for(int i=0;i<akdders.size();i++)
            {
                
                String x=akdders.get(i).getDersid();
                Query qu1 =ses.createQuery("from Ogrenciders where Dersid=:x1");
                qu1.setParameter("x1",x);
                ogrders=qu1.list();
                
                
            }
            
            for(int j=0;j<ogrders.size();j++)
            {
                String y=ogrders.get(j).getTc();
                Query qu2 =ses.createQuery("from Ogrenci where Tc=:y1");
                qu2.setParameter("y1",y);
                Ogrenci liste = (Ogrenci) qu2.uniqueResult();
                ogrencilerim.add((Ogrenci) liste);
                
            }
            ses.getTransaction().commit();
            ses.close();
            return ogrencilerim;
            
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
    public List<Ders> verilenDers()
    {
        HibernateUtil hu=null;
        Session ses=null;
        try
        {
            verilendersliste=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Ders where Akademisyentc=:x");
            qu.setParameter("x",Tc);
            verilendersliste=qu.list();
            ses.getTransaction().commit();
            ses.close();
            for(int i=0;i<verilendersliste.size();i++)
            {
                if(verilendersliste.get(i).getBolumid().equals("101"))
                {
                    verilendersliste.get(i).setBolumid("Bilgisayar Mühendisliği");
                }
                else if(verilendersliste.get(i).getBolumid().equals("201"))
                {
                    verilendersliste.get(i).setBolumid("Matematik");
                }
                else if(verilendersliste.get(i).getBolumid().equals("301"))
                {
                    verilendersliste.get(i).setBolumid("İktisat");
                }
                else if(verilendersliste.get(i).getBolumid().equals("102"))
                {
                    verilendersliste.get(i).setBolumid("İnşaat Mühendisliği");
                }
                else if(verilendersliste.get(i).getBolumid().equals("103"))
                {
                    verilendersliste.get(i).setBolumid("Biyomühendislik");
                }
                else if(verilendersliste.get(i).getBolumid().equals("900"))
                {
                    verilendersliste.get(i).setBolumid("Tıp");
                }
            }
            return verilendersliste;
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
    public List<Akademisyen> kisiBilgi()
    {
        HibernateUtil hu=null;
        Session ses=null;
        try
        {
            akdbilgi=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Akademisyen where Tc=:x");
            qu.setParameter("x",Tc);
            akdbilgi=qu.list();
            ses.getTransaction().commit();
            ses.close();
            return akdbilgi;
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
            Query qu = ses.createQuery("update Akademisyen set Ad=:ad,Soyad=:soyad,Email=:email,Adres=:adres,Yas=:yas"+" where Tc=:tc");
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
    
    public int ogrSayiVer()
    {
        listee=akademisyenOgr();
        ogrsayi=listee.size();
        return ogrsayi;
    }
    
}
