import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.*;
import java.util.*;
import java.util.List;

public class TelaCadastroConsulta extends JFrame {
    private JComboBox<String> comboAnimal, comboVeterinario;
    private JTextArea campoDiagnostico;
    private JCheckBox checkRetorno;
    private JSpinner spinnerData, spinnerHora;
    private List<Consulta> consultas = new ArrayList<>();

    private List<Animal> animais;
    private List<Veterinario> veterinarios;

    public TelaCadastroConsulta(ArrayList<Animal> animais, ArrayList<Veterinario> veterinarios) {
        this.animais = animais;
        this.veterinarios = veterinarios;
        this.consultas = new ArrayList<>();

        // Carrega as consultas do CSV com tratamento de erro
        try {
            for (String linha : CSVUtils.carregarCSV("consultas.csv")) {
                Consulta c = Consulta.fromCSV(linha, animais, veterinarios);
                if (c != null) consultas.add(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar consultas salvas: " + e.getMessage());
        }

        setTitle("🐾 Cadastro de Consulta");
        setSize(800, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoCadastrar = new Color(145, 205, 144);
        Color corBotaoLimpar = new Color(237, 115, 110);
        Color corBotaoGerenciar = new Color(100, 150, 255);

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

        Font fonteCampos = new Font("Comic Sans MS", Font.PLAIN, 18);

        comboAnimal = new JComboBox<>();
        comboVeterinario = new JComboBox<>();
        for (Animal a : animais) comboAnimal.addItem(a.getNome());
        for (Veterinario v : veterinarios) comboVeterinario.addItem(v.getNome());
        comboAnimal.setFont(fonteCampos);
        comboVeterinario.setFont(fonteCampos);

        SpinnerDateModel modelData = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinnerData = new JSpinner(modelData);
        spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
        spinnerData.setFont(fonteCampos);

        SpinnerDateModel modelHora = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
        spinnerHora = new JSpinner(modelHora);
        spinnerHora.setEditor(new JSpinner.DateEditor(spinnerHora, "HH:mm"));
        spinnerHora.setFont(fonteCampos);

        campoDiagnostico = new JTextArea();
        campoDiagnostico.setFont(fonteCampos);
        campoDiagnostico.setLineWrap(true);
        campoDiagnostico.setWrapStyleWord(true);
        campoDiagnostico.setDocument(new JTextFieldLimit(120));
        JScrollPane scrollDiagnostico = new JScrollPane(campoDiagnostico);
        scrollDiagnostico.setPreferredSize(new Dimension(300, 100));

        checkRetorno = new JCheckBox("Foi retorno?");
        checkRetorno.setFont(fonteCampos);
        checkRetorno.setBackground(corFormulario);

        JPanel painelFormulario = new JPanel(new GridLayout(7, 2, 10, 8));
        painelFormulario.setMaximumSize(new Dimension(600, 320));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        painelFormulario.add(new JLabel("Animal:")); painelFormulario.add(comboAnimal);
        painelFormulario.add(new JLabel("Veterinário:")); painelFormulario.add(comboVeterinario);
        painelFormulario.add(new JLabel("Data da consulta:")); painelFormulario.add(spinnerData);
        painelFormulario.add(new JLabel("Horário da consulta:")); painelFormulario.add(spinnerHora);
        painelFormulario.add(new JLabel("Diagnóstico:")); painelFormulario.add(scrollDiagnostico);
        painelFormulario.add(new JLabel("Retorno:")); painelFormulario.add(checkRetorno);

        painelPrincipal.add(Box.createVerticalStrut(5));
        painelPrincipal.add(painelFormulario);

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoLimpar = new JButton("Limpar");
        JButton botaoGerenciar = new JButton("Gerenciar Consultas");

        botaoCadastrar.setBackground(corBotaoCadastrar);
        botaoLimpar.setBackground(corBotaoLimpar);
        botaoGerenciar.setBackground(corBotaoGerenciar);
        botaoCadastrar.setForeground(Color.WHITE);
        botaoLimpar.setForeground(Color.WHITE);
        botaoGerenciar.setForeground(Color.WHITE);
        botaoCadastrar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoLimpar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoGerenciar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(Box.createHorizontalStrut(20));
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(Box.createHorizontalStrut(20));
        painelBotoes.add(botaoGerenciar);

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
            try {
                String nomeAnimal = (String) comboAnimal.getSelectedItem();
                String nomeVet = (String) comboVeterinario.getSelectedItem();

                Animal animalSel = animais.stream().filter(a -> a.getNome().equals(nomeAnimal)).findFirst().orElse(null);
                Veterinario vetSel = veterinarios.stream().filter(v -> v.getNome().equals(nomeVet)).findFirst().orElse(null);

                Date dataSelecionada = (Date) spinnerData.getValue();
                Date horaSelecionada = (Date) spinnerHora.getValue();
                LocalDate data = dataSelecionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalTime hora = horaSelecionada.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                LocalDateTime dataHora = LocalDateTime.of(data, hora);

                if (animalSel != null && vetSel != null) {
                    Consulta nova = new Consulta(dataHora, hora, animalSel, vetSel, campoDiagnostico.getText(), checkRetorno.isSelected());
                    consultas.add(nova);
                    CSVUtils.salvarCSV("consultas.csv", (ArrayList<Consulta>) consultas);
                    JOptionPane.showMessageDialog(this, "Consulta cadastrada com sucesso!");
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: animal ou veterinário não encontrado.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        botaoLimpar.addActionListener(e -> limparCampos());

        botaoGerenciar.addActionListener(e -> {
            TelaGerenciarConsulta tela = new TelaGerenciarConsulta((ArrayList<Consulta>) consultas);
            tela.setVisible(true);
        });
    }

    private void limparCampos() {
        campoDiagnostico.setText("");
        comboAnimal.setSelectedIndex(0);
        comboVeterinario.setSelectedIndex(0);
        checkRetorno.setSelected(false);
        spinnerData.setValue(new Date());
        spinnerHora.setValue(new Date());
    }
}
