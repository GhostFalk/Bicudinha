import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TelaGerenciarConsulta extends JFrame {
    private JTable tabelaConsultas;
    private DefaultTableModel modeloTabela;
    private ArrayList<Consulta> consultas;

    private JTextField campoDiagnosticoEdit;
    private JTextField campoMedicamentoEdit;

    public TelaGerenciarConsulta(ArrayList<Consulta> listaConsultas) {
        this.consultas = listaConsultas;

        setTitle("Gerenciar Consultas");
        setSize(900, 680);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color corFundo = new Color(106, 207, 207);
        Color corBotaoExcluir = new Color(237, 115, 110);
        Color corBotaoEditar = new Color(145, 205, 144);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(corFundo);

        // Título da janela
        JLabel titulo = new JLabel("Consultas Realizadas", SwingConstants.CENTER);
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titulo.setForeground(new Color(65, 52, 40));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Configuração da tabela
        String[] colunas = {"Animal", "Veterinário", "Data e Hora", "Diagnóstico", "Retorno", "Medicamento"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };

        tabelaConsultas = new JTable(modeloTabela);
        tabelaConsultas.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        tabelaConsultas.setRowHeight(100); // Altura padrão
        tabelaConsultas.getTableHeader().setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        // Customiza o renderizador para exibir textos com quebra de linha
        tabelaConsultas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JTextArea area = new JTextArea(value != null ? value.toString() : "");
                area.setWrapStyleWord(true);
                area.setLineWrap(true);
                area.setOpaque(true);
                area.setFont(table.getFont());
                area.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                area.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());

                int altura = area.getPreferredSize().height;
                if (table.getRowHeight(row) < altura) {
                    table.setRowHeight(row, altura);
                }
                return area;
            }
        });

        JScrollPane scroll = new JScrollPane(tabelaConsultas);
        painelPrincipal.add(scroll, BorderLayout.CENTER);

        // Painel para edição dos campos de diagnóstico e medicamento
        JPanel painelEditar = new JPanel(new GridLayout(2, 2, 10, 10));
        painelEditar.setBackground(corFundo);
        painelEditar.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 30));

        campoDiagnosticoEdit = new JTextField();
        campoDiagnosticoEdit.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        campoMedicamentoEdit = new JTextField();
        campoMedicamentoEdit.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        painelEditar.add(new JLabel("Novo Diagnóstico:"));
        painelEditar.add(campoDiagnosticoEdit);
        painelEditar.add(new JLabel("Medicamento Prescrito:"));
        painelEditar.add(campoMedicamentoEdit);

        painelPrincipal.add(painelEditar, BorderLayout.NORTH);

        // Botões de ação
        JButton botaoExcluir = new JButton("Excluir Consulta");
        botaoExcluir.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoExcluir.setBackground(corBotaoExcluir);
        botaoExcluir.setForeground(Color.WHITE);
        botaoExcluir.addActionListener(e -> excluirConsulta());

        JButton botaoEditar = new JButton("Salvar Alterações");
        botaoEditar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoEditar.setBackground(corBotaoEditar);
        botaoEditar.setForeground(Color.WHITE);
        botaoEditar.addActionListener(e -> editarConsulta());

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(Box.createHorizontalStrut(20));
        painelBotoes.add(botaoExcluir);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        add(painelPrincipal);

        atualizarTabela();
    }

    // Preenche a tabela com os dados das consultas
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        for (Consulta c : consultas) {
            String dataFormatada = c.getDataConsulta().toLocalDate().format(formatterData);
            String horaFormatada = c.getHora().format(formatterHora);

            modeloTabela.addRow(new Object[]{
                    c.getAnimal().getNome(),
                    c.getVeterinario().getNome() + " - " + c.getVeterinario().getCrmv(), // <-- aqui
                    dataFormatada + " às " + horaFormatada,
                    c.getDiagnostico(),
                    c.isFoiRetorno() ? "Sim" : "Não",
                    c.getMedicamento() != null ? c.getMedicamento() : ""
            });
        }
    }

    // Remove a consulta selecionada
    private void excluirConsulta() {
        int linha = tabelaConsultas.getSelectedRow();
        if (linha >= 0) {
            int confirmar = JOptionPane.showConfirmDialog(this,
                    "Deseja realmente excluir esta consulta?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                consultas.remove(linha);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }

    // Edita os dados da consulta selecionada
    private void editarConsulta() {
        int linha = tabelaConsultas.getSelectedRow();
        if (linha >= 0) {
            String novoDiag = campoDiagnosticoEdit.getText().trim();
            String novoMed = campoMedicamentoEdit.getText().trim();

            if (novoDiag.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O diagnóstico não pode estar vazio.");
                return;
            }

            Consulta c = consultas.get(linha);
            c.editarConsulta(novoDiag);
            c.setMedicamento(novoMed);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Consulta atualizada com sucesso!");
            campoDiagnosticoEdit.setText("");
            campoMedicamentoEdit.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para editar.");
        }
    }
}
