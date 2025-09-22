package br.com.basicgui.app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.com.basicgui.ui.MainFrame;

public class Main{
    public static void main(String[] args){
        try{
            //Look and fell (cross platform)
            UIManager.setLookAndFeel("java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

}