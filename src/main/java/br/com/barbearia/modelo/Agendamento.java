package br.com.barbearia.modelo;

import java.time.LocalDateTime;

public class Agendamento {
    private int id;
    private Cliente cliente;
    private Servico servico;
    private LocalDateTime dataHorario;
    private StatusAgendamento status; // Usando o enum

    // Construtor
    public Agendamento(int id, Cliente cliente, Servico servico, LocalDateTime dataHorario, StatusAgendamento status) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.dataHorario = dataHorario;
        this.status = status;
    }

    // Getters e Setters
    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Agendamento [id=" + id + ", cliente=" + cliente.getNome() + ", servico=" + servico.getDescricao() +
                ", dataHorario=" + dataHorario + ", status=" + status + "]";
    }
}