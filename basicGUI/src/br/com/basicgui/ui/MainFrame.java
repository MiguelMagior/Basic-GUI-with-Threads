package br.com.basicgui.ui;

import br.com.basicgui.ui.menus.FileMenu;
import br.com.basicgui.ui.menus.ConfigMenu;
import br.com.basicgui.ui.menus.HelpMenu;
import br.com.basicgui.utils.FileUtils;

import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame{
    private JTextArea textArea;
    private StatusBar statusBar; // Corrected type and declaration

    public MainFrame(){
        setTitle("Projeto 2025 - Basic GUI with Threads");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Central panel
        AnimatedBackgroundPanel backgroundPanel = new AnimatedBackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        add(backgroundPanel, BorderLayout.CENTER);

        //Status bar config
        statusBar = new StatusBar(); // Correctly initialize the class variable
        add(statusBar, BorderLayout.SOUTH);

        //Menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new FileMenu(this));
        menuBar.add(new ConfigMenu(this));
        menuBar.add(new HelpMenu(this));
        setJMenuBar(menuBar);
    }

    //Atualiza conteudo do JTextArea
    public void showFileContent(String content){
        textArea.setText(content);
        statusBar.setMessage("File loaded");
    }

    public void clearFileContent(){
        textArea.setText("");
        statusBar.setMessage("File closed");
    }

    //Ler arquivo e atualizar barra de progresso
    public void loadFileWithProgress(File file){
        SwingWorker<String, Integer> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception{
                //Simula leitura de arquivo linha a linha
                StringBuilder content = new StringBuilder();
                String[] lines = FileUtils.readFileLines(file);
                int total = lines.length;
                statusBar.showProgress(0, total);

                for(int i = 0; i < total; i++){
                    content.append(lines[i]).append("\n");
                    Thread.sleep(50);
                    publish(i + 1);
                }
                return content.toString();
            }

            @Override
            protected void process(List<Integer> chunks){
                int last = chunks.get(chunks.size() - 1); // Fixed index and List type
                statusBar.updateProgress(last);
                statusBar.setMessage("Loading file... " + last + "/" + statusBar.getProgressMax()); // Fixed string concatenation and call
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
                    statusBar.hideProgress();
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
}