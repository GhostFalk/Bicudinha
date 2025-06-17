import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CSVUtils {

    // Salva uma lista de objetos genéricos no CSV
    public static <T> void salvarCSV(String caminho, ArrayList<T> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminho))) {
            for (T item : lista) {
                if (item instanceof Recepcionista) {
                    pw.println(((Recepcionista) item).toCSV());
                } else {
                    pw.println(item.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo CSV: " + caminho);
            e.printStackTrace();
        }
    }

    // Carrega as linhas puras de um CSV como Strings
    public static ArrayList<String> carregarCSV(String caminho) {
        ArrayList<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo CSV: " + caminho);
        }
        return linhas;
    }

    // Carrega a lista de consultas
    public static ArrayList<Consulta> carregarConsultas(String caminho, ArrayList<Animal> listaAnimais, ArrayList<Veterinario> listaVeterinarios) {
        ArrayList<Consulta> consultas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 7) {
                    LocalDateTime data = LocalDateTime.parse(partes[0]);
                    LocalTime hora = LocalTime.parse(partes[1]);
                    String nomeAnimal = partes[2];
                    String crmvVeterinario = partes[3];
                    String diagnostico = partes[4];
                    boolean foiRetorno = Boolean.parseBoolean(partes[5]);
                    String medicamento = partes[6];

                    // Busca o animal
                    Animal animal = listaAnimais.stream()
                            .filter(a -> a.getNome().equals(nomeAnimal))
                            .findFirst()
                            .orElse(new Animal(nomeAnimal, "", 0, "", "", 0, null, false));

                    // Busca o veterinário
                    Veterinario veterinario = listaVeterinarios.stream()
                            .filter(v -> v.getCrmv().equals(crmvVeterinario))
                            .findFirst()
                            .orElse(new Veterinario("Desconhecido", "", "", "", crmvVeterinario, ""));

                    Consulta consulta = new Consulta(data, hora, animal, veterinario, diagnostico, foiRetorno);
                    consulta.setMedicamento(medicamento);
                    consultas.add(consulta);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar consultas: " + e.getMessage());
            e.printStackTrace();
        }

        return consultas;
    }

    // Carrega veterinários do CSV
    public static ArrayList<Veterinario> carregarVeterinarios(String caminho) {
        ArrayList<Veterinario> lista = new ArrayList<>();
        for (String linha : carregarCSV(caminho)) {
            lista.add(Veterinario.fromCSV(linha));
        }
        return lista;
    }

    // Carrega recepcionistas do CSV
    public static ArrayList<Recepcionista> carregarRecepcionistas(String caminho) {
        ArrayList<Recepcionista> lista = new ArrayList<>();
        for (String linha : carregarCSV(caminho)) {
            lista.add(Recepcionista.fromCSV(linha));
        }
        return lista;
    }

    // Carrega clientes do CSV
    public static ArrayList<Cliente> carregarClientes(String caminho) {
        ArrayList<Cliente> lista = new ArrayList<>();
        for (String linha : carregarCSV(caminho)) {
            lista.add(Cliente.fromCSV(linha));
        }
        return lista;
    }

    // Carrega animais do CSV e associa ao dono
    public static ArrayList<Animal> carregarAnimais(String caminho, ArrayList<Cliente> clientes) {
        ArrayList<Animal> lista = new ArrayList<>();
        for (String linha : carregarCSV(caminho)) {
            lista.add(Animal.fromCSV(linha, clientes));
        }
        return lista;
    }
}

