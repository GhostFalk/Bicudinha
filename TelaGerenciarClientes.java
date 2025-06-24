import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

public class TelaGerenciarClientes extends JFrame {
    private JComboBox<String> comboSugestoes;
    private JTextField campoBuscaCpf;
    private JTextField campoNome, campoEmail;
    private JFormattedTextField campoCpf, campoTelefone;
    private JButton botaoAtualizar, botaoExcluir;
    private ArrayList<Cliente> listaClientes;
    private final String CAMINHO_SERIALIZADO = "clientes.ser";

    public TelaGerenciarClientes(ArrayList<Cliente> clientes) {
        listaClientes = clientes;

        setTitle("🐾 Gerenciar Clientes");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoAtualizar = new Color(145, 205, 144);
        Color corBotaoExcluir = new Color(237, 115, 110);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        JLabel imagemTopo = new JLabel(new ImageIcon(new ImageIcon("gatinho.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        imagemTopo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(imagemTopo);

        JLabel titulo = new JLabel("Gerenciar Clientes");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(10));

        JPanel painelBusca = new JPanel(new GridBagLayout());
        painelBusca.setBackground(corFundo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblCpf = new JLabel("Digite CPF:");
        lblCpf.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        campoBuscaCpf = new JTextField(20);
        campoBuscaCpf.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        comboSugestoes = new JComboBox<>();
        comboSugestoes.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        comboSugestoes.setMaximumRowCount(5);
        comboSugestoes.setPrototypeDisplayValue("000.000.000-00 - Exemplo");
        comboSugestoes.addItem("Selecione um cliente...");
        comboSugestoes.setVisible(true);

        Dimension mesmoTamanho = new Dimension(250, 30);
        campoBuscaCpf.setPreferredSize(mesmoTamanho);
        comboSugestoes.setPreferredSize(mesmoTamanho);

        gbc.gridx = 0; gbc.gridy = 0;
        painelBusca.add(lblCpf, gbc);
        gbc.gridx = 1;
        painelBusca.add(campoBuscaCpf, gbc);
        gbc.gridy = 1;
        painelBusca.add(comboSugestoes, gbc);
        painelPrincipal.add(painelBusca);

        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        painelFormulario.setMaximumSize(new Dimension(600, 180));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        campoNome = new JTextField(); campoNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 18)); campoNome.setEditable(false);
        campoEmail = new JTextField(); campoEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            MaskFormatter telMask = new MaskFormatter("(##) #####-####");
            cpfMask.setPlaceholderCharacter('_');
            telMask.setPlaceholderCharacter('_');
            campoCpf = new JFormattedTextField(cpfMask);
            campoTelefone = new JFormattedTextField(telMask);
        } catch (ParseException e) {
            campoCpf = new JFormattedTextField(); campoTelefone = new JFormattedTextField();
        }

        campoCpf.setFont(new Font("Comic Sans MS", Font.PLAIN, 18)); campoCpf.setEditable(false);
        campoTelefone.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        painelFormulario.add(new JLabel("Nome:")); painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("CPF:")); painelFormulario.add(campoCpf);
        painelFormulario.add(new JLabel("Email:")); painelFormulario.add(campoEmail);
        painelFormulario.add(new JLabel("Telefone:")); painelFormulario.add(campoTelefone);
        painelPrincipal.add(painelFormulario);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        botaoAtualizar = new JButton("Atualizar");
        botaoExcluir = new JButton("Excluir");

        botaoAtualizar.setBackground(corBotaoAtualizar);
        botaoExcluir.setBackground(corBotaoExcluir);
        botaoAtualizar.setForeground(Color.WHITE);
        botaoExcluir.setForeground(Color.WHITE);
        botaoAtualizar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoExcluir.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        painelBotoes.add(botaoAtualizar);
        painelBotoes.add(Box.createHorizontalStrut(20));
        painelBotoes.add(botaoExcluir);
        painelPrincipal.add(painelBotoes);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.WEST);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.EAST);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        campoBuscaCpf.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String texto = campoBuscaCpf.getText().trim();
                comboSugestoes.removeAllItems();
                comboSugestoes.addItem("Selecione um cliente...");
                boolean encontrou = false;
                for (Cliente c : listaClientes) {
                    if (c.cpf.startsWith(texto)) {
                        comboSugestoes.addItem(c.cpf + " - " + c.nome);
                        encontrou = true;
                    }
                }
                comboSugestoes.setVisible(true);
                if (encontrou) {
                    comboSugestoes.setSelectedIndex(0);
                    comboSugestoes.showPopup();
                } else {
                    comboSugestoes.hidePopup();
                }
                campoNome.setText(""); campoCpf.setText(""); campoEmail.setText(""); campoTelefone.setText("");
            }
        });

        comboSugestoes.addActionListener(e -> {
            String selecao = (String) comboSugestoes.getSelectedItem();
            if (selecao != null && selecao.contains(" - ")) {
                String cpfSelecionado = selecao.split(" - ")[0];
                for (Cliente c : listaClientes) {
                    if (c.cpf.equals(cpfSelecionado)) {
                        campoNome.setText(c.getNome());
                        campoCpf.setText(c.cpf);
                        campoEmail.setText(c.email);
                        campoTelefone.setText(c.telefone);
                        break;
                    }
                }
            }
        });

        botaoAtualizar.addActionListener(e -> {
            String cpfBusca = campoCpf.getText().trim();
            boolean encontrado = false;
            for (Cliente c : listaClientes) {
                if (c.cpf.equals(cpfBusca)) {
                    c.editarDados(campoTelefone.getText(), campoEmail.getText());
                    PersistenciaUtils.salvarLista(CAMINHO_SERIALIZADO, listaClientes);
                    JOptionPane.showMessageDialog(this, "Dados atualizados!");
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                try {
                    throw new ClienteNaoEncontradoException("Cliente não encontrado.");
                } catch (ClienteNaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        });

        botaoExcluir.addActionListener(e -> {
            String cpfBusca = campoCpf.getText().trim();
            Cliente remover = null;
            for (Cliente c : listaClientes) {
                if (c.cpf.equals(cpfBusca)) {
                    remover = c;
                    break;
                }
            }
            if (remover != null) {
                int resposta = JOptionPane.showConfirmDialog(
                        this,
                        "Tem certeza que deseja excluir este cliente?",
                        "Confirmar exclusão",
                        JOptionPane.YES_NO_OPTION
                );
                if (resposta == JOptionPane.YES_OPTION) {
                    listaClientes.remove(remover);
                    PersistenciaUtils.salvarLista(CAMINHO_SERIALIZADO, listaClientes);
                    campoNome.setText(""); campoCpf.setText(""); campoEmail.setText(""); campoTelefone.setText(""); campoBuscaCpf.setText("");
                    comboSugestoes.removeAllItems(); comboSugestoes.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Cliente removido.");
                }
            } else {
                try {
                    throw new ClienteNaoEncontradoException("Cliente não encontrado.");
                } catch (ClienteNaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        });
    }
}
