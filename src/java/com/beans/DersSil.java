
package com.beans;

import com.pojos.Ders;
import com.util.HibernateUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;


@ManagedBean
@SessionScoped
public class DersSil {
    
    private String Dersid;

    public String getDersid() {
        return Dersid;
    }

    public void setDersid(String Dersid) {
        this.Dersid = Dersid;
    }
    
    

    public DersSil() {
    }
    public String dersiSil()
    {
        Session ses=null;
        HibernateUtil hu=null;
        try
        {
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Ders ders=(Ders) ses.get(Ders.class,Dersid);
            ses.delete(ders);
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
