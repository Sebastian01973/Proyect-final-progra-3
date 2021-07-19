package views.splash;

import com.sun.awt.AWTUtilities;
import nets.Client;
import persistence.JManagerFile;
import utilities.Utilities;
import views.Constant;
import views.JMainWindows;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JSplash extends JFrame implements Runnable {

    private JLabel jLabel1,jLabel2,jLabel3,jLabel4;

    private JMainWindows jMainWindows;
    private JManagerFile jManagerFile;
    private Client client;
    private Thread time = null;



    public JSplash(JManagerFile jManagerFile, JMainWindows jMainWindows, Client client) {
        this.jManagerFile = jManagerFile;
        this.jMainWindows = jMainWindows;
        this.client = client;
        this.setIconImage( new ImageIcon(getClass().getResource( Constant.IMG_LOGO)).getImage());
        initComponents();
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        time = new Thread(this);
        time.start();
    }

    private void initComponents(){
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setIcon(new ImageIcon(getClass().getResource(Constant.IMG_SPLASH)));

        jLabel2.setIcon(new ImageIcon(getClass().getResource(Constant.IMG_LOADING)));

        jLabel3.setFont(Constant.FONT_SEGOE_36);
        jLabel3.setForeground(Constant.COLOR_WHITE);
        jLabel3.setText(Utilities.getKey(Constant.TXT_LOADING));

        jLabel4.setIcon(new ImageIcon(getClass().getResource(Constant.IMG_ABOUT)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }

    public void loadImages(){
        client.writeInt(20);
        String pathImage = client.readString();
        String[] images = jManagerFile.readImages(pathImage);
        int size = Integer.parseInt(images[0]);
        for (int i = 1; i < size+1; i++) {
            File download = new File("images/products/"+images[i]);
            if (!download.exists()){
                client.getImage(images[i]);
            }
        }
        System.out.println("Finalizo Carga en splash");
    }

    @Override
    public void run() {
        while (time != null) {
            try {
                loadImages();
                Thread.sleep(4000);
                time = null;
                this.dispose();
                jMainWindows.setVisibleJDialogLogin(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Cargo todo");
    }
}
