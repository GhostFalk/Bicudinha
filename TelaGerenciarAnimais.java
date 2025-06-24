import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TelaGerenciarAnimais extends JFrame {
    private JComboBox<String> comboSugestoes;
    private JTextField campoBuscaNome;
    private JTextField campoNome, campoEspecie, campoRaca, campoIdade, campoPeso, campoSexo, campoDono;
    private JCheckBox checkVacinado;
    private JButton botaoAtualizar, botaoExcluir;
    private ArrayList<Animal> listaAnimais;

    public TelaGerenciarAnimais(ArrayList<Animal> animais) {
        listaAnimais = animais;

        setTitle("🐾 Gerenciar Animais");
        setSize(800, 800);
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

        JPanel painelFormulario = new JPanel(new GridLayout(8, 2, 10, 10));
        painelFormulario.setMaximumSize(new Dimension(600, 300));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        Font fonte = new Font("Comic Sans MS", Font.PLAIN, 18);

        campoNome = new JTextField(); campoNome.setFont(fonte); campoNome.setEditable(false);
        campoEspecie = new JTextField(); campoEspecie.setFont(fonte); campoEspecie.setEditable(false);
        campoRaca = new JTextField(); campoRaca.setFont(fonte);
        campoPeso = new JTextField(); campoPeso.setFont(fonte);
        campoIdade = new JTextField(); campoIdade.setFont(fonte);
        campoSexo = new JTextField(); campoSexo.setFont(fonte); campoSexo.setEditable(false);
        campoDono = new JTextField(); campoDono.setFont(fonte); campoDono.setEditable(false);
        checkVacinado = new JCheckBox(); checkVacinado.setBackground(corFormulario);

        painelFormulario.add(new JLabel("Nome do animal:")); painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("Espécie:")); painelFormulario.add(campoEspecie);
        painelFormulario.add(new JLabel("Raça:")); painelFormulario.add(campoRaca);
        painelFormulario.add(new JLabel("Peso (kg):")); painelFormulario.add(campoPeso);
        painelFormulario.add(new JLabel("Idade:")); painelFormulario.add(campoIdade);
        painelFormulario.add(new JLabel("Sexo:")); painelFormulario.add(campoSexo);
        painelFormulario.add(new JLabel("Dono:")); painelFormulario.add(campoDono);
        painelFormulario.add(new JLabel("Vacinado:")); painelFormulario.add(checkVacinado);
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
                limparCampos();
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
                        campoPeso.setText(String.valueOf(a.getPeso()));
                        campoSexo.setText(a.getSexo());
                        campoDono.setText(a.getDono().getCpf() + " - " + a.getDono().getNome());
                        checkVacinado.setSelected(a.isVacinado());
                        break;
                    }
                }
            }
        });

        botaoAtualizar.addActionListener(e -> {
            String nomeBusca = campoNome.getText().trim();
            for (Animal a : listaAnimais) {
                if (a.getNome().equals(nomeBusca)) {
                    try {
                        a.setRaca(campoRaca.getText());
                        a.setIdade(Integer.parseInt(campoIdade.getText()));
                        a.setPeso(Double.parseDouble(campoPeso.getText()));
                        a.setVacinado(checkVacinado.isSelected());
                        CSVUtils.salvarCSV("animais.csv", listaAnimais);
                        salvarSerializado("animais.ser", listaAnimais);
                        JOptionPane.showMessageDialog(this, "Dados atualizados!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Idade ou peso inválido!");
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
                    CSVUtils.salvarCSV("animais.csv", listaAnimais);
                    salvarSerializado("animais.ser", listaAnimais);
                    limparCampos();
                    comboSugestoes.removeAllItems();
                    comboSugestoes.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Animal removido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Animal não encontrado.");
            }
        });
    }

    private void limparCampos() {
        campoNome.setText("");
        campoEspecie.setText("");
        campoRaca.setText("");
        campoIdade.setText("");
        campoPeso.setText("");
        campoSexo.setText("");
        campoDono.setText("");
        checkVacinado.setSelected(false);
    }

    // Novo metodo para salvar a lista serializada
    private void salvarSerializado(String nomeArquivo, Object objeto) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(objeto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo serializado.");
            e.printStackTrace();
        }
    }
}
