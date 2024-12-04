/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mahasiswa.controller;

import com.mahasiswa.model.HibernateUtil;
import com.mahasiswa.model.ModelMahasiswa;
import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;


public class MahasiswaControllerImpl implements MahasiswaController{

    @Override
    public void addMhs(ModelMahasiswa mhs) {
        Transaction trx = null;
        
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            trx = session.beginTransaction();
            session.save(mhs);
            trx.commit();
        } catch (Exception e) {
            if(trx != null) {
                trx.rollback();
            }
            e.printStackTrace();
        }
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModelMahasiswa getMhs(int id) {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateMhs(ModelMahasiswa mhs) {
        Transaction trx = null;
        
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            trx = session.beginTransaction();
            session.update(mhs);
            trx.commit();
        } catch (Exception e) {
            if(trx != null) {
                trx.rollback();
            }
            e.printStackTrace();
        }
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteMhs(int id) {
        Transaction trx = null;
        
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            trx = session.beginTransaction();
            ModelMahasiswa mhs = session.get(ModelMahasiswa.class, id);
            session.delete(mhs);
            trx.commit();
        } catch (Exception e) {
            if(trx != null) {
                trx.rollback();
            }
            e.printStackTrace();
        }
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModelMahasiswa> getAllMahasiswa() {
        Transaction trx = null;
        List<ModelMahasiswa> listMhs = null;
        
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            trx = session.beginTransaction();
            Query<ModelMahasiswa> query = session.createQuery("from ModelMahasiswa", ModelMahasiswa.class);
            listMhs = query.list();
            
            trx.commit();
        } catch (Exception e) {
            if(trx != null) {
                trx.rollback();
            }
            e.printStackTrace();
        }
        return listMhs;
    }
    public List<ModelMahasiswa> searchMahasiswa(String query) {
        Transaction trx = null;
        List<ModelMahasiswa> listMhs = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            trx = session.beginTransaction();
            Query<ModelMahasiswa> queryObj = session.createQuery("FROM ModelMahasiswa WHERE npm LIKE :query OR nama LIKE :query", ModelMahasiswa.class);
            queryObj.setParameter("query", "%" + query + "%");
            listMhs = queryObj.list();
            trx.commit();
        } catch (Exception e) {
            if (trx != null) {
                trx.rollback();
            }
            e.printStackTrace();
        }
        return listMhs;
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
