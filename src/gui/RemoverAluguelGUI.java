package gui;

import app.ACMEJogos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoverAluguelGUI extends JFrame {
    private JTextField campoId;
    private ACMEJogos sistema;

    public RemoverAluguelGUI(ACMEJogos sistema) {
        this.sistema = sistema;

        setTitle("Remover Aluguel");
        setSize(300, 150);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        campoId = new JTextField();

        add(new JLabel("Identificador do aluguel a remover:"));
        add(campoId);

        JButton botaoRemover = new JButton("Remover");
        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerAluguel();
            }
        });

        add(botaoRemover);
        setVisible(true);
    }

    private void removerAluguel() {
        try {
            int id = Integer.parseInt(campoId.getText());
            sistema.removerAluguel(id);
            JOptionPane.showMessageDialog(this, "Aluguel removido com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
