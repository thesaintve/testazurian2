/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycom.servicio;

/**
 *
 * @author ibesson
 */
public interface JsonTransformer {
    String toJson(Object data);
    Object fromJson(String json, Class clazz);
}
