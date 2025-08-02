package dados.cliente;

import java.io.Serializable;

public abstract class Cliente implements Serializable {
    private int numero;
    private String nome;
    private String endereco;

    public Cliente(int numero, String nome, String endereco) {
        this.numero = numero;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "Número: " + numero + ", Nome: " + nome + ", Endereço: " + endereco;
    }
}
