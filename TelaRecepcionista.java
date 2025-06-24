import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;

public class TelaRecepcionista extends JFrame {
    private Recepcionista recepcionista;
    private ArrayList<Animal> listaAnimais;
    private ArrayList<Veterinario> listaVeterinarios;
    private ArrayList<Consulta> consultas;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaConsultas;

    public TelaRecepcionista(Recepcionista recepcionista,
                             ArrayList<Animal> listaAnimais,
                             ArrayList<Veterinario> listaVeterinarios) {
        this.recepcionista = recepcionista;
        this.listaAnimais = listaAnimais;
        this.listaVeterinarios = listaVeterinarios;

        // ✅ Carregamento via serialização, com fallback para CSV
        consultas = PersistenciaUtils.carregarLista("consultas.ser");
        if (consultas.isEmpty()) {
            consultas = new ArrayList<>();
            ArrayList<String> linhasConsulta = CSVUtils.carregarCSV("consultas.csv");
            for (String linha : linhasConsulta) {
                Consulta c = Consulta.fromCSV(linha, listaAnimais, listaVeterinarios);
                if (c != null) consultas.add(c);
            }
        }

        // 🧱 Interface visual permanece igual (sem alterações)...
        setTitle("🐾 Painel da Recepcionista");
        setSize(880, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoAgendar = new Color(145, 205, 144);
        Color corBotaoChamar = new Color(237, 115, 110);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        JLabel imagemTopo = new JLabel(new ImageIcon(new ImageIcon("gatinho.png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        imagemTopo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(imagemTopo);

        JLabel titulo = new JLabel("Painel da Recepcionista");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(10));

        JPanel painelFormulario = new JPanel(new BorderLayout());
        painelFormulario.setMaximumSize(new Dimension(820, 320));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        modeloLista = new DefaultListModel<>();
        listaConsultas = new JList<>(modeloLista);
        listaConsultas.setFont(new Font("Monospaced", Font.PLAIN, 16));
        listaConsultas.setFixedCellHeight(30);
        listaConsultas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelCabecalho = new JLabel("📅 Data e Hora     | 🐾 Animal      | 👩‍⚕️ Veterinário       | ⏱️ Status", JLabel.LEFT);
        labelCabecalho.setFont(new Font("Monospaced", Font.BOLD, 16));
        labelCabecalho.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        painelFormulario.add(labelCabecalho, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(listaConsultas);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        painelFormulario.add(scroll, BorderLayout.CENTER);

        painelPrincipal.add(painelFormulario);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);

        JButton botaoAgendar = new JButton("📅 Agendar Consulta");
        JButton botaoChamar = new JButton("🔔 Chamar Próximo Cliente");

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

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(corFundo);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.WEST);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.EAST);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        botaoAgendar.addActionListener(e -> {
            TelaCadastroConsulta telaCadastro = new TelaCadastroConsulta(listaAnimais, listaVeterinarios);
            telaCadastro.setVisible(true);
        });

        botaoChamar.addActionListener(e -> {
            consultas.sort(Comparator.comparing(Consulta::getDataConsulta));
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

                proxima.setChamado(true);
                CSVUtils.salvarCSV("consultas.csv", consultas); // CSV continua sendo atualizado
                PersistenciaUtils.salvarLista("consultas.ser", consultas); // Agora também salva binário
                atualizarFila();
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma consulta pendente.");
            }
        });

        atualizarFila();
    }

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