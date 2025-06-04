package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaExclusao extends JFrame {

    private final JTextField txtNome;

    public TelaExclusao() {
        setTitle("Exclusão de Produto");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.setBackground(new Color(230, 240, 250));

        JLabel titulo = new JLabel("Exclusão de Produto", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        painel.add(new JLabel("Nome do Produto:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        JButton btnExcluir = criarBotao("Excluir");
        JButton btnVoltar = criarBotao("Voltar");

        painel.add(btnExcluir);
        painel.add(btnVoltar);

        add(titulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Eventos
        btnExcluir.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                ProdutoDAO dao = new ProdutoDAO();

                Produto p = dao.buscarPorNome(nome);
                if (p != null) {
                    dao.excluirPorNome(nome);
                    JOptionPane.showMessageDialog(this, "Produto excluído com sucesso.");
                    txtNome.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro na exclusão: " + ex.getMessage());
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
