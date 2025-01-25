package br.com.barbearia.dao;

import br.com.barbearia.modelo.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO extends BaseDAO {

    // Método para inserir um serviço
    public void inserir(Servico servico) throws SQLException {
        String sql = "INSERT INTO Servico (descricao, preco) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getPreco());
            stmt.executeUpdate();
        }
    }

    // Método para buscar um serviço pelo ID
    public Servico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Servico WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Servico(
                            rs.getInt("id"),
                            rs.getString("descricao"),
                            rs.getDouble("preco")
                    );
                }
            }
        }
        return null;
    }

    // Método para listar todos os serviços
    public List<Servico> listarTodos() throws SQLException {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                servicos.add(new Servico(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("preco")
                ));
            }
        }
        return servicos;
    }

    // Método para atualizar um serviço
    public void atualizar(Servico servico) throws SQLException {
        String sql = "UPDATE Servico SET descricao = ?, preco = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getPreco());
            stmt.setInt(3, servico.getId());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um serviço pelo ID
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Servico WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}