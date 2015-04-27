/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestion.tesis.service.implement;

import edu.utpl.gestion.tesis.dao.ModalidadDao;
import edu.utpl.gestion.tesis.entidad.PftModalidad;
import edu.utpl.gestion.tesis.service.PftModalidadService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author usuario
 */
@Stateless
public class PftModalidadServiceImplement implements PftModalidadService {

    @EJB
    private ModalidadDao modalidadDao;

    @Override
    public List<PftModalidad> buscarWS() {
        List<PftModalidad> modalidades = new ArrayList<>();
        List<PftModalidad> modalidadesAux = new ArrayList<>();
        modalidadesAux = modalidadDao.buscarPorCriterio(new PftModalidad());
        if (modalidadesAux == null) {
            return modalidades;
        }
        modalidades = !modalidadesAux.isEmpty() ? modalidadesAux : new ArrayList<PftModalidad>();
        return modalidades;
    }
}
