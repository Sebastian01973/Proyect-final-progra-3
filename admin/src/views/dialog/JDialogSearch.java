package views.dialog;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.JMainWindow;
import views.models.JModelButton;
import views.models.JModelComboBox;
import views.models.JModelLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogSearch extends JDialog implements ILanguage {

    private GridLayout gridLayout;
    private JModelComboBox<Object> jCBType;
    private JModelButton jBSearch,jBCancel;
    private JPanel jContainer;

    public JDialogSearch(ActionListener actionListener, JMainWindow jMainWindows) {
        this.setModal(true);
        setTitle(Utilities.getKey(Constant.L_SEARCH));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setIconImage( new ImageIcon(getClass().getResource( Constant.IMG_SEARCH)).getImage());
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

        jCBType = new JModelComboBox<>(Utilities.getKeys(Constant.OPTIONS),Utilities.getKey(Constant.ASSIGN_OPTION), Constant.FONT_ARIAL_ROUNDER_17);
        jContainer.add(jCBType);

        jBSearch = new JModelButton(15, 15,Utilities.getKey(Constant.TXT_SEARCH),Constant.COLOR_GREEN, Constant.COLOR_WHITE,
                Constant.FONT_ARIAL_ROUNDER_15, Command.JD_SEARCH_PRODUCT.toString(), actionListener );
        jBSearch.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        jContainer.add(jBSearch);

        jBCancel = new JModelButton( 15, 15,Utilities.getKey(Constant.CANCEL), Constant.COLOR_RED_LIGHT_1, Constant.COLOR_WHITE,
                Constant.FONT_ARIAL_ROUNDER_15, Command.CANCEL_SEARCH.toString(), actionListener );
        jBCancel.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        jContainer.add( jBCancel);

        this.add(jContainer);
    }

    public int getSelectBoxSearch(){
        return jCBType.getSelectedIndex();
    }

    @Override
    public void changeLanguage() {
        this.setTitle(Utilities.getKey(Constant.L_SEARCH));
        jCBType.setItems(Utilities.getKeys(Constant.OPTIONS));
        jCBType.setTitleBor(Utilities.getKey(Constant.ASSIGN_OPTION));
        jBCancel.setText(Utilities.getKey(Constant.CANCEL));
        jBSearch.setText(Utilities.getKey(Constant.TXT_SEARCH));
    }
}
