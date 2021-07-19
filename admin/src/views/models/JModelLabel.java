package views.models;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class JModelLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;

	public JModelLabel() {

	}

	/**
	 * @descprtion Este metodo nos agrega una imagen a un label
	 * @param
	 */
	public JModelLabel(String imagePath) {
		ImageIcon image;
		this.setSize(250,250);
		image = new ImageIcon(getClass().getResource(imagePath));
		Icon icono = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), 1));
		this.setIcon(icono);
	}

	public void setIconRelative(String imagePath){
		this.setSize(250,250);
		ImageIcon fot = new ImageIcon(getClass().getResource(imagePath));
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(this.getWidth(), this.getHeight(), 1));
		this.setIcon(icono);
	}

	public void setIconImageProduct(ImageIcon image) {
		Icon icono = image;
		this.setIcon(icono);
	}


	public void setIcon(String imagePath){
		this.setSize(250,250);
		ImageIcon fot = new ImageIcon(imagePath);
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(this.getWidth(), this.getHeight(), 1));
		this.setIcon(icono);
	}

	public void setIcon(String imagePath, int width, int height){
		ImageIcon image;
		image = new ImageIcon(getClass().getResource(imagePath));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.setIcon(icon);
	}

	public JModelLabel(String imagePath, int width, int height) {
		ImageIcon image;
		image = new ImageIcon(getClass().getResource(imagePath));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.setIcon(icon);
	}
	
	public JModelLabel(String text,String imagePath,Font font,Color foreground) {
		super(text);
		ImageIcon image;
		image = new ImageIcon(getClass().getResource(imagePath));
		Icon icon = new ImageIcon(image.getImage());
		this.setIcon(icon);
		this.setOpaque(false);
		this.setFont(font);
		this.setForeground(foreground);
		this.setHorizontalTextPosition( SwingConstants.CENTER );
        this.setVerticalTextPosition( SwingConstants.BOTTOM );
	}
	
	public JModelLabel(String text, Color fg, Font font) {
		super(text);
		this.setOpaque(false);
		this.setFont(font);
		this.setForeground(fg);
	}

	public JModelLabel(String text,String imgPath,Font font,Color bg,Color fg,int width,int height){
		super(text);
		ImageIcon imagen = new ImageIcon(getClass().getResource(imgPath));
		Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		this.setIcon(icon);
		this.setHorizontalTextPosition( SwingConstants.CENTER );
		this.setVerticalTextPosition( SwingConstants.BOTTOM );
		this.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		this.setFont(font);
		this.setBackground(bg);
		this.setForeground(fg);
	}


	public JModelLabel(String text, Color bg, Color fg) {
		super(text);
		this.setOpaque(true);
		this.setBackground(bg);
		this.setForeground(fg);
	}

	public JModelLabel(String text,Font font, Color bg, Color fg) {
		super(text);
		this.setHorizontalTextPosition( SwingConstants.CENTER );
		this.setFont(font);
		this.setOpaque(false);
		this.setBackground(bg);
		this.setForeground(fg);
	}
	
	public JModelLabel(String text, Color bg, Color fg, Font font,Border border) {
		super(text);
		this.setOpaque(true);
		this.setBackground(bg);
		this.setForeground(fg);
		this.setFont(font);
		this.setBorder(border);
	}
	
	public JModelLabel(Icon icon) {
		super(icon);
		this.setOpaque(false);
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRoundRect(0, 0, getWidth()-2, getHeight()-1, 20, 20);
		super.paintComponent(g);
	}

	public void setColorPaint(Color newColor){
		this.color = newColor;
	}

	protected void paintBorder(Graphics g) {
		if (color == null){
			g.setColor(color);
			g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 0, 0);
		}else {
			g.setColor(color);
			g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 0, 0);
		}
	}

	public void setIconImage(Image image) {

	}
}
