package controllers;

import nets.Client;
import persistence.JManagerFile;
import utilities.Utilities;
import views.Constant;
import views.JMainWindows;
import views.dialogs.cash.JPanelCash;
import views.models.JModelButton;
import views.panels.JPanelProduct;
import views.splash.JSplash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import static javax.swing.JOptionPane.*;

public class Controller implements ActionListener, WindowListener, MouseListener {

    private JMainWindows jMainWindows;
    private ConfigLanguage configLanguage;
    private Client client;
    private String nickClient;
    private JManagerFile jManagerFile;
    private JSplash jSplash;

    public Controller() {
        nickClient = "";
        configLanguage = new ConfigLanguage();
        configLanguage.loadConfiguration();
        client = new Client();
        jManagerFile = new JManagerFile();
        jMainWindows = new JMainWindows(this, this,this);
        configLanguage.setJfWindowsMain(jMainWindows);
        jSplash = new JSplash(jManagerFile,jMainWindows,client);
        jSplash.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Command.valueOf(e.getActionCommand())) {
            case SING_OFF:
                signOff();
                break;
            case B_MENU:
                this.animationPanel();
                break;
            case JP_HOME:
                this.showPanelsProduct(19,0,Command.JP_HOME.toString());
                break;
            case JP_TECH:
                this.showPanelsProduct(17,1,Command.JP_TECH.toString());
                break;
            case JP_GAMES:
                this.showPanelsProduct(17,2,Command.JP_GAMES.toString());
                break;
            case JP_FOOD:
                this.showPanelsProduct(17,4,Command.JP_FOOD.toString());
                break;
            case JP_CLOTHES:
                this.showPanelsProduct(17,3,Command.JP_CLOTHES.toString());
                break;
            case ES_LANGUAGE:
                this.manageChangeLanguageES();
                break;
            case US_LANGUAGE:
                this.manageChangeLanguageUS();
                break;
            case B_EXIT:
                this.exitApp();
                break;
            case B_REGISTER:
                this.createRegister();
                break;
            case B_LOGIN:
                this.login();
                break;
            case B_SHOPPING_CARD:
                this.jMainWindows.setVisiblecart(true);
                break;
            case BUY_CARD:
                this.buyShoppingCard();
                break;
            case CONTINUE_BUY:
                this.jMainWindows.setVisiblecart(false);
                break;
            case BUY_TOTAL_PRODUCTS:
                this.buyTotalProducts();
                break;
            case ADD_CASH:
                this.showAddCash();
                break;
            default:
                Logger.getGlobal().severe("Error");
                break;
        }
    }


    private void getImages(){
        client.writeInt(20);
        String pathImage = client.readString();
        String[] images = jManagerFile.readImages(pathImage);
        int size = Integer.parseInt(images[0]);
        for (int i = 1; i < size+1; i++) {
            File download = new File("images/products/"+images[i]);
            if (!download.exists()){
                client.getImage(images[i]);
            }
        }
    }

    private void signOff() {
        client.writeInt(9);
        client.writeString(nickClient);
        this.jMainWindows.setVisible(false);
        jMainWindows.deleteAll();
        jMainWindows.setTotalTrolley(0);
        this.jMainWindows.setVisibleJDialogLogin(true);
        this.jMainWindows.setLogin();
    }

    private void showAddCash() {
        jMainWindows.setVisibleCash(true);
    }

    private void getDatesClient(){
        client.writeInt(5);
        client.writeString(nickClient);
        double value = client.readDouble();
        String values = Utilities.toDecimalFormat(value);
        jMainWindows.setDateClient(nickClient,values);
    }

    private void buyTotalProducts() {
        System.out.println("Compro todo");
        client.writeInt(6);
        client.writeString(nickClient);
        String verify = client.readString();
        if (verify.equals(ConstantController.WITH_PRODUCT_CLIENT)){
            String money = client.readString();
            if (money.equals(ConstantController.WITH_MONEY)){
                jMainWindows.setVisiblecart(false);
                JOptionPane.showMessageDialog(jMainWindows,Utilities.getKey(Constant.TXT_YES_BUY));
                getDatesClient();
                jMainWindows.deleteAll();
                jMainWindows.setTotalTrolley(0);
            }else if (money.equals(ConstantController.WITHOUT_MONEY)){
                JOptionPane.showMessageDialog(jMainWindows,Utilities.getKey(Constant.TXT_NO_BUY));
            }
        }else if (verify.equals(ConstantController.WITHOUT_PRODUCT_CLIENT)){
            jMainWindows.setVisiblecart(false);
            JOptionPane.showMessageDialog(jMainWindows,Utilities.getKey(Constant.TXT_EMPTY_KART));
        }
    }


    private void showPanelsProduct(int value,int option,String command){
        if (option != 0){
            client.writeInt(value);
            client.writeInt(option);
        }else {
            client.writeInt(value);
        }
        String product = client.readString();
        if (product.equals("{\"Root\":[]}".trim())) {
            Logger.getGlobal().severe("Not Products");
        } else {
            ArrayList<Object[]> products = jManagerFile.readProductToFile(product);
            jMainWindows.addProductPanels(products,option);
        }
        this.jMainWindows.showStores(command);
    }

    private void buyShoppingCard() {
        jMainWindows.setVisiblejDialogAddCard(false);
        JOptionPane.showMessageDialog(jMainWindows,Utilities.getKey(Constant.TXT_PRODUCT_BUY));
        Object[] nameAndUnits = jMainWindows.getProductAddCart();
        String json = jManagerFile.writeProduct(nameAndUnits);
        client.writeInt(4);
        client.writeString(nickClient);
        client.writeString(json);
        double totalProduct = client.readDouble();
        nameAndUnits[4] = totalProduct;
        jMainWindows.addProductThenCard(nameAndUnits);
        double totalBuy = client.readDouble();
        jMainWindows.setTotalTrolley(totalBuy);
    }

    private void animationPanel() {
        int position = jMainWindows.getPositionLeft();
        if (position > 0) {
            jMainWindows.setVisibleLeft(false);
        } else {
            jMainWindows.setVisibleLeft(true);
        }
    }

    private void createRegister() {
        String[] dates = jMainWindows.getDateRegister();
        if (dates != null) {
            client.writeInt(1); //Option Register Client
            client.writeString(dates[2]); //send nick
            String verify = client.readString();
            if (verify.equals(ConstantController.DOES_NOT_CLIENT)) {
                String register = dates[2] + "-" + dates[3] + "-" + dates[0] + "," + dates[1];
                client.writeString(register);
                JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_REGISTER_SUCCESSFUL));
                jMainWindows.setValueRegister();
            } else if (verify.equals(ConstantController.EXIST_CLIENT)) {
                JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_NICK_EXIST));
                jMainWindows.setNickName();
            }
        } else {
            JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_NO_ENTER_DATE));
        }
    }

    private void login() {
        String[] login = jMainWindows.getDatesLogin();
        if (login != null) {
            String user = login[0] + "-" + login[1];
            client.writeInt(2); //Option Verify Client
            client.writeString(user); //send nick
            String verify = client.readString();
            if (verify.equals(ConstantController.ACCEPT_CLIENT)) {
                jMainWindows.setVisibleJDialogLogin(false);
                JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_WELCOME) + login[0]);
                nickClient = login[0];
                getDatesClient();
                this.jMainWindows.setVisible(true);
                jMainWindows.setLogin();
            } else if (verify.equals(ConstantController.ERROR_LOGIN)) {
                JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_INCORRECT_PASSWORD));
                jMainWindows.setLogin();
            } else if (verify.equals(ConstantController.DOES_NOT_CLIENT)) {
                JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_NO_USER_EXIST));
                jMainWindows.setLogin();
            }
        } else {
            showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_NO_ENTER_DATE));
            jMainWindows.setLogin();
        }
    }

    private void exitApp() {
        getImages();
        String[] buttons = {Utilities.getKey(Constant.CLOSE_CONNECTION), Utilities.getKey(Constant.CANCEL)};
        int option = JOptionPane.showOptionDialog(jMainWindows,Utilities.getKey(Constant.QUESTION_CLOSE),Utilities.getKey(Constant.CLOSE), 0, 0, null, buttons, null);
        if (option == 0) {
            client.writeInt(14);
            System.exit(option);
        }
    }


    private void manageChangeLanguageUS() {
        configLanguage.manageChangeLanguageUS();
    }

    private void manageChangeLanguageES() {
        configLanguage.manageChangeLanguageES();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //To do...
    }

    @Override
    public void windowClosing(WindowEvent e) {
        signOff();
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        Component componentClicked = e.getComponent();
        if (componentClicked instanceof JPanelProduct){
            JPanelProduct selectPanelProduct = (JPanelProduct) componentClicked;
            showPanelProduct(selectPanelProduct.getNameProduct());
        }else if (componentClicked instanceof JButton){
            JModelButton buttonDelete = (JModelButton) componentClicked;
            deleteProductKart(buttonDelete.getNameProduct());
        }else if(componentClicked instanceof JPanelCash){
            JPanelCash jPanelCash = (JPanelCash) componentClicked;
            addCashClient(jPanelCash.getValue());
        }
    }

    private void showPanelProduct(String nameProduct){
        client.writeInt(8);
        client.writeString(nameProduct);
        String verify = client.readString();
        if (verify.equals(ConstantController.DOES_DELETE_PRODUCT)){
            String product = client.readString();
            Object[] oProduct = jManagerFile.readProductClient(product);
            jMainWindows.addObjectProduct(oProduct);
            jMainWindows.setVisiblejDialogAddCard(true);
        }else if (verify.equals(ConstantController.DELETE_PRODUCT)){
            JOptionPane.showMessageDialog(jMainWindows,Utilities.getKey(Constant.TXT_DELETE_PRODUCT));
        }
    }

    private void addCashClient(double value) {
        client.writeInt(7);
        client.writeString(nickClient);
        client.writeDouble(value);
        getDatesClient();
    }

    private void deleteProductKart(String nameProduct) {
        client.writeInt(3); // delete Product Kart
        client.writeString(nickClient);
        client.writeString(nameProduct);
        double total = client.readDouble();
        jMainWindows.setTotalTrolley(total);
        jMainWindows.deletePanelProduct(nameProduct);
    }
    

    @Override
    public void windowClosed(WindowEvent e) {
        //To do...
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //To do...
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //To do...
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //To do...
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //To do...
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To do...
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To do...
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To do...
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To do
    }
}
