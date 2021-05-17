package com.controldeprocesos;

public class Revision {

    private int numRev1, idExamen;
    private float nuevaNota;
    private String observ;
    private boolean asistio;

    public Revision() {
    }

    public Revision(int numRev1, int idExamen, boolean asistio, float nuevaNota, String observ) {
        this.numRev1 = numRev1;
        this.idExamen = idExamen;
        this.asistio = asistio;
        this.nuevaNota = nuevaNota;
        this.observ = observ;
    }

    public int getNumRev1() {
        return numRev1;
    }

    public void setNumRev1(int numRev1) {
        this.numRev1 = numRev1;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

        public float getNuevaNota() {
        return nuevaNota;
    }

    public void setNuevaNota(float nuevaNota) {
        this.nuevaNota = nuevaNota;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }
}
