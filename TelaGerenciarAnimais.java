import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

public class TelaGerenciarAnimais extends JFrame {
    private JComboBox<String> comboSugestoes;
    private JTextField campoBuscaNome;
    private JTextField campoNome, campoEspecie, campoRaca, campoIdade;
    private JButton botaoAtualizar, botaoExcluir;
    private ArrayList<Animal> listaAnimais;

    public TelaGerenciarAnimais(ArrayList<Animal> animais) {
        listaAnimais = animais;

        setTitle("🐾 Gerenciar Animais");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JLabel titulo = new JLabel("Gerenciar Animais");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(10));

        JPanel painelBusca = new JPanel(new GridBagLayout());
        painelBusca.setBackground(corFundo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblNome = new JLabel("Digite nome:");
        lblNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        campoBuscaNome = new JTextField(20);
        campoBuscaNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        comboSugestoes = new JComboBox<>();
        comboSugestoes.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        comboSugestoes.setMaximumRowCount(5);
        comboSugestoes.addItem("Selecione um animal...");
        comboSugestoes.setVisible(true);

        Dimension mesmoTamanho = new Dimension(250, 30);
        campoBuscaNome.setPreferredSize(mesmoTamanho);
        comboSugestoes.setPreferredSize(mesmoTamanho);

        gbc.gridx = 0; gbc.gridy = 0;
        painelBusca.add(lblNome, gbc);
        gbc.gridx = 1;
        painelBusca.add(campoBuscaNome, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        painelBusca.add(comboSugestoes, gbc);

        painelPrincipal.add(painelBusca);

        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        painelFormulario.setMaximumSize(new Dimension(600, 180));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        campoNome = new JTextField(); campoNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 18)); campoNome.setEditable(false);
        campoEspecie = new JTextField(); campoEspecie.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        campoRaca = new JTextField(); campoRaca.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        campoIdade = new JTextField(); campoIdade.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        painelFormulario.add(new JLabel("Nome:")); painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("Espécie:")); painelFormulario.add(campoEspecie);
        painelFormulario.add(new JLabel("Raça:")); painelFormulario.add(campoRaca);
        painelFormulario.add(new JLabel("Idade:")); painelFormulario.add(campoIdade);

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
        painelBotoes.add(Box.createHorizontalStrut(25));
        painelBotoes.add(botaoExcluir);
        painelPrincipal.add(painelBotoes);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.WEST);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.EAST);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        campoBuscaNome.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String texto = campoBuscaNome.getText().trim().toLowerCase();
                comboSugestoes.removeAllItems();
                comboSugestoes.addItem("Selecione um animal...");
                for (Animal a : listaAnimais) {
                    if (a.getNome().toLowerCase().startsWith(texto)) {
                        comboSugestoes.addItem(a.getNome());
                    }
                }
                campoNome.setText(""); campoEspecie.setText(""); campoRaca.setText(""); campoIdade.setText("");
                comboSugestoes.setVisible(true);
                comboSugestoes.showPopup();
            }
        });

        comboSugestoes.addActionListener(e -> {
            String nomeSelecionado = (String) comboSugestoes.getSelectedItem();
            if (nomeSelecionado != null && !nomeSelecionado.equals("Selecione um animal...")) {
                for (Animal a : listaAnimais) {
                    if (a.getNome().equals(nomeSelecionado)) {
                        campoNome.setText(a.getNome());
                        campoEspecie.setText(a.getEspecie());
                        campoRaca.setText(a.getRaca());
                        campoIdade.setText(String.valueOf(a.getIdade()));
                        break;
                    }
                }
            }
        });

        botaoAtualizar.addActionListener(e -> {
            String nomeBusca = campoNome.getText().trim();
            for (Animal a : listaAnimais) {
                if (a.getNome().equals(nomeBusca)) {
                    a.setEspecie(campoEspecie.getText());
                    a.setRaca(campoRaca.getText());
                    try {
                        a.setIdade(Integer.parseInt(campoIdade.getText()));
                        JOptionPane.showMessageDialog(this, "Dados atualizados!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Idade inválida!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Animal não encontrado.");
        });

        botaoExcluir.addActionListener(e -> {
            String nomeBusca = campoNome.getText().trim();
            Animal remover = null;
            for (Animal a : listaAnimais) {
                if (a.getNome().equals(nomeBusca)) {
                    remover = a;
                    break;
                }
            }
            if (remover != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este animal?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    listaAnimais.remove(remover);
                    campoNome.setText(""); campoEspecie.setText(""); campoRaca.setText(""); campoIdade.setText(""); campoBuscaNome.setText("");
                    comboSugestoes.removeAllItems(); comboSugestoes.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Animal removido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Animal não encontrado.");
            }
        });
    }

    public static void main(String[] args) {
        ArrayList<Animal> listaFake = new ArrayList<>();

        Cliente dono1 = new Cliente("Marina", "", "123.456.789-00", "");
        Cliente dono2 = new Cliente("Nicolas", "", "111.222.333-44", "");

        listaFake.add(new Animal("Marley", "Cachorro", 5, "Golden", "Masculino", 25.0, dono1));
        listaFake.add(new Animal("Mimi", "Gato", 3, "Siames", "Feminino", 4.5, dono2));

        SwingUtilities.invokeLater(() -> new TelaGerenciarAnimais(listaFake).setVisible(true));
    }
}
