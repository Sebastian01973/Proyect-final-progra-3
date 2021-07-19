package views.dialogs;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.models.JModelButton;
import views.models.JModelLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JSouthPanelCart extends JPanel implements ILanguage {

    public JModelButton buyTotal,backBuy;
    public JModelLabel total;

    public JSouthPanelCart(ActionListener actionListener) {
        this.setLayout(new BorderLayout());
        this.setBackground(Constant.COLOR_WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        backBuy = new JModelButton(20,20,Utilities.getKey(Constant.TXT_CONTINUE),Constant.FONT_ARIAL_ROUNDER_15,Constant.COLOR_ORANGE_1,Constant.COLOR_WHITE);
        backBuy.setIcon(createIcon(Constant.IMG_ARROW_LEFT,10,10));
        backBuy.setActionCommand(Command.CONTINUE_BUY.toString());
        backBuy.addActionListener(actionListener);
        this.add(backBuy,BorderLayout.WEST);

        JPanel panel = new JPanel();
        panel.setBackground(Constant.COLOR_WHITE);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        total = new JModelLabel("Total: $ 0.0",Constant.FONT_HELVETICA_15,Constant.COLOR_WHITE,Constant.COLOR_BLACK);
        total.setAlignmentY(CENTER_ALIGNMENT);
        total.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(total);

        this.add(panel);

        buyTotal = new JModelButton(20,20,Utilities.getKey(Constant.TXT_BUY),Constant.FONT_ARIAL_ROUNDER_15,Constant.COLOR_GREEN_1,Constant.COLOR_WHITE);
        buyTotal.setHorizontalTextPosition( SwingConstants.LEFT );
        buyTotal.setIcon(createIcon(Constant.IMG_ARROW_RIGHT,10,10));
        buyTotal.setActionCommand(Command.BUY_TOTAL_PRODUCTS.toString());
        buyTotal.addActionListener(actionListener);
        this.add(buyTotal,BorderLayout.EAST);

    }

    public void setTotal(double total){
        this.total.setText("Total: "+Utilities.toDecimalFormat(total));
    }

    private ImageIcon createIcon(String path, int weight, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(weight, height, Image.SCALE_DEFAULT));
        return icono;
    }

    @Override
    public void changeLanguage() {
        backBuy.setText(Utilities.getKey(Constant.TXT_CONTINUE));
        buyTotal.setText(Utilities.getKey(Constant.TXT_BUY));
    }
}
