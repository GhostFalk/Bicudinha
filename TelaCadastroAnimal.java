import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class TelaCadastroAnimal extends JFrame {
    private JTextField campoNomeAnimal, campoRaca, campoPeso, campoIdade;
    private JComboBox<String> comboEspecie, comboSexo, comboCpfDono;
    private ArrayList<Animal> listaAnimais = new ArrayList<>();
    private HashMap<String, String> donos = new HashMap<>();

    public TelaCadastroAnimal() {
        setTitle("🐾 Cadastro de Animal");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        donos.put("123.456.789-00", "Marina");
        donos.put("111.222.333-44", "Nicolas");

        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoCadastrar = new Color(145, 205, 144);
        Color corBotaoLimpar = new Color(237, 115, 110);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        ImageIcon imagemTopo = new ImageIcon("gatinho.png");
        Image imgGato = imagemTopo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(imgGato));
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelImagem.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        painelPrincipal.add(labelImagem);

        JLabel titulo = new JLabel("Cadastro de Animal");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setForeground(new Color(65, 52, 40));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        JPanel painelFormulario = new JPanel(new GridLayout(7, 2, 10, 8));
        painelFormulario.setMaximumSize(new Dimension(600, 280));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        Font fonteCampos = new Font("Comic Sans MS", Font.PLAIN, 18);

        campoNomeAnimal = new JTextField(); campoNomeAnimal.setFont(fonteCampos);
        campoRaca = new JTextField(); campoRaca.setFont(fonteCampos);
        campoPeso = new JTextField(); campoPeso.setFont(fonteCampos);
        campoIdade = new JTextField(); campoIdade.setFont(fonteCampos);
        campoPeso.setDocument(new JTextFieldLimit(5));
        campoIdade.setDocument(new JTextFieldLimit(3));

        comboSexo = new JComboBox<>(new String[]{"Feminino", "Masculino"});
        comboSexo.setFont(fonteCampos);

        comboEspecie = new JComboBox<>(new String[]{"Cachorro", "Gato", "Roedor", "Pássaro", "Peixe"});
        comboEspecie.setFont(fonteCampos);

        comboCpfDono = new JComboBox<>();
        for (String cpf : donos.keySet()) {
            comboCpfDono.addItem(cpf + " - " + donos.get(cpf));
        }
        comboCpfDono.setFont(fonteCampos);

        painelFormulario.add(new JLabel("Nome do Animal:"));
        painelFormulario.add(campoNomeAnimal);
        painelFormulario.add(new JLabel("Espécie:"));
        painelFormulario.add(comboEspecie);
        painelFormulario.add(new JLabel("Raça:"));
        painelFormulario.add(campoRaca);
        painelFormulario.add(new JLabel("Peso (kg):"));
        painelFormulario.add(campoPeso);
        painelFormulario.add(new JLabel("Idade:"));
        painelFormulario.add(campoIdade);
        painelFormulario.add(new JLabel("Sexo:"));
        painelFormulario.add(comboSexo);
        painelFormulario.add(new JLabel("Dono:"));
        painelFormulario.add(comboCpfDono);

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

        JPanel painelCachorro = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelCachorro.setBackground(corFundo);
        painelCachorro.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        painelCachorro.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        JPanel painelHamster = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelHamster.setBackground(corFundo);
        painelHamster.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
        painelHamster.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(painelHamster, BorderLayout.WEST);
        painelInferior.add(painelCachorro, BorderLayout.EAST);

        painelPrincipal.add(Box.createVerticalStrut(5));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        // Ação do botão Cadastrar
        botaoCadastrar.addActionListener((ActionEvent e) -> {
            try {
                String nome = campoNomeAnimal.getText();
                String especie = (String) comboEspecie.getSelectedItem();
                String raca = campoRaca.getText();
                int idade = Integer.parseInt(campoIdade.getText());
                String sexo = (String) comboSexo.getSelectedItem();
                double peso = Double.parseDouble(campoPeso.getText());

                String selecionado = (String) comboCpfDono.getSelectedItem();
                String[] partes = selecionado.split(" - ");
                String cpfDono = partes[0];
                String nomeDono = partes.length > 1 ? partes[1] : "Desconhecido";

                Cliente dono = new Cliente(nomeDono, "", cpfDono, "");

                Animal novo = new Animal(nome, especie, idade, raca, sexo, peso, dono);
                listaAnimais.add(novo);

                JOptionPane.showMessageDialog(this, "Animal cadastrado com sucesso!");
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Verifique os campos de idade e peso.");
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroAnimal().setVisible(true));
    }
}
