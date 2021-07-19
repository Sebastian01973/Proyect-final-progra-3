package views;

import utilities.Utilities;
import views.dialogs.JDialogTrolley;
import views.dialogs.cash.JDialogCash;
import views.login.JDialogLogin;
import views.panels.JDialogAddCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class JMainWindows extends JFrame implements ILanguage{

    private JMainPanel jMainPanel;
    private JDialogLogin jDialogLogin;
    private JDialogTrolley jDialogTrolley;
    private JDialogAddCard jDialogAddCard;
    private JDialogCash jDialogCash;

    public JMainWindows(ActionListener actionListener, WindowListener windowListener, MouseListener mouseListener) throws HeadlessException {
        this.setIconImage( new ImageIcon(getClass().getResource( Constant.IMG_LOGO)).getImage());
        this.setTitle( Utilities.getKey(Constant.TITLE_STORE));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(windowListener);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setMinimumSize(Constant.SCREEN_SIZE);
        initComponents(actionListener,mouseListener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener actionListener, MouseListener mouseListener) {
        jDialogTrolley = new JDialogTrolley(actionListener,mouseListener,this);
        jDialogLogin = new JDialogLogin(actionListener,this);
        jDialogAddCard = new JDialogAddCard(actionListener,this);
        jDialogCash = new JDialogCash(mouseListener,this);
        jMainPanel = new JMainPanel(actionListener,mouseListener);
        this.getContentPane().add(jMainPanel,BorderLayout.CENTER);
    }

    public void deleteAll(){
        jDialogTrolley.deleteAll();
    }

    public void setVisibleCash(boolean status){
        jDialogCash.setVisible(status);
    }

    public void setVisiblecart(boolean status){
        jDialogTrolley.setVisible(status);
    }

    public void setVisibleLeft(boolean visibleLeft){
        jMainPanel.setVisibleLeft(visibleLeft);
    }

    public int getPositionLeft(){
        return jMainPanel.getPositionLeft();
    }

    public void setVisibleJDialogLogin(boolean visible){
        jDialogLogin.setVisible(visible);
    }


    public void setLogin(){
        jDialogLogin.setLogin();
    }

    public void showStores(String command){
        jMainPanel.showStores(command);
    }

    public String[] getDatesLogin(){
        return jDialogLogin.getDatesLogin();
    }

    public String[] getDateRegister(){
        return jDialogLogin.getDateRegister();
    }

    public void setNickName(){
        jDialogLogin.setNickName();
    }

    public void setValueRegister(){
        jDialogLogin.setValueRegister();
    }


    public void setVisiblejDialogAddCard(boolean status){
        jDialogAddCard.setVisible(status);
    }

    public void setIconImageProduct(ImageIcon image){
        jDialogAddCard.setIconImageProduct(image);
    }

    public void addObjectProduct(Object[] objects){
        jDialogAddCard.addObjectsProduct(objects);
    }

    public Object[] getProductAddCart(){
        return jDialogAddCard.getProductAddCart();
    }

    public void setTotalTrolley(double total){
        jDialogTrolley.setTotal(total);
    }

    public void addProductPanels(ArrayList<Object[]> arrayObjects,int panel){
       jMainPanel.addProductPanels(arrayObjects,panel);
    }

    public void addProductThenCard(Object[] objects){
        jDialogTrolley.addProduct(objects);
    }

    public void deletePanelProduct(String nameProduct){
        jDialogTrolley.deletePanelProduct(nameProduct);
    }

    public void setDateClient(String nick,String value){
        jMainPanel.setDateClient(nick,value);
    }

    @Override
    public void changeLanguage() {
        this.setTitle(Utilities.getKey(Constant.TITLE_STORE));
        jMainPanel.changeLanguage();
        jDialogLogin.changeLanguage();
        jDialogTrolley.changeLanguage();
        jDialogAddCard.changeLanguage();
    }
}
