
public class DisplayThread implements Runnable
{
	private String response;

	public DisplayThread(String response)
	{
		this.response = response;
	}
	
	public void run() 
	{	
		do 
		{
			try
			{
				Jframe.appendArea(response);
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				return;
			}
		}while(true);
	}

}
