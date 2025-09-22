package br.com.basicgui.ui.menus;

import br.com.basicgui.ui.MainFrame;

import javax.swing.*;

public class HelpMenu extends JMenu{
    public HelpMenu(MainFrame frame){
        super("Help");

        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "TO DO: implementar ajuda");
        });

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "TO DO: implementar sobre");
        });

        add(helpItem);
        addSeparator();
        add(aboutItem);
    }
}