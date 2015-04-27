/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestion.tesis.dao;

import edu.utpl.gestion.tesis.entidad.PftPersona;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author usuario
 */
@Local
public interface PftPersonaFacadeDao {

    void create(PftPersona pftPersona);

    void edit(PftPersona pftPersona);

    void remove(PftPersona pftPersona);

    PftPersona find(Object id);

    List<PftPersona> findAll();

    List<PftPersona> findRange(int[] range);

    int count();

    public List<PftPersona> buscarPorPrograma(Map parametros);

    public PftPersona validarUsuario(PftPersona persona);

}
