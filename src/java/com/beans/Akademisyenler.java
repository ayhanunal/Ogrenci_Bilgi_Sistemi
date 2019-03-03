/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.pojos.Akademisyen;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ayhan Ünal
 */
@ManagedBean
@SessionScoped
public class Akademisyenler {
    
    
    private Akademisyen akademisyen;
    private List<Akademisyen> akdliste;
    
    private int akdsayi;
    private List<Akademisyen> liste;

    public int getAkdsayi() {
        return akdsayi;
    }

    public void setAkdsayi(int akdsayi) {
        this.akdsayi = akdsayi;
    }
    
    
    
    
    public Akademisyenler() {
    }
    
    public List<Akademisyen> akademisyenGetir()
    {
         HibernateUtil hu=null;
         Session ses=null;
        try
        {
            
            akdliste=new ArrayList<>();
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu =ses.createQuery("from Akademisyen");
            akdliste=qu.list();
            ses.getTransaction().commit();
            ses.close();
            for(int i=0;i<akdliste.size();i++)
            {
                if(akdliste.get(i).getBolumid().equals("101"))
                {
                    akdliste.get(i).setBolumid("Bilgisayar Mühendisliği");
                }
                else if(akdliste.get(i).getBolumid().equals("201"))
                {
                    akdliste.get(i).setBolumid("Matematik");
                }
                else if(akdliste.get(i).getBolumid().equals("301"))
                {
                    akdliste.get(i).setBolumid("İktisat");
                }
                else if(akdliste.get(i).getBolumid().equals("102"))
                {
                    akdliste.get(i).setBolumid("İnşaat Mühendisliği");
                }
                else if(akdliste.get(i).getBolumid().equals("103"))
                {
                    akdliste.get(i).setBolumid("Biyomühendislik");
                }
                else if(akdliste.get(i).getBolumid().equals("900"))
                {
                    akdliste.get(i).setBolumid("Tıp");
                }
            }
            return akdliste;
            
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
    public int akdSayiVer()
    {
        liste=akademisyenGetir();
        akdsayi=liste.size();
        return akdsayi;
    }
        
        
        
        
    }
    

