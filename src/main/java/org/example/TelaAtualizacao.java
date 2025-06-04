package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaAtualizacao extends JFrame {

    private final JTextField txtNomeBuscar;
    private final JTextField txtNome;
    private final JTextField txtPreco;
    private final JTextField txtQuantidade;

    public TelaAtualizacao() {
        setTitle("Atualização de Produto");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.setBackground(new Color(230, 240, 250));

        JLabel titulo = new JLabel("Atualização de Produto", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        painel.add(new JLabel("Nome para Buscar:"));
        txtNomeBuscar = new JTextField();
        painel.add(txtNomeBuscar);

        painel.add(new JLabel("Novo Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Novo Preço:"));
        txtPreco = new JTextField();
        painel.add(txtPreco);

        painel.add(new JLabel("Nova Quantidade:"));
        txtQuantidade = new JTextField();
        painel.add(txtQuantidade);

        JButton btnBuscar = criarBotao("Buscar");
        JButton btnAtualizar = criarBotao("Atualizar");
        JButton btnVoltar = criarBotao("Voltar");

        painel.add(btnBuscar);
        painel.add(btnAtualizar);
        painel.add(new JLabel());
        painel.add(btnVoltar);

        add(titulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Eventos
        btnBuscar.addActionListener(e -> {
            try {
                String nomeBusca = txtNomeBuscar.getText();
                ProdutoDAO dao = new ProdutoDAO();
                Produto p = dao.buscarPorNome(nomeBusca);

                if (p != null) {
                    txtNome.setText(p.getNome());
                    txtPreco.setText(String.valueOf(p.getPreco()));
                    txtQuantidade.setText(String.valueOf(p.getQuantidade()));
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro na busca: " + ex.getMessage());
            }
        });


        btnAtualizar.addActionListener(e -> {
            try {
                String nomeAntigo = txtNomeBuscar.getText();
                ProdutoDAO dao = new ProdutoDAO();
                Produto p = dao.buscarPorNome(nomeAntigo);

                if (p != null) {
                    p.setNome(txtNome.getText());
                    p.setPreco(Double.parseDouble(txtPreco.getText()));
                    p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

                    dao.atualizar(p);
                    JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");

                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preço e Quantidade devem ser numéricos.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro na atualização: " + ex.getMessage());
            }
        });


        btnVoltar.addActionListener(e -> dispose());
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(new Color(100, 149, 237));
        botao.setForeground(Color.WHITE);
        botao.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true));
        return botao;
    }
}
