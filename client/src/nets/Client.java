package nets;

import com.jcraft.jsch.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class Client {

    private static final int PORT = 22320;
//    private static final String HOST = "186.114.217.181";
    private static final String HOST = "localHost";
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public Client(){
        try {
            socket = new Socket(HOST,PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String nameImage) {
        try {
            nameImage = readString();
            int sizeFile = readInt();

            BufferedInputStream bfInput = new BufferedInputStream(input);
            BufferedOutputStream bfOutPut = new BufferedOutputStream(new FileOutputStream("images/products/"+nameImage));

            byte[] buffer = new byte[sizeFile];

            for (int i = 0; i < sizeFile; i++) {
                buffer[i] = (byte) bfInput.read();
            }

            bfOutPut.write(buffer);

            bfOutPut.flush();
            bfOutPut.close();
            System.out.println("Archivo recibido");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getImage(String nameImage){
        JSch ssh = new JSch();
        try {
            Session session = ssh.getSession("sebastian.martinez07","186.114.217.181",22);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword("201914232");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.cd(sftp.pwd());
            sftp.get("proyectofinal/images/"+nameImage, "images/products/"+nameImage);
            Boolean success = true;
            if(success){
                System.out.println("Se Recibieron los archivos");
            }
            channel.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }

    public int available(){
        int aux = 0;
        try {
            aux = input.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aux;
    }

    public void closeSocket(){
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendImages(){
        JSch ssh = new JSch();
        try {
            Session session = ssh.getSession("sebastian.martinez07","186.114.217.181",22);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword("201914232");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftp = (ChannelSftp) channel;
            System.out.println(sftp.pwd());
            sftp.get("prueba.txt", "nausan.txt");
            Boolean success = true;
            if(success){
                System.out.println("Se Recibieron los archivos");
            }
            channel.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }

    public ImageIcon readImage(int width, int height) throws IOException {
        String name = readString();
        int sizeFile = readInt();
        BufferedInputStream bufferedInput = new BufferedInputStream(input);
        byte[] bytesFile = new byte[sizeFile];
        for (int i = 0; i < sizeFile; i++) {
            bytesFile[i] = (byte) bufferedInput.read();
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytesFile);
        BufferedImage image = ImageIO.read(inputStream);
        return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public int readInt(){
        int auxInt = 0;
        try {
            auxInt = input.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auxInt;
    }

    public void writeInt(int integer){
        try {
            output.writeInt(integer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double readDouble(){
        double auxDouble = 0;
        try {
            auxDouble = input.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auxDouble;
    }

    public void writeDouble(double d){
        try {
            output.writeDouble(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean readBoolean(){
        boolean auxBoolean = true;
        try {
            auxBoolean = input.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auxBoolean;
    }

    public void writeBoolean(boolean b){
        try {
            output.writeBoolean(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeString(String string){
        try {
            output.writeUTF(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readString(){
        String auxString = "";
        try {
            auxString = input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auxString;
    }

}
