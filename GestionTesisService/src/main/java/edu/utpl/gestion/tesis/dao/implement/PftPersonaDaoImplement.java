package edu.utpl.gestion.tesis.dao.implement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.utpl.gestion.tesis.dao.AbstractFacade;
import edu.utpl.gestion.tesis.dao.PftPersonaFacadeDao;
import edu.utpl.gestion.tesis.entidad.PftPersona;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author usuario
 */
@Stateless
public class PftPersonaDaoImplement extends AbstractFacade<PftPersona> implements PftPersonaFacadeDao {

    public PftPersonaDaoImplement() {
        super(PftPersona.class);
    }

    @Override
    public List<PftPersona> buscarPorPrograma(Map param) {
//        StringBuffer sql = new StringBuffer();
//        HashMap<String, Object> parametros = new HashMap<String, Object>();
//        sql.append("Select p from PftPersona p where p.praId =:programa order by t.perPrimerApellido ");
//        parametros.put("programa", programa);
//        final Query q = em.createQuery(sql.toString());
//        for (String key : parametros.keySet()) {
//            q.setParameter(key, parametros.get(key));
//        }
//        return q.getResultList();
        return null;
    }

    @Override
    public PftPersona validarUsuario(PftPersona persona) {
        List<PftPersona> personas = new ArrayList<>();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("login");
        storedProcedure.registerStoredProcedureParameter("usuario", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("clave", String.class, ParameterMode.IN);
        storedProcedure.setParameter("usuario", persona.getPerUsuario());
        storedProcedure.setParameter("clave", persona.getPerClave());
        storedProcedure.execute();
        personas = storedProcedure.getResultList();
        return !personas.isEmpty() ? personas.get(0) : null;
    }
}
