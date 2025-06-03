import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Pessoa {
    protected String nome;
    protected String email;
    protected String cpf;
    protected String telefone;

    public Pessoa(String nome, String email, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public abstract void exibirDados();
}

// ---------------------- Cliente ----------------------

// ---------------------- Funcionario ----------------------

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

// ---------------------- Veterinario ----------------------

class Veterinario extends Funcionario {
    private String crmv;
    private String especialidade;

    public Veterinario(String nome, String email, String cpf, String telefone, String crmv, String especialidade) {
        super(nome, email, cpf, telefone, "Veterinário");
        this.crmv = crmv;
        this.especialidade = especialidade;
    }

    @Override
    public void exibirDados() {
        System.out.println("Veterinário: " + nome + " | CRMV: " + crmv + " | Especialidade: " + especialidade);
    }

    public void realizarConsulta(Animal animal) {
        System.out.println("Consulta realizada com " + animal.getNome());
    }

    public void prescreverTratamento(Animal animal, String descricao) {
        System.out.println("Tratamento para " + animal.getNome() + ": " + descricao);
    }
}

// ---------------------- Recepcionista ----------------------

class Recepcionista extends Funcionario {
    private boolean turno;

    public Recepcionista(String nome, String email, String cpf, String telefone, boolean turno) {
        super(nome, email, cpf, telefone, "Recepcionista");
        this.turno = turno;
    }

    @Override
    public void exibirDados() {
        String turnoStr = turno ? "Manhã" : "Tarde";
        System.out.println("Recepcionista: " + nome + " | Turno: " + turnoStr);
    }

    public void agendarConsulta(Consulta consulta) {
        System.out.println("Consulta agendada para " + consulta.detalhesConsulta());
    }

    public void chamarCliente() {
        System.out.println("Próximo cliente, por favor!");
    }
}
