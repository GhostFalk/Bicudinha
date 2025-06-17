import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaGerenciarRecepcionista extends JFrame {
    // Componentes da interface
    private JComboBox<String> comboSugestoes;
    private JTextField campoBuscaCpf;
    private JTextField campoNome, campoEmail, campoCpf, campoTelefone;
    private JComboBox<String> campoTurno;
    private JButton botaoAtualizar, botaoExcluir;
    private ArrayList<Recepcionista> listaRecepcionistas;

    public TelaGerenciarRecepcionista(ArrayList<Recepcionista> recepcionistas) {
        listaRecepcionistas = recepcionistas;

        // Configuração da janela principal
        setTitle("Gerenciar Recepcionistas");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Cores
        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoAtualizar = new Color(145, 205, 144);
        Color corBotaoExcluir = new Color(237, 115, 110);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        // Imagem
        JLabel imagemTopo = new JLabel(new ImageIcon(new ImageIcon("gatinho.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        imagemTopo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(imagemTopo);

        // Título
        JLabel titulo = new JLabel("Gerenciar Recepcionistas");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(10));

        // Painel de busca
        JPanel painelBusca = new JPanel(new GridBagLayout());
        painelBusca.setBackground(corFundo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCpf = new JLabel("Digite CPF:");
        lblCpf.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        campoBuscaCpf = new JTextField(20);
        campoBuscaCpf.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        campoBuscaCpf.setFocusable(true);

        comboSugestoes = new JComboBox<>();
        comboSugestoes.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        comboSugestoes.setMaximumRowCount(5);
        comboSugestoes.setVisible(false); // começa invisível

        // Define tamanho fixo dos campos
        Dimension mesmoTamanho = new Dimension(250, 30);
        campoBuscaCpf.setPreferredSize(mesmoTamanho);
        comboSugestoes.setPreferredSize(mesmoTamanho);

        gbc.gridx = 0; gbc.gridy = 0;
        painelBusca.add(lblCpf, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        painelBusca.add(campoBuscaCpf, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        painelBusca.add(comboSugestoes, gbc);

        painelPrincipal.add(painelBusca);

        // Painel do formulário
        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        painelFormulario.setMaximumSize(new Dimension(600, 250));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        campoNome = new JTextField(); campoNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 18)); campoNome.setEditable(false);
        campoEmail = new JTextField(); campoEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        campoCpf = new JTextField(); campoCpf.setFont(new Font("Comic Sans MS", Font.PLAIN, 18)); campoCpf.setEditable(false);
        campoTelefone = new JTextField(); campoTelefone.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        campoTurno = new JComboBox<>(new String[] { "-- Selecione --", "Manhã", "Tarde" }); campoTurno.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        painelFormulario.add(new JLabel("Nome:")); painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("Email:")); painelFormulario.add(campoEmail);
        painelFormulario.add(new JLabel("CPF:")); painelFormulario.add(campoCpf);
        painelFormulario.add(new JLabel("Telefone:")); painelFormulario.add(campoTelefone);
        painelFormulario.add(new JLabel("Turno:")); painelFormulario.add(campoTurno);

        painelPrincipal.add(painelFormulario);

        // Painel dos botões
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

        // Painel inferior com imagens decorativas
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.WEST);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.EAST);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        // Digitação no campo CPF para sugerir nomes
        campoBuscaCpf.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String texto = campoBuscaCpf.getText().trim();
                comboSugestoes.removeAllItems();
                comboSugestoes.addItem("Selecione um recepcionista...");
                for (Recepcionista r : listaRecepcionistas) {
                    if (r.getCpf().startsWith(texto)) {
                        comboSugestoes.addItem(r.getCpf() + " - " + r.getNome());
                    }
                }
                comboSugestoes.setVisible(comboSugestoes.getItemCount() > 1);
                if (comboSugestoes.isVisible()) {
                    comboSugestoes.showPopup();
                }
                campoBuscaCpf.requestFocusInWindow();
            }
        });

        // Seleção de item
        comboSugestoes.addActionListener(e -> {
            String selecionado = (String) comboSugestoes.getSelectedItem();
            if (selecionado != null && !selecionado.equals("Selecione um recepcionista...")) {
                String cpfSelecionado = selecionado.split(" - ")[0];
                for (Recepcionista r : listaRecepcionistas) {
                    if (r.getCpf().equals(cpfSelecionado)) {
                        campoNome.setText(r.getNome());
                        campoEmail.setText(r.getEmail());
                        campoCpf.setText(r.getCpf());
                        campoTelefone.setText(r.getTelefone());
                        campoTurno.setSelectedItem(r.getTurno());
                        break;
                    }
                }
            }
        });

        // Botão Atualizar
        botaoAtualizar.addActionListener(e -> {
            String cpfBusca = campoCpf.getText().trim();
            for (Recepcionista r : listaRecepcionistas) {
                if (r.getCpf().equals(cpfBusca)) {
                    r.setEmail(campoEmail.getText());
                    r.setTelefone(campoTelefone.getText());
                    r.setTurno(campoTurno.getSelectedItem().equals("Manhã"));
                    JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
                    CSVUtils.salvarCSV("recepcionistas.csv", listaRecepcionistas);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Recepcionista não encontrado.");
        });

        // Botão Excluir
        botaoExcluir.addActionListener(e -> {
            String cpfBusca = campoCpf.getText().trim();
            Recepcionista remover = null;
            for (Recepcionista r : listaRecepcionistas) {
                if (r.getCpf().equals(cpfBusca)) {
                    remover = r;
                    break;
                }
            }
            if (remover != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    listaRecepcionistas.remove(remover);
                    campoNome.setText(""); campoEmail.setText(""); campoCpf.setText("");
                    campoTelefone.setText(""); campoTurno.setSelectedIndex(0); campoBuscaCpf.setText("");
                    comboSugestoes.removeAllItems(); comboSugestoes.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Recepcionista removido.");
                    CSVUtils.salvarCSV("recepcionistas.csv", listaRecepcionistas);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Recepcionista não encontrado.");
            }
        });
    }
}
