package persistence;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

public class JManagerFileJson {

    private File file;


    public File getFile() {
        return file;
    }

    public Object[] readProduct(String json){
        JsonObject jSonObj = null;
        Object[] product = null;
        try {
            jSonObj = (JsonObject) Jsoner.deserialize(json);
            String name = jSonObj.getString("Name");
            String type = jSonObj.getString("Type");
            int units = Integer.parseInt(jSonObj.getString("unit"));
            double price = Double.parseDouble(jSonObj.getString("Price"));
            product = new Object[]{name,type,units,price};
        } catch (DeserializationException e) {
            e.printStackTrace();
        }
        return product;
    }

    public String writeProduct(Object[] product){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("Name",product[0]);
        jsonObject.put("Price",product[1]);
        jsonObject.put("Type",product[2]);
        jsonObject.put("unit",product[3]);
        return jsonObject.toJson();
    }

    public ArrayList<Object[]> readClientToFile(String pathImage){
        ArrayList<Object[]> arrayList = new ArrayList<>();
        JsonObject jSonObj = null;
        try {
            jSonObj = (JsonObject) Jsoner.deserialize(pathImage);
            JsonArray array =(JsonArray)jSonObj.get("Root");
            for (Object object : array) {
                JsonObject object1 = (JsonObject) object;
                String nick = object1.getString("NickName");
                String password = object1.getString("Password");
                Double wallet = Double.parseDouble(object1.getString("Wallet"));
                String name = object1.getString("Name");
                String[] names = name.split(",");
                arrayList.add(new Object[]{names[0],names[1],nick,password,wallet});
            }
        } catch (DeserializationException e) {
            Logger.getGlobal().severe("No cargo los clientes "+e.toString());
        }
        return arrayList;
    }

    public ArrayList<Object[]> readProductToFile(String products) {
        ArrayList<Object[]> arrayList = new ArrayList<>();
        JsonObject jSonObj = null;
        try {
            jSonObj = (JsonObject) Jsoner.deserialize(products);
            JsonArray array = (JsonArray) jSonObj.get("Root");
            for (Object object : array) {
                JsonObject object1 = (JsonObject) object;
                String name = object1.getString("Name");
                String type = object1.getString("Type");
                double price = Double.parseDouble(object1.getString("Price"));
                double total = Double.parseDouble(object1.getString("total"));
                int unit = Integer.parseInt(object1.getString("Units"));
                String imageCode = object1.getString("CodeImage");
                arrayList.add(new Object[]{name,type,price,unit,total,imageCode});
            }
        }catch (DeserializationException e){
            Logger.getGlobal().severe("No cargo los productos "+e.toString());
        }
        return arrayList;
    }
}
