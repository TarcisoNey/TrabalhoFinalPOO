package dados.produto;

public class JogoMesa extends Jogo {
    private String tipo;
    private int numeroPecas;

    public JogoMesa(int codigo, String nome, double valorBase, String tipo, int numeroPecas) {
        super(codigo, nome, valorBase);
        this.tipo = tipo;
        this.numeroPecas = numeroPecas;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumeroPecas() {
        return numeroPecas;
    }

    @Override
    public double calculaAluguel() {

        if (tipo.equalsIgnoreCase("TABULEIRO")) {
            return getValorBase() * numeroPecas;
        } else if (tipo.equalsIgnoreCase("CARTAS")) {
            return getValorBase() * 1.2;
        } else {
            return getValorBase();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: " + tipo + ", Número de Peças: " + numeroPecas + ", Valor Aluguel: " + calculaAluguel();
    }
}
