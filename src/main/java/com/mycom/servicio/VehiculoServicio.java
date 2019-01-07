/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycom.servicio;

import com.mycom.modelo.Vehiculo;
import java.util.List;

/**
 *
 * @author david
 */
public interface VehiculoServicio {
    public List<Vehiculo> getAll();    
    public void guardar(Vehiculo v);    
    public void borrar(String matricula);
    public char cMaRandom();
}
