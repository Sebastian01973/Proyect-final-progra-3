package views.login;

import views.ILanguage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPMainPanel extends JPanel implements ActionListener, ILanguage {

    private static final long serialVersionUID = 1L;

    private ImagesPanel imagesPanel;
    private LoginAndRegisterPanel loginAndRegisterPanel;


    public JPMainPanel(ActionListener actionListener) {

        setLayout(new GridLayout(0,2));
        setBackground(new Color(33, 44, 63));
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {

        imagesPanel = new ImagesPanel(actionListener);
        add(imagesPanel);

        loginAndRegisterPanel = new LoginAndRegisterPanel(this, actionListener);
        add(loginAndRegisterPanel);

    }

    public String[] getDatesLogin(){
        return loginAndRegisterPanel.getDatesLogin();
    }

    public void setValueRegister(){
        loginAndRegisterPanel.setValueRegister();
    }

    public String[] getDateRegister(){
        return loginAndRegisterPanel.getDateRegister();
    }

    public void setLogin(){
        loginAndRegisterPanel.setLogin();
    }

    public void setNickName(){
        loginAndRegisterPanel.setNickName();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (EventsView.valueOf(e.getActionCommand())){
            case REGISTER_PANEL:
                loginAndRegisterPanel.showPanelRegister();
                break;
            case LOGIN_PANEL:
                loginAndRegisterPanel.showPanelLogin();
                break;
        }
    }

    @Override
    public void changeLanguage() {
        imagesPanel.changeLanguage();
        loginAndRegisterPanel.changeLanguage();
    }
}
