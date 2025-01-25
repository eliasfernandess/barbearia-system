package br.com.barbearia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/barbearia"; // Substitua "clinica_medica" pelo nome correto do banco
    private static final String USER = "root"; // Substitua "root" pelo seu usuário do MySQL
    private static final String PASSWORD = "elias"; // Substitua "sua_senha" pela senha correta do MySQL

    /**
     * Retorna uma conexão com o banco de dados.
     *
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Cria e retorna a conexão
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Erro ao carregar o driver do banco de dados: " + e.getMessage());
        }
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @param conn Conexão a ser fechada
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
