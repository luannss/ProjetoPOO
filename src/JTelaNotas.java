import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JTelaNotas extends JFrame {

    private List<Notas> nota;
    private JTextArea caixaTexto;
    private JTextField tituloCamp;
    private JButton salvar;
    private JButton editar;
    private JButton deletar; 
    
    public JTelaNotas() {
        nota = new ArrayList<>();

        setTitle("Enjoy Notes - Nova Nota");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout());

        tituloCamp = new JTextField();
        tituloCamp.setBorder(BorderFactory.createTitledBorder("Título"));
        inputPanel.add(tituloCamp, BorderLayout.NORTH);

        caixaTexto = new JTextArea();
        JScrollPane sp = new JScrollPane(caixaTexto);
        inputPanel.add(sp, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.CENTER);

        salvar = new JButton("Salvar Nota");
        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloCamp.getText();
                String conteudo = caixaTexto.getText();
                Notas note = new Notas(titulo, conteudo);
                nota.add(note);
                tituloCamp.setText("");
                caixaTexto.setText("");
                JOptionPane.showMessageDialog(JTelaNotas.this, "Nota Salva!");
            }
        });

        editar = new JButton("Editar Nota");
        editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = JOptionPane.showOptionDialog(
                        JTelaNotas.this,
                        "Selecione a nota que deseja editar: ",
                        "Editar Nota",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        nota.toArray(),
                        null
                );

                if (selectedIndex >= 0) {
                    Notas selectedNote = nota.get(selectedIndex);
                    tituloCamp.setText(selectedNote.getTitulo());
                    caixaTexto.setText(selectedNote.getContent());
                    nota.remove(selectedNote);
                }
            }
        });

        deletar = new JButton("Deletar Nota"); 
        deletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = JOptionPane.showOptionDialog(
                        JTelaNotas.this,
                        "Selecione a nota que deseja excluir: ",
                        "Excluir Nota",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        nota.toArray(),
                        null
                );

                if (selectedIndex >= 0) {
                    Notas selectedNote = nota.get(selectedIndex);
                    nota.remove(selectedNote);
                    JOptionPane.showMessageDialog(JTelaNotas.this, "Nota Deletada!");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(salvar);
        buttonPanel.add(editar);
        buttonPanel.add(deletar); 

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JTelaNotas app = new JTelaNotas();
                app.setVisible(true);
            }
        });
    }
}

