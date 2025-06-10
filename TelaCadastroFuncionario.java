import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TelaCadastroFuncionario extends JFrame {
    private JTextField campoNome, campoEmail, campoCPF, campoTelefone, campoCRMV;
    private JComboBox<String> campoEspecialidade, comboCargo, comboTurno;
    private JPanel painelFormulario;
    private JLabel labelCRMV, labelEsp, labelTurno;
    private GridBagConstraints gbc;
    private ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    private final Dimension TAMANHO_CAMPO = new Dimension(300, 30);
    private final Font fonte = new Font("Comic Sans MS", Font.PLAIN, 18);

    public TelaCadastroFuncionario() {
        setTitle("🐾 Cadastro de Funcionário");
        setSize(800, 750);
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

        JLabel titulo = new JLabel("Cadastro de Funcionário");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setForeground(new Color(65, 52, 40));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(corFormulario);
        painelFormulario.setMaximumSize(new Dimension(600, 500));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        campoNome = new JTextField(); campoEmail = new JTextField();
        campoCPF = new JTextField(); campoTelefone = new JTextField();
        campoTelefone.setDocument(new JTextFieldLimit(15));
        comboCargo = new JComboBox<>(new String[]{"-- Selecione --", "Recepcionista", "Veterinário"});
        campoCRMV = new JTextField();
        campoEspecialidade = new JComboBox<>(new String[]{
                "-- Selecione --",
                "Clínica Geral",
                "Cirurgia Geral",
                "Dermatologia",
                "Odontologia",
                "Oftalmologia",
                "Atendimento a Exóticos"
        });
        comboTurno = new JComboBox<>(new String[]{"Manhã", "Tarde"});

        addCampo("Nome:", campoNome, 0);
        addCampo("Email:", campoEmail, 1);
        addCampo("CPF:", campoCPF, 2);
        addCampo("Telefone:", campoTelefone, 3);
        addCampo("Cargo:", comboCargo, 4);

        labelCRMV = new JLabel("CRMV:");
        labelEsp = new JLabel("Especialidade:");
        labelTurno = new JLabel("Turno:");

        configurarLabel(labelCRMV); configurarLabel(labelEsp); configurarLabel(labelTurno);
        configurarCampo(campoCRMV); configurarCampo(campoEspecialidade); configurarCampo(comboTurno);

        adicionarCampoExtra(labelCRMV, campoCRMV, 5);
        adicionarCampoExtra(labelEsp, campoEspecialidade, 6);
        adicionarCampoExtra(labelTurno, comboTurno, 5);

        labelCRMV.setVisible(false); campoCRMV.setVisible(false);
        labelEsp.setVisible(false); campoEspecialidade.setVisible(false);
        labelTurno.setVisible(false); comboTurno.setVisible(false);

        painelPrincipal.add(painelFormulario);
        comboCargo.addActionListener(e -> atualizarCamposExtras());

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
        painelCachorro.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));
        painelCachorro.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        JPanel painelHamster = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelHamster.setBackground(corFundo);
        painelHamster.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));
        painelHamster.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(painelHamster, BorderLayout.WEST);
        painelInferior.add(painelCachorro, BorderLayout.EAST);

        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        botaoCadastrar.addActionListener(this::cadastrarFuncionario);
        botaoLimpar.addActionListener(e -> limparCampos());
    }

    private void addCampo(String texto, JComponent campo, int linha) {
        JLabel label = new JLabel(texto);
        label.setFont(fonte);
        campo.setPreferredSize(TAMANHO_CAMPO);
        campo.setFont(fonte);
        gbc.gridx = 0; gbc.gridy = linha;
        painelFormulario.add(label, gbc);
        gbc.gridx = 1;
        painelFormulario.add(campo, gbc);
    }

    private void configurarLabel(JLabel label) {
        label.setFont(fonte);
    }

    private void configurarCampo(JComponent campo) {
        campo.setPreferredSize(TAMANHO_CAMPO);
        campo.setFont(fonte);
    }

    private void adicionarCampoExtra(JLabel label, JComponent campo, int linha) {
        gbc.gridx = 0; gbc.gridy = linha;
        painelFormulario.add(label, gbc);
        gbc.gridx = 1;
        painelFormulario.add(campo, gbc);
    }

    private void atualizarCamposExtras() {
        String cargo = (String) comboCargo.getSelectedItem();
        boolean isVet = "Veterinário".equals(cargo);
        boolean isRec = "Recepcionista".equals(cargo);

        labelCRMV.setVisible(isVet);
        campoCRMV.setVisible(isVet);
        labelEsp.setVisible(isVet);
        campoEspecialidade.setVisible(isVet);

        labelTurno.setVisible(isRec);
        comboTurno.setVisible(isRec);

        painelFormulario.revalidate();
        painelFormulario.repaint();
    }

    private void cadastrarFuncionario(ActionEvent e) {
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String cpf = campoCPF.getText();
        String telefone = campoTelefone.getText();
        String cargo = (String) comboCargo.getSelectedItem();

        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || "-- Selecione --".equals(cargo)) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
            return;
        }

        Funcionario funcionario;
        if ("Veterinário".equals(cargo)) {
            String crmv = campoCRMV.getText();
            String especialidade = (String) campoEspecialidade.getSelectedItem();
            if (crmv.isEmpty() || "-- Selecione --".equals(especialidade)) {
                JOptionPane.showMessageDialog(this, "Preencha CRMV e selecione uma especialidade.");
                return;
            }
            funcionario = new Veterinario(nome, email, cpf, telefone, crmv, especialidade);
        } else {
            boolean turno = comboTurno.getSelectedIndex() == 0;
            funcionario = new Recepcionista(nome, email, cpf, telefone, turno);
        }

        listaFuncionarios.add(funcionario);
        JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
        limparCampos();
    }

    private void limparCampos() {
        campoNome.setText("");
        campoEmail.setText("");
        campoCPF.setText("");
        campoTelefone.setText("");
        campoCRMV.setText("");
        comboCargo.setSelectedIndex(0);
        campoEspecialidade.setSelectedIndex(0);
        comboTurno.setSelectedIndex(0);
        atualizarCamposExtras();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroFuncionario().setVisible(true));
    }
}
