package views.login;

import controllers.Command;
import utilities.Utilities;
import views.Constant;
import views.ILanguage;
import views.models.RoundedJButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ILanguage {

    private JTextFieldModel userName;
    private PasswordFieldModel passwordField;
    JLabel title;
    RoundedJButton loginUser,changePanel;

    public LoginPanel( ActionListener actionListener,ActionListener listenerPresenter) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(38,30,38,30));
        setBackground(Color.WHITE);

        add(Box.createRigidArea(new Dimension(0, 70)));

        title = new JLabel(Utilities.getKey(Constant.L_LOGIN));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Constant.COLOR_BLACK);
        title.setFont(Constant.FONT_ARIAL_ROUNDER_35_B);
        add(title);

        add(Box.createRigidArea(new Dimension(0, 50)));
        userName = new JTextFieldModel("UserName");
        userName.setIcon(createIcon("/login/user.png", 16, 16));
        add(userName);

        add(Box.createRigidArea(new Dimension(0, 30)));
        passwordField = new PasswordFieldModel("Password", Color.white);
        passwordField.setIcon(createIcon("/login/key.png", 16, 16));
        add(passwordField);

        add(Box.createRigidArea(new Dimension(0, 30)));
        loginUser = new RoundedJButton(Utilities.getKey(Constant.B_ENTRY), Command.B_LOGIN.toString(), listenerPresenter, Constant.COLOR_ORANGE_1,Color.WHITE);
        loginUser.setFont(Constant.FONT_ARIAL_ROUNDER_15);
        loginUser.setAlignmentX(CENTER_ALIGNMENT);
        add(loginUser);

        add(Box.createRigidArea(new Dimension(0, 15)));
        changePanel = new RoundedJButton(Utilities.getKey(Constant.B_REGISTER), EventsView.REGISTER_PANEL.toString(), actionListener,Color.WHITE,Constant.COLOR_ORANGE_1);
        changePanel.setFont(Constant.FONT_ARIAL_ROUNDER_15);
        changePanel.setAlignmentX(CENTER_ALIGNMENT);
        add(changePanel);
        add(Box.createRigidArea(new Dimension(0, 50)));

    }

    public void setLogin(){
        userName.setText("");
        passwordField.setText("");
    }

    public String[] getDatesLogin(){
        if (!userName.getText().isEmpty() && !String.valueOf(passwordField.getPassword()).isEmpty()){
            return new String[]{userName.getText(),String.valueOf(passwordField.getPassword())};
        }
        return null;
    }

    private ImageIcon createIcon(String path, int weight, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(weight, height, Image.SCALE_DEFAULT));
        return icono;
    }

    @Override
    public void changeLanguage() {
        changePanel.setText(Utilities.getKey(Constant.B_REGISTER));
        loginUser.setText(Utilities.getKey(Constant.B_ENTRY));
        title.setText(Utilities.getKey(Constant.L_LOGIN));

    }
}
