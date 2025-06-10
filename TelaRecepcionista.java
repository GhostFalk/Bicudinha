import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class TelaRecepcionista extends JFrame {
    private Recepcionista recepcionista;
    private ArrayList<Consulta> consultas;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaConsultas;

    public TelaRecepcionista(Recepcionista recepcionista, ArrayList<Consulta> consultas) {
        this.recepcionista = recepcionista;
        this.consultas = consultas;

        setTitle("🐾 Painel da Recepcionista");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);
        Color corFormulario = new Color(180, 230, 230);
        Color corBotaoAgendar = new Color(145, 205, 144);
        Color corBotaoChamar = new Color(237, 115, 110);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);

        JLabel imagemTopo = new JLabel(new ImageIcon(new ImageIcon("gatinho.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        imagemTopo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(imagemTopo);

        JLabel titulo = new JLabel("Painel da Recepcionista");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(10));

        JPanel painelFormulario = new JPanel(new BorderLayout());
        painelFormulario.setMaximumSize(new Dimension(700, 320));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        painelFormulario.setBackground(corFormulario);

        modeloLista = new DefaultListModel<>();
        listaConsultas = new JList<>(modeloLista);
        listaConsultas.setFont(new Font("Monospaced", Font.PLAIN, 16));
        listaConsultas.setFixedCellHeight(30);
        listaConsultas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelCabecalho = new JLabel("📅 Data e Hora       | 🐾 Animal   | 👩‍⚕️ Veterinário   | ⏱️ Status", JLabel.LEFT);
        labelCabecalho.setFont(new Font("Monospaced", Font.BOLD, 16));
        labelCabecalho.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        painelFormulario.add(labelCabecalho, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(listaConsultas);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        painelFormulario.add(scroll, BorderLayout.CENTER);

        painelPrincipal.add(painelFormulario);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        JButton botaoAgendar = new JButton();
        botaoAgendar.setText("📅 Agendar Consulta de Teste");
        JButton botaoChamar = new JButton();
        botaoChamar.setText("🔔 Chamar Próximo Cliente");
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
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("hamster.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.WEST);
        painelInferior.add(new JLabel(new ImageIcon(new ImageIcon("dogo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))), BorderLayout.EAST);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        add(painelPrincipal);

        botaoAgendar.addActionListener(e -> {
            Cliente dono = new Cliente("João", "joao@email.com", "111.222.333-44", "11991234567");
            Animal animal = new Animal("Biscoito", "Gato", 3, "SRD", "Macho", 4.2, dono);
            Veterinario vet = new Veterinario("Dra. Ana", "ana@vet.com", "12345678900", "11999887766", "4321", "Clínica Geral");
            LocalDateTime dataHora = LocalDateTime.now().plusMinutes(consultas.size() * 15); // simula horários escalonados
            Consulta consulta = new Consulta(dataHora, animal, vet, "Check-up", false);
            consultas.add(consulta);
            recepcionista.agendarConsulta(consulta);
            atualizarFila();
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
            String linha = String.format("📅 %-16s | 🐾 %-8s | 👩‍⚕️ %-10s | %s",
                    c.getDataConsulta().format(formatter),
                    c.getAnimal().getNome(),
                    c.getVeterinario().getNome(),
                    c.foiChamado() ? "✅ Chamado" : "⏳ Pendente");
            modeloLista.addElement(linha);
        }
    }

    public static void main(String[] args) {
        Recepcionista marina = new Recepcionista("Marina", "marina@email.com", "12345678900", "11988887777", true);
        ArrayList<Consulta> fila = new ArrayList<>();
        SwingUtilities.invokeLater(() -> new TelaRecepcionista(marina, fila).setVisible(true));
    }
}
