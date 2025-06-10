import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class TelaCadastroConsulta extends JFrame {
    private JComboBox<String> comboAnimal, comboVeterinario;
    private JTextArea campoDiagnostico;
    private JCheckBox checkRetorno;
    private JLabel campoData;
    private ArrayList<Consulta> consultas = new ArrayList<>();

    private ArrayList<Animal> animais = new ArrayList<>();
    private ArrayList<Veterinario> veterinarios = new ArrayList<>();

    public TelaCadastroConsulta() {
        setTitle("🐾 Cadastro de Consulta");
        setSize(800, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoCadastrar = new Color(145, 205, 144);
        Color corBotaoLimpar = new Color(237, 115, 110);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        JLabel labelImagem = new JLabel(new ImageIcon(new ImageIcon("gatinho.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(labelImagem);

        JLabel titulo = new JLabel("Cadastro de Consulta");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setForeground(new Color(65, 52, 40));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setMaximumSize(new Dimension(600, 450));
        painelFormulario.setBackground(corFormulario);
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        Font fonteCampos = new Font("Comic Sans MS", Font.PLAIN, 18);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dados simulados
        veterinarios.add(new Veterinario("Dra. Lúcia", "lucia@vet.com", "123", "123", "CRMV123", "Clínico"));
        Cliente donoFake = new Cliente("Carlos", "carlos@email.com", "999.888.777-66", "(11) 99999-8888");
        animais.add(new Animal("Bidu", "Cachorro", 3, "Vira-lata", "Masculino", 12.0, donoFake));

        comboAnimal = new JComboBox<>();
        comboVeterinario = new JComboBox<>();
        for (Animal a : animais) comboAnimal.addItem(a.getNome());
        for (Veterinario v : veterinarios) comboVeterinario.addItem(v.getNome());
        comboAnimal.setFont(fonteCampos);
        comboVeterinario.setFont(fonteCampos);
        comboAnimal.setPreferredSize(new Dimension(300, 30));
        comboVeterinario.setPreferredSize(new Dimension(300, 30));

        campoDiagnostico = new JTextArea();
        campoDiagnostico.setFont(fonteCampos);
        campoDiagnostico.setLineWrap(true);
        campoDiagnostico.setWrapStyleWord(true);
        campoDiagnostico.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        campoDiagnostico.setDocument(new JTextFieldLimit(120));
        campoDiagnostico.setPreferredSize(new Dimension(300, 120));

        campoData = new JLabel(LocalDate.now().toString());
        campoData.setFont(fonteCampos);

        checkRetorno = new JCheckBox("Foi retorno?");
        checkRetorno.setFont(fonteCampos);
        checkRetorno.setBackground(corFormulario);

        int y = 0;

        gbc.gridx = 0; gbc.gridy = y;
        painelFormulario.add(new JLabel("Animal:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(comboAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        painelFormulario.add(new JLabel("Veterinário:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(comboVeterinario, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        painelFormulario.add(new JLabel("Data da consulta:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoData, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        painelFormulario.add(new JLabel("Diagnóstico:"), gbc);
        gbc.gridx = 1;

        JPanel painelDiagnostico = new JPanel(new BorderLayout());
        painelDiagnostico.setBackground(corFormulario);
        painelDiagnostico.setPreferredSize(new Dimension(300, 130));
        painelDiagnostico.add(campoDiagnostico, BorderLayout.CENTER);
        painelFormulario.add(painelDiagnostico, gbc);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = ++y;
        painelFormulario.add(new JLabel("Retorno:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(checkRetorno, gbc);

        painelPrincipal.add(painelFormulario);

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoLimpar = new JButton("Limpar");

        botaoCadastrar.setBackground(corBotaoCadastrar);
        botaoLimpar.setBackground(corBotaoLimpar);
        botaoCadastrar.setForeground(Color.WHITE);
        botaoLimpar.setForeground(Color.WHITE);
        botaoCadastrar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoLimpar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(Box.createHorizontalStrut(20));
        painelBotoes.add(botaoLimpar);

        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelBotoes);

        JPanel painelCachorro = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelCachorro.setBackground(corFundo);
        painelCachorro.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        painelCachorro.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        JPanel painelHamster = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelHamster.setBackground(corFundo);
        painelHamster.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
        painelHamster.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(painelHamster, BorderLayout.WEST);
        painelInferior.add(painelCachorro, BorderLayout.EAST);

        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        botaoCadastrar.addActionListener((ActionEvent e) -> {
            String nomeAnimal = (String) comboAnimal.getSelectedItem();
            String nomeVet = (String) comboVeterinario.getSelectedItem();
            Animal animalSel = animais.stream().filter(a -> a.getNome().equals(nomeAnimal)).findFirst().orElse(null);
            Veterinario vetSel = veterinarios.stream().filter(v -> v.getNome().equals(nomeVet)).findFirst().orElse(null);

            if (animalSel != null && vetSel != null) {
                LocalDate data = LocalDate.now();
                LocalDateTime dataHora = data.atTime(LocalTime.of(9, 0));
                Consulta nova = new Consulta(dataHora, animalSel, vetSel, campoDiagnostico.getText(), checkRetorno.isSelected());
                consultas.add(nova);
                JOptionPane.showMessageDialog(this, "Consulta cadastrada com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao localizar animal ou veterinário.");
            }
        });

        botaoLimpar.addActionListener(e -> limparCampos());
    }

    private void limparCampos() {
        campoDiagnostico.setText("");
        comboAnimal.setSelectedIndex(0);
        comboVeterinario.setSelectedIndex(0);
        checkRetorno.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroConsulta().setVisible(true));
    }
}
