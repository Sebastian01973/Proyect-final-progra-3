package views.dialogs;

import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.models.JModelLabel;

import javax.swing.*;
import java.awt.*;

public class JNorthPanelCart extends JPanel implements ILanguage {

    private JModelLabel carBuy;
    private JModelLabel nameP,price,unit,subTotal;

    public JNorthPanelCart() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Constant.COLOR_WHITE);
        this.setBorder(null);
        initComponents();
    }

    private void initComponents() {
        carBuy = new JModelLabel(Utilities.getKey(Constant.TXT_TROLLEY),Constant.IMG_BUY,50,50,Constant.FONT_ARIAL_ROUNDER_25,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        carBuy.setAlignmentX(CENTER_ALIGNMENT);
        this.add(carBuy);

        add(Box.createRigidArea(new Dimension(0,20)));

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Constant.COLOR_WHITE);
        jPanel.setBorder(null);
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        jPanel.add(Box.createRigidArea(new Dimension(20,0)));

        nameP = new JModelLabel(Utilities.getKey(Constant.TXT_PRODUCT),Constant.FONT_HELVETICA_17,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        nameP.setAlignmentY(CENTER_ALIGNMENT);
        jPanel.add(nameP);

        jPanel.add(Box.createRigidArea(new Dimension(140,0)));

        price = new JModelLabel(Utilities.getKey(Constant.TXT_PRICE),Constant.FONT_HELVETICA_17,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        price.setAlignmentY(CENTER_ALIGNMENT);
        jPanel.add(price);

        jPanel.add(Box.createRigidArea(new Dimension(65,0)));

        unit = new JModelLabel(Utilities.getKey(Constant.TXT_AMOUNT),Constant.FONT_HELVETICA_17,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        unit.setAlignmentY(CENTER_ALIGNMENT);
        jPanel.add(unit);

        jPanel.add(Box.createRigidArea(new Dimension(110,0)));

        subTotal = new JModelLabel(Utilities.getKey(Constant.TXT_SUBTOTAL),Constant.FONT_HELVETICA_17,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        subTotal.setAlignmentY(CENTER_ALIGNMENT);
        jPanel.add(subTotal);

        jPanel.add(Box.createRigidArea(new Dimension(30,0)));

        this.add(jPanel);
    }

    @Override
    public void changeLanguage() {
        carBuy.setText(Utilities.getKey(Constant.TXT_TROLLEY));
        nameP.setText(Utilities.getKey(Constant.TXT_PRODUCT));
        price.setText(Utilities.getKey(Constant.TXT_PRICE));
        unit.setText(Utilities.getKey(Constant.TXT_AMOUNT));
        subTotal.setText(Utilities.getKey(Constant.TXT_SUBTOTAL));
    }
}
