package com.controldeprocesos;

import java.sql.Date;

public class Reservacion {
    private int idReservacion,idLocal,idDocente;
    private String horaInicio,horaFin;
    private Date fecha;

    public Reservacion() {
    }

    public int getIdLocal() {
        return idLocal;}

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;}

    public int getIdDocente() {
        return idDocente;}

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;}

    public int getIdReservacion() {
        return idReservacion;}

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;}

    public String getHoraInicio() {
        return horaInicio;}

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;}

    public String getHoraFin() {
        return horaFin;}

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;}

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}