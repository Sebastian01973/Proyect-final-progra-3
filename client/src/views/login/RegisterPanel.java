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

public class RegisterPanel extends JPanel implements ILanguage {

    private JTextFieldModel name;
    private JTextFieldModel lastName;
    private JTextFieldModel nick;
    private PasswordFieldModel password;

    private JLabel title;
    private RoundedJButton register,loginUser;

    public RegisterPanel(ActionListener actionListener, ActionListener listenerPresenter) {
        setBackground(Color.blue);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(38, 30, 38, 30));
        setBackground(Color.WHITE);

        add(Box.createRigidArea(new Dimension(0, 50)));

        title = new JLabel(Utilities.getKey(Constant.L_REGISTER));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.BLACK);
        title.setFont(Constant.FONT_ARIAL_ROUNDER_35_B);
        add(title);

        add(Box.createRigidArea(new Dimension(0, 40)));

        name = new JTextFieldModel("Name");
        name.setIcon(createIcon("/login/nameUser.png", 16, 16));
        add(name);

        add(Box.createRigidArea(new Dimension(0, 15)));
        lastName = new JTextFieldModel("Last Name");
        lastName.setIcon(createIcon("/login/user.png", 16, 16));
        add(lastName);

        add(Box.createRigidArea(new Dimension(0, 15)));
        nick = new JTextFieldModel("Email");
        nick.setIcon(createIcon("/login/email.png", 16, 16));
        add(nick);

        add(Box.createRigidArea(new Dimension(0, 15)));
        password = new PasswordFieldModel("Password", Color.white);
        password.setIcon(createIcon("/login/key.png", 16, 16));
        add(password);

        add(Box.createRigidArea(new Dimension(0, 40)));
        register = new RoundedJButton(Utilities.getKey(Constant.L_REGISTER_USER), Command.B_REGISTER.toString(), listenerPresenter,Constant.COLOR_ORANGE_1, Color.WHITE);
        register.setFont(Constant.FONT_ARIAL_ROUNDER_15);
        register.setAlignmentX(CENTER_ALIGNMENT);
        add(register);

        add(Box.createRigidArea(new Dimension(0, 10)));
        loginUser = new RoundedJButton(Utilities.getKey(Constant.L_LOGIN), EventsView.LOGIN_PANEL.name(), actionListener, Color.WHITE,Constant.COLOR_ORANGE_1);
        loginUser.setFont(Constant.FONT_ARIAL_ROUNDER_15);
        loginUser.setAlignmentX(CENTER_ALIGNMENT);
        add(loginUser);
        add(Box.createRigidArea(new Dimension(0, 40)));

    }

    public void setValueRegister(){
        name.setText("");
        lastName.setText("");
        nick.setText("");
        password.setText("");
    }

    public String[] getDateRegister(){
        if (!name.getText().isEmpty() && !lastName.getText().isEmpty() &&
                !nick.getText().equals("") && !String.valueOf(password.getPassword()).isEmpty()){
            return new String[]{
                    name.getText(),lastName.getText(),nick.getText(),
                    String.valueOf(password.getPassword()) };
        }
        return null;
    }

    public void setNickName(){
        nick.setText("");
    }

    private ImageIcon createIcon(String path, int weight, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(weight, height, Image.SCALE_DEFAULT));
        return icono;
    }

    @Override
    public void changeLanguage() {
        loginUser.setText(Utilities.getKey(Constant.L_LOGIN));
        title.setText(Utilities.getKey(Constant.L_REGISTER));
        register.setText(Utilities.getKey(Constant.L_REGISTER_USER));
    }
}
