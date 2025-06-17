import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class TelaCadastroCliente extends JFrame {

    // Campos de entrada
    private JTextField campoNome, campoEmail;
    private JFormattedTextField campoCpf, campoTelefone;

    // Lista de clientes
    private ArrayList<Cliente> listaClientes;

    // Construtor da tela
    public TelaCadastroCliente(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;

        // Configurações da janela
        setTitle("🐾 Cadastro de Cliente");
        setSize(800, 700);
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
        ImageIcon imagemTopo = new ImageIcon("gatinho.png");
        Image imgGato = imagemTopo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(imgGato));
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelImagem.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        painelPrincipal.add(labelImagem);

        // Título da tela
        JLabel titulo = new JLabel("Cadastro de Cliente");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setForeground(new Color(65, 52, 40));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        // Painel do formulário
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 8));
        painelFormulario.setMaximumSize(new Dimension(600, 200));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        Font fonteCampos = new Font("Comic Sans MS", Font.PLAIN, 18);

        // Campos de texto
        campoNome = new JTextField(); campoNome.setFont(fonteCampos);
        campoEmail = new JTextField(); campoEmail.setFont(fonteCampos);

        try {
            // Máscara para CPF
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            campoCpf = new JFormattedTextField(mascaraCpf);
            campoCpf.setFont(fonteCampos);

            // Máscara para Telefone
            MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
            mascaraTelefone.setPlaceholderCharacter('_');
            campoTelefone = new JFormattedTextField(mascaraTelefone);
            campoTelefone.setFont(fonteCampos);
        } catch (ParseException e) {
            // Se der erro, cria campos vazios como fallback
            campoCpf = new JFormattedTextField();
            campoTelefone = new JFormattedTextField();
        }

        // Adiciona os campos no painel
        painelFormulario.add(new JLabel("Nome:"));      painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("Email:"));     painelFormulario.add(campoEmail);
        painelFormulario.add(new JLabel("CPF:"));       painelFormulario.add(campoCpf);
        painelFormulario.add(new JLabel("Telefone:"));  painelFormulario.add(campoTelefone);

        painelPrincipal.add(Box.createVerticalStrut(5)); // Espaço
        painelPrincipal.add(painelFormulario);

        // Botões de ação
        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoLimpar = new JButton("Limpar");

        // Estilo dos botões
        botaoCadastrar.setBackground(corBotaoCadastrar);
        botaoLimpar.setBackground(corBotaoLimpar);
        botaoCadastrar.setForeground(Color.WHITE);
        botaoLimpar.setForeground(Color.WHITE);
        botaoCadastrar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoLimpar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(Box.createHorizontalStrut(20)); // Espaço entre os botões
        painelBotoes.add(botaoLimpar);

        painelPrincipal.add(Box.createVerticalStrut(10)); // Espaço
        painelPrincipal.add(painelBotoes);

        // Imagem
        JPanel painelHamster = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelHamster.setBackground(corFundo);
        painelHamster.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
        painelHamster.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        // Imagem
        JPanel painelCachorro = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelCachorro.setBackground(corFundo);
        painelCachorro.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        painelCachorro.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        // Junta as duas imagens nos cantos
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(painelHamster, BorderLayout.WEST);
        painelInferior.add(painelCachorro, BorderLayout.EAST);

        painelPrincipal.add(Box.createVerticalStrut(5)); // Espaço
        painelPrincipal.add(painelInferior);

        add(painelPrincipal); // Adiciona tudo ao frame

        // Ação do botão CADASTRAR
        botaoCadastrar.addActionListener((ActionEvent e) -> {
            // Coleta os dados preenchidos
            String nome = campoNome.getText().trim();
            String email = campoEmail.getText().trim();
            String cpf = campoCpf.getText();
            String telefone = campoTelefone.getText();

            // Verifica se campos obrigatórios foram preenchidos
            if (nome.isEmpty() || cpf.contains("_") || telefone.contains("_")) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios corretamente.");
                return;
            }

            // Cria novo cliente e adiciona na lista
            Cliente novo = new Cliente(nome, email, cpf, telefone);
            listaClientes.add(novo);

            // Salva lista no CSV
            CSVUtils.salvarCSV("clientes.csv", listaClientes);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            limparCampos(); // Limpa os campos após salvar
        });

        // Ação do botão LIMPAR
        botaoLimpar.addActionListener(e -> limparCampos());
    }

    // Metodo para limpar os campos do formulário
    private void limparCampos() {
        campoNome.setText("");
        campoEmail.setText("");
        campoCpf.setValue(null);
        campoTelefone.setValue(null);
    }
}
