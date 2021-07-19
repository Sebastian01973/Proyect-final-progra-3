package controllers;

import nets.Admin;
import persistence.JManagerFileJson;
import utilities.Utilities;
import views.Constant;
import views.JMainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class Controller implements ActionListener, MouseListener, WindowListener {

    private JMainWindow jMainWindows;
    private ConfigLanguage configLanguage;
    private JManagerFileJson jManagerFileJson;
    private Admin admin;
    File file;

    public Controller() {
        file = null;
        configLanguage = new ConfigLanguage();
        configLanguage.loadConfiguration();
        jMainWindows = new JMainWindow(this, this, this);
        jManagerFileJson = new JManagerFileJson();
        admin = new Admin();
        configLanguage.setJfWindowsMain(jMainWindows);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            Logger.getGlobal().severe(Controller.class.getName());
        }
    }

    private void manageChangeLanguageUS() {
        configLanguage.manageChangeLanguageUS();
    }

    private void manageChangeLanguageES() {
        configLanguage.manageChangeLanguageES();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Command.valueOf(e.getActionCommand())) {
            case US_LANGUAGE:
                this.manageChangeLanguageUS();
                break;
            case ES_LANGUAGE:
                this.manageChangeLanguageES();
                break;
            case B_MENU:
                this.setLeftPanel();
                break;
            case ADD_PRODUCT:
                this.jMainWindows.setVisibleDialogAdd(true);
                break;
            case SAVE_PRODUCT:
                this.addProduct();
                break;
            case LOAD_IMAGE:
                this.loadImage();
                break;
            case T_CLIENTS:
                this.getClients();
                break;
            case T_PRODUCT:
                this.loadProducts();
                break;
            case MODIFY_PRODUCT:
                this.modifyProduct();
                break;
            case JD_DELETE_PRODUCT:
                this.deleteProductDialog();
                break;
            case SEARCH_PRODUCT:
                this.jMainWindows.setVisibleSearchDialog(true);
                break;
            case JD_SEARCH_PRODUCT:
                this.searchProducts();
                break;
            case CANCEL_SEARCH:
                this.jMainWindows.setVisibleSearchDialog(false);
                break;
            case DELETE_PRODUCT:
                this.jMainWindows.setVisibleDeleteDialog(true);
                break;
            case CANCEL_DELETE:
                this.jMainWindows.setVisibleDeleteDialog(false);
                break;
            case DIALOG_DELETE:
                this.deleteDialog();
                break;
            case EXIT:
                this.exitAdmin();
                break;
            default:
                Logger.getGlobal().severe("Error");
                break;
        }
    }

    private void deleteDialog() {
//        System.out.println("Eliminar con dialogo");
        String nameProduct = jMainWindows.getNameProductDelete();
        if (!nameProduct.isEmpty()){
            admin.writeInt(18);
            admin.writeString(nameProduct);
            String verify = admin.readString();
            if (verify.equals(ConstantAdmin.EXIST_PRODUCT)) {
                loadProducts();
                this.jMainWindows.setVisibleDeleteDialog(false);
                JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_DELETE_PRODUCT));
            }else if (verify.equals(ConstantAdmin.DOES_NOT_PRODUCT)){
                JOptionPane.showMessageDialog(jMainWindows,Utilities.getKey(Constant.TXT_NO_EXIST_PRODUCT));
            }
        }else {
            JOptionPane.showMessageDialog(jMainWindows,Utilities.getKey(Constant.TXT_NO_THERE_TEXT));
        }
    }

    private void exitAdmin() {
        admin.writeInt(14);
        System.exit(0);
    }

    private void searchProducts() {
//        System.out.println("Buscar productos");
        int option = jMainWindows.getSelectBoxSearch();
        if (option != 0){
            admin.writeInt(17);
            admin.writeInt(option);
            String json = admin.readString();
            ArrayList<Object[]> arrayList = jManagerFileJson.readProductToFile(json);
            jMainWindows.addElementToTable(arrayList);
            jMainWindows.setVisibleSearchDialog(false);
        }else {
            JOptionPane.showMessageDialog(jMainWindows,Utilities.getKey(Constant.TXT_NO_SELECTED_FILTER));
        }
    }

    private void deleteProductDialog() {
//        System.out.println("Elimino");
        String name = jMainWindows.nameProductDialog();
        admin.writeInt(15); // delete
        admin.writeString(name);
        loadProducts();
        jMainWindows.setVisibleDialogProduct(false);
        JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_DELETE_PRODUCT));
    }

    private void modifyProduct() {
//        System.out.println("Modifico");
        String name = jMainWindows.nameProductDialog();
        admin.writeInt(16); //modify
        admin.writeString(name);
        String jsonProduct = jManagerFileJson.writeProduct(jMainWindows.modifyProduct());
        admin.writeString(jsonProduct);
        loadProducts();
        jMainWindows.setVisibleDialogProduct(false);
        JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_MODIFY_EXIT));

    }

    private void loadImage() {
        file = jMainWindows.loadImage();
        if (file != null) {
            jMainWindows.setIconProduct(file.getPath());
        }
    }

    private void loadProducts() {
//        System.out.println("Cargar productos");
        admin.writeInt(12);
        String products = admin.readString();
        ArrayList<Object[]> arrayList = jManagerFileJson.readProductToFile(products);
        jMainWindows.addElementToTable(arrayList);
    }

    private void getClients() {
//        System.out.println("Clientes");
        admin.writeInt(10);
        String clients = admin.readString();
        ArrayList<Object[]> arrayList = jManagerFileJson.readClientToFile(clients);
        jMainWindows.addRowsToTable(arrayList, Constant.H_CLIENTS);
    }


    private void addProduct() {
//        System.out.println("Agrego los productos");
        Object[] product = jMainWindows.createProduct();
        if (product != null) {
            String nameProduct = String.valueOf(product[0]);
            admin.writeInt(11); //addClient
            admin.writeString(nameProduct);
            String verify = admin.readString();
            if (verify.equals(ConstantAdmin.DOES_NOT_PRODUCT)) {
                String jsonProduct = jManagerFileJson.writeProduct(product);
                admin.writeString(jsonProduct);
                if (file != null) {
                    admin.writeString(ConstantAdmin.WITH_IMAGE);
                    admin.sendFile(file);
                } else {
                    admin.writeString(ConstantAdmin.WITHOUT_IMAGE);
                }
                this.jMainWindows.setVisibleDialogAdd(false);
                JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_ADD_PRODUCT));
                jMainWindows.resetDatesAddProduct(false);
            } else if (verify.equals(ConstantAdmin.EXIST_PRODUCT)) {
                JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_EXIST_PRODUCT));
                jMainWindows.resetDatesAddProduct(true);
            }
        } else {
            JOptionPane.showMessageDialog(jMainWindows, Utilities.getKey(Constant.TXT_NOT_ALL_TEXT));
        }
    }

    private void setLeftPanel() {
        int position = jMainWindows.getPositionLeft();
        if (position > 0) {
            jMainWindows.setVisibleLeft(false);
        } else {
            jMainWindows.setVisibleLeft(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (jMainWindows.getColumnCountTable() == Constant.H_PRODUCTS.length){
            Point value = e.getPoint();
            String nameProduct = jMainWindows.getSelectedRow(value);
            admin.writeInt(13);
            admin.writeString(nameProduct);
            String json = admin.readString();
            Object[] objects = jManagerFileJson.readProduct(json);
            jMainWindows.addObjectProduct(objects);
            try {
                ImageIcon image = admin.readImage(250,250);
                jMainWindows.setIconImageProduct(image);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            jMainWindows.setVisibleProductDialog(true);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String[] buttons = {Utilities.getKey(Constant.CLOSE_CONNECTION),Utilities.getKey( Constant.CANCEL)};
        int option = JOptionPane.showOptionDialog(jMainWindows, Utilities.getKey(Constant.QUESTION_CLOSE),Utilities.getKey( Constant.CLOSE), 0, 0, null, buttons, jMainWindows);
        if (option == 0) {
            admin.writeInt(14);
            System.exit(option);
        }
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
        //To do...
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //To do...
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
}
