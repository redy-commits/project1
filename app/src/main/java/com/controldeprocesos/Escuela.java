package com.controldeprocesos;

public class Escuela {
    private int codEscuela;
    private String nombre;


public Escuela(){
}
 public Escuela(int codEscuela, String nombre){
    this.codEscuela=codEscuela;
    this.nombre=nombre;
 }
 public int getcodEscuela(){
    return codEscuela;
 }
 public void setcodEscuela(int codEscuela){
    this.codEscuela=codEscuela;
 }
 public String getNombre(){
    return nombre;
 }
 public void setNombre(String nombre){
    this.nombre=nombre;
 }

}