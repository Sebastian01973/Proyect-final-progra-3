package views.panels;

import views.Constant;
import views.models.JModelLabel;

import javax.swing.*;
import java.awt.*;

public class JPanelClient extends JPanel {

    private JModelLabel jMLCode,jMLName,jMLImage;

    public JPanelClient(String imgPath) {
        this.setBorder(BorderFactory.createEmptyBorder(10,5,10,3));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Constant.COLOR_BLUE_DARK_2);
        initComponents(imgPath);
    }

    private void initComponents(String imgPath) {
        jMLCode = new JModelLabel("",Constant.FONT_ARIAL_ROUNDER_20,Constant.COLOR_BLUE_DARK_2,Constant.COLOR_WHITE);
        jMLCode.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        jMLCode.setBackground(Constant.COLOR_BLUE_DARK_2);
        this.add(jMLCode);

        jMLImage = new JModelLabel(imgPath,30,30);
        jMLImage.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        jMLImage.setBackground(Constant.COLOR_BLUE_DARK_2);
        this.add(jMLImage);

        jMLName = new JModelLabel("",Constant.FONT_ARIAL_ROUNDER_20,Constant.COLOR_BLUE_DARK_2,Constant.COLOR_WHITE);
        jMLName.setColorPaint(Constant.COLOR_BLUE_DARK_2);
        jMLName.setBackground(Constant.COLOR_BLUE_DARK_2);
        this.add(jMLName);
    }

    public void setBorders(int top, int left, int bottom,int right){
        this.setBorder(BorderFactory.createEmptyBorder(top,left,bottom,right));
    }

    public void setValues(String code,String name){
        jMLName.setText(name);
        jMLCode.setText(code);
    }

    public void setFgFirst(Color bg){
        this.setForeground(bg);
        jMLCode.setForeground(bg);
        jMLName.setForeground(bg);
    }

    public void setBgFirst(Color bg){
        this.setBackground(bg);
        jMLCode.setBackground(bg);
        jMLName.setBackground(bg);
    }
}
