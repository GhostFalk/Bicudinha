import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaMainVeterinario extends JFrame {

    private ArrayList<Animal> animais;
    private ArrayList<Consulta> consultas;
    private ArrayList<Veterinario> veterinarios;

    public TelaMainVeterinario(ArrayList<Animal> animais, ArrayList<Consulta> consultas, ArrayList<Veterinario> veterinarios) {
        this.animais = animais;
        this.consultas = consultas;
        this.veterinarios = veterinarios;

        setTitle("🐾 Área do Veterinário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);
        JPanel painel = new JPanel(new GridLayout(2, 1, 20, 20));
        painel.setBackground(corFundo);
        painel.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));

        JButton botaoGerenciarAnimais = new JButton("Gerenciar Animais");
        JButton botaoGerenciarConsultas = new JButton("Gerenciar Consultas");

        painel.add(botaoGerenciarAnimais);
        painel.add(botaoGerenciarConsultas);
        add(painel);

        botaoGerenciarAnimais.addActionListener(e -> {
            ArrayList<Animal> animaisAtualizados = PersistenciaUtils.carregarLista("animais.ser");
            if (animaisAtualizados.isEmpty()) {
                ArrayList<Cliente> clientes = PersistenciaUtils.carregarLista("clientes.ser");
                if (clientes.isEmpty()) clientes = CSVUtils.carregarClientes("clientes.csv");
                animaisAtualizados = CSVUtils.carregarAnimais("animais.csv", clientes);
            }
            new TelaGerenciarAnimais(animaisAtualizados).setVisible(true);
        });

        botaoGerenciarConsultas.addActionListener(e -> {
            ArrayList<Animal> animaisAtualizados = PersistenciaUtils.carregarLista("animais.ser");
            if (animaisAtualizados.isEmpty()) {
                ArrayList<Cliente> clientes = PersistenciaUtils.carregarLista("clientes.ser");
                if (clientes.isEmpty()) clientes = CSVUtils.carregarClientes("clientes.csv");
                animaisAtualizados = CSVUtils.carregarAnimais("animais.csv", clientes);
            }

            ArrayList<Veterinario> vetsAtualizados = PersistenciaUtils.carregarLista("veterinarios.ser");
            if (vetsAtualizados.isEmpty()) vetsAtualizados = CSVUtils.carregarVeterinarios("veterinarios.csv");

            ArrayList<Consulta> consultasAtualizadas = PersistenciaUtils.carregarLista("consultas.ser");
            if (consultasAtualizadas.isEmpty())
                consultasAtualizadas = CSVUtils.carregarConsultas("consultas.csv", animaisAtualizados, vetsAtualizados);

            new TelaGerenciarConsulta(consultasAtualizadas).setVisible(true);
        });
    }
}
