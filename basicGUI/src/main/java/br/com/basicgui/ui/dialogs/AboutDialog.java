package br.com.basicgui.ui.dialogs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parentFrame) {
        super(parentFrame, "About", true);

        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(new Color(240, 240, 240));

        Border thinBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(thinBorder,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        leftPanel.setPreferredSize(new Dimension(150, 200));

        try (InputStream logoStream = getClass().getResourceAsStream("/images/logo.png")) {
            if (logoStream != null) {
                ImageIcon originalIcon = new ImageIcon(logoStream.readAllBytes());
                Image originalImage = originalIcon.getImage();

                int panelWidth = leftPanel.getPreferredSize().width;
                int panelHeight = leftPanel.getPreferredSize().height;

                double aspectRatio = (double) originalImage.getWidth(null) / originalImage.getHeight(null);
                int newWidth = panelWidth;
                int newHeight = (int) (newWidth / aspectRatio);

                if (newHeight > panelHeight) {
                    newHeight = panelHeight;
                    newWidth = (int) (newHeight * aspectRatio);
                }

                Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                JLabel logoLabel = new JLabel(resizedIcon);
                leftPanel.add(logoLabel);
            } else {
                JLabel errorLabel = new JLabel("Image not found");
                leftPanel.add(errorLabel);
                System.err.println("Could not load image: logo.png. File not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(leftPanel, BorderLayout.WEST);

        JTextArea aboutText = new JTextArea();
        aboutText.setEditable(false);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setBackground(new Color(250, 250, 250));

        JScrollPane scrollPane = new JScrollPane(aboutText);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(thinBorder,
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        try (InputStream textStream = getClass().getResourceAsStream("/texts/about.txt")) {
            if (textStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(textStream))) {
                    String line;
                    StringBuilder content = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    aboutText.setText(content.toString());
                    // Esta linha move o cursor para o início do texto
                    aboutText.setCaretPosition(0);
                }
            } else {
                aboutText.setText("Error: File 'about.txt' not found.");
                System.err.println("Could not load file: about.txt. File not found.");
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