/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.MongoClient;
import java.io.*;
import org.eclipse.paho.client.mqttv3.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.misc.*;
/**
 *
 * @author Mr.Danu
 */
public class Global_function {
    Interface_ga inter_login;
    //SQLConnection sqlcon = new SQLConnection();
    //Connection con;
    MqttClient client_transreport = null; 
    public Global_function(){
       	Read_Setting_ini();  
       	//con = sqlcon.get_connection_db(getIp_database(),getUser_database(),getPass_database(),getPort_database(),getNama_database());
           //inter_login  = new Implement_ga(con);
           //get_ConnectionMQtt();
           //is_proses_setting_main();
//           if(con == null) {
//           	System.out.println("TIDAK KONEK KE DB UTAMA");
//           	//publish_main_setting();
//           }else {
//           	System.out.println("KONEK KE DB UTAMA");
//           }
           
       }


    String ip_broker;
       String port_broker;
       String username_broker;
       String password_broker;
       String cleansession;
       String keepalive;
       String reconnect;
       String will_retained;
       String is_mongo_db;
       String ip_mongodb;
       String port_mongodb;
       String max_inflight;
       String ip_database;
       String user_database;
       String pass_database;
       String port_database;
       String nama_database;
       String id_reporter;
       String cabang;
       String topic;
       String tampilkan_query_console;
       
       String time_message;
       String batas_menit;
       String max_vm_used;
       String is_restart_service;
       String list_single_service_523;
       String list_single_service_223;
       String list_multiple_service_523;
       String list_multiple_service_223;
       String max_message_incoming_mb;
       
       public String getMaxMessageIncomingMB() {
	   		return max_message_incoming_mb;
	   }
	
	   public void setMaxMessageIncomingMB(String max_message_incoming_mb) {
	   		this.max_message_incoming_mb = max_message_incoming_mb;
	   } 
	   	
       public String getListMultipleService223() {
	   		return list_multiple_service_223;
	   	}
	
	   	public void setListMultipleService223(String list_multiple_service_223) {
	   		this.list_multiple_service_223 = list_multiple_service_223;
	   	} 
	   	
        public String getListMultipleService523() {
	   		return list_multiple_service_523;
	   	}
	
	   	public void setListMultipleService523(String list_multiple_service_523) {
	   		this.list_multiple_service_523 = list_multiple_service_523;
	   	} 
   
        public String getListSingleService223() {
	   		return list_single_service_223;
	   	}
	
	   	public void setListSingleService223(String list_single_service_223) {
	   		this.list_single_service_223 = list_single_service_223;
	   	} 
    
        public String getListSingleService523() {
	   		return list_single_service_523;
	   	}
	
	   	public void setListSingleService523(String list_single_service_523) {
	   		this.list_single_service_523 = list_single_service_523;
	   	} 
     
   	
        public String getIsRestartService() {
    		return is_restart_service;
    	}

    	public void setIsRestartService(String is_restart_service) {
    		this.is_restart_service = is_restart_service;
    	} 
      
       public String getMaxVM() {
     		return max_vm_used;
     	}

     	public void setMaxVM(String max_vm_used) {
     		this.max_vm_used = max_vm_used;
     	} 
     	
   public String getBatasMenit() {
  		return batas_menit;
  	}

  	public void setBatasMenit(String batas_menit) {
  		this.batas_menit = batas_menit;
  	}   
       
    public String getTampilkan_query_console() {
   		return tampilkan_query_console;
   	}

   	public void setTampilkan_query_console(String tampilkan_query_console) {
   		this.tampilkan_query_console = tampilkan_query_console;
   	}
   			
       
       
       	public String getCabang() {
   		return cabang;
   	}

   	public void setCabang(String cabang) {
   		this.cabang = cabang;
   	}

   	public String getTopic() {
   		return topic;
   	}

   	public void setTopic(String topic) {
   		this.topic = topic;
   	}

       	public String getIp_broker() {
   		return ip_broker;
   	}

   	public void setIp_broker(String ip_broker) {
   		this.ip_broker = ip_broker;
   	}

   	public String getPort_broker() {
   		return port_broker;
   	}

   	public void setPort_broker(String port_broker) {
   		this.port_broker = port_broker;
   	}

   	public String getUsername_broker() {
   		return username_broker;
   	}

   	public void setUsername_broker(String username_broker) {
   		this.username_broker = username_broker;
   	}

   	public String getPassword_broker() {
   		return password_broker;
   	}

   	public void setPassword_broker(String password_broker) {
   		this.password_broker = password_broker;
   	}

   	public String getCleansession() {
   		return cleansession;
   	}

   	public void setCleansession(String cleansession) {
   		this.cleansession = cleansession;
   	}

   	public String getKeepalive() {
   		return keepalive;
   	}

   	public void setKeepalive(String keepalive) {
   		this.keepalive = keepalive;
   	}

   	public String getReconnect() {
   		return reconnect;
   	}

   	public void setReconnect(String reconnect) {
   		this.reconnect = reconnect;
   	}

   	public String getWill_retained() {
   		return will_retained;
   	}

   	public void setWill_retained(String will_retained) {
   		this.will_retained = will_retained;
   	}

   	public String getIs_mongo_db() {
   		return is_mongo_db;
   	}

   	public void setIs_mongo_db(String is_mongo_db) {
   		this.is_mongo_db = is_mongo_db;
   	}

   	public String getIp_mongodb() {
   		return ip_mongodb;
   	}

   	public void setIp_mongodb(String ip_mongodb) {
   		this.ip_mongodb = ip_mongodb;
   	}

   	public String getPort_mongodb() {
   		return port_mongodb;
   	}

   	public void setPort_mongodb(String port_mongodb) {
   		this.port_mongodb = port_mongodb;
   	}

   	public String getMax_inflight() {
   		return max_inflight;
   	}

   	public void setMax_inflight(String max_inflight) {
   		this.max_inflight = max_inflight;
   	}

   	public String getIp_database() {
   		return ip_database;
   	}

   	public void setIp_database(String ip_database) {
   		this.ip_database = ip_database;
   	}

   	public String getUser_database() {
   		return user_database;
   	}

   	public void setUser_database(String user_database) {
   		this.user_database = user_database;
   	}

   	public String getPass_database() {
   		return pass_database;
   	}

   	public void setPass_database(String pass_database) {
   		this.pass_database = pass_database;
   	}

   	public String getPort_database() {
   		return port_database;
   	}

   	public void setPort_database(String port_database) {
   		this.port_database = port_database;
   	}

   	public String getNama_database() {
   		return nama_database;
   	}

   	public void setNama_database(String nama_database) {
   		this.nama_database = nama_database;
   	}

   	public String getId_reporter() {
   		return id_reporter;
   	}

   	public void setId_reporter(String id_reporter) {
   		this.id_reporter = id_reporter;
   	}
   	
	public String getTimeMessge() {
   		return time_message;
   	}

   	public void setTimeMessage(String time_message) {
   		this.time_message = time_message;
   	}


