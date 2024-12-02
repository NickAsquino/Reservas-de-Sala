package com.example.dados;

import java.util.LinkedList;
import java.util.List;

public class Sala {
    private String nome;
    private Integer capacidade;

    private List<Equipamento> equipamentos = new LinkedList<Equipamento>();

    public Sala(String nome, Integer capacidade, List<Equipamento> equipamentos) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.equipamentos = equipamentos;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }
    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Nome: ").append(nome).append("\n");
        builder.append("Capacidade: ").append(capacidade).append("\n");
        builder.append("Equipamentos:\n");
        for (Equipamento equipamento : equipamentos) {
            builder.append("  - ").append(equipamento.toString()).append("\n");
        }
        return builder.toString();
    }
}
