package views.dialog;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.JMainWindow;
import views.models.JModelButton;
import views.models.JModelComboBox;
import views.models.JModelLabel;
import views.models.JModelTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogDelete extends JDialog implements ILanguage {

    private GridLayout gridLayout;
    private JModelTextField jTFProduct;
    private JModelButton jbDelete,jBCancel;
    private JPanel jContainer;

    public JDialogDelete(ActionListener actionListener, JMainWindow jMainWindows) {
        this.setModal(true);
        setTitle(Utilities.getKey(Constant.TXT_DELETE));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setIconImage( new ImageIcon(getClass().getResource( Constant.IMG_DELETE)).getImage());
        this.getContentPane().setBackground(Constant.COLOR_BLACK);
        this.setSize(new Dimension(400, 500));
        this.setResizable(false);
        this.setLocationRelativeTo(jMainWindows);
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        jContainer = new JPanel();
        gridLayout = new GridLayout(4,1);
        gridLayout.setVgap(20);
        jContainer.setLayout(gridLayout);
        jContainer.setBorder(new EmptyBorder(20,30,15,30));
        jContainer.setBackground(Constant.COLOR_BLUE_DARK_2);

        JModelLabel amazon = new JModelLabel(Constant.IMG_AMAZON,350,120);
        amazon.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        amazon.setBackground(Constant.COLOR_BLUE_DARK_2);
        jContainer.add(amazon);

        jTFProduct = new JModelTextField(Utilities.getKey(Constant.NAME_PRODUCT),Constant.FONT_HELVETICA_15,Constant.COLOR_WHITE,Constant.COLOR_GRAY_LIGHT_1);
        jTFProduct.validateText(jTFProduct);
        jTFProduct.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_PRODUCT)));
        jContainer.add(jTFProduct);

        jbDelete = new JModelButton(15, 15,Utilities.getKey(Constant.TXT_DELETE),Constant.COLOR_GREEN, Constant.COLOR_WHITE,
                Constant.FONT_ARIAL_ROUNDER_15, Command.DIALOG_DELETE.toString(), actionListener );
        jbDelete.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        jContainer.add(jbDelete);

        jBCancel = new JModelButton( 15, 15,Utilities.getKey(Constant.CANCEL), Constant.COLOR_RED_LIGHT_1, Constant.COLOR_WHITE,
                Constant.FONT_ARIAL_ROUNDER_15, Command.CANCEL_DELETE.toString(), actionListener );
        jBCancel.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        jContainer.add( jBCancel);

        this.add(jContainer);
    }

    public String getNameProductDelete(){
        if (!jTFProduct.getText().isEmpty()){
            return jTFProduct.getText();
        }
        return "";
    }

    @Override
    public void changeLanguage() {
        jBCancel.setText(Utilities.getKey(Constant.CANCEL));
        jbDelete.setText(Utilities.getKey(Constant.TXT_DELETE));
        jTFProduct.setBorder(BorderFactory.createTitledBorder(Utilities.getKey(Constant.TXT_PRODUCT)));
        jTFProduct.setPlaceHolder(Utilities.getKey(Constant.NAME_PRODUCT));
        this.setTitle(Utilities.getKey(Constant.TXT_DELETE));
    }
}
