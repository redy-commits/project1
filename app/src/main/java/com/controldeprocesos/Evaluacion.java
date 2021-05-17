package com.controldeprocesos;

import java.util.Date;


public class Evaluacion {

    private  int numEva, idDocente, alumnosEvaluados;
    private String codMateria, tipo;
    private Date fechaRealizacion, fechaPublicacion;

    public Evaluacion(){}

    public Evaluacion(int numEva, int idDocente, int alumnosEvaluados, String codMateria, String tipo, Date fechaRealizacion, Date fechaPublicacion) {
        this.numEva = numEva;
        this.idDocente = idDocente;
        this.alumnosEvaluados = alumnosEvaluados;
        this.codMateria = codMateria;
        this.tipo = tipo;
        this.fechaRealizacion = fechaRealizacion;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getNumEva() {
        return numEva;
    }
    public void setNumEva(int numEva) {
        this.numEva = numEva;
    }

    public int getIdDocente() {
        return idDocente;
    }
    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public int getAlumnosEvaluados() {
        return alumnosEvaluados;
    }
    public void setAlumnosEvaluados(int alumnosEvaluados) {
        this.alumnosEvaluados = alumnosEvaluados;
    }

    public String getCodMateria() {
        return codMateria;
    }
    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }
    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

}
