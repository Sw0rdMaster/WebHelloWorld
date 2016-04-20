/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import Database.ConnectToDB;

/**
 *
 * @author Roman
 */
public class NameServlet extends HttpServlet {
    
    String responseString = "random";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InputStream in = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        
        String line;
        while((line = reader.readLine()) != null)
        {
            result.append(line);
        }
        
        try{
            JSONObject myJson = new JSONObject(result.toString());
            System.out.println("Anfrage von der App: " + result.toString());
            String task = myJson.getString("Task");
            if(task.equals("Register"))
            {
                ConnectToDB newDB = new ConnectToDB();
                newDB.registerUser(myJson.getString("Username"), myJson.getString("Password"));
                responseString = "Erfolgreich registriert";
            }
            if(task.equals("Login"))
            {
                ConnectToDB newDB = new ConnectToDB();
                responseString = newDB.userLogin(myJson.getString("Username"));
            }
            if(task.equals("GetUserDevices"))
            {
                JSONObject json1 = new JSONObject();
                json1.put("Device", "Dampfbad");
                json1.put("Name", "Dampfbad SFWQ-2007");
                json1.put("Musik", "true");
                json1.put("Sole", "false");
                json1.put("Light", "true");
                json1.put("Krauter", "false");
                json1.put("Smell", "true");
                
                JSONObject json2 = new JSONObject();
                json2.put("Device", "Dampfbad");
                json2.put("Name", "Dampfbad HFKF-1209");
                json2.put("Musik", "true");
                json2.put("Sole", "false");
                json2.put("Light", "true");
                json2.put("Krauter", "false");
                json2.put("Smell", "true");
                
                JSONArray json = new JSONArray();
                json.put(0, json1);
                json.put(1, json2);
                
                responseString = json.toString();
            }
            if(task.equals("GetAllDevices"))
            {
                JSONObject dampfbad1 = new JSONObject();
                dampfbad1.put("Device", "Dampfbad");
                dampfbad1.put("Name", "Dampfbad SFWQ-2007");
                dampfbad1.put("Musik", "true");
                dampfbad1.put("Sole", "false");
                dampfbad1.put("Light", "true");
                dampfbad1.put("Krauter", "false");
                dampfbad1.put("Smell", "true");
                
                JSONObject dampfbad2 = new JSONObject();
                dampfbad2.put("Device", "Dampfbad");
                dampfbad2.put("Name", "Dampfbad HFKF-1209");
                dampfbad2.put("Musik", "true");
                dampfbad2.put("Sole", "true");
                dampfbad2.put("Light", "true");
                dampfbad2.put("Krauter", "false");
                dampfbad2.put("Smell", "true");
                
                JSONObject dampfbad3 = new JSONObject();
                dampfbad3.put("Device", "Dampfbad");
                dampfbad3.put("Name", "Individuell");
                dampfbad3.put("Musik", "true");
                dampfbad3.put("Sole", "false");
                dampfbad3.put("Light", "false");
                dampfbad3.put("Krauter", "false");
                dampfbad3.put("Smell", "true");
                
                JSONObject sauna1 = new JSONObject();
                sauna1.put("Device", "Sauna");
                sauna1.put("Name", "Sauna PF3F9");
                sauna1.put("Musik", "true");
                sauna1.put("Sole", "false");
                sauna1.put("Light", "true");
                sauna1.put("Krauter", "false");
                sauna1.put("Smell", "true");
                
                JSONObject sauna2 = new JSONObject();
                sauna2.put("Device", "Sauna");
                sauna2.put("Name", "Sauna A4RW-200");
                sauna2.put("Musik", "true");
                sauna2.put("Sole", "true");
                sauna2.put("Light", "false");
                sauna2.put("Krauter", "false");
                sauna2.put("Smell", "false");
                
                JSONObject whirlpool1 = new JSONObject();
                whirlpool1.put("Device", "Whirlpool");
                whirlpool1.put("Name", "Whirlpool Blubberblub");
                whirlpool1.put("Musik", "false");
                whirlpool1.put("Sole", "true");
                whirlpool1.put("Light", "false");
                whirlpool1.put("Krauter", "false");
                whirlpool1.put("Smell", "false");
                
                JSONObject hotspring1 = new JSONObject();
                hotspring1.put("Device", "Hotspring");
                hotspring1.put("Name", "Hotspring Shining Sun");
                hotspring1.put("Musik", "false");
                hotspring1.put("Sole", "true");
                hotspring1.put("Light", "false");
                hotspring1.put("Krauter", "false");
                hotspring1.put("Smell", "true");
                
                JSONArray a = new JSONArray();
                a.put(dampfbad1);
                a.put(dampfbad2);
                a.put(dampfbad3);
                a.put(sauna1);
                a.put(whirlpool1);
                a.put(hotspring1);
                
                responseString = a.toString();
            }
            if(task.equals("Treatment"))
            {
                JSONObject element1 = new JSONObject();
                element1.put("Device", "Dampbad");
                element1.put("Name", "Dampfbad AGAF-200");
                element1.put("Light", "Blue");
                element1.put("Musik", "Rock");
                element1.put("Krauter", "Lavendel");
                element1.put("Dauer", "20min");
                element1.put("Sole", "false");
                
                JSONObject element2 = new JSONObject();
                element2.put("Device", "Sauna");
                element2.put("Name", "Sauna Sunspring");
                element2.put("Light", "Green");
                element2.put("Musik", "Classic");
                element2.put("Krauter", "false");
                element2.put("Dauer", "15min");
                element2.put("Sole", "false");
                
                JSONArray a = new JSONArray();
                a.put(element1);
                a.put(element2);
                
                responseString = a.toString();
            }
            if(task.equals("getTreatmentAims"))
            {
                JSONObject treatment1 = new JSONObject();
                treatment1.put("Treatment", "Entspannt fühlen");
                
                JSONObject treatment2 = new JSONObject();
                treatment2.put("Treatment", "Haut pflegen");
                
                JSONObject treatment3 = new JSONObject();
                treatment3.put("Treatment", "Kreislauf aktivieren");
                
                JSONArray a = new JSONArray();
                a.put(treatment1);
                a.put(treatment2);
                a.put(treatment3);
                
                responseString = a.toString();
                
            }
            if(task.equals("getConditions"))
            {
                JSONObject condition1 = new JSONObject();
                condition1.put("Condition", "Kopfschmerzen");
                
                JSONObject condition2 = new JSONObject();
                condition2.put("Condition", "Kreislaufprobleme");
                
                JSONObject condition3 = new JSONObject();
                condition3.put("Condition", "Unreine Haut");
                
                JSONArray a = new JSONArray();
                a.put(condition1);
                a.put(condition2);
                a.put(condition3);
                
                responseString = a.toString();
                
            }
            
            else
            {
                //responseString = "Kenn ich noch nicht";
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception: Reading on Server");
            System.err.println(e.getMessage());
        }
        
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("Task", "AddDevice");
        }
        catch(Exception e)
        {
            
        }
       
        System.out.println("Antwort an App " + responseString);
            try (PrintWriter out = response.getWriter()) {
            out.println(responseString);
            
        }
            responseString = "random";
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Hallo, ich bin GET");

        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        processRequest(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
