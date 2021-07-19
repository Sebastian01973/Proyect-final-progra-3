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

public class JDialogAdd extends JDialog implements ILanguage {


    private JPanel jContainerLogin;
    private JModelLabel imagePhoto;
    private JModelButton addPhoto;
    private JModelTextField nameProduct;
    private JSpinner units,prices;
    private JModelComboBox<Object> jCBType;
    private JModelButton createProduct;
    private  GridModel gridModel;


    public JDialogAdd(ActionListener actionListener, JMainWindow jMainWindows) {
        this.setModal(true);
        setTitle(Utilities.getKey(Constant.ADD_PRODUCT));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setIconImage( new ImageIcon(getClass().getResource( Constant.IMG_ADD_PRODUCT)).getImage());
        this.getContentPane().setBackground(Constant.COLOR_BLUE_DARK_2);
        this.setSize(new Dimension(550,600));
        this.setResizable(false);
        this.setLocationRelativeTo(jMainWindows);
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        jContainerLogin = new JPanel();
        gridModel = new GridModel(jContainerLogin);
        jContainerLogin.setLayout(new GridBagLayout());
        jContainerLogin.setBorder(new EmptyBorder(0, 5, 0, 5));
        jContainerLogin.setBackground(Constant.COLOR_BLUE_DARK_2);

        JModelLabel amazon = new JModelLabel(Constant.IMG_AMAZON,470,150);
        amazon.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        amazon.setBackground(Constant.COLOR_BLUE_DARK_2);
        jContainerLogin.add(amazon,gridModel.insertComponent(0,0,4,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.BOTH));

        gridModel.addExternalBorder(10,10,10,20);

        imagePhoto = new JModelLabel(Constant.IMG_PHOTO);
        imagePhoto.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        imagePhoto.setBackground(Constant.COLOR_BLUE_DARK_2);
        jContainerLogin.add(imagePhoto,gridModel.insertComponent(1,0,2,4,1,GridBagConstraints.PAGE_START, GridBagConstraints.BOTH));

        addPhoto = new JModelButton(15,15,Utilities.getKey(Constant.ADD_PHOTO),Constant.COLOR_WHITE,Constant.COLOR_BLACK,Constant.FONT_ARIAL_ROUNDER_15,Command.LOAD_IMAGE.toString(),actionListener);
        addPhoto.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        addPhoto.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));
        gridModel.addExternalBorder(10,10,10,20);
        jContainerLogin.add(addPhoto,gridModel.insertComponent(5,0,2,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));


        nameProduct = new JModelTextField(Utilities.getKey(Constant.NAME_PRODUCT),Constant.FONT_HELVETICA_15,Constant.COLOR_WHITE,Constant.COLOR_GRAY_LIGHT_1);
        nameProduct.validateText(nameProduct);
        nameProduct.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_PRODUCT)));
        gridModel.addExternalBorder(10,10,10,20);
        jContainerLogin.add(nameProduct,gridModel.insertComponent(1,3,1,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        jCBType = new JModelComboBox<>(Utilities.getKeys(Constant.OPTIONS), Utilities.getKey(Constant.ASSIGN_OPTION), Constant.FONT_HELVETICA_15);
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

        createProduct = new JModelButton(15,15,Utilities.getKey(Constant.TXT_CREATE),Constant.COLOR_ORANGE_1,Constant.COLOR_WHITE,Constant.FONT_ARIAL_ROUNDER_15,Command.SAVE_PRODUCT.toString(),actionListener);
        createProduct.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        createProduct.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));
        gridModel.addExternalBorder(10,10,10,20);
        jContainerLogin.add(createProduct,gridModel.insertComponent(6,0,4,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        this.add(jContainerLogin);
    }

    public void resetDatesAddProduct(boolean status){
        if (status){
            nameProduct.setText("");
        }else {
            nameProduct.setText("");
            jCBType.setSelectedIndex(0);
            prices.setValue(1000);
            units.setValue(1);
            imagePhoto.setIconRelative(Constant.IMG_PHOTO);
        }
    }

    public void setIconRelative(String url){
        imagePhoto.setIconRelative(url);
    }

    public void setIconProduct(String url){
        imagePhoto.setIcon(url);
    }

    public Object[] createProduct(){
        if (!nameProduct.getText().isEmpty() && jCBType.getSelectedIndex() != 0){
            return new Object[]{
                nameProduct.getText(),prices.getValue(),jCBType.getSelectedIndex(),
                    units.getValue(),
            };
        }
        return null;
    }

    @Override
    public void changeLanguage() {
        jCBType.setItems(Utilities.getKeys(Constant.OPTIONS));
        jCBType.setTitleBor(Utilities.getKey(Constant.ASSIGN_OPTION));
        this.setTitle(Utilities.getKey(Constant.ADD_PRODUCT));
        addPhoto.setText(Utilities.getKey(Constant.ADD_PHOTO));
        nameProduct.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_PRODUCT)));
        nameProduct.setPlaceHolder(Utilities.getKey(Constant.NAME_PRODUCT));
        prices.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_PRICE)));
        units.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_UNITS)));
        createProduct.setText(Utilities.getKey(Constant.TXT_CREATE));
    }
}
