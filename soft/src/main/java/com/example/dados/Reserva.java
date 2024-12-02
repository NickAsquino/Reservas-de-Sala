package com.example.dados;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {

    private Sala sala;
    private LocalDate dataReserva;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private Boolean status;

    public Reserva(Sala sala, LocalDate dataReserva, LocalTime horarioInicio, LocalTime horarioFim, Boolean status) {
        this.sala = sala;
        this.dataReserva = dataReserva;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.status = status;
    }

    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public LocalDate getDataReserva() {
        return dataReserva;
    }
    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }
    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }
    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }
    public LocalTime getHorarioFim() {
        return horarioFim;
    }
    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "\nSala: " + sala.getNome() + "\n" +
            "Data: " + dataReserva + "\n" +
            "Horário: " + horarioInicio + " - " + horarioFim + "\n" +
            "Status: " + (status ? "Confirmada" : "Pendente");
    }
}
