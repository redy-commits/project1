package com.controldeprocesos;

public class Escuela {
    private String codEscuela;
    private String nombre;


public Escuela(){
}
 public Escuela(String codEscuela, String nombre){
    this.codEscuela=codEscuela;
    this.nombre=nombre;
 }
 public String getcodEscuela(){
    return codEscuela;
 }
 public void setcodEscuela(String codEscuela){
    this.codEscuela=codEscuela;
 }
 public String getNombre(){
    return nombre;
 }
 public void setNombre(String nombre){
    this.nombre=nombre;
 }

}