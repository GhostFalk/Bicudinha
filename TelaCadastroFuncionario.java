import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class TelaCadastroFuncionario extends JFrame {
    // Campos de entrada de texto
    private JTextField campoNome, campoEmail, campoCRMV;
    private JFormattedTextField campoCPF, campoTelefone;

    // Comboboxes e painel
    private JComboBox<String> campoEspecialidade, comboCargo, comboTurno;
    private JPanel painelFormulario;

    // Labels para campos dinâmicos
    private JLabel labelCRMV, labelEsp, labelTurno;

    // Constraints para posicionamento no GridBagLayout
    private GridBagConstraints gbc;

    // Listas
    private ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

    // Listas externas compartilhadas
    private ArrayList<Veterinario> listaVeterinariosExternos;
    private ArrayList<Recepcionista> listaRecepcionistasExternos;

    // Tamanho e fonte
    private final Dimension TAMANHO_CAMPO = new Dimension(300, 30);
    private final Font fonte = new Font("Comic Sans MS", Font.PLAIN, 18);

    // Construtor
    public TelaCadastroFuncionario() {
        initComponents();
    }

    // Construtor com listas externas
    public TelaCadastroFuncionario(ArrayList<Veterinario> veterinarios, ArrayList<Recepcionista> recepcionistas) {
        this(); // Chama o construtor padrão
        this.listaVeterinariosExternos = veterinarios;
        this.listaRecepcionistasExternos = recepcionistas;
    }

    // Metodo que monta a interface gráfica
    private void initComponents() {
        setTitle("🐾 Cadastro de Funcionário");
        setSize(800, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Cores
        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoCadastrar = new Color(145, 205, 144);
        Color corBotaoLimpar = new Color(237, 115, 110);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        // Imagem
        JLabel labelImagem = new JLabel(new ImageIcon(new ImageIcon("gatinho.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(labelImagem);

        // Título
        JLabel titulo = new JLabel("Cadastro de Funcionário");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setForeground(new Color(65, 52, 40));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        // Painel de formulário com layout em grade
        painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(corFormulario);
        painelFormulario.setMaximumSize(new Dimension(600, 500));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Configurações do grid
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Criação dos campos
        campoNome = new JTextField();
        campoEmail = new JTextField();

        // ✅ Máscara para CPF e Telefone
        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            campoCPF = new JFormattedTextField(cpfMask);

            MaskFormatter telMask = new MaskFormatter("(##) #####-####");
            telMask.setPlaceholderCharacter('_');
            campoTelefone = new JFormattedTextField(telMask);
        } catch (ParseException e) {
            campoCPF = new JFormattedTextField();
            campoTelefone = new JFormattedTextField();
        }

        // Combobox para cargo
        comboCargo = new JComboBox<>(new String[]{"-- Selecione --", "Recepcionista", "Veterinário"});

        // Campo CRMV com limite de 6 caracteres
        campoCRMV = new JTextField();
        campoCRMV.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (str == null) return;
                if ((getLength() + str.length()) <= 6) {
                    super.insertString(offs, str, a);
                }
            }
        });

        // Combobox para especialidade
        campoEspecialidade = new JComboBox<>(new String[]{
                "-- Selecione --", "Clínica Geral", "Cirurgia Geral", "Dermatologia",
                "Odontologia", "Oftalmologia", "Atendimento a Exóticos"
        });

        // Campo específico para recepcionista
        comboTurno = new JComboBox<>(new String[]{"Manhã", "Tarde"});

        // Adiciona campos básicos ao painel
        addCampo("Nome:", campoNome, 0);
        addCampo("Email:", campoEmail, 1);
        addCampo("CPF:", campoCPF, 2);
        addCampo("Telefone:", campoTelefone, 3);
        addCampo("Cargo:", comboCargo, 4);

        // Labels extras
        labelCRMV = new JLabel("CRMV:");
        labelEsp = new JLabel("Especialidade:");
        labelTurno = new JLabel("Turno:");

        // Configura visual e adiciona ao painel (mas ocultos)
        configurarLabel(labelCRMV); configurarLabel(labelEsp); configurarLabel(labelTurno);
        configurarCampo(campoCRMV); configurarCampo(campoEspecialidade); configurarCampo(comboTurno);

        adicionarCampoExtra(labelCRMV, campoCRMV, 5);
        adicionarCampoExtra(labelEsp, campoEspecialidade, 6);
        adicionarCampoExtra(labelTurno, comboTurno, 5);

        // Inicialmente, campos extras estão invisíveis
        labelCRMV.setVisible(false); campoCRMV.setVisible(false);
        labelEsp.setVisible(false); campoEspecialidade.setVisible(false);
        labelTurno.setVisible(false); comboTurno.setVisible(false);

        painelPrincipal.add(painelFormulario);
        comboCargo.addActionListener(e -> atualizarCamposExtras());

        // Criação dos botões
        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoLimpar = new JButton("Limpar");

        // Estilo dos botões
        botaoCadastrar.setBackground(corBotaoCadastrar);
        botaoLimpar.setBackground(corBotaoLimpar);
        botaoCadastrar.setForeground(Color.WHITE);
        botaoLimpar.setForeground(Color.WHITE);
        botaoCadastrar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoLimpar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        // Painel com os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(Box.createHorizontalStrut(20));
        painelBotoes.add(botaoLimpar);

        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelBotoes);

        // Painéis com imagens decorativas
        JPanel painelCachorro = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelCachorro.setBackground(corFundo);
        painelCachorro.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        JPanel painelHamster = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelHamster.setBackground(corFundo);
        painelHamster.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(painelHamster, BorderLayout.WEST);
        painelInferior.add(painelCachorro, BorderLayout.EAST);

        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        // Ações dos botões
        botaoCadastrar.addActionListener(this::cadastrarFuncionario);
        botaoLimpar.addActionListener(e -> limparCampos());
    }

    // Metodo para adicionar campos ao formulário
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

    // Configura estilo dos labels
    private void configurarLabel(JLabel label) {
        label.setFont(fonte);
    }

    // Configura estilo dos campos
    private void configurarCampo(JComponent campo) {
        campo.setPreferredSize(TAMANHO_CAMPO);
        campo.setFont(fonte);
    }

    // Adiciona campo extra ao formulário
    private void adicionarCampoExtra(JLabel label, JComponent campo, int linha) {
        gbc.gridx = 0; gbc.gridy = linha;
        painelFormulario.add(label, gbc);
        gbc.gridx = 1;
        painelFormulario.add(campo, gbc);
    }

    // Mostra ou esconde campos conforme cargo selecionado
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

    // Ação do botão "Cadastrar"
    private void cadastrarFuncionario(ActionEvent e) {
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String cpf = campoCPF.getText();
        String telefone = campoTelefone.getText();
        String cargo = (String) comboCargo.getSelectedItem();

        if (nome.isEmpty() || email.isEmpty() || cpf.contains("_") || telefone.contains("_") || "-- Selecione --".equals(cargo)) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
            return;
        }

        Funcionario funcionario;

        if ("Veterinário".equals(cargo)) {
            String crmv = campoCRMV.getText();
            String especialidade = (String) campoEspecialidade.getSelectedItem();
            if (crmv.isEmpty() || crmv.length() != 6 || "-- Selecione --".equals(especialidade)) {
                JOptionPane.showMessageDialog(this, "Preencha CRMV com 6 dígitos e selecione uma especialidade.");
                return;
            }

            Veterinario vet = new Veterinario(nome, email, cpf, telefone, crmv, especialidade);
            funcionario = vet;
            if (listaVeterinariosExternos != null) listaVeterinariosExternos.add(vet);
            CSVUtils.salvarCSV("veterinarios.csv", new ArrayList<>(listaVeterinariosExternos));
        } else {
            boolean turno = comboTurno.getSelectedIndex() == 0;
            Recepcionista recep = new Recepcionista(nome, email, cpf, telefone, turno);
            funcionario = recep;
            if (listaRecepcionistasExternos != null) listaRecepcionistasExternos.add(recep);
            CSVUtils.salvarCSV("recepcionistas.csv", new ArrayList<>(listaRecepcionistasExternos));
        }

        listaFuncionarios.add(funcionario);
        JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
        limparCampos();
    }

    // Limpa todos os campos da tela
    private void limparCampos() {
        campoNome.setText("");
        campoEmail.setText("");
        campoCPF.setValue(null);
        campoTelefone.setValue(null);
        campoCRMV.setText("");
        comboCargo.setSelectedIndex(0);
        campoEspecialidade.setSelectedIndex(0);
        comboTurno.setSelectedIndex(0);
        atualizarCamposExtras();
    }
}
