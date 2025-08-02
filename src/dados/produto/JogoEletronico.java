package dados.produto;

public class JogoEletronico extends Jogo {
    private String tipo;
    private String plataforma;

    public JogoEletronico(int codigo, String nome, double valorBase, String tipo, String plataforma) {
        super(codigo, nome, valorBase);
        this.tipo = tipo;
        this.plataforma = plataforma;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    @Override
    public double calculaAluguel() {
        double percentual = 0;
        switch (tipo) {
            case "AVENTURA": percentual = 0.05; break;
            case "ESTRATEGIA": percentual = 0.15; break;
            case "SIMULACAO": percentual = 0.25; break;
        }
        return getValorBase() + (getValorBase() * percentual);
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: " + tipo + ", Plataforma: " + plataforma + ", Valor Aluguel: " + calculaAluguel();
    }
}
