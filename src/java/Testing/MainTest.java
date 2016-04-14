/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;

import Database.ConnectToDB;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Roman
 */
public class MainTest {
    
        public static void printResult(JSONObject input)
        {
            try{
            System.out.println(input.get("firstName"));
            
            if(input.get("lastName") != null)
            {
                System.out.println("true");
                System.out.println(input.get("lastName"));
            }
            else
            {
                System.out.println("false");
            }
            }
            catch(Exception e)
            {
                
            }
            
            
        }
        
        public static void oldMain()
        {
            /*  Glassfish (Java EE 8 Code)
            JsonObject value = Json.createObjectBuilder()
            .add("Task", "RegisterUser")
            .add("Username", "Johnny7")
            .add("Password", "NotYetEncrypted")
            .build();*/
        
        JSONObject jsonObj = null;
        
        String uName = "SuperMario";
        
   
        
        try{
            /*
            jsonObj = new JSONObject(
                    "{\"Task\":\"RegisterUser\","
                            + "\"Username:" + uName
                            + "\"Password\":\"NotYetEncrypted\"}");
                            */
            jsonObj = new JSONObject();
            jsonObj.put("Task", "RegisterUser");
            jsonObj.put("Username", uName);
            jsonObj.put("Password", "NotEncrypted");
        }
        catch(Exception e)
        {
            
        }
        
        System.out.println("Ich bin JSONObject" + jsonObj);
            
            System.out.println(jsonObj.toString());
            printResult(jsonObj);
            

            int tmp;
            String data = "";
                try {
                URL url = new URL("http://localhost:8080/WebHelloWorld/NameServlet");

                //String message ="name=roman&password=143";

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(jsonObj.toString().getBytes("utf-8"));
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                System.out.println("Vom Server erhaltene Antwort" + data);
                is.close();
                httpURLConnection.disconnect();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    	public static void main(String [] args)
	{
            String a = "{\n" +
"    \"cars\": {\n" +
"        \"Nissan\": [\n" +
"            {\"model\":\"Sentra\", \"doors\":4},\n" +
"            {\"model\":\"Maxima\", \"doors\":4}\n" +
"        ],\n" +
"        \"Ford\": [\n" +
"            {\"model\":\"Taurus\", \"doors\":4},\n" +
"            {\"model\":\"Escort\", \"doors\":4}\n" +
"        ]\n" +
"    }\n" +
"}";
            
            
            try{
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                jsonObject.put("Car", "Monster");
                jsonArray.put(0, jsonObject);
                jsonObject.put("Car", "Energy");
                jsonArray.put(1, jsonObject);
                jsonObject.put("Car", "Drink");
                jsonArray.put(2, jsonObject);
                
                System.out.println(jsonArray);
                System.out.println(jsonObject);
                System.out.println(jsonArray.length());
                
            }
            catch(Exception e)
            {
                System.err.println(e.getMessage());
            }
            
                    
        }
        
        

        
}
