/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestion.tesis.dao;

import edu.utpl.gestion.tesis.entidad.PftModalidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author usuario
 */
@Local
public interface ModalidadDao {

    public List<PftModalidad> buscarPorCriterio(PftModalidad modalidad);
}
