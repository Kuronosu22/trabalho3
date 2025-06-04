package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdutoDAO {

    public void cadastrar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome().toLowerCase(Locale.ROOT));
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());

            stmt.executeUpdate();
        }
    }

    public Produto buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE nome = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome.toLowerCase(Locale.ROOT));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
            }
            return null;
        }
    }

    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome().toLowerCase(Locale.ROOT));
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getId());

            stmt.executeUpdate();
        }
    }

    public void excluirPorNome(String nome) throws SQLException {
        String sql = "DELETE FROM produtos WHERE nome = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome.toLowerCase(Locale.ROOT));
            stmt.executeUpdate();
        }
    }

    public List<Produto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
                produtos.add(p);
            }
        }
        return produtos;
    }
}
