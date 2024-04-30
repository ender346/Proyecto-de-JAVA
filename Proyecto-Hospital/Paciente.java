/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionclinica;

/**
 *
 * @author 3nder
 */
public class Paciente {
    private String nombre;
    private int edad;
    private String numeroSeguridadSocial;

    public Paciente(String nombre, int edad, String numeroSeguridadSocial) {
        this.nombre = nombre;
        this.edad = edad;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getNumeroSeguridadSocial() {
        return numeroSeguridadSocial;
    }
}
