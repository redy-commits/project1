package com.controldeprocesos;

public class SolicitudDeImpresiones {
    private int idSolicitud, numPaginas;
    private boolean estadoAprobado;

    public SolicitudDeImpresiones() {
    }

    public SolicitudDeImpresiones(int idSolicitud, int numPaginas, boolean estadoAprobado) {
        this.idSolicitud = idSolicitud;
        this.numPaginas = numPaginas;
        this.estadoAprobado = estadoAprobado;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public boolean isEstadoAprobado() {
        return estadoAprobado;
    }

    public void setEstadoAprobado(boolean estadoAprobado) {
        this.estadoAprobado = estadoAprobado;
    }
}
