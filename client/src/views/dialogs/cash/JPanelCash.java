package views.dialogs.cash;

import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.models.JModelLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class JPanelCash extends JPanel {

    private JModelLabel image,price;
    private double value;

    public double getValue() {
        return value;
    }

    public JPanelCash(MouseListener mouseListener, String pathImage, double value, Color color) {
        this.value = value;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBackground(color);
        this.addMouseListener(mouseListener);
        initComponents(pathImage,value);
    }

    private void initComponents(String pathImage, double value) {
        image = new JModelLabel(pathImage,100,100);
        image.setBackground(getBackground());
        image.setColorPaint(getBackground());
        image.setAlignmentX(CENTER_ALIGNMENT);
        this.add(image);

        add(Box.createRigidArea(new Dimension(0,20)));

        price = new JModelLabel(Utilities.toDecimalFormat(value), Constant.FONT_ARIAL_ROUNDER_15,getBackground(),Constant.COLOR_BLACK);
        price.setAlignmentX(CENTER_ALIGNMENT);
        price.setBackground(getBackground());
        price.setColorPaint(getBackground());
        this.add(price);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setOpaque(false);
        g.setColor(getBackground());
        g.fillRoundRect(0,0,getWidth(),getHeight(),25,25);
    }
}
