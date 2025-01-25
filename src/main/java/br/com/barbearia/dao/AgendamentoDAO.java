package br.com.barbearia.dao;

import br.com.barbearia.modelo.Agendamento;
import br.com.barbearia.modelo.StatusAgendamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO extends BaseDAO {

    // Método para inserir um agendamento
    public void inserir(Agendamento agendamento) throws SQLException {
        String sql = "INSERT INTO Agendamento (cliente_id, servico_id, data_horario, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, agendamento.getCliente().getId());
            stmt.setInt(2, agendamento.getServico().getId());
            stmt.setObject(3, agendamento.getDataHorario());
            stmt.setString(4, agendamento.getStatus().name());
            stmt.executeUpdate();
        }
    }

    // Método para buscar um agendamento pelo ID
    public Agendamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Agendamento WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    ServicoDAO servicoDAO = new ServicoDAO();

                    return new Agendamento(
                            rs.getInt("id"),
                            clienteDAO.buscarPorId(rs.getInt("cliente_id")),
                            servicoDAO.buscarPorId(rs.getInt("servico_id")),
                            rs.getObject("data_horario", LocalDateTime.class),
                            StatusAgendamento.valueOf(rs.getString("status"))
                    );
                }
            }
        }
        return null;
    }

    // Método para listar todos os agendamentos
    public List<Agendamento> listarTodos() throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM Agendamento";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ClienteDAO clienteDAO = new ClienteDAO();
                ServicoDAO servicoDAO = new ServicoDAO();

                agendamentos.add(new Agendamento(
                        rs.getInt("id"),
                        clienteDAO.buscarPorId(rs.getInt("cliente_id")),
                        servicoDAO.buscarPorId(rs.getInt("servico_id")),
                        rs.getObject("data_horario", LocalDateTime.class),
                        StatusAgendamento.valueOf(rs.getString("status"))
                ));
            }
        }
        return agendamentos;
    }

    // Método para atualizar o status de um agendamento
    public void atualizarStatus(int id, StatusAgendamento status) throws SQLException {
        String sql = "UPDATE Agendamento SET status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // Método para excluir um agendamento pelo ID
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Agendamento WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}