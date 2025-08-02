package app;

import gui.*;
import dados.aluguel.*;
import dados.cliente.*;
import dados.produto.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ACMEJogos implements Serializable {
    private List<Jogo> jogos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Aluguel> alugueis = new ArrayList<>();

    public void exexutar(){
        new MenuPrincipalGUI(ACMEJogos.this);
    }

    public void inicializar(String nomeBase) {
        carregarJogosCSV(nomeBase + "JOGOSENTRADA.csv");
        carregarClientesCSV(nomeBase + "CLIENTESENTRADA.csv");
        carregarAlugueisCSV(nomeBase + "ALUGUEISENTRADA.csv");
    }

    public void cadastrarJogo(Jogo jogo) throws Exception {
        for (Jogo j : jogos) {
            if (j.getCodigo() == jogo.getCodigo()) {
                throw new Exception("Código de jogo já existente.");
            }
        }
        jogos.add(jogo);
        jogos.sort(Comparator.comparingInt(Jogo::getCodigo));
    }

    public void cadastrarCliente(Cliente cliente) throws Exception {
        for (Cliente c : clientes) {
            if (c.getNumero() == cliente.getNumero()) {
                throw new Exception("Número de cliente já existente.");
            }
        }
        clientes.add(cliente);
        clientes.sort(Comparator.comparingInt(Cliente::getNumero));
    }

    public void cadastrarAluguel(Aluguel aluguel) throws Exception {
        for (Aluguel a : alugueis) {
            if (a.getIdentificador() == aluguel.getIdentificador()) {
                throw new Exception("Identificador de aluguel já existe.");
            }
            if (a.getJogo().getCodigo() == aluguel.getJogo().getCodigo()) {
                LocalDate novaDataInicio = aluguel.getDataInicial();
                LocalDate novaDataFim = novaDataInicio.plusDays(aluguel.getPeriodo());

                LocalDate existenteInicio = a.getDataInicial();
                LocalDate existenteFim = existenteInicio.plusDays(a.getPeriodo());

                boolean sobreposicao = !(novaDataFim.isBefore(existenteInicio) || novaDataInicio.isAfter(existenteFim));
                if (sobreposicao) {
                    throw new Exception("Colisão de datas de aluguel para o mesmo jogo.");
                }
            }
        }
        alugueis.add(aluguel);
        alugueis.sort((a1, a2) -> Integer.compare(a2.getIdentificador(), a1.getIdentificador()));
    }

    public void removerAluguel(int id) throws Exception {
        Aluguel encontrado = null;
        for (Aluguel a : alugueis) {
            if (a.getIdentificador() == id) {
                encontrado = a;
                break;
            }
        }
        if (encontrado != null) {
            alugueis.remove(encontrado);
        } else {
            throw new Exception("Aluguel não encontrado.");
        }
    }

    public void alterarCliente(int numero, Cliente novoCliente) throws Exception {
        boolean encontrado = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNumero() == numero) {
                clientes.set(i, novoCliente);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new Exception("Cliente não encontrado.");
        }
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }



    public void salvarCSV(String nomeBase) throws IOException {
        salvarJogosCSV(nomeBase + "JOGOSENTRADA.csv");
        salvarClientesCSV(nomeBase + "CLIENTESENTRADA.csv");
        salvarAlugueisCSV(nomeBase + "ALUGUEISENTRADA.csv");
    }


    private void carregarJogosCSV(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            br.readLine(); // Pula cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int codigo = Integer.parseInt(partes[0]);
                String nome = partes[1];
                double valorBase = Double.parseDouble(partes[2]);
                int tipoJogo = Integer.parseInt(partes[3]);
                String tipo = partes[4];
                if (tipoJogo == 1) {
                    String plataforma = partes[5];
                    cadastrarJogo(new JogoEletronico(codigo, nome, valorBase, tipo, plataforma));
                } else {
                    int pecas = Integer.parseInt(partes[5]);
                    cadastrarJogo(new JogoMesa(codigo, nome, valorBase, tipo, pecas));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar jogos: " + e.getMessage());
        }
    }

    private void carregarClientesCSV(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int numero = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String endereco = partes[2];
                int tipoCliente = Integer.parseInt(partes[3]);
                if (tipoCliente == 1) {
                    String cpf = partes[4];
                    cadastrarCliente(new Individual(numero, nome, endereco, cpf));
                } else {
                    String nomeFantasia = partes[4];
                    String cnpj = partes[5];
                    cadastrarCliente(new Empresarial(numero, nome, endereco, nomeFantasia, cnpj));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    private void carregarAlugueisCSV(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                LocalDate data = LocalDate.parse(partes[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                int periodo = Integer.parseInt(partes[2]);
                int numCliente = Integer.parseInt(partes[3]);
                int codJogo = Integer.parseInt(partes[4]);

                Cliente cliente = null;
                for (Cliente c : clientes) {
                    if (c.getNumero() == numCliente) {
                        cliente = c;
                        break;
                    }
                }

                Jogo jogo = null;
                for (Jogo j : jogos) {
                    if (j.getCodigo() == codJogo) {
                        jogo = j;
                        break;
                    }
                }

                if (cliente != null && jogo != null) {
                    cadastrarAluguel(new Aluguel(id, data, periodo, cliente, jogo));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar alugueis: " + e.getMessage());
        }
    }

    private void salvarJogosCSV(String arquivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("codigo;nome;valorbase;tipoJogo;tipo;plataforma_numeropecas\n");
            for (Jogo jogo : jogos) {
                if (jogo instanceof JogoEletronico) {
                    JogoEletronico je = (JogoEletronico) jogo;
                    bw.write(je.getCodigo() + ";" + je.getNome() + ";" + je.getValorBase() + ";1;" +
                            je.getTipo() + ";" + je.getPlataforma() + "\n");
                } else if (jogo instanceof JogoMesa) {
                    JogoMesa jt = (JogoMesa) jogo;
                    bw.write(jt.getCodigo() + ";" + jt.getNome() + ";" + jt.getValorBase() + ";2;" +
                            jt.getTipo() + ";" + jt.getNumeroPecas() + "\n");
                }
            }
        }
    }

    private void salvarClientesCSV(String arquivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("numero;nome;endereco;tipoCliente;cpf_nomefantasia;cnpj\n");
            for (Cliente cliente : clientes) {
                if (cliente instanceof Individual) {
                    Individual ci = (Individual) cliente;
                    bw.write(ci.getNumero() + ";" + ci.getNome() + ";" + ci.getEndereco() + ";1;" +
                            ci.getCpf() + "\n");
                } else if (cliente instanceof Empresarial) {
                    Empresarial ce = (Empresarial) cliente;
                    bw.write(ce.getNumero() + ";" + ce.getNome() + ";" + ce.getEndereco() + ";2;" +
                            ce.getNomeFantasia() + ";" + ce.getCnpj() + "\n");
                }
            }
        }
    }

    private void salvarAlugueisCSV(String arquivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("identificador;datainicial;período;numero;codigo\n");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Aluguel a : alugueis) {
                bw.write(a.getIdentificador() + ";" + a.getDataInicial().format(dtf) + ";" +
                        a.getPeriodo() + ";" + a.getCliente().getNumero() + ";" + a.getJogo().getCodigo() + "\n");
            }
        }
    }
}
