package br.com.barbearia.modelo;

public class Cliente extends Usuario {
    private String telefone;
    private String email;

    public Cliente(int id, String nome, String login, String senha, String telefone, String email) {
        super(id, nome, login, senha, "CLIENTE");
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() + ", telefone=" + telefone + ", email=" + email + "]";
    }
}