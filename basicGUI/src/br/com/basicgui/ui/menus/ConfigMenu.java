package br.com.basicgui.ui.menus;

import br.com.basicgui.ui.MainFrame;

import javax.swing.*;

public class ConfigMenu extends JMenu{
    public ConfigMenu(MainFrame frame){
        super("Settings");

        //Padrões
        JMenuItem patternItem = new JMenuItem("Pattern");
        patternItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "TO DO: implementar ajustes de padrões");
        });

        //Cores
        JMenuItem colorsItem = new JMenuItem("Colors");
        colorsItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "TO DO: implementar ajustes de cores");
        });

        //Velocidade
        JMenuItem speedItem = new JMenuItem("Speed");
        speedItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "TO DO: implementar ajustes de padrões");
        });

        add(patternItem);
        add(colorsItem);
        add(speedItem);
    }
}