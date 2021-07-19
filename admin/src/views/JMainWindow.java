package views;

import utilities.Utilities;
import views.dialog.JDialogAdd;
import views.dialog.JDialogDelete;
import views.dialog.JDialogProduct;
import views.dialog.JDialogSearch;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

public class JMainWindow extends JFrame implements ILanguage{

    private JMainPanel jMainPanel;
    private JDialogAdd jDialogAdd;
    private JDialogProduct jDialogProduct;
    private JDialogSearch jDialogSearch;
    private JDialogDelete jDialogDelete;

    public JMainWindow(ActionListener actionListener, MouseListener mouseListener, WindowListener windowListener) throws HeadlessException {
        this.addWindowListener(windowListener);
        this.setIconImage( new ImageIcon(getClass().getResource(Constant.IMG_ADMIN)).getImage());
        this.setTitle( Utilities.getKey(Constant.TITLE_STORE));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(Constant.SCREEN_SIZE);
        this.setExtendedState(MAXIMIZED_BOTH);
        initComponents(actionListener,mouseListener);
        this.setVisible(true);
    }

    public int getSelectBoxSearch(){
        return jDialogSearch.getSelectBoxSearch();
    }

    public String getNameProductDelete(){
        return jDialogDelete.getNameProductDelete();
    }

    public void setVisibleProductDialog(boolean status){
        jDialogProduct.setVisible(status);
    }

    public void setVisibleSearchDialog(boolean status){
        jDialogSearch.setVisible(status);
    }

    public void setVisibleDeleteDialog(boolean status){
        jDialogDelete.setVisible(status);
    }

    public void addObjectProduct(Object[] objects){
        jDialogProduct.addObjectsProduct(objects);
    }

    public void setVisibleDialogProduct(boolean status){
        jDialogProduct.setVisible(status);
    }


    public Object[] modifyProduct(){
        return jDialogProduct.modifyProduct();
    }

    public void setIconImageProduct(ImageIcon image){
        jDialogProduct.setIconImageProduct(image);
    }

    public String nameProductDialog(){
        return jDialogProduct.nameProductDialog();
    }

    public void addRowsToTable(ArrayList<Object[]> matrix, String[] newHeaders){
        jMainPanel.addRowsToTable(matrix, newHeaders);
    }

    public void addElementToTable(ArrayList<Object[]> matrix){
        jMainPanel.addElementToTable(matrix);
    }

    public String getSelectedRow(Point point ){
        return jMainPanel.getSelectedRow(point);
    }

    public int getColumnCountTable(){
        return jMainPanel.getColumnCountTable();
    }

    private void initComponents(ActionListener actionListener, MouseListener mouseListener) {
        jDialogProduct = new JDialogProduct(actionListener,this);
        jDialogDelete = new JDialogDelete(actionListener,this);
        jDialogSearch = new JDialogSearch(actionListener,this);
        jDialogAdd = new JDialogAdd(actionListener,this);
        jMainPanel = new JMainPanel(actionListener,mouseListener);
        this.getContentPane().add(jMainPanel,BorderLayout.CENTER);
    }

    public void setIconProduct(String url){
        jDialogAdd.setIconProduct(url);
    }

    public void setVisibleDialogAdd(boolean status){
        jDialogAdd.setVisible(status);
    }

    public void setVisibleLeft(boolean visibleLeft){
        jMainPanel.setVisibleLeft(visibleLeft);
    }

    public int getPositionLeft() {
        return jMainPanel.getPositionLeft();
    }

    public Object[] createProduct(){
        return jDialogAdd.createProduct();
    }

    public File loadImage(){
        File file = null;
        JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        String[] extensions = new String[] {"png","jpg","PNG","JPG"};
        FileNameExtensionFilter filterFile = new FileNameExtensionFilter(Utilities.getKey(Constant.TXT_FILE),extensions);
        fc.setFileFilter(filterFile);
        fc.setDialogTitle(Utilities.getKey(Constant.TXT_SEARCH_PHOTO));
        int option = fc.showOpenDialog(this);
        if (option != JFileChooser.CANCEL_OPTION){
            file = new File(fc.getSelectedFile().getPath());
        }
        return file;
    }

    public void resetDatesAddProduct(boolean status){
        jDialogAdd.resetDatesAddProduct(status);
    }


    @Override
    public void changeLanguage() {
        this.setTitle( Utilities.getKey(Constant.TITLE_STORE));
        jMainPanel.changeLanguage();
        jDialogAdd.changeLanguage();
    }
}
