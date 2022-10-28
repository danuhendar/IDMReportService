
public class Main {
	static ThreadMain t1;
	static ThreadTime t2;
	public static void main(String args[]) {
		try {
			t1 = new ThreadMain(1);
			t1.start();
			System.out.println("t1 run");
			t2 = new ThreadTime(1);
			t2.start();
			System.out.println("t2 run");
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
