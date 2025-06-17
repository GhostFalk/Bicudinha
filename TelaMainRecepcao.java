import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Tela principal da recepção com acesso às funcionalidades da recepcionista
public class TelaMainRecepcao extends JFrame {

    // Construtor da tela principal da recepção
    public TelaMainRecepcao(ArrayList<Cliente> clientes, ArrayList<Animal> animais, ArrayList<Consulta> consultas,
                            ArrayList<Veterinario> veterinarios, ArrayList<Recepcionista> recepcionistas) {

        setTitle("🐾 Área da Recepção");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);

        // Painel principal da tela
        JPanel painel = new JPanel();
        painel.setBackground(corFundo);
        painel.setLayout(new GridLayout(3, 2, 15, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(50, 70, 50, 70)); // Margens internas

        // Criação dos botões
        JButton btnCadastroAnimal = new JButton("Cadastrar Animal");
        JButton btnCadastroConsulta = new JButton("Cadastrar Consulta");
        JButton btnCadastroCliente = new JButton("Cadastrar Cliente");
        JButton btnGerenciarCliente = new JButton("Gerenciar Cliente");
        JButton btnRecepcionista = new JButton("Painel Recepção");
        JButton btnVoltar = new JButton("Voltar");

        // Adiciona os botões ao painel
        painel.add(btnCadastroAnimal);
        painel.add(btnCadastroConsulta);
        painel.add(btnCadastroCliente);
        painel.add(btnGerenciarCliente);
        painel.add(btnRecepcionista);
        painel.add(btnVoltar);

        // Adiciona o painel à janela
        add(painel);

        // Ações dos botões:

        // Abre a tela para cadastrar um novo animal
        btnCadastroAnimal.addActionListener(e -> new TelaCadastroAnimal(animais, clientes).setVisible(true));

        // Abre a tela para cadastrar uma nova consulta
        btnCadastroConsulta.addActionListener(e -> new TelaCadastroConsulta(animais, veterinarios).setVisible(true));

        // Abre a tela para cadastrar um novo cliente
        btnCadastroCliente.addActionListener(e -> new TelaCadastroCliente(clientes).setVisible(true));

        // Abre a tela para gerenciar clientes
        btnGerenciarCliente.addActionListener(e -> new TelaGerenciarClientes(clientes).setVisible(true));

        // Usa uma recepcionista real da lista para abrir o painel
        btnRecepcionista.addActionListener(e -> {
            if (!recepcionistas.isEmpty()) {
                Recepcionista r = recepcionistas.get(0); // Pega a primeira da lista
                new TelaRecepcionista(r, animais, veterinarios).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma recepcionista cadastrada.");
            }
        });

        // Fecha essa janela e volta para a anterior
        btnVoltar.addActionListener(e -> dispose());
    }
}
