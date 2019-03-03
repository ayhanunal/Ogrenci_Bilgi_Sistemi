
package com.beans;

import com.pojos.Ogrenci;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;


@ManagedBean
@SessionScoped
public class OgrisOgrenciGor {
    
    private Ogrenci ogrenci;
    private List<Ogrenci> ogrliste;
    
    private int ogrsayi;

    public int getOgrsayi() {
        return ogrsayi;
    }

    public void setOgrsayi(int ogrsayi) {
        this.ogrsayi = ogrsayi;
    }
    
    private List<Ogrenci> liste;
    
    
    public OgrisOgrenciGor() {
    }
    
    public List<Ogrenci> ogrenciGetir()
    {
        
        HibernateUtil hu=null;
        Session ses=null;
        try
        {
            ogrliste=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Ogrenci");
            ogrliste=qu.list();
            ses.getTransaction().commit();
            ses.close();
            for(int i=0;i<ogrliste.size();i++)
            {
                if(ogrliste.get(i).getBolumid().equals("101"))
                {
                    ogrliste.get(i).setBolumid("Bilgisayar Mühendisliği");
                }
                else if(ogrliste.get(i).getBolumid().equals("201"))
                {
                    ogrliste.get(i).setBolumid("Matematik");
                }
                else if(ogrliste.get(i).getBolumid().equals("301"))
                {
                    ogrliste.get(i).setBolumid("İktisat");
                }
                else if(ogrliste.get(i).getBolumid().equals("102"))
                {
                    ogrliste.get(i).setBolumid("İnşaat Mühendisliği");
                }
                else if(ogrliste.get(i).getBolumid().equals("103"))
                {
                    ogrliste.get(i).setBolumid("Biyomühendislik");
                }
                else if(ogrliste.get(i).getBolumid().equals("900"))
                {
                    ogrliste.get(i).setBolumid("Tıp");
                }
            }
            return ogrliste;
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
    
    public int ogrSayiVer()
    {
        liste=ogrenciGetir();
        ogrsayi=liste.size();
        return ogrsayi;
    }
    
}
