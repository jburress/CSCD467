
public class Waiter implements Runnable
{
	public void run() 
	{
		boolean checker = false;
		while(!checker)
		{
			if(Thread.interrupted())
			{
				System.out.println("Printer has half its work done!");
				checker = true;
			}
		}
		System.out.println("Waiter is complete");
		
	}

}
