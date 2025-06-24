import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class TelaCadastroAnimal extends JFrame {

    private JTextField campoNomeAnimal, campoRaca, campoPeso, campoIdade;
    private JComboBox<String> comboEspecie, comboSexo, comboCpfDono;
    private JCheckBox checkVacinado;

    private ArrayList<Animal> listaAnimais;
    private ArrayList<Cliente> listaClientes;
    private HashMap<String, String> donos;

    public TelaCadastroAnimal(ArrayList<Animal> animais, ArrayList<Cliente> clientes) {
        this.listaAnimais = animais;
        this.listaClientes = clientes;
        this.donos = new HashMap<>();

        for (Cliente c : clientes) {
            donos.put(c.getCpf(), c.getNome());
        }

        // ✅ Carrega animais salvos do CSV
        this.listaAnimais.clear();
        this.listaAnimais.addAll(CSVUtils.carregarAnimais("animais.csv", clientes));

        initComponents();
    }

    private void initComponents() {
        setTitle("🐾 Cadastro de Animal");
        setSize(800, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        labelImagem.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        painelPrincipal.add(labelImagem);

        JLabel titulo = new JLabel("Cadastro de Animal");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setForeground(new Color(65, 52, 40));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        JPanel painelFormulario = new JPanel(new GridLayout(8, 2, 10, 8));
        painelFormulario.setMaximumSize(new Dimension(600, 320));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        Font fonteCampos = new Font("Comic Sans MS", Font.PLAIN, 18);

        campoNomeAnimal = new JTextField(); campoNomeAnimal.setFont(fonteCampos);
        campoRaca = new JTextField(); campoRaca.setFont(fonteCampos);
        campoPeso = new JTextField(); campoPeso.setFont(fonteCampos); campoPeso.setDocument(new JTextFieldLimit(5));
        campoIdade = new JTextField(); campoIdade.setFont(fonteCampos); campoIdade.setDocument(new JTextFieldLimit(3));

        comboSexo = new JComboBox<>(new String[]{"Feminino", "Masculino"}); comboSexo.setFont(fonteCampos);
        comboEspecie = new JComboBox<>(new String[]{"Cachorro", "Gato", "Roedor", "Pássaro", "Peixe"}); comboEspecie.setFont(fonteCampos);

        comboCpfDono = new JComboBox<>();
        for (String cpf : donos.keySet()) {
            comboCpfDono.addItem(cpf + " - " + donos.get(cpf));
        }
        comboCpfDono.setFont(fonteCampos);

        checkVacinado = new JCheckBox("Vacinado");
        checkVacinado.setFont(fonteCampos);
        checkVacinado.setBackground(corFormulario);

        painelFormulario.add(new JLabel("Nome do Animal:")); painelFormulario.add(campoNomeAnimal);
        painelFormulario.add(new JLabel("Espécie:")); painelFormulario.add(comboEspecie);
        painelFormulario.add(new JLabel("Raça:")); painelFormulario.add(campoRaca);
        painelFormulario.add(new JLabel("Peso (kg):")); painelFormulario.add(campoPeso);
        painelFormulario.add(new JLabel("Idade:")); painelFormulario.add(campoIdade);
        painelFormulario.add(new JLabel("Sexo:")); painelFormulario.add(comboSexo);
        painelFormulario.add(new JLabel("Dono:")); painelFormulario.add(comboCpfDono);
        painelFormulario.add(new JLabel("Vacinado:")); painelFormulario.add(checkVacinado);

        painelPrincipal.add(Box.createVerticalStrut(5));
        painelPrincipal.add(painelFormulario);

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

        JPanel painelHamster = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelHamster.setBackground(corFundo);
        painelHamster.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
        painelHamster.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        JPanel painelCachorro = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelCachorro.setBackground(corFundo);
        painelCachorro.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        painelCachorro.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(painelHamster, BorderLayout.WEST);
        painelInferior.add(painelCachorro, BorderLayout.EAST);

        painelPrincipal.add(Box.createVerticalStrut(5));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        botaoCadastrar.addActionListener((ActionEvent e) -> {
            try {
                String nome = campoNomeAnimal.getText();
                String especie = (String) comboEspecie.getSelectedItem();
                String raca = campoRaca.getText();
                String textoIdade = campoIdade.getText().trim();
                String textoPeso = campoPeso.getText().trim().replace(",", ".");

                if (textoIdade.isEmpty() || textoPeso.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha os campos de idade e peso.");
                    return;
                }

                int idade = Integer.parseInt(textoIdade);
                double peso = Double.parseDouble(textoPeso);
                String sexo = (String) comboSexo.getSelectedItem();
                boolean vacinado = checkVacinado.isSelected();

                String selecionado = (String) comboCpfDono.getSelectedItem();
                String[] partes = selecionado.split(" - ");
                String cpfDono = partes[0];

                Cliente dono = null;
                for (Cliente c : listaClientes) {
                    if (c.getCpf().equals(cpfDono)) {
                        dono = c;
                        break;
                    }
                }

                if (dono == null) {
                    JOptionPane.showMessageDialog(this, "Dono não encontrado.");
                    return;
                }

                Animal novo = new Animal(nome, especie, idade, raca, sexo, peso, dono, vacinado);
                listaAnimais.add(novo);

                CSVUtils.salvarCSV("animais.csv", listaAnimais);

                JOptionPane.showMessageDialog(this, "Animal cadastrado com sucesso!");
                limparCampos();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifique os campos de idade e peso. Use apenas números. No campo de peso, use ponto (.) ao invés de vírgula (,).");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + ex.getMessage());
            }
        });

        botaoLimpar.addActionListener(e -> limparCampos());
    }

    private void limparCampos() {
        campoNomeAnimal.setText("");
        campoRaca.setText("");
        campoPeso.setText("");
        campoIdade.setText("");
        comboSexo.setSelectedIndex(0);
        comboEspecie.setSelectedIndex(0);
        comboCpfDono.setSelectedIndex(0);
        checkVacinado.setSelected(false);
    }
}
