package com.example.dados;

import com.example.negocio.GerenciamentoSalas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdministradorTest {
    private Administrador administrador;
    private List<Sala> salasCriadas = new LinkedList<Sala>();
    
    @BeforeEach
    public void setUp() {
        // Dados para a sala 1 da lista do adm
        List<Equipamento> equipamentos1 = new LinkedList<Equipamento>();
        equipamentos1.add(new Equipamento("Projetor", 1));
        equipamentos1.add(new Equipamento("Mesa", 3));

        Sala sala1 = new Sala("Sala A", 20, equipamentos1);

        // Dados para a Sala 2 da lista do adm
        List<Equipamento> equipamentos2 = new LinkedList<Equipamento>();
        equipamentos2.add(new Equipamento("Quadro", 1));

        Sala sala2 = new Sala("Sala B", 4, equipamentos2);
        
        salasCriadas.add(sala1);
        salasCriadas.add(sala2);

        administrador = new Administrador("Gerivaldo", "58490541035", "47888888888", "senhaAdm");
        administrador.setSalasCriadas(salasCriadas);
    }
    
    @Test
    public void testUsuarioInicializacao() {
        assertEquals("Gerivaldo", administrador.getNome(), "O nome do usuário deveria ser Gerivaldo.");
        assertEquals("58490541035", administrador.getCPF(), "O CPF deveria ser 58490541035.");
        assertEquals("47888888888", administrador.getTelefone(), "O telefone deveria ser 47888888888.");
        assertEquals("senhaAdm", administrador.getSenha(), "A senha deveria ser 'senhaAdm'.");
        assertEquals(2, administrador.getSalasCriadas().size(), "O usuário deveria ter 2 salas criadas.");
    }

    @Test
    public void testSalasCriadasAssociadas() {
        assertEquals(2, administrador.getSalasCriadas().size(), "O administrador deve ter duas salas associadas.");
        assertTrue(administrador.getSalasCriadas().containsAll(salasCriadas), "As salas criadas devem estar associadas ao administrador.");
    }

    @Test
    public void testAdicionarSala() {
        Sala novaSala = new Sala("Sala C", 10, new LinkedList<>());
        salasCriadas.add(novaSala);
        administrador.setSalasCriadas(salasCriadas);

        assertEquals(3, administrador.getSalasCriadas().size(), "Deve haver três salas associadas ao administrador.");
        assertTrue(administrador.getSalasCriadas().contains(novaSala), "A nova sala deve estar na lista de salas criadas.");
    }

    @Test
    public void testRemoverSala() {
        Sala salaParaRemover = salasCriadas.get(0);
        salasCriadas.remove(salaParaRemover);
        administrador.setSalasCriadas(salasCriadas);

        assertEquals(1, administrador.getSalasCriadas().size(), "Deve haver uma sala restante após a remoção.");
        assertFalse(administrador.getSalasCriadas().contains(salaParaRemover), "A sala removida não deve estar na lista de salas criadas.");
    }

    @Test
    public void testBuscarSala() {
        Sala sala = null;
        for(int i = 0; i < salasCriadas.size(); i++) {
            if(administrador.getSalasCriadas().get(i).getNome().equals("Sala A")) {
                sala = administrador.getSalasCriadas().get(i);
            }
        }

        assertNotNull(sala, "A sala buscada deve existir.");
        assertEquals("Sala A", sala.getNome(), "O nome da sala encontrada deve corresponder ao esperado.");
    }

    @Test
    public void testAdicionarSalaDuplicada() {
        GerenciamentoSalas gerenciamento = new GerenciamentoSalas();
        gerenciamento.cadastrarSala(administrador, new Sala("Sala A", 20, null));

        assertEquals(2, administrador.getSalasCriadas().size(), "O administrador não deve poder adicionar salas duplicadas.");
    }

    @Test
    public void testAdministradorSemSalas() {
        Administrador novoAdm = new Administrador("Novo Adm", "12345678901", "4700000000", "senhaNova");

        assertTrue(novoAdm.getSalasCriadas().isEmpty(), "Um administrador recém-criado não deve ter salas associadas.");
    }

    @Test
    public void testAtualizarSala() {
        Sala salaParaAtualizar = salasCriadas.get(0);
        salaParaAtualizar.setCapacidade(30);

        assertEquals(30, salaParaAtualizar.getCapacidade(), "A capacidade da sala deve ser atualizada corretamente.");
    }

}
