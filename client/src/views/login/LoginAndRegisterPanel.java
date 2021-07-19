package views.login;

import views.ILanguage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginAndRegisterPanel extends JPanel implements ILanguage {

    public static final String LOGIN_PANEL = "loginPanel";
    public static final String REGISTER_PANEL = "registerPanel";
    private CardLayout cardLayout;

    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;

    public LoginAndRegisterPanel(ActionListener actionListener, ActionListener listenerPresenter) {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        initComponents(actionListener, listenerPresenter);
        cardLayout.show(this,LOGIN_PANEL);
    }

    private void initComponents(ActionListener actionListener, ActionListener listenerPresenter) {
        loginPanel = new LoginPanel(actionListener, listenerPresenter);
        add(loginPanel , LOGIN_PANEL);

        registerPanel = new RegisterPanel(actionListener, listenerPresenter);
        add(registerPanel, REGISTER_PANEL);
    }

    public String[] getDatesLogin(){
        return loginPanel.getDatesLogin();
    }

    public void setValueRegister(){
        registerPanel.setValueRegister();
    }

    public String[] getDateRegister(){
        return registerPanel.getDateRegister();
    }

    public void setNickName(){
        registerPanel.setNickName();
    }

    public void setLogin(){
        loginPanel.setLogin();
    }

    public void showPanelLogin(){
        cardLayout.show(this,LOGIN_PANEL);
    }

    public void showPanelRegister(){
        cardLayout.show(this,REGISTER_PANEL);
    }

    @Override
    public void changeLanguage() {
        loginPanel.changeLanguage();
        registerPanel.changeLanguage();
    }
}
