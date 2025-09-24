package main.java.br.com.basicgui.ui.menus;

import main.java.br.com.basicgui.ui.MainFrame;
import main.java.br.com.basicgui.ui.dialogs.AboutDialog;
import main.java.br.com.basicgui.ui.dialogs.HelpDialog;

import javax.swing.*;

public class HelpMenu extends JMenu{
    public HelpMenu(MainFrame frame){
        super("Help");

        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener(e -> {
            HelpDialog helpDialog = new HelpDialog(frame);
            helpDialog.setVisible(true);
        });

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> {
            AboutDialog aboutDialog = new AboutDialog(frame);
            aboutDialog.setVisible(true);
        });

        add(helpItem);
        addSeparator();
        add(aboutItem);
    }
}