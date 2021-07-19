package views.login;

import views.models.TextPrompt;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JTextFieldModel extends JTextField{
	
	private Icon icon;

	private static final long serialVersionUID = 1L;

	public JTextFieldModel( String name) {

		setOpaque(false);
		setForeground(Color.black);
		Border empty = new EmptyBorder(5,25,5,25);
		setBorder(empty);
//		setHorizontalAlignment(CENTER);
		new TextPrompt(name, this);
		
	}

//	public JTextFieldModel() {
//		setBorder(null);
////		setBackground(new Color(33,44,62));
//		setBackground(Color.white);
//		setForeground(Color.white);
//	}
	
	public void setIcon(Icon newIcon){ 
		 this.icon = newIcon; 
		} 
	
	@Override
	
	protected void paintComponent(Graphics g) {
	
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2.setColor(new Color(33,44,63));
		g2.setColor(Color.decode("#EEEEEE"));
//		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0,0,getWidth(),getHeight(),40,40); 
//	    g2.clip(r2d); 
		g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1,20,20);
		if(this.icon!=null){ 
			   int iconHeight = icon.getIconHeight(); 
			   int x =  5; 
			   int y = (this.getHeight() - iconHeight)/2; 
			   icon.paintIcon(this, g2, x, y); 
			  }
		
		super.paintComponent(g);
		
	}
	
//	protected void paintBorder(Graphics g) {
//		Graphics2D g2 = (Graphics2D) g;
//		g2.setStroke(new BasicStroke(3));
//		g.setColor(new Color(0,88,255));
//		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1,20,20);
//	}
	
	

}
