package gui;

import app.*;
import dados.produto.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarJogoGUI extends JFrame {
    private ACMEJogos sistema;

    private JTextField campoCodigo, campoNome, campoValorBase, campoTipo, campoExtra;

    public CadastrarJogoGUI(ACMEJogos sistema) {
        this.sistema = sistema;

        setTitle("Cadastrar Jogo");
        setSize(400, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        campoCodigo = new JTextField();
        campoNome = new JTextField();
        campoValorBase = new JTextField();
        campoTipo = new JTextField();
        campoExtra = new JTextField();

        add(new JLabel("Código:"));
        add(campoCodigo);
        add(new JLabel("Nome:"));
        add(campoNome);
        add(new JLabel("Valor Base:"));
        add(campoValorBase);
        add(new JLabel("Tipo do Jogo (1-Eletrônico, 2-Tabuleiro):"));
        add(campoTipo);
        add(new JLabel("Tipo/Plataforma ou Tipo/Número de Peças:(Separado por virgula)"));
        add(campoExtra);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarJogo();
            }
        });

        add(botaoCadastrar);
        setVisible(true);
    }

    private void cadastrarJogo() {
        try {
            int codigo = Integer.parseInt(campoCodigo.getText());
            String nome = campoNome.getText();
            double valorBase = Double.parseDouble(campoValorBase.getText());
            int tipo = Integer.parseInt(campoTipo.getText());
            String extra = campoExtra.getText();

            Jogo jogo;
            if (tipo == 1) {
                String[] partes = extra.split(",");
                jogo = new JogoEletronico(codigo, nome, valorBase, partes[0], partes[1]);
            } else {
                String[] partes = extra.split(",");
                jogo = new JogoMesa(codigo, nome, valorBase, partes[0], Integer.parseInt(partes[1]));
            }

            sistema.cadastrarJogo(jogo);
            JOptionPane.showMessageDialog(this, "Jogo cadastrado com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
