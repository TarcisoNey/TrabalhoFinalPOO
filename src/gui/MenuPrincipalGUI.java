package gui;

import app.ACMEJogos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalGUI extends JFrame {
    private ACMEJogos sistema;

    public MenuPrincipalGUI(ACMEJogos sistema) {
        this.sistema = sistema;
        setTitle("ACME Jogos - Menu Principal");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 1));

        addBotao("1. Cadastrar Jogo", e -> new CadastrarJogoGUI(sistema));
        addBotao("2. Cadastrar Cliente", e -> new CadastrarClienteGUI(sistema));
        addBotao("3. Cadastrar Aluguel", e -> new CadastrarAluguelGUI(sistema));
        addBotao("4. Relatório de Jogos", e -> new RelatorioJogosGUI(sistema));
        addBotao("5. Relatório de Clientes", e -> new RelatorioClientesGUI(sistema));
        addBotao("6. Relatório de Aluguéis", e -> new RelatorioAlugueisGUI(sistema));
        addBotao("7. Remover Aluguel", e -> new RemoverAluguelGUI(sistema));
        addBotao("8. Alterar Cliente", e -> new AlterarClienteGUI(sistema));
        addBotao("9. Salvar Dados", e -> new SalvarDadosGUI(sistema));
        addBotao("10. Carregar Dados", e -> new CarregarDadosGUI(sistema));
        addBotao("11. Finalizar Sistema", e -> System.exit(0));

        setVisible(true);
    }

    private void addBotao(String texto, ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.addActionListener(acao);
        add(botao);
    }
}
