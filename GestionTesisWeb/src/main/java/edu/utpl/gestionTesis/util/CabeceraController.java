/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestionTesis.util;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Abarca
 */
@Named(value = "cabeceraController")
@SessionScoped
public class CabeceraController implements Serializable {

    private MessageView messageView;

    public CabeceraController() {
    }

    public void preRenderViewLogin() {
        this.messageView = new MessageView();
    }

    public MessageView getMessageView() {
        return messageView;
    }

    public void setMessageView(MessageView messageView) {
        this.messageView = messageView;
    }
}
