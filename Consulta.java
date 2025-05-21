import java.time.LocalDate;

public class Consulta {
    private LocalDate data;
    private Animal animal;
    private Veterinario veterinario;
    private String diagnostico;
    private boolean foiRetorno;

    public Consulta(LocalDate data, Animal animal, Veterinario veterinario, String diagnostico, boolean foiRetorno) {
        this.data = data;
        this.animal = animal;
        this.veterinario = veterinario;
        this.diagnostico = diagnostico;
        this.foiRetorno = foiRetorno;
    }

    public void exibirConsulta() {
        System.out.println("Consulta em: " + data + " | Animal: " + animal.getNome() +
                " | Veterinário: " + veterinario.getNome());
    }

    public void editarConsulta(String novoDiagnostico) {
        this.diagnostico = novoDiagnostico;
    }

    public void marcarRetorno(LocalDate novaData) {
        this.data = novaData;
        this.foiRetorno = true;
    }

    public String detalhesConsulta() {
        return "Data: " + data + ", Diagnóstico: " + diagnostico + ", Retorno: " + (foiRetorno ? "Sim" : "Não");
    }

    public Animal getAnimal() {
        return animal;
    }
}