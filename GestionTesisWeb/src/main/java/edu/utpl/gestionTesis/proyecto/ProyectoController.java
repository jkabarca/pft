/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestionTesis.proyecto;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import edu.utpl.gestionTesis.entity.PftCompetencias;
import edu.utpl.gestionTesis.entity.PftProyecto;
import edu.utpl.gestionTesis.entity.PftProyectoCompetencia;
import edu.utpl.gestionTesis.seguridad.usuario.SessionLogin;
import edu.utpl.gestionTesis.service.CompetenciaService;
import edu.utpl.gestionTesis.service.PftProyectoService;
import edu.utpl.gestionTesis.service.ProyectoCompetenciaService;
import edu.utpl.gestionTesis.util.CabeceraController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Abarca
 */
@Named(value = "proyectoController")
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(
            id = "editarProyecto",
            pattern = "/editarProyecto/",
            viewId = "/faces/pages/proyecto/editarProyecto.xhtml"
    ),
    @URLMapping(
            id = "crearProyecto",
            pattern = "/crearProyecto/",
            viewId = "/faces/pages/proyecto/editarProyecto.xhtml"
    ),
    @URLMapping(
            id = "proyectos",
            pattern = "/proyectos/",
            viewId = "/faces/pages/proyecto/index.xhtml"
    )
})
public class ProyectoController implements Serializable {

    @EJB(lookup = "java:global/ServiceGestionTesis/PftProyectoServiceImplement!edu.utpl.gestionTesis.service.PftProyectoService")
    private PftProyectoService proyectoService;
    @EJB(lookup = "java:global/ServiceGestionTesis/ProyectoCompetenciaServiceImplement!edu.utpl.gestionTesis.service.ProyectoCompetenciaService")
    private ProyectoCompetenciaService proyectoCompetenciaService;
    @EJB(lookup = "java:global/ServiceGestionTesis/CompetenciaServiceImplement!edu.utpl.gestionTesis.service.CompetenciaService")
    private CompetenciaService competenciaService;
    @Inject
    private CabeceraController cabeceraController;
    @Inject
    private SessionLogin sessionLogin;
    @Inject
    private ProyectoSession proyectoSession;

    public ProyectoController() {
    }

    public void preRenderView() {
        buscar();
    }

    public String crear() {
        proyectoSession.setPftProyecto(new PftProyecto());
        iniciar();
        return "pretty:crearProyecto";
    }

    private void buscar() {
        try {
            proyectoSession.getProyectos().clear();
            PftProyecto proyectoBuscar = new PftProyecto();
            proyectoBuscar.setProIdPersona(sessionLogin.getPersona());
            proyectoSession.getProyectos().addAll(proyectoService.buscar(proyectoBuscar));
            proyectoSession.getFilterProyectos().addAll(proyectoSession.getProyectos());
        } catch (Exception e) {
        }
    }

    public String guardar() {
        try {
            if (proyectoSession.getPftProyecto().getProId() == null) {
                proyectoSession.getPftProyecto().setProIdPersona(sessionLogin.getPersona());
                proyectoService.guardar(proyectoSession.getPftProyecto());
                grabarCompetencias();
                cabeceraController.getMessageView().message(FacesMessage.SEVERITY_INFO, "Proyecto guardado", "");
                return "pretty:proyectos";
            }
            proyectoService.actualizar(proyectoSession.getPftProyecto());
            grabarCompetencias();
            eliminarCompetencias();
            cabeceraController.getMessageView().message(FacesMessage.SEVERITY_INFO, "Proyecto actualizado", "");
            return "pretty:proyectos";
        } catch (Exception e) {
        }
        return "";
    }

    private void iniciar() {
         proyectoSession.getCompetenciasRemovidas().clear();
        proyectoSession.getCompetenciasSeleccionadas().clear();
        pickListCompetencias();
    }

