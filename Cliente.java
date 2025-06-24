import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

// Classe Cliente herda da classe Pessoa e permite persistência
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate dataCadastro; // Data de cadastro
    private ArrayList<Animal> listaAnimais = new ArrayList<>(); // Relação com animais

    // Construtor padrão
    public Cliente(String nome, String email, String cpf, String telefone) {
        super(nome, email, cpf, telefone);
        this.dataCadastro = LocalDate.now();
    }

    // Construtor com data de cadastro (usado ao carregar CSV)
    public Cliente(String nome, String email, String cpf, String telefone, LocalDate dataCadastro) {
        super(nome, email, cpf, telefone);
        this.dataCadastro = dataCadastro;
    }

    // Adiciona um animal ao cliente
    public void adicionarAnimal(Animal animal) {
        listaAnimais.add(animal);
    }

    // Remove animal por nome
    public void removerAnimal(String nomeAnimal) {
        listaAnimais.removeIf(a -> a.getNome().equalsIgnoreCase(nomeAnimal));
    }

    // Lista todos os animais do cliente
    public void listarAnimais() {
        for (Animal a : listaAnimais) {
            a.exibirFicha();
        }
    }

    // Edita email e telefone
    public void editarDados(String telefone, String email) {
        this.telefone = telefone;
        this.email = email;
    }

    // Getters
    public ArrayList<Animal> getListaAnimais() {
        return listaAnimais;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    // Exibe os dados no console
    @Override
    public void exibirDados() {
        System.out.println("Cliente: " + nome + " | CPF: " + cpf + " | Email: " + email);
    }

    // Exporta os dados para CSV
    public String toCSV() {
        return nome + ";" + email + ";" + cpf + ";" + telefone + ";" + dataCadastro;
    }

    // Constrói cliente a partir do CSV
    public static Cliente fromCSV(String linha) {
        String[] partes = linha.split(";");
        String nome = partes[0];
        String email = partes[1];
        String cpf = partes[2];
        String telefone = partes[3];
        LocalDate dataCadastro = LocalDate.parse(partes[4]);
        return new Cliente(nome, email, cpf, telefone, dataCadastro);
    }

    // Representação em texto
    @Override
    public String toString() {
        return toCSV();
    }
}
