/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestionTesis.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Abarca
 */
public class MessageView implements Serializable{
     public void message(FacesMessage.Severity severity, String mensaje, String mensajeCabecera) {
        FacesMessage message = new FacesMessage(severity, mensaje, mensajeCabecera);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
