package br.com.barbearia.modelo;

public class Servico {
    private int id;
    private String descricao;
    private double preco;

    // Construtor
    public Servico(int id, String descricao, double preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Servico [id=" + id + ", descricao=" + descricao + ", preco=" + preco + "]";
    }
}