package views.store;

import controllers.Command;
import views.Constant;
import views.ILanguage;
import views.dialogs.JProductCart;
import views.models.GridModel;
import views.models.JModelButton;
import views.models.JModelButtonMenu;
import views.models.JModelLabel;
import views.panels.JPanelClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JNorthPanel extends JPanel implements ILanguage {

    private JModelButtonMenu jBMenu,jBShoppingCard;
    private JModelButton english,spanish;
    private JModelLabel jLClient;
    private JPanelClient jPanelClient;

    public JNorthPanel(ActionListener actionListener) {
        this.setBackground(Constant.COLOR_BLUE_DARK_2);
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(BorderFactory.createEmptyBorder(2,30,2,5));
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {

        JPanel panelLeft = new JPanel();
        panelLeft.setBackground(Constant.COLOR_BLUE_DARK_2);
        panelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));

        jBMenu = new JModelButtonMenu("",Constant.IMG_MENU,30,30);
        jBMenu.setActionCommand(Command.B_MENU.toString());
        jBMenu.addActionListener(actionListener);
        jBMenu.setColorNormal(Constant.COLOR_BLUE_DARK_2);
        jBMenu.setColorPressed(Constant.COLOR_BLUE_DARK_1);
        jBMenu.setColorHover(Constant.COLOR_BLUE_DARK_3);
        panelLeft.add(jBMenu);

        JModelLabel amazon = new JModelLabel(Constant.IMG_AMAZON_STORE,150,40);
        amazon.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        panelLeft.add(amazon);

        this.add(panelLeft,BorderLayout.WEST);

        jPanelClient = new JPanelClient(Constant.IMG_USER_STORE);
        this.add(jPanelClient,BorderLayout.CENTER);

        JPanel right = new JPanel();
        right.setLayout(new FlowLayout(FlowLayout.CENTER));
        right.setBackground(Constant.COLOR_BLUE_DARK_2);

        english = new JModelButton(Constant.IMG_ENGLISH,30,30);
        english.setBackground(Constant.COLOR_BLUE_DARK_2);
        english.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        english.setActionCommand(Command.US_LANGUAGE.toString());
        english.addActionListener(actionListener);
        right.add(english);


        spanish = new JModelButton(Constant.IMG_SPANISH,30,30);
        spanish.setBackground(Constant.COLOR_BLUE_DARK_2);
        spanish.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        spanish.setActionCommand(Command.ES_LANGUAGE.toString());
        spanish.addActionListener(actionListener);
        right.add(spanish);

        jBShoppingCard = new JModelButtonMenu("",Constant.IMG_CART,30,30);
        jBShoppingCard.setActionCommand(Command.B_SHOPPING_CARD.toString());
        jBShoppingCard.addActionListener(actionListener);
        jBShoppingCard.setColorNormal(Constant.COLOR_BLUE_DARK_2);
        jBShoppingCard.setColorPressed(Constant.COLOR_BLUE_DARK_1);
        jBShoppingCard.setColorHover(Constant.COLOR_BLUE_DARK_3);
        right.add(jBShoppingCard);

        this.add(right,BorderLayout.EAST);
    }

    public void setDateClient(String nick,String value){
        jPanelClient.setValues(nick,value);
    }

    @Override
    public void changeLanguage() {

    }
}
