package views.dialogs;

import views.Constant;
import views.ILanguage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class JTrolleyPanel extends JPanel{

    private ArrayList<JProductCart> panelProduct;
    private ActionListener actionListener;
    private MouseListener mouseListener;
    public JTrolleyPanel(ActionListener actionListener, MouseListener mouseListener) {
        this.actionListener = actionListener;
        this.mouseListener = mouseListener;
        panelProduct = new ArrayList<>();
        this.setBackground(Constant.COLOR_WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }

    public boolean existProduct(String name){
        for (JProductCart jProductCart:panelProduct) {
            if (jProductCart.getNameProduct().equals(name)){
                return true;
            }
        }
        return false;
    }

    public JProductCart getProduct(String name){
        for (JProductCart jProductCart:panelProduct) {
            if (jProductCart.getNameProduct().equals(name)){
                return jProductCart;
            }
        }
        return null;
    }

    public void deleteAll(){
        this.removeAll();
        panelProduct.clear();
        this.updateUI();
    }

    public void deletePanelProduct(String nameProduct){
        panelProduct.removeIf(p -> p.getNameProduct().equals(nameProduct));
        this.removeAll();
        for (JProductCart jProductCart : panelProduct) {
            this.add(jProductCart);
        }
        this.updateUI();
    }

    public void addProduct(Object[] objects){
        String nameProduct = String.valueOf(objects[0]);
        if (!existProduct(nameProduct)){
            JProductCart jProductCart = new JProductCart(actionListener,mouseListener,objects);
            panelProduct.add(jProductCart);
            this.add(jProductCart);
        }else {
            JProductCart existProduct = getProduct(nameProduct);
            int unit = Integer.parseInt(String.valueOf(objects[3]));
            existProduct.setUnit(unit);
        }
    }

//    public void repaintPanels(){
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("Timeeeeers");
//                for (JProductCart jProductCart:panelProduct) {
//                    add(jProductCart);
//                }
//            }
//        };
//        timer.schedule(task,0,1000);
//    }

}
