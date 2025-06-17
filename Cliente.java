import java.time.LocalDate;
import java.util.ArrayList;

// Classe Cliente herda da classe Pessoa
public class Cliente extends Pessoa {
    private LocalDate dataCadastro; // Data em que o cliente foi cadastrado
    private ArrayList<Animal> listaAnimais = new ArrayList<>();

    // Metodo Construtor
    public Cliente(String nome, String email, String cpf, String telefone) {
        super(nome, email, cpf, telefone);
        this.dataCadastro = LocalDate.now(); // data atual
    }

    // Construtor com data personalizada (usado ao carregar CSV)
    public Cliente(String nome, String email, String cpf, String telefone, LocalDate dataCadastro) {
        super(nome, email, cpf, telefone);
        this.dataCadastro = dataCadastro;
    }

    // Adiciona um animal a lista do cliente
    public void adicionarAnimal(Animal animal) {
        listaAnimais.add(animal);
    }

    // Remove animal pelo nome
    public void removerAnimal(String nomeAnimal) {
        listaAnimais.removeIf(a -> a.getNome().equalsIgnoreCase(nomeAnimal));
    }

    // Lista todos os animais do cliente
    public void listarAnimais() {
        for (Animal a : listaAnimais) {
            a.exibirFicha();
        }
    }

    // Permite atualizar telefone e email do cliente
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

    // Sobrescreve metodo da classe Pessoa para exibir dados específicos do cliente
    @Override
    public void exibirDados() {
        System.out.println("Cliente: " + nome + " | CPF: " + cpf + " | Email: " + email);
    }

    // Exporta os dados do cliente como linha CSV
    public String toCSV() {
        return nome + ";" + email + ";" + cpf + ";" + telefone + ";" + dataCadastro;
    }

    // Cria cliente a partir de linha CSV
    public static Cliente fromCSV(String linha) {
        String[] partes = linha.split(";");
        String nome = partes[0];
        String email = partes[1];
        String cpf = partes[2];
        String telefone = partes[3];
        LocalDate dataCadastro = LocalDate.parse(partes[4]);
        return new Cliente(nome, email, cpf, telefone, dataCadastro);
    }

    @Override
    public String toString() {
        return toCSV();
    }
}
