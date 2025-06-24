import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaGerenciarVeterinario extends JFrame {
    private JComboBox<String> comboSugestoes;
    private JTextField campoBuscaCrmv;
    private JTextField campoNome, campoEmail, campoCrmv, campoTelefone;
    private JComboBox<String> campoEspecialidade;
    private JButton botaoAtualizar, botaoExcluir;

    private ArrayList<Veterinario> listaVeterinarios;

    public TelaGerenciarVeterinario(ArrayList<Veterinario> veterinarios) {
        listaVeterinarios = veterinarios;

        setTitle("🐾 Gerenciar Veterinários");
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

        JLabel titulo = new JLabel("Gerenciar Veterinários");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(10));

        JPanel painelBusca = new JPanel(new GridBagLayout());
        painelBusca.setBackground(corFundo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCrmv = new JLabel("Digite CRMV:");
        lblCrmv.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        campoBuscaCrmv = new JTextField(20);
        campoBuscaCrmv.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        campoBuscaCrmv.setFocusable(true);

        comboSugestoes = new JComboBox<>();
        comboSugestoes.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        comboSugestoes.setMaximumRowCount(5);
        comboSugestoes.setVisible(false);

        Dimension mesmoTamanho = new Dimension(250, 30);
        campoBuscaCrmv.setPreferredSize(mesmoTamanho);
        comboSugestoes.setPreferredSize(mesmoTamanho);

        gbc.gridx = 0; gbc.gridy = 0;
        painelBusca.add(lblCrmv, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        painelBusca.add(campoBuscaCrmv, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        painelBusca.add(comboSugestoes, gbc);

        painelPrincipal.add(painelBusca);

        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        painelFormulario.setMaximumSize(new Dimension(600, 250));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        campoNome = new JTextField(); campoNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 18)); campoNome.setEditable(false);
        campoEmail = new JTextField(); campoEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        campoCrmv = new JTextField(); campoCrmv.setFont(new Font("Comic Sans MS", Font.PLAIN, 18)); campoCrmv.setEditable(false);
        campoTelefone = new JTextField(); campoTelefone.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        campoEspecialidade = new JComboBox<>(new String[] {
                "-- Selecione --", "Clínica Geral", "Cirurgia Geral", "Dermatologia", "Odontologia", "Oftalmologia", "Atendimento a Exóticos"
        });
        campoEspecialidade.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        painelFormulario.add(new JLabel("Nome:")); painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("Email:")); painelFormulario.add(campoEmail);
        painelFormulario.add(new JLabel("CRMV:")); painelFormulario.add(campoCrmv);
        painelFormulario.add(new JLabel("Especialidade:")); painelFormulario.add(campoEspecialidade);
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

        campoBuscaCrmv.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String texto = campoBuscaCrmv.getText().trim();
                comboSugestoes.removeAllItems();
                comboSugestoes.addItem("Selecione um veterinário...");
                for (Veterinario v : listaVeterinarios) {
                    if (v.getCrmv().startsWith(texto)) {
                        comboSugestoes.addItem(v.getCrmv() + " - " + v.getNome());
                    }
                }
                comboSugestoes.setVisible(comboSugestoes.getItemCount() > 1);
                if (comboSugestoes.isVisible()) comboSugestoes.showPopup();
                campoBuscaCrmv.requestFocusInWindow();
            }
        });

        comboSugestoes.addActionListener(e -> {
            String selecionado = (String) comboSugestoes.getSelectedItem();
            if (selecionado != null && !selecionado.equals("Selecione um veterinário...")) {
                String crmvSelecionado = selecionado.split(" - ")[0];
                for (Veterinario v : listaVeterinarios) {
                    if (v.getCrmv().equals(crmvSelecionado)) {
                        campoNome.setText(v.getNome());
                        campoEmail.setText(v.getEmail());
                        campoCrmv.setText(v.getCrmv());
                        campoEspecialidade.setSelectedItem(v.getEspecialidade());
                        campoTelefone.setText(v.getTelefone());
                        break;
                    }
                }
            }
        });

        botaoAtualizar.addActionListener(e -> {
            String crmvBusca = campoCrmv.getText().trim();
            for (Veterinario v : listaVeterinarios) {
                if (v.getCrmv().equals(crmvBusca)) {
                    v.setEmail(campoEmail.getText());
                    v.setEspecialidade((String) campoEspecialidade.getSelectedItem());
                    v.setTelefone(campoTelefone.getText());
                    JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
                    CSVUtils.salvarCSV("veterinarios.csv", listaVeterinarios);
                    PersistenciaUtils.salvarLista("veterinarios.ser", listaVeterinarios);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Veterinário não encontrado.");
        });

        botaoExcluir.addActionListener(e -> {
            String crmvBusca = campoCrmv.getText().trim();
            Veterinario remover = null;
            for (Veterinario v : listaVeterinarios) {
                if (v.getCrmv().equals(crmvBusca)) {
                    remover = v;
                    break;
                }
            }
            if (remover != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    listaVeterinarios.remove(remover);
                    campoNome.setText(""); campoEmail.setText(""); campoCrmv.setText("");
                    campoEspecialidade.setSelectedIndex(0); campoTelefone.setText(""); campoBuscaCrmv.setText("");
                    comboSugestoes.removeAllItems(); comboSugestoes.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Veterinário removido.");
                    CSVUtils.salvarCSV("veterinarios.csv", listaVeterinarios);
                    PersistenciaUtils.salvarLista("veterinarios.ser", listaVeterinarios);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veterinário não encontrado.");
            }
        });
    }
}
