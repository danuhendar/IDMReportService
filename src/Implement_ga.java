/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.html.parser.Entity;
/**
 *
 * @author Archie
 */
public class Implement_ga implements Interface_ga{
    
    Connection con;
    
    public Implement_ga(Connection con){
         this.con = con;
    }

    @Override
    public String call_get_data(String procedure,boolean option_message) {
        
        StringBuffer result = new StringBuffer();
            try{
                PreparedStatement sta = con.prepareStatement(procedure);
                ResultSetMetaData rsa = sta.getMetaData();
                int count_column = rsa.getColumnCount();
              

             
                ResultSet rs = sta.executeQuery();
                    while(rs.next()){
                      for(int i = 1;i<=count_column;i++){
                          result.append(rs.getString(i)+"%");
                      }
                      result.append("~");

                    }
               
                if(option_message == true){
                    if(result.toString().equals("")){
                        System.out.println("Data tidak ada"); 
                        //JOptionPane.showMessageDialog(null, "Data tidak ada");
                    }else{

                    }
                }else{
                    
                }

            
            }catch(Exception exc){exc.printStackTrace();}

       return result.toString();
    }

    @Override
    public boolean call_upd_fetch(String procedure,boolean option_message) {
       boolean hasil = false;
       try{
           PreparedStatement sta = con.prepareStatement(procedure);
           int rsa  = sta.executeUpdate();
           if(rsa == 1){
               hasil = true;
               //JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
               if(option_message == true){
                   System.out.println("Data berhasil disimpan");
                   //JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
               }else{
                   //-- tidak menampilkan pesan --//
               }
           }else{
               hasil = false;
               //JOptionPane.showMessageDialog(null, "Data gagal disimpan");
           }
       }catch(Exception exc){
           exc.getMessage();
           
           //JOptionPane.showMessageDialog(null, exc.getCause().toString(),"Pesan error",JOptionPane.ERROR_MESSAGE);
       }
      
       return hasil;
    }
    
    @Override
    public boolean cek(String procedure) {
        
        
            boolean stat = false;
            StringBuffer result = new StringBuffer();
            try{
                PreparedStatement sta = con.prepareStatement(procedure);
                ResultSet rs = sta.executeQuery();
                
               
                    if(rs.next()){
                        stat = true;
                    }else{
                        stat = false;
                    }
                
                
                
        }catch(Exception exc){
            exc.printStackTrace();
        }
        
        return stat;
    }
    
    @Override
    public int cek_data(String procedure) {
        
        
            int stat = 0;
            StringBuffer result = new StringBuffer();
            try{
                PreparedStatement sta = con.prepareStatement(procedure);
                ResultSet rs = sta.executeQuery();
                
                while(rs.next()){
                    String id_master_produk  = rs.getString(1);
                    if(id_master_produk.equals("")){
                        stat = 0;
                    }else{
                        stat = 1;
                    }
                }
                
                
        }catch(Exception exc){
            exc.printStackTrace();
        }
        
        return stat;
    }

  

    public String call_get_procedure(String procedure,int count_column, boolean option_message) {
       StringBuffer result = new StringBuffer();
            try{
                PreparedStatement sta = con.prepareStatement(procedure);
                ResultSet rs = sta.executeQuery();
                    while(rs.next()){
                      for(int i = 1;i<=count_column;i++){
                          result.append(rs.getString(i)+"%");
                      }
                      result.append("~");

                    }
               
                if(option_message == true){
                    if(result.toString().equals("")){
                        JOptionPane.showMessageDialog(null, "Data tidak ada");
                    }else{

                    }
                }else{
                    
                }
                //con.close();    
                
            }catch(Exception exc){exc.printStackTrace();}
                
       return result.toString();
    }



    
}
