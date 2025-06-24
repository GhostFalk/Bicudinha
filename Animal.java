import java.io.Serializable;
import java.util.ArrayList;

// Classe que representa um animal da clínica
public class Animal implements Serializable {
    private static final long serialVersionUID = 1L;

    // Atributos
    private String nome;
    private String especie;
    private int idade;
    private String raca;
    private String sexo;
    private double peso;
    private Cliente dono; // Associação com a classe Cliente
    private boolean vacinado;

    // Construtor
    public Animal(String nome, String especie, int idade, String raca, String sexo, double peso, Cliente dono, boolean vacinado) {
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.raca = raca;
        this.sexo = sexo;
        this.peso = peso;
        this.dono = dono;
        this.vacinado = vacinado;
    }

    // Exibe os dados do animal
    public void exibirFicha() {
        System.out.println("Animal: " + nome +
                ", Espécie: " + especie +
                ", Raça: " + raca +
                ", Sexo: " + sexo +
                ", Peso: " + peso + "kg" +
                ", Vacinado: " + (vacinado ? "Sim" : "Não"));
    }

    // Marca o animal como vacinado
    public void vacinar() {
        if (!vacinado) {
            vacinado = true;
            System.out.println(nome + " foi vacinado.");
        } else {
            System.out.println(nome + " já está vacinado.");
        }
    }

    // Atualiza o peso
    public void atualizarPeso(double novoPeso) {
        this.peso = novoPeso;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public boolean isVacinado() {
        return vacinado;
    }

    public void setVacinado(boolean vacinado) {
        this.vacinado = vacinado;
    }

    // Retorna os dados no formato CSV
    public String toCSV() {
        return nome + ";" + especie + ";" + idade + ";" + raca + ";" + sexo + ";" + peso + ";" + vacinado + ";" +
                (dono != null ? dono.getCpf() : "sem_dono");
    }

    // Cria um objeto Animal a partir de uma linha CSV e uma lista de clientes
    public static Animal fromCSV(String linha, ArrayList<Cliente> listaClientes) {
        String[] partes = linha.split(";");
        String nome = partes[0];
        String especie = partes[1];
        int idade = Integer.parseInt(partes[2]);
        String raca = partes[3];
        String sexo = partes[4];
        double peso = Double.parseDouble(partes[5]);
        boolean vacinado = Boolean.parseBoolean(partes[6]);
        String cpfDono = partes[7];

        // Busca o cliente correspondente pelo CPF
        Cliente donoEncontrado = null;
        for (Cliente c : listaClientes) {
            if (c.getCpf().equals(cpfDono)) {
                donoEncontrado = c;
                break;
            }
        }

        return new Animal(nome, especie, idade, raca, sexo, peso, donoEncontrado, vacinado);
    }

    // toString sobrescrito para retornar o mesmo formato do CSV
    @Override
    public String toString() {
        return toCSV();
    }
}
