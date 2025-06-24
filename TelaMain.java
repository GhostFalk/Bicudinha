import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaMain extends JFrame {

    public TelaMain(ArrayList<Veterinario> veterinarios, ArrayList<Recepcionista> recepcionistas,
                    ArrayList<Cliente> clientes, ArrayList<Animal> animais, ArrayList<Consulta> consultas) {

        setTitle("🐾 Tela Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);
        JPanel painel = new JPanel();
        painel.setBackground(corFundo);
        painel.setLayout(new GridLayout(3, 2, 20, 20));
        painel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JButton botaoVeterinario = new JButton("Área do Veterinário");
        JButton botaoRecepcao = new JButton("Área da Recepção");
        JButton botaoGerenciarVet = new JButton("Gerenciar Veterinários");
        JButton botaoGerenciarRecep = new JButton("Gerenciar Recepcionistas");
        JButton botaoCadastroFunc = new JButton("Cadastro Funcionário");
        JButton botaoSair = new JButton("Sair");

        painel.add(botaoVeterinario);
        painel.add(botaoRecepcao);
        painel.add(botaoGerenciarVet);
        painel.add(botaoGerenciarRecep);
        painel.add(botaoCadastroFunc);
        painel.add(botaoSair);
        add(painel);

        botaoVeterinario.addActionListener(e -> {
            ArrayList<Veterinario> vetsAtualizados = CSVUtils.carregarVeterinarios("veterinarios.csv");
            ArrayList<Cliente> clientesAtualizados = CSVUtils.carregarClientes("clientes.csv");
            ArrayList<Animal> animaisAtualizados = CSVUtils.carregarAnimais("animais.csv", clientesAtualizados);
            ArrayList<Consulta> consultasAtualizadas = CSVUtils.carregarConsultas("consultas.csv", animaisAtualizados, vetsAtualizados);

            PersistenciaUtils.salvarLista("veterinarios.ser", vetsAtualizados);
            PersistenciaUtils.salvarLista("clientes.ser", clientesAtualizados);
            PersistenciaUtils.salvarLista("animais.ser", animaisAtualizados);
            PersistenciaUtils.salvarLista("consultas.ser", consultasAtualizadas);

            new TelaMainVeterinario(animaisAtualizados, consultasAtualizadas, vetsAtualizados).setVisible(true);
        });

        botaoRecepcao.addActionListener(e -> {
            ArrayList<Veterinario> vetsAtualizados = CSVUtils.carregarVeterinarios("veterinarios.csv");
            ArrayList<Recepcionista> recepAtualizados = CSVUtils.carregarRecepcionistas("recepcionistas.csv");
            ArrayList<Cliente> clientesAtualizados = CSVUtils.carregarClientes("clientes.csv");
            ArrayList<Animal> animaisAtualizados = CSVUtils.carregarAnimais("animais.csv", clientesAtualizados);
            ArrayList<Consulta> consultasAtualizadas = CSVUtils.carregarConsultas("consultas.csv", animaisAtualizados, vetsAtualizados);

            PersistenciaUtils.salvarLista("veterinarios.ser", vetsAtualizados);
            PersistenciaUtils.salvarLista("recepcionistas.ser", recepAtualizados);
            PersistenciaUtils.salvarLista("clientes.ser", clientesAtualizados);
            PersistenciaUtils.salvarLista("animais.ser", animaisAtualizados);
            PersistenciaUtils.salvarLista("consultas.ser", consultasAtualizadas);

            new TelaMainRecepcao(clientesAtualizados, animaisAtualizados, consultasAtualizadas, vetsAtualizados, recepAtualizados).setVisible(true);
        });

        botaoGerenciarVet.addActionListener(e -> {
            ArrayList<Veterinario> vetsAtualizados = CSVUtils.carregarVeterinarios("veterinarios.csv");
            PersistenciaUtils.salvarLista("veterinarios.ser", vetsAtualizados);
            new TelaGerenciarVeterinario(vetsAtualizados).setVisible(true);
        });

        botaoGerenciarRecep.addActionListener(e -> {
            ArrayList<Recepcionista> recepAtualizados = CSVUtils.carregarRecepcionistas("recepcionistas.csv");
            PersistenciaUtils.salvarLista("recepcionistas.ser", recepAtualizados);
            new TelaGerenciarRecepcionista(recepAtualizados).setVisible(true);
        });

        botaoCadastroFunc.addActionListener(e -> new TelaCadastroFuncionario(veterinarios, recepcionistas).setVisible(true));
        botaoSair.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        final ArrayList<Veterinario> veterinarios = PersistenciaUtils.carregarLista("veterinarios.ser");
        if (veterinarios.isEmpty()) veterinarios.addAll(CSVUtils.carregarVeterinarios("veterinarios.csv"));

        final ArrayList<Recepcionista> recepcionistas = PersistenciaUtils.carregarLista("recepcionistas.ser");
        if (recepcionistas.isEmpty()) recepcionistas.addAll(CSVUtils.carregarRecepcionistas("recepcionistas.csv"));

        final ArrayList<Cliente> clientes = PersistenciaUtils.carregarLista("clientes.ser");
        if (clientes.isEmpty()) clientes.addAll(CSVUtils.carregarClientes("clientes.csv"));

        final ArrayList<Animal> animais = PersistenciaUtils.carregarLista("animais.ser");
        if (animais.isEmpty()) animais.addAll(CSVUtils.carregarAnimais("animais.csv", clientes));

        final ArrayList<Consulta> consultas = PersistenciaUtils.carregarLista("consultas.ser");
        if (consultas.isEmpty()) consultas.addAll(CSVUtils.carregarConsultas("consultas.csv", animais, veterinarios));

        SwingUtilities.invokeLater(() ->
                new TelaMain(veterinarios, recepcionistas, clientes, animais, consultas).setVisible(true)
        );
    }
}
