package nets;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class Admin {

    private static final int PORT = 22320;
//    private static final String HOST = "186.114.217.181";
    private static final String HOST = "localHost";
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public Admin() {
        try {
            socket = new Socket(HOST, PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int available() {
        int aux = 0;
        try {
            aux = input.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aux;
    }

    public void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readInt() {
        int auxInt = 0;
        try {
            auxInt = input.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auxInt;
    }

    public void writeInt(int integer) {
        try {
            output.writeInt(integer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBytes(byte[] bytes) {
        try {
            output.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile() {
        try {
            String nameIMage = readString();
            int sizeFile = readInt();

            BufferedInputStream bfInput = new BufferedInputStream(input);
            BufferedOutputStream bfOutPut = new BufferedOutputStream(new FileOutputStream("images/" + nameIMage));

            byte[] buffer = new byte[sizeFile];

            for (int i = 0; i < sizeFile; i++) {
                buffer[i] = (byte) bfInput.read();
            }

            bfOutPut.write(buffer);

            bfOutPut.flush();
            bfOutPut.close();
        } catch (IOException e) {
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

    public void sendFile(File file) {
        try {
            System.out.println(file.getName());
            int sizeFile = (int) file.length();
            writeString(file.getName());
            output.writeInt(sizeFile);

            BufferedInputStream bfInput = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bfOutput = new BufferedOutputStream(output);

            byte[] bytesFile = new byte[sizeFile];
            bfInput.read(bytesFile);

            bfOutput.write(bytesFile);

            bfOutput.flush();
            bfInput.close();
            System.out.println("Archivo Enviado " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double readDouble() {
        double auxDouble = 0;
        try {
            auxDouble = input.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auxDouble;
    }

    public void writeDouble(double d) {
        try {
            output.writeDouble(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean readBoolean() {
        boolean auxBoolean = true;
        try {
            auxBoolean = input.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auxBoolean;
    }

    public void writeBoolean(boolean b) {
        try {
            output.writeBoolean(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeString(String string) {
        try {
            output.writeUTF(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readString() {
        String auxString = "";
        try {
            auxString = input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auxString;
    }
}
