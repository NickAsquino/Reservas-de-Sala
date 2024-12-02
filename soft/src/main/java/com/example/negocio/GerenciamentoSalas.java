package com.example.negocio;

import java.util.LinkedList;
import java.util.List;

import com.example.dados.Administrador;
import com.example.dados.Reserva;
import com.example.dados.Sala;
import com.example.dados.Usuario;

public class GerenciamentoSalas {

    private List<Usuario> usuarios = new LinkedList<Usuario>();
    private List<Sala> salas = new LinkedList<Sala>();
    private List<Reserva> reservas = new LinkedList<Reserva>();

    public List<Reserva> getReservas() {
        return reservas;
    }
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean cadastroUsuario(Usuario usuario) {
        for(Usuario u : usuarios) {
            if(u.getCPF().equals(usuario.getCPF())) {
                System.out.println("Nao foi possivel cadastrar usuario, o CPF ja esta em uso");
                return false;
            }
        }
        usuarios.add(usuario);
        return true;
    }

    public Usuario entrar(String CPF, String senha) {
        for(Usuario usuario : usuarios) {
            if(usuario.getCPF().equals(CPF) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean cadastrarSala(Usuario usuario, Sala novaSala) {
        Administrador administrador = (Administrador) usuario;
        
        for(Sala sala : administrador.getSalasCriadas()) {
            if(sala.getNome().equals(novaSala.getNome())) {
                System.out.println("Essa sala já existe.");
                return false;
            }
        }

        administrador.cadastrarSala(novaSala);
        salas.add(novaSala);
        return true;
    }

    public void mostrarSalas() {
        for(Sala sala : salas) {
            System.out.println(sala.toString());
        }
    }

    public Sala acharSala(String nome) {
        for(Sala sala : salas) {
            if(sala.getNome().equals(nome)) {
                return sala;
            }
        }
        return null;
    }

    public boolean cadastrarReserva(Usuario usuario, Reserva novaReserva, Sala sala) {
        for (Reserva reserva : reservas) {
            if (reserva.getSala().equals(sala) &&
                reserva.getDataReserva().equals(novaReserva.getDataReserva()) &&
                reserva.getHorarioInicio().isBefore(novaReserva.getHorarioFim()) &&
                reserva.getHorarioFim().isAfter(novaReserva.getHorarioInicio())) {
                
                return false;
            }
        }
        
        usuario.cadastrarReserva(novaReserva);

        if (!reservas.contains(novaReserva)) {
            reservas.add(novaReserva);
        }
        System.out.println("Reserva cadastrada com sucesso para a " + sala.getNome());
        return true;
    }

    public void excluirReserva(Usuario usuario, Reserva reserva) {
        reservas.remove(reserva);
        usuario.reservas.remove(reserva);

        System.out.println("Reserva removida :)");
    }

    public void excluirSala(Usuario usuario, Sala sala) {
        Administrador administrador = (Administrador) usuario;
        administrador.excluirSala(sala);

        salas.remove(sala);

        System.out.println("Sala removida :)");
    }

    public void consultarReservas() {
        for(Reserva reserva : reservas) {
            System.out.println(reserva.toString());
        }
    }

}
