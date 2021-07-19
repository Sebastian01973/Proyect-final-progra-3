package views.models;

import views.Constant;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JModelTextField extends JTextField{
	
	private Icon icon;

	private static final long serialVersionUID = 1L;
	private TextPrompt placeHolder;

	public JModelTextField(String path,int weight,int height,String water, Font font, Color bg, Color fg) {
		setOpaque(false);
		setBackground(bg);
		setFont(font);
		setForeground(Color.black);
		ImageIcon image;
		image = new ImageIcon(getClass().getResource(path));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(weight, height, Image.SCALE_SMOOTH));
		this.setIcon(icon);
		Border empty = new EmptyBorder(5,25,5,25);
		setBorder(empty);
		placeHolder = new TextPrompt(water, this);
		placeHolder.setForeground(fg);
		setColumns(22);
	}

	public JModelTextField(String water, Font font, Color bg, Color fg) {
		setOpaque(false);
		setFont(font);
		setBackground(bg);
		setForeground(Color.black);
		Border empty = new EmptyBorder(5,25,5,25);
		setBorder(empty);
		placeHolder = new TextPrompt(water, this);
		placeHolder.setForeground(fg);
		setColumns(22);
		
	}

	public void setIcon(Icon newIcon){ 
		 this.icon = newIcon; 
		} 
	
	@Override
	
	protected void paintComponent(Graphics g) {
	
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Constant.COLOR_GRAY_LIGHT_2);
		g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1,20,20);
		if(this.icon!=null){ 
			   int iconHeight = icon.getIconHeight(); 
			   int x =  5; 
			   int y = (this.getHeight() - iconHeight)/2; 
			   icon.paintIcon(this, g2, x, y); 
			  }
		
		super.paintComponent(g);
		
	}

	public void validateNum(JTextField jTextField) {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se puede ingresar Numeros.");
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
	}

	public void validateText(JTextField jTextField) {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se puede Ingresar Texto");
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
	}

	public void setPlaceHolder(String water){
		placeHolder.setText(water);
	}
}
