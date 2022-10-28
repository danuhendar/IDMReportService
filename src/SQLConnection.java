/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Mr.Danu
 */
public class SQLConnection {
    private static Connection con;
    
    public String DecodeString(String encodedString){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
	String decodedString = new String(decodedBytes);
         
        return decodedString;   
    } 
    public String EncodeString(String plain_text){
        String encodedString = "";
        try{
            String originalInput = plain_text;
            encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return encodedString;   
    } 
    public Connection get_connection_db(String host,String user,String password,String port,String db){
        
        try {
            
                
                	/*
                    Properties p = new Properties();
                    p.load(new FileInputStream("setting.ini"));
                   
        			
                    String host = gf.getIp_database();
                    String user = gf.getUser_database();
                    String password = gf.getPass_database();
                    String port = gf.getPort_database();
                    String db = gf.getNama_database();
                     */
        	
                	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	                con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?characterEncoding=latin1&autoReconnect=true",user,password);
	                System.out.println("DB\t:\tSUKSES KONEKSI DB : "+host);  
            
        } catch (Exception e) {
            System.out.println("ERROR KONEKSI DB : "+e.getMessage());
            //System.exit(0);
        }
        return con;
    }
      
     public void disconnect_db(Connection koneksi){
        try {
             koneksi.close();
             System.err.println("KONEKSI DB : TERPUTUS");
             con = null;
        } catch (SQLException ex) {
             System.out.println("ERROR DISKONEK DB : "+ex.getMessage());
             System.exit(0);
        }
         
    }
    
      
     
}
