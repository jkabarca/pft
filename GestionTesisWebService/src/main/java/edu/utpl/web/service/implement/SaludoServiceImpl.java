/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.web.service.implement;

import edu.utpl.web.service.SaludoService;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author usuario
 */
@WebService(endpointInterface = "edu.utpl.web.service.SaludoService")
@Stateless
public class SaludoServiceImpl implements SaludoService {

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }

}
