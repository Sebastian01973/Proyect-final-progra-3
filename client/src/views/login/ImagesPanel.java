package views.login;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.models.RoundedJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ImagesPanel extends JPanel implements ILanguage {

    RoundedJButton exitButton;

    public ImagesPanel(ActionListener actionListener) {
        setLayout(null);
        setBackground(Color.BLACK);
        exitButton = new RoundedJButton(Utilities.getKey(Constant.EXIT), Command.B_EXIT.toString(), actionListener,Constant.COLOR_ORANGE_1,Color.white);
        exitButton.setBounds(20,430,80,25);
        add(exitButton);
    }

    @Override
    public void paint(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(new ImageIcon(getClass().getResource("/login/img1.jpg")).getImage(),0,0,getWidth(),getHeight(),this);
        super.paint(g);
    }

    @Override
    public void changeLanguage() {
        exitButton.setText(Utilities.getKey(Constant.EXIT));
    }
}
