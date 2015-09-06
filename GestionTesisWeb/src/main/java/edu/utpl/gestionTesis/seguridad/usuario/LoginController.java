/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestionTesis.seguridad.usuario;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import edu.utpl.gestionTesis.entity.PftPersona;
import edu.utpl.gestionTesis.service.PftPersonaService;
import edu.utpl.gestionTesis.util.CabeceraController;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;

/**
 *
 * @author usuario
 */
@Named(value = "loginController")
@URLMappings(mappings = {
    @URLMapping(
            id = "login",
            pattern = "/login/",
            viewId = "/faces/login.xhtml"
    ),
    @URLMapping(
            id = "inicio",
            pattern = "/inicio/",
            viewId = "/faces/pages/inicio/index.xhtml"
    ),
    @URLMapping(
            id = "cambiarClave",
            pattern = "/cambiarClave/",
            viewId = "/faces/pages/seguridad/cambiarClaveForm.xhtml"
    )
})
@SessionScoped
public class LoginController implements Serializable {
    
    @Inject
    private SessionLogin sessionLogin;
    @Inject
    private CabeceraController cabeceraController;
    @EJB(lookup = "java:global/ServiceGestionTesis/PftPersonaServiceImplement!edu.utpl.gestionTesis.service.PftPersonaService")
    private PftPersonaService personaService;
    
    public LoginController() {
        
    }

    public void actualizarClave() {
        List<PftPersona> personas = personaService.buscar(new PftPersona());
        for (PftPersona persona : personas) {
            Sha256Hash sha256Hash = new Sha256Hash(persona.getPerClave(),
                    (new SimpleByteSource("random_salt_value_" + persona.getPerClave())).getBytes());
            String result = sha256Hash.toHex();
            persona.setPerClave(result);
            personaService.editar(persona);
        }
    }

    public String authenticate() {
        try {
            Sha256Hash sha256Hash = new Sha256Hash(sessionLogin.getPassword(),
                    (new SimpleByteSource("random_salt_value_" + sessionLogin.getPassword())).getBytes());
            String result = sha256Hash.toHex();
            sessionLogin.setPassword(result);
            UsernamePasswordToken token = new UsernamePasswordToken(sessionLogin.getUsername(), sessionLogin.getPassword());
//            token.setRememberMe(sessionLogin.getRemember());
            org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            PftPersona personaBuscar = new PftPersona();
            personaBuscar.setPerUsuario(sessionLogin.getUsername());
            sessionLogin.setPersona(personaService.buscarPorUsuario(personaBuscar));
        } catch (Exception e) {
            cabeceraController.getMessageView().message(FacesMessage.SEVERITY_ERROR, "USUARIO INCORRECTO", e.getMessage());
            return "";
        }
        return "pretty:inicio";
    }
    
    public String logout() {
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "pretty:login";
    }
    
    public String cambiarClave() {
        try {
            if (sessionLogin.getConfirmarClave().equalsIgnoreCase(sessionLogin.getPersona().getPerClave())) {
                Sha256Hash sha256Hash = new Sha256Hash(sessionLogin.getPersona().getPerClave(),
                        (new SimpleByteSource("random_salt_value_" + sessionLogin.getPersona().getPerClave())).getBytes());
                String result = sha256Hash.toHex();
                sessionLogin.getPersona().setPerClave(result);
                personaService.editar(sessionLogin.getPersona());
                cabeceraController.getMessageView().message(FacesMessage.SEVERITY_INFO, "Clave Actualizada", "");
                return "pretty:inicio";
            } else {
                cabeceraController.getMessageView().message(FacesMessage.SEVERITY_ERROR, "Clave no coinciden", "");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return "";
    }
}
