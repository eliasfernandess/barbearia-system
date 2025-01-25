package br.com.barbearia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/barbearia";
    private static final String USER = "root";
    private static final String PASSWORD = "elias";

    // Conexão não é mais estática
    private Connection connection;

    // Construtor
    public BaseDAO() {
        // A conexão não é mais criada automaticamente no construtor
    }

    // Método para obter uma conexão
    protected Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    // Método para fechar a conexão
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Aqui você pode adicionar um logger para registrar o erro
            }
        }
    }

    // Método para iniciar uma transação
    public void beginTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    // Método para confirmar uma transação
    public void commitTransaction() throws SQLException {
        getConnection().commit();
        getConnection().setAutoCommit(true); // Retorna ao modo padrão
    }

    // Método para desfazer uma transação
    public void rollbackTransaction() {
        try {
            if (connection != null && !connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true); // Retorna ao modo padrão
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Aqui você pode adicionar um logger para registrar o erro
        }
    }
}