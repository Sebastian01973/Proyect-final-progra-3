package views.admin;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.models.JModelButton;
import views.models.JModelButtonMenu;
import views.models.JModelLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JNorthPanel extends JPanel implements ILanguage {

    private JModelButton english,spanish;
    private JModelButtonMenu jBMenu;
    private JModelButton clients,products;

    public JNorthPanel(ActionListener actionListener) {
        this.setBackground(Constant.COLOR_BLUE_DARK_2);
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(2,30,2,5));
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        jBMenu = new JModelButtonMenu("",Constant.IMG_MENU,30,30);
        jBMenu.setActionCommand(Command.B_MENU.toString());
        jBMenu.addActionListener(actionListener);
        jBMenu.setColorNormal(Constant.COLOR_BLUE_DARK_2);
        jBMenu.setColorPressed(Constant.COLOR_BLUE_DARK_1);
        jBMenu.setColorHover(Constant.COLOR_BLUE_DARK_3);
        this.add(jBMenu);

        add(Box.createRigidArea(new Dimension((int) (Constant.SCREEN_SIZE.getWidth()*0.01),0)));

        JModelLabel amazon = new JModelLabel(Constant.IMG_AMAZON_ADMIN,150,40);
        amazon.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        this.add(amazon);

        add(Box.createRigidArea(new Dimension((int) (Constant.SCREEN_SIZE.getWidth()*0.2),0)));

        products = new JModelButton(Utilities.getKey(Constant.TXT_PRODUCTS),Constant.IMG_PRODUCTS,Constant.FONT_ARIAL_ROUNDER_15,25,25,Constant.COLOR_BLUE_DARK_2,Constant.COLOR_WHITE);
        products.setActionCommand(Command.T_PRODUCT.toString());
        products.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        products.addActionListener(actionListener);
        this.add(products);

        add(Box.createRigidArea(new Dimension((int) (Constant.SCREEN_SIZE.getWidth()*0.01),0)));

        clients = new JModelButton(Utilities.getKey(Constant.TXT_CLIENTS),Constant.IMG_CLIENTS,Constant.FONT_ARIAL_ROUNDER_15,25,25,Constant.COLOR_BLUE_DARK_2,Constant.COLOR_WHITE);
        clients.setActionCommand(Command.T_CLIENTS.toString());
        clients.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        clients.addActionListener(actionListener);
        this.add(clients);

        add(Box.createRigidArea(new Dimension((int) (Constant.SCREEN_SIZE.getWidth()*0.3),0)));

        english = new JModelButton(Constant.IMG_ENGLISH,30,30);
        english.setBackground(Constant.COLOR_BLUE_DARK_2);
        english.setActionCommand(Command.US_LANGUAGE.toString());
        english.addActionListener(actionListener);
        this.add(english);

        add(Box.createRigidArea(new Dimension(30,0)));

        spanish = new JModelButton(Constant.IMG_SPANISH,30,30);
        spanish.setBackground(Constant.COLOR_BLUE_DARK_2);
        spanish.setActionCommand(Command.ES_LANGUAGE.toString());
        spanish.addActionListener(actionListener);
        this.add(spanish);

        add(Box.createRigidArea(new Dimension(30,0)));

    }

    @Override
    public void changeLanguage() {
        clients.setText(Utilities.getKey(Constant.TXT_CLIENTS));
        products.setText(Utilities.getKey(Constant.TXT_PRODUCTS));
    }
}
