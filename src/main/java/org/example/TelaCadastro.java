package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaCadastro extends JFrame {

    private final JTextField txtNome;
    private final JTextField txtPreco;
    private final JTextField txtQuantidade;

    public TelaCadastro() {
        setTitle("Cadastro de Produto");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.setBackground(new Color(230, 240, 250));

        JLabel titulo = new JLabel("Cadastro de Produto", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        painel.add(txtPreco);

        painel.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        painel.add(txtQuantidade);

        JButton btnSalvar = criarBotao("Salvar");
        JButton btnLimpar = criarBotao("Limpar");
        JButton btnVoltar = criarBotao("Voltar");

        painel.add(btnSalvar);
        painel.add(btnLimpar);
        painel.add(new JLabel());
        painel.add(btnVoltar);

        add(titulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Eventos
        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                double preco = Double.parseDouble(txtPreco.getText());
                int quantidade = Integer.parseInt(txtQuantidade.getText());

                Produto p = new Produto(nome, preco, quantidade);
                ProdutoDAO dao = new ProdutoDAO();
                dao.cadastrar(p);

                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");

                txtNome.setText("");
                txtPreco.setText("");
                txtQuantidade.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preço e Quantidade devem ser numéricos.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
            }
        });


        btnLimpar.addActionListener(e -> {
            txtNome.setText("");
            txtPreco.setText("");
            txtQuantidade.setText("");
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
