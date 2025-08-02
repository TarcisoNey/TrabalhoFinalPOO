package dados.produto;

import java.io.Serializable;

public abstract class Jogo implements Serializable {
    private int codigo;
    private String nome;
    private double valorBase;

    public Jogo(int codigo, String nome, double valorBase) {
        this.codigo = codigo;
        this.nome = nome;
        this.valorBase = valorBase;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getValorBase() {
        return valorBase;
    }

    public abstract double calculaAluguel();

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nome: " + nome + ", Valor Base: " + valorBase;
    }
}
