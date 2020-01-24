
public class Lab2Tester 
{
	public static Thread print;
	public static Thread wait;
	public static void main(String args[]) throws InterruptedException
	{
		print = new Thread(new Printer());
		wait = new Thread(new Waiter());
		print.start();
		wait.start();
		
		print.join();
		wait.join();
		System.out.println("Both threads have completed");
	}

}
