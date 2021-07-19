package views.models;

import views.Constant;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JModelPasswordField extends JPasswordField{
	
	private static final long serialVersionUID = 1L;
	private TextPrompt placeHolder;
	private Icon icon;

	public JModelPasswordField(String path,int weight,int height,String water, Font font, Color bg, Color fg) {
		setOpaque(false);
		setBackground(bg);
		setFont(font);
		setForeground(Color.black);
		ImageIcon icon = new ImageIcon(getClass().getResource(path));
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(weight, height, Image.SCALE_DEFAULT));
		this.setIcon(icon);
		Border empty = new EmptyBorder(5,25,5,25);
		setBorder(empty);
		placeHolder = new TextPrompt(water, this);
		placeHolder.setForeground(fg);
		setColumns(22);
	}

	public JModelPasswordField(String water, Font font, Color bg, Color fg) {
		setOpaque(false);
		setBackground(bg);
		setFont(font);
		setForeground(Color.black);
		Border empty = new EmptyBorder(5,25,5,25);
		setBorder(empty);
		placeHolder = new TextPrompt(water, this);
		placeHolder.setForeground(fg);
		setColumns(22);
	}

	public void setPlaceHolder(String water){
		placeHolder.setText(water);
	}

	public void setIcon(Icon newIcon) {
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

}
