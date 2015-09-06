/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestionTesis.seguridad.usuario;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Properties;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Abarca
 */
@Named(value = "usuarioController")
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(
            id = "recuperarClave",
            pattern = "/recuperarClave/",
            viewId = "/faces/pages/seguridad/recuperarClave.xhtml"
    )
})
public class UsuarioController implements Serializable {

    @Inject
    private SessionLogin sessionLogin;

    public UsuarioController() {
    }

    public void recuperarClave() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp");
        // TLS si está disponible  
        props.setProperty("mail.smtp.starttls.enable", "true");
        // Puerto de gmail para envio de correos  
        props.setProperty("mail.smtp.port", "");
        // Nombre del usuario  
        props.setProperty("mail.smtp.user", "jkabarca@utpl.edu.ec");
        // Si requiere o no usuario y password para conectarse.  
        props.setProperty("mail.smtp.auth", "true");
        // Se inicia una nueva sesion  
        Session session = Session.getDefaultInstance(props);
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            BodyPart texto = new MimeBodyPart();
            BodyPart archivo = new MimeBodyPart();
            MimeMultipart correo = new MimeMultipart();
            correo.addBodyPart(texto);
            correo.addBodyPart(archivo);
            texto.setText("Recuperación de Clave");
            archivo.setText("");
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(sessionLogin.getUsername()));
            mimeMessage.setSubject("Su clave es:" + "");
            mimeMessage.setContent(correo);
            Transport t = session.getTransport("smtp");
            t.connect("jkabarca@utpl.edu.ec", "jk1104417942");
            t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            t.close();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
