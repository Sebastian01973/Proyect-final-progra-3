package nets;
import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Connection {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public Connection(Socket socket) {
        try {
            this.socket = socket;
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            Logger.getGlobal().severe(e.toString());
        }
        Logger.getGlobal().info("Cliente Aceptado");
    }


    public void closeSocket(){
        try {
            this.socket.close();
        } catch (IOException e) {
           Logger.getGlobal().severe("Close "+e.toString());
        }
    }

    public int availableWeb(){
        int aux = 0;
        try {
            aux = input.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aux;
    }

    public String writeFile() {
        String nameIMage = "";
        try {
             nameIMage = readString();
            int sizeFile = readInt();
            System.out.println(" Nombre: " + nameIMage);
            System.out.println("Recibiendo Archivo");

            BufferedInputStream bfInput = new BufferedInputStream(input);
            BufferedOutputStream bfOutPut = new BufferedOutputStream(new FileOutputStream("images/"+nameIMage));

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
        return nameIMage;
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


    public int readInt(){
        int auxInt = 0;
        try {
            auxInt = input.readInt();
        } catch (IOException e) {
            Logger.getGlobal().severe("input int " + e.toString());
        }
        return auxInt;
    }

    public void writeInt(int integer){
        try {
            output.writeInt(integer);
        } catch (IOException e) {
            Logger.getGlobal().severe("output int "+e.toString());
        }
    }

    public double readDouble(){
        double auxDouble = 0;
        try {
            auxDouble = input.readDouble();
        } catch (IOException e) {
            Logger.getGlobal().severe("input double "+e.toString());
        }
        return auxDouble;
    }

    public void writeDouble(double d){
        try {
            output.writeDouble(d);
        } catch (IOException e) {
            Logger.getGlobal().severe("output double "+e.toString());
        }
    }

    public void writeBoolean(boolean b){
        try {
            output.writeBoolean(b);
        } catch (IOException e) {
            Logger.getGlobal().severe("output boolean "+e.toString());
        }
    }

    public boolean readBoolean(){
        boolean auxBoolean = true;
        try {
            auxBoolean = input.readBoolean();
        } catch (IOException e) {
            Logger.getGlobal().severe("input boolean "+e.toString());
        }
        return auxBoolean;
    }

    public String readString(){
        String auxString = "";
        try {
            auxString = input.readUTF();
        } catch (IOException e) {
            Logger.getGlobal().severe("input String "+e.toString());
        }
        return auxString;
    }

    public void writeString(String string){
        try {
            output.writeUTF(string);
        } catch (IOException e) {
            Logger.getGlobal().severe("output String "+e.toString());
        }
    }
}
