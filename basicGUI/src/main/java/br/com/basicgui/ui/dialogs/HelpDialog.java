package br.com.basicgui.ui.dialogs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class HelpDialog extends JDialog {

    public HelpDialog(JFrame parentFrame) {
        super(parentFrame, "Help", true);

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        Border thinBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);

        JTextArea helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setBackground(new Color(250, 250, 250));

        JScrollPane scrollPane = new JScrollPane(helpText);
        scrollPane.setPreferredSize(new Dimension(460, 200));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(thinBorder,
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        try (InputStream textStream = getClass().getResourceAsStream("/texts/help.txt")) {
            if (textStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(textStream))) {
                    String line;
                    StringBuilder content = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    helpText.setText(content.toString());
                    // Esta linha move o cursor para o início do texto
                    helpText.setCaretPosition(0);
                }
            } else {
                helpText.setText("Error: File 'help.txt' not found.");
                System.err.println("Could not load file: help.txt. File not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        buttonPanel.setBackground(new Color(240, 240, 240));
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parentFrame);
    }
}