   public void Read_Setting_ini() {
       	try {
   		    JSONParser parser = new JSONParser();
   	        JSONObject obj = null;
   	        FileReader fr = new FileReader("setting.ini");
   	        BufferedReader br = new BufferedReader(fr);
   	        String line = br.readLine();
   	        
   	        try {
   	            obj = (JSONObject) parser.parse(line);
   	            
   	        } catch (org.json.simple.parser.ParseException ex) {
   	            ex.printStackTrace();
   	        }
   	        
   	        setIp_broker(obj.get("ip_broker").toString());
   	        setPort_broker(obj.get("port_broker").toString());
   	        setUsername_broker(obj.get("username_broker").toString());
   	        setPassword_broker(obj.get("password_broker").toString());
   	        setCleansession(obj.get("cleansession").toString());
   	        setKeepalive(obj.get("keepalive").toString());
   	        setReconnect(obj.get("reconnect").toString());
   	        setWill_retained(obj.get("will_retained").toString());
   	        setIs_mongo_db(obj.get("is_mongo_db").toString());
   	        setIp_mongodb(obj.get("ip_mongodb").toString());
   	        setPort_mongodb(obj.get("port_mongodb").toString());
   	        setMax_inflight(obj.get("max_inflight").toString());
   	        setIp_database(obj.get("ip_database").toString());
   	        setUser_database(obj.get("user_database").toString());
   	        setPass_database(obj.get("pass_database").toString());
   	        setPort_database(obj.get("port_database").toString());
   	        setNama_database(obj.get("nama_database").toString());
   	        setId_reporter(obj.get("id_reporter").toString());
   	        setCabang(obj.get("cabang").toString());
   	        setTopic(obj.get("topic").toString());
   	        setTampilkan_query_console(obj.get("tampilkan_query_console").toString());
   	        setBatasMenit(obj.get("batas_menit").toString());
   	        setMaxVM(obj.get("max_vm_used").toString());
   	        setIsRestartService(obj.get("is_restart_service").toString());
   	        setListSingleService523(obj.get("list_single_service_523").toString());
   	        setListSingleService223(obj.get("list_single_service_223").toString());
   	        setListMultipleService523(obj.get("list_multiple_service_523").toString());
   	        setListMultipleService223(obj.get("list_multiple_service_223").toString());
   	        setMaxMessageIncomingMB(obj.get("max_message_incoming_mb").toString());
   	        
   	        
   	        System.out.println("Load Setting Sukses");
       	}catch(Exception exc) {
       		exc.printStackTrace();
       	}
       }
       

   public boolean is_proses_setting_main() {
       	boolean res = false;
       	try {
       		
       		//Properties p = new Properties(); 
   	        //p.load(new FileInputStream("setting.ini"));
   	        String id_reporter = getId_reporter();
   	         
       		int qos_message = 0;
       		String rtopic_command = "SETTING_MAIN/"+id_reporter+"/";
       	    System.out.println("SUBS : "+rtopic_command);
               client_transreport.subscribe(rtopic_command,qos_message,new IMqttMessageListener() {
          
                           @Override
                           public void messageArrived(final String topic, final MqttMessage message) throws Exception {
                               //----------------------------- FILTER TOPIC NOT CONTAINS -------------------------------//
                                       
                               Date HariSekarang_run = new Date();
                               String payload = new String(message.getPayload());

                               String msg_type = "";
                               String message_ADT_Decompress = "";
                               try{
                                   message_ADT_Decompress = ADTDecompress(message.getPayload());
                                   msg_type = "json";
                               }catch(Exception exc){
                                   message_ADT_Decompress = payload;
                                   msg_type = "non json";
                               }

                                
                               UnpackJSON(message_ADT_Decompress);
                               //System.out.println(message_ADT_Decompress);
                               PrintMessage2("RECV > "+rtopic_command+"",1,msg_type,topic,Parser_TASK,Parser_FROM,Parser_TO,null,HariSekarang_run);
                               File f = new File("settting.ini");
                               if(f.exists()) {
                               	f.delete();
                               }else {
                               	
                               }
                               
                               FileWriter fw = new FileWriter("setting.ini");
                               BufferedWriter bw = new BufferedWriter(fw);
                               bw.write(Parser_HASIL);
                               bw.flush();
                               bw.close();
                               fw.close();
                               System.out.println("Tulis Konfigurasi Sukses");
   			    System.exit(0);
                           }
                       });
                       
       		res = true;
       	}catch(Exception exc) {
       		res =false;
       	}
       	
       	return res;
       }

