package com.example.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class ReservaTest {

    private Reserva reserva;
    private Sala sala;
    private List<Equipamento> equipamentos = new LinkedList<Equipamento>();
    private LocalDate dataReserva;
    private LocalTime horarioInicio, horarioFim;

    @BeforeEach
    public void setUp() {
        equipamentos.add(new Equipamento("Projetor", 1));
        equipamentos.add(new Equipamento("Mesa", 3));

        sala = new Sala("Sala A", 20, equipamentos);
        
        dataReserva = LocalDate.of(2025, 01, 05);
        horarioInicio = LocalTime.of(9, 0);
        horarioFim = LocalTime.of(10, 0);
        
        reserva = new Reserva(sala, dataReserva, horarioInicio, horarioFim, false);
    }

    @Test
    public void testSala() {
        assertEquals("Sala A", reserva.getSala().getNome(), "A sala associada deveria ser Sala A");
        assertEquals(20, reserva.getSala().getCapacidade(), "A capacidade da sala deveria ser 20");
    }

    @Test
    public void testEquipamentos() {
        assertEquals(2, reserva.getSala().getEquipamentos().size(), "Deveria haver 2 equipamentos na sala");
        assertEquals("Projetor", reserva.getSala().getEquipamentos().get(0).getNomeEquipamento(),
                "O primeiro equipamento deveria ser Projetor");
    }

    @Test
    public void testHorario() {
        assertEquals(horarioInicio, reserva.getHorarioInicio(), "O horário de início deveria ser 09:00");
        assertEquals(horarioFim, reserva.getHorarioFim(), "O horário de fim deveria ser 10:00");
    }

}
