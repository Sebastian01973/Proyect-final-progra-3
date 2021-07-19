package views.models;

import javax.swing.*;
import java.awt.*;


/**
 * @author Sebastian Felipe Martinez Samaca 
 * @descprtion Esta clase es un modelo de Jmenu
 * @date 20/06/2020
 */
public class JModelMenu extends JMenu{

	/**
	 * @description Constructor con 3 parametros
	 * @param text
	 * @param backGround
	 * @param font
	 */
	public JModelMenu(String text,Color backGround,Font font) {
		super(text);
		this.setBackground(backGround);
		this.setFont(font);
	}
	
	/**
	 * @description Un constructor con 4 parametros
	 * @param text // texto
	 * @param backGround
	 * @param foreGround
	 * @param font
	 */
	public JModelMenu(String text,Color backGround,Color foreGround,Font font) {
		super(text);
		this.setBackground(backGround);
		this.setForeground(foreGround);
		this.setFont(font);
	}

	public JModelMenu(String text,String imgPath,Color backGround,Color foreGround,Font font,int width,int height) {
		super(text);
		ImageIcon image = new ImageIcon(getClass().getResource(imgPath));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		this.setIcon(icon);
		this.setBackground(backGround);
		this.setForeground(foreGround);
		this.setFont(font);
	}
}
