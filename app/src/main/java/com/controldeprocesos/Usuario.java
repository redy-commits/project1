package com.controldeprocesos;

public class Usuario{
    private int idUsuario;
    private String tipo,contrasena,nombre,correo;
    private boolean sesion;

    public boolean isSesion(){return sesion;}

    public void setSesion(boolean sesion){this.sesion = sesion;}

    public Usuario(){}

    public int getIdUsuario(){return idUsuario;}

    public void setIdUsuario(int idUsuario){this.idUsuario = idUsuario;}

    public String getTipo(){return tipo;}

    public void setTipo(String tipo){this.tipo = tipo;}

    public String getContrasena(){return contrasena;}

    public void setContrasena(String contrasena){this.contrasena = contrasena;}

    public String getNombre(){return nombre;}

    public void setNombre(String nombre){this.nombre = nombre;}

    public String getCorreo(){return correo;}

    public void setCorreo(String correo){this.correo = correo;}}