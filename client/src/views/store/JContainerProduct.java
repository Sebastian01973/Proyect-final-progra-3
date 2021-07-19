package views.store;

import views.Constant;
import views.ILanguage;
import views.models.GridModel;
import views.panels.JPanelProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class JContainerProduct extends JPanel {

    private GridModel gridModel;
    private MouseListener mouseListener;
    private String imageFont;
    private ArrayList<JPanelProduct> jPanelProducts;

    public JContainerProduct(MouseListener mouseListener,String image) {
        this.jPanelProducts = new ArrayList<>();
        this.imageFont = image;
        this.mouseListener = mouseListener;
        gridModel = new GridModel(this);
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void addJPanelProduct(int rows, int column, Object[] objects) {
        gridModel.addExternalBorder(10, 10, 10, 10);
        JPanelProduct jPanelProduct = new JPanelProduct(mouseListener, objects);
        jPanelProducts.add(jPanelProduct);
        this.add(jPanelProduct,
                gridModel.insertComponent(rows, column, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH));
    }

    public void addProduct(ArrayList<Object[]> arrayObjects) {
        this.jPanelProducts.clear();
        this.removeAll();
        this.repaint();
        int size = arrayObjects.size();
        if (size <= 4) {
            for (int i = 0; i < size; i++) {
                addJPanelProduct(0, i, arrayObjects.get(i));
            }
        } else {
            int i = 0, j = 0;
            for (Object[] arrayObject : arrayObjects) {
                addJPanelProduct(i, j, arrayObject);
                j++;
                if (j == 4) {
                    j = 0;
                    i++;
                }
            }
        }
        this.updateUI();
    }

    @Override
    public void paint(Graphics g) {
        setOpaque(false);
        Image image = new ImageIcon(getClass().getResource(imageFont)).getImage();
        g.drawImage(image,0,0,getWidth(),getHeight(),this);
        super.paint(g);
    }


}
