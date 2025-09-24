package main.java.br.com.basicgui.ui;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    private JLabel statusLabel;
    private JProgressBar progressBar;

    public StatusBar() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder(
                javax.swing.border.EtchedBorder.LOWERED));

        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);

        add(statusLabel, BorderLayout.WEST);
        add(progressBar, BorderLayout.EAST);
    }

    public void setMessage(String message) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(message));
    }

    public void showProgress(int min, int max) {
        SwingUtilities.invokeLater(() -> {
            progressBar.setIndeterminate(false); // Garante que não está indeterminada
            progressBar.setMinimum(min);
            progressBar.setMaximum(max);
            progressBar.setValue(min);
            progressBar.setVisible(true);
        });
    }

    public void updateProgress(int value) {
        SwingUtilities.invokeLater(() -> progressBar.setValue(value));
    }

    public int getProgressMax() {
        return progressBar.getMaximum();
    }

    public void hideProgress() {
        SwingUtilities.invokeLater(() -> progressBar.setVisible(false));
    }

    public void showIndeterminateProgress() {
        SwingUtilities.invokeLater(() -> {
            progressBar.setIndeterminate(true);
            progressBar.setVisible(true);
        });
    }

    public void hideIndeterminateProgress() {
        SwingUtilities.invokeLater(() -> {
            progressBar.setIndeterminate(false);
            progressBar.setVisible(false);
        });
    }
}