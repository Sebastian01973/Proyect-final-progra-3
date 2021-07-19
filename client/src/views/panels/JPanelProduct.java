package views.panels;

import utilities.Utilities;
import views.Constant;
import views.models.JModelLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class JPanelProduct extends JPanel{

    private JModelLabel imagePhoto;
    private JModelLabel nameProduct,prices,type;

    public JPanelProduct(MouseListener mouseListener, Object[] objects) {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.addMouseListener(mouseListener);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBackground(null);
        initComponents(objects);
    }

    public String getNameProduct(){
        return nameProduct.getText();
    }

    private void initComponents(Object[] objects) {

        String nameProd = String.valueOf(objects[0]);
        String type1 = String.valueOf(objects[1]).toLowerCase();
        double price1 = Double.parseDouble(String.valueOf(objects[2]));
        String priceFinal = Utilities.toDecimalFormat(price1);
        String nameImage = String.valueOf(objects[5]);

        imagePhoto = new JModelLabel("/products/"+nameImage);
        imagePhoto.setColorPaint(Constant.COLOR_WHITE);
        imagePhoto.setBackground(Constant.COLOR_WHITE);
        this.add(imagePhoto);

        this.add(Box.createRigidArea(new Dimension(0,10)));

        nameProduct = new JModelLabel(nameProd, Constant.FONT_ARIAL_ROUNDER_17, Constant.COLOR_WHITE, Constant.COLOR_BLACK);
        nameProduct.setAlignmentX(CENTER_ALIGNMENT);
        nameProduct.setColorPaint(Constant.COLOR_WHITE);
        this.add(nameProduct);

        this.add(Box.createRigidArea(new Dimension(0,10)));

        type = new JModelLabel(type1, Constant.FONT_ARIAL_ROUNDER_17, Constant.COLOR_WHITE, Constant.COLOR_BLACK);
        type.setColorPaint(Constant.COLOR_WHITE);
        type.setAlignmentX(CENTER_ALIGNMENT);
        this.add(type);

        this.add(Box.createRigidArea(new Dimension(0,10)));

        prices = new JModelLabel(priceFinal, Constant.FONT_ARIAL_ROUNDER_25, Constant.COLOR_WHITE, Constant.COLOR_RED_LIGHT_1);
        prices.setColorPaint(Constant.COLOR_WHITE);
        prices.setAlignmentX(CENTER_ALIGNMENT);
        this.add(prices);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        setOpaque(false);
        g.setColor(Color.WHITE);
        g.fillRoundRect(0,0,getWidth(),getHeight(),55,55);
    }
}
