import java.sql.*;

import org.json.simple.JSONObject;


public class SQLExecute {
	JSONObject obj = new JSONObject();
	
	public String SqlGet(String procedure) {
		String res = "";
		try {
			JSONObject obj_data = new JSONObject();
			Connection conn = DriverManager.getConnection("jdbc:mysql://172.24.16.131:6446/idmcmd?characterEncoding=latin1&" +
	                "user=root&password=edpho@Idm2020");

			PreparedStatement sta = conn.prepareStatement(procedure);
			ResultSetMetaData rsa = sta.getMetaData();
			ResultSet rs = sta.executeQuery();
            int count_column = rsa.getColumnCount();
//            obj.put("1", "DANU");
//            obj.put("2", "ADIT");
//            obj.put("3", "DIMAS");
              
            
                  while(rs.next()){
                    for(int i = 1;i<=count_column;i++){
                    	//System.out.println(rsa.getColumnName(i)+"-"+rs.getString(i));
//                    	obj_data.put(rsa.getColumnName(i), rs.getString(i));
                        //System.out.println("Push");    
                    	
                    }
                    obj.put("BRANCH_CODE",rs.getString(1));
                  
                    System.out.println(rs.getString(1));    
                  }
                  
            
            System.out.println(obj.toString()); 
			conn.close();
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		
		return res;	
	}
	
}