    private void pickListCompetencias() {
        this.proyectoSession.getDualCompetencias().getSource().clear();
        this.proyectoSession.getDualCompetencias().getTarget().clear();
        this.proyectoSession.getProyectoCompetencias().clear();
        @SuppressWarnings("UnusedAssignment")
        List<PftCompetencias> proyectoCompetencias = new ArrayList<>();
        List<PftCompetencias> competencias = new ArrayList<>();
        try {
            PftProyectoCompetencia proyectoCompetenciaBuscar = new PftProyectoCompetencia();
            proyectoCompetenciaBuscar.setProyCompIdProyecto(proyectoSession.getPftProyecto());
            proyectoCompetencias = proyectoCompetenciaService.buscarCompetencias(
                    proyectoCompetenciaBuscar);
            proyectoSession.getProyectoCompetencias().addAll(proyectoCompetenciaService.buscar(proyectoCompetenciaBuscar));
            PftCompetencias pftCompetencias = new PftCompetencias();
            competencias.addAll(competenciaService.buscar(pftCompetencias));
            proyectoSession.setDualCompetencias(new DualListModel<>(competenciaService.buscarDiferenciaProyectoCompetencia(competencias, proyectoCompetencias), proyectoCompetencias));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void eliminarCompetencias() {
        for (PftProyectoCompetencia ppc : proyectoSession.getCompetenciasRemovidas()) {
            Integer id = devuelveCompetencia(proyectoSession.getProyectoCompetencias(), ppc);
            PftProyectoCompetencia pftProyectoCompetenciaBuscar = new PftProyectoCompetencia();
            pftProyectoCompetenciaBuscar.setProyCompId(id);
            PftProyectoCompetencia pftProyectoCompetencia = proyectoCompetenciaService.buscarPorId(pftProyectoCompetenciaBuscar);
            proyectoCompetenciaService.eliminar(pftProyectoCompetencia);
        }
    }

    public Integer devuelveCompetencia(List<PftProyectoCompetencia> proyectoCompetencias, final PftProyectoCompetencia pftProyectoCompetencia) {
        for (PftProyectoCompetencia ppc : proyectoCompetencias) {
            if (ppc.getProyCompIdCompetencia().equals(pftProyectoCompetencia.getProyCompIdCompetencia())) {
                return ppc.getProyCompId();
            }
        }
        return null;
    }

    public void transferCompetencias(TransferEvent event) {
        try {
            for (Object item : event.getItems()) {
                int v = item.toString().indexOf(":");
                Integer id = Integer.parseInt(item.toString().substring(0, v));
                PftCompetencias c = new PftCompetencias();
                c.setCompId(id);
                PftCompetencias competencias = competenciaService.buscarPorId(c);
                PftProyectoCompetencia pc = new PftProyectoCompetencia();
                if (competencias != null) {
                    pc.setProyCompIdCompetencia(competencias);
                }
                if (event.isRemove()) {
                    proyectoSession.getCompetenciasSeleccionadas().remove(pc);
                    proyectoSession.getCompetenciasRemovidas().add(pc);

                } else {
                    if (event.isAdd()) {
                        if (contieneCompetencia(proyectoSession.getProyectoCompetencias(), pc)) {
                            proyectoSession.getCompetenciasRemovidas().remove(pc);
                        }
                        proyectoSession.getCompetenciasSeleccionadas().add(pc);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void grabarCompetencias() {
        try {
            for (PftProyectoCompetencia proyectoCompetencia : proyectoSession.getCompetenciasSeleccionadas()) {
                if (proyectoCompetencia.getProyCompId() == null) {
                    proyectoCompetencia.setProyCompIdProyecto(proyectoSession.getPftProyecto());
                    proyectoCompetenciaService.guardar(proyectoCompetencia);
                }
                proyectoCompetenciaService.actualizar(proyectoCompetencia);
            }
        } catch (Exception e) {
        }

    }

    public boolean contieneCompetencia(List<PftProyectoCompetencia> proyectoCompetencias,
            PftProyectoCompetencia proyectoCompetencia) {
        boolean var = false;
        for (PftProyectoCompetencia ppc : proyectoCompetencias) {
            if (ppc.getProyCompIdCompetencia().equals(proyectoCompetencia.getProyCompIdCompetencia())) {
                var = true;
            }
        }
        return var;
    }

    public String editar(PftProyecto pftProyecto) {
        proyectoSession.setPftProyecto(pftProyecto);
        iniciar();
        return "pretty:editarProyecto";
    }
}
