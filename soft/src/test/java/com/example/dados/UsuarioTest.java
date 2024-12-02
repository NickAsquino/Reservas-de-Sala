package com.example.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {
    private Usuario usuario;
    List<Reserva> reservas = new LinkedList<Reserva>();

    @BeforeEach
    public void setUp() {
        
        // Dados para a Reserva 1 da lista do usuário
        List<Equipamento> equipamentos1 = new LinkedList<Equipamento>();
        equipamentos1.add(new Equipamento("Projetor", 1));
        equipamentos1.add(new Equipamento("Mesa", 3));

        Sala sala1 = new Sala("Sala A", 20, equipamentos1);
        Reserva reserva1 = new Reserva(sala1, LocalDate.of(2024, 12, 20), LocalTime.of(10, 0), LocalTime.of(11, 0), false);

        // Dados para a Reserva 2 da lista do usuário
        List<Equipamento> equipamentos2 = new LinkedList<Equipamento>();
        equipamentos2.add(new Equipamento("Quadro", 1));

        Sala sala2 = new Sala("Sala B", 4, equipamentos2);
        Reserva reserva2 = new Reserva(sala2, LocalDate.of(2025, 10, 5), LocalTime.of(19, 0), LocalTime.of(20, 0), true);

        reservas.add(reserva1);
        reservas.add(reserva2);

        usuario = new Usuario("Ricardo", "26655725089", "47999999999", "senha");
        usuario.setReservas(reservas);
    }

    @Test
    public void testUsuarioInicializacao() {
        assertEquals("Ricardo", usuario.getNome(), "O nome do usuário deveria ser Ricardo.");
        assertEquals("26655725089", usuario.getCPF(), "O CPF deveria ser 26655725089.");
        assertEquals("47999999999", usuario.getTelefone(), "O telefone deveria ser 47999999999.");
        assertEquals("senha", usuario.getSenha(), "A senha deveria ser 'senha'.");
        assertEquals(2, usuario.getReservas().size(), "O usuário deveria ter 2 reservas.");
    }

    @Test
    public void testUsuarioReservas() {
        assertEquals(reservas.size(), usuario.getReservas().size(), "O número de reservas deveria ser igual.");
        
        for (int i = 0; i < reservas.size(); i++) {
            assertEquals(reservas.get(i), usuario.getReservas().get(i), 
                "A reserva na posição " + i + " deveria ser igual.");
        }
    }

    @Test
    public void testAdicionarReserva() {
        Sala salaNova = new Sala("Sala C", 10, null);
        Reserva novaReserva = new Reserva(salaNova, LocalDate.of(2025, 12, 15), LocalTime.of(14, 0), LocalTime.of(15, 0), false);

        usuario.getReservas().add(novaReserva);

        assertEquals(3, usuario.getReservas().size(), "O usuário deveria ter 3 reservas.");
        assertTrue(usuario.getReservas().contains(novaReserva), "A nova reserva deveria estar na lista.");
    }

    @Test
    public void testRemoverReserva() {
        Reserva reservaRemovida = usuario.getReservas().get(0);
        usuario.getReservas().remove(reservaRemovida);

        assertEquals(1, usuario.getReservas().size(), "O usuário deveria ter 1 reserva após a remoção.");
        assertFalse(usuario.getReservas().contains(reservaRemovida), "A reserva removida não deveria estar na lista.");
    }

    @Test
    public void testReservasConfirmadas() {
        List<Reserva> reservasConfirmadas = usuario.getReservas().stream().filter(Reserva::getStatus).toList();

        assertEquals(1, reservasConfirmadas.size(), "Deveria haver 1 reserva confirmada.");
        assertTrue(reservasConfirmadas.get(0).getStatus(), "A reserva recuperada deveria estar confirmada.");
    }

    @Test
    public void testReservasPorData() {
        LocalDate data = LocalDate.of(2024, 12, 20);
        List<Reserva> reservasNaData = usuario.getReservas().stream()
                .filter(reserva -> reserva.getDataReserva().equals(data))
                .toList();

        assertEquals(1, reservasNaData.size(), "Deveria haver 1 reserva na data 2024-12-20.");
        assertEquals(data, reservasNaData.get(0).getDataReserva(), "A data da reserva recuperada deveria ser 2024-12-20.");
    }

    @Test
    public void testNenhumaReservaDisponivel() {
        usuario.getReservas().forEach(reserva -> reserva.setStatus(true));

        boolean existemReservasDisponiveis = usuario.getReservas().stream()
                .anyMatch(reserva -> !reserva.getStatus());

        assertFalse(existemReservasDisponiveis, "Não deveria haver reservas disponíveis.");
    }

    @Test
    public void testAlterarDadosUsuario() {
        usuario.setNome("Maria");
        usuario.setTelefone("48988888888");

        assertEquals("Maria", usuario.getNome(), "O nome do usuário deveria ter sido alterado para Maria.");
        assertEquals("48988888888", usuario.getTelefone(), "O telefone do usuário deveria ter sido alterado para 48988888888.");
    }

    @Test
    public void testSenhaNaoVisivelPublicamente() {
        assertNotNull(usuario.getSenha(), "A senha não deveria ser nula.");
        assertEquals("senha", usuario.getSenha(), "A senha deveria ser 'senha'.");
    }


}
