import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.DecimalFormat;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ch.ethz.ssh2.StreamGobbler;

public class IDMReportService {
	
	MqttClient client_transreport_login;
	MqttClient client_transreport;
	Global_function gf = new Global_function();
	Global_variable gv = new Global_variable();
	Interface_ga inter_login;
	Connection con;
	SQLConnection sqlcon = new SQLConnection();
	int counter = 1;
	int count_size_message = 0;

	public IDMReportService() {

	}

	String Parser_TASK, Parser_ID, Parser_SOURCE, Parser_COMMAND, Parser_OTP, Parser_TANGGAL_JAM, Parser_VERSI,
			Parser_HASIL, Parser_FROM, Parser_TO, Parser_SN_HDD, Parser_IP_ADDRESS, Parser_STATION, Parser_CABANG,
			Parser_NAMA_FILE, Parser_CHAT_MESSAGE, Parser_REMOTE_PATH, Parser_LOCAL_PATH, Parser_SUB_ID;

	public void UnpackJSON(String json_message) {

		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(json_message);
		} catch (org.json.simple.parser.ParseException ex) {
			System.out.println("message json : " + json_message);
			System.out.println("message error : " + ex.getMessage());
			// ex.printStackTrace();
			// Logger.getLogger(IDMReport.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			Parser_TASK = obj.get("TASK").toString();
		} catch (Exception ex) {
			Parser_TASK = "";
		}
		try {
			Parser_ID = obj.get("ID").toString();
		} catch (Exception exc) {
			Parser_ID = "";
		}
		try {
			Parser_SOURCE = obj.get("SOURCE").toString();
		} catch (Exception exc) {
			Parser_SOURCE = "";
		}
		try {
			Parser_COMMAND = obj.get("COMMAND").toString();
		} catch (Exception exc) {
			Parser_COMMAND = "";
		}
		try {
			Parser_OTP = obj.get("OTP").toString();
		} catch (Exception exc) {
			Parser_OTP = "";
		}

		try {
			Parser_TANGGAL_JAM = obj.get("TANGGAL_JAM").toString();
		} catch (Exception exc) {
			Parser_TANGGAL_JAM = "";
		}
		try {
			Parser_VERSI = obj.get("RESULT").toString().split("_")[7];
		} catch (Exception exc) {
			try {
				Parser_VERSI = obj.get("VERSI").toString();
			} catch (Exception exc1) {
				Parser_VERSI = "";
			}

		}

		try {
			Parser_HASIL = obj.get("HASIL").toString();
			Parser_FROM = obj.get("FROM").toString();
			Parser_TO = obj.get("TO").toString();

		} catch (Exception exc) {
			Parser_HASIL = "";
			Parser_FROM = "";
			Parser_TO = "";
		}

		try {
			Parser_SN_HDD = obj.get("SN_HDD").toString();
		} catch (Exception exc) {
			try {
				Parser_SN_HDD = obj.get("SN_HDD").toString();
			} catch (Exception exc1) {
				Parser_SN_HDD = "";
			}

		}
		try {
			Parser_IP_ADDRESS = obj.get("IP_ADDRESS").toString();
		} catch (Exception exc) {
			try {
				Parser_IP_ADDRESS = obj.get("IP_ADDRESS").toString();
			} catch (Exception exc1) {
				Parser_IP_ADDRESS = "";
			}

		}

		try {
			Parser_STATION = obj.get("STATION").toString();
		} catch (Exception exc) {
			try {
				Parser_STATION = obj.get("STATION").toString();
			} catch (Exception exc1) {
				Parser_STATION = "";
			}

		}

		try {
			Parser_CABANG = obj.get("CABANG").toString();
		} catch (Exception exc) {
			try {
				Parser_CABANG = obj.get("CABANG").toString();
			} catch (Exception exc1) {
				Parser_CABANG = "";
			}
		}

