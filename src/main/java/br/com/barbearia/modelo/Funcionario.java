package br.com.barbearia.modelo;

public class Funcionario extends Usuario {
    private String cargo;
    private double salario;

    public Funcionario(int id, String nome, String login, String senha, String cargo, double salario) {
        super(id, nome, login, senha, "FUNCIONARIO");
        this.cargo = cargo;
        this.salario = salario;
    }

    // Getters e Setters
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString() + ", cargo=" + cargo + ", salario=" + salario + "]";
    }
}