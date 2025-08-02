package dados.cliente;

public class Individual extends Cliente {
    private String cpf;

    public Individual(int numero, String nome, String endereco, String cpf) {
        super(numero, nome, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: Individual, CPF: " + cpf;
    }
}

