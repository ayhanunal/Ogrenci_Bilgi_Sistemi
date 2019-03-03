/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.pojos.Ders;
import com.pojos.Ogrenci;
import com.pojos.Ogrenciders;
import com.util.HibernateUtil;
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
public class OgrenciGirisi {
    
    private String Tc;
    private String Sifre;
   
    private Double ortalama;
    private List<Ogrenciders> liste;
    
    
   
    private Ogrenci ogrbilgi;
    private List<Ogrenci> bilgiliste;
    
    private Ogrenciders ogrnot;
    private List<Ogrenciders> notliste;
    
    
    private Ders alınanders;
    private List<Ogrenciders> ogrders;
    private List<Ders> derslerim;
    

    private String Dersid;
    
    private List<Ogrenci> ogrbilgim;

    public String getDersid() {
        return Dersid;
    }

    public void setDersid(String Dersid) {
        this.Dersid = Dersid;
    }
    
    
    private Ders kontrol;
    private List<Ders> kontrollist;
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    

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
    
    

    public OgrenciGirisi() {
    }
    
    public String ogrenciGirdi()
    {
        Session ses;
        try
        {
            ses = HibernateUtil.getSessionFactory().openSession();
            ses.beginTransaction();
            Ogrenci ogr = new Ogrenci();
            Criteria criteria = ses.createCriteria(Ogrenci.class);
            criteria.add(Restrictions.eq("tc", Tc));
            List liste = criteria.list();
            List<Ogrenci> ogrliste = new ArrayList<Ogrenci>();
            
            if (liste != null && liste.size() > 0) 
            {
                for (int i = 0; i < liste.size(); i++) 
                {
                    ogr = (Ogrenci) liste.get(i);
                    ogrliste.add(ogr);
                }
            } 
            else if (liste == null) 
            {
                ses.close();
                return "ogrenci_girisi?faces-redirect=true";
            }
            Ogrenci gecici = new Ogrenci();
            gecici.setTc(Tc);
            gecici.setSifre(Sifre);
            
            for (int j = 0; j < ogrliste.size(); j++) 
            {

                if ((ogrliste.get(j).getSifre().equals(gecici.getSifre())) && (ogrliste.get(j).getTc().equals(gecici.getTc()))) 
                {
                    ses.close();
                    return "ogrenci_sayfa?faces-redirect=true";
              
                }
              
            }
            ses.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
        return "ogrenci_girisi?faces-redirect=true";
             
        
        
    }
    public List<Ogrenci> kisiselBilgi()
    {
        
        HibernateUtil hu=null;
        Session ses=null;
        try
        {
            bilgiliste=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Ogrenci where Tc=:x");
            qu.setParameter("x",Tc);
            bilgiliste=qu.list();
            ses.getTransaction().commit();
            ses.close();
            for(int i=0;i<bilgiliste.size();i++)
            {
                if(bilgiliste.get(i).getBolumid().equals("101"))
                {
                    bilgiliste.get(i).setBolumid("Bilgisayar Mühendisliği");
                }
                else if(bilgiliste.get(i).getBolumid().equals("201"))
                {
                    bilgiliste.get(i).setBolumid("Matematik");
                }
                else if(bilgiliste.get(i).getBolumid().equals("301"))
                {
                    bilgiliste.get(i).setBolumid("İktisat");
                }
                else if(bilgiliste.get(i).getBolumid().equals("102"))
                {
                    bilgiliste.get(i).setBolumid("İnşaat Mühendisliği");
                }
                else if(bilgiliste.get(i).getBolumid().equals("103"))
                {
                    bilgiliste.get(i).setBolumid("Biyomühendislik");
                }
                else if(bilgiliste.get(i).getBolumid().equals("900"))
                {
                    bilgiliste.get(i).setBolumid("Tıp");
                }
            }
            return bilgiliste;
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
    
    public List<Ogrenciders> notBilgisi()
    {
        
        HibernateUtil hu=null;
        Session ses=null;
        try
        {
            notliste=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Ogrenciders where Tc=:x");
            qu.setParameter("x",Tc);
            notliste=qu.list();
            ses.getTransaction().commit();
            ses.close();
            return notliste;
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
    public List<Ders> alınanDersler()
    {
        HibernateUtil hu=null;
        Session ses=null;
        try
        {
            ogrders=new ArrayList<>();
            derslerim=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Ogrenciders where Tc=:x");
            qu.setParameter("x",Tc);
            ogrders=qu.list();
            for(int i=0;i<ogrders.size();i++)
            {
                
                String x=ogrders.get(i).getDersid();
                Query qu1 =ses.createQuery("from Ders where Dersid=:x1");
                qu1.setParameter("x1",x);
                Ders liste = (Ders) qu1.uniqueResult(); //uniqueResult :tc uniq değer olduğu için(tek geleceğinden emin olduğumuz için kullandık)
                derslerim.add((Ders) liste);
                
            }
          
            ses.getTransaction().commit();
            ses.close();
            return derslerim;
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
    public String dersiSec()
    {
        String gonder="basarisiz?faces-redirect=true";
        Session ses=null;
        HibernateUtil hu=null;
        try
        {
            kontrollist=new ArrayList<>();
            ogrders=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu1 =ses.createQuery("from Ders");
            kontrollist=qu1.list();       
            for(int i=0;i<kontrollist.size();i++)
            {
                if(kontrollist.get(i).getDersid().equals(Dersid))
                {
                    Query qu=ses.createSQLQuery("insert into Ogrenciders(tc,dersid) values(:tc,:dersid)")
                    .setParameter("tc",Tc)
                    .setParameter("dersid",Dersid);
                    int result=qu.executeUpdate();
                    ses.getTransaction().commit();
                    ses.close();
                    gonder= "basarili?faces-redirect=true";
                    break;
                }
               
            }
            return gonder;
            
            
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
    public String dersiBirak()
    {
        Session ses=null;
        HibernateUtil hu=null;
        try
        {
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Ogrenciders ogrders=(Ogrenciders) ses.get(Ogrenciders.class,id);
            ses.delete(ogrders);
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
    public List<Ogrenci> kisiBilgi()
    {
        HibernateUtil hu=null;
        Session ses=null;
        try
        {
            ogrbilgim=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Ogrenci where Tc=:x");
            qu.setParameter("x",Tc);
            ogrbilgim=qu.list();
            ses.getTransaction().commit();
            ses.close();
            return ogrbilgim;
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
    
    
    
    public double ortalamaGoster()
    {
        ortalama=0.0;
        liste=notBilgisi();
        int x=0;
        for(int i=0;i<liste.size();i++)
        {
            if(liste.get(i).getVize()!=null && liste.get(i).getFinal_()!=null && liste.get(i).getBut()!=null){
            double vize=(liste.get(i).getVize())*0.4;
            double finall=(liste.get(i).getFinal_())*0.6;
            double but=(liste.get(i).getBut())*0.6;
            if(liste.get(i).getVize()<0 || liste.get(i).getVize()>100)
            {
                vize=0.0;
            }
            if(liste.get(i).getBut()<0 || liste.get(i).getBut()>100)
            {
                but=0.0;
            }
            if(liste.get(i).getFinal_()<0 || liste.get(i).getFinal_()>100)
            {
                finall=0.0;
            }
            if((liste.get(i).getFinal_()<0 || liste.get(i).getFinal_()>100) || liste.get(i).getBut()!=-1)
            {
                ortalama+=vize+but;
            }
            else
            {
                ortalama+=vize+finall;
            }
            x++;
            }
            
        }
        
        if(x>0)
        {
        ortalama=ortalama/x;
        ortalama=ortalama*0.04;
        return ortalama;
        }
        else
        {
            return 0.0;
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
            Query qu = ses.createQuery("update Ogrenci set Ad=:ad,Soyad=:soyad,Email=:email,Adres=:adres,Yas=:yas"+" where Tc=:tc");
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
