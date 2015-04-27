/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestion.tesis.dao.implement;

import edu.utpl.gestion.tesis.dao.AbstractFacade;
import edu.utpl.gestion.tesis.dao.ModalidadDao;
import edu.utpl.gestion.tesis.entidad.PftModalidad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author usuario
 */
@Stateless
public class ModalidadDaoImplement extends AbstractFacade<PftModalidad> implements ModalidadDao {

    public ModalidadDaoImplement() {
        super(PftModalidad.class);
    }

    @Override
    public List<PftModalidad> buscarPorCriterio(PftModalidad modalidad) {
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sp_modalidades");
        storedProcedure.execute();
        return storedProcedure.getResultList();
    }

}
