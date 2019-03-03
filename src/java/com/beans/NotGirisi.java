
package com.beans;

import com.pojos.Ogrenciders;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;


@ManagedBean
@SessionScoped
public class NotGirisi {
    
    private String Tc;
    private String Dersid;
    private int Vize;
    private int Finall;
    private int But;

    
    private Ogrenciders ogrders;
    private List<Ogrenciders> dersliste;
    
    
    
    
    public String getTc() {
        return Tc;
    }

    public void setTc(String Tc) {
        this.Tc = Tc;
    }

    public String getDersid() {
        return Dersid;
    }

    public void setDersid(String Dersid) {
        this.Dersid = Dersid;
    }

    public int getVize() {
        return Vize;
    }

    public void setVize(int Vize) {
        this.Vize=Vize;
    }

    public int getFinall() {
        return Finall;
    }

    public void setFinall(int Finall) {
     
            this.Finall=Finall;
            
        
    }

    public int getBut() {
        return But;
    }

    public void setBut(int But) {
     
            this.But=But;
            
        
    }
    

    public NotGirisi() {
        
        
    }
    
    
    public String notuGir()
    {
        Session ses=null;
        HibernateUtil hu=null;
        try
        {
            
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu = ses.createQuery("update Ogrenciders set Vize=:vize,But=:but,Final=:final"+" where Tc=:tc AND Dersid=:dersid");
            qu.setParameter("vize", Vize);
            qu.setParameter("tc", Tc);
            qu.setParameter("dersid", Dersid);
            qu.setParameter("but", But);
            qu.setParameter("final", Finall);
           
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