		try {
			Parser_NAMA_FILE = obj.get("NAMA_FILE").toString();
		} catch (Exception exc) {
			Parser_NAMA_FILE = "";
		}
		try {
			Parser_CHAT_MESSAGE = obj.get("CHAT_MESSAGE").toString();
		} catch (Exception exc) {
			Parser_CHAT_MESSAGE = "";
		}
		try {
			Parser_REMOTE_PATH = obj.get("REMOTE_PATH").toString();
		} catch (Exception exc) {
			Parser_REMOTE_PATH = "";
		}
		try {
			Parser_LOCAL_PATH = obj.get("LOCAL_PATH").toString();
		} catch (Exception exc) {
			Parser_LOCAL_PATH = "";
		}
		try {
			Parser_SUB_ID = obj.get("SUB_ID").toString();
		} catch (Exception exc) {
			Parser_SUB_ID = "";
		}

	}
	
	public String get_from(String from) {
		String res = "";
		try {
			if(from.contains("_")) {
				res = from.split("_")[1];
				try {
					String get_nik = gf.GetTransReport("SELECT NAMA FROM idm_org_structure WHERE NIK = '"+res+"';", 1, true);
					if(get_nik != "") {
						res = "1 - "+get_nik;
					}else {
						res = "2 - "+from;
					}
					
				}catch(Exception exc) {
					try {
						String get_toko = gf.GetTransReport("SELECT CONCAT(TOKO,'-',NAMA) AS CONTENT FROM tokomain WHERE IP = '"+res+"';", 1, true);
						res = "3 - "+get_toko;
					}catch(Exception exc1) {
						res = "4 - "+from;
					}
				}
			}else {
				try {
					String get_toko = gf.GetTransReport("SELECT CONCAT(TOKO,'-',NAMA) AS CONTENT FROM tokomain WHERE IP = '"+from+"';", 1, true);
					if(get_toko != "") {
						res = "5 - "+get_toko;
					}else {
						res = "5 - "+from;
					}
					
				}catch(Exception exc1) {
					res = "6 - "+from;
				}
			}
		}catch(Exception exc) {
			exc.printStackTrace();
			res = "7 - "+from;
		}
		return res;
	}
	
	ch.ethz.ssh2.Connection connection,connection_REG;
	 
	private void executeCommandWithReturnContinue(String command) throws Exception{
        //buka sesi
		ch.ethz.ssh2.Session session = connection.openSession();
        //eksekusi command
        session.execCommand(command);
        //StringBuilder sb = new StringBuilder();
        //mendapatkan setiap input stream dari session dengan method getStdout
        InputStream is = new StreamGobbler(session.getStdout());
        BufferedReader buff = new BufferedReader(new InputStreamReader(is));
        String line = buff.readLine();
        while(line != null){
            //sb.append(line + "\n");
            System.out.println(line+"\n");
            line = buff.readLine();
        }
        //Close the session
        session.close();
         //return result
        //return sb.toString();
    }
	
	public void ConnectSSH(String host,String username,String password){
        try {
            //String host = "172.24.52.3";
            int port = 22;
            
            connection = new ch.ethz.ssh2.Connection(host, port);
            connection.connect();
            //hasil dari boolean
            boolean result = connection.authenticateWithPassword(username, password);
            String content = "Connect SSH Server "+result+" : "+host+" Port : "+port;
            //WriteLog(content);
            if(result){
                
            }else{
                //WriteLog("Persiapan exit program");
                System.exit(0);
            }
            System.out.println("Status SSH : "+content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private void Disconnect(){
	    connection.close();
	    connection.isAuthenticationComplete();
	}
	
	public void executeCommandNotreturn(String command) {
		try {
			//buka sesi
			ch.ethz.ssh2.Session session = connection.openSession();
	        //eksekusi command
	        session.execCommand(command);
	        //Close the session
	        session.close();
	        gf.WriteLog("Restart "+command+" : SUKSES", true);
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public boolean RestartIDMReporter_HO(){
        boolean res = false;
        try {
            String ip = "172.24.52.3";
            String username = "root";
            String password = "edpho@idm";
            ConnectSSH(ip,username,password);
            executeCommandNotreturn("systemctl restart OTPService1");
            executeCommandNotreturn("systemctl restart BroadcastReportService");
//            executeCommandNotreturn("systemctl restart BroadcastReportHeaderDetail");
//            executeCommandNotreturn("systemctl restart RequestTokoService");
            executeCommandNotreturn("systemctl restart ChatService");
            executeCommandNotreturn("systemctl restart IRISClientService");
            executeCommandNotreturn("systemctl restart SQLBrowserService");
            executeCommandNotreturn("systemctl restart FileTransferService");
//            executeCommandNotreturn("systemctl restart InitialCabangService");
            executeCommandNotreturn("systemctl restart HardwareInfoRefresh");
            executeCommandNotreturn("systemctl restart HardwareInfoService");
            executeCommandNotreturn("systemctl restart CaptureScreen");
            executeCommandNotreturn("systemctl restart ShortcutService");
            executeCommandNotreturn("systemctl restart AktivasiWindowsService");
            executeCommandNotreturn("systemctl restart NPPService");
//            executeCommandNotreturn("systemctl restart Broadcast_Report_Pending");
//            executeCommandNotreturn("systemctl restart LoginService");
            //executeCommandNotreturn("sh restart_bc_dc.sh");
            //executeCommandWithReturnContinue("sh IDMReporter.sh");
            executeCommandWithReturnContinue("sh restart_bc_dc.sh");
            executeCommandWithReturnContinue("sh restart_bc_sql.sh");
            gf.WriteLog(gf.get_tanggal_curdate_curtime()+" - Restart Service HO", true);//WriteFile("log_restart_HO.txt", "",gf.get_tanggal_curdate_curtime()+" - Restart Service");
            Disconnect();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        
        return res;
    }
	
	public boolean RestartIDMReporter_by_service(String topic,String command,String nama_service){
        boolean res = false;
        try {
        	
        	String id = gf.get_id(true);
        	String source = "IDMTester";
        	//String command = "systemctl restart LoginService";
        	String otp = "";
        	String tanggal_jam = gf.get_tanggal_curdate_curtime();
        	String versi = "1.0.1";
        	String hasil = "";
        	String from = "IDMTester";
        	String to = "IDMReporter";
        	String sn_hdd = "";
        	String ip_address = "192.168.131.104";
        	String station = "";
        	String cabang = "HO";
        	String file = "";
        	String nama_file = "";
        	String chat_message = "";
        	String remote_path = "";
        	String local_path = "";
        	String sub_id = gf.get_id(false);
        	
			String res_message = gf.CreateMessage("RESTART_SERVICE",id,source,command,otp,tanggal_jam,versi,hasil,from,to,sn_hdd,ip_address,station,cabang,file,nama_file,chat_message,remote_path,local_path,sub_id);
            System.out.println("MESSAGE NOTIF : "+res_message);
			byte[] convert_message = res_message.getBytes("US-ASCII");
            byte[] bytemessage = gf.compress(convert_message);
            String topic_dest = topic;
        	gf.PublishMessageNotDocumenter(topic_dest,bytemessage,0,res_message,1);
        	gf.WriteLog("Restart "+nama_service+" : SUKSES", true);
            //String ip = "192.168.131.223";
            //String username = "edpreg4";
            //String password = "edpho@idm";
            //ConnectSSH(ip,username,password);
            //executeCommandWithReturnContinue("sh restart_bc_sql.sh");
            //executeCommandWithReturnContinue("systemctl restart LoginService");
            //gf.WriteLog(gf.get_tanggal_curdate_curtime()+" - Restart Service HO", true);
            
//            executeCommandNotreturn("systemctl restart OTPService1");
//            executeCommandNotreturn("systemctl restart BroadcastReport");
//            executeCommandNotreturn("systemctl restart BroadcastReportHeaderDetail");
//            executeCommandNotreturn("systemctl restart RequestToko");
//            executeCommandNotreturn("systemctl restart Chat");
//            executeCommandNotreturn("systemctl restart IRISClient");
//            executeCommandNotreturn("systemctl restart SQLBrowser");
//            executeCommandNotreturn("systemctl restart FileTransfer");
//            executeCommandNotreturn("systemctl restart InitialCabangService");
//            executeCommandNotreturn("systemctl restart HardwareInfoRefresh");
//            executeCommandNotreturn("systemctl restart HardwareInfoService");
//            executeCommandNotreturn("systemctl restart CaptureScreen");
//            executeCommandNotreturn("systemctl restart ShortcutService");
//            executeCommandNotreturn("systemctl restart AktivasiWindowsService");
//            executeCommandNotreturn("systemctl restart Broadcast_Report_Pending");
//            executeCommandNotreturn("systemctl restart LoginService");
//            executeCommandNotreturn("sh restart_bc_dc.sh");
            //executeCommandWithReturnContinue("sh IDMReporter.sh");
            //executeCommandWithReturnContinue("sh restart_bc_dc.sh");
            //executeCommandWithReturnContinue("sh restart_bc_dc.sh");
            //WriteFile("log_restart_HO.txt", "",get_tanggal_curdate_curtime()+" - Restart Service");
            //Disconnect();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        
        return res;
    }
	
	 public boolean TaskkillCMD(){
	        boolean res = false;
	        try {
	            //Runtime r = Runtime.getRuntime();
	            //Process proc = r.exec("taskkill /F /IM java.exe");
	            //WriteFile("log_restart_WIN.txt", "","Restart Service");
	            res = true;
	        } catch (Exception e) {
	            //e.printStackTrace();
	            res = false;
	        }
	        
	        return res;
	    }

	
	 public double roundAvoid(double value, int places) {
		    double scale = Math.pow(10, places);
		    return Math.round(value * scale) / scale;
	}
	 
	public void Run() {
		System.out.println("=================================          START         ==================================");
		try {
			client_transreport = gf.get_ConnectionMQtt();
			// ---------------------------- COMMAND -----------------------//
			int qos_message_command = 0;
			
			
			String rtopic_command = gf.getTopic();
			client_transreport.subscribe(rtopic_command, qos_message_command, new IMqttMessageListener() {
				@Override
				public void messageArrived(final String topic, final MqttMessage message) throws Exception {
					// ----------------------------- FILTER TOPIC NOT CONTAINS
					// -------------------------------//
					byte[] message_byte = message.getPayload();
					count_size_message = count_size_message + message_byte.length;
					int data  = count_size_message;
			    	double hitung = Double.parseDouble(""+data) / 1024 / 1024;
					DecimalFormat df = new DecimalFormat("#.###");
					if(hitung > Double.parseDouble(gf.getMaxMessageIncomingMB())) {
						gf.WriteLog("Restart IDMReport, incoming message melebihi batas. Max Incoming Message : "+Double.parseDouble(gf.getMaxMessageIncomingMB())+", total message yang di proses sampai saat ini : "+hitung, true);
						System.exit(0);
					}else {
					
						if (topic.contains("BYLINE")) {
							//String payload = new String(message.getPayload());
							//System.err.println("BYLINE > " + payload);
						} else {
							String payload = new String(message.getPayload());
							 
							String message_ADT_Decompress = "";
							try {
								message_ADT_Decompress = gf.ADTDecompress(message.getPayload());
								 
							} catch (Exception exc) {
								message_ADT_Decompress = payload;
								 
							}

							UnpackJSON(message_ADT_Decompress);
							if(Parser_TASK.equals("ATTRIBUTE") || Parser_TASK.equals("CEKAKTIF") || topic.equals("LOGIRISADMIN/") || Parser_TASK.equals("BC_POWERSHELL_COMMAND") || topic.contains("monitoring") || topic.contains("report/")  ) {
								
							}else {
								//System.out.println(message_ADT_Decompress);
								System.out.println("Index\t:\t"+counter);
								System.out.println("Topic\t:\t"+topic.trim());
								System.out.println("Task\t:\t"+Parser_TASK.trim());
								System.out.println("Cabang\t:\t"+Parser_CABANG.trim());
								System.out.println("From\t:\t"+Parser_FROM.trim());
								System.out.println("IP\t:\t"+Parser_IP_ADDRESS.trim());
								System.out.println("Source\t:\t"+Parser_SOURCE);
								System.out.println("Inc. B\t:\t"+data+" bytes");
								System.out.println("Inc. MB\t:\t"+df.format(hitung)+" MB");
								
								String time_message = Parser_TANGGAL_JAM.replaceAll("_", " ").split(" ")[1];
								JSONObject obj = new JSONObject();
								String cabang_from = "";
								if(Parser_SOURCE.equals("IDMCommander")) {
									cabang_from = Parser_FROM.substring(0, 4).trim();
								}else if(cabang_from.equals("IDMCommandListeners")) {
									cabang_from = Parser_CABANG;
								}else {
									
								}
								
								
								System.out.println("Jam\t:\t"+Parser_TANGGAL_JAM.replaceAll("_", " "));
								obj.put("TIME",time_message);
						        obj.put("MESSAGE",message_ADT_Decompress);
						        obj.put("CABANG",cabang_from);
						        
						        String res = obj.toJSONString();
								gf.WriteFile("timemessage.txt", "", res, false);
							 
								
								gf.get_MonitoringResources();
								System.out.println("===============================================================================");
								counter++;
							}
							
						}
						
					}
					
					
				}
			});
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	
	public void Run_time() {
		try {
			int menit_batas = Integer.parseInt(gf.getBatasMenit());
			String ip = "172.24.52.3";
	        String username = "root";
	        String password = "edpho@idm";
	        ConnectSSH(ip,username,password);
	        
	        JSONParser parser = new JSONParser();    
			while(true) {
				String tanggal_jam = gf.get_tanggal_curdate_curtime().split(" ")[1];
				String tanggal_message = gf.ReadFile("timemessage.txt");
				int jam_kini_restart1 = Integer.parseInt(tanggal_jam.split(":")[0]);
				System.err.println("jam_kini_restart : "+jam_kini_restart1);
				
				//-- parsing data json timemessage --//
				
				JSONObject obj = null;
				try {
					obj = (JSONObject) parser.parse(tanggal_message);
				} catch (org.json.simple.parser.ParseException ex) {
					System.out.println("message json : " + tanggal_message);
					System.out.println("message error : " + ex.getMessage());
					// ex.printStackTrace();
					// Logger.getLogger(IDMReport.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				String res_time = "";
				try {
					res_time = obj.get("TIME").toString();
				} catch (Exception ex) {
					res_time = "";
				}
				String res_message = "";
				try {
					res_message = obj.get("MESSAGE").toString();
				} catch (Exception exc) {
					res_message = "";
				}
				String res_cabang = "";
				try {
					res_cabang = obj.get("CABANG").toString();
				} catch (Exception exc) {
					res_cabang = "";
				}
				
				
				String time_diff = "";
				String type_diff = "";
				//System.err.println(tanggal_message);
				if(res_cabang.equals("") || res_cabang.equals("G146") || res_cabang.equals("G174") || res_cabang.equals("G158") || res_cabang.equals("G097") || res_cabang.equals("G149") || res_cabang.equals("G177") || res_cabang.equals("G030") || res_cabang.equals("G034") || res_cabang.equals("G224") || res_cabang.equals("G146") || res_cabang.equals("G092") || res_cabang.equals("G050")|| res_cabang.equals("G224") || res_cabang.equals("G232") || res_cabang.equals("G234") || res_cabang.equals("G236")) {
					time_diff = gf.get_time_diff(tanggal_jam,res_time);
					gf.WriteLog("Diff BWIB : "+time_diff+" Cabang : "+res_cabang, true);
					type_diff = "Diff BWIB : "+tanggal_jam+" VS "+gf.get_tanggal_curdate_curtime().split(" ")[1];
				}else {
					time_diff = gf.get_time_diff(tanggal_jam,res_time);
					gf.WriteLog("Diff RWIB : "+time_diff+" Cabang : "+res_cabang, true);
					type_diff = "Diff RWIB : "+tanggal_jam+" VS "+res_time;
				}
				
				
				//time_diff = gf.get_time_diff(tanggal_jam,res_time);
				//gf.WriteLog("Diff : "+time_diff, true);
				System.err.println("Timer\t:\t"+gf.get_tanggal_curdate_curtime());
				System.err.println(type_diff+"\t:\t"+time_diff);
				/*
				String menit = time_diff.split(":")[2];
				if(Integer.parseInt(menit) > Integer.parseInt(gf.getBatasMenit())) {
					System.exit(0);
				}else {
					
				}
				*/
				 
				 	
				String getIsRestartService = gf.getIsRestartService();
				
			 
				if(getIsRestartService.equals("Ya")) {
					System.err.println("IS Restart : "+getIsRestartService);
					
					int res_int_jam_message =  Integer.parseInt(res_time.replace(":", "").replace(".", ""));
					int res_int_jam_kini = Integer.parseInt(tanggal_jam.replace(":", ""));
					
					if( (Integer.parseInt(time_diff.split(":")[1]) < 56) && (Integer.parseInt(time_diff.split(":")[1]) > menit_batas) && (res_int_jam_message >  res_int_jam_kini) ) {
						gf.WriteLog("Mulai Restart Service : "+Integer.parseInt(time_diff.split(":")[1])+" > "+menit_batas+" - res_int_jam_message : "+res_int_jam_message+" VS res_int_jam_kini : "+res_int_jam_kini, true);
						gf.WriteLog("Perbandingan Waktu Kini : "+tanggal_jam+" VS Waktu Message : "+res_time, true);
						gf.WriteLog("Last Message : "+res_message, true);
						if(gf.getCabang().equals("")) {
							gf.WriteLog("Flag Cabang kosong, tidak melakukan aktivitas restart", true);
							System.exit(0);
						}else {
							String get_list_single_service_523[] = gf.getListSingleService523().split(",");
							String get_list_single_service_223[] = gf.getListSingleService223().split(",");
							String get_list_multiple_service_523[] = gf.getListMultipleService523().split(",");
							String get_list_multiple_service_223[] = gf.getListMultipleService223().split(",");
							
							String restart_service_terakhir = gf.ReadFile("flag_restart_validasi.txt");
							int jam_kini_restart = Integer.parseInt(tanggal_jam.split(":")[0]);
							
							if(jam_kini_restart == 2 || jam_kini_restart == 4 || jam_kini_restart == 6  || jam_kini_restart == 9 || jam_kini_restart == 12 || jam_kini_restart == 15 ||  jam_kini_restart == 19 || jam_kini_restart == 23) {
								
								gf.WriteLog("Proses cek jam restart , Jam Restart Terakhir : "+restart_service_terakhir+" VS  Jam Kini : "+jam_kini_restart, true);
								if(restart_service_terakhir.equals(""+jam_kini_restart)) {
									gf.WriteLog("Tidak Perlu Melakukan Restart Service jam terakhir restart masih sama dengan jam saat ini : "+restart_service_terakhir+" - "+jam_kini_restart, true);
								}else{
									//-- publish ke BE Restart service BC Command --//
									String command_validasi_withOTP = "systemctl restart ValidasiBCCommandWithOTP";
									RestartIDMReporter_by_service("LISTENER_BACKEND_223/",command_validasi_withOTP,"ValidasiBCCommandWithOTP");
									Thread.sleep(1000);
									String command_Approval_bc_command = "systemctl restart Approval_BC_Command";
									RestartIDMReporter_by_service("LISTENER_BACKEND_223/",command_Approval_bc_command,"Approval_BC_Command");
									Thread.sleep(1000);
									 
									String command_ValidasiBCCommand = "sh /home/idmcmd/Restart_Validasi_BC_Command.sh";
									RestartIDMReporter_by_service("LISTENER_BACKEND_223/",command_ValidasiBCCommand,"Restart_Validasi_BC_Command");
									Thread.sleep(30000);
									
									String command_IRISClientService = "systemctl restart IRISClientService";
									executeCommandNotreturn(command_IRISClientService);
									//RestartIDMReporter_by_service("LISTENER_BACKEND_523/",command_IRISClientService,"IRISClientService");
									Thread.sleep(3000);
									
									String command_HardwareInfoService = "systemctl restart HardwareInfoService";
									executeCommandNotreturn(command_HardwareInfoService);
									//RestartIDMReporter_by_service("LISTENER_BACKEND_523/",command_IRISClientService,"IRISClientService");
									Thread.sleep(3000);
									
									String command_AktivasiWindowsService = "systemctl restart AktivasiWindowsService";
									executeCommandNotreturn(command_AktivasiWindowsService);
									//RestartIDMReporter_by_service("LISTENER_BACKEND_523/",command_IRISClientService,"IRISClientService");
									Thread.sleep(3000);
									
									 
									
									String command_BCCommandDC = "sh /root/restart_bc_dc.sh";
									//executeCommandNotreturn(command_BCCommandDC);
									RestartIDMReporter_by_service("LISTENER_BACKEND_523/",command_BCCommandDC,"BCCommandDC");
									Thread.sleep(1000);
									
									String command_BC_SQL = "sh /root/restart_bc_sql.sh";
									RestartIDMReporter_by_service("LISTENER_BACKEND_523/",command_BC_SQL,"BC_SQL");
									//executeCommandNotreturn(command_BC_SQL);
									Thread.sleep(1000);
									
									 
									
								}
								gf.WriteFile("flag_restart_validasi.txt", "", ""+jam_kini_restart, false);
								
							}else {
								 
								
							}
							
							if(restart_service_terakhir.equals(""+jam_kini_restart)) {
								gf.WriteLog("Tidak Perlu Melakukan Restart Service jam terakhir restart masih sama dengan jam saat ini : "+restart_service_terakhir+" - "+jam_kini_restart, true);
							}else{
								//-- publish ke BE Restart service 523 dan 223 --//
								for(int a = 0;a<get_list_single_service_523.length;a++) {
									String nama_service = get_list_single_service_523[a];
									String command_service = "systemctl restart "+nama_service;
									RestartIDMReporter_by_service("LISTENER_BACKEND_523/",command_service,nama_service);
									//executeCommandNotreturn(command_service);
									Thread.sleep(3000);
								}
								
								for(int a = 0;a<get_list_multiple_service_523.length;a++) {
									String nama_service = get_list_multiple_service_523[a];
									String command_service = "sh "+nama_service;
									//executeCommandNotreturn(command_service);
									RestartIDMReporter_by_service("LISTENER_BACKEND_523/",command_service,nama_service);
									Thread.sleep(20000);
								}
								
								for(int a = 0;a<get_list_single_service_223.length;a++) {
									String nama_service = get_list_single_service_223[a];
									String command_service = "systemctl restart "+nama_service;
									RestartIDMReporter_by_service("LISTENER_BACKEND_223/",command_service,nama_service);
									Thread.sleep(3000);
								}
								
								for(int a = 0;a<get_list_multiple_service_223.length;a++) {
									String nama_service = get_list_multiple_service_223[a];
									//System.err.println("jam_kini_restart : "+jam_kini_restart);
									if (jam_kini_restart < 10 && nama_service == "/home/idmcmd/restart_bc_init.sh") {
										gf.WriteLog("Tidak Perlu Melakukan Restart Service 223 Jam kini : "+jam_kini_restart, true);
									}else {
										String command_service = "sh "+nama_service;
										//executeCommandNotreturn(command_service);
										RestartIDMReporter_by_service("LISTENER_BACKEND_223/",command_service,nama_service);
										Thread.sleep(20000);
									}
								}
								
								//-- end of publish BE restart service 523 dan 223 --//
								
								
								gf.del_transreport_30_hari_kebelakang();
								gf.WriteLog("Restart\t:\tTidak ada aktivitas lebih dari "+menit_batas+" menit : Tanggal Jam kini : "+tanggal_jam+", Time Message : "+tanggal_message, true);
								//TaskkillCMD();
								System.exit(0);
								gf.WriteFile("flag_restart_validasi.txt", "", ""+jam_kini_restart, false);
							}
						}
							
							
					}
				}else {
					System.out.println("Tidak melakukan restart service, flag is restart service : "+gf.getIsRestartService());
				}
				
				Boolean thread_mqtt_Alive = Main.t1.isAlive();
				System.err.println("MQTT Alive\t:\t"+thread_mqtt_Alive);
				/*
				if(thread_mqtt_Alive == false) {
					System.exit(0);
				}
				*/
				
				Boolean thread_timer_Alive = Main.t2.isAlive();
				System.err.println("Timer Alive\t:\t"+thread_timer_Alive);
				Thread.sleep(1000);
			}
		}catch(Exception exc) {
			exc.printStackTrace();
			gf.WriteLog("Error Thread Run_time\t:\t"+exc, true);
			System.exit(0);
			gf.WriteLog("Restart\t:\tError Sistem", true);
		}
		Disconnect();
	}
	

	
}
