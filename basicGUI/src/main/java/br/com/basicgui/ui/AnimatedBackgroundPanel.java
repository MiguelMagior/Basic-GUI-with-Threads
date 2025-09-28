package br.com.basicgui.ui;

import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.basicgui.utils.GradientFactory;
import br.com.basicgui.utils.GradientFactory.GradientType;

public class AnimatedBackgroundPanel extends JPanel {
	private Color color1 = Color.BLACK;
	private Color color2 = Color.WHITE;
	private float animationProgress = 0f;
	private float animationSpeed = 0.01f;
	private boolean running = false;
	private Thread animationThread;
	private GradientType gradientType = GradientType.DIAGONAL_LINEAR;

	public AnimatedBackgroundPanel() {}

	public AnimatedBackgroundPanel(Color color1, Color color2) {
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	protected void paintComponent(Graphics graphic) {
	    super.paintComponent(graphic);
	    
	    Graphics2D graphic2d = (Graphics2D) graphic;
	    
	    Color currentColor1 = interpolateColor(color1, color2, animationProgress);
	    Color currentColor2 = interpolateColor(color2, color1, animationProgress);
	    
	    int width = getWidth();
	    int height = getHeight();
	    Paint gradient = null;
	    
	    switch(gradientType) {
	    case DIAGONAL_LINEAR:
	    	gradient = GradientFactory.createDiagonalLinear(width, height, currentColor1, currentColor2);
	    	break;
	    case BASIC_RADIAL:
	    	Point2D center = new Point2D.Float(getWidth() / 2f, getHeight() / 2f);
		    float radius = Math.max(getWidth(), getHeight());
		    
	    	gradient = GradientFactory.createRadial(center, radius, currentColor1, currentColor2);
	    	break;
	    case VERTICAL_LINEAR:
	    	gradient = GradientFactory.createVerticalLinear(width, currentColor1, currentColor2);
	    	break;
		default:
			gradient = GradientFactory.createDiagonalLinear(width, height, currentColor1, currentColor2);
	    }
	    
	    if(running) {
	    	graphic2d .setPaint(gradient);
		    graphic2d .fillRect(0, 0, getWidth(), getHeight()); 
	    }
	}

	public void startAnimation() {
		if (running) return;

		running = true;
		animationThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (running) {
					animationProgress += animationSpeed;
					if (animationProgress > 1f || animationProgress < 0) {
						animationSpeed *= -1;
					}

					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							repaint();
						}
					});

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						break;
					}
				}
			}
		});

		animationThread.start();
	}

	public void stopAnimation() {
		running = false;
		if (animationThread != null) {
			animationThread.interrupt();
			try {
				animationThread.join(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			animationThread = null;
		}
	}

	private Color interpolateColor(Color start, Color end, float progress) {
		int red = (int) (start.getRed() + (end.getRed() - start.getRed()) * progress);
		int green = (int) (start.getGreen() + (end.getGreen() - start.getGreen()) * progress);
		int blue = (int) (start.getBlue() + (end.getBlue() - start.getBlue()) * progress);

		red = Math.max(0, Math.min(255, red));
		green = Math.max(0, Math.min(255, green));
		blue = Math.max(0, Math.min(255, blue));

		return new Color(red, green, blue);
	}

	public void setColors(Color color1, Color color2) {
		this.color1 = color1;
		this.color2 = color2;
		repaint();
	}

	public void setAnimationSpeed(float animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

	public void setGradientType(GradientType gradientType) {
		this.gradientType = gradientType;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	

}