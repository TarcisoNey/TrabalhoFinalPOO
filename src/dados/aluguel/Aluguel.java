package dados.aluguel;
import dados.cliente.*;
import dados.produto.*;

import java.io.Serializable;
import java.time.LocalDate;

public class Aluguel implements Serializable {
    private int identificador;
    private LocalDate dataInicial;
    private int periodo;
    private Cliente cliente;
    private Jogo jogo;

    public Aluguel(int identificador, LocalDate dataInicial, int periodo, Cliente cliente, Jogo jogo) {
        this.identificador = identificador;
        this.dataInicial = dataInicial;
        this.periodo = periodo;
        this.cliente = cliente;
        this.jogo = jogo;
    }

    public int getIdentificador() {
        return identificador;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public int getPeriodo() {
        return periodo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public double calculaValorFinal() {
        double valorAluguel = jogo.calculaAluguel();
        double valorFinal = 0;

        if (cliente instanceof Individual) {
            if (periodo < 7) {
                valorFinal = 0.9 * valorAluguel * periodo;
            } else if (periodo <= 14) {
                valorFinal = 0.8 * valorAluguel * periodo;
            } else {
                valorFinal = 0.75 * valorAluguel * periodo;
            }
        } else if (cliente instanceof Empresarial) {
            if (jogo instanceof JogoEletronico) {
                valorFinal = valorAluguel * periodo;
            } else if (jogo instanceof JogoMesa) {
                valorFinal = 0.85 * valorAluguel * periodo;
            }
        }

        return valorFinal;
    }

    @Override
    public String toString() {
        return "ID: " + identificador + ", Data Inicial: " + dataInicial + ", Período: " + periodo +
                ", Cliente: " + cliente.getNome() + ", Jogo: " + jogo.getNome() + ", Valor Final: " + calculaValorFinal();
    }
}

