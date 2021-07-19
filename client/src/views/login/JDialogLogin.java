package views.login;

import views.ILanguage;
import views.JMainWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogLogin extends JDialog implements ILanguage {

	private static final long serialVersionUID = 1L;
	private JPMainPanel jpMainPanel;
	
	public JDialogLogin(ActionListener actionListener, JMainWindows jMainWindows) {
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(new Dimension(700,470));
	setModal(true);
	setUndecorated(true);
	this.setLocationRelativeTo(jMainWindows);
	iniComponents(actionListener);
	}

	public String[] getDatesLogin(){
		return jpMainPanel.getDatesLogin();
	}

	public String[] getDateRegister(){
		return jpMainPanel.getDateRegister();
	}

	public void setValueRegister(){
		jpMainPanel.setValueRegister();
	}

	public void setLogin(){
		jpMainPanel.setLogin();
	}

	public void setNickName(){
		jpMainPanel.setNickName();
	}
	
	private void iniComponents(ActionListener actionListener) {
	jpMainPanel = new JPMainPanel(actionListener);
	add(jpMainPanel);		
	}

	@Override
	public void changeLanguage() {
		jpMainPanel.changeLanguage();
	}
}
