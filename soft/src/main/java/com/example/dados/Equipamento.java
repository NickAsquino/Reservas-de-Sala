package com.example.dados;

public class Equipamento {
    private String nomeEquipamento;
    private Integer quantidade;

    public Equipamento(String nomeEquipamento, Integer quantidade) {
        this.nomeEquipamento = nomeEquipamento;
        this.quantidade = quantidade;
    }
    
    public String getNomeEquipamento() {
        return nomeEquipamento;
    }
    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }    

    @Override
    public String toString() {
        return nomeEquipamento + " (Quantidade: " + quantidade + ")";
    }
}