   public void PublishMessageNotDocumenter(String topic,byte[] content,int counter,String plain_text_res_message,int qos){
           try {
             
               
               SimpleDateFormat sformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
               Date HariSekarang = new Date();
               JSONParser parser = new JSONParser();
               //-- sesi koneksi db --//
               //String file_attribute           = ReadFile("attribute");
               //String new_attribute            = sqlcon.DecodeString(file_attribute);
               //System.out.println("new_attribute : "+new_attribute);
               //String sp_new_attribute[]       = new_attribute.split("~");
               //String broker_primary[]         = sp_new_attribute[0].split(":");
               
               String res_broker_primary       = getIp_broker()+":"+getPort_broker();
             
               String res_username_primary     = getUsername_broker();
               String res_password_primary     = getPassword_broker();
               
               /* ssl://mqtt.cumulocity.com:8883 for a secure connection */
               //-------------------------------- TRANS CONNECTION ----------------------//
               MqttClient send_transreport = null;
               
               try{
                  
                   final String serverUrl   = "tcp://"+res_broker_primary;
                   //System.out.println("serverUrl : "+serverUrl);
                   String clientId = UUID.randomUUID().toString();
                   MemoryPersistence persistence = new MemoryPersistence();
                   send_transreport = new MqttClient(serverUrl, clientId,persistence);
                   MqttConnectOptions options = new MqttConnectOptions();
                   options.setCleanSession(true);
                   options.setKeepAliveInterval(1000);
                   options.setAutomaticReconnect(true);
             
                   if(res_username_primary.equals("null")||res_password_primary.equals("null")){
                       
                   }else if(res_username_primary.equals("")||res_password_primary.equals("")) {
                   	
                   }else{
                   
                       options.setUserName(res_username_primary);
                       options.setPassword(res_password_primary.toCharArray());
                   }
                  
                   send_transreport.connect(options);
                   MqttMessage message = new MqttMessage(content);
                   message.setQos(qos);
                   //message.setRetained(res_will_retained);
                   send_transreport.publish(topic, message);
                   //System.err.println("Publish Message");
                   send_transreport.disconnect();
                   UnpackJSON(plain_text_res_message);
                   PrintMessage2("SEND > "+Parser_TASK, counter, "json", topic, Parser_TASK, Parser_FROM, Parser_TO, HariSekarang, HariSekarang);
               }catch(Exception exc){
                  exc.printStackTrace();
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       } 

   public void publish_main_setting() {
      	try {
      		
            String id_reporter = getId_reporter();
               
      		 Parser_TASK = "MAIN_SETTING";
      		 Parser_ID = get_tanggal_curdate();
      		 Parser_SOURCE = "IDMReporter";
      		 Parser_COMMAND = getTopic().toString();
      		 Parser_OTP = "";
      		 Parser_TANGGAL_JAM = "";
      		 Parser_VERSI = "";
      		 Parser_HASIL = "";
      		 Parser_FROM = id_reporter;
      		 Parser_TO = "IDMReporter";
      		 Parser_SN_HDD = "";
      		 Parser_IP_ADDRESS = "";
      		 Parser_STATION = "";
      		 Parser_CABANG = getCabang();
      		 Parser_NAMA_FILE = "";
      		 Parser_CHAT_MESSAGE = "";
      		 Parser_REMOTE_PATH = "";
      		 Parser_LOCAL_PATH = "";
      		 Parser_SUB_ID = get_tanggal_curdate_curtime();
      		 
               
           String res_message = CreateMessage(Parser_TASK,Parser_ID,Parser_SOURCE,Parser_COMMAND,Parser_OTP,Parser_TANGGAL_JAM,Parser_VERSI,Parser_HASIL,Parser_FROM,Parser_TO,Parser_SN_HDD,Parser_IP_ADDRESS,Parser_STATION,Parser_CABANG,"",Parser_NAMA_FILE,Parser_CHAT_MESSAGE,Parser_REMOTE_PATH,Parser_LOCAL_PATH,Parser_SUB_ID);
           //System.err.println("res_message_otp : "+res_message);
           byte[] convert_message = res_message.getBytes("US-ASCII");
           byte[] bytemessage = compress(convert_message);
           String topic_dest = "SETTING_MAIN/";
           System.out.println("TOPIC DEST : "+topic_dest);
           PublishMessageNotDocumenter(topic_dest, bytemessage, 1, res_message,1);
               
      	}catch(Exception exc) {
      		System.out.println("Gagal publish ke Broker atas prosedure pengambilan setting main");
      	}
      }

    
   
    long previousJvmProcessCpuTime = 0;
    long previousJvmUptime = 0;
    String write_log;
    boolean res_write_log = false;
    Global_variable gv = new Global_variable();
  
   String Parser_TASK,
    Parser_ID,
    Parser_SOURCE,
    Parser_COMMAND,
    Parser_OTP,
    Parser_TANGGAL_JAM,
    Parser_VERSI,
    Parser_HASIL,
    Parser_FROM,
    Parser_TO,
    Parser_SN_HDD,
    Parser_IP_ADDRESS,
    Parser_STATION,
    Parser_CABANG,
    Parser_NAMA_FILE,
    Parser_CHAT_MESSAGE,
    Parser_REMOTE_PATH,
    Parser_LOCAL_PATH,
    Parser_SUB_ID; 
 
    
     
    public void UnpackJSON(String json_message){
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(json_message);
        } catch (org.json.simple.parser.ParseException ex) {
            ex.printStackTrace();
        }
        
        Parser_TASK = obj.get("TASK").toString();
        //gv.setParser_TASK(obj.get("TASK").toString());
        try{
            Parser_ID = obj.get("ID").toString();
            //gv.setParser_TASK(obj.get("ID").toString());
        }catch(Exception exc){
             gv.setParser_TASK("");
        }

        Parser_SOURCE = obj.get("SOURCE").toString();
        //gv.setParser_SOURCE(obj.get("SOURCE").toString());
        Parser_COMMAND = obj.get("COMMAND").toString();
        //gv.setParser_COMMAND(obj.get("COMMAND").toString());
        Parser_OTP = obj.get("OTP").toString();
        //gv.setParser_OTP(obj.get("OTP").toString());
        try{
           Parser_TANGGAL_JAM = obj.get("TANGGAL_JAM").toString();
           //gv.setParser_TANGGAL_JAM(obj.get("TANGGAL_JAM").toString());
        }catch(Exception exc){
            Parser_TANGGAL_JAM = "";
            //gv.setParser_TANGGAL_JAM("");
        }
        try{
            Parser_VERSI = obj.get("RESULT").toString().split("_")[7];
            //gv.setParser_VERSI(obj.get("RESULT").toString().split("_")[7]);
        }catch(Exception exc){
            Parser_VERSI = obj.get("VERSI").toString();
            //gv.setParser_VERSI(obj.get("VERSI").toString());
        }


        Parser_HASIL = obj.get("HASIL").toString();
        //gv.setParser_HASIL(obj.get("HASIL").toString());
        Parser_FROM = obj.get("FROM").toString();
        //gv.setParser_FROM(obj.get("FROM").toString());
        Parser_TO = obj.get("TO").toString();
        //gv.setParser_TO(obj.get("TO").toString());
        try{
            Parser_SN_HDD = Parser_HASIL.split("_")[3];
            //String hasil = gv.getParser_HASIL().split("_")[3];
            //gv.setParser_SN_HDD(hasil);
        }catch(Exception exc){
            //gv.setParser_SN_HDD(obj.get("SN_HDD").toString());
            Parser_SN_HDD = obj.get("SN_HDD").toString();
        }
        try{
            //String hasil = gv.getParser_HASIL().split("_")[4];
            //gv.setParser_IP_ADDRESS(hasil);
            Parser_IP_ADDRESS = Parser_HASIL.split("_")[4];
        }catch(Exception exc){
            try{
                //gv.setParser_IP_ADDRESS(obj.get("IP_ADDRESS").toString());
                Parser_IP_ADDRESS = obj.get("IP_ADDRESS").toString();
            }catch(Exception exc1){
                //gv.setParser_IP_ADDRESS("");
                Parser_IP_ADDRESS = "";
            }

        }
        
        try{
            Parser_STATION = Parser_HASIL.split("_")[2];
        }catch(Exception exc){
            Parser_STATION = obj.get("STATION").toString();;
        }
        
        Parser_CABANG = obj.get("CABANG").toString();
        try{
            Parser_NAMA_FILE = obj.get("NAMA_FILE").toString();
        }catch(Exception exc){
            Parser_NAMA_FILE = "";
        }
        try{
            Parser_CHAT_MESSAGE = obj.get("CHAT_MESSAGE").toString();
        }catch(Exception exc){
            Parser_CHAT_MESSAGE = "";
        }
        try{
            Parser_REMOTE_PATH = obj.get("REMOTE_PATH").toString();
        }catch(Exception exc){
            Parser_REMOTE_PATH = "";
        }
        try{
            Parser_LOCAL_PATH = obj.get("LOCAL_PATH").toString();
        }catch(Exception exc){
            Parser_LOCAL_PATH = "";
        }
        try{
            Parser_SUB_ID = obj.get("SUB_ID").toString();
        }catch(Exception exc){
            Parser_SUB_ID = "";
        }
        
    }
    
    public void MongoLogger() {
    	try {
    		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
    		mongoLogger.setLevel(Level.SEVERE); 
    	}catch(Exception exc) {
    		exc.printStackTrace();
    	}
    }
    
    public Document Create_document(String KDCAB,
					            String TASK,
					            String ID,
					            String SUB_ID,
					            String SOURCE,
					            String FROM,
					            String TO,
					            String OTP,
					            String STATION,
					            String IP,
					            String IN_KDTK,
					            String IN_NAMA_PC,
					            String SN_HDD,
					            String CMD,
					            String RESULT,
					            String CHAT_MSG,
					            String NAMA_FILE,
					            String REMOTE_PATH,
					            String LOCAL_PATH,
					            String DATE_TIME,
					            String VERSION,
					            String ADDTIME){
					
					
					Document doc = new Document("KDCAB",KDCAB)
					        .append("TASK",TASK)
					        .append("ID",ID)
					        .append("SUB_ID",SUB_ID)
					        .append("SOURCE",SOURCE)
					        .append("FROM",FROM)
					        .append("TO",TO)
					        .append("OTP",OTP)
					        .append("KDTK",IN_KDTK)
					        .append("NAMA_PC",IN_NAMA_PC)
					        .append("STATION",STATION)
					        .append("IP",IP)
					        .append("SN_HDD",SN_HDD)
					        .append("CMD",CMD)
					        .append("RESULT",RESULT)
					        .append("CHAT_MSG",CHAT_MSG)
					        .append("NAMA_FILE",NAMA_FILE)
					        .append("REMOTE_PATH",REMOTE_PATH)
					        .append("LOCAL_PATH",LOCAL_PATH)
					        .append("DATE_TIME",DATE_TIME)
					        .append("VERSION",VERSION)
					        .append("rowid",new ObjectId())
					        .append("ADDTIME",ADDTIME)                 
					;
		return doc;
    }  
    
    public boolean createCollection(String table,String nama_database) {
    	boolean res = false;
    	try {
    		
    		 String ip_mongodb = "";
             String port_mongodb = "";
             //Properties p = new Properties();
             try {
                 
                 ip_mongodb = getIp_mongodb();
                 port_mongodb = getPort_mongodb();
             } catch (Exception ex) {
                  System.err.println("ERROR READING setting.ini"+ex.getMessage());
             }
             
    		com.mongodb.client.MongoClient mongo = MongoClients.create("mongodb://"+ip_mongodb+":"+port_mongodb);  //(1)
            MongoDatabase db = mongo.getDatabase(nama_database);
             
             
    		MongoIterable <String> collection =  db.listCollectionNames();
    	    for(String s : collection) {
    	        if(s.equals(table)) {
    	        	System.out.println("Collection Exists");
    	        	res = true;
    	        }else {
    	        	db.createCollection(table);
    	        	System.out.println("Collection Tidak Exists");
    	        }
    	    }
            mongo.close();
    	}catch(Exception exc) {
    		res = false;
    	}
    	return res;
    }



 public void InsertDocument(String nama_database,String table,Document doc){
        try{
            String ip_mongodb = "";
            String port_mongodb = "";
            
            try {
                
                ip_mongodb = getIp_mongodb();
                port_mongodb = getPort_mongodb();
            } catch (Exception ex) {
                 System.err.println("ERROR READING setting.ini"+ex.getMessage());
            }

            com.mongodb.client.MongoClient mongo = MongoClients.create("mongodb://"+ip_mongodb+":"+port_mongodb);  //(1)
            MongoDatabase db = mongo.getDatabase(nama_database);
            boolean cek_collection = createCollection(table,nama_database);
            db.getCollection(table).insertOne(doc);
            mongo.close();
        }catch(Exception exc){
            exc.printStackTrace();
        }
       
}

   
    public boolean create_table_mysql(String nama_table) {
	   	boolean res = false;
	   	try {
	   		String get_syntax_generate_table = GetTransReport("SELECT CONTENT FROM setting WHERE ID = '1'", 1, true);
	   		inter_login.call_upd_fetch(get_syntax_generate_table.replace("transreport", nama_table), false);
	   		res = true;
	   	}catch(Exception exc) {
	   		res = false;
	   	}
	   	
	   	return res;
  	
    }
    
     
    
     public String get_tanggal_curdate_curtime_format(){
        String res = "";
        try {
            String get = GetTransReport("SELECT NOW() AS TANGGAL",1,true);
            res = get;
        } catch (Exception e) {
              res = "";  
        }
        
        return res;
    }
   
    public String get_curtime(){
        String res = "";
        try {
              int year = Calendar.getInstance().get(Calendar.YEAR);
              int month = Calendar.getInstance().get(Calendar.MONTH)+1;
              String bulan = "";
              if(month<10){
                  bulan = "0"+month;
              }else{
                  bulan = ""+month;
              }
              int d = Calendar.getInstance().get(Calendar.DATE);
              String tanggal = "";
              if(d<10){
                  tanggal = "0"+d;
              }else{
                  tanggal = ""+d;
              }
              int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
              String jam = "";
              if(h<10){
                  jam = "0"+h;
              }else{
                  jam = ""+h;
              }
              int min = Calendar.getInstance().get(Calendar.MINUTE);
              String menit = "";
              if(min<10){
                  menit = "0"+min;
              }else{
                  menit = ""+min;
              }
              int sec = Calendar.getInstance().get(Calendar.SECOND);
              String detik = "";
              if(sec<10){
                  detik = "0"+sec;
              }else{
                  detik = ""+sec;
              }
              String concat = jam+":"+menit;
              res = concat;                      
        } catch (Exception e) {
              res = "";  
        }
        
        return res;
    }
    
    public String get_id(boolean is_sub_id){
        String res = "";
        try {
              int year = Calendar.getInstance().get(Calendar.YEAR);
              int month = Calendar.getInstance().get(Calendar.MONTH)+1;
              String bulan = "";
              if(month<10){
                  bulan = "0"+month;
              }else{
                  bulan = ""+month;
              }
              int d = Calendar.getInstance().get(Calendar.DATE);
              String tanggal = "";
              if(d<10){
                  tanggal = "0"+d;
              }else{
                  tanggal = ""+d;
              }
              int h = Calendar.getInstance().get(Calendar.HOUR);
              String jam = "";
              if(h<10){
                  jam = "0"+h;
              }else{
                  jam = ""+h;
              }
              int min = Calendar.getInstance().get(Calendar.MINUTE);
              String menit = "";
              if(min<10){
                  menit = "0"+min;
              }else{
                  menit = ""+min;
              }
              int sec = Calendar.getInstance().get(Calendar.SECOND);
              String detik = "";
              if(sec<10){
                  detik = "0"+sec;
              }else{
                  detik = ""+sec;
              }
              
              String concat = "";
              if(is_sub_id) {
            	  concat = year+""+bulan+""+tanggal+""+jam+""+menit+""+detik;
              }else {
            	  concat = year+""+bulan+""+tanggal+""+jam+""+menit;
              }
              
                        
              res = concat;                      
        } catch (Exception e) {
              res = "";  
        }
        
        return res;
    }
    
    
    
    
    public String CreateMessage(String IN_TASK,
                              String IN_ID,
                              String IN_SOURCE,
                              String IN_COMMAND,
                              String IN_OTP,
                              String IN_TANGGAL_JAM,
                              String IN_VERSI,
                              String IN_HASIL,
                              String IN_FROM,
                              String IN_TO,
                              String IN_SN_HDD,
                              String IN_IP_ADDRESS,
                              String IN_STATION,
                              String IN_CABANG,
                              String IN_FILE,
                              String IN_NAMA_FILE,
                              String IN_CHAT_MESSAGE,
                              String IN_REMOTE_PATH,
                              String IN_LOCAL_PATH,
                              String IN_SUB_ID           
        ){
        String res = "";
        try {
            JSONObject obj = new JSONObject();
            obj.put("TASK",IN_TASK);
            obj.put("ID",IN_ID);
            obj.put("SOURCE",IN_SOURCE);
            obj.put("COMMAND",IN_COMMAND);
            obj.put("OTP",IN_OTP);
            obj.put("TANGGAL_JAM",IN_TANGGAL_JAM);
            obj.put("VERSI",IN_VERSI);
            obj.put("HASIL",IN_HASIL);
            obj.put("FROM",IN_FROM);
            obj.put("TO",IN_TO);
            obj.put("SN_HDD",IN_SN_HDD);
            obj.put("IP_ADDRESS",IN_IP_ADDRESS);
            obj.put("STATION",IN_STATION);
            obj.put("CABANG",IN_CABANG);
            obj.put("FILE",IN_FILE);
            obj.put("NAMA_FILE",IN_NAMA_FILE);
            obj.put("CHAT_MESSAGE",IN_CHAT_MESSAGE);
            obj.put("REMOTE_PATH",IN_REMOTE_PATH);
            obj.put("LOCAL_PATH",IN_LOCAL_PATH);
            obj.put("SUB_ID",IN_SUB_ID);

            res = obj.toJSONString();
        } catch (Exception e) {
            res = e.getMessage();
        }
        
        return res;
    }
    
    
      public void PublishNotCompressAndDocumenter(String topic,String pesan){
          try {
          
            int qos = 0;
            String msg_type = "non compress";
            SimpleDateFormat sformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Date HariSekarang = new Date();
            JSONParser parser = new JSONParser();
            
            //-- sesi koneksi db --//
            String file_attribute           = ReadFile("attribute");
            String new_attribute            = "";//sqlcon.DecodeString(file_attribute);
            //System.out.println("new_atCEKAKTItribute : "+new_attribute);
            String sp_new_attribute[]       = new_attribute.split("~");
            String broker_primary[]         = sp_new_attribute[0].split(":");
            
            String res_broker_primary       = broker_primary[0]+":"+broker_primary[1];
          
            String res_username_primary     = broker_primary[2];
            String res_password_primary     = broker_primary[3];
            
           
            //-------------------------------- TRANS CONNECTION ----------------------//
            
            MqttClient send_transreport = null;
            
          
               
                final String serverUrl   = "tcp://"+res_broker_primary;
                //System.out.println("serverUrl : "+serverUrl);
                String clientId = UUID.randomUUID().toString();
                MemoryPersistence persistence = new MemoryPersistence();
                send_transreport = new MqttClient(serverUrl, clientId,persistence);
                MqttConnectOptions options = new MqttConnectOptions();
                options.setCleanSession(true);
                options.setKeepAliveInterval(1000);
                options.setAutomaticReconnect(true);
          
                if(res_username_primary.equals("null")||res_password_primary.equals("null")){
                    
                }else{
                    options.setUserName(res_username_primary);
                    options.setPassword(res_password_primary.toCharArray());
                }
               
                send_transreport.connect(options);
                String res_message = pesan;
                byte[] convert_message = res_message.getBytes("US-ASCII");
                MqttMessage message = new MqttMessage(convert_message);
                message.setQos(qos);
                
                //message.setRetained(res_will_retained);
                send_transreport.publish(topic, message);
                //System.err.println("Connect Broker for Send : "+send_transreport.isConnected());
                //System.err.println("Publish Message");
                send_transreport.disconnect();
            
                UnpackJSON(res_message);
                //InsTransReport(Parser_TASK,Parser_ID,Parser_SOURCE,Parser_COMMAND,Parser_OTP,Parser_TANGGAL_JAM,Parser_VERSI,Parser_HASIL,Parser_TO,Parser_FROM,Parser_SN_HDD,Parser_IP_ADDRESS,Parser_STATION,Parser_CABANG,Parser_NAMA_FILE,Parser_CHAT_MESSAGE,Parser_REMOTE_PATH,Parser_LOCAL_PATH,Parser_SUB_ID,false,"INSERT","transreport");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          
       
    }
      
   public boolean ChangeData(String query){
        boolean res = false;
        try{
            //con = sqlcon.get_connection_db();
            //inter_login = new Implement_ga(con);
            res = inter_login.call_upd_fetch(query, false);
            //sqlcon.disconnect_db(con);
        }catch(Exception exc){
            exc.printStackTrace();
            res = false;
        }
        
        return res;
    }
     public String GetTransReport(String query,int column,boolean return_one_columns){
        String res = null;
        try{
            //con = sqlcon.get_connection_db();
            //inter_login = new Implement_ga(con);
           
                if(return_one_columns == true){
                    String get = inter_login.call_get_procedure(query, column, false);
                    res = get.split("~")[0].split("%")[0];
                }else{
                    String get = inter_login.call_get_procedure(query, column, false);
                    res = get;
                }
            //sqlcon.disconnect_db(con);
        
        }catch(Exception exc){
            exc.printStackTrace();
            res = exc.getMessage();
        }
        
        return res;
        
    }
      
    public boolean GetExistsData(String query,boolean is_exit){
        boolean res = false;
        try{
            //con = sqlcon.get_connection_db();
            //inter_login = new Implement_ga(con);
            boolean get = inter_login.cek(query);    
            res = get;
            //sqlcon.disconnect_db(con);
        }catch(Exception exc){
            exc.printStackTrace();
            res = false;
        }
        
        return res;
    }   
      
    public void PublishMessageAndDocumenter(String topic,byte[] content,int counter,String plain_text_res_message,int qos){
        try {
          
            
            SimpleDateFormat sformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Date HariSekarang = new Date();
            JSONParser parser = new JSONParser();
            //-- sesi koneksi db --//
            String file_attribute           = ReadFile("attribute");
            String new_attribute            = ""; //sqlcon.DecodeString(file_attribute);
            //System.out.println("new_attribute : "+new_attribute);
            String sp_new_attribute[]       = new_attribute.split("~");
            String broker_primary[]         = sp_new_attribute[0].split(":");
            
            String res_broker_primary       = broker_primary[0]+":"+broker_primary[1];
          
            String res_username_primary     = broker_primary[2];
            String res_password_primary     = broker_primary[3];
            
            /* ssl://mqtt.cumulocity.com:8883 for a secure connection */
            //-------------------------------- TRANS CONNECTION ----------------------//
            MqttClient send_transreport = null;
            
            try{
               
                final String serverUrl   = "tcp://"+res_broker_primary;
                //System.out.println("serverUrl : "+serverUrl);
                String clientId = UUID.randomUUID().toString();
                MemoryPersistence persistence = new MemoryPersistence();
                send_transreport = new MqttClient(serverUrl, clientId,persistence);
                MqttConnectOptions options = new MqttConnectOptions();
                options.setCleanSession(true);
                options.setKeepAliveInterval(1000);
                options.setAutomaticReconnect(true);
          
                if(res_username_primary.equals("null")||res_password_primary.equals("null")){
                    
                }else{
                    options.setUserName(res_username_primary);
                    options.setPassword(res_password_primary.toCharArray());
                }
               
                send_transreport.connect(options);
                MqttMessage message = new MqttMessage(content);
                message.setQos(qos);
                //message.setRetained(res_will_retained);
                send_transreport.publish(topic, message);
                System.err.println("Publish Message");
                send_transreport.disconnect();
                UnpackJSON(plain_text_res_message);
                if(Parser_TASK.contains("LOGIN")){
                    //InsTransReport(Parser_TASK,Parser_ID,Parser_SOURCE,sqlcon.EncodeString(Parser_COMMAND),Parser_OTP,Parser_TANGGAL_JAM,Parser_VERSI,sqlcon.EncodeString(Parser_HASIL),Parser_FROM,Parser_TO,Parser_SN_HDD,Parser_IP_ADDRESS,Parser_STATION,Parser_CABANG,Parser_NAMA_FILE,Parser_CHAT_MESSAGE,Parser_REMOTE_PATH,Parser_LOCAL_PATH,Parser_SUB_ID,false,"INSERT","transreport");
                }else{
                    //InsTransReport(Parser_TASK,Parser_ID,Parser_SOURCE,Parser_COMMAND,Parser_OTP,Parser_TANGGAL_JAM,Parser_VERSI,Parser_HASIL,Parser_FROM,Parser_TO,Parser_SN_HDD,Parser_IP_ADDRESS,Parser_STATION,Parser_CABANG,Parser_NAMA_FILE,Parser_CHAT_MESSAGE,Parser_REMOTE_PATH,Parser_LOCAL_PATH,Parser_SUB_ID,false,"INSERT","transreport");
                }
                
                PrintMessage2("SEND > "+Parser_TASK, counter, "json", topic, Parser_TASK, Parser_FROM, Parser_TO, HariSekarang, HariSekarang);
            }catch(Exception exc){
               exc.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
     public String ADTDecompress(byte[] data) {
        try {
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          ByteArrayInputStream bis = new ByteArrayInputStream(data);
          GZIPInputStream in = new GZIPInputStream(bis);
          byte[] buffer = new byte[1024];
          int len = 0;
          while ((len = in.read(buffer)) >= 0) {
            bos.write(buffer, 0, len);
          }
          in.close();
          bos.close();
          
          byte[]a = bos.toByteArray();
          String s = new String(a);
          return s;
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }
    
    public byte[] compress(byte[] str) throws Exception {
        ByteArrayOutputStream obj=new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(str);
        gzip.close();
        return obj.toByteArray();
    }
    
    
    // Method menghitung selisih dua waktu
    public String selisihDateTime(Date waktuSatu, Date waktuDua) {
        long selisihMS = Math.abs(waktuSatu.getTime() - waktuDua.getTime());
        long selisihDetik = selisihMS / 1000 % 60;
        long selisihMenit = selisihMS / (60 * 1000) % 60;
        long selisihJam = selisihMS / (60 * 60 * 1000) % 24;
        long selisihHari = selisihMS / (24 * 60 * 60 * 1000);
        String selisih = selisihHari + " hari " + selisihJam + " Jam "
                + selisihMenit + " Menit " + selisihDetik + " Detik";
        return selisih;
    }
  
    protected Date konversiStringkeDate(String tanggalDanWaktuStr,String pola, Locale lokal) {
        Date tanggalDate = null;
        SimpleDateFormat formatter;
        if (lokal == null) {
            formatter = new SimpleDateFormat(pola);
        } else {
            formatter = new SimpleDateFormat(pola, lokal);
        }
        try {
            tanggalDate = formatter.parse(tanggalDanWaktuStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return tanggalDate;
    }
    
    
    public boolean RestartApps(){
        boolean res = false;
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("restartapps.bat");
            res = true;
        } catch (Exception e) {
            res = false;
        }
        
        return res;
    }
    
    public String ReadFile(String file){
        String data = null;
        try {
         
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
                    
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return data;
    }
    
      public String ReadFileLog(String file){
        String res = null;
        try {
             
            
            File f = new File(file);
            if(f.exists()){
                Scanner myReader = new Scanner(f);
                while (myReader.hasNextLine()) {
                  String data = myReader.nextLine();
                  
                  res += data+"\n";
                }
                myReader.close();
            }else{
                try {
                    f.createNewFile();
                } catch (IOException ex) {
                    
                }
                res = "";
            }
          
          
        } catch (Exception e) {
           
        }
        
        return res;
    }
    
    
    public void writeLogTopic(String id,String sub_id,String filename,String message,boolean append_or_not){
        
        String folder = "history_broadcast";
        File f = new File(folder);
        if(f.exists()){
            
        }else{
            f.mkdir();
        }
        String path = "";
        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Global_function.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String folder_id_subid = path+"/"+folder+"/"+id+"_"+sub_id;
        System.out.println("folder idsub_id : "+folder_id_subid);
        File ffolder_id_subid = new File(folder_id_subid);
        if(ffolder_id_subid.exists()){
            
        }else{
            ffolder_id_subid.mkdir();
        }

        Logger logger = Logger.getLogger(path+"_"+folder+"_"+filename);

        // Create an instance of FileHandler that write log to a file called
        // app.log. Each new message will be appended at the at of the log file.
        FileHandler fileHandler = null;        
        try {
            fileHandler = new FileHandler(folder_id_subid+"/"+filename+".txt",append_or_not);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } 
        logger.addHandler(fileHandler);
        logger.info(message);
//        fileHandler.flush();
//        fileHandler.close();
    }
    
    public void del_history_log(){
        String tanggal = get_tanggal_curdate();
        String curtime = get_tanggal_curdate_curtime_for_log(false);
        
        
           
        String nama_file_except = "log_idmreporter_"+tanggal+".txt";
        String[] pathnames;

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        String dir = System.getProperty("user.dir");
        File f = new File(dir);

        // Populates the array with names of files and directories
        pathnames = f.list();

        // For each pathname in the pathnames array
        for (String pathname : pathnames) {
            // Print the names of files and directories
            if(pathname.equals(nama_file_except)){
                
            }else{
                System.out.println(pathname);
                if(pathname.contains("log_idmreport")){
                    f.delete();
                }
            }
            
        }
    }
    
    public void WriteFile(String file,String content_before,String content_after,boolean append_content){
        try {
            
            if(append_content == true){
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content_before+"");
                bw.write(content_after+"");
                bw.flush();
                bw.close();
                fw.close();
            }else{
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content_after+"");
                bw.flush();
                bw.close();
                fw.close();
            }
          
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public String get_tanggal_curdate(){
        String res = "";
        try {
              int year = Calendar.getInstance().get(Calendar.YEAR);
              int month = Calendar.getInstance().get(Calendar.MONTH)+1;
              
              String bulan = "";
              if(month<10){
                  bulan = "0"+month;
              }else{
                  bulan = ""+month;
              }
              int d = Calendar.getInstance().get(Calendar.DATE);
              String tanggal = "";
              if(d<10){
                  tanggal = "0"+d;
              }else{
                  tanggal = ""+d;
              }
              String concat = year+""+bulan+""+tanggal;
              res = concat;                      
        } catch (Exception e) {
              res = "";  
        }
        
        return res;
    }
     
     public String get_tanggal_curdate_curtime(){
        String res = "";
        try {
              int year = Calendar.getInstance().get(Calendar.YEAR);
              int month = Calendar.getInstance().get(Calendar.MONTH)+1;
              String bulan = "";
              if(month<10){
                  bulan = "0"+month;
              }else{
                  bulan = ""+month;
              }
              int d = Calendar.getInstance().get(Calendar.DATE);
              String tanggal = "";
              if(d<10){
                  tanggal = "0"+d;
              }else{
                  tanggal = ""+d;
              }
              int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
              String jam = "";
              if(h<10){
                  jam = "0"+h;
              }else{
                  jam = ""+h;
              }
              int min = Calendar.getInstance().get(Calendar.MINUTE);
              String menit = "";
              if(min<10){
                  menit = "0"+min;
              }else{
                  menit = ""+min;
              }
              int sec = Calendar.getInstance().get(Calendar.SECOND);
              String detik = "";
              if(sec<10){
                  detik = "0"+sec;
              }else{
                  detik = ""+sec;
              }
              String concat = year+"-"+bulan+"-"+tanggal+" "+jam+":"+menit+":"+detik;
              res = concat;                      
        } catch (Exception e) {
              res = "";  
        }
        
        return res;
    }
    
     public String get_tanggal_curdate_curtime_for_log(boolean format_log){
         String res = "";
         try {
               int year = Calendar.getInstance().get(Calendar.YEAR);
               int month = Calendar.getInstance().get(Calendar.MONTH)+1;
               String bulan = "";
               if(month<10){
                   bulan = "0"+month;
               }else{
                   bulan = ""+month;
               }
               int d = Calendar.getInstance().get(Calendar.DATE);
               String tanggal = "";
               if(d<10){
                   tanggal = "0"+d;
               }else{
                   tanggal = ""+d;
               }
               int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
               String jam = "";
               if(h<10){
                   jam = "0"+h;
               }else{
                   jam = ""+h;
               }
               int min = Calendar.getInstance().get(Calendar.MINUTE);
               String menit = "";
               if(min<10){
                   menit = "0"+min;
               }else{
                   menit = ""+min;
               }
               int sec = Calendar.getInstance().get(Calendar.SECOND);
               String detik = "";
               if(sec<10){
                   detik = "0"+sec;
               }else{
                   detik = ""+sec;
               }
               
               String concat = "";
               if(format_log == true)
               {
                   concat = year+"-"+bulan+"-"+tanggal+" "+jam;
               }
               else
               {
                   concat = year+""+bulan+""+tanggal;
               }
                                 
               res = concat;                      
         } catch (Exception e) {
               res = "";  
         }
         
         return res;
     }
     
    
     public void WriteLog(String content_after,boolean append_content){
       
        String read = "";
        try {
            String tanggal = get_tanggal_curdate();
            String curtime = get_tanggal_curdate_curtime_for_log(true);
            int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            String jam = "";
            if(h<10){
                jam = "0"+h;
            }else{
                jam = ""+h;
            }
           
            String nama_file = "log_idmreporter_"+tanggal+"_"+jam+".txt";
            
            try{
                read = ReadFileLog(nama_file);
            }catch(Exception exc){
                read = "";
            }
            //System.out.println("read before : "+read);
            if(append_content == true){
                if(read.equals("")){
                    WriteFile(nama_file, "======================= Starting IDMReporter =======================\n",""+curtime+ " - "+content_after+"\n",append_content);
                }else{
                    WriteFile(nama_file, read.replaceAll("null", ""),""+curtime+ " - "+content_after+"\n",append_content);
                }
            }else{
                 WriteFile(nama_file,"",content_after+"\n",append_content);
            }
           
            
        } catch (Exception e) {
            read = "";
        }
    }
     
    
    public void PrintMessage(String FROM_THREAD,int counter,String msg_type,String topic,String Parser_TASK,String Parser_FROM,String Parser_TO,Date HariSekarang,Date HariSekarang_run){
        //=========================================================//
        Global_variable gv = new Global_variable();
        SimpleDateFormat sformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        System.out.println("\n");
        System.out.println("PUBLISH RECEIVE "+FROM_THREAD+" - "+counter+"");
        System.out.println("=========================================");
        //System.out.println("Msg\t:\t"+message_ADT_Decompress);
        System.out.println("MsgType\t:\t"+msg_type);
        System.out.println("Topic\t:\t" + topic);
        System.out.println("Task\t:\t" + Parser_TASK);
        System.out.println("From\t:\t" +Parser_FROM);
        System.out.println("To\t:\t" +Parser_TO);
        System.out.println("Date\t:\t"+sformat.format(HariSekarang));
        

    }
    
    private static final String ALGORITHM = "md5";
    private static final String DIGEST_STRING = "HG58YZ3CR9";
    private static final String CHARSET_UTF_8 = "utf-8";
    private static final String SECRET_KEY_ALGORITHM = "DESede";
    private static final String TRANSFORMATION_PADDING = "DESede/CBC/PKCS5Padding";

    /* Encryption Method */
    public byte[] encrypt(String message) throws Exception 
    { 
        final MessageDigest md = MessageDigest.getInstance(ALGORITHM); 
        final byte[] digestOfPassword = md.digest(DIGEST_STRING.getBytes(CHARSET_UTF_8)); 
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24); 
        for (int j = 0, k = 16; j < 8;) { 
                keyBytes[k++] = keyBytes[j++]; 
        } 
        System.out.println(new String(keyBytes));
        final SecretKey key = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM); 
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]); 
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION_PADDING); 
        cipher.init(Cipher.ENCRYPT_MODE, key, iv); 

        final byte[] plainTextBytes = message.getBytes(CHARSET_UTF_8);
        System.out.println(new String(plainTextBytes));
        final byte[] cipherText = cipher.doFinal(plainTextBytes); 

        //BASE64Encoder base64encoder = new BASE64Encoder();
        //return base64encoder.encode(cipherText);
        return cipherText; 
    } 



/* Decryption Method */
    public String decrypt(byte[] message) throws Exception { 
        final MessageDigest md = MessageDigest.getInstance(ALGORITHM); 
        final byte[] digestOfPassword = md.digest(DIGEST_STRING.getBytes(CHARSET_UTF_8)); 
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24); 
        for (int j = 0, k = 16; j < 8;) { 
                keyBytes[k++] = keyBytes[j++]; 
        } 
        System.out.println(new String(keyBytes));
        final SecretKey key = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM); 
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]); 
        final Cipher decipher = Cipher.getInstance(TRANSFORMATION_PADDING); 
        decipher.init(Cipher.DECRYPT_MODE, key, iv); 

