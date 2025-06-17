import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaMainVeterinario extends JFrame {

    // Armazena as listas recebidas no construtor
    private ArrayList<Animal> animais;
    private ArrayList<Consulta> consultas;
    private ArrayList<Veterinario> veterinarios;

    // Construtor
    public TelaMainVeterinario(ArrayList<Animal> animais, ArrayList<Consulta> consultas, ArrayList<Veterinario> veterinarios) {
        this.animais = animais;
        this.consultas = consultas;
        this.veterinarios = veterinarios;

        // Configuração da janela
        setTitle("🐾 Área do Veterinário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Cores e layout
        Color corFundo = new Color(106, 207, 207);
        JPanel painel = new JPanel(new GridLayout(2, 1, 20, 20));
        painel.setBackground(corFundo);
        painel.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));

        // Botões
        JButton botaoGerenciarAnimais = new JButton("Gerenciar Animais");
        JButton botaoGerenciarConsultas = new JButton("Gerenciar Consultas");

        painel.add(botaoGerenciarAnimais);
        painel.add(botaoGerenciarConsultas);
        add(painel);

        // Usa as listas já passadas, sem recarregar CSV
        botaoGerenciarAnimais.addActionListener(e ->
                new TelaGerenciarAnimais(animais).setVisible(true)
        );

        botaoGerenciarConsultas.addActionListener(e -> {
            // Atualiza apenas as consultas usando as listas em memória
            ArrayList<Consulta> consultasAtualizadas = CSVUtils.carregarConsultas("consultas.csv", animais, veterinarios);
            new TelaGerenciarConsulta(consultasAtualizadas).setVisible(true);
        });
    }
}
