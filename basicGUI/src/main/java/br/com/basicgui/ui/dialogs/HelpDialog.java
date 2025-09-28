package br.com.basicgui.ui.dialogs;

import br.com.basicgui.ui.dialogs.TutorialPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class HelpDialog extends JDialog {

    public HelpDialog(JFrame parentFrame) {
        // Atualizamos o título do diálogo para refletir as duas funcionalidades
        super(parentFrame, "Help & Tutorial", true);

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        Border thinBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);

        // --- 1. Configuração do Painel de Ajuda Textual ---
        JTextArea helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setBackground(new Color(250, 250, 250));

        JScrollPane helpScrollPane = new JScrollPane(helpText);
        // Define um tamanho padrão que o TutorialPanel também deve usar para consistência
        helpScrollPane.setPreferredSize(new Dimension(650, 450));
        helpScrollPane.setBorder(BorderFactory.createCompoundBorder(thinBorder,
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Carregar o conteúdo de help.txt
        try (InputStream textStream = getClass().getResourceAsStream("/texts/help.txt")) {
            if (textStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(textStream))) {
                    String line;
                    StringBuilder content = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    helpText.setText(content.toString());
                    helpText.setCaretPosition(0);
                }
            } else {
                helpText.setText("Error: File 'help.txt' not found.");
                System.err.println("Could not load file: help.txt. File not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- 2. Criar o Painel de Tutorial com Imagens ---
        // É necessário que você tenha criado a classe TutorialPanel (que extende JPanel)
        TutorialPanel tutorialPanel = new TutorialPanel();


        // --- 3. Criar o JTabbedPane (Painel de Abas) ---
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Help Text", helpScrollPane);
        // Adicionamos o novo painel na segunda aba
        tabbedPane.addTab("Tutorial (See Images)", tutorialPanel);

        // Adicionar o JTabbedPane ao centro do diálogo
        add(tabbedPane, BorderLayout.CENTER);


        // --- 4. Painel de Botões (Rodapé) ---
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // Removemos o botão 'See Images' pois a função é executada pelas abas do JTabbedPane
        buttonPanel.add(closeButton);
        buttonPanel.setBackground(new Color(240, 240, 240));
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parentFrame);
    }
}