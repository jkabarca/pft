/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestion.tesis.service.implement;

import edu.utpl.gestion.tesis.dao.PftPersonaFacadeDao;
import edu.utpl.gestion.tesis.entidad.PftPersona;
import edu.utpl.gestion.tesis.service.PftPersonaService;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author usuario
 */
@Stateless
public class PftPersonaServiceImplement implements PftPersonaService {

    @EJB
    private PftPersonaFacadeDao personaFacadeDao;

    @Override
    public List<PftPersona> buscarPorPrograma(Map parametros) {
        try {
//            return personaFacadeDao.buscarPorPrograma(tipoRol, programa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean validarUsuario(PftPersona persona) {
        if (personaFacadeDao.validarUsuario(persona) == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Usuario no existe");
            facesContext.addMessage(null, facesMessage);
            return false;
        }
        return true;
    }

    @Override
    public void guardar(PftPersona persona) {
        personaFacadeDao.create(persona);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Usuaro guardado"));

    }

    @Override
    public void editar(PftPersona persona) {
        personaFacadeDao.edit(persona);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Usuaro actualizado"));
    }

    @Override
    public void eliminar(PftPersona persona) {
        personaFacadeDao.remove(persona);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Usuaro guardado"));
    }
}
