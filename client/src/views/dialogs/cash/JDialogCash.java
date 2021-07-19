package views.dialogs.cash;

import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.JMainWindows;
import views.models.GridModel;
import views.models.JModelLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class JDialogCash extends JDialog implements ILanguage {

    private JPanel container;
    private GridModel gridModel;
    private MouseListener mouseListener;

    public JDialogCash(MouseListener mouseListener, JMainWindows jMainWindows) {
        this.mouseListener = mouseListener;
        this.setModal(true);
        setTitle(Utilities.getKey(Constant.ADD_CASH));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setIconImage( new ImageIcon(getClass().getResource( Constant.IMG_CASH)).getImage());
        this.getContentPane().setBackground(Constant.COLOR_BLACK);
        this.setSize(new Dimension(600, 500));
        this.setResizable(false);
        this.setLocationRelativeTo(jMainWindows);
        container = new JPanel();
        gridModel = new GridModel(container);
        container.setBackground(Constant.COLOR_WHITE);
        container.setLayout(new GridBagLayout());
        container.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        initComponents();

    }

    public void addJPanelProduct(int rows, int column, String image,double value) {
        gridModel.addExternalBorder(15, 15, 15, 15);
        container.add(new JPanelCash(mouseListener, image,value,Constant.COLOR_GRAY_LIGHT_4),
                gridModel.insertComponent(rows, column, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH));
    }

    private void initComponents() {

        JModelLabel jModelLabel = new JModelLabel(Constant.IMG_CASH_AMAZON,500,80);
        jModelLabel.setBackground(Constant.COLOR_WHITE);
        jModelLabel.setColorPaint(Constant.COLOR_WHITE);
        container.add(jModelLabel,gridModel.insertComponent(0, 0, 3, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL));

        int j = 0,k = 1;
        int size = Constant.IMG_CASHES.length;
        for (int i = 0; i < size; i++) {
            addJPanelProduct(k, j, Constant.IMG_CASHES[i],Constant.PRICES[i]);
            j++;
            if (j == 3) {
                j = 0;
                k++;
            }
        }

        this.add(container);
    }

    @Override
    public void changeLanguage() {

    }
}
