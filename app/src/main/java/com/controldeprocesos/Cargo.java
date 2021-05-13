package com.controldeprocesos;

public class Cargo {
    private int idCargo;
    private String NombreCargo;
    public Cargo() {}

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombreCargo() {
        return NombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        NombreCargo = nombreCargo;
    }
}
