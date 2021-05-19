package com.controldeprocesos;

public class SolicitudDeCambio {
    private int idSolicitudCambio,idExamen,idRazon;
    private float nuevaNota;
    private boolean estadoAprobado;

    public SolicitudDeCambio(){}

    public int getIdSolicitudCambio(){return idSolicitudCambio;}

    public void setIdSolicitudCambio(int idSolicitudDeCambio){this.idSolicitudCambio = idSolicitudDeCambio;}

    public int getIdExamen(){return idExamen;}

    public void setIdExamen(int idExamen){this.idExamen = idExamen;}

    public int getIdRazon(){return idRazon;}

    public void setIdRazon(int idRazon){this.idRazon = idRazon;}

    public float getNuevaNota(){return nuevaNota;}

    public void setNuevaNota(float nuevaNota){this.nuevaNota = nuevaNota;}

    public boolean isEstadoAprobado(){return estadoAprobado;}

    public void setEstadoAprobado(boolean estadoAprobado){this.estadoAprobado = estadoAprobado;}}