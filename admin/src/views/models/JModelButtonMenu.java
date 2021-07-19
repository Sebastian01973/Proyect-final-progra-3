/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.models;

/**
 *
 * @author Rojeru San
 */

import views.Constant;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
* @author Rojeru San
 */
public class JModelButtonMenu extends JButton implements MouseListener, MouseMotionListener {
    /**
     * Color para el fondo del boton
     */
    private Color colorNormal = Constant.COLOR_WHITE;
    private Color colorPressed = Constant.COLOR_BLUE_DARK_2;
    private Color colorHover = Constant.COLOR_GRAY_LIGHT_1;
    
    private Border bordeMoved = BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(255, 255, 255));
    /**
     * Color para el texto
     */
    private Color colorTextNormal = Constant.COLOR_BLACK;
    private Color colorTextPressed = Constant.COLOR_WHITE;
    private Color colorTextHover = Constant.COLOR_BLUE_DARK_1;
    
    private Font f = Constant.FONT_ARIAL_ROUNDER_15;

    public JModelButtonMenu(String text,String pathImage,int width,int height) {
        super(text);
        ImageIcon imagen = new ImageIcon(getClass().getResource(pathImage));
        Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        this.setIcon(icon);
        this.setFont(f);
        this.setFocusable( false );
        this.setPreferredSize(new Dimension(150, 35));
        this.setSize(new Dimension(150, 35));
        this.setBorder(null);
//        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBackground(colorNormal);
        this.setForeground(this.colorTextNormal);
        this.setOpaque(true);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * se pinta la imagen con dimensiones de ancho y alto iguales al alto del
     * jbutton
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Cuando se presiona el boton se cambian los colores de fondo y de texto
     */
    public void mousePressed(MouseEvent e) {
        this.setForeground(this.colorTextPressed);
        this.setBackground(this.colorPressed);
    }

    /**
     * Cuando se leventa el mouse del jbutton se retoman los colores originales
     */
    public void mouseReleased(MouseEvent e) {       
        this.setBackground(this.colorNormal);
        this.setForeground(this.colorTextNormal);
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        this.setBorder(null);
        this.setBackground(this.colorNormal);
        this.setForeground(this.colorTextNormal);
    }

    public Color getColorPressed() {
        return colorPressed;
    }

    public void setColorPressed(Color colorPressed) {
        this.colorPressed = colorPressed;
    }

    public Color getColorTextPressed() {
        return colorTextPressed;
    }

    public void setColorTextPressed(Color colorTextPressed) {
        this.colorTextPressed = colorTextPressed;
    }

    public Color getColorHover() {
        return colorHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    public Color getColorTextHover() {
        return colorTextHover;
    }

    public void setColorTextHover(Color colorTextHover) {
        this.colorTextHover = colorTextHover;
    }

    public Color getColorNormal() {
        return colorNormal;
    }

    public void setColorNormal(Color colorNormal) {
        this.setBackground(colorNormal);
        this.colorNormal = colorNormal;      
    }

    public Color getColorTextNormal() {
        return colorTextNormal;
    }

    public void setColorTextNormal(Color colorTextNormal) {
        this.setForeground(colorTextNormal);
        this.colorTextNormal = colorTextNormal;
    }

    public Border getColorBorde() {
        return bordeMoved;
    }

    public void setColorBorde(Border bordeMoved) {
        this.bordeMoved = bordeMoved;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        this.setForeground(this.colorTextHover);
        this.setBackground(this.colorHover);
        
        this.setBorder(bordeMoved);
    }
}
