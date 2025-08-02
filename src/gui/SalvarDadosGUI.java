package gui;

import app.ACMEJogos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SalvarDadosGUI extends JFrame {
    private ACMEJogos sistema;
    private JTextField campoNomeArquivo;

    public SalvarDadosGUI(ACMEJogos sistema) {
        this.sistema = sistema;

        setTitle("Salvar Dados");
        setSize(300, 200);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        campoNomeArquivo = new JTextField();

        add(new JLabel("Nome base do arquivo (sem extensão):"));
        add(campoNomeArquivo);

        JButton botaoSalvarCSV = new JButton("Salvar como CSV");
        botaoSalvarCSV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarCSV();
            }
        });

        add(botaoSalvarCSV);
        setVisible(true);
    }


    private void salvarCSV() {
        try {
            sistema.salvarCSV(campoNomeArquivo.getText());
            JOptionPane.showMessageDialog(this, "Dados salvos como CSV com sucesso!");
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar CSV: " + e.getMessage());
        }
    }
}
