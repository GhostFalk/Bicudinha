import java.time.LocalDate;
import java.util.ArrayList;

// ---------------------- Pessoa (classe abstrata base) ----------------------
public abstract class Pessoa {
    protected String nome;
    protected String email;
    protected String cpf;
    protected String telefone;

    // Construtor
    public Pessoa(String nome, String email, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Metodo abstrato que deve ser implementado pelas subclasses
    public abstract void exibirDados();
}

// ---------------------- Funcionario ----------------------
class Funcionario extends Pessoa {
    protected String cargo;

    //Metodo Construtor Funcionário
    public Funcionario(String nome, String email, String cpf, String telefone, String cargo) {
        super(nome, email, cpf, telefone);
        this.cargo = cargo;
    }

    //Metodo Get e Set
    public String getTipo() {
        return cargo;
    }

    public void setTipo(String tipo) {
        this.cargo = tipo;
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

    //Metodo Contrutor
    public Veterinario(String nome, String email, String cpf, String telefone, String crmv, String especialidade) {
        super(nome, email, cpf, telefone, "Veterinário");
        this.crmv = crmv;
        this.especialidade = especialidade;
    }

    //Getters e Setters
    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
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

    // Converte os dados do veterinário para formato CSV
    public String toCSV() {
        return nome + ";" + email + ";" + cpf + ";" + telefone + ";" + crmv + ";" + especialidade;
    }

    // Cria veterinário a partir de linha CSV
    public static Veterinario fromCSV(String linha) {
        String[] partes = linha.split(";");
        if (partes.length < 6) return null;
        return new Veterinario(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
    }

    // Usa toCSV para salvar em arquivos
    @Override
    public String toString() {
        return toCSV();
    }
}

// ---------------------- Recepcionista ----------------------
class Recepcionista extends Funcionario {
    private boolean turno; // true = manhã, false = tarde

    //Metodo Contrututor Recepcionista
    public Recepcionista(String nome, String email, String cpf, String telefone, boolean turno) {
        super(nome, email, cpf, telefone, "Recepcionista");
        this.turno = turno;
    }

    //Get e Set

    public String getTurno() {
        return turno ? "Manhã" : "Tarde";
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    @Override
    public void exibirDados() {
        String turnoStr = turno ? "Manhã" : "Tarde";
        System.out.println("Recepcionista: " + getNome() + " | Turno: " + turnoStr);
    }

    public void agendarConsulta(Consulta consulta) {
        System.out.println("Consulta agendada para " + consulta.detalhesConsulta());
    }

    public void chamarCliente() {
        System.out.println("Próximo cliente, por favor!");
    }

    // Converte recepcionista em formato CSV
    public String toCSV() {
        return nome + ";" + email + ";" + cpf + ";" + telefone + ";" + (turno ? "true" : "false");
    }

    // Cria recepcionista a partir de CSV
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
