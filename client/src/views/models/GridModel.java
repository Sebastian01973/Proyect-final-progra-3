package views.models;

import java.awt.*;


public class GridModel {

	public static final Dimension INIT_DIMENTION = new Dimension(0,0);
	
	protected GridBagConstraints constraints;
	
	public GridModel(Container container) {
		container.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
	}


    /**
	 * 
	 * @param row initial value is 1
	 * @param column initial value is 0
	 * @param width number between 1 and 12, is the columns number that fill the component
	 * @param height of the row
	 * @return constrains with the new values
	 */
	public GridBagConstraints insertComponent(int row, int column, int width,int height, double weightx,int anchor,int fill){
		constraints.gridy = row;
		constraints.gridx = column;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.weightx = weightx;
		constraints.anchor = anchor;
		constraints.fill = fill;
		return constraints;
	}

	public GridBagConstraints insertComponent(int row, int column,Insets insets){
		constraints.gridy = row;
		constraints.gridx = column;
		constraints.insets = insets;
		return constraints;
	}

	public GridBagConstraints insertComponent(int row, int column, int width, int height,Insets insets){
		constraints.gridy = row;
		constraints.gridx = column;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.insets = insets;
		return constraints;
	}
	/**
	 * 
	 * Keeps row and height
	 * @param column initial value is 0
	 * @param width number between 1 and 12, is the columns number that fill the component
	 * @param height number between 1 and 12, is the rows number that fill the component
	 * @return constrains with the new values
	 */
	public GridBagConstraints insertComponent(int row,int column, int width, int height){
		constraints.gridx = column;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		return constraints;
	}

	/**
	 * Add a border to the previous component
	 * @param top
	 * @param right
	 * @param bottom
	 * @param left
	 */
	public void addExternalBorder(int top, int right, int bottom, int left){
		constraints.insets = new Insets(top,left,bottom,right);
	}
	
	public void setExternalBorder() {
		constraints.insets = new Insets(0,0,0,0);
	}
}
