package utilities;

import persistence.HandlerLanguage;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Properties;

public class Utilities {

    public static void moveRight(int start,int end,long sleep,int jump,JPanel panel) {
        (new Thread() {
            public void run() {
                for(int i = start; i <= end; i += jump) {
                    try {
                        Thread.sleep(sleep);
                        panel.setLocation(i, panel.getY());
                    } catch (Exception var3) {
                        var3.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public static void moveLeft(int start,int end,long sleep,int jump,JPanel panel) {
        (new Thread() {
            public void run() {
                for(int i = start; i >= end; i -= jump) {
                    try {
                        Thread.sleep(sleep);
                        panel.setLocation(i, panel.getY());
                    } catch (Exception var3) {
                        var3.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public static void getModelColumn(JTable jTable, int numCol, int minWidth, int maxWidth, int preferWidth) {
        TableColumn column;
        column = jTable.getColumnModel().getColumn(numCol);
        column.setPreferredWidth(preferWidth);
        column.setMaxWidth(maxWidth);
        column.setMinWidth(minWidth);
    }

    public static String toDecimalFormat( double value ) {
        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
        df.applyPattern( "$ ##############,###.##" );
        String strValue = df.format( value );
        return strValue;
    }

    public static Properties generateProperties(String pathFile ) throws IOException {
        Properties properties = new Properties();
        InputStream input = new FileInputStream(pathFile);
        properties.load( input );
        return properties;
    }

    public static void saveProperties( Properties properties, String pathFile ) throws IOException{
        FileOutputStream output = new FileOutputStream(pathFile);
        properties.store(output, null);
        output.close();
    }

    public static Object[] getKeys(Object[] keys) {
        String[] auxConstants = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            auxConstants[i] = getKey(String.valueOf(keys[i]));
        }
        return auxConstants;
    }

    public static String getKey(String key) {
        return HandlerLanguage.languageProperties.getProperty(key);
    }
}
