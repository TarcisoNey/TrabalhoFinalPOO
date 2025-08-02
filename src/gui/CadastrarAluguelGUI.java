package gui;

import app.ACMEJogos;
import dados.aluguel.Aluguel;
import dados.produto.*;
import dados.cliente.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastrarAluguelGUI extends JFrame {
    private ACMEJogos sistema;
    private JTextField campoId, campoData, campoPeriodo, campoNumeroCliente, campoCodigoJogo;

    public CadastrarAluguelGUI(ACMEJogos sistema) {
        this.sistema = sistema;

        setTitle("Cadastrar Aluguel");
        setSize(300, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        campoId = new JTextField();
        campoData = new JTextField();
        campoPeriodo = new JTextField();
        campoNumeroCliente = new JTextField();
        campoCodigoJogo = new JTextField();

        add(new JLabel("Identificador:"));
        add(campoId);
        add(new JLabel("Data Inicial (dd/MM/yyyy):"));
        add(campoData);
        add(new JLabel("Período (dias):"));
        add(campoPeriodo);
        add(new JLabel("Número do Cliente:"));
        add(campoNumeroCliente);
        add(new JLabel("Código do Jogo:"));
        add(campoCodigoJogo);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarAluguel();
            }
        });

        add(botaoCadastrar);
        setVisible(true);
    }

    private void cadastrarAluguel() {
        try {
            int id = Integer.parseInt(campoId.getText());
            LocalDate data = LocalDate.parse(campoData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int periodo = Integer.parseInt(campoPeriodo.getText());
            int numCliente = Integer.parseInt(campoNumeroCliente.getText());
            int codJogo = Integer.parseInt(campoCodigoJogo.getText());

            Cliente cliente = null;
            for (Cliente c : sistema.getClientes()) {
                if (c.getNumero() == numCliente) {
                    cliente = c;
                    break;
                }
            }

            Jogo jogo = null;
            for (Jogo j : sistema.getJogos()) {
                if (j.getCodigo() == codJogo) {
                    jogo = j;
                    break;
                }
            }

            if (cliente == null || jogo == null) {
                throw new Exception("Cliente ou Jogo não encontrado.");
            }

            sistema.cadastrarAluguel(new Aluguel(id, data, periodo, cliente, jogo));
            JOptionPane.showMessageDialog(this, "Aluguel cadastrado com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
