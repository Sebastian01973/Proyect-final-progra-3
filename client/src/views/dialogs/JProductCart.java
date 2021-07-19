package views.dialogs;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.models.JModelButton;
import views.models.JModelLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class JProductCart extends JPanel {

    private JModelLabel imageProduct,nameProduct,priceTotal,price;
    private JModelButton deleteProduct;
    private JModelLabel units;
    private ActionListener actionListener;
    private String name;
    private int unit;

    public JProductCart(ActionListener actionListener,MouseListener mouseListener,Object[] objects) {
        this.actionListener = actionListener;
        this.setLayout(new FlowLayout());
        this.setBackground(Constant.COLOR_WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        initComponents(objects,mouseListener);
    }

    public void setUnit(int unit){
        int unitFinal = this.unit + unit;
        units.setText(""+unitFinal);
    }

    private void initComponents(Object[] objects, MouseListener mouseListener) {
        name = String.valueOf(objects[0]);
        double prices = Double.parseDouble(String.valueOf(objects[1]));
        String priceProduct = Utilities.toDecimalFormat(prices);
        unit = Integer.parseInt(String.valueOf(objects[3]));
        String type = String.valueOf(objects[2]);
        double total = Double.parseDouble(String.valueOf(objects[4]));
        String totalPrice = Utilities.toDecimalFormat(total);
        String codeImage = String.valueOf(objects[5]);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        imageProduct = new JModelLabel("/products/"+codeImage,40,40);
        jPanel.add(imageProduct);

        jPanel.add(Box.createRigidArea(new Dimension(20,0)));

        nameProduct = new JModelLabel(name,Constant.FONT_ARIAL_ROUNDER_15,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        nameProduct.setAlignmentY(CENTER_ALIGNMENT);
        jPanel.add(nameProduct);

        jPanel.add(Box.createRigidArea(new Dimension(70,0)));


        price = new JModelLabel(priceProduct,Constant.FONT_ARIAL_ROUNDER_15,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        price.setAlignmentY(CENTER_ALIGNMENT);
        jPanel.add(price);

        jPanel.add(Box.createRigidArea(new Dimension(50,0)));

        units = new JModelLabel(""+unit,Constant.FONT_ARIAL_ROUNDER_15,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        units.setAlignmentY(CENTER_ALIGNMENT);
        jPanel.add(units);

        jPanel.add(Box.createRigidArea(new Dimension(100,0)));

        priceTotal = new JModelLabel(totalPrice,Constant.FONT_ARIAL_ROUNDER_15,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        priceTotal.setAlignmentY(CENTER_ALIGNMENT);
        jPanel.add(priceTotal);

        this.add(jPanel);

        this.add(Box.createRigidArea(new Dimension(50,0)));

        deleteProduct = new JModelButton(Constant.IMG_DELETE,20,20,15,15);
        deleteProduct.addMouseListener(mouseListener);
        deleteProduct.setNameProduct(name);
        deleteProduct.setColorPaint(Color.WHITE);
        deleteProduct.setBackground(Constant.COLOR_RED_LIGHT_1);
        this.add(deleteProduct);

    }

    public String getNameProduct(){
        return nameProduct.getText();
    }
}
