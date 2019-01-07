package com.mycom.endpoint;

import com.mycom.modelo.Vehiculo;
import com.mycom.servicio.JsonTransformer;
import com.mycom.servicio.VehiculoServicio;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author david
 */
@Controller
public class VehiculoControlador {
    @Autowired
    private JsonTransformer jsonTransformer;
    
    @Autowired
    private VehiculoServicio vehiculoS;
    
    @RequestMapping(value = {"/vehiculo"}, method = RequestMethod.GET, produces = "application/json")
    public void getAll(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) throws IOException {
        String jsonAllUno=jsonTransformer.toJson(vehiculoS.getAll());         
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.getWriter().println(jsonAllUno);        
    }
    
    @RequestMapping(value = {"/vehiculo"}, method = RequestMethod.POST, produces = "application/json")
    public void save(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        try {
            Vehiculo t1 = (Vehiculo) jsonTransformer.fromJson(jsonEntrada, Vehiculo.class);
            vehiculoS.guardar(t1);        
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);             
        }                
    }

    @RequestMapping(value = {"/vehiculo/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("id") String id) throws IOException {
        try {
            vehiculoS.borrar(id);        
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);             
        }                
    }
    
    
}
