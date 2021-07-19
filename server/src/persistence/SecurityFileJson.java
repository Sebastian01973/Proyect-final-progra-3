package persistence;


import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class SecurityFileJson {

    public static final String PATH_FILE = "resources/Clients.out";
    public static final String PATH_FILE_PRODUCT = "resources/Products.out";
    private File file;


    public File getFile() {
        return file;
    }

    public String writeImages(String[] images) {
        JsonObject root = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonArray.addAll(Arrays.asList(images));
        root.put("Root", jsonArray);
        return root.toJson();
    }

    public String writeProductClients(Object[] objects){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("Name", objects[0]);
        jsonObject.put("Type", objects[1]);
        jsonObject.put("Units", objects[2]);
        jsonObject.put("Price", objects[3]);
        jsonObject.put("total", objects[4]);
        jsonObject.put("CodeImage", objects[5]);
        return jsonObject.toJson();
    }

    public String writeProduct(Object[] product) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("Name", product[0]);
        jsonObject.put("Type", product[1]);
        jsonObject.put("unit", product[2]);
        jsonObject.put("Price", product[3]);
        return jsonObject.toJson();
    }

    public Object[] readProductClient(String json) {
        JsonObject jSonObj = null;
        Object[] product = null;
        try {
            jSonObj = (JsonObject) Jsoner.deserialize(json);
            String name = jSonObj.getString("Name");
            String type = jSonObj.getString("Type");
            int units = Integer.parseInt(jSonObj.getString("unit"));
            double price = Double.parseDouble(jSonObj.getString("Price"));
            product = new Object[]{name, type, units, price};
        } catch (DeserializationException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Object[] readProduct(String json) {
        JsonObject jSonObj = null;
        Object[] product = null;
        try {
            jSonObj = (JsonObject) Jsoner.deserialize(json);
            String name = jSonObj.getString("Name");
            int type = Integer.parseInt(jSonObj.getString("Type"));
            int units = Integer.parseInt(jSonObj.getString("unit"));
            double price = Double.parseDouble(jSonObj.getString("Price"));
            product = new Object[]{name, type, units, price};
        } catch (DeserializationException e) {
            e.printStackTrace();
        }
        return product;
    }

    public ArrayList<Object[]> readClientToFile() {
        ArrayList<Object[]> arrayList = new ArrayList<>();
        JsonObject jSonObj = null;
        try {
            jSonObj = (JsonObject) Jsoner.deserialize(new FileReader(PATH_FILE));
            JsonArray array = (JsonArray) jSonObj.get("Root");
            for (Object object : array) {
                JsonObject object1 = (JsonObject) object;
                String nick = object1.getString("NickName");
                String password = object1.getString("Password");
                String name = object1.getString("Name");
                Double wallet = Double.parseDouble(object1.getString("Wallet"));
                arrayList.add(new Object[]{nick, password, name, wallet});
            }
        } catch (DeserializationException | IOException e) {
            Logger.getGlobal().severe("No cargo los clientes " + e.toString());
        }
        return arrayList;
    }

    public ArrayList<Object[]> readProductToFile() {
        ArrayList<Object[]> arrayList = new ArrayList<>();
        JsonObject jSonObj = null;
        try {
            jSonObj = (JsonObject) Jsoner.deserialize(new FileReader(PATH_FILE_PRODUCT));
            JsonArray array = (JsonArray) jSonObj.get("Root");
            for (Object object : array) {
                JsonObject object1 = (JsonObject) object;
                String name = object1.getString("Name");
                String type = object1.getString("Type");
                double price = Double.parseDouble(object1.getString("Price"));
                double total = Double.parseDouble(object1.getString("total"));
                int unit = Integer.parseInt(object1.getString("Units"));
                String imageCode = object1.getString("CodeImage");
                arrayList.add(new Object[]{name, type, price, unit, total, imageCode});
            }
        } catch (IOException | DeserializationException e) {
            Logger.getGlobal().severe("No cargo los productos " + e.toString());
        }
        return arrayList;
    }

    public void writeProductToFile(ArrayList<Object[]> arrayList, String path) {
        file = new File(path);
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            JsonObject root = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            for (Object[] objects : arrayList) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.put("Name", objects[0]);
                jsonObject.put("Type", objects[1]);
                jsonObject.put("Units", objects[2]);
                jsonObject.put("Price", objects[3]);
                jsonObject.put("total", objects[4]);
                jsonObject.put("CodeImage", objects[5]);
                jsonArray.add(jsonObject);
            }
            root.put("Root", jsonArray);
            fileWriter.write(root.toJson());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            Logger.getGlobal().severe("No escribio los productos " + e.toString());
        }
    }

    public String writeProductToFile(ArrayList<Object[]> arrayList) {
        String json = "";
        JsonObject root = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (Object[] objects : arrayList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("Name", objects[0]);
            jsonObject.put("Type", objects[1]);
            jsonObject.put("Units", objects[2]);
            jsonObject.put("Price", objects[3]);
            jsonObject.put("total", objects[4]);
            jsonObject.put("CodeImage", objects[5]);
            jsonArray.add(jsonObject);
        }
        root.put("Root", jsonArray);
        json = root.toJson();
        return json;
    }

    public void writeClientToFile(ArrayList<Object[]> arrayList) {
        try {
            file = new File(PATH_FILE);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            JsonObject root = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            for (Object[] objects : arrayList) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.put("NickName", objects[0]);
                jsonObject.put("Password", objects[1]);
                jsonObject.put("Name", objects[2]);
                jsonObject.put("Wallet", objects[3]);
                jsonArray.add(jsonObject);
            }
            root.put("Root", jsonArray);
            fileWriter.write(root.toJson());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            Logger.getGlobal().severe("No escribio los clientes " + e.toString());
        }
    }

}
