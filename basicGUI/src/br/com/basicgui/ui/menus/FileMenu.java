package br.com.basicgui.ui.menus;

import br.com.basicgui.ui.MainFrame;

import javax.swing.*;

public class FileMenu extends JMenu{
    public FileMenu(MainFrame frame){
        super("File");

        //Open file
        JMenuItem openItem = new JMenuItem("Open file");
        openItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "TO DO: implementar abertura de arquivo");
        });

        //Close file
        JMenuItem closeItem = new JMenuItem("Close file");
        closeItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "File closed.");
            frame.clearFileContent();
        });

        //Adds
        add(openItem);
        add(closeItem);
        addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        add(exitItem);
    }
}