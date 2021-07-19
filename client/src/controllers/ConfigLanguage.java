package controllers;

import persistence.HandlerLanguage;
import views.JMainWindows;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ConfigLanguage {

    private JMainWindows jMainWindows;
    private HandlerLanguage config;

    public void setJfWindowsMain(JMainWindows jMainWindows) {
        this.jMainWindows = jMainWindows;
    }

    public void loadConfiguration() {
        if(config == null){
            config = new HandlerLanguage(ConstantController.NAME_FILE_CONFIG);
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
            new HandlerLanguage(ConstantController.NAME_FILE_CONFIG).saveLanguage();
        } catch (IOException e) {
            showMessageDialog(jMainWindows, e.getMessage());
        }
    }

    public void changeToEnglish() throws IOException{
        HandlerLanguage.language = ConstantController.ENGLISH_PATH;
        saveConfig();
        loadLanguage();
    }

    public void changeToSpanish() throws IOException{
        HandlerLanguage.language = ConstantController.SPANISH_PATH;
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
