package gui;

import app.ACMEJogos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarregarDadosGUI extends JFrame {
    private ACMEJogos sistema;
    private JTextField campoNomeArquivo;

    public CarregarDadosGUI(ACMEJogos sistema) {
        this.sistema = sistema;

        setTitle("Carregar Dados");
        setSize(300, 200);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        campoNomeArquivo = new JTextField();

        add(new JLabel("Nome base do arquivo (sem extensão):"));
        add(campoNomeArquivo);


        JButton botaoCarregarCSV = new JButton("Carregar de CSV (JOGOS, CLIENTES, ALUGUEIS)");
        botaoCarregarCSV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarCSV();
            }
        });

        add(botaoCarregarCSV);
        setVisible(true);
    }

    private void carregarCSV() {
        try {
            sistema.getJogos().clear();
            sistema.getClientes().clear();
            sistema.getAlugueis().clear();

            sistema.inicializar(campoNomeArquivo.getText());
            JOptionPane.showMessageDialog(this, "Dados carregados com sucesso (CSV)!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar CSV: " + e.getMessage());
        }
    }
}
