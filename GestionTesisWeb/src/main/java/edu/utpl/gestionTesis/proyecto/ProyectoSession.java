/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utpl.gestionTesis.proyecto;

import edu.utpl.gestionTesis.entity.PftCompetencias;
import edu.utpl.gestionTesis.entity.PftProyecto;
import edu.utpl.gestionTesis.entity.PftProyectoCompetencia;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Abarca
 */
@Named(value = "proyectoSession")
@SessionScoped
public class ProyectoSession implements Serializable {

    private PftProyecto pftProyecto;
    private List<PftProyecto> proyectos;
    private List<PftProyecto> filterProyectos;
    private DualListModel<PftCompetencias> dualCompetencias;
    private List<PftProyectoCompetencia> competenciasSeleccionadas;
    private List<PftProyectoCompetencia> competenciasRemovidas;
    private List<PftProyectoCompetencia> proyectoCompetencias;

    public ProyectoSession() {
        this.proyectoCompetencias = new ArrayList<>();
        this.competenciasSeleccionadas = new ArrayList<>();
        this.competenciasRemovidas = new ArrayList<>();
        this.pftProyecto = new PftProyecto();
        this.proyectos = new ArrayList<>();
        this.filterProyectos = new ArrayList<>();
        this.dualCompetencias = new DualListModel<>();
    }

    public PftProyecto getPftProyecto() {
        return pftProyecto;
    }

    public void setPftProyecto(PftProyecto pftProyecto) {
        this.pftProyecto = pftProyecto;
    }

    public List<PftProyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<PftProyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<PftProyecto> getFilterProyectos() {
        return filterProyectos;
    }

    public void setFilterProyectos(List<PftProyecto> filterProyectos) {
        this.filterProyectos = filterProyectos;
    }

    public DualListModel<PftCompetencias> getDualCompetencias() {
        return dualCompetencias;
    }

    public void setDualCompetencias(DualListModel<PftCompetencias> dualCompetencias) {
        this.dualCompetencias = dualCompetencias;
    }

    public List<PftProyectoCompetencia> getCompetenciasSeleccionadas() {
        return competenciasSeleccionadas;
    }

    public void setCompetenciasSeleccionadas(List<PftProyectoCompetencia> competenciasSeleccionadas) {
        this.competenciasSeleccionadas = competenciasSeleccionadas;
    }

    public List<PftProyectoCompetencia> getCompetenciasRemovidas() {
        return competenciasRemovidas;
    }

    public void setCompetenciasRemovidas(List<PftProyectoCompetencia> competenciasRemovidas) {
        this.competenciasRemovidas = competenciasRemovidas;
    }

    public List<PftProyectoCompetencia> getProyectoCompetencias() {
        return proyectoCompetencias;
    }

    public void setProyectoCompetencias(List<PftProyectoCompetencia> proyectoCompetencias) {
        this.proyectoCompetencias = proyectoCompetencias;
    }

}
