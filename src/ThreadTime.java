
public class ThreadTime extends Thread{
	IDMReportService idm;
    
    public ThreadTime(int num){
    	idm = new IDMReportService();
    }
    
    public void run(){
        for(int l = 0;l<1;l++){
           try{
        	   idm.Run_time();
           }catch(Exception exc){
               
           }
           
        }
    } 
}
