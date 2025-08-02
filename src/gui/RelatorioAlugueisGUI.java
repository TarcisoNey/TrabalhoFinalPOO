package gui;

import app.ACMEJogos;
import dados.aluguel.Aluguel;

import javax.swing.*;
import java.util.List;

public class RelatorioAlugueisGUI extends JFrame {
    public RelatorioAlugueisGUI(ACMEJogos sistema) {
        setTitle("Relatório de Aluguéis");
        setSize(500, 400);
        JTextArea areaTexto = new JTextArea();

        List<Aluguel> alugueis = sistema.getAlugueis();
        if (alugueis.isEmpty()) {
            areaTexto.setText("Nenhum aluguel cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Aluguel a : alugueis) {
                sb.append(a).append("\n");
            }
            areaTexto.setText(sb.toString());
        }

        add(new JScrollPane(areaTexto));
        setVisible(true);
    }
}
