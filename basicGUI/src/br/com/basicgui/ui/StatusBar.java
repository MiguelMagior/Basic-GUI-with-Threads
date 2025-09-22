package br.com.basicgui.ui;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel{
    private JLabel messageLabel;
    private JProgressBar progressBar;
    private JLabel iconLabel;

    public StatusBar(){
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEtchedBorder());

        iconLabel = new JLabel();
        iconLabel.setPreferredSize(new Dimension(24, 24));
        add(iconLabel, BorderLayout.WEST);

        messageLabel = new JLabel("Ready");
        add(messageLabel, BorderLayout.CENTER);

        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        add(progressBar, BorderLayout.EAST);
    }

    //Atualiza mensagem da barra de status
    public void setMessage(String message){
        messageLabel.setText(message);
    }

    //Atualiza icone da barra de status
    public void setIcon(Icon icon){
        iconLabel.setIcon(icon);
    }

    //Mostra progresso da barra de status
    public void showProgress(int min, int max){
        progressBar.setMinimum(min);
        progressBar.setMaximum(max);
        progressBar.setValue(min);
        progressBar.setVisible(true);
    }

    //Atualiza barra de status
    public void updateProgress(int value){
        progressBar.setValue(value);
    }

    //esconde barra de progresso
    public void hideProgress(){
        progressBar.setVisible(false);
    }

    //Add a public getter for the maximum value of the progress bar
    public int getProgressMax(){
        return progressBar.getMaximum();
    }
}