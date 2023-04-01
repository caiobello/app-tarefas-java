import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AplicativoDeTarefas implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JTextField taskField;
    private JButton addButton;
    private JTextArea taskArea;
    private JButton removeButton;

    public AplicativoDeTarefas() {
        // Cria a janela e o painel
        frame = new JFrame("Lista de Tarefas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Cria o campo de texto e botão de adicionar tarefa
        JLabel label = new JLabel("Adicione uma tarefa:");
        label.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label);
        taskField = new JTextField(20);
        panel.add(taskField);
        addButton = new JButton("Adicionar Tarefa");
        addButton.addActionListener(this);
        panel.add(addButton);

        // Cria a área de texto e botão de remover tarefa
        taskArea = new JTextArea(10, 20);
        taskArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taskArea);
        panel.add(scrollPane);
        removeButton = new JButton("Remover Tarefa Selecionada");
        removeButton.addActionListener(this);
        removeButton.setEnabled(false);
        panel.add(removeButton);

        // Define as propriedades da janela
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AplicativoDeTarefas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Adiciona uma nova tarefa na lista de tarefas
            String task = taskField.getText();
            if (!task.isEmpty()) {
                taskArea.append("- " + task + "\n");
                taskField.setText("");
                removeButton.setEnabled(true);
            }
        } else if (e.getSource() == removeButton) {
            // Remove a tarefa selecionada da lista de tarefas
            int index = taskArea.getCaretPosition();
            if (index > 0) {
                try {
                    int start = taskArea.getLineStartOffset(taskArea.getLineOfOffset(index));
                    int end = taskArea.getLineEndOffset(taskArea.getLineOfOffset(index));
                    taskArea.replaceRange("", start, end);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (taskArea.getText().isEmpty()) {
                    removeButton.setEnabled(false);
                }
            }
        }
    }
}
