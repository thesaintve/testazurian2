package com.mycom.servicio.impl;

import com.mycom.servicio.PersistenService;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.springframework.stereotype.Service;

/**
 *
 * @author ibesson
 */
@Service
public class PersistenServiceImpl implements PersistenService {
    @Override
    public EntityManager getEM(){
        return Persistence.createEntityManagerFactory("com.mycom_SpringAngular03_war_1.0PU").createEntityManager();
    }
}
