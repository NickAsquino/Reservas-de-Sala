package com.example.dados;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
    private String nome;
    private String CPF;
    private String telefone;
    private String senha;

    public List<Reserva> reservas = new LinkedList<Reserva>();

    public Usuario(String nome, String CPF, String telefone, String senha) {
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String cPF) {
        CPF = cPF;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public List<Reserva> getReservas() {
        return reservas;
    }
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void cadastrarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void excluirReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuário: ").append(this.nome).append("\nReservas:\n");
        
        for (Reserva reserva : this.reservas) {
            sb.append(reserva.toString()).append("\n");
        }
        
        return sb.toString();
    }

}