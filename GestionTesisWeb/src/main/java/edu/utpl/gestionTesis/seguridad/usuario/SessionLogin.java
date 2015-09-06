/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestionTesis.seguridad.usuario;

import edu.utpl.gestionTesis.entity.PftPersona;
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
    private String username;
    private String password;
    private Boolean remember;
    private String confirmarClave;
    private String email;

    public SessionLogin() {
        this.persona = new PftPersona();
        this.confirmarClave = "";
    }

    public PftPersona getPersona() {
        return persona;
    }

    public void setPersona(PftPersona persona) {
        this.persona = persona;
    }

    public String getConfirmarClave() {
        return confirmarClave;
    }

    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
