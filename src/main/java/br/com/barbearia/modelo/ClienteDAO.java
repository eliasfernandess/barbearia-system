package br.com.barbearia.modelo;

import br.com.barbearia.dao.BaseDAO;
import br.com.barbearia.modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO extends BaseDAO {

    // Método para inserir um cliente
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (id, telefone, email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.executeUpdate();
        }
    }

    // Método para buscar um cliente pelo ID
    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT u.*, c.telefone, c.email FROM Usuario u JOIN Cliente c ON u.id = c.id WHERE u.id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getString("telefone"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    // Método para atualizar um cliente
    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET telefone = ?, email = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getTelefone());
            stmt.setString(2, cliente.getEmail());
            stmt.setInt(3, cliente.getId());
            stmt.executeUpdate();
        }
    }
}