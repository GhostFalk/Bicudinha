import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class TelaCadastroCliente extends JFrame {
    private JTextField campoNome, campoEmail;
    private JFormattedTextField campoCpf, campoTelefone;
    private ArrayList<Cliente> listaClientes = new ArrayList<>();

    public TelaCadastroCliente() {
        setTitle("🐾 Cadastro de Cliente");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 🎨 Cores
        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoCadastrar = new Color(145, 205, 144);
        Color corBotaoLimpar = new Color(237, 115, 110);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        // 🐱 Gato no topo (REDUZIDO)
        ImageIcon imagemTopo = new ImageIcon("C:\\Users\\Ferre\\OneDrive\\Documentos\\MARINA\\Bicudinha-main\\gatinho.png");
        Image imagemRedimensionada = imagemTopo.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelImagem.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        painelPrincipal.add(labelImagem);

        // Título
        JLabel titulo = new JLabel("Cadastro de Cliente");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setForeground(new Color(65, 52, 40));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        // Formulário
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 20, 20));
        painelFormulario.setMaximumSize(new Dimension(600, 200));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        painelFormulario.setBackground(corFormulario);

        Font fonteCampos = new Font("Comic Sans MS", Font.PLAIN, 18);

        campoNome = new JTextField(); campoNome.setFont(fonteCampos);
        campoEmail = new JTextField(); campoEmail.setFont(fonteCampos);

        try {
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            campoCpf = new JFormattedTextField(mascaraCpf);
            campoCpf.setFont(fonteCampos);

            MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
            mascaraTelefone.setPlaceholderCharacter('_');
            campoTelefone = new JFormattedTextField(mascaraTelefone);
            campoTelefone.setFont(fonteCampos);
        } catch (ParseException e) {
            e.printStackTrace();
            campoCpf = new JFormattedTextField();
            campoTelefone = new JFormattedTextField();
        }

        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("Email:"));
        painelFormulario.add(campoEmail);
        painelFormulario.add(new JLabel("CPF:"));
        painelFormulario.add(campoCpf);
        painelFormulario.add(new JLabel("Telefone:"));
        painelFormulario.add(campoTelefone);

        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(painelFormulario);

        // Botões
        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoLimpar = new JButton("Limpar");

        botaoCadastrar.setBackground(corBotaoCadastrar);
        botaoLimpar.setBackground(corBotaoLimpar);
        botaoCadastrar.setForeground(Color.WHITE);
        botaoLimpar.setForeground(Color.WHITE);
        botaoCadastrar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoLimpar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoCadastrar.setFocusPainted(false);
        botaoLimpar.setFocusPainted(false);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(Box.createHorizontalStrut(20));
        painelBotoes.add(botaoLimpar);

        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(painelBotoes);

        // 🐹 Hamster no canto inferior ESQUERDO
        JPanel painelHamster = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelHamster.setBackground(corFundo);
        painelHamster.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
        try {
            ImageIcon imagemHamster = new ImageIcon("C:\\Users\\Ferre\\OneDrive\\Documentos\\MARINA\\Bicudinha-main\\hamster.png"); // <--- ALTERE AQUI
            Image hamsterRedimensionado = imagemHamster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel hamsterLabel = new JLabel(new ImageIcon(hamsterRedimensionado));
            painelHamster.add(hamsterLabel);
        } catch (Exception e) {
            painelHamster.add(new JLabel("🐹"));
        }

        // 🐶 Cachorro no canto inferior DIREITO
        JPanel painelCachorro = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelCachorro.setBackground(corFundo);
        painelCachorro.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
        try {
            ImageIcon imagemCachorro = new ImageIcon("C:\\Users\\Ferre\\OneDrive\\Documentos\\MARINA\\Bicudinha-main\\dogo.png");
            Image cachorroRedimensionado = imagemCachorro.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JLabel cachorroLabel = new JLabel(new ImageIcon(cachorroRedimensionado));
            painelCachorro.add(cachorroLabel);
        } catch (Exception e) {
            painelCachorro.add(new JLabel("🐶"));
        }

        // Rodapé com hamster à esquerda e cachorro à direita
        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.setBackground(corFundo);
        painelRodape.add(painelHamster, BorderLayout.WEST);
        painelRodape.add(painelCachorro, BorderLayout.EAST);

        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelRodape);

        add(painelPrincipal);

        // Ações
        botaoCadastrar.addActionListener((ActionEvent e) -> {
            String nome = campoNome.getText();
            String email = campoEmail.getText();
            String cpf = campoCpf.getText();
            String telefone = campoTelefone.getText();

            if (nome.isEmpty() || cpf.contains("_") || telefone.contains("_")) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios corretamente.");
                return;
            }

            Cliente novo = new Cliente(nome, email, cpf, telefone);
            listaClientes.add(novo);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            limparCampos();
        });

        botaoLimpar.addActionListener(e -> limparCampos());
    }

    private void limparCampos() {
        campoNome.setText("");
        campoEmail.setText("");
        campoCpf.setValue(null);
        campoTelefone.setValue(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroCliente().setVisible(true));
    }
}
