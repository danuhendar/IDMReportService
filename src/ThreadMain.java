
public class ThreadMain extends Thread {
	IDMReportService idm;
     
    public ThreadMain(int num){
    	idm = new IDMReportService();
    }
    
    public void run(){
        for(int l = 0;l<1;l++){
           try{
        	   idm.Run();
           }catch(Exception exc){
               
           }
           
        }
    } 
}
