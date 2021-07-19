package views.dialog;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.JMainWindow;
import views.models.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogProduct extends JDialog implements ILanguage {

    private JPanel jContainerLogin;
    private JModelLabel imagePhoto;
    private JModelButton changePhoto;
    private JSpinner units,prices;
    private JModelComboBox<Object> jCBType;
    private JModelButton delete,modify;
    private JModelLabel nameProduct;
    private GridModel gridModel;

    private Object[] objects;

    public JDialogProduct(ActionListener actionListener, JMainWindow jMainWindows) {
        this.setModal(true);
        this.objects = new Object[4];
        setTitle(Utilities.getKey(Constant.TXT_PRODUCT));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setIconImage( new ImageIcon(getClass().getResource( Constant.IMG_MODIFY)).getImage());
        this.getContentPane().setBackground(Constant.COLOR_BLUE_DARK_2);
        this.setSize(new Dimension(600,700));
        this.setResizable(false);
        this.setLocationRelativeTo(jMainWindows);
        initComponents(actionListener);
    }

    public int getSelectType(String value){
        switch (value){
            case "TECHNOLOGY": return 1;
            case "GAMES": return 2;
            case "CLOTHES": return 3;
            case "FOOD": return 4;
            default: return -1;
        }
    }

    public void addObjectsProduct(Object[] objects){
        this.objects = objects;
        String name = String.valueOf(objects[0]);
        String type = String.valueOf(objects[1]);
        int unit = Integer.parseInt(String.valueOf(objects[2]));
        double price = Double.parseDouble(String.valueOf(objects[3]));
        nameProduct.setText(name);
        jCBType.setSelectedIndex(getSelectType(type));
        units.setValue(unit);
        prices.setValue(price);
    }

    private void initComponents(ActionListener actionListener) {
        jContainerLogin = new JPanel();
        gridModel = new GridModel(jContainerLogin);
        jContainerLogin.setLayout(new GridBagLayout());
        jContainerLogin.setBorder(new EmptyBorder(0, 5, 0, 5));
        jContainerLogin.setBackground(Constant.COLOR_BLUE_DARK_2);

        JModelLabel amazon = new JModelLabel(Constant.IMG_AMAZON,550,150);
        amazon.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        amazon.setBackground(Constant.COLOR_BLUE_DARK_2);
        jContainerLogin.add(amazon,gridModel.insertComponent(0,0,4,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.BOTH));

        gridModel.addExternalBorder(10,10,10,20);

        imagePhoto = new JModelLabel(Constant.IMG_PHOTO);
        imagePhoto.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        imagePhoto.setBackground(Constant.COLOR_BLUE_DARK_2);
        jContainerLogin.add(imagePhoto,gridModel.insertComponent(1,0,2,4,1,GridBagConstraints.PAGE_START, GridBagConstraints.BOTH));

//        changePhoto = new JModelButton(15,15,Utilities.getKey(Constant.CHANGE_PHOTO),Constant.COLOR_WHITE,Constant.COLOR_BLACK,Constant.FONT_ARIAL_ROUNDER_15, Command.LOAD_IMAGE.toString(),actionListener);
//        changePhoto.setColorPaint(Constant.COLOR_BLUE_DARK_2);
//        changePhoto.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));
//        gridModel.addExternalBorder(10,10,10,20);
//        jContainerLogin.add(changePhoto,gridModel.insertComponent(5,0,2,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        nameProduct = new JModelLabel("",Constant.FONT_ARIAL_ROUNDER_25,Constant.COLOR_BLUE_DARK_2,Constant.COLOR_WHITE);
        nameProduct.setBorder(BorderFactory.createEmptyBorder(10,30,10,10));
        gridModel.addExternalBorder(10,10,10,20);
        jContainerLogin.add(nameProduct,gridModel.insertComponent(1,3,1,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        jCBType = new JModelComboBox<>(Utilities.getKeys(Constant.OPTIONS),Utilities.getKey(Constant.ASSIGN_OPTION), Constant.FONT_HELVETICA_15);
        jCBType.setSelectedIndex(getSelectType(""));
        jContainerLogin.add(jCBType,gridModel.insertComponent(3,3,1,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        prices = new JSpinner(new SpinnerNumberModel(1000, 1000, 10000000, 1000));
        ((DefaultFormatter) ((JSpinner.NumberEditor)prices.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        prices.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_PRICE)));
        prices.setBackground(Constant.COLOR_WHITE);
        jContainerLogin.add(prices,gridModel.insertComponent(2,3,1,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));


        units = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 10));
        ((DefaultFormatter) ((JSpinner.NumberEditor)units.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        units.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_UNITS)));
        units.setBackground(Constant.COLOR_WHITE);
        jContainerLogin.add(units,gridModel.insertComponent(4,3,1,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        modify = new JModelButton(15,15,Utilities.getKey(Constant.TXT_MODIFY),Constant.COLOR_ORANGE_1,Constant.COLOR_WHITE,Constant.FONT_ARIAL_ROUNDER_15,Command.MODIFY_PRODUCT.toString(),actionListener);
        modify.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        modify.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));
        gridModel.addExternalBorder(10,10,10,20);
        jContainerLogin.add(modify,gridModel.insertComponent(6,0,4,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        delete = new JModelButton(15,15,Utilities.getKey(Constant.TXT_DELETE),Constant.COLOR_RED_LIGHT_1,Constant.COLOR_WHITE,Constant.FONT_ARIAL_ROUNDER_15,Command.JD_DELETE_PRODUCT.toString(),actionListener);
        delete.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        delete.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));
        gridModel.addExternalBorder(10,10,10,20);
        jContainerLogin.add(delete,gridModel.insertComponent(7,0,4,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        this.add(jContainerLogin);
    }


    public String nameProductDialog() {
        return nameProduct.getText();
    }

    public void setIconImageProduct(ImageIcon image){
        imagePhoto.setIconImageProduct(image);
    }

    public Object[] modifyProduct(){
        if (jCBType.getSelectedIndex() != 0){
            return new Object[]{
                    nameProduct.getText(),prices.getValue(),jCBType.getSelectedIndex(),
                    units.getValue(),
            };
        }
        return null;
    }

    @Override
    public void changeLanguage() {
        setTitle(Utilities.getKey(Constant.TXT_PRODUCT));
        changePhoto.setText(Utilities.getKey(Constant.CHANGE_PHOTO));
        nameProduct.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_PRODUCT)));
        jCBType.setTitleBor(Utilities.getKey(Constant.ASSIGN_OPTION));
        prices.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_PRICE)));
        units.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_UNITS)));
        modify.setText(Utilities.getKey(Constant.TXT_MODIFY));
        delete.setText(Utilities.getKey(Constant.TXT_DELETE));
    }

}
