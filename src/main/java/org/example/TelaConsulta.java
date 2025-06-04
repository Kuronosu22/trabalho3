package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class TelaConsulta extends JFrame {

    private final JTextField txtBuscar;
    private final DefaultTableModel modeloTabela;

    public TelaConsulta() {
        setTitle("Consulta de Produto");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelSuperior.setBackground(new Color(230, 240, 250));

        painelSuperior.add(new JLabel("Buscar por Nome:"));
        txtBuscar = new JTextField(20);
        painelSuperior.add(txtBuscar);

        JButton btnBuscar = criarBotao("Buscar");
        JButton btnVoltar = criarBotao("Voltar");

        painelSuperior.add(btnBuscar);
        painelSuperior.add(btnVoltar);

        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Preço", "Quantidade"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabela = new JTable(modeloTabela);
        tabela.setRowSelectionAllowed(false);
        tabela.setCellSelectionEnabled(false);

        JScrollPane scrollPane = new JScrollPane(tabela);

        add(painelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Eventos
        btnBuscar.addActionListener(e -> {
            try {
                String nomeBusca = txtBuscar.getText();

                ProdutoDAO dao = new ProdutoDAO();
                modeloTabela.setRowCount(0); // Limpa tabela

                if (nomeBusca.isEmpty()) {
                    // Listar todos
                    for (Produto p : dao.listarTodos()) {
                        modeloTabela.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()});
                    }
                } else {
                    Produto p = dao.buscarPorNome(nomeBusca);
                    if (p != null) {
                        modeloTabela.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()});
                    } else {
                        JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro na busca: " + ex.getMessage());
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
