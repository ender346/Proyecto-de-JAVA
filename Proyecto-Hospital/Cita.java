/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionclinica;

/**
 *
 * @author 3nder
 */
import java.util.Date;

public class Cita {
    private String fecha;
    private Medico medico;
    private Paciente paciente;

    public Cita(String fecha, Medico medico, Paciente paciente) {
        this.fecha = fecha;
        this.medico = medico;
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}


