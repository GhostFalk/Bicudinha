import java.io.*;
import java.util.ArrayList;

public class PersistenciaUtils {

    /**
     * Salva uma lista de objetos serializáveis em um arquivo binário.
     *
     * @param caminho Caminho do arquivo .ser
     * @param lista   Lista de objetos a serem salvos
     * @param <T>     Tipo dos objetos
     */
    public static <T> void salvarLista(String caminho, ArrayList<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar arquivo: " + caminho);
            e.printStackTrace();
        }
    }

    /**
     * Carrega uma lista de objetos serializáveis de um arquivo binário.
     *
     * @param caminho Caminho do arquivo .ser
     * @param <T>     Tipo dos objetos
     * @return Lista carregada do arquivo, ou lista vazia se houver erro
     */
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> carregarLista(String caminho) {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            System.out.println("ℹ️ Arquivo não encontrado: " + caminho + " — retornando lista vazia.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Erro ao carregar arquivo: " + caminho);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
