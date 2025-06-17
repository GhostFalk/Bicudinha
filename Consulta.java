import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Consulta {
    private LocalDateTime data;         // Data e hora da consulta
    private LocalTime hora;             // Hora separada
    private Animal animal;              // Animal atendido
    private Veterinario veterinario;    // Veterinário responsável
    private String diagnostico;
    private boolean foiRetorno;
    private String medicamento;         // Medicamento prescrito
    private boolean chamado;            // O animal já foi chamado na recepção?

    // Construtor
    public Consulta(LocalDateTime data, LocalTime hora, Animal animal, Veterinario veterinario, String diagnostico, boolean foiRetorno) {
        this.data = data;
        this.hora = hora;
        this.animal = animal;
        this.veterinario = veterinario;
        this.diagnostico = diagnostico;
        this.foiRetorno = foiRetorno;
        this.medicamento = "";
        this.chamado = false;
    }

    // Exibe os dados resumidos da consulta
    public void exibirConsulta() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Consulta em: " + data.format(formatter) +
                " | Animal: " + animal.getNome() +
                " | Veterinário: " + veterinario.getNome());
    }

    // Permite editar apenas o diagnóstico
    public void editarConsulta(String novoDiagnostico) {
        this.diagnostico = novoDiagnostico;
    }

    // Retorna detalhes resumidos da consulta
    public String detalhesConsulta() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Data: " + data.format(formatter) +
                ", Diagnóstico: " + diagnostico +
                ", Retorno: " + (foiRetorno ? "Sim" : "Não");
    }

    // Atualiza a consulta como sendo de retorno
    public void marcarRetorno(LocalDate novaData, LocalTime novaHora) {
        this.data = LocalDateTime.of(novaData, novaHora);
        this.hora = novaHora;
        this.foiRetorno = true;
    }

    // Getters
    public Animal getAnimal() { return animal; }
    public Veterinario getVeterinario() { return veterinario; }
    public LocalDateTime getDataConsulta() { return data; }
    public LocalTime getHora() { return hora; }
    public String getDiagnostico() { return diagnostico; }
    public boolean isFoiRetorno() { return foiRetorno; }
    public String getMedicamento() { return medicamento; }
    public boolean foiChamado() { return chamado; }

    // Setters
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }
    public void setChamado(boolean chamado) { this.chamado = chamado; }

    // Converte os dados da consulta em formato CSV
    public String toCSV() {
        return data.toString() + ";" +
                hora.toString() + ";" +
                animal.getNome() + ";" +
                veterinario.getCrmv() + ";" +
                diagnostico + ";" +
                foiRetorno + ";" +
                medicamento + ";" +
                chamado;
    }

    // Cria um objeto Consulta a partir de uma linha CSV
    public static Consulta fromCSV(String linha, ArrayList<Animal> listaAnimais, ArrayList<Veterinario> listaVeterinarios) {
        String[] partes = linha.split(";");
        if (partes.length < 8) return null;

        // Extrai os dados
        LocalDateTime data = LocalDateTime.parse(partes[0]);
        LocalTime hora = LocalTime.parse(partes[1]);
        String nomeAnimal = partes[2];
        String crmvVeterinario = partes[3];
        String diagnostico = partes[4];
        boolean foiRetorno = Boolean.parseBoolean(partes[5]);
        String medicamento = partes[6];
        boolean chamado = Boolean.parseBoolean(partes[7]);

        // Busca o animal
        Animal animalEncontrado = null;
        for (Animal a : listaAnimais) {
            if (a.getNome().equalsIgnoreCase(nomeAnimal)) {
                animalEncontrado = a;
                break;
            }
        }

        // Busca o veterinário pelo CRMV
        Veterinario vetEncontrado = null;
        for (Veterinario v : listaVeterinarios) {
            if (v.getCrmv().equals(crmvVeterinario)) {
                vetEncontrado = v;
                break;
            }
        }

        // Verifica se encontrou ambos
        if (animalEncontrado == null || vetEncontrado == null) {
            return null;
        }

        // Cria e retorna a consulta
        Consulta c = new Consulta(data, hora, animalEncontrado, vetEncontrado, diagnostico, foiRetorno);
        c.setMedicamento(medicamento);
        c.setChamado(chamado);
        return c;
    }

    // Para salvar com CSVUtils
    @Override
    public String toString() {
        return toCSV();
    }
}
