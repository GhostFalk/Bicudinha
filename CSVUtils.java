import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CSVUtils {

    // ------------------------ SALVAR ------------------------

    // Salva uma lista de objetos genéricos no CSV
    public static <T> void salvarCSV(String caminho, ArrayList<T> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminho))) {
            for (T item : lista) {
                if (item instanceof Recepcionista) {
                    pw.println(((Recepcionista) item).toCSV());
                } else {
                    pw.println(item.toString()); // Usa toCSV() se estiver implementado
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar o arquivo CSV: " + caminho);
            e.printStackTrace();
        }
    }

    // ------------------------ CARREGAR LINHAS ------------------------

    // Carrega as linhas puras de um CSV como Strings
    public static ArrayList<String> carregarCSV(String caminho) {
        ArrayList<String> linhas = new ArrayList<>();
        File arquivo = new File(caminho);
        if (!arquivo.exists()) return linhas;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler o arquivo CSV: " + caminho);
        }
        return linhas;
    }

    // ------------------------ CONSULTAS ------------------------

    public static ArrayList<Consulta> carregarConsultas(String caminho, ArrayList<Animal> listaAnimais, ArrayList<Veterinario> listaVeterinarios) {
        ArrayList<Consulta> consultas = new ArrayList<>();

        for (String linha : carregarCSV(caminho)) {
            try {
                String[] partes = linha.split(";");
                if (partes.length >= 7) {
                    LocalDateTime data = LocalDateTime.parse(partes[0]);
                    LocalTime hora = LocalTime.parse(partes[1]);
                    String nomeAnimal = partes[2];
                    String crmvVeterinario = partes[3];
                    String diagnostico = partes[4];
                    boolean foiRetorno = Boolean.parseBoolean(partes[5]);
                    String medicamento = partes[6];

                    // Busca animal por nome (e dono, se necessário no futuro)
                    Animal animal = listaAnimais.stream()
                            .filter(a -> a.getNome().equalsIgnoreCase(nomeAnimal))
                            .findFirst()
                            .orElse(null);

                    // Busca veterinário por CRMV
                    Veterinario vet = listaVeterinarios.stream()
                            .filter(v -> v.getCrmv().equals(crmvVeterinario))
                            .findFirst()
                            .orElse(null);

                    if (animal == null || vet == null) {
                        System.out.println("⚠️ Consulta ignorada: animal ou veterinário não encontrado.");
                        continue;
                    }

                    Consulta consulta = new Consulta(data, hora, animal, vet, diagnostico, foiRetorno);
                    consulta.setMedicamento(medicamento);
                    consultas.add(consulta);
                }
            } catch (Exception e) {
                System.out.println("❌ Erro ao carregar uma linha de consulta: " + e.getMessage());
            }
        }

        return consultas;
    }

    // ------------------------ VETERINÁRIOS ------------------------

    public static ArrayList<Veterinario> carregarVeterinarios(String caminho) {
        ArrayList<Veterinario> lista = new ArrayList<>();
        for (String linha : carregarCSV(caminho)) {
            Veterinario v = Veterinario.fromCSV(linha);
            if (v != null) lista.add(v);
        }
        return lista;
    }

    // ------------------------ RECEPCIONISTAS ------------------------

    public static ArrayList<Recepcionista> carregarRecepcionistas(String caminho) {
        ArrayList<Recepcionista> lista = new ArrayList<>();
        for (String linha : carregarCSV(caminho)) {
            Recepcionista r = Recepcionista.fromCSV(linha);
            if (r != null) lista.add(r);
        }
        return lista;
    }

    // ------------------------ CLIENTES ------------------------

    public static ArrayList<Cliente> carregarClientes(String caminho) {
        ArrayList<Cliente> lista = new ArrayList<>();
        for (String linha : carregarCSV(caminho)) {
            Cliente c = Cliente.fromCSV(linha);
            if (c != null) lista.add(c);
        }
        return lista;
    }

    // ------------------------ ANIMAIS ------------------------

    public static ArrayList<Animal> carregarAnimais(String caminho, ArrayList<Cliente> clientes) {
        ArrayList<Animal> lista = new ArrayList<>();
        for (String linha : carregarCSV(caminho)) {
            Animal a = Animal.fromCSV(linha, clientes);
            if (a != null) lista.add(a);
        }
        return lista;
    }
}
