package gui;

import app.ACMEJogos;
import dados.cliente.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterarClienteGUI extends JFrame {
    private ACMEJogos sistema;
    private JTextField campoNumero, campoNome, campoEndereco, campoExtra1, campoExtra2;

    public AlterarClienteGUI(ACMEJogos sistema) {
        this.sistema = sistema;

        setTitle("Alterar Cliente");
        setSize(300, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        campoNumero = new JTextField();
        campoNome = new JTextField();
        campoEndereco = new JTextField();
        campoExtra1 = new JTextField();
        campoExtra2 = new JTextField();

        add(new JLabel("Número do Cliente a alterar:"));
        add(campoNumero);
        add(new JLabel("Novo Nome:"));
        add(campoNome);
        add(new JLabel("Novo Endereço:"));
        add(campoEndereco);
        add(new JLabel("Novo CPF ou Nome Fantasia:"));
        add(campoExtra1);
        add(new JLabel("Novo CNPJ (se Empresarial):"));
        add(campoExtra2);

        JButton botaoAlterar = new JButton("Alterar");
        botaoAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alterarCliente();
            }
        });

        add(botaoAlterar);
        setVisible(true);
    }

    private void alterarCliente() {
        try {
            int numero = Integer.parseInt(campoNumero.getText());

            Cliente clienteAntigo = null;
            for (Cliente c : sistema.getClientes()) {
                if (c.getNumero() == numero) {
                    clienteAntigo = c;
                    break;
                }
            }

            if (clienteAntigo == null) {
                throw new Exception("Cliente não encontrado.");
            }

            Cliente novoCliente;
            if (clienteAntigo instanceof Individual) {
                novoCliente = new Individual(numero, campoNome.getText(), campoEndereco.getText(), campoExtra1.getText());
            } else {
                novoCliente = new Empresarial(numero, campoNome.getText(), campoEndereco.getText(), campoExtra1.getText(), campoExtra2.getText());
            }

            sistema.alterarCliente(numero, novoCliente);
            JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
