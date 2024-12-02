package com.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.example.dados.Administrador;
import com.example.dados.Equipamento;
import com.example.dados.Reserva;
import com.example.dados.Sala;
import com.example.dados.Usuario;
import com.example.menus.MenuInicial;
import com.example.negocio.GerenciamentoSalas;

public class App {

    static Scanner scanner = new Scanner(System.in);
        
    public static void main(String[] args) throws Exception {

        GerenciamentoSalas gerenciamento = new GerenciamentoSalas();

        iniciarDados(gerenciamento);
        
        MenuInicial menuInicial = new MenuInicial();
        menuInicial.apresentarMenuInicial(gerenciamento);
        
                
        scanner.close();    
        }
        
    public static void iniciarDados(GerenciamentoSalas gerenciamento) {

        Usuario u1 = new Usuario("Garibaldi", "123", "47999601223", "senha");
        Usuario adm1 = new Administrador("Fabio", "345", "5598898334", "adm");
        List<Equipamento> equipamentos = Arrays.asList(
            new Equipamento("Projetor", 2),
            new Equipamento("Quadro", 1)
        );
        Sala sala1 = new Sala("Sala 1", 3, equipamentos);
        Sala sala2 = new Sala("Sala 2", 3, equipamentos);

        LocalDate dataReserva = LocalDate.of(2024, 12, 20);
        LocalTime horarioInicio = LocalTime.of(10, 0);
        LocalTime horarioFim = LocalTime.of(11, 0);
        Reserva reserva = new Reserva(sala2, dataReserva, horarioInicio, horarioFim, false);

        gerenciamento.cadastrarReserva(u1, reserva, sala1);

        gerenciamento.cadastroUsuario(u1);
        gerenciamento.cadastroUsuario(adm1);

        gerenciamento.cadastrarSala(adm1, sala1);
        gerenciamento.cadastrarSala(adm1, sala2);
    }
}