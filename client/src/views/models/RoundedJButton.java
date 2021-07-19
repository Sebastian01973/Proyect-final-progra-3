package views.models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RoundedJButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int arcW;
	private int arcH;
	
	
	public RoundedJButton(String text, String Command, ActionListener actionListener, Color background, Color foreground) {
		super(text);
		this.arcW = 25;
		this.arcH = 25;
		this.setBackground(background);
		this.setContentAreaFilled(false);
		this.setFocusable(false);
		this.setForeground(foreground);

		this.setActionCommand(Command);
		this.addActionListener(actionListener);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setMaximumSize(new Dimension(400,30));
	}
	

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcW, arcH);
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getBackground());
		g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcW, arcH);
	}


	}

