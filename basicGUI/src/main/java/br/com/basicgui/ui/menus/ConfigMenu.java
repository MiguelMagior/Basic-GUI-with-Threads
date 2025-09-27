package main.java.br.com.basicgui.ui.menus;

import main.java.br.com.basicgui.ui.MainFrame;
import main.java.br.com.basicgui.utils.GradientFactory.GradientType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ConfigMenu extends JMenu{
    private float speed = 1.0f;
    private Color color1 = Color.BLACK;
    private Color color2 = Color.WHITE;
    private GradientType gradientType;
    private ArrayList<ConfigChangeListener> listeners = new ArrayList<>();

    public ConfigMenu(MainFrame frame){
        super("Settings");

      //Patterns
        JMenuItem patternItem = new JMenuItem("Pattern");
        patternItem.addActionListener(e -> {
            JPanel panel = new JPanel(new GridLayout(4, 1));
            ButtonGroup group = new ButtonGroup();
            
            JRadioButton diagonalLinear = new JRadioButton("Diagonal Linear", gradientType == GradientType.DIAGONAL_LINEAR);
            JRadioButton basicRadial = new JRadioButton("Basic Radial", gradientType == GradientType.BASIC_RADIAL);
            JRadioButton verticalLinear = new JRadioButton("Vertical Linear", gradientType == GradientType.VERTICAL_LINEAR);
            
            group.add(diagonalLinear);
            group.add(basicRadial);
            group.add(verticalLinear);

            panel.add(diagonalLinear);
            panel.add(basicRadial);
            panel.add(verticalLinear);

            
            int option = JOptionPane.showConfirmDialog(
                    frame,
                    panel,
                    "Gradient Pattern",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
            
            if (option == JOptionPane.OK_OPTION) {
                if (diagonalLinear.isSelected()) gradientType = GradientType.DIAGONAL_LINEAR;
                else if (basicRadial.isSelected()) gradientType = GradientType.BASIC_RADIAL;
                else if (verticalLinear.isSelected()) gradientType = GradientType.VERTICAL_LINEAR;
                
                notifyConfigChanged(); 
                JOptionPane.showMessageDialog(frame, "Gradient pattern set to: " + gradientType);
            }
        });

        //Colors
        JMenuItem colorsItem = new JMenuItem("Colors");
        colorsItem.addActionListener(e -> {
            JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
            JButton colorBtn1 = new JButton("Select First Color");
            JButton colorBtn2 = new JButton("Select Second Color");

            // preview das cores escolhidas
            colorBtn1.setBackground(color1);
            colorBtn2.setBackground(color2);

            colorBtn1.addActionListener(ev -> {
                Color chosen = JColorChooser.showDialog(frame, "Choose First Color", color1);
                if (chosen != null) {
                    color1 = chosen;
                    colorBtn1.setBackground(color1);
                }
            });

            colorBtn2.addActionListener(ev -> {
                Color chosen = JColorChooser.showDialog(frame, "Choose Second Color", color2);
                if (chosen != null) {
                    color2 = chosen;
                    colorBtn2.setBackground(color2);
                }
            });

            panel.add(new JLabel("First Color:"));
            panel.add(colorBtn1);
            panel.add(new JLabel("Second Color:"));
            panel.add(colorBtn2);

            int option = JOptionPane.showConfirmDialog(
                    frame,
                    panel,
                    "Color Settings",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
            if (option == JOptionPane.OK_OPTION) {
            	notifyConfigChanged(); 
                JOptionPane.showMessageDialog(frame,
                        "Colors set!\nFirst: " + color1 + "\nSecond: " + color2);
            }
        });

        //Speed
        JMenuItem speedItem = new JMenuItem("Speed");
        speedItem.addActionListener(e -> {
            JSlider slider = new JSlider(50, 200, (int) (speed * 100));
            slider.setMajorTickSpacing(25);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel("Adjust Speed (0.5x - 2.0x):"), BorderLayout.NORTH);
            panel.add(slider, BorderLayout.CENTER);

            int option = JOptionPane.showConfirmDialog(
                    frame,
                    panel,
                    "Speed Settings",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (option == JOptionPane.OK_OPTION) {
                speed = slider.getValue() / 100f;
                notifyConfigChanged(); 
                JOptionPane.showMessageDialog(frame, "Speed set to: " + speed + "x");
            }
        });

        add(patternItem);
        add(colorsItem);
        add(speedItem);
    }
    
    public void addConfigChangeListener(ConfigChangeListener listener) {
        listeners.add(listener);
    }
    
    private void notifyConfigChanged() {
        for (ConfigChangeListener listener : listeners) {
            listener.onConfigChanged();
        }
    }

    // Getters para serem usados no backGround
    public float getSpeed() {
        return speed/100;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }
    
    public GradientType getGradientType() {
        return gradientType;
    }
}