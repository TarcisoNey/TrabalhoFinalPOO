package gui;

import app.*;
import dados.cliente.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarClienteGUI extends JFrame {
    private ACMEJogos sistema;
    private JTextField campoNumero, campoNome, campoEndereco, campoTipo, campoExtra1, campoExtra2;

    public CadastrarClienteGUI(ACMEJogos sistema) {
        this.sistema = sistema;

        setTitle("Cadastrar Cliente");
        setSize(300, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        campoNumero = new JTextField();
        campoNome = new JTextField();
        campoEndereco = new JTextField();
        campoTipo = new JTextField();
        campoExtra1 = new JTextField();
        campoExtra2 = new JTextField();

        add(new JLabel("Número:"));
        add(campoNumero);
        add(new JLabel("Nome:"));
        add(campoNome);
        add(new JLabel("Endereço:"));
        add(campoEndereco);
        add(new JLabel("Tipo (1-Individual, 2-Empresarial):"));
        add(campoTipo);
        add(new JLabel("CPF ou Nome Fantasia:"));
        add(campoExtra1);
        add(new JLabel("CNPJ (se Empresarial):"));
        add(campoExtra2);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        add(botaoCadastrar);
        setVisible(true);
    }

    private void cadastrarCliente() {
        try {
            int numero = Integer.parseInt(campoNumero.getText());
            String nome = campoNome.getText();
            String endereco = campoEndereco.getText();
            int tipo = Integer.parseInt(campoTipo.getText());

            Cliente cliente;
            if (tipo == 1) {
                cliente = new Individual(numero, nome, endereco, campoExtra1.getText());
            } else {
                cliente = new Empresarial(numero, nome, endereco, campoExtra1.getText(), campoExtra2.getText());
            }

            sistema.cadastrarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
