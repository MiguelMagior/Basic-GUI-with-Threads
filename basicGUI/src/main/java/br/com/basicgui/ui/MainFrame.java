package main.java.br.com.basicgui.ui;

import main.java.br.com.basicgui.ui.menus.FileMenu;
import main.java.br.com.basicgui.ui.menus.ConfigChangeListener;
import main.java.br.com.basicgui.ui.menus.ConfigMenu;
import main.java.br.com.basicgui.ui.menus.HelpMenu;
import main.java.br.com.basicgui.utils.FileUtils;
import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame implements ConfigChangeListener{
    private JTextArea textArea;
    private StatusBar statusBar;
    private ConfigMenu configMenu;
    private AnimatedBackgroundPanel backgroundPanel; 

    public MainFrame(){
        setTitle("Project 2025 - Basic GUI with Threads");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Central panel
        backgroundPanel= new AnimatedBackgroundPanel();
        //backgroundPanel.setLayout(new BorderLayout());
  
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        add(backgroundPanel, BorderLayout.CENTER);

        //Status bar config
        statusBar = new StatusBar();
        add(statusBar, BorderLayout.SOUTH);
        
        configMenu = new ConfigMenu(this);
        configMenu.addConfigChangeListener(this);
        
        //Menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new FileMenu(this));
        menuBar.add(configMenu);
        menuBar.add(new HelpMenu(this));
        setJMenuBar(menuBar);
        
        
    }

    //Updates JTextArea content
    public void showFileContent(String content){
        textArea.setText(content);
        statusBar.setMessage("File loaded");
    }

    public void clearFileContent(){
        textArea.setText("");
        statusBar.setMessage("File closed");
    }

    //Read file and update progress bar
    public void loadFileWithProgress(File file){
        statusBar.setMessage("Loading file...");
        statusBar.showIndeterminateProgress();

        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception{
                String[] lines = FileUtils.readFileLines(file);
                StringBuilder content = new StringBuilder();
                for(String line : lines) {
                    content.append(line).append("\n");
                }
                return content.toString();
            }

            @Override
            protected void done(){
                try{
                    textArea.setText(get());
                    statusBar.setMessage("File loaded.");
                }catch(Exception e){
                    statusBar.setMessage("Failed to load file");
                    JOptionPane.showMessageDialog(MainFrame.this, "ERROR: " + e.getMessage());
                }finally{
                    statusBar.hideIndeterminateProgress();
                }
            }
        };

        worker.execute();
    }

    public void openFile(){
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            loadFileWithProgress(file);
        }
    }
    
    @Override
    public void onConfigChanged() {
    	if(!backgroundPanel.isRunning()) {
    		backgroundPanel.startAnimation();
    		backgroundPanel.setRunning(true);
    	}
        backgroundPanel.setColors(configMenu.getColor1(), configMenu.getColor2());
        backgroundPanel.setAnimationSpeed(configMenu.getSpeed());
        backgroundPanel.setGradientType(configMenu.getGradientType());
        
        backgroundPanel.repaint();
    }
}