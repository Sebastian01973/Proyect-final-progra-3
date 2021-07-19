package views.models;

import utilities.Utilities;
import views.Constant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class JModelComboBox<A> extends JComboBox<Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JModelComboBox(Object[] objects, String title, Font font) {

		addItems(objects);
		this.setFont(font);
		this.setBackground(Constant.COLOR_WHITE);
		this.setForeground(Constant.COLOR_BLACK);
		this.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(0,0,0,0),title,
				0,0,Constant.FONT_HELVETICA_13,Constant.COLOR_BLACK));
		setUI(propieties.createUI(getRootPane()));		
	}


	public void setTitleBor(String text){
		this.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(0,0,0,0),text,
				0,0,Constant.FONT_HELVETICA_13,Constant.COLOR_BLACK));
	}

	public void addItems(Object[] objects){
		this.addItem(Utilities.getKey(Constant.M_SELECT_OPTION));
		for (int i = 0; i < objects.length; i++) {
			this.addItem(objects[i]);
		}
	}
	
	public void setItems(Object[] objects) {
		this.removeAllItems();
		this.addItem(Utilities.getKey(Constant.M_SELECT_OPTION));
		for (int i = 0; i < objects.length; i++) {
			this.addItem(objects[i]);
		}
	}
	
	public static class propieties extends BasicComboBoxUI{
		
		public static BasicComboBoxUI createUI(JComponent component) {
			return new propieties();
		}

		@Override
		protected JButton createArrowButton() {
			JModelButton jModelButton = new JModelButton(Constant.IMG_ARROW_DOWN,25,25,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
			jModelButton.setColorPaint(Constant.COLOR_WHITE);
			jModelButton.setMargin(new Insets(0,0,0,0));
//			jModelButton.setBorder(BorderFactory.createLineBorder(Constant.COLOR_WHITE,2,true));
			jModelButton.setContentAreaFilled(false);
			return jModelButton;
		}

		@Override
		public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
			g.setColor(null);
			g.fillRect(bounds.x,bounds.y, bounds.width, bounds.height);
		}

		@Override
		protected ListCellRenderer createRenderer() {
		
			return new DefaultListCellRenderer() {

				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus) {
					 super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					 list.setSelectionBackground(null);
					 list.setSelectionForeground(Constant.COLOR_BLACK);
					 if (index !=- 1) {
						 ImageIcon imagen = new ImageIcon(getClass().getResource(Constant.IMG_ARROW_RIGHT));
						 Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
						 setIcon(icon);
					 }
					 return this;
				}
			};
		}
		
		
	}
}
