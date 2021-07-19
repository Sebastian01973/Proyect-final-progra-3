package views.dialogs;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.JMainWindows;
import views.models.JModelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class JDialogTrolley extends JDialog implements ILanguage {

    public JTrolleyPanel jTrolleyPanel;
    public JNorthPanelCart jNorthPanelCart;
    public JPanel jPanel;
    private JScrollPane jScrollPane;
    private JSouthPanelCart jSouthPanelCart;

    public JDialogTrolley(ActionListener actionListener, MouseListener mouseListener, JMainWindows jMainWindows) {
        this.setModal(true);
        setTitle(Utilities.getKey(Constant.TXT_TROLLEY));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setIconImage( new ImageIcon(getClass().getResource( Constant.IMG_CART)).getImage());
        this.getContentPane().setBackground(Constant.COLOR_BLACK);
        this.setSize(new Dimension(850, 500));
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(jMainWindows);
        initComponents(actionListener,mouseListener);
    }

    private void initComponents(ActionListener actionListener, MouseListener mouseListener) {
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(Constant.COLOR_WHITE);
        jPanel.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));

        jNorthPanelCart = new JNorthPanelCart();
        jPanel.add(jNorthPanelCart,BorderLayout.NORTH);

        jTrolleyPanel = new JTrolleyPanel(actionListener,mouseListener);
        jScrollPane = new JScrollPane(jTrolleyPanel);
        jScrollPane.setForeground(Color.white);
        jScrollPane.setBorder(null);
        jScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        jPanel.add(jScrollPane, BorderLayout.CENTER);

        jSouthPanelCart = new JSouthPanelCart(actionListener);
        jPanel.add(jSouthPanelCart,BorderLayout.SOUTH);

        this.add(jPanel);

    }

    public void deleteAll(){
        jTrolleyPanel.deleteAll();
    }

    public void deletePanelProduct(String nameProduct){
        jTrolleyPanel.deletePanelProduct(nameProduct);
    }


    public void setTotal(double total){
        jSouthPanelCart.setTotal(total);
    }

    /**
     * Add product.
     *
     * @param objects the objects
     */
    public void addProduct(Object[] objects){
        jTrolleyPanel.addProduct(objects);
    }

    @Override
    public void changeLanguage() {
        setTitle(Utilities.getKey(Constant.TXT_TROLLEY));
        jNorthPanelCart.changeLanguage();
        jSouthPanelCart.changeLanguage();
    }
}
