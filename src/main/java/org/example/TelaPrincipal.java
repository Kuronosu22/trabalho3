package org.example;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Cadastro de Produtos");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 1, 15, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        painel.setBackground(new Color(230, 240, 250));

        JLabel titulo = new JLabel("Menu Principal", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JButton btnCadastrar = criarBotao("Cadastrar Produto");
        JButton btnConsultar = criarBotao("Consultar Produto");
        JButton btnAtualizar = criarBotao("Atualizar Produto");
        JButton btnExcluir = criarBotao("Excluir Produto");
        JButton btnSair = criarBotao("Sair");

        painel.add(btnCadastrar);
        painel.add(btnConsultar);
        painel.add(btnAtualizar);
        painel.add(btnExcluir);
        painel.add(btnSair);

        add(titulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Eventos
        btnCadastrar.addActionListener(e -> new TelaCadastro().setVisible(true));
        btnConsultar.addActionListener(e -> new TelaConsulta().setVisible(true));
        btnAtualizar.addActionListener(e -> new TelaAtualizacao().setVisible(true));
        btnExcluir.addActionListener(e -> new TelaExclusao().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setBackground(new Color(100, 149, 237));
        botao.setForeground(Color.WHITE);
        botao.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true));
        return botao;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
