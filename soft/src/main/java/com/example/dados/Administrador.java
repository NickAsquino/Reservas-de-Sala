package com.example.dados;

import java.util.LinkedList;
import java.util.List;

public class Administrador extends Usuario {

    private List<Sala> salasCriadas;

    public Administrador(String nome, String CPF, String telefone, String senha) {
        super(nome, CPF, telefone, senha);
        this.salasCriadas = new LinkedList<>();
    }

    public List<Sala> getSalasCriadas() {
        return salasCriadas;
    }
    public void setSalasCriadas(List<Sala> salasCriadas) {
        this.salasCriadas = salasCriadas;
    }

    public void cadastrarSala(Sala sala) {
        if (sala != null && !salasCriadas.contains(sala)) {
            salasCriadas.add(sala);
        }
    }

    public void excluirSala(Sala sala) {
        if (sala != null) {
            salasCriadas.remove(sala);
        }
    }
}
