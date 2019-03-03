/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.pojos.Ders;
import com.util.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Ayhan Ünal
 */
@ManagedBean
@SessionScoped
public class DersEkle {
    
    private String Dersid;
    private String Dersadi;
    private String Bolumid;
    private String Akademisyentc;
    private int Donem;
    

    public String getDersid() {
        return Dersid;
    }

    public void setDersid(String Dersid) {
        this.Dersid = Dersid;
    }

    public String getDersadi() {
        return Dersadi;
    }

    public void setDersadi(String Dersadi) {
        this.Dersadi = Dersadi;
    }

    public String getBolumid() {
        return Bolumid;
    }

    public void setBolumid(String Bolumid) {
        this.Bolumid = Bolumid;
    }

    public String getAkademisyentc() {
        return Akademisyentc;
    }

    public void setAkademisyentc(String Akademisyentc) {
        this.Akademisyentc = Akademisyentc;
    }

    public int getDonem() {
        return Donem;
    }

    public void setDonem(int Donem) {
        this.Donem = Donem;
    }
    
    
    public DersEkle() {
    }
    
    public String dersiEkle()
    {
       
        Session ses=null;
        HibernateUtil hu=null;
        try
        {
            ses=hu.getSessionFactory().openSession();
            ses.beginTransaction();
            Query qu1=ses.createSQLQuery("insert into Akademisyenders(tc,dersid) values(:tc,:dersid)")
            .setParameter("dersid",Dersid)
            .setParameter("tc",Akademisyentc);
            int result1 =qu1.executeUpdate();
            Query qu=ses.createSQLQuery("insert into Ders(dersid,dersadi,bolumid,akademisyentc,donem) values(:dersid,:dersadi,:bolumid,:akademisyentc,:donem)")
            .setParameter("dersid",Dersid)
            .setParameter("dersadi",Dersadi)
            .setParameter("bolumid",Bolumid)
            .setParameter("akademisyentc",Akademisyentc)
            .setParameter("donem", Donem);
            int result=qu.executeUpdate();
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
