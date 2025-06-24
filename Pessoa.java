import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

// ---------------------- Pessoa (classe abstrata base) ----------------------
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public abstract void exibirDados();
}

// ---------------------- Funcionario ----------------------
class Funcionario extends Pessoa {
    private static final long serialVersionUID = 2L;

    protected String cargo;

    public Funcionario(String nome, String email, String cpf, String telefone, String cargo) {
        super(nome, email, cpf, telefone);
        this.cargo = cargo;
    }

    public String getTipo() { return cargo; }
    public void setTipo(String tipo) { this.cargo = tipo; }

    @Override
    public void exibirDados() {
        System.out.println("Funcionário: " + nome + " | Cargo: " + cargo);
    }
}

// ---------------------- Veterinario ----------------------
class Veterinario extends Funcionario {
    private static final long serialVersionUID = 3L;

    private String crmv;
    private String especialidade;

    public Veterinario(String nome, String email, String cpf, String telefone, String crmv, String especialidade) {
        super(nome, email, cpf, telefone, "Veterinário");
        this.crmv = crmv;
        this.especialidade = especialidade;
    }

    public String getCrmv() { return crmv; }
    public void setCrmv(String crmv) { this.crmv = crmv; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

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

    public String toCSV() {
        return nome + ";" + email + ";" + cpf + ";" + telefone + ";" + crmv + ";" + especialidade;
    }

    public static Veterinario fromCSV(String linha) {
        String[] partes = linha.split(";");
        if (partes.length < 6) return null;
        return new Veterinario(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
    }

    @Override
    public String toString() {
        return toCSV();
    }
}

// ---------------------- Recepcionista ----------------------
class Recepcionista extends Funcionario {
    private static final long serialVersionUID = 4L;

    private boolean turno; // true = manhã, false = tarde

    public Recepcionista(String nome, String email, String cpf, String telefone, boolean turno) {
        super(nome, email, cpf, telefone, "Recepcionista");
        this.turno = turno;
    }

    public String getTurno() {
        return turno ? "Manhã" : "Tarde";
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    @Override
    public void exibirDados() {
        System.out.println("Recepcionista: " + nome + " | Turno: " + (turno ? "Manhã" : "Tarde"));
    }

    public void agendarConsulta(Consulta consulta) {
        System.out.println("Consulta agendada para " + consulta.detalhesConsulta());
    }

    public void chamarCliente() {
        System.out.println("Próximo cliente, por favor!");
    }

    public String toCSV() {
        return nome + ";" + email + ";" + cpf + ";" + telefone + ";" + turno;
    }

    public static Recepcionista fromCSV(String linha) {
        String[] partes = linha.split(";");
        boolean turno = Boolean.parseBoolean(partes[4]);
        return new Recepcionista(partes[0], partes[1], partes[2], partes[3], turno);
    }

    @Override
    public String toString() {
        return toCSV();
    }
}
