package br.com.basicgui.ui;

import javax.swing.*;
import java.awt.*;

public class AnimatedBackgroundPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        //Temporario
        GradientPaint gp = new GradientPaint(0, 0, new Color(173, 216, 230, 180), 0, getHeight(), new Color(135, 206, 250, 120));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }

    public void startAnimation(){
        //TO DO: implementar animação thread
    }

    public void stopAnimation(){
        //TO DO: implementar parada de animação thread
    }
}