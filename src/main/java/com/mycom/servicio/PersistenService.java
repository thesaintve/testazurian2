/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycom.servicio;

import javax.persistence.EntityManager;

/**
 *
 * @author ibesson
 */
public interface PersistenService {
     public EntityManager getEM();
}
