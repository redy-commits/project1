package com.controldeprocesos;

public class Docente {

    private int idUsuario;


    private int idDocente;

    public Docente(){
    }
    public Docente(int idDocente, Integer idUsuario){
        this.idDocente=idDocente;
        this.idUsuario = idUsuario;
    }
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }


}
