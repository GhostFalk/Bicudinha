import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Pessoa {
    protected String nome;
    protected String email;
    protected String cpf;
    protected String telefone;
}

public Pessoa(String nome, String email, String cpf, String telefone) {
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.telefone = telefone;
}

public abstract void exibirDados();
}

class Cliente extends Pessoa {
    private LocalDate dataCadastro;
    private ArrayList<Animal> listaAnimais = new ArrayList<>();

    public Cliente(String nome, String email, String cpf, String telefone) {
        super(nome, email, cpf, telefone);
        this.dataCadastro = LocalDate.now();
    }

    public void adicionarAnimal(Animal animal) {
        listaAnimais.add(animal);
    }

    public void removerAnimal(String nomeAnimal) {
        listaAnimais.removeIf(a -> a.getNome().equalsIgnoreCase(nomeAnimal));
    }

    public void listarAnimais() {
        for (Animal a : listaAnimais) {
            a.exibirFicha();
        }
    }

    public void editarDados(String telefone, String email) {
        this.telefone = telefone;
        this.email = email;
    }

    @Override
    public void exibirDados() {
        System.out.println("Cliente: " + nome + " | CPF: " + cpf + " | Email: " + email);
    }
}

class Funcionario extends Pessoa {
    protected String cargo;

    public Funcionario(String nome, String email, String cpf, String telefone, String cargo) {
        super(nome, email, cpf, telefone);
        this.cargo = cargo;
    }

    public void registrarPonto() {
        System.out.println(nome + " registrou ponto como " + cargo);
    }

    @Override
    public void exibirDados() {
        System.out.println("Funcionário: " + nome + " | Cargo: " + cargo);
    }
}

class Veterinario extends Funcionario {
    private String nome;
    private String especialidade;
}
class Recepcionista extends Funcionario {
    private Boolean turno;

}