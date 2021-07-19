package views;

import views.store.JContainerCard;
import views.store.JLeftPanel;
import views.store.JNorthPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Logger;

public class JMainPanel extends JPanel implements ILanguage{

    private JNorthPanel jNorthPanel;
    private JContainerCard jCardStore;
    private JLeftPanel jLeftPanel;

    public JMainPanel(ActionListener actionListener, MouseListener mouseListener) {
        this.setBackground(Constant.COLOR_WHITE);
        this.setLayout(new BorderLayout());
        initComponents(actionListener,mouseListener);
    }

    private void initComponents(ActionListener actionListener, MouseListener mouseListener) {
        jNorthPanel = new JNorthPanel(actionListener);
        this.add(jNorthPanel,BorderLayout.NORTH);

        jCardStore = new JContainerCard(actionListener,mouseListener);
        this.add(jCardStore,BorderLayout.CENTER);

        jLeftPanel = new JLeftPanel(actionListener);
        this.add(jLeftPanel,BorderLayout.WEST);
    }

    public void setDateClient(String nick,String value){
        jNorthPanel.setDateClient(nick,value);
    }

    public JLeftPanel getjLeftPanel(){
        return jLeftPanel;
    }


    public void setVisibleLeft(boolean visibleLeft){
        jLeftPanel.setVisibleLeft(visibleLeft);
    }

    public int getPositionLeft(){
        return jLeftPanel.getPositionLeft();
    }

    public void showStores(String command){
        jCardStore.showStores(command);
    }

    public void addProductPanels(ArrayList<Object[]> arrayObjects,int panel){
        jCardStore.addProductPanels(arrayObjects,panel);
    }

    @Override
    public void changeLanguage() {
        jNorthPanel.changeLanguage();
        jLeftPanel.changeLanguage();
    }
}
