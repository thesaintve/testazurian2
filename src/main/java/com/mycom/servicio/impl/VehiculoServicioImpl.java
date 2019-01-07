package com.mycom.servicio.impl;

import com.mycom.modelo.Vehiculo;
import com.mycom.servicio.PersistenService;
import com.mycom.servicio.VehiculoServicio;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author david
 */
public class VehiculoServicioImpl implements VehiculoServicio {
    @Autowired
    private PersistenService ps;
    
    EntityManager em;
    
    @Override
    public List<Vehiculo> getAll(){
        em = ps.getEM();
        Query q = em.createQuery("SELECT t FROM Vehiculo t", Vehiculo.class);
        return q.getResultList();
    }
    
    @Override
    public void guardar(Vehiculo v) {
        em = ps.getEM();
        em.getTransaction().begin();    
        
        if (v.getMatricula()==null || v.getMatricula().length()<1) {
            Random R = new Random();
            int intchar = R.nextInt(900)+99;            
            String mat = cMaRandom()+cMaRandom()+cMaRandom()+""+intchar;
            v.setMatricula(mat);
        }        
        
        if (this.em.find(Vehiculo.class, v.getMatricula())!=null){
        this.em.merge(v);
        }else{
        this.em.persist(v);
        }        
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public void borrar(String matricula) {
        em = ps.getEM();
        Vehiculo v = this.em.find(Vehiculo.class, matricula);
        if (v!=null) {
            try {
                em.getTransaction().begin();        
                this.em.remove(v);
                em.getTransaction().commit();
            } catch(Exception e){
                System.out.println(e.getMessage());
                em.getTransaction().rollback();
            } finally {
                em.close();
            }
        }
    }
    
    @Override
    public char cMaRandom(){
        int minimo = 65;
        int maximo = 90;
        Random R = new Random();
        int intchar = R.nextInt(maximo - minimo + 1)+minimo;
        return (char)intchar; 
    }

}
