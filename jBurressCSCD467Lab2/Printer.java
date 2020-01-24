
public class Printer implements Runnable
{

	// public static int i;
	 public static boolean isHalfWay = false;
	
	public void run() 
	{
		for(int i = 1; i <= 50; i++)
		{
			System.out.println("Message i=" + i + " from Thread Printer");
			
			if(i == 25)
				Lab2Tester.wait.interrupt();
					
		}
		
		
	}

}
