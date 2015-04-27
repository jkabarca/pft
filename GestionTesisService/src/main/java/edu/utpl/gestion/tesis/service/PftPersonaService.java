/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestion.tesis.service;

import edu.utpl.gestion.tesis.entidad.PftPersona;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author usuario
 */
@Local
public interface PftPersonaService {

    List<PftPersona> buscarPorPrograma(Map parametros);

    boolean validarUsuario(PftPersona persona);

    void guardar(PftPersona persona);

    void editar(PftPersona persona);

    void eliminar(PftPersona persona);
}
