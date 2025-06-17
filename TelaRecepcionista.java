import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

// Tela com funcionalidades da recepcionista
public class TelaRecepcionista extends JFrame {
    private Recepcionista recepcionista;
    private ArrayList<Animal> listaAnimais;
    private ArrayList<Veterinario> listaVeterinarios;
    private ArrayList<Consulta> consultas;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaConsultas;

    // Construtor recebe recepcionista, lista de animais e veterinários
    public TelaRecepcionista(Recepcionista recepcionista,
                             ArrayList<Animal> listaAnimais,
                             ArrayList<Veterinario> listaVeterinarios) {
        this.recepcionista = recepcionista;
        this.listaAnimais = listaAnimais;
        this.listaVeterinarios = listaVeterinarios;

        // Carrega as consultas a partir do CSV e reconstrói os objetos com base nas listas
        this.consultas = new ArrayList<>();
        ArrayList<String> linhasConsulta = CSVUtils.carregarCSV("consultas.csv");
        for (String linha : linhasConsulta) {
            Consulta c = Consulta.fromCSV(linha, listaAnimais, listaVeterinarios);
            if (c != null) {
                consultas.add(c);
            }
        }

        // Configuração da janela
        setTitle("🐾 Painel da Recepcionista");
        setSize(880, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Cores
        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoAgendar = new Color(145, 205, 144);
        Color corBotaoChamar = new Color(237, 115, 110);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        // Imagem
        JLabel imagemTopo = new JLabel(new ImageIcon(new ImageIcon("gatinho.png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        imagemTopo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(imagemTopo);

        // Título
        JLabel titulo = new JLabel("Painel da Recepcionista");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(10));

        // Painel de formulário onde é exibida a lista de consultas
        JPanel painelFormulario = new JPanel(new BorderLayout());
        painelFormulario.setMaximumSize(new Dimension(820, 320));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        // Modelo da lista e JList para exibir a fila de consultas
        modeloLista = new DefaultListModel<>();
        listaConsultas = new JList<>(modeloLista);
        listaConsultas.setFont(new Font("Monospaced", Font.PLAIN, 16));
        listaConsultas.setFixedCellHeight(30);
        listaConsultas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Cabeçalho da lista com ícones
        JLabel labelCabecalho = new JLabel("📅 Data e Hora     | 🐾 Animal      | 👩‍⚕️ Veterinário       | ⏱️ Status", JLabel.LEFT);
        labelCabecalho.setFont(new Font("Monospaced", Font.BOLD, 16));
        labelCabecalho.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        painelFormulario.add(labelCabecalho, BorderLayout.NORTH);

        // Scroll para a lista
        JScrollPane scroll = new JScrollPane(listaConsultas);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        painelFormulario.add(scroll, BorderLayout.CENTER);

        painelPrincipal.add(painelFormulario);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);

        JButton botaoAgendar = new JButton("📅 Agendar Consulta");
        JButton botaoChamar = new JButton("🔔 Chamar Próximo Cliente");

        // Estilo dos botões
        botaoAgendar.setBackground(corBotaoAgendar);
        botaoChamar.setBackground(corBotaoChamar);
        botaoAgendar.setForeground(Color.WHITE);
        botaoChamar.setForeground(Color.WHITE);
        botaoAgendar.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        botaoChamar.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));

        painelBotoes.add(botaoAgendar);
        painelBotoes.add(Box.createHorizontalStrut(20));
        painelBotoes.add(botaoChamar);
        painelPrincipal.add(painelBotoes);

        // Imagens
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.WEST);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.EAST);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        // Adiciona tudo à janela
        add(painelPrincipal);

        // Abre a tela de cadastro de consulta
        botaoAgendar.addActionListener(e -> {
            TelaCadastroConsulta telaCadastro = new TelaCadastroConsulta(listaAnimais, listaVeterinarios);
            telaCadastro.setVisible(true);
        });

        // Chama o próximo cliente da fila
        botaoChamar.addActionListener(e -> {
            consultas.sort(Comparator.comparing(Consulta::getDataConsulta)); // Ordena por data
            Consulta proxima = null;

            for (Consulta c : consultas) {
                if (!c.foiChamado()) {
                    proxima = c;
                    break;
                }
            }

            if (proxima != null) {
                recepcionista.chamarCliente();
                JOptionPane.showMessageDialog(this, "Chamando: " + proxima.getAnimal().getNome() +
                        " (" + proxima.getAnimal().getEspecie() + ") - Médico: " + proxima.getVeterinario().getNome());

                proxima.setChamado(true); // Marca como chamado
                CSVUtils.salvarCSV("consultas.csv", consultas); // Atualiza o CSV
                atualizarFila(); // Atualiza a visualização
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma consulta pendente.");
            }
        });

        // Preenche a lista ao abrir a tela
        atualizarFila();
    }

    // Atualiza a lista de consultas exibida
    private void atualizarFila() {
        modeloLista.clear();
        consultas.sort(Comparator.comparing(Consulta::getDataConsulta));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Consulta c : consultas) {
            String linha = String.format("📅 %-16s | 🐾 %-12s | 👩‍⚕️ %-18s | %s",
                    c.getDataConsulta().format(formatter),
                    c.getAnimal().getNome(),
                    c.getVeterinario().getNome(),
                    c.foiChamado() ? "✅ Chamado" : "⏳ Pendente");

            modeloLista.addElement(linha);
        }
    }
}
