package gui;

import app.ACMEJogos;
import dados.cliente.*;

import javax.swing.*;
import java.util.List;

public class RelatorioClientesGUI extends JFrame {
    public RelatorioClientesGUI(ACMEJogos sistema) {
        setTitle("Relatório de Clientes");
        setSize(400, 400);
        JTextArea areaTexto = new JTextArea();

        List<Cliente> clientes = sistema.getClientes();
        if (clientes.isEmpty()) {
            areaTexto.setText("Nenhum cliente cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Cliente c : clientes) {
                sb.append(c).append("\n");
            }
            areaTexto.setText(sb.toString());
        }

        add(new JScrollPane(areaTexto));
        setVisible(true);
    }
}
