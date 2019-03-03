
package com.beans;

import com.pojos.Akademisyen;
import com.pojos.Ogrenci;
import com.util.HibernateUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class KisiSil {
    
    private String Tc;
    public String Durumu;

    public String getTc() {
        return Tc;
    }

    public void setTc(String Tc) {
        this.Tc = Tc;
    }

    public String getDurumu() {
        return Durumu;
    }

    public void setDurumu(String Durumu) {
        this.Durumu = Durumu;
    }
    

    public KisiSil() {
    }
    
    public String kisiyiSil()
    {
        Session ses=null;
        HibernateUtil hu=null;
        try
        {
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            if(Durumu.equals("ogrenci"))
            {
                Ogrenci ogr =(Ogrenci) ses.get(Ogrenci.class,Tc);
                ses.delete(ogr);
                ses.getTransaction().commit();
                
            }
            else
            {
                Akademisyen akd=(Akademisyen) ses.get(Akademisyen.class,Tc);
                ses.delete(akd);
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
