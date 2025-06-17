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

    public TelaGerenciarClientes(ArrayList<Cliente> clientes) {
        listaClientes = clientes;

        // Configuração da janela
        setTitle("🐾 Gerenciar Clientes");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Cores
        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoAtualizar = new Color(145, 205, 144);
        Color corBotaoExcluir = new Color(237, 115, 110);

        // Painel principal com layout vertical
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        // Imagem
        JLabel imagemTopo = new JLabel(new ImageIcon(new ImageIcon("gatinho.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        imagemTopo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(imagemTopo);

        // Título
        JLabel titulo = new JLabel("Gerenciar Clientes");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(10));

        // Painel de busca por CPF
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

        // Tamanhos dos campos
        Dimension mesmoTamanho = new Dimension(250, 30);
        campoBuscaCpf.setPreferredSize(mesmoTamanho);
        comboSugestoes.setPreferredSize(mesmoTamanho);

        // Adicionando componentes de busca
        gbc.gridx = 0; gbc.gridy = 0;
        painelBusca.add(lblCpf, gbc);
        gbc.gridx = 1;
        painelBusca.add(campoBuscaCpf, gbc);
        gbc.gridy = 1;
        painelBusca.add(comboSugestoes, gbc);

        painelPrincipal.add(painelBusca);

        // Formulário para dados do cliente
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        painelFormulario.setMaximumSize(new Dimension(600, 180));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        // Campos de texto formatados
        campoNome = new JTextField(); campoNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 18)); campoNome.setEditable(false);
        campoEmail = new JTextField(); campoEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        try {
            // Máscaras para CPF e telefone
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

        // Adicionando campos ao formulário
        painelFormulario.add(new JLabel("Nome:")); painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("CPF:")); painelFormulario.add(campoCpf);
        painelFormulario.add(new JLabel("Email:")); painelFormulario.add(campoEmail);
        painelFormulario.add(new JLabel("Telefone:")); painelFormulario.add(campoTelefone);
        painelPrincipal.add(painelFormulario);

        // Botões de ação
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

        // Imagens
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.WEST);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.EAST);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        // Busca por CPF digitado
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

        // Preencher campos com base no cliente selecionado
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

        // Atualiza os dados do cliente
        botaoAtualizar.addActionListener(e -> {
            String cpfBusca = campoCpf.getText().trim();
            for (Cliente c : listaClientes) {
                if (c.cpf.equals(cpfBusca)) {
                    c.editarDados(campoTelefone.getText(), campoEmail.getText());
                    CSVUtils.salvarCSV("clientes.csv", listaClientes);
                    JOptionPane.showMessageDialog(this, "Dados atualizados!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
        });

        // Exclui o cliente selecionado
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
                    CSVUtils.salvarCSV("clientes.csv", listaClientes);

                    campoNome.setText(""); campoCpf.setText(""); campoEmail.setText(""); campoTelefone.setText(""); campoBuscaCpf.setText("");
                    comboSugestoes.removeAllItems(); comboSugestoes.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Cliente removido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            }
        });
    }
}
