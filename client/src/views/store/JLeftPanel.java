package views.store;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.models.JModelButton;
import views.models.JModelButtonMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JLeftPanel extends JPanel implements ILanguage {

    private JModelButtonMenu[] jButtons;
    private JModelButton jBSignOff,jbAddCash;
    private String[] commands = {Command.JP_HOME.toString(),Command.JP_TECH.toString(),Command.JP_GAMES.toString(),
           Command.JP_FOOD.toString(),Command.JP_CLOTHES.toString(),
    };

    public JLeftPanel(ActionListener actionListener) {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Constant.COLOR_GRAY_LIGHT_3);
        this.setBorder(BorderFactory.createEmptyBorder(30,5,5,5));
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        jButtons = new JModelButtonMenu[6];
        for (int i = 0; i < commands.length; i++) {
            jButtons[i] = new JModelButtonMenu(Utilities.getKey(Constant.TXT_MENU[i]),Constant.IMAGES_MENU[i],25,25,true);
            jButtons[i].setActionCommand(commands[i]);
            jButtons[i].setColorNormal(Constant.COLOR_GRAY_LIGHT_3);
            jButtons[i].addActionListener(actionListener);
            jButtons[i].setAlignmentX(CENTER_ALIGNMENT);
            this.add(jButtons[i]);
           add(Box.createRigidArea(new Dimension(0,20)));
        }

        add(Box.createRigidArea(new Dimension(0,80)));

        jbAddCash = new JModelButton(Utilities.getKey(Constant.ADD_CASH),Constant.IMG_CASH,Constant.FONT_ARIAL_ROUNDER_15,30,30,Constant.COLOR_WHITE,Constant.COLOR_BLUE_DARK_1);
        jbAddCash.setColorPaint(Constant.COLOR_GRAY_LIGHT_3);
        jbAddCash.setBackground(Constant.COLOR_GRAY_LIGHT_3);
        jbAddCash.setActionCommand(Command.ADD_CASH.toString());
        jbAddCash.addActionListener(actionListener);
        jbAddCash.setAlignmentX(CENTER_ALIGNMENT);
        this.add(jbAddCash);

        add(Box.createRigidArea(new Dimension(0,30)));

        jBSignOff = new JModelButton(Utilities.getKey(Constant.TXT_SIGN_OFF),Constant.IMG_SING_OFF,Constant.FONT_ARIAL_ROUNDER_15,30,30,Constant.COLOR_WHITE,Constant.COLOR_BLUE_DARK_1);
        jBSignOff.setColorPaint(Constant.COLOR_GRAY_LIGHT_3);
        jBSignOff.setBackground(Constant.COLOR_GRAY_LIGHT_3);
        jBSignOff.setActionCommand(Command.SING_OFF.toString());
        jBSignOff.addActionListener(actionListener);
        jBSignOff.setAlignmentX(CENTER_ALIGNMENT);
        this.add(jBSignOff);

    }

    public void setVisibleLeft(boolean visibleLeft){
        this.setVisible(visibleLeft);
    }

    public int getPositionLeft(){
        int position = this.getX();
        if (position > -1){
            Utilities.moveLeft(0,-188,2,2,this);
            return 1;
        }else {
            Utilities.moveRight(-188,0,2,2,this);
            return -1;
        }
    }

    @Override
    public void changeLanguage() {
        jBSignOff.setText(Utilities.getKey(Constant.TXT_SIGN_OFF));
        jbAddCash.setText(Utilities.getKey(Constant.ADD_CASH));
        for (int i = 0; i < Constant.IMAGES_MENU.length; i++) {
            jButtons[i].setText(Utilities.getKey(Constant.TXT_MENU[i]));
        }
    }
}