        final byte[] plainText = decipher.doFinal(message); 

        return new String(plainText); 
    }

    
    public void PrintMessage2(String FROM_THREAD,int counter,String msg_type,String topic,String Parser_TASK,String Parser_FROM,String Parser_TO,Date HariSekarang,Date HariSekarang_run){
        //=========================================================//
        Global_variable gv = new Global_variable();
        SimpleDateFormat sformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        //System.out.println("\n");
        System.out.println(FROM_THREAD+" : "+counter+" >> "+Parser_FROM);
    }
    
    public void PrintMessage3(String FROM_THREAD,int counter,String msg_type,String topic,String Parser_TASK,String Parser_FROM,String Parser_TO,Date HariSekarang,Date HariSekarang_run){
        //=========================================================//
        Global_variable gv = new Global_variable();
        SimpleDateFormat sformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        //System.out.println("\n");
        System.out.println(counter+" >> "+Parser_FROM.split("_")[0]+"_"+Parser_FROM.split("_")[1]);
    }
      
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey) throws UnsupportedEncodingException 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    
    public MqttClient get_ConnectionMQtt() {
    	//Properties p = new Properties();
    	int res_keepalive = 60;
    	Boolean res_cleansession = false;   
    	int qos_message = 1;
	        
	        try {
	            //p.load(new FileInputStream("setting.ini"));
	            //String maxvmusepercent = p.getProperty("maxvmusepercent");
	            String  cleansession = getCleansession(); //p.getProperty("cleansession");
	            res_cleansession = Boolean.parseBoolean(cleansession);
	            System.out.println("res_cleansession : "+res_cleansession);
	            
	            String keepalive = getKeepalive();//p.getProperty("keepalive");
	            res_keepalive = Integer.parseInt(keepalive);
	            
	            String reconnect = getReconnect();//p.getProperty("reconnect");
	            Boolean res_reconnect = Boolean.parseBoolean(reconnect);
	            System.out.println("res_reconnect : "+res_reconnect);
	            
	            String will_retained =  getWill_retained();//p.getProperty("will_retained");
	            Boolean res_will_retained = Boolean.parseBoolean(will_retained);
	            System.out.println("will_retained\t:\t"+res_will_retained);
	            
	            String ip_mongo_db = getIp_mongodb();//p.getProperty("ip_mongodb");
	            System.out.println("ip_mongo_db : "+ip_mongo_db);
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	     
	           
	         
	            SimpleDateFormat sformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	            Date HariSekarang = new Date();
	            JSONParser parser = new JSONParser();
	            //-- sesi koneksi db --//
	            String file_attribute           = ReadFile("attribute");
	            String new_attribute            = "";//sqlcon.DecodeString(file_attribute);
	            //System.out.println("new_attribute : "+new_attribute);
	            String sp_new_attribute[]       = new_attribute.split("~");
	            //String broker_primary[]         = sp_new_attribute[0].split(":");
	            
	            String res_broker_primary       = getIp_broker()+":"+getPort_broker();//broker_primary[0]+":"+broker_primary[1];
	          
	            String res_username_primary     = "";
	            if(getUsername_broker().equals("")){
	            	//broker_primary[2];
	            	res_username_primary = "";
	            }else {
	            	res_username_primary = getUsername_broker();
	            }
	            String res_password_primary     = "";
	            if(getPassword_broker().equals("")){
	            	//broker_primary[2];
	            	res_password_primary = "";
	            }else {
	            	res_password_primary = getPassword_broker();
	            }
	            //getPassword_broker();//broker_primary[3];

	            /* ssl://mqtt.cumulocity.com:8883 for a secure connection */
	            //-------------------------------- TRANS CONNECTION ----------------------//
	            try{
	                final String serverUrl   = "tcp://"+res_broker_primary;
	                
	                String clientId = UUID.randomUUID().toString();
	                MemoryPersistence persistence = new MemoryPersistence();
	                client_transreport = new MqttClient(serverUrl, clientId,persistence);
	                MqttConnectOptions options = new MqttConnectOptions();
	                options.setCleanSession(res_cleansession);
	             
	                options.setKeepAliveInterval(res_keepalive);
	                options.setAutomaticReconnect(true);
	                
	                if(res_username_primary.equals("null")||res_password_primary.equals("null")){
	                    
	                }else if(res_username_primary.equals("")||res_password_primary.equals("")) {
	                
	                }else{
	                    options.setUserName(res_username_primary);
	                    options.setPassword(res_password_primary.toCharArray());
	                }
	               
	                client_transreport.connect(options);
	                //client_transreport_login.connect(options);
	                System.out.println("Konek ke Broker : "+res_broker_primary);
	            }catch(Exception exc){
	               exc.printStackTrace();
	            }
	            	
    	return client_transreport;
    	
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
    
    public void del_transreport_30_hari_kebelakang() {
    	try {
    		String get_date_30Hari_lalu = GetTransReport("SELECT CONCAT('transreport',REPLACE(DATE_SUB(CURDATE(),INTERVAL 38 DAY),'-','')) AS HASIL", 1, true);
    		ChangeData("DROP TABLE IF EXISTS "+get_date_30Hari_lalu);
    		WriteLog("SUKSES MAINTAIN TABLE "+get_date_30Hari_lalu, true);
    	}catch(Exception exc) {WriteLog("ERROR MAINTAIN TABLE "+exc.toString(), true);}
    }
    
    public String get_time_diff(String time1,String time2) {
		String res = "";
		try {
			// Dates to be parsed
	     
	  
	        // Creating a SimpleDateFormat object
	        // to parse time in the format HH:MM:SS
	        SimpleDateFormat simpleDateFormat
	            = new SimpleDateFormat("HH:mm:ss");
	  
	        // Parsing the Time Period
	        Date date1 = simpleDateFormat.parse(time1);
	        Date date2 = simpleDateFormat.parse(time2);
	  
	        // Calculating the difference in milliseconds
	        long differenceInMilliSeconds
	            = Math.abs(date2.getTime() - date1.getTime());
	  
	        // Calculating the difference in Hours
	        long differenceInHours
	            = (differenceInMilliSeconds / (60 * 60 * 1000))
	              % 24;
	  
	        // Calculating the difference in Minutes
	        long differenceInMinutes
	            = (differenceInMilliSeconds / (60 * 1000)) % 60;
	  
	        // Calculating the difference in Seconds
	        long differenceInSeconds
	            = (differenceInMilliSeconds / 1000) % 60;
	  
	        String time_diff = "" + differenceInHours + ":"
		            + differenceInMinutes + ":"
		            + differenceInSeconds + "";
	        // Printing the answer
	        //System.out.println(time_diff);
	        
	        res = time_diff;
	    
		}catch(Exception exc) {
			
		}
		
		return res;
	}
    
    public void get_MonitoringResources(){
        try {
            SimpleDateFormat sformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Locale lokal = null;
            String pola = "dd-MM-yyyy HH:mm:ss";
            DecimalFormat format = new DecimalFormat("##.##");
            long total, free, used;
            int mb = 1024*1024;
            Runtime runtime = Runtime.getRuntime();
            total = runtime.totalMemory();
            free = runtime.freeMemory();
            used = total - free;
            double used_percent_memory = (double)used/(double)total*100;
            double free_percent_memory = (double)free/(double)total*100;
            
            System.out.println("VMMem\t:\t" + total / mb + " MB "+"Used\t: " + used / mb + " MB "+format.format(used_percent_memory)+ " %"+" Free\t: " + free / mb + " MB "+format.format(free_percent_memory)+ " %");
           
            int batas_memory = Integer.parseInt(getMaxVM());    
            if(used_percent_memory > batas_memory){
                System.out.println("Exit IDMReporter Memory Usage > "+batas_memory+" % : Memory Saat ini : "+used_percent_memory+" %");
                WriteLog("Exit IDMReporterService Memory Usage > "+batas_memory+" % : Memory Saat ini : "+used_percent_memory+" %", true);
                System.exit(0);
              
            }else{
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
    	
    	try {
    		 String res_broker_primary = "172.24.16.131";
    		 final String serverUrl   = "tcp://"+res_broker_primary;
    		 String res_username_primary = "idmbroker";
    		 String res_password_primary = "idm@broker2020";
             
             String clientId = UUID.randomUUID().toString();
             MemoryPersistence persistence = new MemoryPersistence();
             MqttClient client_transreport = new MqttClient(serverUrl, clientId,persistence);
             MqttConnectOptions options = new MqttConnectOptions();
             options.setCleanSession(true);
          
             options.setKeepAliveInterval(70);
             options.setAutomaticReconnect(true);
             options.setUserName(res_username_primary);
             options.setPassword(res_password_primary.toCharArray());
             
            
             client_transreport.connect(options);
             //client_transreport_login.connect(options);
             System.out.println("Konek ke Broker : "+client_transreport.isConnected());
             client_transreport.disconnect();
             System.out.println("Disconnect");
    	}catch(Exception exc) {exc.printStackTrace();}
    	
    }
    
}
