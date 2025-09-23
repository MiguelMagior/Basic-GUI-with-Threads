package br.com.basicgui.app;

import javax.swing.SwingUtilities;
import br.com.basicgui.ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}