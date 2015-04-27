/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.prueba;

import edu.utpl.gestion.tesis.entidad.PftModalidad;
import edu.utpl.web.service.Academico;
import edu.utpl.web.service.SaludoService;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author usuario
 */
public class PruebaMain {

    public static void main(String[] args) {
        p();
//        conectar("http://localhost:8080/GestionTesisWebService-1.0/AcademicoImplement?wsdl",
//                "", "");
    }

    public static String conectar(final String servicesUrl, final String usuario, final String password) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(servicesUrl);
//            String u = URLEncoder.encode(servicesUrl, "UTF-8");
            String loginAdminPassword = usuario + ":" + password;
            String encoded = new sun.misc.BASE64Encoder().encode(loginAdminPassword.getBytes());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Authorization", "Basic " + encoded);
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return sb.toString();
    }

    private static void p() {
        try {

            URL url = new URL("http://localhost:8080/GestionTesisWebService-1.0/AcademicoImplement?wsdl");

            //1st argument service URI, refer to wsdl document above
            //2nd argument is service name, refer to wsdl document above
            QName qname = new QName("http://implement.service.web.utpl.edu/", "AcademicoImplementService");
            Service service = Service.create(url, qname);
            Academico academico = service.getPort(Academico.class);
            System.out.println(academico.buscarModalidades().size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
