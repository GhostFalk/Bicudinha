import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends Pessoa {
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

    public ArrayList<Animal> getListaAnimais() {
        return listaAnimais;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void exibirDados() {
        System.out.println("Cliente: " + nome + " | CPF: " + cpf + " | Email: " + email);
    }
}
