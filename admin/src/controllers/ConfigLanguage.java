package controllers;

import persistence.HandlerLanguage;
import views.JMainWindow;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ConfigLanguage {

    private JMainWindow jMainWindows;
    private HandlerLanguage config;

    public void setJfWindowsMain(JMainWindow jMainWindows) {
        this.jMainWindows = jMainWindows;
    }

    public void loadConfiguration() {
        if(config == null){
            config = new HandlerLanguage(ConstantAdmin.NAME_FILE_CONFIG);
        }
        try{
            config.loadLanguage();
        }catch(IOException e){
            showMessageDialog(jMainWindows, e.getMessage());
        }
    }

    private void manageChangeLanguage(){
        jMainWindows.changeLanguage();
    }

    public void loadLanguage(){
        try {
            config.loadLanguage();
        } catch (IOException e) {
            showMessageDialog(jMainWindows, e.getMessage());
        }
    }

    public void saveConfig(){
        try {
            new HandlerLanguage(ConstantAdmin.NAME_FILE_CONFIG).saveLanguage();
        } catch (IOException e) {
            showMessageDialog(jMainWindows, e.getMessage());
        }
    }

    public void changeToEnglish() throws IOException{
        HandlerLanguage.language = ConstantAdmin.ENGLISH_PATH;
        saveConfig();
        loadLanguage();
    }

    public void changeToSpanish() throws IOException{
        HandlerLanguage.language = ConstantAdmin.SPANISH_PATH;
        saveConfig();
        loadLanguage();
    }

    public void manageChangeLanguageUS(){
        try {
            changeToEnglish();
        } catch (IOException e1) {
            showMessageDialog(jMainWindows, e1.getMessage());
        }
        manageChangeLanguage();
    }

    public void manageChangeLanguageES(){
        try {
            changeToSpanish();
        } catch (IOException e1) {
            showMessageDialog(jMainWindows, e1.getMessage());
        }
        manageChangeLanguage();
    }
}
