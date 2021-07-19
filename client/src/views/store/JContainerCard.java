package views.store;

import controllers.Command;
import views.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Logger;

public class JContainerCard extends JPanel{

    private CardLayout cardLayout;
    private JContainerProduct jCardHome;
    private JContainerProduct jCardTech;
    private JContainerProduct jCardClothes;
    private JContainerProduct jCardFood;
    private JContainerProduct jCardGames;

    public JContainerCard(ActionListener actionListener, MouseListener mouseListener) {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.setBackground(Constant.COLOR_WHITE);
        initComponents(actionListener,mouseListener);
    }

    private void initComponents(ActionListener actionListener, MouseListener mouseListener) {

        jCardHome = new JContainerProduct(mouseListener,Constant.IMG_FONT_HOME);
        JScrollPane jScrollPaneHome = new JScrollPane(jCardHome);
        jScrollPaneHome.setForeground(Color.white);
        jScrollPaneHome.setBorder(null);
        jScrollPaneHome.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(jScrollPaneHome,Command.JP_HOME.toString());

        jCardTech = new JContainerProduct(mouseListener,Constant.IMG_FONT_TECH);
        JScrollPane jScrollPaneTech = new JScrollPane(jCardTech);
        jScrollPaneTech.setForeground(Color.white);
        jScrollPaneTech.setBorder(null);
        jScrollPaneTech.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(jScrollPaneTech,Command.JP_TECH.toString());

        jCardGames = new JContainerProduct(mouseListener,Constant.IMG_FONT_GAMER);
        JScrollPane jScrollPaneGame = new JScrollPane(jCardGames);
        jScrollPaneGame.setForeground(Color.white);
        jScrollPaneGame.setBorder(null);
        jScrollPaneGame.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(jScrollPaneGame,Command.JP_GAMES.toString());

        jCardFood = new JContainerProduct(mouseListener,Constant.IMG_FONT_FOOD);
        JScrollPane jScrollPaneFood = new JScrollPane(jCardFood);
        jScrollPaneFood.setForeground(Color.white);
        jScrollPaneFood.setBorder(null);
        jScrollPaneFood.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(jScrollPaneFood,Command.JP_FOOD.toString());

        jCardClothes = new JContainerProduct(mouseListener,Constant.IMG_FONT_CLOTHE);
        JScrollPane jScrollPaneClothes = new JScrollPane(jCardClothes);
        jScrollPaneClothes.setForeground(Color.white);
        jScrollPaneClothes.setBorder(null);
        jScrollPaneClothes.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(jScrollPaneClothes,Command.JP_CLOTHES.toString());

    }

    public void addProductPanels(ArrayList<Object[]> arrayObjects,int panel){
        switch (panel){
            case 0:
                jCardHome.addProduct(arrayObjects);
                break;
            case 1:
                jCardTech.addProduct(arrayObjects);
                break;
            case 2:
                jCardGames.addProduct(arrayObjects);
                break;
            case 3:
                jCardClothes.addProduct(arrayObjects);
                break;
            case 4:
                jCardFood.addProduct(arrayObjects);
                break;
            default:
                Logger.getGlobal().severe("Error");
        }
    }

    public void showStores(String command){
        cardLayout.show(this,command);
    }

}
