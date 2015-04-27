/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestionTesis.seguridadad.controlador;

import edu.utpl.gestion.tesis.service.PftPersonaService;
import edu.utpl.gestionTesis.seguridad.managed.session.SessionLogin;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author usuario
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    @Inject
    private SessionLogin sessionLogin;
    @EJB
    private PftPersonaService personaService;

    public Login() {
    }

    public String login() {
        try {
            if (personaService.validarUsuario(sessionLogin.getPersona())) {
                return "principal?faces-redirect=true";
            } else {
                return "";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

}
