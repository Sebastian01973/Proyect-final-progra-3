package views.panels;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.JMainWindows;
import views.models.GridModel;
import views.models.JModelButton;
import views.models.JModelLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogAddCard extends JDialog implements ILanguage {

    private JPanel jContainerLogin;
    private JSpinner units;
    private JModelButton buy;
    private JModelLabel imagePhoto;
    private JModelLabel nameProduct,prices,types,avalaible;
    private GridModel gridModel;
    private int maxUnits;
    private double price;

    private Object[] objects;
    private String photo;

    public JDialogAddCard(ActionListener actionListener, JMainWindows jMainWindows) {
        this.setModal(true);
        photo = "";
        this.objects = new Object[5];
        setTitle(Utilities.getKey(Constant.ADD_CARD));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setIconImage( new ImageIcon(getClass().getResource( Constant.IMG_BUY)).getImage());
        this.getContentPane().setBackground(Constant.COLOR_BLUE_DARK_2);
        this.setSize(new Dimension(550,600));
        this.setResizable(false);
        this.setLocationRelativeTo(jMainWindows);
        initComponents(actionListener);
    }

    public void addObjectsProduct(Object[] objects){
        this.objects = objects;
        String name = String.valueOf(objects[0]);
        String type = String.valueOf(objects[1]);
        maxUnits = Integer.parseInt(String.valueOf(objects[3]));
        price = Double.parseDouble(String.valueOf(objects[2]));
        String priceFinal = Utilities.toDecimalFormat(price);
        photo = String.valueOf(objects[5]);
        imagePhoto.setIconRelative("/products/"+photo);
        nameProduct.setText(name);
        types.setText(type);
        prices.setText(priceFinal);
        avalaible.setText("Max: "+maxUnits);
        if (maxUnits > 0){
            units.setModel(new SpinnerNumberModel(1, 1, maxUnits, 1));
            ((DefaultFormatter) ((JSpinner.NumberEditor)units.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        }else {
            units.setModel(new SpinnerNumberModel(0, 0, 0, 1));
            ((DefaultFormatter) ((JSpinner.NumberEditor)units.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        }

    }

    private void initComponents(ActionListener actionListener) {
        jContainerLogin = new JPanel();
        gridModel = new GridModel(jContainerLogin);
        jContainerLogin.setLayout(new GridBagLayout());
        jContainerLogin.setBorder(new EmptyBorder(0, 10, 0, 5));
        jContainerLogin.setBackground(Constant.COLOR_BLUE_DARK_2);

        JModelLabel amazon = new JModelLabel(Constant.IMG_AMAZON_STORE,500,125);
        amazon.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        amazon.setBackground(Constant.COLOR_BLUE_DARK_2);
        jContainerLogin.add(amazon,gridModel.insertComponent(0,0,4,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.BOTH));

        gridModel.addExternalBorder(0,10,10,20);

        imagePhoto = new JModelLabel(Constant.IMG_FONT,160,160);
        imagePhoto.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        imagePhoto.setBackground(Constant.COLOR_BLUE_DARK_2);
        gridModel.addExternalBorder(10, 10, 10, 10);
        jContainerLogin.add(imagePhoto, gridModel.insertComponent(1, 0, 2, 4, 1, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH));

        nameProduct = new JModelLabel("", Constant.FONT_ARIAL_ROUNDER_20, Constant.COLOR_BLUE_DARK_2, Constant.COLOR_WHITE);
        nameProduct.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        nameProduct.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        gridModel.addExternalBorder(10, 10, 10, 10);
        jContainerLogin.add(nameProduct, gridModel.insertComponent(1, 3, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        types = new JModelLabel("", Constant.FONT_ARIAL_ROUNDER_20, Constant.COLOR_BLUE_DARK_2, Constant.COLOR_WHITE);
        types.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        types.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        gridModel.addExternalBorder(10, 10, 10, 10);
        jContainerLogin.add(types, gridModel.insertComponent(2, 3, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        prices = new JModelLabel("", Constant.FONT_ARIAL_ROUNDER_20, Constant.COLOR_BLUE_DARK_2, Constant.COLOR_RED_LIGHT_1);
        prices.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        prices.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        gridModel.addExternalBorder(10, 10, 10, 10);
        jContainerLogin.add(prices, gridModel.insertComponent(3, 3, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        avalaible = new JModelLabel("", Constant.FONT_ARIAL_ROUNDER_20, Constant.COLOR_BLUE_DARK_2, Constant.COLOR_BLACK);
        avalaible.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        avalaible.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        gridModel.addExternalBorder(10, 10, 10, 10);
        jContainerLogin.add(avalaible, gridModel.insertComponent(4, 3, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));


        units = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        ((DefaultFormatter) ((JSpinner.NumberEditor)units.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        units.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_UNITS)));
        units.setBackground(Constant.COLOR_WHITE);
        jContainerLogin.add(units,gridModel.insertComponent(6,0,1,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        buy = new JModelButton(15,15,Utilities.getKey(Constant.ADD_CARD),Constant.COLOR_YELLOW_1,Constant.COLOR_WHITE,Constant.FONT_ARIAL_ROUNDER_15, Command.BUY_CARD.toString(),actionListener);
        buy.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        buy.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));
        gridModel.addExternalBorder(10,10,10,20);
        jContainerLogin.add(buy,gridModel.insertComponent(6,3,1,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        this.add(jContainerLogin);
    }

    public Object[] getProductAddCart(){
        return new Object[]{
                nameProduct.getText(),price,types.getText(),(int)units.getValue(),0,photo
        };
    }

    public void setIconImageRelative(String path){
        imagePhoto.setIconRelative(path);
    }

    public void setIconImageProduct(ImageIcon image){
        imagePhoto.setIconImageProduct(image);
    }

    @Override
    public void changeLanguage() {
        this.setTitle(Utilities.getKey(Constant.ADD_CARD));
        buy.setText(Utilities.getKey(Constant.ADD_CARD));
    }
}
