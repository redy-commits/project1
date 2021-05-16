package com.controldeprocesos;

public class  Estudiante{
    private int idUsuario;

    private String carnet;


    public Estudiante(){
    }
    public Estudiante(String carnet, Integer idUsuario){
        this.carnet=carnet;
        this.idUsuario = idUsuario;
    }
    public String getCarnet()
    {
        return carnet;
    }
    public Integer getIdUsuario()
    {
        return idUsuario;
    }
    public void setCarnet(String carnet)
    {
        this.carnet=carnet;
    }
    public void setIdUsuario(Integer idUsuario)
    {
    this.idUsuario=idUsuario;
    }
}