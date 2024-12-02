package com.example.menus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.example.dados.Administrador;
import com.example.dados.Equipamento;
import com.example.dados.Reserva;
import com.example.dados.Sala;
import com.example.dados.Usuario;
import com.example.negocio.GerenciamentoSalas;

public class MenuADM extends MenuComum {

    public void apresentarMenuAdm(Usuario usuario, GerenciamentoSalas gerenciamento) {

        this.usuario = usuario;
        this.gerenciamento = gerenciamento;

        Scanner scanner = new Scanner(System.in);
        int executandoAdm = 1;

        while (executandoAdm == 1) {
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1 - Reservar sala");
            System.out.println("2 - Cancelar reserva");
            System.out.println("3 - Editar reserva");
            System.out.println("4 - Consultar todas as reservas do sistema");
            System.out.println("5 - Aprovar reserva");
            System.out.println("6 - Cadastrar sala");
            System.out.println("7 - Remover sala");
            System.out.println("8 - Consultar salas criadas");
            System.out.println("9 - Sair");

            int opcao = scanner.nextInt();
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            switch (opcao) {
                case 1:
                    reservarSala();
                    break;
                case 2:
                    if(consultarReservas()) {
                        System.out.println("Qual o nome da sala que voce deseja cancelar a reserva?");    
                        cancelarReserva();
                    }
                    break;
                case 3:
                    if(consultarReservas()) {
                        System.out.println("Qual o nome da sala que voce deseja editar a reserva?");
                        editarReserva();
                    }
                break;
                case 4:
                    gerenciamento.consultarReservas();
                    break;
                case 5:
                    aprovarReserva();
                    break;
                case 6:
                    cadastrarSala();
                    break;
                case 7:
                    gerenciamento.mostrarSalas();
                    removerSala();
                    break;
                case 8:
                    consultarSalasCriadas();
                    break;
                case 9:
                    System.out.println("Saindo do menu ADM...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    public void aprovarReserva() {
        int qtd = 0;
        for(Reserva reserva : gerenciamento.getReservas()) {
            if(reserva.getStatus().equals(false)) {
                System.out.println(reserva.toString());
                qtd++;
            }
        }
        if(qtd != 0) {
            Reserva reserva = acharReservaADM();
            
            if (reserva != null) {
                reserva.setStatus(true);
                System.out.println("Reserva aprovada com sucesso!");
            } else {
                System.out.println("Aprovação cancelada. Reserva não encontrada.");
            }
        } else {
            System.out.println("Nao ha reservas pendentes para aprovacao");
        }
    }
    
    public void cadastrarSala() {
        System.out.print("Digite o nome da sala: ");
        String nome = scanner.nextLine();

        Sala sala = gerenciamento.acharSala(nome);
        if(sala == null) {
            System.out.print("Digite a capacidade da sala: ");
            int capacidade = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Quantos tipos de equipamentos distintos a sala possui? ");
            int quantidadeTipos = scanner.nextInt();
            scanner.nextLine();

            List<Equipamento> equipamentos = new LinkedList<>();
            for (int i = 0; i < quantidadeTipos; i++) {
                System.out.print("Digite o nome do equipamento " + (i + 1) + ": ");
                String nomeEquipamento = scanner.nextLine();

                System.out.print("Digite a quantidade de " + nomeEquipamento + ": ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                equipamentos.add(new Equipamento(nomeEquipamento, quantidade));
            }
            Sala novaSala = new Sala(nome, capacidade, equipamentos);
            if(gerenciamento.cadastrarSala(usuario, novaSala)) {
                System.out.println("Sala criada com sucesso");
            }
        } else {
            System.out.println("Nao foi possivel criar sala, pois ela ja existe");
        }
    }

    public void removerSala() {
        System.out.print("Insira o nome da sala que voce deseja remover: ");
        String nome = scanner.nextLine();

        Sala sala = gerenciamento.acharSala(nome);

        if(sala == null) {
            System.out.println("Sala nao encontrada.");
        } else {
            gerenciamento.excluirSala(usuario, sala);
        }
    }

    public void consultarSalasCriadas() {
        Administrador administrador = (Administrador) usuario;
        
        for(Sala sala : administrador.getSalasCriadas()) {
            System.out.println(sala.toString());
        }
    }

    public Reserva acharReservaADM() {
        int repete = 0;
        Reserva reserva = null;
        
        System.out.println("Qual o nome da sala que voce deseja aprovar?");
        String nomeSala = scanner.nextLine();

        Sala sala = gerenciamento.acharSala(nomeSala);
        
        if(sala == null) {
            System.out.println("Sala nao encontrada.");
            return null;
        } else {            
            for(Reserva r : gerenciamento.getReservas()) {
                if(r.getSala().equals(sala)) {
                    reserva = r;
                    repete++;
                }
            }
            if(repete == 0) {
                System.out.println("Sala nao encontrada nas reservas.");
                return null;
            } else if (repete == 1) {
                return reserva;
            } else if (repete > 1) {
                System.out.println("Digite a data da reserva (formato: yyyy-MM-dd): ");
                String dataInput = scanner.nextLine();
                LocalDate dataReserva = LocalDate.parse(dataInput);

                System.out.println("Digite o horário de início (formato: HH:mm): ");
                String inicioInput = scanner.nextLine();
                LocalTime horarioInicio = LocalTime.parse(inicioInput);

                for(Reserva r : gerenciamento.getReservas()) {
                    if(r.getSala().equals(sala) && r.getDataReserva().equals(dataReserva) && r.getHorarioInicio().equals(horarioInicio)) {
                        reserva = r;
                        return reserva;
                    }
                }
            }
        }
        return reserva;
    }
}
