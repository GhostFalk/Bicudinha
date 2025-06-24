import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaMainRecepcao extends JFrame {

    public TelaMainRecepcao(ArrayList<Cliente> clientes, ArrayList<Animal> animais, ArrayList<Consulta> consultas,
                            ArrayList<Veterinario> veterinarios, ArrayList<Recepcionista> recepcionistas) {

        setTitle("🐾 Área da Recepção");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);

        JPanel painel = new JPanel();
        painel.setBackground(corFundo);
        painel.setLayout(new GridLayout(4, 2, 15, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(50, 70, 50, 70));

        JButton btnCadastroAnimal = new JButton("Cadastrar Animal");
        JButton btnCadastroConsulta = new JButton("Cadastrar Consulta");
        JButton btnCadastroCliente = new JButton("Cadastrar Cliente");
        JButton btnGerenciarCliente = new JButton("Gerenciar Cliente");
        JButton btnRecepcionista = new JButton("Painel Recepção");
        JButton btnListarFuncionarios = new JButton("📋 Ver todos funcionários");
        JButton btnVoltar = new JButton("Voltar");

        painel.add(btnCadastroAnimal);
        painel.add(btnCadastroConsulta);
        painel.add(btnCadastroCliente);
        painel.add(btnGerenciarCliente);
        painel.add(btnRecepcionista);
        painel.add(btnListarFuncionarios);
        painel.add(btnVoltar);

        add(painel);

        // AÇÕES

        btnCadastroAnimal.addActionListener(e -> {
            ArrayList<Cliente> clientesAtualizados = PersistenciaUtils.carregarLista("clientes.ser");
            if (clientesAtualizados.isEmpty()) clientesAtualizados = CSVUtils.carregarClientes("clientes.csv");

            ArrayList<Animal> animaisAtualizados = PersistenciaUtils.carregarLista("animais.ser");
            if (animaisAtualizados.isEmpty()) animaisAtualizados = CSVUtils.carregarAnimais("animais.csv", clientesAtualizados);

            new TelaCadastroAnimal(animaisAtualizados, clientesAtualizados).setVisible(true);
        });

        btnCadastroConsulta.addActionListener(e -> {
            ArrayList<Animal> animaisAtualizados = PersistenciaUtils.carregarLista("animais.ser");
            if (animaisAtualizados.isEmpty()) animaisAtualizados = CSVUtils.carregarAnimais("animais.csv", clientes);

            ArrayList<Veterinario> vetsAtualizados = PersistenciaUtils.carregarLista("veterinarios.ser");
            if (vetsAtualizados.isEmpty()) vetsAtualizados = CSVUtils.carregarVeterinarios("veterinarios.csv");

            new TelaCadastroConsulta(animaisAtualizados, vetsAtualizados).setVisible(true);
        });

        btnCadastroCliente.addActionListener(e -> {
            ArrayList<Cliente> clientesAtualizados = PersistenciaUtils.carregarLista("clientes.ser");
            if (clientesAtualizados.isEmpty()) clientesAtualizados = CSVUtils.carregarClientes("clientes.csv");

            new TelaCadastroCliente(clientesAtualizados).setVisible(true);
        });

        btnGerenciarCliente.addActionListener(e -> {
            ArrayList<Cliente> clientesAtualizados = PersistenciaUtils.carregarLista("clientes.ser");
            if (clientesAtualizados.isEmpty()) clientesAtualizados = CSVUtils.carregarClientes("clientes.csv");

            new TelaGerenciarClientes(clientesAtualizados).setVisible(true);
        });

        btnRecepcionista.addActionListener(e -> {
            ArrayList<Recepcionista> recepAtualizadas = PersistenciaUtils.carregarLista("recepcionistas.ser");
            if (recepAtualizadas.isEmpty()) recepAtualizadas = CSVUtils.carregarRecepcionistas("recepcionistas.csv");

            ArrayList<Veterinario> vetsAtualizados = PersistenciaUtils.carregarLista("veterinarios.ser");
            if (vetsAtualizados.isEmpty()) vetsAtualizados = CSVUtils.carregarVeterinarios("veterinarios.csv");

            ArrayList<Animal> animaisAtualizados = PersistenciaUtils.carregarLista("animais.ser");
            if (animaisAtualizados.isEmpty()) animaisAtualizados = CSVUtils.carregarAnimais("animais.csv", clientes);

            if (!recepAtualizadas.isEmpty()) {
                Recepcionista r = recepAtualizadas.get(0);
                new TelaRecepcionista(r, animaisAtualizados, vetsAtualizados).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma recepcionista cadastrada.");
            }
        });

        btnListarFuncionarios.addActionListener(e -> {
            ArrayList<Veterinario> vets = PersistenciaUtils.carregarLista("veterinarios.ser");
            if (vets.isEmpty()) vets = CSVUtils.carregarVeterinarios("veterinarios.csv");

            ArrayList<Recepcionista> recep = PersistenciaUtils.carregarLista("recepcionistas.ser");
            if (recep.isEmpty()) recep = CSVUtils.carregarRecepcionistas("recepcionistas.csv");

            ArrayList<Pessoa> todos = new ArrayList<>();
            todos.addAll(vets);
            todos.addAll(recep);

            StringBuilder sb = new StringBuilder("Funcionários:\n\n");
            for (Pessoa p : todos) {
                p.exibirDados();
                sb.append("- ").append(p.getClass().getSimpleName()).append(": ").append(p.getNome()).append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        btnVoltar.addActionListener(e -> dispose());
    }
}
