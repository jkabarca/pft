/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.web.service.implement;

import edu.utpl.gestion.tesis.entidad.PftModalidad;
import edu.utpl.gestion.tesis.service.PftModalidadService;
import edu.utpl.web.service.Academico;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author usuario
 */
@WebService(endpointInterface = "edu.utpl.web.service.Academico")
@Stateless
public class AcademicoImplement implements Academico {

    @EJB(lookup = "java:global/GestionTesisService-1.0/PftModalidadServiceImplement!edu.utpl.gestion.tesis.service.PftModalidadService")
    private PftModalidadService modalidadService;

    @Override
    public List<PftModalidad> buscarModalidades() {
      return modalidadService.buscarWS();
    }

}
