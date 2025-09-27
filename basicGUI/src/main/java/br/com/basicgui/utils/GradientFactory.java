package main.java.br.com.basicgui.utils;

import java.awt.*;
import java.awt.geom.Point2D;

public class GradientFactory {
	public enum GradientType {
	    BASIC_RADIAL, DIAGONAL_LINEAR, HORIZONTAL_LINEAR, VERTICAL_LINEAR
	}
    
    // Gradiente Linear personalizado
    public static LinearGradientPaint createDiagonalLinear(int width, int height, Color color1, Color color2) {
        return new LinearGradientPaint(
            0, 0, width, height,
            new float[] {0.0f, 0.5f, 1.0f},
            new Color[] {color1, color2, color1}
        );
    }
    
    // Gradiente Linear horizontal
    public static LinearGradientPaint createHorizontalLinear(int width, Color color1, Color color2) {
        return new LinearGradientPaint(
            0, 0, width, 0,
            new float[] {0.0f, 0.3f, 0.7f, 1.0f},
            new Color[] {color1, color2, color1, color2}
        );
    }
    
    // Gradiente Linear vertical
    public static LinearGradientPaint createVerticalLinear(int height, Color color1, Color color2) {
        return new LinearGradientPaint(
            0, 0, 0, height,
            new float[] {0.0f, 0.5f, 1.0f},
            new Color[] {color1, color2, color1}
        );
    }
    
    public static RadialGradientPaint createRadial(Point2D center, float radius, Color color1, Color color2) {
        
        return new RadialGradientPaint(
            center,
            radius,
            new float[] {0.0f, 1.0f},
            new Color[] {color1, color2}
      
        );
    }
}
