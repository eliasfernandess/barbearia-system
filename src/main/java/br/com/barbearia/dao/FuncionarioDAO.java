package br.com.barbearia.dao;

import br.com.barbearia.modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO extends BaseDAO {

    // Método para inserir um funcionário
    public void inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario (id, cargo, salario) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getCargo());
            stmt.setDouble(3, funcionario.getSalario());
            stmt.executeUpdate();
        }
    }

    // Método para buscar um funcionário pelo ID
    public Funcionario buscarPorId(int id) throws SQLException {
        String sql = "SELECT u.*, f.cargo, f.salario FROM Usuario u JOIN Funcionario f ON u.id = f.id WHERE u.id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Funcionario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getString("cargo"),
                            rs.getDouble("salario")
                    );
                }
            }
        }
        return null;
    }

    // Método para atualizar um funcionário
    public void atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE Funcionario SET cargo = ?, salario = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCargo());
            stmt.setDouble(2, funcionario.getSalario());
            stmt.setInt(3, funcionario.getId());
            stmt.executeUpdate();
        }
    }
}