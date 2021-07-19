package views.admin;

import utilities.Utilities;
import views.Constant;
import views.ILanguage;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class JTableCenter extends JPanel implements ILanguage {
    private JTable jTable;
    private DefaultTableModel dtmElements;
    private JScrollPane jScrollPane;
    private String[]  headers;

    public JTableCenter(ActionListener actionListener, MouseListener mouseListener) {
        dtmElements = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        };
        dtmElements.setColumnIdentifiers(Utilities.getKeys(Constant.H_PRODUCTS));
        this.setLayout(new BorderLayout());
        initComponents(actionListener, mouseListener);
    }
    private void initComponents(ActionListener actionListener, MouseListener mouseListener){
        jTable = new JTable();
        jTable = new JTable();
        jTable.setModel(dtmElements);
        jTable.setIntercellSpacing(new Dimension(0, 0));
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.getTableHeader().setBackground(Constant.COLOR_GRAY_LIGHT_1);
        jTable.getTableHeader().setForeground(Constant.COLOR_BLUE_DARK_2);
        jTable.getTableHeader().setPreferredSize(new Dimension(0, 50));
        jTable.getTableHeader().setFont(Constant.FONT_ARIAL_ROUNDER_17);
        jTable.getTableHeader().setResizingAllowed(false);
        jTable.setFont(Constant.FONT_ARIAL_ROUNDER_15);
        jTable.setBackground(Color.WHITE);
        jTable.setFillsViewportHeight(false);
        jTable.setRowHeight( 35 );
        jTable.setBorder(null);
        jTable.addMouseListener(mouseListener);
        jTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setForeground(Color.white);
        jScrollPane.setBorder(null);
        jScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(jScrollPane, BorderLayout.CENTER);
        this.setBorder(null);
    }

    public void addRowTable(Object[] vector){
        dtmElements.addRow(vector);
    }

    public void cleanRowsTable() {
        dtmElements.setNumRows(0);
    }

    private void centerText() {
        DefaultTableCellRenderer centeRenderer = new DefaultTableCellRenderer();
        centeRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(centeRenderer);
        }
    }

    public void addElementToTable(ArrayList<Object[]> matrix){
        cleanRowsTable();
        dtmElements.setColumnIdentifiers(Utilities.getKeys(Constant.H_PRODUCTS));
        this.centerText();
//        UtilitiesViews.getModelColumn(jtElements, 0, 75, 75, 75);
//        UtilitiesViews.getModelColumn(jtElements, 2, 55, 55, 55);
        for (Object[] objects : matrix) {
            addElementToTable(objects,true);
        }
    }

    public void addElementToTable(Object[] vector,boolean status){
        if (status){
            vector[2] = Utilities.toDecimalFormat((double) vector[2]);
            vector[4] = Utilities.toDecimalFormat((double) vector[4]);
            dtmElements.addRow(vector);
        }else{
            dtmElements.addRow(vector);
        }
    }

    public void addRowsToTable(ArrayList<Object[]> matrix, String[] newHeaders){
        cleanRowsTable();
        this.headers = newHeaders;
        dtmElements.setColumnIdentifiers(Utilities.getKeys(headers));
        this.centerText();
        for (Object[] objects : matrix) {
            addRowTable(objects);
        }
    }

    public int getColumnCountTable(){
        return jTable.getColumnCount();
    }

    public String getSelectedRow(Point point ){
        int selectedRow = jTable.rowAtPoint(point);
        return String.valueOf(jTable.getValueAt(selectedRow,0));
    }

    @Override
    public void changeLanguage() {
        if (jTable.getColumnCount() == Constant.H_PRODUCTS.length){
            dtmElements.setColumnIdentifiers(Utilities.getKeys(Constant.H_PRODUCTS));
            this.centerText();
//            UtilitiesViews.getModelColumn(jtElements, 0, 75, 75, 75);
//            UtilitiesViews.getModelColumn(jtElements, 2, 55, 55, 55);
        }else{
            dtmElements.setColumnIdentifiers(Utilities.getKeys(headers));
            this.centerText();
        }
    }
}
