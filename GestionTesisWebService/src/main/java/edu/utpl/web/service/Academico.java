/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.web.service;

import edu.utpl.gestion.tesis.entidad.PftModalidad;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author usuario
 */
@WebService
public interface Academico {

    @WebMethod(action = "modalidades")
    public List<PftModalidad> buscarModalidades();
}
