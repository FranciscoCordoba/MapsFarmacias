package com.example.mapsfarmacias.modelos;

import java.io.Serializable;

public class Farmacia implements Serializable {

    private String nombre;
    private String horarios;
    private int img;
    private String direccion;

    public Farmacia(String nombre, String horarios, int img, String direccion) {
        this.nombre = nombre;
        this.horarios = horarios;
        this.img = img;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
