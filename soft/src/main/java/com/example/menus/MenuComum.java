package com.example.menus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import com.example.dados.Sala;
import com.example.dados.Usuario;
import com.example.dados.Reserva;
import com.example.negocio.GerenciamentoSalas;

public class MenuComum {
    protected Usuario usuario;
    protected GerenciamentoSalas gerenciamento;

    static Scanner scanner = new Scanner(System.in);

    public void apresentarMenuComum(Usuario usuario, GerenciamentoSalas gerenciamento) {
        this.usuario = usuario;
        this.gerenciamento = gerenciamento;

        int executandoComum = 1;

        while(executandoComum == 1) {
            System.out.println("\n--- Menu Usuario ---");
            System.out.println("1 - Reservar sala");
            System.out.println("2 - Cancelar reserva");
            System.out.println("3 - Editar reserva");
            System.out.println("4 - Consultar reservas");
            System.out.println("5 - Consultar reservas pendentes");
            System.out.println("6 - Sair");

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
                    consultarReservas();
                break;
                case 5:
                    if(!consultarReservasPendentes(usuario)) {
                        System.out.println("Nenhuma reserva pendente encontrada");
                    }
                break;
                case 6:
                    System.out.println("Saindo do menu comum...");
                return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    public void reservarSala() {
        gerenciamento.mostrarSalas();
        System.out.println("Qual o nome da sala que voce deseja reservar?");
        String nomeSala = scanner.nextLine();
        
        Sala sala = gerenciamento.acharSala(nomeSala);
        if(sala == null) {
            System.out.println("Sala não encontrada");
        } else {
            System.out.println("Digite a data da reserva (formato: yyyy-MM-dd): ");
            String dataInput = scanner.nextLine();
            LocalDate dataReserva = LocalDate.parse(dataInput);

            System.out.println("Digite o horário de início (formato: HH:mm): ");
            String inicioInput = scanner.nextLine();
            LocalTime horarioInicio = LocalTime.parse(inicioInput);
            LocalTime horarioFim = horarioInicio.plusHours(1);

            if(!gerenciamento.cadastrarReserva(usuario, new Reserva(sala, dataReserva, horarioInicio, horarioFim, false), sala)){
                System.out.println("Conflito de horario! Sala ja reservada nesse horario.");
            }
        }
    }

    public void cancelarReserva() {        
        Reserva reservaExcluir = acharReserva();
        if(reservaExcluir != null) {
            gerenciamento.excluirReserva(usuario, reservaExcluir);
        }
    }

    public void editarReserva() {
        Reserva reservaEditar = acharReserva();
        if(reservaEditar != null) {
            System.out.println("O que voce deseja editar?");
            System.out.println("1 - Editar data");
            System.out.println("2 - Editar hora");
            System.out.println("3 - Editar data e hora");
            System.out.println("4 - Cancelar");

            int opcaoEditar = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoEditar) {
                case 1:
                    alterarData(reservaEditar);
                break;
                case 2:
                    alterarHorario(reservaEditar);
                break;
                case 3:
                    alterarData(reservaEditar);
                    alterarHorario(reservaEditar);
                break;
                default:
                    System.out.println("Opcao invalida");
                break;
            }
        }

    }

    public boolean consultarReservas() {
        if (usuario.reservas.isEmpty()) {
            System.out.println("Nenhuma reserva sua foi encontrada.");
            return false;
        } else {
            for (Reserva reserva : usuario.reservas) {
                System.out.println(reserva.toString());
            }
        }
        return true;
    }

    public boolean consultarReservasPendentes(Usuario u) {
        boolean encontrou = false;
        for (Reserva reserva : u.reservas) {
            if(!reserva.getStatus()) {
                System.out.println(reserva.toString());
                encontrou = true;
            }
        }
        if(!encontrou) {
            return false;
        }
        return true;
    }

    public Reserva acharReserva() {
        int repete = 0;
        Reserva reserva = null;

        String nomeSala = scanner.nextLine();

        Sala sala = gerenciamento.acharSala(nomeSala);
        
        if(sala == null) {
            System.out.println("Sala nao encontrada.");
            return null;
        } else {
            for(Reserva r : usuario.reservas) {
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

                for(Reserva r : usuario.reservas) {
                    if(reserva.getSala().equals(sala) && reserva.getDataReserva().equals(dataReserva) && reserva.getHorarioInicio().equals(horarioInicio)) {
                        reserva = r;
                        return reserva;
                    }
                }
            }
        }
        return reserva;
    }

    public void alterarData(Reserva reserva) {
        System.out.print("Insira a nova data (formato: yyyy-MM-dd): ");
        String dataInput = scanner.nextLine();
        LocalDate dataReserva = LocalDate.parse(dataInput);

        reserva.setDataReserva(dataReserva);
        reserva.setStatus(false);
    }

    public void alterarHorario(Reserva reserva) {
        System.out.print("Insira o novo horario (formato: HH:mm): ");
        String inicioInput = scanner.nextLine();
        LocalTime horarioInicio = LocalTime.parse(inicioInput);
        LocalTime horarioFim = horarioInicio.plusHours(1);

        reserva.setHorarioInicio(horarioInicio);
        reserva.setHorarioFim(horarioFim);
        reserva.setStatus(false);
    }
}
