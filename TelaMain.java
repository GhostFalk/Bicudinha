import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Tela principal do sistema
public class TelaMain extends JFrame {

    // Construtor da tela principal
    public TelaMain(ArrayList<Veterinario> veterinarios, ArrayList<Recepcionista> recepcionistas,
                    ArrayList<Cliente> clientes, ArrayList<Animal> animais, ArrayList<Consulta> consultas) {

        setTitle("🐾 Tela Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);

        // Painel principal da tela
        JPanel painel = new JPanel();
        painel.setBackground(corFundo);
        painel.setLayout(new GridLayout(3, 2, 20, 20));
        painel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // Botões principais da tela
        JButton botaoVeterinario = new JButton("Área do Veterinário");
        JButton botaoRecepcao = new JButton("Área da Recepção");
        JButton botaoGerenciarVet = new JButton("Gerenciar Veterinários");
        JButton botaoGerenciarRecep = new JButton("Gerenciar Recepcionistas");
        JButton botaoCadastroFunc = new JButton("Cadastro Funcionário");
        JButton botaoSair = new JButton("Sair");

        // Adiciona os botões ao painel
        painel.add(botaoVeterinario);
        painel.add(botaoRecepcao);
        painel.add(botaoGerenciarVet);
        painel.add(botaoGerenciarRecep);
        painel.add(botaoCadastroFunc);
        painel.add(botaoSair);

        add(painel);

        // ✅ Abre a área do veterinário com listas recarregadas do CSV
        botaoVeterinario.addActionListener(e -> {
            ArrayList<Veterinario> vetsAtualizados = CSVUtils.carregarVeterinarios("veterinarios.csv");
            ArrayList<Cliente> clientesAtualizados = CSVUtils.carregarClientes("clientes.csv");
            ArrayList<Animal> animaisAtualizados = CSVUtils.carregarAnimais("animais.csv", clientesAtualizados);
            ArrayList<Consulta> consultasAtualizadas = CSVUtils.carregarConsultas("consultas.csv", animaisAtualizados, vetsAtualizados);

            new TelaMainVeterinario(animaisAtualizados, consultasAtualizadas, vetsAtualizados).setVisible(true);
        });

        // Ação: abre a área da recepção
        botaoRecepcao.addActionListener(e -> {
            ArrayList<Veterinario> vetsAtualizados = CSVUtils.carregarVeterinarios("veterinarios.csv");
            ArrayList<Recepcionista> recepAtualizados = CSVUtils.carregarRecepcionistas("recepcionistas.csv");
            ArrayList<Cliente> clientesAtualizados = CSVUtils.carregarClientes("clientes.csv");
            ArrayList<Animal> animaisAtualizados = CSVUtils.carregarAnimais("animais.csv", clientesAtualizados);
            ArrayList<Consulta> consultasAtualizadas = CSVUtils.carregarConsultas("consultas.csv", animaisAtualizados, vetsAtualizados);

            new TelaMainRecepcao(clientesAtualizados, animaisAtualizados, consultasAtualizadas, vetsAtualizados, recepAtualizados).setVisible(true);
        });

        // Ação: gerenciar veterinários
        botaoGerenciarVet.addActionListener(e -> {
            ArrayList<Veterinario> vetsAtualizados = CSVUtils.carregarVeterinarios("veterinarios.csv");
            new TelaGerenciarVeterinario(vetsAtualizados).setVisible(true);
        });

        // Ação: gerenciar recepcionistas
        botaoGerenciarRecep.addActionListener(e -> {
            ArrayList<Recepcionista> recepAtualizados = CSVUtils.carregarRecepcionistas("recepcionistas.csv");
            new TelaGerenciarRecepcionista(recepAtualizados).setVisible(true);
        });

        // Ação: cadastro de funcionário
        botaoCadastroFunc.addActionListener(e ->
                new TelaCadastroFuncionario(veterinarios, recepcionistas).setVisible(true)
        );

        // Ação: sair do sistema
        botaoSair.addActionListener(e -> dispose());
    }

    // Metodo principal da aplicação
    public static void main(String[] args) {
        // Carregamento inicial dos dados
        ArrayList<Veterinario> veterinarios = CSVUtils.carregarVeterinarios("veterinarios.csv");
        ArrayList<Recepcionista> recepcionistas = CSVUtils.carregarRecepcionistas("recepcionistas.csv");
        ArrayList<Cliente> clientes = CSVUtils.carregarClientes("clientes.csv");
        ArrayList<Animal> animais = CSVUtils.carregarAnimais("animais.csv", clientes);
        ArrayList<Consulta> consultas = CSVUtils.carregarConsultas("consultas.csv", animais, veterinarios);

        SwingUtilities.invokeLater(() -> {
            TelaMain tela = new TelaMain(veterinarios, recepcionistas, clientes, animais, consultas);
            tela.setVisible(true);
        });
    }
}
