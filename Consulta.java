import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private LocalDateTime data;
    private Animal animal;
    private Veterinario veterinario;
    private String diagnostico;
    private boolean foiRetorno;
    private boolean chamado; // usado pela fila da recepcionista

    public Consulta(LocalDateTime data, Animal animal, Veterinario veterinario, String diagnostico, boolean foiRetorno) {
        this.data = data;
        this.animal = animal;
        this.veterinario = veterinario;
        this.diagnostico = diagnostico;
        this.foiRetorno = foiRetorno;
        this.chamado = false;
    }

    public void exibirConsulta() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Consulta em: " + data.format(formatter) +
                " | Animal: " + animal.getNome() +
                " | Veterinário: " + veterinario.getNome());
    }

    public void editarConsulta(String novoDiagnostico) {
        this.diagnostico = novoDiagnostico;
    }

    public void marcarRetorno(LocalDateTime novaData) {
        this.data = novaData;
        this.foiRetorno = true;
    }

    public String detalhesConsulta() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Data: " + data.format(formatter) +
                ", Diagnóstico: " + diagnostico +
                ", Retorno: " + (foiRetorno ? "Sim" : "Não");
    }

    public Animal getAnimal() {
        return animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public LocalDateTime getDataConsulta() {
        return data;
    }

    public boolean foiChamado() {
        return chamado;
    }

    public void setChamado(boolean chamado) {
        this.chamado = chamado;
    }
}
