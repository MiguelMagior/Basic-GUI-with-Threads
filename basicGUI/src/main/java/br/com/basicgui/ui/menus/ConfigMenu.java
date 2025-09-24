package main.java.br.com.basicgui.ui.menus;

import main.java.br.com.basicgui.ui.MainFrame;

import javax.swing.*;

public class ConfigMenu extends JMenu{
    public ConfigMenu(MainFrame frame){
        super("Settings");

        //Patterns
        JMenuItem patternItem = new JMenuItem("Pattern");
        patternItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "TO DO: implement patterns adjustments");
        });

        //Colors
        JMenuItem colorsItem = new JMenuItem("Colors");
        colorsItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "TO DO: implement colors adjustments");
        });

        //Speed
        JMenuItem speedItem = new JMenuItem("Speed");
        speedItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "TO DO: implement speed adjustments");
        });

        add(patternItem);
        add(colorsItem);
        add(speedItem);
    }
}