/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestionTesis.seguridad.managed.session;

import edu.utpl.gestion.tesis.entidad.PftPersona;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author usuario
 */
@Named(value = "sessionLogin")
@SessionScoped
public class SessionLogin implements Serializable {

    private PftPersona persona;

    public SessionLogin() {
        this.persona = new PftPersona();
    }

    public PftPersona getPersona() {
        return persona;
    }

    public void setPersona(PftPersona persona) {
        this.persona = persona;
    }

}
