package gui;

import app.ACMEJogos;
import dados.produto.*;

import javax.swing.*;
import java.util.List;

public class RelatorioJogosGUI extends JFrame {
    public RelatorioJogosGUI(ACMEJogos sistema) {
        setTitle("Relatório de Jogos");
        setSize(400, 400);
        JTextArea areaTexto = new JTextArea();

        List<Jogo> jogos = sistema.getJogos();
        if (jogos.isEmpty()) {
            areaTexto.setText("Nenhum jogo cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Jogo j : jogos) {
                sb.append(j).append("\n");
            }
            areaTexto.setText(sb.toString());
        }

        add(new JScrollPane(areaTexto));
        setVisible(true);
    }
}
